package com.adoph.framework.permission;

import com.adoph.framework.core.cache.CacheFactory;
import com.adoph.framework.core.cache.service.CacheService;
import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.LoginService;
import com.adoph.framework.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.security.Key;
import java.security.KeyPair;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

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
     * redis缓存
     */
    private static CacheService redisCache = CacheFactory.getRedisCache();

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
     * 用户登录密钥对缓存key前缀，默认有效时间5分钟
     */
    private final static String KEY_PAIR_TAG = "LOGIN_KEY_PAIR";

    /**
     * 用户登录失败次数缓存key前缀
     */
    private final static String LOGIN_FAIL_TAG = "LOGIN_FAIL_COUNT";

    /**
     * 间隔
     */
    private static final String UNDERLINE = "_";

    /**
     * 登录失败
     *
     * @param loginId 登录id
     * @return Integer
     */
    private static Long fail(String loginId) {
        String key = LOGIN_FAIL_TAG + UNDERLINE + loginId;
        Long count = redisCache.increment(key, 1L);
        if (count == 1) {
            redisCache.expire(key, 5L, TimeUnit.MINUTES);
        }
        return count;
    }

    /**
     * 获取登录失败次数
     *
     * @param loginId 登录id
     * @return Integer
     */
    public static Long getFailCount(String loginId) {
        String key = LOGIN_FAIL_TAG + UNDERLINE + loginId;
        return (Long) redisCache.get(key);
    }

    /**
     * 移除用户登录失败记录
     *
     * @param loginId 登录id
     */
    private static void removeFailCount(String loginId) {
        redisCache.delete(LOGIN_FAIL_TAG + UNDERLINE + loginId);
    }

    /**
     * 新增密钥
     *
     * @param loginId 登录id
     * @return KeyPair
     */
    public static KeyPair addKey(String loginId) {
        KeyPair keyPair = RSAEncryptUtils.genKeyPair();
        redisCache.add(KEY_PAIR_TAG + UNDERLINE + loginId, keyPair, 5, TimeUnit.MINUTES);
        return keyPair;
    }

    /**
     * 移除密钥
     *
     * @param loginId 登录id
     */
    private static void removeKey(String loginId) {
        redisCache.delete(KEY_PAIR_TAG + UNDERLINE + loginId);
    }

    /**
     * 获取密钥
     *
     * @param loginId 登录id
     */
    private static KeyPair getKey(String loginId) {
        return (KeyPair) redisCache.get(KEY_PAIR_TAG + UNDERLINE + loginId);
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
        LoginService loginService = SpringUtils.getBean("loginService", LoginService.class);
        KeyPair keyPair = getKey(loginId);
        if (keyPair == null) {// 非法登录
            log.warn("{非法登录！loginId=" + loginId + "失效！}");
            return null;
        }
        SysUser user = loginService.login(userName, SecurityUtils.MD5(RSAEncryptUtils.decrypt(keyPair.getPrivate(), password)));
        if (user != null) {//登录成功
            // TODO 记住用户时，不能移除密钥对
            removeKey(loginId);//移除密钥对
            removeFailCount(loginId);//移除登录失败记录
            removeVerifyCode(loginId);//基础验证码
            OnlineUser online = new OnlineUser(loginId, user);
            redisCache.add(loginId, online);//缓存用户
            return online;
        } else {
            log.warn("{用户登录失败次数：loginId=" + loginId + "," + fail(loginId) + "次}");//记录登录失败次数
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
