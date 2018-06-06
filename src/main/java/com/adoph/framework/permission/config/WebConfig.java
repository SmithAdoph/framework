package com.adoph.framework.permission.config;

import com.adoph.framework.permission.interceptor.IndexInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Web相关配置适配器
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/11/22
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * 静态资源路径配置
     *
     * @param registry 资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/app/**").addResourceLocations("classpath:/templates/app/");
        super.addResourceHandlers(registry);
    }

    /**
     * 默认访问视图
     *
     * @param registry 视图
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.do");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    /**
     * 过滤器拦截规则
     *
     * @param registry 过滤器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IndexInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.do", "/login/**", "/error");
        super.addInterceptors(registry);
    }
}
