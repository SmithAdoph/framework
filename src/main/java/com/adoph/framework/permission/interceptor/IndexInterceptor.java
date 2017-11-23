package com.adoph.framework.permission.interceptor;

import com.adoph.framework.core.cache.CacheFactory;
import com.adoph.framework.core.cache.service.CacheService;
import com.adoph.framework.permission.constant.LoginConstant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器handler
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/11/23
 */
public class IndexInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginId = request.getParameter("loginId");
        if (loginId == null) {
            return false;
        }
//        CacheService redisCache = CacheFactory.getStringRedisCache();
//        redisCache.get(LoginConstant.LOGIN_ONLINE_TIMEOUT);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
