package cn.tofucat.toolkit.util;

/**
 * zzzxb
 * 2023/8/25
 */
public class ByteUtils {
    public static byte[] translationASCII(String str) {
        byte[] bytes = new byte[str.length()];
        for (int i = 0; i < str.length(); i++) {
            bytes[i] = (byte) str.charAt(i);
        }
        return bytes;
    }

    public static byte[] parallelTransport(String numStr) {
        byte[] bytes = new byte[numStr.length()];
        for (int i = 0; i < numStr.length(); i++) {
            bytes[i] = (byte) (numStr.charAt(i) - 48);
        }
        return bytes;
    }
}
