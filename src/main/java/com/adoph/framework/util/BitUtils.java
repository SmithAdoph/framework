package com.adoph.framework.util;

import java.util.Arrays;

/**
 * 进位工具类：
 * 2进制、10进制、16进制等转换
 *
 * @author Adohp
 * @version v1.0
 * @date 2017/9/22
 */
public class BitUtils {

    /**
     * 二进制标识
     */
    public static final String BINARY_CHARS = "0123456789ABCDEF";

    /**
     * 字符串转字节数组
     *
     * @param content 输入
     * @return byte[]
     */
    public static byte[] string2Bytes(String content) {
        return content.getBytes();
    }

    /**
     * 字节转10进制
     *
     * @param b 输入
     * @return int
     */
    public static int byte2Int(byte b) {
        return (int) b;
    }

    /**
     * 10进制转字节
     *
     * @param i 输入
     * @return byte
     */
    public static byte int2Byte(int i) {
        return (byte) i;
    }

    /**
     * 字节数组转16进制字符串
     *
     * @param bytes 字节数组
     * @return String
     */
    public static String bytes2HexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 字符转字节
     *
     * @param c 字符
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) BINARY_CHARS.indexOf(c);
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param hex 16进制字符串
     * @return byte[]
     */
    public static byte[] hexString2Bytes(String hex) {
        if (StringUtils.isEmpty(hex)) {
            return null;
        } else if (hex.length() % 2 != 0) {
            return null;
        } else {
            hex = hex.toUpperCase();
            int len = hex.length() / 2;
            byte[] b = new byte[len];
            char[] hc = hex.toCharArray();
            for (int i = 0; i < len; i++) {
                int p = 2 * i;
                b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
            }
            return b;
        }
    }

    public static void main(String[] args) {
        String content = "abc";
        byte[] bytes = string2Bytes(content);
        System.out.println("字符串转字节数组：" + Arrays.toString(bytes));
        byte b = 97;
        System.out.println("字节转10进制：" + byte2Int(b));
        System.out.println("10进制转字节：" + int2Byte(150));
        System.out.println("字节数组转16进制字符串：" + bytes2HexString(bytes));
        String hexStr = "1DA47C";
        System.out.println("16进制字符串转字节数组：" + Arrays.toString(hexString2Bytes(hexStr)));
    }
}
