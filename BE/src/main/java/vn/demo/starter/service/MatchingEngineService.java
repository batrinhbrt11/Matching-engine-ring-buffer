package vn.demo.starter.service;

import vn.demo.starter.entity.P2POrder;

import java.util.List;

public interface MatchingEngineService {

    void processOrders();

    void receiveOrder(P2POrder order);

    void loadUnprocessedOrders(List<P2POrder> unprocessedOrders);

}
