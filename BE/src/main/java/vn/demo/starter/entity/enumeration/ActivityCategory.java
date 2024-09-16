package vn.demo.starter.entity.enumeration;

import vn.demo.starter.entity.LogCategoryResponseDto;

import java.util.List;
import java.util.stream.Stream;

public enum ActivityCategory {

    AUTHENTICATION("Authentication"),
    USER_ACCOUNT("User Account"),
    ADMIN("Admin");

    public final String label;

    private ActivityCategory(String label) {
        this.label = label;
    }

    public static List<LogCategoryResponseDto> toLogCategoryResponseDtoList() {
        return Stream.of(ActivityCategory.values())
                .map(activityCategory -> new LogCategoryResponseDto(activityCategory.name(), activityCategory.label))
                .toList();
    }
}
