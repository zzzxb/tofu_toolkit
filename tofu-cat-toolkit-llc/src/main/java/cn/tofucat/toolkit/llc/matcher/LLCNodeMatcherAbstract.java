package cn.tofucat.toolkit.llc.matcher;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public abstract class LLCNodeMatcherAbstract implements LLCNodeMatcher {
    private final static ConcurrentHashMap<String, LLCNodeMatcher> REGISTER = new ConcurrentHashMap<>();

    protected LLCNodeMatcher pattern(String key) {
        return REGISTER.get(key);
    }

    public static void register(String key, LLCNodeMatcher matcher) {
        log.info("Register LLCNodeMatcher[{}]: {}", key, matcher);
        REGISTER.put(key, matcher);
    }
}
