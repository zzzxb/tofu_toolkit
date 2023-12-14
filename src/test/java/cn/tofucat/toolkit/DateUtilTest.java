package cn.tofucat.toolkit;

import cn.tofucat.toolkit.util.DateUtils;
import org.junit.Test;

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
