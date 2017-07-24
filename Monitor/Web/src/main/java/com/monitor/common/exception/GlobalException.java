package com.monitor.common.exception;

import com.monitor.baseservice.exception.LogicalException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理类
 *
 * Created by demon on 2017/7/2 0002.
 */
@ControllerAdvice
public class GlobalException {
    protected Logger logger = Logger.getLogger(GlobalException.class);

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception e, HttpServletRequest request) throws Exception {
        logger.error("请求地址：" + request.getRequestURL());
        ModelAndView mav = new ModelAndView();
        logger.error("异常信息：",e);
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = LogicalException.class)
    public LogicalException LogicalErrorHandler(LogicalException e, HttpServletRequest request) throws Exception {
        return e;
    }
}
