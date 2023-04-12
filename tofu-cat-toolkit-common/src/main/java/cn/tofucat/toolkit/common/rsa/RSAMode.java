package cn.tofucat.toolkit.common.rsa;


import cn.tofucat.toolkit.common.util.FileUtils;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAMode {
    private KeyPair keyPair;
    private KeyEnums keyEnums;

    public RSAMode() {
    }

    public RSAMode(KeyPair keyPair, KeyEnums keyEnums) {
        this.keyPair = keyPair;
        this.keyEnums = keyEnums;
    }

    /**
     * 读取RSA公钥
     * @return 公钥
     */
    public byte[] writeByte() {
        return writeByte(this.keyPair, this.keyEnums);
    }

    /**
     * 写出RSA密钥
     * @param keyPair 密钥对
     * @param keyEnums 密钥类型
     * @return 密钥
     */
    public byte[] writeByte(KeyPair keyPair, KeyEnums keyEnums) {
        return writeKey(keyPair, keyEnums);
    }

    /**
     * 写出RSA密钥
     */
    public String writeFormatStr() {
        return writeFormatStr(this.keyPair, this.keyEnums);
    }

    /**
     * 写出并格式化RSA密钥
     * @param keyPair 密钥对
     * @param keyEnums 密钥类型
     * @return 格式化后的密钥
     */
    public String writeFormatStr(KeyPair keyPair, KeyEnums keyEnums) {
        writeByte(keyPair, keyEnums);
        return formatRSA(Base64.getEncoder().encodeToString(writeByte()), keyEnums);
    }

    /**
     * 格式化RSA密钥
     * @param key 密钥
     * @param keyEnums 密钥类型
     * @return 格式化后的密钥
     */
    public String formatRSA(String key, KeyEnums keyEnums) {
        String keyName = keyEnums == KeyEnums.PUBLIC_KEY ? "PUBLIC KEY" : "PRIVATE KEY";
        StringBuilder keyBuilder = new StringBuilder("-----BEGIN "+ keyName + "-----\n");
        int lineNum = key.length() % 64 == 0? key.length() / 64 : key.length() / 64 + 1;
        for (int i = 0; i < lineNum; i++) {
            keyBuilder.append(key, i * 64, i == lineNum - 1 ? key.length() : i * 64 + 64).append("\n");
        }

        keyBuilder.append("-----END ").append(keyName).append("-----");
        return keyBuilder.toString();
    }

    /**
     * 读取RSA密钥
     * @param path 密钥路径
     */
    public void writeFile(String path) {
        writeFile(this.keyPair, this.keyEnums, path);
    }


    /**
     * 写出RSA密钥
     * @param keyPair 密钥对
     * @param keyEnums 密钥类型
     * @param path 密钥写出路径
     */
    public void writeFile(KeyPair keyPair, KeyEnums keyEnums, String path) {
        FileUtils.writeFile(path, writeKey(keyPair, keyEnums));
    }

    /**
     * 写出RSA密钥
     * @param path 密钥写出路径
     */
    public void writeFormatFile(String path) {
        writeFormatFile(this.keyPair, this.keyEnums, path);
    }

    /**
     * 写出并格式化RSA密钥
     * @param keyPair 密钥对
     * @param keyEnums 密钥类型
     * @param path 密钥写出路径
     */
    public void writeFormatFile(KeyPair keyPair, KeyEnums keyEnums, String path) {
        FileUtils.writeFile(path, writeFormatStr(keyPair, keyEnums).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 读取公钥
     * @param path 公钥路径
     * @return 公钥
     */
    public PublicKey readPublicKey(String path) {
        try {
            KeyFactory rsa = KeyFactory.getInstance("RSA");
            return rsa.generatePublic(readKey(path));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取私钥
     * @param path 私钥路径
     * @return 私钥
     */
    public PrivateKey readPrivateKey(String path) {
        try {
            KeyFactory rsa = KeyFactory.getInstance("RSA");
            return rsa.generatePrivate(readKey(path));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取密钥
     * @param path 密钥路径
     * @return 密钥
     */
    private X509EncodedKeySpec readKey(String path) {
        if (!FileUtils.fileExists(path)) {
            throw new RuntimeException("not found key file");
        }
        byte[] keyBytes = FileUtils.readFile(path);

        return new X509EncodedKeySpec(keyBytes);
    }

    /**
     * 写入密钥
     * @param keyPair 密钥对
     * @param keyEnums 密钥类型
     * @return 密钥
     */
    private byte[] writeKey(KeyPair keyPair, KeyEnums keyEnums) {
        byte[] keyBytes = null;
        switch (keyEnums) {
            case PUBLIC_KEY:
                keyBytes = keyPair.getPublic().getEncoded();
                break;
            case PRIVATE_KEY:
                keyBytes = keyPair.getPrivate().getEncoded();
                break;
        }
        return keyBytes;
    }
}
