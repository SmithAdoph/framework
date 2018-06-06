package com.adoph.framework.util;

import com.adoph.framework.test.design.pattern.observer.v3.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * PropertiesUtils
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/3/22
 */
public class PropertiesUtils {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    /**
     * 获取Properties
     *
     * @param fileName 文件名（resources目录下）
     * @return Properties
     */
    public static Properties loadProperties(String fileName) {
        Properties props = new Properties();
        try {
            props.load(Test.class.getClassLoader().getResourceAsStream("test/observer.properties"));
        } catch (IOException e) {
            logger.error("加载[" + fileName + "]文件异常！", e);
        }
        return props;
    }

    /**
     * 根据文件路径和key获取值
     *
     * @param fileName 文件路径（resources目录下）
     * @param key      数据key
     * @return String
     */
    public static String getProperty(String fileName, String key) {
        return loadProperties(fileName).getProperty(key);
    }

}
