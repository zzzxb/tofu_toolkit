package cn.tofucat.toolkit;

import cn.tofucat.toolkit.util.PathUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;


@Slf4j
public class PathUtilsTest {

    @Test
    public void formatForward() {
        Assert.assertEquals("http://zzzxb.xyz/a/c", PathUtils.formatForward("http://zzzxb.xyz/a/b../c"));
    }
}
