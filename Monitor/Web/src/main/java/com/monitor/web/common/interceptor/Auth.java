package com.monitor.web.common.interceptor;

import java.lang.annotation.*;

/**
 * 在类或方法上添加@Auth就验证登录
 *
 * Created by Administrator on 2017/7/6 0006.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {

}
