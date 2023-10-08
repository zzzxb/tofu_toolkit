package cn.tofucat.toolkit.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CipherUtils {
    private static final char[] HEX_CHARS = getHexChars();

    public static String encrypt(String content, Encrypt encrypt) {
        return encrypt(content.getBytes(), encrypt);
    }
    /**
     * MD5 编码
     * @param content 需要进行加密的内容
     * @param encrypt 加密类型
     * @return MD5 加密后的内容
     */
    public static String encrypt(byte[] content, Encrypt encrypt) {
        return encode(buildDigest((byte)0, content, encrypt));
    }

    public static String encrypt(byte content, Encrypt encrypt) {
        return encode(buildDigest(content, null, encrypt));
    }

    public static byte[] buildDigest(byte[] contentBytes, Encrypt encrypt) {
        return buildDigest((byte)0, contentBytes, encrypt);
    }

    private static byte[] buildDigest(byte contentByte, byte[] contentBytes, Encrypt encrypt) {
        MessageDigest instance;
        try {
            instance = MessageDigest.getInstance(encrypt.toString());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("未知加密算法");
        }
        if (contentBytes != null) {
            instance.update(contentBytes);
        }else {
            instance.update(contentByte);
        }
        return instance.digest();
    }

    private static String encode(byte[] digest) {
        int digestLength = digest.length;
        char[] hexChar = new char[digestLength * 2];
        int hexCharIndex = 0;
        for (byte digestOne : digest) {
            hexChar[hexCharIndex++] = HEX_CHARS[digestOne >>> 4 & 0xf];
            hexChar[hexCharIndex++] = HEX_CHARS[digestOne & 0xf];
        }
        return new String(hexChar);
    }

    private static char[] getHexChars() {
        return new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    public enum Encrypt {
        MD5("MD5"),
        SHA1("SHA-1"),
        SHA256("SHA-256");

        private final String encryptType;

        Encrypt(String encryptType) {
            this.encryptType = encryptType;
        }

        @Override
        public String toString() {
            return this.encryptType;
        }
    }
}