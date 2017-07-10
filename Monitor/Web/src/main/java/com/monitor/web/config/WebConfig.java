package com.monitor.web.config;

import com.monitor.web.common.interceptor.LoginInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * Created by Administrator on 2017/7/1 0001.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.monitor")
@PropertySource(value = "classpath:application.yml",
        ignoreResourceNotFound = true,encoding = "UTF-8")
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Autowired
    LoginInterceptor loginInterceptor;

//    /**
//     * 视图处理器
//     * @return
//     */
//    @Bean
//    public ViewResolver viewResolver() {
//        logger.info("ViewResolver");
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/templates/");
//        viewResolver.setSuffix(".html");
//        return viewResolver;
//    }

    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册监控拦截器
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/");
    }

    /**
     * 资源处理器
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("addResourceHandlers");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}