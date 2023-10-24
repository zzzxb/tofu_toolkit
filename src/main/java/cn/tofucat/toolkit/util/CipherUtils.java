package cn.tofucat.toolkit.util;

import cn.tofucat.toolkit.algorithm.EncryptBase;


public final class CipherUtils {
    private final static EncryptBase encryptBase = new EncryptBase();

    public static String md5_16(String str) {
        return md5_32(str).substring(8, 24);
    }

    public static String md5_32(String str) {
        CheckParamUtils.isNull(str).throwMessage("str is null");
        return encryptBase.encrypt(str, EncryptBase.Encrypt.MD5);
    }

    public static String sha256(String str) {
        CheckParamUtils.isNull(str).throwMessage("str is null");
        return encryptBase.encrypt(str, EncryptBase.Encrypt.SHA256);
    }

    public static String sha1(String str) {
        CheckParamUtils.isNull(str).throwMessage("str is null");
        return encryptBase.encrypt(str, EncryptBase.Encrypt.SHA1);
    }
}