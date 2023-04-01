package cn.tofucat.toolkit.llc.matcher;

import java.util.concurrent.ConcurrentHashMap;

public abstract class LLCNodeMatcherAbstract implements LLCNodeMatcher {
    private final static ConcurrentHashMap<String, LLCNodeMatcher> REGISTER = new ConcurrentHashMap<>();

    static {
        MatcherLoad.INSTANCE.load("cn.tofucat.toolkit.llc.handler");
    }

    protected LLCNodeMatcher pattern(String key) {
        return REGISTER.get(key.toUpperCase());
    }

    public static void register(String key, LLCNodeMatcher matcher) {
        REGISTER.put(key, matcher);
    }
}
