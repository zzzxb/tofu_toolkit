package cn.tofucat.toolkit.util;

/**
 * zzzxb
 * 2023/8/10
 */
public final class StringUtils {

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String format(String msg, Object ... args) {
        if (StringUtils.isBlank(msg) || !msg.contains("{}") || args == null) {
            return msg;
        }

        for (Object arg : args) {
            if (!msg.contains("{}")) {
                return msg;
            }
            msg = msg.replaceFirst("\\{\\}", arg.toString());
        }

        return msg;
    }
}
