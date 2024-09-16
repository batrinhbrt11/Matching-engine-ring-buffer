package vn.demo.starter.service.dto;

public record CoinDto(
        Long id,
        String symbol,
        String image,
        String name
) {
}
