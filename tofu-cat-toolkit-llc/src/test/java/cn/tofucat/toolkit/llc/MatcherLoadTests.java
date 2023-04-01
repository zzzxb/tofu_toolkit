package cn.tofucat.toolkit.llc;

import cn.tofucat.toolkit.llc.matcher.MatcherLoad;
import org.junit.jupiter.api.Test;

public class MatcherLoadTests {

    @Test
    public void load() {
        MatcherLoad.INSTANCE.load("cn");
    }
}
