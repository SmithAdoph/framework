package com.adoph.framework.permission.controller.sys;

import com.adoph.framework.permission.OnlineUser;
import com.adoph.framework.permission.pojo.SysMenu;
import com.adoph.framework.permission.service.sys.SysMenuService;
import com.adoph.framework.web.controller.BaseController;
import com.adoph.framework.web.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统菜单管理
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/2/6
 */
@RequestMapping("menu")
@Controller
public class SysMenuController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysMenuService sysMenuService;

    /**
     * 获取所有菜单列表
     *
     * @return BaseResponse
     */
    @RequestMapping("queryMenus.do")
    @ResponseBody
    public BaseResponse<List<SysMenu>> queryMenus() {
        BaseResponse<List<SysMenu>> response = new BaseResponse<>();
        try {
            response.setData(sysMenuService.queryMenus());
        } catch (Exception e) {
            logger.error("获取菜单信息异常，请联系管理员！", e);
            response.error("获取菜单信息异常，请联系管理员！");
        }
        return response;
    }

    /**
     * 异步加载菜单列表
     *
     * @param pid 父节点id
     * @return BaseResponse
     */
    @RequestMapping("queryMenusByPid.do")
    @ResponseBody
    public BaseResponse<List<SysMenu>> queryMenusByPid(Long pid) {
        BaseResponse<List<SysMenu>> response = new BaseResponse<>();
        try {
            response.setData(sysMenuService.queryMenusByPid(pid));
        } catch (Exception e) {
            logger.error("获取菜单信息异常，请联系管理员！", e);
            response.error("获取菜单信息异常，请联系管理员！");
        }
        return response;
    }

    /**
     * 新增或者更新菜单
     *
     * @param menu 菜单信息
     * @return BaseResponse
     */
    @RequestMapping("saveMenu.do")
    @ResponseBody
    public BaseResponse saveMenu(SysMenu menu) {
        BaseResponse response = new BaseResponse();
        try {
            OnlineUser online = online();
            menu.setCreatedBy(online.getSysUser().getId());
            menu.setUpdatedBy(online.getSysUser().getId());
            sysMenuService.saveMenu(menu);
        } catch (Exception e) {
            logger.error("保存菜单信息异常，请联系管理员！", e);
            response.error("保存菜单信息异常，请联系管理员！");
        }
        return response;
    }

    /**
     * 删除菜单
     *
     * @param id 主键
     * @return BaseResponse
     */
    @RequestMapping("delMenu.do")
    @ResponseBody
    public BaseResponse delMenu(Long id) {
        BaseResponse response = new BaseResponse();
        try {
            sysMenuService.delMenu(id);
        } catch (Exception e) {
            logger.error("删除菜单异常，请联系管理员！", e);
            response.error("删除菜单异常，请联系管理员！");
        }
        return response;
    }

}
