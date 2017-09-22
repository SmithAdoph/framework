package com.adoph.framework.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring工具类
 *
 * @author Adohp
 * @version v1.0
 * @since 2017/9/18
 */
@Component
//@Lazy(false)
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringUtils.context = context;
    }

    /**
     * 取得Bean
     *
     * @param id    bean实例id
     * @param clazz 类Class
     * @return T
     */
    public static <T> T getBean(String id, Class<?> clazz) {
        return (T) context.getBean(id, clazz);
    }

    /**
     * 取得Bean
     *
     * @param id bean实例id
     * @return T
     */
    public static <T> T getBean(String id) {
        return (T) context.getBean(id);
    }

    /**
     * 取得Bean
     *
     * @param clazz 类型
     * @return T
     */
    public static <T> T getBean(Class<?> clazz) {
        return (T) context.getBean(clazz);
    }

    /**
     * 判断Bean是否存在
     *
     * @param id bean实例id
     * @return boolean
     */
    public static boolean isBeanExist(String id) {
        return context.containsBean(id);
    }

    /**
     * 动态创建Bean
     *
     * @param clazz 类型
     * @return Object
     */
    public static Object createBean(Class<?> clazz) {
        return createBean(clazz, StringUtils.firstCharLowerCase(clazz.getSimpleName()));
    }

    /**
     * 动态创建Bean
     *
     * @param clazz    类型
     * @param beanName 实例名称
     * @return Object
     */
    public static Object createBean(Class<?> clazz, String beanName) {
        return createBean(clazz, beanName, new HashMap<String, Object>());
    }

    /**
     * 动态创建Bean
     *
     * @param clazz      类型
     * @param beanName   实例名称
     * @param properties 属性
     * @return Object
     */
    public static Object createBean(Class<?> clazz, String beanName, Map<String, Object> properties) {
        GenericApplicationContext ctx = (GenericApplicationContext) context;
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(clazz);
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            builder.addPropertyValue(entry.getKey(), entry.getValue());
        }
        ctx.registerBeanDefinition(beanName, builder.getBeanDefinition());
        return ctx.getBean(beanName);
    }
}

