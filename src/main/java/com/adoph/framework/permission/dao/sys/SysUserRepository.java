package com.adoph.framework.permission.dao.sys;

import com.adoph.framework.permission.pojo.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import static javafx.scene.input.KeyCode.T;

/**
 * 系统用户管理
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/10/13
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    @Override
    Page<SysUser> findAll(Pageable pageable);

    Page<SysUser> findByUserNameLike(String userName, Pageable pageable);
}
