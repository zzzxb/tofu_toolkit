package xyz.zzzxb.tofu.common;

import org.junit.Test;
import xyz.zzzxb.tofu.common.util.DateUtils;

import java.util.Date;

/**
 * zzzxb
 * 2023/12/14
 */
public class DateUtilTest {

    @Test
    public void generate() {
        String s = DateUtils.formatDate("yyyyMMddHHmmssSSS", new Date());
        System.out.println(s);
    }
}
