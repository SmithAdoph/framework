package com.adoph.framework.permission.service.sys;

import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.vo.UserRequest;
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

    Page<SysUser> findAllByUserExample(UserRequest request, Pageable pageable);
}
