package com.adoph.framework.util;

import com.adoph.framework.permission.pojo.SysUser;
import com.alibaba.fastjson.JSON;

/**
 * JSON转换工具类
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/12/29
 */
public class JSONUtils {

    public static String toJSONString(Object o) {
        return JSON.toJSONString(o);
    }

    public static void main(String[] args) {
        SysUser user = new SysUser();
        user.setUserName("admin");
        System.out.println(toJSONString(user));
    }

}
