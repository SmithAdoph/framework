package com.adoph.framework.permission.controller.sys;

import com.adoph.framework.permission.OnlineUser;
import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.sys.SysUserService;
import com.adoph.framework.permission.vo.UserRequest;
import com.adoph.framework.pojo.Page;
import com.adoph.framework.web.controller.BaseController;
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
public class SysUserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysUserService sysUserService;

    /**
     * 用户列表
     *
     * @param user 查询条件
     * @return BaseResponse
     * @throws Exception
     */
    @RequestMapping("list.do")
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

    /**
     * 新增或更新用户信息
     *
     * @param user 用户信息
     * @return BaseResponse
     * @throws Exception
     */
    @RequestMapping("save.do")
    @ResponseBody
    public BaseResponse saveUser(SysUser user) throws Exception {
        String err = "保存用户信息异常，请联系管理员！";
        BaseResponse response = new BaseResponse();
        try {
            OnlineUser online = online();
            user.setCreatedBy(online.getSysUser().getId());
            user.setUpdatedBy(online.getSysUser().getId());
            sysUserService.saveUser(user);
        } catch (Exception e) {
            logger.error(err, e);
            response.error(err);
        }
        return response;
    }

}