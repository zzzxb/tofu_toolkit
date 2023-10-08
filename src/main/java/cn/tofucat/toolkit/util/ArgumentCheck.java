package cn.tofucat.toolkit.util;

import java.util.Collection;

/**
 * zzzxb
 * 2023/8/10
 */
public class ArgumentCheck {
    public static void check(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void collectionIsEmpty(Collection<?> collection, String message) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
