package cn.tofucat.toolkit.llc.parser;

import cn.tofucat.toolkit.llc.LLC;
import cn.tofucat.toolkit.llc.consts.GlobalConfig;

import java.util.regex.Pattern;

/**
 * zzzxb
 * 2023/3/31
 */
public interface LLCParser extends LLC {

    LLCNode parser(String llc);
}
