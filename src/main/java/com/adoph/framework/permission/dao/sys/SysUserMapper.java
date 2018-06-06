package com.adoph.framework.permission.dao.sys;

import com.adoph.framework.permission.pojo.SysRole;
import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.vo.UserRequest;

import java.util.List;
import java.util.Map;

/**
 * 系统用户DAO
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/1/3
 */
public interface SysUserMapper {
    /**
     * 分页查询用户列表
     *
     * @param userRequest 查询参数
     * @return List<SysUser>
     */
    List<SysUser> queryUserList(UserRequest userRequest);

    /**
     * 查询用户总记录数
     *
     * @param userRequest 查询参数
     * @return Long
     */
    Long countUsers(UserRequest userRequest);

    /**
     * 根据用户名查询
     *
     * @param userName 用户名
     * @return SysUser
     */
    SysUser queryUserByName(String userName);

    /**
     * 更新用户登录信息
     *
     * @param user 用户信息
     */
    void updateLoginInfo(SysUser user);

    /**
     * 新增用户
     *
     * @param user 用户信息
     */
    void insertUser(SysUser user);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     */
    void updateUser(SysUser user);

    /**
     * 查询是否存在用户名
     *
     * @param user id(可选)，userName
     * @return Integer 0,否；1,是；
     */
    Integer containUserName(SysUser user);

    /**
     * 删除用户
     *
     * @param id 主键
     */
    void deleteUser(Long id);

    /**
     * 查询用户角色
     *
     * @param userId 用户id
     * @return List
     */
    List<SysRole> selectUserRoles(Long userId);

    /**
     * 保存用户角色
     *
     * @param params 角色信息
     */
    void insertUserRoles(Map<String, Object> params);

    /**
     * 清空用户角色
     *
     * @param userId 用户id
     */
    void delUserRoles(Long userId);
}
