package cn.tofucat.toolkit.llc;

import org.junit.jupiter.api.Test;

public class LLCTests {

    @Test
    public void llc() {
        LLC llc = new LLC();
        System.out.println(llc.exec("{MD5[llc]}"));
        System.out.println(llc.exec("{md5[llc]}"));
        System.out.println(llc.exec("{BASE64[{md5[llc]}]}"));
        System.out.println(llc.exec("{base64[{MD5[llc]}]}"));
    }
}
