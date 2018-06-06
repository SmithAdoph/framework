package com.adoph.framework.permission.vo;

import com.adoph.framework.web.request.BasePageRequest;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 用户列表查询请求参数
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/12/3
 */
public class UserRequest extends BasePageRequest implements Serializable {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 开始创建日期
     */
    @DateTimeFormat
    private Long startCreateTime;

    /**
     * 结束创建日期
     */
    @DateTimeFormat
    private Long endCreateTime;

    /**
     * 开始最后登录时间
     */
    @DateTimeFormat
    private Long startLastLoginTime;

    /**
     * 结束最后登录时间
     */
    @DateTimeFormat
    private Long endLastLoginTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Long startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Long getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Long endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public Long getStartLastLoginTime() {
        return startLastLoginTime;
    }

    public void setStartLastLoginTime(Long startLastLoginTime) {
        this.startLastLoginTime = startLastLoginTime;
    }

    public Long getEndLastLoginTime() {
        return endLastLoginTime;
    }

    public void setEndLastLoginTime(Long endLastLoginTime) {
        this.endLastLoginTime = endLastLoginTime;
    }
}
