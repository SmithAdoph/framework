package com.adoph.framework.exception;

/**
 * 工具类异常
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/9/15
 */
public class UtilException extends RuntimeException {
    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilException(String message) {
        super(message);
    }
}
