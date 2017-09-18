package com.adoph.framework.core;

import com.adoph.framework.pojo.permission.SysUser;
import com.adoph.framework.service.common.LoginService;
import com.adoph.framework.util.RSAEncryptUtils;
import com.adoph.framework.util.SecurityUtils;
import com.adoph.framework.util.SpringUtils;

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

    /**
     * 用户登录密钥对
     */
    private static Map<String, KeyPair> keyPairMap = new ConcurrentHashMap<>();

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
        SysUser user = loginService.login(userName, SecurityUtils.MD5(RSAEncryptUtils.decrypt(keyPair.getPrivate(), password)));
        if (user != null) {
            // TODO 记住用户时，不能移除密钥对
            removeKey(loginId);//移除密钥对
            return new OnlineUser(loginId, user);
        }
        return null;
    }

}
