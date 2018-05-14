package com.adoph.framework.util;

import com.adoph.framework.exception.UtilException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加密工具类
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/9/15
 */
public class RSAEncryptUtils {

    /**
     * 指定加密算法为RSA
     */
    private static final String ALGORITHM = "RSA";

    /**
     * 密钥长度，用来初始化
     */
    private static final int KEY_SIZE = 1024;

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
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            // 利用上面的随机数据源初始化这个KeyPairGenerator对象
            keyPairGenerator.initialize(KEY_SIZE, secureRandom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert keyPairGenerator != null;
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
            throw new UtilException("加密源不能为空或空字符串！");
        }
        String r = null;
        try {
            // 得到Cipher对象来实现对源数据的RSA加密
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // 执行加密操作
            byte[] b = cipher.doFinal(source.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            r = encoder.encode(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert r != null;
        return replaceSpace(r);
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
            throw new UtilException("解密内容不能为空或空字符串！");
        }
        byte[] b = null;
        try {
            // 得到Cipher对象对已用公钥加密的数据进行RSA解密
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            BASE64Decoder decoder = new BASE64Decoder();

            // 执行解密操作
            b = cipher.doFinal(decoder.decodeBuffer(content));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert b != null;
        return new String(b);
    }

    /**
     * 公钥base64编码串
     *
     * @param keyPair 公钥
     * @return String
     */
    public static String getPublicKey(KeyPair keyPair) {
        return new BASE64Encoder().encode(keyPair.getPublic().getEncoded());
    }

    /**
     * 私钥base64编码串
     *
     * @param keyPair 密钥
     * @return String
     */
    public static String getPrivateKey(KeyPair keyPair) {
        return new BASE64Encoder().encode(keyPair.getPrivate().getEncoded());
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
        keyBytes = (new BASE64Decoder()).decodeBuffer(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
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
        keyBytes = (new BASE64Decoder()).decodeBuffer(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
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
        String password = "renre的我我我我 我我";
        KeyPair keyPair = genKeyPair();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();

        String publicKeyBase64 = new BASE64Encoder().encode(publicKeyBytes);
        String privateKeyBase64 = new BASE64Encoder().encode(privateKeyBytes);

        System.out.println("publicKeyBase64.length():" + publicKeyBase64.length());
        System.out.println("publicKeyBase64:" + publicKeyBase64);
        System.out.println("-----------------华丽的分割线-------------------");
        System.out.println("privateKeyBase64.length():" + privateKeyBase64.length());
        System.out.println("privateKeyBase64:" + privateKeyBase64);

        String encrypt = encrypt(keyPair.getPublic(), password);
        String decrypt = decrypt(keyPair.getPrivate(), encrypt);
        System.out.println("加密前：" + password);
        System.out.println();
        System.out.println("加密后：" + encrypt);
        System.out.println("加密后长度：" + encrypt.length());
        System.out.println();
        System.out.println("解密后：" + decrypt);
    }

}
