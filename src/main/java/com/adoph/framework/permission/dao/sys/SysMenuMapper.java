package com.adoph.framework.permission.dao.sys;

import com.adoph.framework.permission.pojo.SysMenu;

import java.util.List;

/**
 * 系统菜单
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/1/30
 */
public interface SysMenuMapper {

    /**
     * 查询所有菜单
     *
     * @return List
     */
    List<SysMenu> selectMenus();

    /**
     * 异步加载菜单树
     *
     * @param pid 父节点id
     * @return List
     */
    List<SysMenu> selectMenusByPid(Long pid);

    /**
     * 查询菜单信息
     *
     * @param id 主键
     * @return SysMenu
     */
    SysMenu selectMenuById(Long id);

    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     */
    void insertMenu(SysMenu menu);

    /**
     * 更新菜单
     *
     * @param menu 菜单信息
     */
    void updateMenu(SysMenu menu);

    /**
     * 删除菜单
     *
     * @param id 主键
     */
    void deleteMenu(Long id);
}
