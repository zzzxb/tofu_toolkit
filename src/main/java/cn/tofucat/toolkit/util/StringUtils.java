package cn.tofucat.toolkit.util;

/**
 * zzzxb
 * 2023/8/10
 */
public class StringUtils {

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
