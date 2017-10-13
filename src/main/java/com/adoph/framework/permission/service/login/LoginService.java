package com.adoph.framework.permission.service.login;

import com.adoph.framework.permission.pojo.SysUser;

/**
 * 用户登录
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/9/14
 */
public interface LoginService {

    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return SysUser
     */
    SysUser login(String userName, String password);

}
