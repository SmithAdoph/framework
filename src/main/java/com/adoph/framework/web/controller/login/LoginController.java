package com.adoph.framework.web.controller.login;

import com.adoph.framework.pojo.permission.SysUser;
import com.adoph.framework.service.common.LoginService;
import com.adoph.framework.util.RSAEncryptUtils;
import com.adoph.framework.web.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.KeyPair;
import java.util.UUID;

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

    @Autowired
    private LoginService loginService;

    @RequestMapping("login.do")
    public ModelAndView login() {
        ModelAndView loginMav = new ModelAndView("login/login");
        loginMav.addObject("loginId", UUID.randomUUID().toString());
        KeyPair keyPair = RSAEncryptUtils.genKeyPair();
        loginMav.addObject("publicKey", RSAEncryptUtils.getPublicKey(keyPair));
        return loginMav;
    }

    @RequestMapping(value = "doLogin.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse doLogin(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        BaseResponse response = new BaseResponse();
//        String sql = "select * from sys_user where user_name = ? and password = ?";
//        try {
//            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, userName, password);
//            log.info(maps.toString());
//            response.setMsg("登录成功！");
//        } catch (DataAccessException e) {
//            log.error("登录失败!", e);
//            response.error("登录失败！");
//        }
        SysUser user = null;
        try {
            user = loginService.login(userName, password);
            if (user == null) {
                response.error("用户名或者密码错误！");
            } else {
                response.success("登录成功！");
            }
        } catch (Exception e) {
            log.error("登录失败!", e);
        }
        return response;
    }

}
