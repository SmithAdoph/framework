package com.adoph.framework.web.controller.login;

import com.adoph.framework.pojo.permission.SysUser;
import com.adoph.framework.web.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 用户登录控制器
 *
 * @author Adohp
 * @version v1.0
 * @since 2017/8/11
 */
@Controller
@RequestMapping("login")
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("login.do")
    public ModelAndView login() {
        return new ModelAndView("login/login");
    }

    @RequestMapping(value = "doLogin.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse doLogin(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        BaseResponse response = new BaseResponse();
        try {
            String sql = "select * from sys_user where user_name = ? and password = ?";
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, userName, password);
            log.info(maps.toString());
            response.setMsg("登录成功！");
        } catch (DataAccessException e) {
            log.error("登录失败!", e);
            response.error("登录失败！");
        }
        return response;
    }

}
