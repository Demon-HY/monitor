package com.monitor.baseservice.common.aop;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * Created by demon on 2017/7/1 0001.
 */
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManage {

    String description();
}
