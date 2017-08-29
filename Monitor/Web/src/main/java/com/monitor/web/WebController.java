package com.monitor.web;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * Created by demon on 2017/7/1 0001.
 */
@RestController
@RequestMapping("/")
public class WebController {

    private static final Logger logger = Logger.getLogger(WebController.class);

    @RequestMapping(value="/console")
    public String index(){
        return "console";
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value="/index")
    public String index(Model model){
        return "index";
    }

//    @RequestMapping(value="/register")
//    public String register(Model model){
//        return "register";
//    }

    @RequestMapping(value="/test")
    public String test(Model model){
        return "test";
    }
}
