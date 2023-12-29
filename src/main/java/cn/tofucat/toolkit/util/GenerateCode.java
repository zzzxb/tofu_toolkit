package cn.tofucat.toolkit.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * zzzxb
 * 2023/11/22
 */
public class GenerateCode {
    public final static GenerateCode INSTANCE = new GenerateCode();
    private final Random random = new Random();
    private final static List<String> codeList = Arrays.asList(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z", "0", "1", "2", "3",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "4", "5", "6", "7",
            "8", "9");

    private GenerateCode() {
    }

    public String randomCode() {
        return codeList.get(random.nextInt(62));
    }

    public String phoneEncode(String phone) {
        return Ten2Thirty.decimalToBase32(Long.parseLong(phone));
    }

    public String phoneDecode(String code) {
        try {
            String subPhone = code.substring(0, code.length() - 1);
            return String.valueOf(Ten2Thirty.base32ToDecimal(subPhone));
        }catch (Exception e) {
            return "";
        }
    }

    public String hindPhone(String code) {
        String phone = phoneDecode(code);
        if (StringUtils.isBlank(phone)) {
            return "";
        }
        return phone.substring(0,3) + "****" + phone.substring(7);
    }
}
