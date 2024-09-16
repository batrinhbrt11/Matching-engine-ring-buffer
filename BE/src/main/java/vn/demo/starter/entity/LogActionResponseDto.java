package vn.demo.starter.entity;

import vn.demo.starter.entity.enumeration.ActivityCategory;

public record LogActionResponseDto(
        String value,
        String label,
        ActivityCategory category
) {
}
