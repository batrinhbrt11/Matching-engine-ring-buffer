package vn.demo.starter.service.dto;

import vn.demo.starter.entity.enumeration.OrderStatus;
import vn.demo.starter.entity.enumeration.OrderType;

import java.math.BigDecimal;

public record P2POrderDto(
    Long id,
    OrderType orderType,
    OrderStatus orderStatus,
    String price,
    String remainingAmount,
    String totalAmount,
    AssetDto asset
) {
}
