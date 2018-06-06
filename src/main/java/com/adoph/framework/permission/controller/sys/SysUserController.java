package com.adoph.framework.permission.controller.sys;

import com.adoph.framework.permission.OnlineUser;
import com.adoph.framework.permission.pojo.SysRole;
import com.adoph.framework.permission.pojo.SysUser;
import com.adoph.framework.permission.service.sys.SysUserService;
import com.adoph.framework.permission.vo.SysUserVO;
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

import java.util.List;

import static com.adoph.framework.permission.constant.Operation.REPEAT_PROPERTIES;

/**
 * 系统用户管理控制层
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/10/13
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
     * @param userVO 用户信息
     * @return BaseResponse
     * @throws Exception
     */
    @RequestMapping("save.do")
    @ResponseBody
    public BaseResponse saveUser(SysUserVO userVO) throws Exception {
        String err = "保存用户信息异常，请联系管理员！";
        BaseResponse response = new BaseResponse();
        try {
            OnlineUser online = online();
            userVO.setCreatedBy(online.getSysUser().getId());
            userVO.setUpdatedBy(online.getSysUser().getId());
            Integer r = sysUserService.saveUser(userVO);
            if (r == REPEAT_PROPERTIES) {
                response.error("保存失败，用户名重复！");
            }
        } catch (Exception e) {
            logger.error(err, e);
            response.error(err);
        }
        return response;
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return BaseResponse
     * @throws Exception
     */
    @RequestMapping("del.do")
    @ResponseBody
    public BaseResponse saveUser(Long id) throws Exception {
        String err = "删除失败，请联系管理员！";
        BaseResponse response = new BaseResponse();
        try {
            sysUserService.delUser(id);
        } catch (Exception e) {
            logger.error(err, e);
            response.error(err);
        }
        return response;
    }

    /**
     * 获取用户角色
     *
     * @param userId 用户id
     * @return BaseResponse
     */
    @RequestMapping("queryUserRoles.do")
    @ResponseBody
    public BaseResponse<List<SysRole>> queryUserRoles(Long userId) {
        BaseResponse<List<SysRole>> response = new BaseResponse<>();
        try {
            response.setData(sysUserService.queryUserRoles(userId));
        } catch (Exception e) {
            logger.error("获取用户角色异常，请联系管理员！", e);
            response.error("获取用户角色异常，请联系管理员！");
        }
        return response;
    }

}