package cn.tofucat.toolkit.llc.parser;


import java.util.regex.Matcher;

/**
 * zzzxb
 * 2023/3/31
 */
public class DefaultLLCParser extends LLCParserAbstract {

    @Override
    public LLCNode parser(String llc) {
        return matcherHeart(matcherSkins(llc.trim()));
    }

    /**
     * 伪代码外层格式匹配解析
     * @param skins {MD5[内容]}
     * @return MD5[内容]
     */
    public String matcherSkins(String skins) {
        if (null == skins) {
            return null;
        }

        Matcher matcher = PATTERN_SKINS.matcher(skins);
        if (matcher.find()) {
            String group = matcher.group();
            return group.substring(1, group.length() - 1);
        }
        return null;
    }

    /**
     * 伪代码内层格式匹配解析
     * @param heart MD5[内容]
     * @return {@link LLCNode}
     */
    public LLCNode matcherHeart(String heart) {
        if (null == heart) {
            return null;
        }

        Matcher matcher = PATTERN_HEART.matcher(heart);
        if (matcher.find()) {
            String group = matcher.group();
            String[] parts = group.split("\\[", 2);
            String key = parts[0].trim();
            String value = parts[1].substring(0, parts[1].length() - 1).trim();
            return new LLCNode(key, value, matcherHeart(matcherSkins(value)));
        }
        return null;
    }
}
