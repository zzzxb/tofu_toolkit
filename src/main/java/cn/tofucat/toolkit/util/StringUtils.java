package cn.tofucat.toolkit.util;

/**
 * zzzxb
 * 2023/10/8
 */
public class StringUtils {
    public static boolean isBlack(String str) {
        return null == str || str.trim().isEmpty();
    }

    public static boolean isNotBlack(String str) {
        return !isBlack(str);
    }
}
