package xyz.zzzxb.tofu.common;

import org.junit.Test;
import xyz.zzzxb.tofu.common.util.MathUtils;

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
