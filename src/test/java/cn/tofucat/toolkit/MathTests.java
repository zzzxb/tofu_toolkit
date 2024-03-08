package cn.tofucat.toolkit;

import cn.tofucat.toolkit.util.MathUtils;
import org.junit.Test;

import java.math.RoundingMode;

/**
 * zzzxb
 * 2024/3/8
 */
public class MathTests {

    @Test
    public void division() {
        System.out.println(MathUtils.rate(3, 10, 2, RoundingMode.UP) + "%");

    }
}
