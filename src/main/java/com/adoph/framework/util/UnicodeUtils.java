package com.adoph.framework.util;

/**
 * Unicode工具类
 *
 * @author Adohp
 * @version v1.0
 * @date 2017/9/22
 */
public class UnicodeUtils {

    /**
     * 获取unicode编码串
     *
     * @param content String
     * @return String
     */
    public static String encode(String content) {
        String prefix = "\\u";
        StringBuilder unicode = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            String code = prefix + format(Integer.toHexString(c));
            unicode.append(code);
        }
        return unicode.toString();
    }

    /**
     * unicode串解码
     *
     * @param unicode 编码串
     * @return String
     */
    public static String decode(String unicode) {
        StringBuffer str = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            str.append((char) data);
        }
        return str.length() > 0 ? str.toString() : unicode;
    }

    /**
     * 转换16进制串，不足4位补0
     *
     * @param str 16进制串
     * @return String
     */
    private static String format(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0, l = 4 - sb.length(); i < l; i++) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String encodeStr = encode("12Abc");
        System.out.println(encodeStr);
        System.out.println(decode("\u0000"));
    }

}
