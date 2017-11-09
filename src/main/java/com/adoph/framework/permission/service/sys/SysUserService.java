package com.adoph.framework.permission.service.sys;

import com.adoph.framework.permission.pojo.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 系统用户管理
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/10/13
 */
public interface SysUserService {

    /**
     * 根据用户名查询用户列表
     *
     * @param userName 用户名
     * @param pageable 分页
     * @return Page<SysUser>
     */
    Page<SysUser> findByUserName(String userName, Pageable pageable);
}
