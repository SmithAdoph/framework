package com.adoph.framework.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 安全工具类：MD5加密等
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/9/18
 */
public class SecurityUtils {

    /**
     * MD5
     *
     * @param content 加密内容
     * @return String
     */
    public static String MD5(String content) {
        MessageDigest md = null;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            // 计算MD5函数
            md.update(content.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        return new BigInteger(1, md.digest()).toString(16);
    }

    public static void main(String[] args) {
        System.out.println(MD5("1"));
    }

}
