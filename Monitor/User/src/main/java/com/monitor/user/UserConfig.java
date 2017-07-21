package com.monitor.user;

import com.monitor.user.listener.PostOnApplicationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 *
 *
 * Created by Administrator on 2017/7/15 0015.
 */
@Component
@Configuration
public class UserConfig {


    @Autowired
    public Environment env; //当前环境的application.properties的 配置

    @Value("${monitor.user.init.user}")
    public String user;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public PostOnApplicationListener applicationStartListener(){
        return new PostOnApplicationListener();
    }

//    public void outputResource(){
//        try {
//            System.out.println(user);
//            System.out.println(env.getProperty("monitor.user.init.admin.username"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}