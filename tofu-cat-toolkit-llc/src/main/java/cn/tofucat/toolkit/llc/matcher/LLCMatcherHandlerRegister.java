package cn.tofucat.toolkit.llc.matcher;

import java.util.concurrent.ConcurrentHashMap;

/**
 * author: Zzzxb
 * date: 2023/4/15
 **/
public class LLCMatcherHandlerRegister {
    private final static ConcurrentHashMap<String, LLCNodeMatcher> REGISTER = new ConcurrentHashMap<>();

    public static void register(String key, LLCNodeMatcher matcher) {
        REGISTER.put(key, matcher);
    }

    public static LLCNodeMatcher get(String key) {
        return REGISTER.get(key);
    }
}
