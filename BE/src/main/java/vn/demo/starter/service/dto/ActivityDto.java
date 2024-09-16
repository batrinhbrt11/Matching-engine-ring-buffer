package vn.demo.starter.service.dto;

import vn.demo.starter.entity.enumeration.ActivityAction;
import vn.demo.starter.entity.enumeration.ActivityCategory;

import java.time.Instant;

public record ActivityDto(
        Long userId,
        ActivityAction action,
        String detail,
        ActivityCategory category,
        String userAgent,
        String userIp,
        Instant timestamp
) {
}
