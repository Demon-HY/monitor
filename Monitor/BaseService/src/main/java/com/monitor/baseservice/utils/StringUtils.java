package com.monitor.baseservice.utils;

/**
 * 字符串处理工具类
 *
 * Created by demon on 2017/7/1 0001.
 */
public class StringUtils {

    /**
     * get string template, template index starts from zero
     * @param template template format :"a={0},b={1}"
     * @param keys params
     * @return stitching a full string
     */
    public static String getStringTemplate(String template, String... keys) {
        int count = 0;
        for (String key : keys) {
            template = template.replace("{" + count++ + "}", key);
        }
        return template;
    }

    public static void main(String[] args) {
        System.out.println(getStringTemplate("a={0},b={1}", "60", "hehe"));
    }
}
