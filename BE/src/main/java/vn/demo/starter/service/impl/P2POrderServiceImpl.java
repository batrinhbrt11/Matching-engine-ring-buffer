package vn.demo.starter.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.demo.starter.entity.Asset;
import vn.demo.starter.entity.P2POrder;
import vn.demo.starter.entity.User;
import vn.demo.starter.entity.UserCryptoWallet;
import vn.demo.starter.entity.UserWallet;
import vn.demo.starter.entity.enumeration.OrderStatus;
import vn.demo.starter.entity.enumeration.OrderType;
import vn.demo.starter.repository.P2POrderRepository;
import vn.demo.starter.security.SecurityUtils;
import vn.demo.starter.service.AssetService;
import vn.demo.starter.service.MatchingEngineService;
import vn.demo.starter.service.P2POrderService;
import vn.demo.starter.service.UserCryptoWalletService;
import vn.demo.starter.service.UserService;
import vn.demo.starter.service.UserWalletService;
import vn.demo.starter.service.dto.P2POrderDto;
import vn.demo.starter.service.dto.request.P2POrderRequestDto;
import vn.demo.starter.service.mapper.AppMapper;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class P2POrderServiceImpl implements P2POrderService {

    private final P2POrderRepository p2POrderRepository;

    private final UserService userService;
    private final UserWalletService userWalletService;
    private final UserCryptoWalletService userCryptoWalletService;
    private final AssetService assetService;
    private final MatchingEngineService matchingEngineService;

    private final AppMapper appMapper;

    @Override
    @Transactional
    public void createOrder(P2POrderRequestDto request) {
        User user = userService.getUser(SecurityUtils.getCurrentUserId());
        Asset asset = assetService.getById(request.getAssetId());

        if(request.getOrderType().equals(OrderType.BUY)){
            this.buyOrder(request, user);
        }
        else{
            this.sellOrder(request, user,asset);
        }

        P2POrder newOrder = p2POrderRepository.save(
                                P2POrder.builder()
                                        .asset(asset)
                                        .orderType(request.getOrderType())
                                        .orderStatus(OrderStatus.OPEN)
                                        .price(request.getPrice())
                                        .totalAmount(request.getAssetAmount())
                                        .remainingAmount(request.getAssetAmount())
                                        .user(user)
                                        .build()
        );
        // Add order to matching engine
        matchingEngineService.receiveOrder(newOrder);
        log.info("Create order success, orderId: {}", newOrder.getId());
    }

    @Override
    public List<P2POrderDto> getOpenOrder() {
        return p2POrderRepository
                .findByOrderStatusIn(List.of(OrderStatus.OPEN))
                .stream()
                .map(appMapper::toDto)
                .toList();
    }

    /**
     * Freeze user's balance
     */
    private void buyOrder(P2POrderRequestDto request,  User user) {
        UserWallet userWallet = userWalletService.getWalletByUser(user);
        BigDecimal calculateBalance = request.getAssetAmount().multiply(request.getPrice());
        userWalletService.checkAvailableBalance(userWallet.getId(), calculateBalance);
        userWalletService.updateBalance(userWallet.getId(), calculateBalance.multiply(BigDecimal.valueOf(-1)), calculateBalance);
    }

    /**
     * Freeze user's balance when create sell order 
     */
    private void sellOrder(P2POrderRequestDto request,  User user, Asset asset) {

        UserCryptoWallet userWallet = userCryptoWalletService.getWalletByUserAndAsset(user, asset);
        userCryptoWalletService.checkAvailableBalance(userWallet.getId(), request.getAssetAmount());
        userCryptoWalletService.updateBalance(userWallet.getId(), request.getAssetAmount().multiply(BigDecimal.valueOf(-1)), request.getAssetAmount());
    }

    /**
     * Load open order in db when application start
     */
    @PostConstruct
    public void onApplicationStart() {
        log.info("Load open order in db");
        List<P2POrder> p2POrders = p2POrderRepository
                .findByOrderStatusIn(List.of(OrderStatus.OPEN));
        matchingEngineService.loadUnprocessedOrders(p2POrders);
    }
}
