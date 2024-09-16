package vn.demo.starter.service;

import java.math.BigDecimal;
import java.util.List;

import vn.demo.starter.entity.P2POrder;

public interface P2PTransactionService {
    void create(P2POrder buyOrder, P2POrder sellOrder, BigDecimal quantity);

    List<P2POrder> getOpenOrders();
}
