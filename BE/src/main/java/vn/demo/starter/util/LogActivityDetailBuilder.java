package vn.demo.starter.util;

public final class LogActivityDetailBuilder {

    private LogActivityDetailBuilder() {}

    public static String registerDetail(Long userId) {
        return "User %s register successfully.".formatted(userId);
    }

    public static String loginDetail(Long userId){
        return "User %s logged in successfully.".formatted(userId);
    }

    public static String loginBlockByAdminDetail(Long userId){
        return "User %s is blocked by admin!".formatted(userId);
    }

    public static String loginBlockTimeDetail(Long userId){
        return "User %s is in block time.".formatted(userId);
    }

    public static String loginInactiveDetail(Long userId){
        return "User %s is inactive.".formatted(userId);
    }

    public static String loginWrongPasswordDetail(Long userId){
        return "User %s wrong password.".formatted(userId);
    }

    public static String logoutDetail(Long userId){
        return "User %s logged out.".formatted(userId);
    }

    public static String changePasswordDetail(Long userId){
        return "User %s changed the password.".formatted(userId);
    }

    public static String changeNameDetail(Long userId){
        return "User %s changed the name.".formatted(userId);
    }

    public static String resetPasswordDetail(Long userId){
        return "User %s reset the password.".formatted(userId);
    }
}

