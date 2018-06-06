package com.adoph.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加密工具类
 * 备注：加密的长度限制是53个字节
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/9/15
 */
public class RSAEncryptUtils {

    /**
     * 指定加密算法为RSA
     */
    private static final String ALGORITHM_RSA = "RSA";

    /**
     * 密钥长度，用来初始化
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 固定公钥
     */
    private static final String permPublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMKgQ3DtWh9Ah9V2I8qaar333NDclZmkB" +
            "Jls+pSlR3YpmzFur3Tbv86AGVqtb3xqlmW8r/ot3vAz42gxdt59eFMCAwEAAQ==";

    /**
     * 固定私钥
     */
    private static final String permPrivateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAwqBDcO1aH0CH1XYj" +
            "yppqvffc0NyVmaQEmWz6lKVHdimbMW6vdNu/zoAZWq1vfGqWZbyv+i3e8DPjaDF23n14UwIDAQABAkBYAGEeMaQ7V1FT+qwqUvIK3" +
            "YUxx1u5cclGmlkfVzBdw53AaVFiwxQBTyEQv6Ol9B5zlBku6iBl53XTTSJM2GSRAiEA8rkDyY3k7XtVMU8jO6IJiTTj/+iilzRnCl" +
            "dvkzKM+VsCIQDNRbkktQkGqB0olGA0carnKhpuyIqsOyP5iG8dHNY2aQIgUW1kKd/iZxEzGWG1LjJEBLWrr5R5x0QbNUrz8WvKA3U" +
            "CIBg0kJBziIzwZf/S/0Uv4idAH73QiAmnL6bNH80fCWOBAiBNhp50JiEiFmiZ48YnZ60fm1Ezw4Uodj2SaM1oP4PWEA==";

    /**
     * 生成密钥对
     *
     * @return KeyPair
     */
    public static KeyPair genKeyPair() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            // RSA算法要求有一个可信任的随机数源
            SecureRandom secureRandom = new SecureRandom();
            // 为RSA算法创建一个KeyPairGenerator对象
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_RSA);
            // 利用上面的随机数据源初始化这个KeyPairGenerator对象
            keyPairGenerator.initialize(KEY_SIZE, secureRandom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (keyPairGenerator == null) {
            throw new RuntimeException("生成密钥对异常！");
        }
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 加密
     *
     * @param publicKey 公钥
     * @param source    加密源
     * @return String
     */
    public static String encrypt(Key publicKey, String source) {
        if (StringUtils.isEmpty(source)) {
            throw new RuntimeException("加密内容不能为空！");
        }
        byte[] src = source.getBytes();
        if (src.length > 53) {
            throw new RuntimeException("加密内容超长！最多53个字节。加密内容：【" + source + "】");
        }
        String r = null;
        try {
            // 得到Cipher对象来实现对源数据的RSA加密
            Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // 执行加密操作
            byte[] b = cipher.doFinal(src);
            r = Base64Utils.encodeToString(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (r == null) {
            throw new RuntimeException("加密异常!加密内容：【" + source + "】");
        }
        return r;
    }

    /**
     * 固定公钥加密
     *
     * @param source 待加密内容
     * @return 加密后内容
     * @throws Exception
     */
    public static String encrypt(String source) throws Exception {
        return encrypt(getPublicKey(permPublicKey), source);
    }

    /**
     * 解密
     *
     * @param privateKey 私钥
     * @param content    解密内容
     * @return String
     */
    public static String decrypt(Key privateKey, String content) {
        if (StringUtils.isEmpty(content)) {
            throw new RuntimeException("解密内容不能为空或空字符串！");
        }
        byte[] b = null;
        try {
            // 得到Cipher对象对已用公钥加密的数据进行RSA解密
            Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            b = cipher.doFinal(Base64Utils.decodeFromString(content));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (b == null) {
            throw new RuntimeException("解密异常！解密内容：【" + content + "】");
        }
        return new String(b);
    }

    /**
     * 固定私钥解密
     *
     * @param content 待解密内容
     * @return 解密后内容
     * @throws Exception
     */
    public static String decrypt(String content) throws Exception {
        return decrypt(getPrivateKey(permPrivateKey), content);
    }

    /**
     * 公钥base64编码串
     *
     * @param keyPair 公钥
     * @return String
     */
    public static String getPublicKey(KeyPair keyPair) {
        return Base64Utils.encodeToString(keyPair.getPublic().getEncoded());
    }

    /**
     * 私钥base64编码串
     *
     * @param keyPair 密钥
     * @return String
     */
    public static String getPrivateKey(KeyPair keyPair) {
        return Base64Utils.encodeToString(keyPair.getPrivate().getEncoded());
    }

    /**
     * 公钥
     *
     * @param publicKey 密钥字符串（base64编码）
     * @return PublicKey
     * @throws Exception
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64Utils.decodeFromString(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 私钥
     *
     * @param privateKey 密钥字符串（base64编码）
     * @return PrivateKey
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64Utils.decodeFromString(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * base64加密后的数据一行不能超过76字符，超过则添加回车换行符
     *
     * @param content 需要替换的内容
     * @return String
     */
    private static String replaceSpace(String content) {
        return content.replaceAll("[\\s*\t\n\r]", "");
    }

    public static void main(String[] args) throws Exception {
        String password = "一只小黄鸭，游过门前小河沟111111111111111";
//        KeyPair keyPair = genKeyPair();
//        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
//        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
//
//        String publicKeyBase64 = new BASE64Encoder().encode(publicKeyBytes);
//        String privateKeyBase64 = new BASE64Encoder().encode(privateKeyBytes);
//
//        System.out.println("publicKeyBase64.length():" + publicKeyBase64.length());
//        System.out.println("publicKeyBase64:" + publicKeyBase64);
//        System.out.println("-----------------华丽的分割线-------------------");
//        System.out.println("privateKeyBase64.length():" + privateKeyBase64.length());
//        System.out.println("privateKeyBase64:" + privateKeyBase64);
//
//        String encrypt = encrypt(keyPair.getPublic(), password);
//        String decrypt = decrypt(keyPair.getPrivate(), encrypt);
//        System.out.println("加密前：" + password);
//        System.out.println();
//        System.out.println("加密后：" + encrypt);
//        System.out.println("加密后长度：" + encrypt.length());
//        System.out.println();
//        System.out.println("解密后：" + decrypt);
        System.out.println("加密前：" + password);
        System.out.println("------------------------------------------------");
        String encrypt = encrypt(password);
        System.out.println("加密后：" + encrypt);
        System.out.println("------------------------------------------------");
        System.out.println("解密后：" + decrypt(encrypt));
    }

}
