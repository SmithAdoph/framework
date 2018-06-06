package com.adoph.framework.permission.service.sys;

import com.adoph.framework.permission.pojo.SysRole;
import com.adoph.framework.permission.vo.RoleRequest;
import com.adoph.framework.pojo.Page;

import java.util.List;

/**
 * 系统角色管理
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/10/13
 */
public interface SysRoleService {

    /**
     * 分页查询角色列表
     *
     * @param params 请求参数
     * @return Page
     * @throws Exception
     */
    Page<SysRole> queryRoleList(RoleRequest params) throws Exception;

    /**
     * 查询所有的角色信息
     *
     * @return List
     */
    List<SysRole> queryAllRoles();

    /**
     * 新增或者更新角色
     *
     * @param role 角色信息
     */
    int saveRole(SysRole role);

    /**
     * 删除角色
     *
     * @param id 主键
     */
    void delRole(Long id);
}
