package com.adoph.framework.util;

import org.json.JSONObject;

/**
 * 系统相关工具类：
 * 1.打印对象
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/11/16
 */
public class SysUtils {

    /**
     * 以json格式打印对象
     *
     * @param o 输入对象
     */
    public static void print(Object o) {
        try {
            System.out.println(JSONObject.valueToString(o));
        } catch (Exception e) {
            System.out.println(o.toString());
        }
    }

}