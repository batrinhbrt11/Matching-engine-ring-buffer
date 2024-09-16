package vn.demo.starter.service.dto;

import vn.demo.starter.util.BalanceUtils;

public record WalletDto(
        Long id,
        String availableBalance,
        String freezeBalance,
        AssetDto assetDto
) {
}
