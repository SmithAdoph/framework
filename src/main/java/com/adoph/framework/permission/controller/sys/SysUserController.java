package com.adoph.framework.permission.controller.sys;

import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.sys.SysUserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

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

    @GetMapping("index")
    public String index() {
        return "/admin/user_list";
    }

    @RequestMapping("list")
    @ResponseBody
    public String list() {
        JSONObject json = new JSONObject();
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(0, 15, sort);
        Page<SysUser> page = sysUserService.findAll("admin", pageable);
        List<SysUser> list = page.getContent();
        json.put("count", page.getTotalElements());
        json.put("data", page.getContent());
        json.put("code", 0);
        return json.toString();
    }

}
