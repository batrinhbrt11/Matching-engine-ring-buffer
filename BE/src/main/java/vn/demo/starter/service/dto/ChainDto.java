package vn.demo.starter.service.dto;

import java.util.List;

public record ChainDto(
        Long id,
        String symbol,
        String image,
        String name
) {
}
