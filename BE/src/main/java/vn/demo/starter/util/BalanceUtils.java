package vn.demo.starter.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BalanceUtils {
    private BalanceUtils() {}

    public static String toString(BigDecimal balance) {
        return balance
                .setScale(18, RoundingMode.HALF_UP)
                .stripTrailingZeros()
                .toPlainString();
    }

    public static boolean isInsufficientBalance(BigDecimal balance, BigDecimal spentAmount) {
        return balance.compareTo(spentAmount) < 0;
    }

    public static boolean isLT(BigDecimal src, BigDecimal dest) {
        return src.compareTo(dest) < 0;
    }

    public static boolean isLTE(BigDecimal src, BigDecimal dest) {
        return src.compareTo(dest) <= 0;
    }

    public static boolean isGT(BigDecimal src, BigDecimal dest) {
        return src.compareTo(dest) > 0;
    }

    public static boolean isGTE(BigDecimal src, BigDecimal dest) {
        return src.compareTo(dest) >= 0;
    }


    public static BigDecimal convertOddsAmericanToDecimal(BigDecimal americanPrice) {
        if (americanPrice.compareTo(BigDecimal.ZERO) >= 0) {
            return americanPrice.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP).add(BigDecimal.ONE);
        } else {
            return BigDecimal.valueOf(100).divide(americanPrice.multiply(BigDecimal.valueOf(-1)), 2, RoundingMode.HALF_UP).add(BigDecimal.ONE);
        }
    }
}
