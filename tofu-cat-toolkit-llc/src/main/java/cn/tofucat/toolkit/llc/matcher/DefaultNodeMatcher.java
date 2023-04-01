package cn.tofucat.toolkit.llc.matcher;

import cn.tofucat.toolkit.llc.parser.LLCNode;

public class DefaultNodeMatcher extends LLCNodeMatcherAbstract {
    @Override
    public String convert(LLCNode node) {
        if (node.hasNext()) {
            node.setValue(convert(node.getNode()));
        }
        return pattern(node.getKey()).convert(node);
    }

}
