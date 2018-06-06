package com.adoph.framework.permission.vo;

import com.adoph.framework.permission.OnlineUser;

/**
 * 用户登录VO
 *
 * @author Adohp
 * @version v1.0
 * @date 2017/9/19
 */
public class LoginVO {

    /**
     * 是否显示验证码
     */
    private int showVerifyCode = 0;

    private OnlineUser online;

    public LoginVO() {
    }

    public LoginVO(int showVerifyCode) {
        this.showVerifyCode = showVerifyCode;
    }

    public LoginVO(OnlineUser online) {
        this.online = online;
    }

    public int getShowVerifyCode() {
        return showVerifyCode;
    }

    public void setShowVerifyCode(int showVerifyCode) {
        this.showVerifyCode = showVerifyCode;
    }

    public OnlineUser getOnline() {
        return online;
    }

    public void setOnline(OnlineUser online) {
        this.online = online;
    }
}
