package com.adoph.framework.permission.service.sys.impl;

import com.adoph.framework.permission.dao.sys.SysUserMapper;
import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.sys.SysUserService;
import com.adoph.framework.permission.vo.UserRequest;
import com.adoph.framework.pojo.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统用户管理实现类
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/10/13
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public Page<SysUser> queryUserList(UserRequest userReq) {
        List<SysUser> userList = sysUserMapper.queryUserList(userReq);
        Long count = sysUserMapper.countUsers(userReq);
        return new Page<>(userReq.getPage(), userReq.getLimit(), count, userList);
    }
}
