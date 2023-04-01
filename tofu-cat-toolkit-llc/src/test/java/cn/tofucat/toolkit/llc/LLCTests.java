package cn.tofucat.toolkit.llc;

import cn.tofucat.toolkit.llc.matcher.DefaultNodeMatcher;
import cn.tofucat.toolkit.llc.parser.DefaultLLCParser;
import cn.tofucat.toolkit.llc.parser.LLCNode;
import org.junit.jupiter.api.Test;

public class LLCTests {

    @Test
    public void llc() {
        String code = "{md5[你好]}";
        DefaultLLCParser defaultLLCParser = new DefaultLLCParser();
        LLCNode parser = defaultLLCParser.parser(code);
        System.out.println(parser);

        String convert = DefaultNodeMatcher.INSTANCE.convert(parser);
        System.out.println(convert);
    }
}
