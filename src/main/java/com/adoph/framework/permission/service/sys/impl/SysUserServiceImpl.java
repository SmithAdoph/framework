package com.adoph.framework.permission.service.sys.impl;

import com.adoph.framework.permission.dao.sys.SysUserMapper;
import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.sys.SysUserService;
import com.adoph.framework.permission.vo.UserRequest;
import com.adoph.framework.pojo.Page;
import com.adoph.framework.util.SecurityUtils;
import com.adoph.framework.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

import static com.adoph.framework.permission.constant.Operation.*;

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
    public Page<SysUser> queryUserList(UserRequest userReq) throws Exception {
        List<SysUser> userList = sysUserMapper.queryUserList(userReq);
        Long count = sysUserMapper.countUsers(userReq);
        return new Page<>(userReq.getPage(), userReq.getLimit(), count, userList);
    }

    @Override
    public Integer saveUser(SysUser user) {
        Long id = user.getId();
        Integer r = sysUserMapper.containUserName(user);
        if (r == 1) {
            return REPEAT_PROPERTIES;
        }
        String password = user.getPassword();
        if (StringUtils.isNotEmpty(password)) {
            user.setPassword(SecurityUtils.MD5(password));
        }
        if (id != null) {
            sysUserMapper.updateUser(user);
        } else {
            sysUserMapper.insertUser(user);
        }
        return SUCCESS;
    }

    @Override
    public void delUser(Long id) {
        Assert.notNull(id, "id不能为空！");
        sysUserMapper.deleteUser(id);
    }
}
