package cn.tofucat.toolkit;

import cn.tofucat.toolkit.algorithm.RSA;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * zzzxb
 * 2023/12/11
 */
public class RSATests {

    @Test
    public void write() throws Exception {
        RSA.getInstance().writeKey("etc");
    }

    @Test
    public void read() {
        RSAPublicKey publicKey = RSA.getInstance().readKey("etc/pub.key", RSA.Key.PublicKey).getRsaPublicKey();
        Assert.assertNotNull(publicKey);

        RSAPrivateKey privateKey = RSA.getInstance().readKey("etc/pri.key", RSA.Key.PrivateKey).getRsaPrivateKey();
        Assert.assertNotNull(privateKey);
    }

    @Test
    public void encode() throws Exception {
        String message = "Hello";
        RSAPublicKey publicKey = RSA.getInstance().readKey("etc/pub.key", RSA.Key.PublicKey).getRsaPublicKey();
        RSAPrivateKey privateKey = RSA.getInstance().readKey("etc/pri.key", RSA.Key.PrivateKey).getRsaPrivateKey();


        Cipher rsa = Cipher.getInstance("RSA");
        rsa.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] bytes1 = rsa.doFinal(messageBytes);

        String s = Base64.getEncoder().encodeToString(bytes1);
        System.out.println(s);

        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = decryptCipher.doFinal(bytes1);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }

}
