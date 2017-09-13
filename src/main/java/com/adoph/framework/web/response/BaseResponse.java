package com.adoph.framework.web.response;

/**
 * 统一返回前端包装实体
 *
 * @author Tangqiandong
 * @version v1.0
 * @since 2017/9/13
 */
public class BaseResponse<T> {
    /**
     * 返回状态值(默认：成功)
     *
     * @see ResponseStatus
     */
    private String status = ResponseStatus.SUCCESS_VALUE;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public String getStatus() {
        return status;
    }

    private void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 失败
     */
    public void error() {
        setStatus(ResponseStatus.ERROR_VALUE);
    }

    /**
     * 失败
     *
     * @param errorMsg 失败信息
     */
    public void error(String errorMsg) {
        setStatus(ResponseStatus.ERROR_VALUE);
        setMsg(errorMsg);
    }

    /**
     * 成功
     */
    public void success() {
        setStatus(ResponseStatus.SUCCESS_VALUE);
    }

    /**
     * 成功
     *
     * @param successMsg 成功信息
     */
    public void success(String successMsg) {
        setStatus(ResponseStatus.SUCCESS_VALUE);
        setMsg(successMsg);
    }
}
