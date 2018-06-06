package com.adoph.framework.permission.pojo;

import com.adoph.framework.pojo.BasePojo;

import java.io.Serializable;

/**
 * 系统用户角色关联表
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/9/14
 */
public class SysUserRole extends BasePojo implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
