package com.adoph.framework.permission.controller.sys;

import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.sys.SysUserService;
import com.adoph.framework.permission.vo.UserRequest;
import com.adoph.framework.pojo.Page;
import com.adoph.framework.web.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("list")
    @ResponseBody
    public BaseResponse<Page<SysUser>> list(UserRequest user) throws Exception {
        String err = "查询用户列表异常，请联系管理员！";
        BaseResponse<Page<SysUser>> response = new BaseResponse<>();
        try {
            response.setData(sysUserService.queryUserList(user));
        } catch (Exception e) {
            logger.error(err, e);
            response.error(err);
        }
        return response;
    }

}