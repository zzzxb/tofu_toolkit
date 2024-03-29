package xyz.zzzxb.tofu.common;

import org.junit.BeforeClass;
import org.junit.Test;
import xyz.zzzxb.tofu.common.algorithm.RSA;

import java.io.File;
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
        String encode = RSA.getInstance().encode(new File("etc/pri.key"), RSA.Key.PrivateKey, "你好");
        String decode = RSA.getInstance().decode(new File("etc/pub.key"), RSA.Key.PublicKey, encode);
        System.out.println(decode);
    }
}
