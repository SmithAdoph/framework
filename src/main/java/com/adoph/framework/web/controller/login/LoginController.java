package com.adoph.framework.web.controller.login;

import com.adoph.framework.web.response.BaseResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户登录控制器
 *
 * @author Tangqiandong
 * @version v1.0
 * @since 2017/8/11
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping("login.do")
    public ModelAndView login() {
        return new ModelAndView("login/login");
    }

    @RequestMapping(value = "doLogin.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse doLogin(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        BaseResponse response = new BaseResponse();
        if (userName.equals("admin") && password.equals("1")) {
            response.setMsg("登录成功！");
        } else {
            response.error("登录失败！");
        }
        return response;
    }

}
