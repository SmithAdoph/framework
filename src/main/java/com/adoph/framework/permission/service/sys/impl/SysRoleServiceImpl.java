package com.adoph.framework.permission.service.sys.impl;

import com.adoph.framework.permission.dao.sys.SysRoleMapper;
import com.adoph.framework.permission.pojo.SysRole;
import com.adoph.framework.permission.service.sys.SysRoleService;
import com.adoph.framework.permission.vo.RoleRequest;
import com.adoph.framework.pojo.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统角色管理
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/1/15
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper roleMapper;


    @Override
    public Page<SysRole> queryRoleList(RoleRequest params) throws Exception {
        return new Page<>(params.getPage(), params.getLimit(), roleMapper.countRoles(params), roleMapper.queryRoleList(params));
    }
}
