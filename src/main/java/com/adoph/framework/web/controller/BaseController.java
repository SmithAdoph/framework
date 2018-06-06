package com.adoph.framework.web.controller;

import com.adoph.framework.permission.OnlineUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.adoph.framework.permission.constant.LoginConstant.LOGIN_ONLINE_TAG;

/**
 * 基础控制器
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/1/10
 */
@Component
public class BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public HttpServletRequest request;
    public HttpServletResponse response;

    @ModelAttribute
    public void getRequestResponse(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * 获取当前在线用户信息
     *
     * @return OnlineUser
     */
    public OnlineUser online() {
        return (OnlineUser) request.getSession().getAttribute(LOGIN_ONLINE_TAG);
    }
}
