package com.adoph.framework.permission;

import com.adoph.framework.permission.pojo.SysUser;

import java.io.Serializable;

/**
 * 在线用户
 *
 * @author Adohp
 * @version v1.0
 * @date 2017/9/18
 */
public class OnlineUser implements Serializable {

    public OnlineUser(String loginId, SysUser sysUser) {
        this.loginId = loginId;
        this.sysUser = sysUser;
    }

    /**
     * 用户登录随机UUID
     */
    private String loginId;

    /**
     * 系统用户对象
     */
    private SysUser sysUser;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public SysUser getSysUser() {
        return sysUser;
    }
}
