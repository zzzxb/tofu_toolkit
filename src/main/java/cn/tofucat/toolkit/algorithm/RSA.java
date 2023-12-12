package cn.tofucat.toolkit.algorithm;

import cn.tofucat.toolkit.util.FileUtils;
import lombok.Data;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.Base64;

/**
 * zzzxb
 * 2023/12/11
 */
public class RSA {
    private final static RSA INSTANCE = new RSA();
    public final static String ALGORITHM = "RSA";
    public final static Integer KEY_SIZE = 2048;


    private RSA() {}

    public static RSA getInstance() {
        return INSTANCE;
    }

    public KeyPair generatorKey() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
        generator.initialize(KEY_SIZE);
        return generator.generateKeyPair();
    }

    public void writeKey(String outPath) throws NoSuchAlgorithmException {
        KeyPair keyPair = generatorKey();

        PublicKey pubKey = keyPair.getPublic();
        FileUtils.write(Base64.getEncoder().encodeToString(pubKey.getEncoded()).getBytes(StandardCharsets.UTF_8), outPath + File.separator + "pub.key");

        PrivateKey priKey = keyPair.getPrivate();
        FileUtils.write(Base64.getEncoder().encodeToString(priKey.getEncoded()).getBytes(StandardCharsets.UTF_8), outPath + File.separator + "pri.key");
    }

    public RsaKey readKey(String filePath, Key key) {
        try {
            byte[] bytes = Base64.getDecoder().decode(FileUtils.read(filePath));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            if (key == Key.PrivateKey) {
                PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
                return new RsaKey((RSAPrivateKey) keyFactory.generatePrivate(spec));
            }
            EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
            return new RsaKey((RSAPublicKey) keyFactory.generatePublic(spec));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String encode(String publicKeyPath, String content) {
        return encode(RSA.getInstance().readKey(publicKeyPath, RSA.Key.PublicKey).getRsaPublicKey(), content);
    }

    public String encode(RSAPublicKey key, String content) {
        try {
            Cipher rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = rsa.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(bytes);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decode(String privateKeyPath, String content) {
        return decode(RSA.getInstance().readKey(privateKeyPath, RSA.Key.PrivateKey).getRsaPrivateKey(), content);
    }
    public String decode(RSAPrivateKey key, String content) {
        try {

            Cipher decryptCipher = Cipher.getInstance("RSA");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] bytes = decryptCipher.doFinal(Base64.getDecoder().decode(content));
            return new String(bytes, StandardCharsets.UTF_8);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public enum Key { PrivateKey, PublicKey }

    @Data
    public static class RsaKey {
        private RSAPrivateKey rsaPrivateKey;
        private RSAPublicKey rsaPublicKey;
        public RsaKey(RSAPrivateKey rsaPrivateKey) { this.rsaPrivateKey = rsaPrivateKey; }
        public RsaKey(RSAPublicKey rsaPublicKey) { this.rsaPublicKey = rsaPublicKey; }

        public RsaKey(RSAPrivateKey rsaPrivateKey, RSAPublicKey rsaPublicKey) {
            this.rsaPrivateKey = rsaPrivateKey;
            this.rsaPublicKey = rsaPublicKey;
        }
    }
}
