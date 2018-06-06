package com.adoph.framework.permission;

import com.adoph.framework.core.cache.CacheFactory;
import com.adoph.framework.core.cache.service.CacheService;
import com.adoph.framework.core.cache.service.RedisCacheService;
import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.login.LoginService;
import com.adoph.framework.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.security.KeyPair;
import java.util.concurrent.TimeUnit;

import static com.adoph.framework.permission.constant.LoginConstant.*;

/**
 * 在线用户管理
 *
 * @author Adohp
 * @version v1.0
 * @date 2017/9/15
 */
public class LoginManager {

    private final static Logger log = LoggerFactory.getLogger(LoginManager.class);

    /**
     * redis缓存
     */
    private static RedisCacheService<String, Object> redisCache = CacheFactory.getRedisCache();

    /**
     * string redis 缓存
     */
    private static RedisCacheService<String, String> strRedisCache = CacheFactory.getStringRedisCache();

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
            redisCache.expire(key, LOGIN_COMMON_TIMEOUT, TimeUnit.MINUTES);
        }
        return count;
    }

    /**
     * 获取登录失败次数
     *
     * @param loginId 登录id
     * @return Long
     */
    public static Long getFailCount(String loginId) {
        String key = LOGIN_FAIL_TAG + UNDERLINE + loginId;
        return redisCache.increment(key, 0L);
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
        redisCache.add(KEY_PAIR_TAG + UNDERLINE + loginId, keyPair, LOGIN_COMMON_TIMEOUT, TimeUnit.MINUTES);
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
        strRedisCache.add(VERIFY_CODE_TAG + UNDERLINE + loginId, verifyCode, LOGIN_COMMON_TIMEOUT, TimeUnit.MINUTES);
    }

    /**
     * 移除验证码
     *
     * @param loginId 登录id
     */
    private static void removeVerifyCode(String loginId) {
        strRedisCache.delete(VERIFY_CODE_TAG + UNDERLINE + loginId);
    }

    /**
     * 获取用户验证码
     *
     * @param loginId 登录id
     * @return String
     */
    private static String getVerifyCode(String loginId) {
        return (String) strRedisCache.get(VERIFY_CODE_TAG + UNDERLINE + loginId);
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
     * 缓存登录用户
     *
     * @param loginId 登录id
     * @param online  登录用户
     */
    private static void addUser(String loginId, OnlineUser online) {
        redisCache.add(LOGIN_ONLINE_TAG + UNDERLINE + loginId, online, LOGIN_ONLINE_TIMEOUT, TimeUnit.MINUTES);
    }

    /**
     * 重置会话时间
     *
     * @param loginId 登录id
     */
    public static void reset(String loginId) {
        redisCache.expire(LOGIN_ONLINE_TAG + UNDERLINE + loginId, LOGIN_ONLINE_TIMEOUT, TimeUnit.MINUTES);
    }

    /**
     * 获取登录用户
     *
     * @param loginId 登录id
     * @return OnlineUser
     */
    public static OnlineUser getUser(String loginId) {
        return (OnlineUser) redisCache.get(LOGIN_ONLINE_TAG + UNDERLINE + loginId);
    }

    /**
     * 删除登录用户
     *
     * @param loginId 登录id
     */
    public static void removeUser(String loginId) {
        redisCache.delete(LOGIN_ONLINE_TAG + UNDERLINE + loginId);
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
            removeVerifyCode(loginId);//移除验证码
            OnlineUser online = new OnlineUser(loginId, user);
//            addUser(loginId, online);//缓存登录用户
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
