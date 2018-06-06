package com.adoph.framework.permission.service.login.impl;

import com.adoph.framework.permission.dao.sys.SysUserMapper;
import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.login.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 用户登录
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/9/14
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Resource
    private SysUserMapper userMapper;

    @Override
    public SysUser login(String userName, String password) {
        SysUser user = userMapper.queryUserByName(userName);
        if (user == null) {
            return null;
        }
        if (Objects.equals(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public void updateLoginInfo(SysUser user) {
        userMapper.updateLoginInfo(user);
    }
}
