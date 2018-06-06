package com.adoph.framework.permission.service.sys.impl;

import com.adoph.framework.permission.dao.sys.SysRoleMapper;
import com.adoph.framework.permission.pojo.SysRole;
import com.adoph.framework.permission.service.sys.SysRoleService;
import com.adoph.framework.permission.vo.RoleRequest;
import com.adoph.framework.pojo.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.adoph.framework.permission.constant.Operation.REPEAT_PROPERTIES;
import static com.adoph.framework.permission.constant.Operation.SUCCESS;

/**
 * 系统角色管理
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/1/15
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper roleMapper;


    @Override
    public Page<SysRole> queryRoleList(RoleRequest params) throws Exception {
        return new Page<>(params.getPage(), params.getLimit(), roleMapper.countRoles(params), roleMapper.queryRoleList(params));
    }

    @Override
    public List<SysRole> queryAllRoles() {
        return roleMapper.queryAllRoles();
    }

    @Override
    public int saveRole(SysRole role) {
        Integer r = roleMapper.containRoleName(role);
        if (r == 1) {
            return REPEAT_PROPERTIES;
        }
        Long id = role.getId();
        if (id != null) {
            roleMapper.updateRole(role);
        } else {
            roleMapper.insertRole(role);
        }
        return SUCCESS;
    }

    @Override
    public void delRole(Long id) {
        roleMapper.delRole(id);
    }
}
