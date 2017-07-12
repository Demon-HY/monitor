package com.monitor.baseservice.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie
 */
public class CookieUtils {

    public static Cookie getCookie(HttpServletRequest request, String cookieName){
         
         Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
         if(null==cookies) {
             return null;
         }else{  
             for(Cookie cookie : cookies){
                 if (cookie.getName().equals(cookieName)) {
                     return cookie;
                 } 
             }  
         }  
           
         return null;
     }
}
