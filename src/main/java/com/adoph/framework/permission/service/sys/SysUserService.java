package com.adoph.framework.permission.service.sys;

import com.adoph.framework.permission.pojo.SysRole;
import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.vo.SysUserVO;
import com.adoph.framework.permission.vo.UserRequest;
import com.adoph.framework.pojo.Page;

import java.util.List;

/**
 * 系统用户管理
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/10/13
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

    /**
     * 新增或更新用户信息
     *
     * @param user 用户信息
     */
    Integer saveUser(SysUserVO user);

    /**
     * 删除用户
     *
     * @param id 主键
     */
    void delUser(Long id);

    /**
     * 获取用户角色
     *
     * @param userId 用户id
     * @return List
     */
    List<SysRole> queryUserRoles(Long userId);
}
