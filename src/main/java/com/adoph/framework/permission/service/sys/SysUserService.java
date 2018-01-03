package com.adoph.framework.permission.service.sys;

import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.vo.UserRequest;
import com.adoph.framework.pojo.Page;

/**
 * 系统用户管理
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/10/13
 */
public interface SysUserService {

    /**
     * 分页查询用户列表
     *
     * @param userReq 请求参数
     * @return Page<SysUser>
     * @throws Exception
     */
    Page<SysUser> queryUserList(UserRequest userReq) throws Exception;
}
