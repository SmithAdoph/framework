package com.adoph.framework.permission.vo;

import com.adoph.framework.permission.pojo.SysRole;
import com.adoph.framework.pojo.BasePojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/9/14
 */
public class SysUserVO extends BasePojo implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户角色
     */
    private List<Long> sysRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Long> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<Long> sysRoles) {
        this.sysRoles = sysRoles;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
