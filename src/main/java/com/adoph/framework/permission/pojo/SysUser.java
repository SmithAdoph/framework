package com.adoph.framework.permission.pojo;

import com.adoph.framework.pojo.BasePojo;
import org.springframework.data.annotation.Id;

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
public class SysUser extends BasePojo implements Serializable {

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
     * 最后登录地址
     */
    private String lastLoginHost;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 用户角色
     */
    private List<SysRole> sysRoles;

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

    public String getLastLoginHost() {
        return lastLoginHost;
    }

    public void setLastLoginHost(String lastLoginHost) {
        this.lastLoginHost = lastLoginHost;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", lastLoginHost='" + lastLoginHost + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
