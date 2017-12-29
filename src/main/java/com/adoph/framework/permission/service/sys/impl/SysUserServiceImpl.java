package com.adoph.framework.permission.service.sys.impl;

import com.adoph.framework.permission.dao.sys.SysUserRepository;
import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.sys.SysUserService;
import com.adoph.framework.permission.vo.UserRequest;
import com.adoph.framework.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

/**
 * 系统用户管理实现类
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/10/13
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public Page<SysUser> findAllByUserExample(UserRequest userReq, Pageable pageable) throws Exception {
        SysUser queryUser = new SysUser();
        BeanUtils.copyProperties(userReq, queryUser);
        ExampleMatcher m = ExampleMatcher.matching()
                .withIgnoreCase(false);
        if (StringUtils.isNotEmpty(userReq.getUserName())) {
            m.withMatcher("userName", endsWith());
        }
        if (userReq.getStartCreateDate() != null) {

        }
        if (userReq.getEndCreateDate() != null) {

        }
        Example<SysUser> e = Example.of(queryUser, m);
        return sysUserRepository.findAll(e, pageable);
    }
}
