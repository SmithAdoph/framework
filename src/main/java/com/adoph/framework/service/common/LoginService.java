package com.adoph.framework.service.common;

import com.adoph.framework.pojo.permission.SysUser;

/**
 * TODO
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/9/14
 */
public interface LoginService {

    SysUser login(String userName, String password);

}
