package com.coderdream.freeapps.util.other;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @author hedf
 */
public class MD5Encoder {
    private static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String encode(String rawPass, String salt) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            //加密后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass, salt).getBytes("utf-8")));
        } catch (Exception ex) {
        }
        return result;
    }


    private static String mergePasswordAndSalt(String password, String salt) {
        if (password == null) {
            password = "";
        }
        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + salt.toString();
        }
    }

    public static String getMessageDigest(String data) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));

            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
        } catch (Exception e) {
            return null;
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    public static String getUUID()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-","").toUpperCase();
    }
}
