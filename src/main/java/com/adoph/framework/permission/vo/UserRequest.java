package com.adoph.framework.permission.vo;

import com.adoph.framework.web.request.BasePageRequest;

import java.io.Serializable;

/**
 * 用户列表查询请求参数
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/12/3
 */
public class UserRequest extends BasePageRequest implements Serializable {

    /**
     * 用户名
     */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
