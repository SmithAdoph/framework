package com.adoph.framework.permission.controller.sys;

import com.adoph.framework.permission.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统用户管理控制层
 *
 * @author Adohp
 * @version v1.0
 * @since 2017/10/13
 */
@Controller
@RequestMapping("sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    public void querySysUserList() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(1, 15, sort);
        sysUserService.findAll("admin", pageable);
    }

}
