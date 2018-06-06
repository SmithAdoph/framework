package com.adoph.framework.permission.pojo;

import com.adoph.framework.pojo.BasePojo;

import java.io.Serializable;

/**
 * 系统角色菜单关联表
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/9/14
 */
public class SysRoleMenu extends BasePojo implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单id
     */
    private Long menuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
