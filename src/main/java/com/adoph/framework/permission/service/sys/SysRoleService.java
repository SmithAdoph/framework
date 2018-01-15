package com.adoph.framework.permission.service.sys;

import com.adoph.framework.permission.pojo.SysRole;
import com.adoph.framework.permission.vo.RoleRequest;
import com.adoph.framework.pojo.Page;

/**
 * 系统角色管理
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/10/13
 */
public interface SysRoleService {

    /**
     * 分页查询用户列表
     *
     * @param params 请求参数
     * @return Page
     * @throws Exception
     */
    Page<SysRole> queryRoleList(RoleRequest params) throws Exception;

}
