package com.adoph.framework.permission.dao.sys;

import com.adoph.framework.permission.pojo.SysUser;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 系统用户管理
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/10/13
 */
@Repository
public interface SysUserRepository extends QueryByExampleExecutor<SysUser>, JpaRepository<SysUser, ID> {

}
