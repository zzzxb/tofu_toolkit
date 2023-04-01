package cn.tofucat.toolkit.llc.parser;


import cn.tofucat.toolkit.llc.consts.GlobalConfig;

import java.util.regex.Pattern;

/**
 * zzzxb
 * 2023/3/31
 */
public abstract class LLCParserAbstract implements LLCParser {
    protected final Pattern PATTERN_SKINS = Pattern.compile(GlobalConfig.LLC_SKINS_REGEX);
    protected final Pattern PATTERN_HEART = Pattern.compile(GlobalConfig.LLC_HEART_REGEX);
}
