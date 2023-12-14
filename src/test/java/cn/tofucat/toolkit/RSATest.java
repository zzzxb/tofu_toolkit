package cn.tofucat.toolkit;

import cn.tofucat.toolkit.algorithm.RSA;
import org.junit.BeforeClass;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

/**
 * zzzxb
 * 2023/12/14
 */
public class RSATest {

    @BeforeClass
    public static void write() throws NoSuchAlgorithmException {
        RSA.getInstance().writeKey(1024, "etc");
    }

    @Test
    public void a () {
        String encode = RSA.getInstance().encode("etc/pri.key", RSA.Key.PrivateKey, "你好");
        String decode = RSA.getInstance().decode("etc/pub.key", RSA.Key.PublicKey, encode);
        System.out.println(decode);
    }
}
