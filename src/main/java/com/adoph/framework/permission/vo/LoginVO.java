package com.adoph.framework.permission.vo;

/**
 * 用户登录VO
 *
 * @author Adohp
 * @version v1.0
 * @since 2017/9/19
 */
public class LoginVO {

    /**
     * 是否显示验证码
     */
    private int showVerifyCode = 0;

    public int getShowVerifyCode() {
        return showVerifyCode;
    }

    public void setShowVerifyCode(int showVerifyCode) {
        this.showVerifyCode = showVerifyCode;
    }
}
