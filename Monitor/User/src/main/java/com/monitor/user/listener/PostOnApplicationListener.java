package com.monitor.user.listener;

import com.monitor.user.UserConfig;
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

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // 二次调用问题
        // 传统的application.xml和project-servlet.xml配置中会出现二次调用的问题。主要原因是初始化root容器之后，
        // 会初始化project-servlet.xml对应的子容器
        if(applicationReadyEvent.getApplicationContext().getParent() != null){
            return;
        }

        // 初始化用户

        System.out.println(userConfig.env.getProperty("monitor.user.init.admin.username"));
    }
}
