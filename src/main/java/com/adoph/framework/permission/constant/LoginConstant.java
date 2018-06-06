package com.adoph.framework.permission.constant;

/**
 * 用户登录常量
 *
 * @author Adohp
 * @version v1.0
 * @date 2017/10/12
 */
public class LoginConstant {

    /**
     * 登录失败最大次数
     */
    public final static int FAIL_COUNT_MAX = 3;

    /**
     * 用户登录密钥对缓存key前缀
     */
    public final static String KEY_PAIR_TAG = "LOGIN_KEY_PAIR";

    /**
     * 用户登录失败次数缓存key前缀
     */
    public final static String LOGIN_FAIL_TAG = "LOGIN_FAIL_COUNT";

    /**
     * 用户登录验证码缓存key前缀
     */
    public final static String VERIFY_CODE_TAG = "LOGIN_VERIFY_CODE";

    /**
     * 用户登录缓存key前缀，默认有效时间30分钟
     */
    public final static String LOGIN_ONLINE_TAG = "LOGIN_ONLINE_USER";

    /**
     * 间隔
     */
    public static final String UNDERLINE = "_";

    /**
     * 登录密钥，登录次数，验证码默认缓存时间5分钟
     */
    public static final long LOGIN_COMMON_TIMEOUT = 5L;

    /**
     * 用户登录会话有效时间30分钟
     */
    public static final long LOGIN_ONLINE_TIMEOUT = 30L;

}
