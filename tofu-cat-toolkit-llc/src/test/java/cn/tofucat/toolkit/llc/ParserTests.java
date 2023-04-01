package cn.tofucat.toolkit.llc;

import cn.tofucat.toolkit.llc.parser.DefaultLLCParser;
import cn.tofucat.toolkit.llc.parser.LLCNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * zzzxb
 * 2023/3/31
 */
public class ParserTests {

    @Test
    public void matcherSkinsTests() {
        String skins = "{MD5[内容]}";
        DefaultLLCParser defaultLLCParser = new DefaultLLCParser();
        String result = defaultLLCParser.matcherSkins(skins);
        System.out.println(result);
    }

    @Test
    public void matcherHeartTests() {
        String heart = "MD5[{BASE64[内容]}]";
        DefaultLLCParser defaultLLCParser = new DefaultLLCParser();
        LLCNode llcNode = defaultLLCParser.matcherHeart(heart);
        System.out.println(llcNode);
    }
}
