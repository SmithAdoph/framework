package com.adoph.framework.permission.interceptor;

import com.adoph.framework.core.cache.CacheFactory;
import com.adoph.framework.core.cache.service.CacheService;
import com.adoph.framework.permission.OnlineUser;
import com.adoph.framework.permission.constant.LoginConstant;
import com.adoph.framework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.adoph.framework.permission.constant.LoginConstant.LOGIN_ONLINE_TAG;
import static com.adoph.framework.permission.constant.LoginConstant.UNDERLINE;

/**
 * 过滤器handler
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/11/23
 */
public class IndexInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        OnlineUser online = null;
        Object _user = request.getSession().getAttribute(LOGIN_ONLINE_TAG);
        if (_user != null) {
            online = (OnlineUser) _user;
        }
        if (online == null) {
            String requestType = request.getHeader("X-Requested-With");
            if (!StringUtils.isEmpty(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest")) {
                // ajax请求
                response.setHeader("SessionStatus", "timeout");
                response.setStatus(520);
            } else {
                // 非ajax，直接跳转登录页面
                response.sendRedirect("index.do");
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
