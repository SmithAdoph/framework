package com.adoph.framework.permission.controller.sys;

import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.sys.SysUserService;
import com.adoph.framework.permission.vo.UserRequest;
import com.adoph.framework.web.response.BaseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 系统用户管理控制层
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/10/13
 */
@Controller
@RequestMapping("sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("list")
    @ResponseBody
    public Page<SysUser> list(UserRequest user) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(user.getPage() - 1, user.getLimit(), sort);
        return sysUserService.findByUserName("admin%", pageable);
    }

}