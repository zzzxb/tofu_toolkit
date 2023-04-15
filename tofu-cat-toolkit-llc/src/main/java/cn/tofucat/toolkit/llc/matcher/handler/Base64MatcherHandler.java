package cn.tofucat.toolkit.llc.matcher.handler;

import cn.tofucat.toolkit.common.annotation.Register;
import cn.tofucat.toolkit.llc.matcher.LLCNodeMatcher;
import cn.tofucat.toolkit.llc.parser.LLCNode;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Register("BASE64")
public class Base64MatcherHandler implements LLCNodeMatcher {
    @Override
    public String convert(LLCNode node) {
        return Base64.getEncoder().encodeToString(node.getValue().getBytes(StandardCharsets.UTF_8));
    }
}
