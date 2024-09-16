package vn.demo.starter.service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import vn.demo.starter.entity.enumeration.OrderType;

import java.math.BigDecimal;

@Data
public class P2POrderRequestDto {

    @NotNull
    private OrderType orderType;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigDecimal assetAmount;

    @NotNull
    private Long assetId;

}
