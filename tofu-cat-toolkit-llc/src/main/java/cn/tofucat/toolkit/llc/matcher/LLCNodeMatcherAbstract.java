package cn.tofucat.toolkit.llc.matcher;


public abstract class LLCNodeMatcherAbstract implements LLCNodeMatcher {

    static {
        MatcherLoad.INSTANCE.load("cn.tofucat.toolkit.llc.matcher.handler");
    }

    protected LLCNodeMatcher pattern(String key) {
        return LLCMatcherHandlerRegister.get(key.toUpperCase());
    }

    protected static void register(String key, LLCNodeMatcher matcher) {
        LLCMatcherHandlerRegister.register(key, matcher);
    }
}
