package com.monitor.auth;

import com.monitor.baseservice.common.aop.LoggerManage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录认证接口
 * Created by Administrator on 2017/7/1 0001.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/test")
    @LoggerManage(description="测试")
    public void test() {
        System.out.println("test");
    }

}
