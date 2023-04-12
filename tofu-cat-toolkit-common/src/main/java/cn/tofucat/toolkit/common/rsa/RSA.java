package cn.tofucat.toolkit.common.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSA {
    private int keySize = 2048;
    private KeyPair keyPair;

    public RSA() {
        init();
    }

    public RSA(int keySize) {
        this.keySize = Math.max(2048, keySize);
        init();
    }

    private void init() {
        KeyPairGenerator rsa = null;
        try {
            rsa = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        rsa.initialize(keySize);
        keyPair = rsa.generateKeyPair();
    }

    public RSAMode publicKey() {
        return new RSAMode(keyPair, KeyEnums.PUBLIC_KEY);
    }

    public RSAMode privateKey() {
        return new RSAMode(keyPair, KeyEnums.PRIVATE_KEY);
    }
}