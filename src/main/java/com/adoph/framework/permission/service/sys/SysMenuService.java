package com.adoph.framework.permission.service.sys;

import com.adoph.framework.permission.pojo.SysMenu;

import java.util.List;

/**
 * 系统菜单管理
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/1/30
 */
public interface SysMenuService {

    /**
     * 查询菜单列表（树形）
     *
     * @return List
     */
    List<SysMenu> queryMenus();

    /**
     * 新增或者更新菜单
     *
     * @param menu 菜单信息
     */
    void saveMenu(SysMenu menu);

    /**
     * 删除菜单
     *
     * @param id 主键
     */
    void delMenu(Long id);
}
