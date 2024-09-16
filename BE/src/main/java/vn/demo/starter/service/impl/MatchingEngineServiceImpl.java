package vn.demo.starter.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.demo.starter.entity.P2POrder;
import vn.demo.starter.entity.enumeration.OrderStatus;
import vn.demo.starter.entity.enumeration.OrderType;
import vn.demo.starter.repository.P2POrderRepository;
import vn.demo.starter.service.MatchingEngineService;
import vn.demo.starter.service.P2PTransactionService;
import vn.demo.starter.util.BalanceUtils;
import vn.demo.starter.util.RingBuffer;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchingEngineServiceImpl implements MatchingEngineService {

    private RingBuffer<P2POrder> orderBuffer;
    // Priority queue for buy orders with price descending order
    private final PriorityQueue<P2POrder> buyOrders = new PriorityQueue<>(Comparator.comparing(P2POrder::getPrice).reversed());
    // Priority queue for sell orders with price ascending order
    private final PriorityQueue<P2POrder> sellOrders = new PriorityQueue<>(Comparator.comparing(P2POrder::getPrice));

    private final P2PTransactionService p2PTransactionService;
    private final P2POrderRepository p2POrderRepository;

    @Value("${application.ring-buffer.size}")
    private int bufferSize;

    /**
     * Init buffer size
     */
    @PostConstruct
    public void init() {
        this.orderBuffer = new RingBuffer<>(bufferSize); // init buffer size
    }


    /**
     * Receive order from order service
     * @param order
     */
    @Override
    public void receiveOrder(P2POrder order) {
        orderBuffer.push(order); // add order to ring buffer
    }

    /**
     * Load unprocessed orders from order service
     * @param unprocessedOrders
     */
    @Override
    @Transactional(readOnly = true)
    public void loadUnprocessedOrders(List<P2POrder> unprocessedOrders) {
        for (P2POrder order : unprocessedOrders) {
            orderBuffer.push(order);
        }
    }

    /**
     * Process orders from ring buffer
     */
    @Override
    @Transactional
    public void processOrders() {
        // Loop until no more orders in buffer
        while (!orderBuffer.isEmpty()) {
            // Get order from buffer
            P2POrder order = orderBuffer.pop();
            log.info("[Start] - Process Order {} type {}", order.getId(), order.getOrderType());
            if (order.getOrderType() == OrderType.BUY) {
                matchOrder(order, sellOrders, buyOrders);
            } else {
                matchOrder(order, buyOrders, sellOrders);
            }
            log.info("[Done] - Process Order {} type {}", order.getId(), order.getOrderType());
        }
    }

    /**
     * Match order
     * @param incomingOrder
     * @param oppositeOrders
     * @param sameSideOrders
     */ 
    private void matchOrder(P2POrder incomingOrder, PriorityQueue<P2POrder> oppositeOrders, PriorityQueue<P2POrder> sameSideOrders) {
        // Loop until no more orders can be matched
        while (!oppositeOrders.isEmpty() && canMatch(incomingOrder, oppositeOrders.peek())) {
            P2POrder bestMatch = oppositeOrders.poll();
            BigDecimal tradedQuantity = incomingOrder.getRemainingAmount().min(bestMatch.getRemainingAmount());

            // Update remaining amount of incoming order and best match
            incomingOrder.setRemainingAmount(incomingOrder.getRemainingAmount().subtract(tradedQuantity));
            bestMatch.setRemainingAmount(bestMatch.getRemainingAmount().subtract(tradedQuantity));

            // Record transaction
            recordTransaction(incomingOrder, bestMatch, tradedQuantity);

            // If best match still has remaining amount, add it back to opposite orders
            if (BalanceUtils.isGT(bestMatch.getRemainingAmount(),BigDecimal.ZERO) ) {
                oppositeOrders.offer(bestMatch);
            }
            // If best match has no remaining amount, set order status to success
            else {
                bestMatch.setOrderStatus(OrderStatus.SUCCESS);
            }
            // Save best match to database
            p2POrderRepository.save(bestMatch);

            // If incoming order has no remaining amount, set order status to success
            if (incomingOrder.getRemainingAmount().compareTo(BigDecimal.ZERO) == 0) {
                incomingOrder.setOrderStatus(OrderStatus.SUCCESS);
                p2POrderRepository.save(incomingOrder);
                return;
            }
        }
        // If incoming order still has remaining amount, add it back to same side orders
        if (BalanceUtils.isGT(incomingOrder.getRemainingAmount(),BigDecimal.ZERO) ) {
            sameSideOrders.offer(incomingOrder);
            incomingOrder.setOrderStatus(OrderStatus.OPEN);
        }

        // Save incoming order to database
        p2POrderRepository.save(incomingOrder);
    }

    /**
     * Check if two orders can match
     * @param incomingOrder
     * @param existingOrder
     * @return
     */
    /**
     * Different between buy and sell order
     * Buy order: price >= existingOrder.getPrice()
     * Sell order: price <= existingOrder.getPrice()
     * User can not buy or sell to himself
     * Asset must be the same
     * @param incomingOrder
     * @param existingOrder
     * @return
     */
    private boolean canMatch(P2POrder incomingOrder, P2POrder existingOrder) {
        // User can not buy or sell to himself
        if(incomingOrder.getUser().getId().equals(existingOrder.getUser().getId())) {
            return false;
        }
        // Asset must be the same
        if(!incomingOrder.getAsset().getId().equals(existingOrder.getAsset().getId())){
            return false;
        }
        // Different between buy and sell order
        if (incomingOrder.getOrderType() == OrderType.BUY) {
            // Buy order: price >= existingOrder.getPrice()
            return BalanceUtils.isGTE(incomingOrder.getPrice(), existingOrder.getPrice());
        } else {
            // Sell order: price <= existingOrder.getPrice()
            return BalanceUtils.isLTE(incomingOrder.getPrice(), existingOrder.getPrice());
        }
    }

    /** 
     * Save transaction to database
     * @param incomingOrder
     * @param bestMatch
     * @param quantity
     */
    private void recordTransaction(P2POrder incomingOrder, P2POrder bestMatch, BigDecimal quantity) {
        if(incomingOrder.getOrderType().equals(OrderType.BUY)) {
            p2PTransactionService.create(incomingOrder, bestMatch, quantity);
        }else {
            p2PTransactionService.create(bestMatch, incomingOrder, quantity);
        }

    }
}
