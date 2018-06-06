package com.adoph.framework.exception;

/**
 * Web接口异常
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/12/29
 */
public class WebException extends Exception {
    public WebException(String message) {
        super(message);
    }
}
