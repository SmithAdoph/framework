package com.adoph.framework.permission.controller;

import com.adoph.framework.permission.LoginManager;
import com.adoph.framework.util.RSAEncryptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.KeyPair;
import java.util.UUID;

/**
 * 默认控制器
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/11/23
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
        ModelAndView mav = new ModelAndView("index");
        String loginId = UUID.randomUUID().toString();
        KeyPair keyPair = LoginManager.addKey(loginId);
        mav.addObject("loginId", loginId);
        mav.addObject("publicKey", RSAEncryptUtils.getPublicKey(keyPair));
        return mav;
    }
}
