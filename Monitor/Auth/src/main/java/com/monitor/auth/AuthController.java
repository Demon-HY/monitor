package com.monitor.auth;

import com.monitor.auth.domain.TokenInfo;
import com.monitor.baseservice.common.aop.LoggerManage;
import com.monitor.baseservice.common.interceptor.Auth;
import com.monitor.baseservice.utils.CookieUtils;
import com.monitor.baseservice.utils.DateUtils;
import com.monitor.user.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录认证接口
 * Created by demon on 2017/7/1 0001.
 */
@RestController
@Auth
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private LoginIdService loginIdService;
    @Autowired
    private TokenService tokenService;

    /**
     * 验证登录
     * 检查 cookie 中 token 是否过期
     *
     * @param request
     * @param response
     * @return
     */
    @Auth(continueCheck = false)
    @RequestMapping(value = "/api/checkLogin")
    @LoggerManage(description="验证登录")
    public Long checkLogin(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUtils.getCookie(request, "TOKEN");
        if (cookie == null) {
            return -1L;
        }

        TokenInfo tokenInfo = tokenService.getTokenInfo(cookie.getValue());
        if (tokenInfo == null) {
            return -1L;
        }

        return tokenInfo.getExpires().getTime() - DateUtils.getCurrentTimeMillis();
    }

    /**
     * 用户登录
     *
     * @param account
     * <blockquote>
     * 		类型：字符串<br/>
     * 		描述：手机号/用户名/邮箱<br/>
     * 		必需：YES
     * </blockquote>
     * @param password
     * <blockquote>
     * 		类型：字符串<br/>
     * 		描述：密码<br/>
     * 		必需：YES
     * </blockquote>
     * @param type
     * <blockquote>
     * 		类型：字符串<br/>
     * 		描述：账号类型：手机号(phone)/用户名(name)/邮箱(email)<br/>
     * 		必需：YES
     * </blockquote>
     * @param tokenAge
     * <blockquote>
     * 		类型：整形<br/>
     * 		描述：token 过期时间(单位：毫秒)<br/>
     * 		必需：NO
     * </blockquote>
     * @param isCookie
     * <blockquote>
     * 		类型：字符串<br/>
     * 		描述：是否写入cookie(no,yes)<br/>
     * 		必需：NO
     * </blockquote>
     * @return UserInfo
     */
    @RequestMapping("/api/login")
    @LoggerManage(description="登录")
    public UserInfo login(String account, String password, String type, Long tokenAge, String isCookie) {
        isCookie = null == isCookie ? "yes" : isCookie;







        return null;
    }

}
