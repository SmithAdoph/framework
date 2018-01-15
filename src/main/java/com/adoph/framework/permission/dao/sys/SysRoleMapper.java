package com.adoph.framework.permission.dao.sys;

import com.adoph.framework.permission.pojo.SysRole;
import com.adoph.framework.permission.vo.RoleRequest;
import com.adoph.framework.permission.vo.UserRequest;

import java.util.List;

/**
 * 系统角色DAO
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/1/15
 */
public interface SysRoleMapper {

    /**
     * 分页查询角色列表
     *
     * @param params 请求参数
     * @return List
     */
    List<SysRole> queryRoleList(RoleRequest params);

    /**
     * 获取总记录数
     *
     * @param params 请求参数
     * @return Long
     */
    Long countRoles(RoleRequest params);
}
