package xyz.zzzxb.tofu.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import xyz.zzzxb.tofu.common.util.PathUtils;


@Slf4j
public class PathUtilsTest {

    @Test
    public void formatForward() {
        Assert.assertEquals("http://zzzxb.xyz/a/c", PathUtils.formatForward("http://zzzxb.xyz/a/b../c"));
    }
}
