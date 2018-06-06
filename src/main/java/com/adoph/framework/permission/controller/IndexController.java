package com.adoph.framework.permission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 默认控制器
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/11/23
 */
@Controller
public class IndexController {

    /**
     * 默认访问页面
     *
     * @return ModelAndView 首页视图
     */
    @RequestMapping(value = "index.do")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
