package com.adoph.framework.core;

import com.adoph.framework.pojo.permission.SysUser;
import com.adoph.framework.service.common.LoginService;
import com.adoph.framework.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.security.KeyPair;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 在线用户管理
 *
 * @author Adohp
 * @version v1.0
 * @since 2017/9/15
 */
public class LoginManager {

    private final static Logger log = LoggerFactory.getLogger(LoginManager.class);

    /**
     * 用户登录密钥对
     */
    private static Map<String, KeyPair> keyPairMap = new ConcurrentHashMap<>();

    /**
     * 用户登录失败计数
     */
    private static Map<String, Integer> loginCountMap = new ConcurrentHashMap<>();

    /**
     * 用户登录验证码
     */
    private static Map<String, String> loginVerifyCodeMap = new ConcurrentHashMap<>();

    /**
     * 登录失败最大次数
     */
    public final static int FAIL_COUNT_MAX = 3;

    /**
     * 登录失败
     *
     * @param loginId 登录id
     * @return Integer
     */
    private static Integer fail(String loginId) {
        Integer failCount = loginCountMap.get(loginId);
        if (failCount == null) {
            failCount = 0;
        }
        loginCountMap.put(loginId, failCount + 1);
        return loginCountMap.get(loginId);
    }

    /**
     * 获取登录失败次数
     *
     * @param loginId 登录id
     * @return Integer
     */
    public static Integer getFailCount(String loginId) {
        return loginCountMap.get(loginId);
    }

    /**
     * 移除用户登录失败记录
     *
     * @param loginId 登录id
     */
    private static void removeFailCount(String loginId) {
        loginCountMap.remove(loginId);
    }

    /**
     * 新增密钥
     *
     * @param loginId 登录id
     * @return KeyPair
     */
    public static KeyPair addKey(String loginId) {
        KeyPair keyPair = RSAEncryptUtils.genKeyPair();
        keyPairMap.put(loginId, keyPair);
        return keyPair;
    }

    /**
     * 移除密钥
     *
     * @param loginId 登录id
     */
    private static void removeKey(String loginId) {
        keyPairMap.remove(loginId);
    }

    /**
     * 添加用户验证码
     *
     * @param loginId    登录id
     * @param verifyCode 验证码
     */
    private static void addVerifyCode(String loginId, String verifyCode) {
        loginVerifyCodeMap.put(loginId, verifyCode);
    }

    /**
     * 移除验证码
     *
     * @param loginId 登录id
     */
    private static void removeVerifyCode(String loginId) {
        loginVerifyCodeMap.remove(loginId);
    }

    /**
     * 获取用户验证码
     *
     * @param loginId 登录id
     * @return String
     */
    private static String getVerifyCode(String loginId) {
        return loginVerifyCodeMap.get(loginId);
    }

    /**
     * 校验用户验证码(忽略大小写)
     *
     * @param loginId    登录id
     * @param verifyCode 验证码
     * @return boolean
     */
    public static boolean verifyCode(String loginId, String verifyCode) {
        return getVerifyCode(loginId).equalsIgnoreCase(verifyCode);
    }

    /**
     * 用户登录
     *
     * @param loginId  登录id
     * @param userName 用户名
     * @param password 用户密码
     * @return OnlineUser
     */
    public static OnlineUser login(String loginId, String userName, String password) {
        LoginService loginService = SpringUtils.getBean("loginService");
        KeyPair keyPair = keyPairMap.get(loginId);
        if (keyPair == null) {
            // 非法登录
            log.warn("{非法登录！loginId=" + loginId + "失效！}");
            return null;
        }
        SysUser user = loginService.login(userName, SecurityUtils.MD5(RSAEncryptUtils.decrypt(keyPair.getPrivate(), password)));
        if (user != null) {
            // TODO 记住用户时，不能移除密钥对
            removeKey(loginId);//移除密钥对
            removeFailCount(loginId);//移除登录失败记录
            removeVerifyCode(loginId);//基础验证码
            return new OnlineUser(loginId, user);
        } else {
            log.warn("{用户登录失败次数：loginId=" + loginId + "," + fail(loginId) + "次}");//登录失败记录次数
        }
        return null;
    }

    /**
     * 获取验证码
     *
     * @param loginId 登录id
     * @return ByteArrayInputStream 图片流
     */
    public static ByteArrayInputStream getVerifyCodeImage(String loginId) {
        char[] codes = RandomSecurityCode.getSecurityCode(4, RandomSecurityCode.SecurityCodeLevel.Hard, true);
        addVerifyCode(loginId, new String(codes));
        return RandomSecurityImage.getGaussianBlurImage(codes);
    }

}
