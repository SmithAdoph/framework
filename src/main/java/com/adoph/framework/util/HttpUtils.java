package com.adoph.framework.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Http请求工具类
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/1/5
 */
public class HttpUtils {
    /**
     * 获取请求远程地址
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
}
