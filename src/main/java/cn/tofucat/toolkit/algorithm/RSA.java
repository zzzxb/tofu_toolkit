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

    public KeyPair generatorKey(int len) throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
        generator.initialize(len < 1024 ? KEY_SIZE : len);
        return generator.generateKeyPair();
    }

    public void writeKey(int len, String outPath) throws NoSuchAlgorithmException {
        KeyPair keyPair = generatorKey(len);

        PublicKey pubKey = keyPair.getPublic();
        FileUtils.write(Base64.getEncoder().encodeToString(pubKey.getEncoded()).getBytes(StandardCharsets.UTF_8), outPath + File.separator + "pub.key");

        PrivateKey priKey = keyPair.getPrivate();
        FileUtils.write(Base64.getEncoder().encodeToString(priKey.getEncoded()).getBytes(StandardCharsets.UTF_8), outPath + File.separator + "pri.key");
    }


    public RsaKey readKey(File file, Key keyType) {
        String keyBase64 = new String(FileUtils.read(file), StandardCharsets.UTF_8);
        return readKey(keyBase64, keyType);
    }


    public RsaKey readKey(String key, Key keyType) {
        byte[] bytes = Base64.getDecoder().decode(key);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            if (keyType == Key.PrivateKey) {
                PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
                return new RsaKey((RSAPrivateKey) keyFactory.generatePrivate(spec));
            }
            EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
            return new RsaKey((RSAPublicKey) keyFactory.generatePublic(spec));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String encode(File keyPath, RSA.Key keyType, String content) {
        return encode(readKey(keyPath, keyType), keyType, content);
    }

    public String encode(String key, RSA.Key keyType, String content) {
        return encode(readKey(key, keyType), keyType, content);
    }

    private String encode(RsaKey rsaKey, RSA.Key keyType, String content) {
        try {
            Cipher rsa = Cipher.getInstance(ALGORITHM);
            rsa.init(Cipher.ENCRYPT_MODE, keyType == Key.PublicKey ? rsaKey.getRsaPublicKey() : rsaKey.getRsaPrivateKey());
            byte[] bytes = rsa.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(bytes);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decode(File keyPath, RSA.Key keyType, String content) {
        return decode(readKey(keyPath, keyType), keyType, content);
    }

    public String decode(String key, RSA.Key keyType, String content) {
        return decode(readKey(key, keyType), keyType, content);
    }

    private String decode(RsaKey rsaKey, RSA.Key keyType, String content) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keyType == Key.PublicKey ? rsaKey.getRsaPublicKey() : rsaKey.getRsaPrivateKey());
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(content));
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
