package com.adoph.framework.permission.pojo;

import com.adoph.framework.pojo.BasePojo;

import java.io.Serializable;

/**
 * 系统角色
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/9/14
 */
public class SysRole extends BasePojo implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
