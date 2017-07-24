package com.monitor.baseservice.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.monitor.baseservice.common.config.GlobalConfig.LOGIN_SESSION_KEY;

/**
 * 登录拦截器
 *
 * Created by demon on 2017/7/6 0006.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * Controller 调用前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();

        if (clazz.isAnnotationPresent(Auth.class) || method.isAnnotationPresent(Auth.class)) {
            if (method.isAnnotationPresent(Auth.class)) {
                Auth auth = method.getAnnotation(Auth.class);
                if (!auth.continueCheck()) { // 跳过验证
                    return true;
                }
            }
            if(request.getAttribute(LOGIN_SESSION_KEY) == null) {
                throw new Exception("用户未登录");
            } else {
                // 查看token是否已过期
                return true;
            }
        }

        return true;
    }

    /**
     * Controller 调用后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
