package com.monitor.baseservice.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 全局配置文件
 *
 * Created by demon on 2017/7/1 0001.
 */
@Component
public class GlobalConfig {

    public static String BASE_PATH = "http://localhost:8080/";
    public static String LAST_REFERER = "LAST_REFERER";

    public static final String LOGIN_SESSION_KEY = "TOKEN";
    public static final String PASSWORD_KEY = "@#$%^&*()MON#$%^&*(DE";
    public static String DES3_KEY = "9964DYByKL967c3308imytCB";

    @Autowired(required = true)
    public void setBasePath(@Value("${monitor.base.path}")String basePath) {
        BASE_PATH = basePath;
    }
}
