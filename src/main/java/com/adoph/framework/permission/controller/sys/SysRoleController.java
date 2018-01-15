package com.adoph.framework.permission.controller.sys;

import com.adoph.framework.permission.pojo.SysRole;
import com.adoph.framework.permission.service.sys.SysRoleService;
import com.adoph.framework.permission.vo.RoleRequest;
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
 * 系统角色管理控制层
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/1/15
 */
@Controller
@RequestMapping("sysRole")
public class SysRoleController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 用户列表
     *
     * @param params 查询条件
     * @return BaseResponse
     * @throws Exception
     */
    @RequestMapping("list.do")
    @ResponseBody
    public BaseResponse<Page<SysRole>> list(RoleRequest params) throws Exception {
        String err = "查询角色列表异常，请联系管理员！";
        BaseResponse<Page<SysRole>> response = new BaseResponse<>();
        try {
            response.setData(sysRoleService.queryRoleList(params));
        } catch (Exception e) {
            logger.error(err, e);
            response.error(err);
        }
        return response;
    }

}