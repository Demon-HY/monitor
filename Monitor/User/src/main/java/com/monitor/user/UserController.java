package com.monitor.user;

import com.monitor.baseservice.common.aop.LoggerManage;
import com.monitor.user.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息操作接口
 *
 * Created by demon on 2017/7/1 0001.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/api/test")
    @LoggerManage(description="测试")
    public String test() {
        return "OK";
    }



}
