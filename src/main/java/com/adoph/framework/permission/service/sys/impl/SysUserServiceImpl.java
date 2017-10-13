package com.adoph.framework.permission.service.sys.impl;

import com.adoph.framework.permission.dao.sys.SysUserRepository;
import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 系统用户管理实现类
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/10/13
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public Page<SysUser> findAll(String userName, Pageable pageable) {
        return sysUserRepository.findAll(pageable);
    }
}
