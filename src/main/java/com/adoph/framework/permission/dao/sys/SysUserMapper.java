package com.adoph.framework.permission.dao.sys;

import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.vo.UserRequest;

import java.util.List;

/**
 * 系统用户DAO
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/1/3
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
}
