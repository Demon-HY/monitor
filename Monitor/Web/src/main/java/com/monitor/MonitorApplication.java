package com.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Spring-Boot baseservice entrance
 *
 * Created by demon on 2017/7/1 0001.
 */
//(exclude = { DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class })
@SpringBootApplication
public class MonitorApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MonitorApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }
}
