package com.adoph.framework.permission.dao.sys;

import com.adoph.framework.permission.pojo.SysRole;
import com.adoph.framework.permission.vo.RoleRequest;

import java.util.List;

/**
 * 系统角色DAO
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/1/15
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
     * 查询全部角色信息
     *
     * @return List
     */
    List<SysRole> queryAllRoles();

    /**
     * 获取总记录数
     *
     * @param params 请求参数
     * @return Long
     */
    Long countRoles(RoleRequest params);

    /**
     * 新增角色
     *
     * @param role 角色信息
     */
    void insertRole(SysRole role);

    /**
     * 更新角色
     *
     * @param role 角色信息
     */
    void updateRole(SysRole role);

    /**
     * 删除角色
     *
     * @param id 主键
     */
    void delRole(Long id);

    /**
     * 查询是否名称重复
     *
     * @param role 角色信息
     * @return Integer 0，无；1，存在
     */
    Integer containRoleName(SysRole role);
}
