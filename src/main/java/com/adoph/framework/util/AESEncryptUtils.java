package com.adoph.framework.util;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/**
 * AES加密
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/5/24
 */
public class AESEncryptUtils {

    /**
     * 密钥长度必须等于16
     */
    private static String PERM_KEY = "ThisIsASecretKey";

    /**
     * AES加密，长度必须是16的倍数
     * PKCS5Padding 不足16位，自动补齐
     */
    private static String ALGORITHM_AES = "AES/CBC/PKCS5Padding";

    /**
     * 加密
     *
     * @param content 待加密内容
     * @return 加密后内容
     * @throws GeneralSecurityException
     */
    public static String encrypt(String content)
            throws GeneralSecurityException {
        byte[] raw = PERM_KEY.getBytes(Charset.forName("UTF-8"));
        if (raw.length != 16) {
            throw new IllegalArgumentException("非法的密钥key!长度必须等于16.");
        }

        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec,
                new IvParameterSpec(new byte[16]));
        return Base64Utils.encodeToString(cipher.doFinal(content.getBytes(Charset.forName("UTF-8"))));
    }

    /**
     * 解密
     *
     * @param encryptedText 加密字节数组
     * @return 解密后字符串
     * @throws GeneralSecurityException
     */
    public static String decrypt(String encryptedText)
            throws GeneralSecurityException {
        byte[] raw = PERM_KEY.getBytes(Charset.forName("UTF-8"));
        if (raw.length != 16) {
            throw new IllegalArgumentException("非法的密钥key!长度必须等于16.");
        }
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec,
                new IvParameterSpec(new byte[16]));
        byte[] original = cipher.doFinal(Base64Utils.decodeFromString(encryptedText));
        return new String(original, Charset.forName("UTF-8"));
    }

    public static void main(String[] args) throws GeneralSecurityException {
        String content = System.currentTimeMillis() + "#1#" + "13348960778";
        String encryptedText = encrypt(content);
        System.out.println("加密前：" + content);
        System.out.println("------------------------");
        System.out.println("加密后：" + encryptedText);
        System.out.println("------------------------");
        System.out.println("解密后：" + decrypt(encryptedText));
    }

}
