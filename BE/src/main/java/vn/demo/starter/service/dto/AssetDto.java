package vn.demo.starter.service.dto;


public record AssetDto(
        Long id,
        CoinDto coin,
        boolean nativeToken,
        ChainDto chain
) {
}
