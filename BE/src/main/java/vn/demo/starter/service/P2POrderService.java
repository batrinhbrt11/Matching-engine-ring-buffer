package vn.demo.starter.service;

import vn.demo.starter.service.dto.P2POrderDto;
import vn.demo.starter.service.dto.request.P2POrderRequestDto;

import java.util.List;

public interface P2POrderService {

    void createOrder(P2POrderRequestDto request);

    List<P2POrderDto> getOpenOrder();
}
