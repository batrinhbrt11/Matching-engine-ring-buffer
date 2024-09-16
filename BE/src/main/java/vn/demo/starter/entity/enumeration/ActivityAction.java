package vn.demo.starter.entity.enumeration;

import vn.demo.starter.entity.LogActionResponseDto;

import java.util.List;
import java.util.stream.Stream;

import static vn.demo.starter.entity.enumeration.ActivityCategory.AUTHENTICATION;
import static vn.demo.starter.entity.enumeration.ActivityCategory.USER_ACCOUNT;

public enum ActivityAction {
    LOGIN("Login",AUTHENTICATION),
    LOGIN_FAILED("Login Failed", AUTHENTICATION),
    LOGOUT("Logout",AUTHENTICATION),
    REGISTER("Register",USER_ACCOUNT),
    RESET_PASSWORD("Reset Password",AUTHENTICATION),
    CHANGE_PASSWORD("Change Password",AUTHENTICATION),
    ACTIVE_USER("Active user",USER_ACCOUNT);

    public final String label;
    public final ActivityCategory category;

    private ActivityAction(String label, ActivityCategory category) {
        this.label = label;
        this.category = category;
    }

    public static List<LogActionResponseDto> toLogActionResponseDtoList() {
        return Stream.of(ActivityAction.values())
                .map(activityAction -> new LogActionResponseDto(activityAction.name(), activityAction.label, activityAction.category))
                .toList();
    }
}
