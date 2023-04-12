package cn.tofucat.toolkit.llc;

import cn.tofucat.toolkit.llc.matcher.DefaultNodeMatcher;
import cn.tofucat.toolkit.llc.matcher.LLCNodeMatcher;
import cn.tofucat.toolkit.llc.parser.DefaultLLCParser;
import cn.tofucat.toolkit.llc.parser.LLCNode;
import cn.tofucat.toolkit.llc.parser.LLCParser;

/**
 * zzzxb
 * 2023/3/31
 */
public class LLC {

    public static final LLC DEFAULT = new LLC();
    private final LLCParser parser;
    private final LLCNodeMatcher matcher;

    public LLC() {
        this.parser = DefaultLLCParser.INSTANCE;
        this.matcher = DefaultNodeMatcher.INSTANCE;
    }
    public LLC(LLCParser parser, LLCNodeMatcher matcher) {
        this.parser = parser;
        this.matcher = matcher;
    }

    public LLCNode parser(String llc) {
        return parser.parser(llc);
    }

    public String convert(LLCNode node) {
        return matcher.convert(node);
    }

    public String exec(String llc) {
        return convert(parser(llc));
    }
}
