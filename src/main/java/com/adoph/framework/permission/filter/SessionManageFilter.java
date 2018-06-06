package com.adoph.framework.permission.filter;

import org.springframework.core.annotation.Order;
import org.springframework.session.web.http.HttpSessionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 用户会话管理过滤器
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/11/22
 */
@Order(1)
@WebFilter(filterName = "sessionManageFilter", urlPatterns = "/*")
public class SessionManageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
