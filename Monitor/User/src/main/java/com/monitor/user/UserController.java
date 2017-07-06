package com.monitor.user;

import com.monitor.baseservice.common.aop.LoggerManage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息操作接口
 *
 * Created by Administrator on 2017/7/1 0001.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/")
    @LoggerManage(description="测试")
    public void test() {
        System.out.println("test");
    }

}
