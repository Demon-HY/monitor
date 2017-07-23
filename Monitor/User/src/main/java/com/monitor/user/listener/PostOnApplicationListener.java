package com.monitor.user.listener;

import com.monitor.auth.LoginIdService;
import com.monitor.baseservice.exception.LogicalException;
import com.monitor.user.InitUser;
import com.monitor.user.UserConfig;
import com.monitor.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * 监听工程启动的监听事件
 *
 * Created by demon on 2017/7/15 0015.
 */
public class PostOnApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    public PostOnApplicationListener() {}

    @Autowired
    UserConfig userConfig;
    @Autowired
    UserService userService;
    @Autowired
    LoginIdService loginIdService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // 二次调用问题
        // 传统的application.xml和project-servlet.xml配置中会出现二次调用的问题。主要原因是初始化root容器之后，
        // 会初始化project-servlet.xml对应的子容器
        if(applicationReadyEvent.getApplicationContext().getParent() != null){
            return;
        }

        // 初始化用户
        InitUser initUser = new InitUser();
        try {
            initUser.initUser(userConfig, userService, loginIdService);
        } catch (LogicalException e) {
            e.printStackTrace();
        }
    }
}
