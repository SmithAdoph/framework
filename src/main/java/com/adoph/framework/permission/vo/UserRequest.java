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

    /**
     * 开始创建日期
     */
    private Long startCreateDate;

    /**
     * 结束创建日期
     */
    private Long endCreateDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getStartCreateDate() {
        return startCreateDate;
    }

    public void setStartCreateDate(Long startCreateDate) {
        this.startCreateDate = startCreateDate;
    }

    public Long getEndCreateDate() {
        return endCreateDate;
    }

    public void setEndCreateDate(Long endCreateDate) {
        this.endCreateDate = endCreateDate;
    }
}
