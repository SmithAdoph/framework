package com.adoph.framework.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring工具类
 *
 * @author Adohp
 * @version v1.0
 * @date 2017/9/18
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringUtils.context = context;
    }

    /**
     * 取得Bean
     *
     * @param id bean实例id
     * @return Object
     */
    public static Object getBean(String id) {
        return context.getBean(id);
    }

    /**
     * 取得Bean
     *
     * @param clazz 类型
     * @return Object
     */
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    /**
     * 取得Bean
     *
     * @param id    bean实例id
     * @param clazz 类Class
     * @return T
     */
    public static <T> T getBean(String id, Class<T> clazz) {
        return context.getBean(id, clazz);
    }

    /**
     * 判断Bean是否存在
     *
     * @param id bean实例id
     * @return boolean
     */
    public static boolean containsBean(String id) {
        return context.containsBean(id);
    }

    /**
     * 动态创建Bean
     *
     * @param clazz 类型
     * @return Object
     */
    public static <T> T createBean(Class<T> clazz) {
        return createBean(clazz, StringUtils.firstCharLowerCase(clazz.getSimpleName()));
    }

    /**
     * 动态创建Bean
     *
     * @param clazz 类型
     * @param id    bean实例id
     * @return Object
     */
    public static <T> T createBean(Class<T> clazz, String id) {
        return createBean(clazz, id, new HashMap<>());
    }

    /**
     * 动态创建Bean
     *
     * @param clazz      类型
     * @param id         bean实例id
     * @param properties 属性
     * @return Object
     */
    public static <T> T createBean(Class<T> clazz, String id, Map<String, Object> properties) {
        GenericApplicationContext ctx = (GenericApplicationContext) context;
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(clazz);
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            builder.addPropertyValue(entry.getKey(), entry.getValue());
        }
        ctx.registerBeanDefinition(id, builder.getBeanDefinition());
        return getBean(id, clazz);
    }
}