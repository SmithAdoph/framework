package com.adoph.framework.permission.service.sys.impl;

import com.adoph.framework.permission.dao.sys.SysUserMapper;
import com.adoph.framework.permission.pojo.SysRole;
import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.sys.SysUserService;
import com.adoph.framework.permission.vo.SysUserVO;
import com.adoph.framework.permission.vo.UserRequest;
import com.adoph.framework.pojo.Page;
import com.adoph.framework.util.SecurityUtils;
import com.adoph.framework.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.adoph.framework.permission.constant.Operation.*;

/**
 * 系统用户管理实现类
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/10/13
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

    @Transactional
    @Override
    public Integer saveUser(SysUserVO sysUserVO) {
        Long id = sysUserVO.getId();
        SysUser user = new SysUser();
        BeanUtils.copyProperties(sysUserVO, user, "sysRoles");
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
        //开始修改用户角色信息
        List<Long> sysRoles = sysUserVO.getSysRoles();
        //1.清空角色
        sysUserMapper.delUserRoles(user.getId());
        if (sysRoles != null && sysRoles.size() > 0) {
            Map<String, Object> userRolesParams = new HashMap<>();
            userRolesParams.put("userId", user.getId());
            userRolesParams.put("roleIds", sysRoles);
            userRolesParams.put("createdBy", user.getCreatedBy());
            //2.新增
            sysUserMapper.insertUserRoles(userRolesParams);
        }
        return SUCCESS;
    }

    @Transactional
    @Override
    public void delUser(Long id) {
        Assert.notNull(id, "id不能为空！");
        sysUserMapper.deleteUser(id);
    }

    @Override
    public List<SysRole> queryUserRoles(Long userId) {
        return sysUserMapper.selectUserRoles(userId);
    }
}
