package com.adoph.framework.dao.permission;

import com.adoph.framework.pojo.permission.SysUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 用户登录Repository
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/9/15
 */
public interface LoginRepository extends CrudRepository<SysUser, Long> {
    /**
     * 根据用户名查找用户列表
     *
     * @param userName 用户名称
     * @return List<SysUser>
     */
    List<SysUser> findByUserName(String userName);
}
