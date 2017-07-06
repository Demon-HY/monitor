package com.monitor.web;

import com.monitor.web.common.interceptor.Auth;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
@RestController
@RequestMapping("/")
public class WebController {

    private static final Logger logger = Logger.getLogger(WebController.class);

    @RequestMapping(value="/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public ModelAndView login(Map<String, Object> model) {
        return new ModelAndView("login");
    }

    @RequestMapping(value="/index")
    public String index(Model model){
        return "index";
    }

    @RequestMapping(value="/register")
    public String register(Model model){
        return "register";
    }

    @Auth
    @RequestMapping(value="/test")
    public String test(Model model){
        return "test";
    }
}
