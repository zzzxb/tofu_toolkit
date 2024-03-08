package cn.tofucat.toolkit.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * zzzxb
 * 2024/3/8
 */
public class MathUtils {
    public static String rate(long x, long y, int scale, RoundingMode roundingMode) {
        return rate(String.valueOf(x), String.valueOf(y), scale, roundingMode);
    }
    public static String rate(double x, double y, int scale, RoundingMode roundingMode) {
        return rate(String.valueOf(x), String.valueOf(y), scale, roundingMode);
    }

    public static String rate(String x, String y, int scale, RoundingMode roundingMode) {
        CheckParamUtils.isBlack(x).throwMessage("x number is null", x);
        CheckParamUtils.isBlack(x).throwMessage("y number is null", y);
        CheckParamUtils.isBlack(y).throwMessage("divisor cannot be zero /0");
        CheckParamUtils.notMatchRegex("[0-9.]+", x).throwMessage("{} number format error", x);
        CheckParamUtils.notMatchRegex("[0-9.]+", y).throwMessage("{} number format error", y);

        if (Integer.parseInt(x) == 0) {
            return "0";
        }

        BigDecimal oneHundred = new BigDecimal("100");
        BigDecimal dividends = new BigDecimal(x);
        BigDecimal divisor = new BigDecimal(y);
        BigDecimal percentage = dividends.divide(divisor, scale, roundingMode).multiply(oneHundred);
        return percentage.toPlainString();
    }
}
