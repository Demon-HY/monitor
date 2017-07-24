//package com.monitor.user;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
///**
// * 用户模块 Controller 测试类
// *
// * Created by Demon on 2017/7/16 0016.
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {UserController.class})
//@WebAppConfiguration
//public class UserControllerTest {
//
//    private MockMvc mvc;
//
//    @Before
//    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
//    }
//
//    @Test
//    public void test() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/user/api/test").accept(MediaType.APPLICATION_JSON))
////                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//}
