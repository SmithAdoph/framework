package com.adoph.framework.permission.controller.sys;

import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.sys.SysUserService;
import com.adoph.framework.web.response.BaseResponse;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    @Resource
    private SysUserService sysUserService;

    @GetMapping("index")
    public String index() {
        return "/admin/user_list";
    }

    @RequestMapping("list")
    @ResponseBody
    public String list(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        JSONObject json = new JSONObject();
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(page - 1, pageSize, sort);
        Page<SysUser> userPage = sysUserService.findByUserName("admin%", pageable);
        json.put("count", userPage.getTotalElements());
        json.put("rows", userPage.getContent());
        json.put("status", "success");
        return json.toString();
    }

}