package com.adoph.framework.util;

/**
 * String工具类：
 * 1.继承org.apache.commons.lang3.StringUtils
 * 2.自定义功能
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/9/18
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{

    /**
     * 首字母小写
     *
     * @param content 输入内容
     * @return String
     */
    public static String firstCharLowerCase(String content) {
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(content)) {
            return content.substring(0, 1).toLowerCase() + content.substring(1);
        }
        return content;
    }

}
