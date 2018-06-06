package com.adoph.framework.exception.handler;

import com.adoph.framework.exception.bean.Error;
import com.adoph.framework.util.JSONUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * web异常处理类
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/12/29
 */
@ControllerAdvice
public class WebExceptionHandler {
    /**
     * 异常默认返回视图
     */
    private static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        String accept = request.getHeader("Accept");
        String requestType = request.getHeader("X-Requested-With");
        boolean ajax = requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest");
        if (ajax || accept.contains("json")) {//ajax请求或者请求端接受json数据
            response.setHeader("SystemException", "system exception");
//            response.sendError(500, "The system throws an exception!");
            response.setStatus(500);
            Error<String> r = new Error<>();
            r.error(e.getMessage());
            r.setUrl(request.getRequestURL().toString());
            PrintWriter writer = response.getWriter();
            writer.write(JSONUtils.toJSONString(r));
            writer.flush();
            writer.close();
            return null;
        } else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("exception", e);
            mav.addObject("url", request.getRequestURL());
            mav.setViewName(DEFAULT_ERROR_VIEW);
            return mav;
        }
    }
}
