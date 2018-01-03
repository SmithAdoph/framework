package com.adoph.framework.permission.pojo;

import com.adoph.framework.pojo.BasePojo;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 系统用户
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/9/14
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

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
