package com.monitor.auth;

import com.monitor.baseservice.common.aop.LoggerManage;
import com.monitor.baseservice.common.interceptor.Auth;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录认证接口
 * Created by demon on 2017/7/1 0001.
 */
@RestController
@Auth
@RequestMapping("/auth/api")
public class AuthController {

    @Auth(continueCheck = false)
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    @LoggerManage(description="验证登录")
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) {
    }

}
