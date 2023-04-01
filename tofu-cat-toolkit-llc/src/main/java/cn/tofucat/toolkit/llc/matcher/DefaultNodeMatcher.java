package cn.tofucat.toolkit.llc.matcher;

import cn.tofucat.toolkit.llc.parser.LLCNode;

import java.util.regex.Pattern;

public class DefaultNodeMatcher extends LLCNodeMatcherAbstract {
    public final static DefaultNodeMatcher INSTANCE = new DefaultNodeMatcher();

    @Override
    public String convert(LLCNode node) {
        if (node.hasNext()) {
            node.setValue(convert(node.getNode()));
        }

        String value = pattern(node.getKey()).convert(node);
        if (Pattern.matches(".*[a-z].*", node.getKey())) {
            return value.toLowerCase();
        }
        return value.toUpperCase();
    }

}
