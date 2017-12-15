package com.adoph.framework.permission.dao.sys;

import com.adoph.framework.permission.pojo.SysUser;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * 系统用户管理
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/10/13
 */
@Repository
public interface SysUserRepository extends QueryByExampleExecutor<SysUser> {

//    @Override
//    Page<SysUser> findAll(Pageable pageable);
//
//    Page<SysUser> findByUserNameLike(String userName, Pageable pageable);
}
