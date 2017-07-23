package com.monitor.user;

import com.monitor.baseservice.common.aop.LoggerManage;
import com.monitor.user.domain.UserInfo;
import com.monitor.user.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户信息操作接口
 *
 * Created by demon on 2017/7/1 0001.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/api/test")
    @LoggerManage(description="测试")
    public String test() {
        return "OK";
    }

    @RequestMapping("/api/mybatis")
    public String testMybatis() {
        UserInfo user = new UserInfo();
        user.setType(1);
        user.setStatus(0);
        user.setName("admin");
        user.setPassword("123456");
        user.setCtime(new Date());
        user.setMtime(new Date());
        user.setLast_time(new Date());

        Integer result = userMapper.create(user);

        return result.toString();
    }

}
