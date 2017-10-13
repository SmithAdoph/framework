package com.adoph.framework.permission.service.login.impl;

import com.adoph.framework.permission.dao.login.LoginRepository;
import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户登录
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/9/14
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public SysUser login(String userName, String password) {
        List<SysUser> sysUserList = loginRepository.findByUserName(userName);
        for (SysUser user : sysUserList) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
