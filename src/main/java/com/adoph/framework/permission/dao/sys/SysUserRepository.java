package com.adoph.framework.permission.dao.sys;

import com.adoph.framework.permission.pojo.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 系统用户管理
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/10/13
 */
public interface SysUserRepository extends PagingAndSortingRepository<SysUser, Long> {

    @Override
    Page<SysUser> findAll(Pageable pageable);
}
