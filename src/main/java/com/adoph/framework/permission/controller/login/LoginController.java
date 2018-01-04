package com.adoph.framework.permission.controller.login;

import com.adoph.framework.permission.LoginManager;
import com.adoph.framework.permission.OnlineUser;
import com.adoph.framework.permission.service.login.LoginService;
import com.adoph.framework.permission.vo.LoginVO;
import com.adoph.framework.util.RSAEncryptUtils;
import com.adoph.framework.web.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyPair;
import java.util.UUID;

import static com.adoph.framework.permission.constant.LoginConstant.FAIL_COUNT_MAX;
import static com.adoph.framework.permission.constant.LoginConstant.LOGIN_ONLINE_TAG;

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

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoginService loginService;

    @RequestMapping("login.do")
    public ModelAndView login() {
        ModelAndView loginMav = new ModelAndView("login/login");
        String loginId = UUID.randomUUID().toString();
        KeyPair keyPair = LoginManager.addKey(loginId);
        loginMav.addObject("loginId", loginId);
        loginMav.addObject("publicKey", RSAEncryptUtils.getPublicKey(keyPair));
        return loginMav;
    }

    @RequestMapping("authPubKey.do")
    @ResponseBody
    public String authPubKey(@RequestParam("loginId") String loginId) {
        Assert.notNull(loginId, "loginId不能为空！");
        KeyPair keyPair = LoginManager.addKey(loginId);
        return RSAEncryptUtils.getPublicKey(keyPair);
    }

    @RequestMapping(value = "doLogin.do", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse doLogin(@RequestParam("loginId") String loginId, @RequestParam("userName") String userName,
                                @RequestParam("password") String password, HttpServletRequest request) {
        BaseResponse<LoginVO> response = new BaseResponse<>();
        Long failCount = LoginManager.getFailCount(loginId);
        if (failCount != null) {
            if (failCount >= FAIL_COUNT_MAX) {
                String verifyCode = request.getParameter("verifyCode");
                if (verifyCode == null || !LoginManager.verifyCode(loginId, verifyCode)) {
                    response.error("验证码输入错误！");
                    response.setData(new LoginVO(1));
                    return response;
                }
            }
        }
        OnlineUser user;
        try {
            user = LoginManager.login(loginId, userName, password);
            if (user == null) {
                failCount = LoginManager.getFailCount(loginId);
                if (failCount == null) {
                    // 非法登录
                    log.warn("{非法登录！loginId=" + loginId + "失效！}");
                    response.error("登录异常，请联系管理员！");
                } else {
                    if (failCount >= FAIL_COUNT_MAX) {
                        response.setData(new LoginVO(1));
                    }
                    response.error("用户名或者密码错误！");
                }
            } else {
                HttpSession session = request.getSession();
                session.setAttribute(LOGIN_ONLINE_TAG, user);
                session.setMaxInactiveInterval(60 * 30);//默认session有效时间30分钟
                response.setData(new LoginVO(user));//返回登录用户信息
                response.success("登录成功！");
            }
        } catch (Exception e) {
            response.error("登录异常，请联系管理员！");
            log.error("{登录失败!}", e);
        }
        return response;
    }

    /**
     * 注销登录
     *
     * @param request 请求信息
     * @return BaseResponse<Integer>
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse logout(HttpServletRequest request) {
        BaseResponse resp = new BaseResponse<>();
        try {
            request.getSession().setMaxInactiveInterval(0);
        } catch (Exception e) {
            resp.error("注销登录失败，请重试！");
            log.error("{注销登录失败!}", e);
        }
        return resp;
    }

    /**
     * 获取验证码
     *
     * @param loginId  登录id
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @RequestMapping(value = "verifyCode.do", method = RequestMethod.GET)
    public void verifyCode(@RequestParam("loginId") String loginId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ByteArrayInputStream is = null;
        OutputStream os = null;
        try {
            is = LoginManager.getVerifyCodeImage(loginId);
            response.addHeader("Content-Disposition", "attachment;filename=verifyCode.jpeg");
            response.setContentType("image/jpeg");
            os = response.getOutputStream();  //创建输出流
            byte[] b = new byte[1024];
            while (is.read(b) != -1) {
                os.write(b);
            }
        } catch (IOException e) {
            log.error("{获取验证码异常!}", e);
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.flush();
            }
            if (os != null) {
                os.close();
            }
        }
    }

}
