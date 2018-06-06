package com.adoph.framework.permission.controller.sys;

import com.adoph.framework.permission.OnlineUser;
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

import java.util.List;

import static com.adoph.framework.permission.constant.Operation.REPEAT_PROPERTIES;

/**
 * 系统角色管理控制层
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/1/15
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

    /**
     * 获取所有的角色信息
     *
     * @return BaseResponse
     * @throws Exception
     */
    @RequestMapping("queryAllRoles.do")
    @ResponseBody
    public BaseResponse<List<SysRole>> queryAllRoles() throws Exception {
        String err = "查询角色列表异常，请联系管理员！";
        BaseResponse<List<SysRole>> response = new BaseResponse<>();
        try {
            response.setData(sysRoleService.queryAllRoles());
        } catch (Exception e) {
            logger.error(err, e);
            response.error(err);
        }
        return response;
    }

    /**
     * 新增或者更新角色信息
     *
     * @param role 角色信息
     * @return BaseResponse
     */
    @RequestMapping("save.do")
    @ResponseBody
    public BaseResponse saveRole(SysRole role) {
        BaseResponse response = new BaseResponse();
        try {
            OnlineUser online = online();
            long userId = online.getSysUser().getId();
            role.setCreatedBy(userId);
            role.setUpdatedBy(userId);
            int r = sysRoleService.saveRole(role);
            if (r == REPEAT_PROPERTIES) {
                response.error("保存角色信息异常，角色名称重复！");
            }
        } catch (Exception e) {
            logger.error("保存角色信息异常，请联系管理员！", e);
            response.error("保存角色信息异常，请联系管理员！");
        }
        return response;
    }

    /**
     * 删除角色
     *
     * @param id 主键
     * @return BaseResponse
     */
    @RequestMapping("del.do")
    @ResponseBody
    public BaseResponse delRole(long id) {
        BaseResponse response = new BaseResponse();
        try {
            sysRoleService.delRole(id);
        } catch (Exception e) {
            logger.error("删除角色异常，请联系管理员！", e);
            response.error("删除角色异常，请联系管理员！");
        }
        return response;
    }

}