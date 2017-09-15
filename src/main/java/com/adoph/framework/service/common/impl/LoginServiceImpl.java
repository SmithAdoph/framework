package com.adoph.framework.service.common.impl;

import com.adoph.framework.dao.permission.LoginRepository;
import com.adoph.framework.pojo.permission.SysUser;
import com.adoph.framework.service.common.LoginService;
import com.adoph.framework.util.RSAEncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author Tangqiandong
 * @version v1.0
 * @since 2017/9/14
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public SysUser login(String userName, String securityPassword) {
        String password = RSAEncryptUtils.encrypt(null, securityPassword);
        List<SysUser> sysUserList = loginRepository.findByUserName(userName);
        for (SysUser user : sysUserList) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
