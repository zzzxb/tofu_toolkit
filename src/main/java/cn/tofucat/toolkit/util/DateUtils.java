package cn.tofucat.toolkit.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * zzzxb
 * 2023/8/25
 */
public class DateUtils {

    public static String currentDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }
}
