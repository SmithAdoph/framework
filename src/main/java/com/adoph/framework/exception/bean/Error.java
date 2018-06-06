package com.adoph.framework.exception.bean;

import com.adoph.framework.web.response.BaseResponse;

import java.io.Serializable;

/**
 * 错误消息
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/12/29
 */
public class Error<T> extends BaseResponse<T> implements Serializable {

    /**
     * 请求地址
     */
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
