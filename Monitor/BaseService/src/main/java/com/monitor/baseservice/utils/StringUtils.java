package com.monitor.baseservice.utils;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 *
 * Created by demon on 2017/7/1 0001.
 */
public class StringUtils {

    private StringUtils() { }

    /**
     * 字符串分隔符, 空格
     */
    public static final String SEPARATOR = String.valueOf((char) 29);

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

    /**
     * 判断为空
     * true : 为空 false: 不为空
     *
     * @param text
     * @return boolean
     */
    public static boolean isEmpty(String text) {
        return null == text || "".equals(text) || text.trim().isEmpty();
    }

    /**
     * 判断不为空
     *
     * @param text
     * @return boolean
     */
    public static boolean isNotEmpty(String text) {
        return !isEmpty(text);
    }

    /**
     * 根据separator分割字符串为字符串数组
     *
     * @param text
     * @param separator
     * @return String[]
     */
    public static String[] split(String text, String separator) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparator(text, separator);
    }

    /**
     * 替换字符串中regex匹配所有的内容为replace
     *
     * @param text
     * @param regex
     * @param replace
     * @return String
     */
    public static String replaceAll(String text, String regex, String replace) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, replace);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * 获得包含中文的字符串长度方法(一个中文为2个字符)
     *
     * @param text
     * @return int
     */
    public static int len(String text) {
        if (isEmpty(text))
            return 0;
        int len = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) > 127 || text.charAt(i) == 94) {
                len += 2;
            } else {
                len++;
            }
        }
        return len;
    }

    /**
     * 带有默认值的为空判断
     *
     * @param text
     * @param def
     * @return
     */
    public static String defIfEmpty(String text, String def) {
        return isEmpty(text) ? def : text;
    }

    /**
     * 替换{}中的内容
     *
     * @param sourceString
     * @param object
     * @return
     */
    public static String replaceBrackets(final String sourceString, Object[] object) {
        String temp = sourceString;
        for (int i = 0; i < object.length; i++) {
            String[] result = (String[]) object[i];
            Pattern pattern = Pattern.compile(result[0]);
            Matcher matcher = pattern.matcher(temp);
            temp = matcher.replaceAll(result[1]);
        }
        return temp;
    }

    /**
     * 去除null以及空格串
     */
    public static String noNull(Object s) {
        if (s == null)
            return "";
        else
            return s.toString().trim();
    }

    /**
     *
     * {将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名}
     * @param s 原文件名
     * @return  重新编码后的文件名
     */
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c <= 255) {
                sb.append(c);
            }
            else {
                byte[] b;

                try {
                    b = Character.toString(c).getBytes("UTF-8");
                }
                catch (Exception ex) {
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%").append(Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * 字符型数值处理
     *
     * @param val 要四舍五入的数(0.045)
     * @param precision(2) 保留位数 0.05
     * @return
     */
    public static String round(double val, int precision) {
        Double ret = null;
        try {
            double factor = Math.pow(10, precision);
            ret = Math.floor(val * factor + 0.5) / factor;
            return ret+"";
        } catch (Exception e) {
            e.printStackTrace();
            return val+"";
        }
    }

    /**
     * 隐藏手机号中间4位
     */
    public static String hidePhoneNumber(String phoneNumber){
        return phoneNumber.substring(0,3)+"****"+phoneNumber.substring(7);
    }

    /**
     * 读取字符串第i次出现特定符号的位置
     * @param string
     * @param i
     * @param character
     * @return
     */
    public static int getCharacterPosition(String string ,int i,String character){
        //这里是获取"/"符号的位置
        Matcher slashMatcher = Pattern.compile(character).matcher(string);
        int mIdx = 0;
        while(slashMatcher.find()) {
            mIdx++;
            //当"/"符号第三次出现的位置
            if(mIdx == i){
                break;
            }
        }
        return slashMatcher.start();
    }

    public static void main(String[] args) {
//        System.out.println(getStringTemplate("a={0},b={1}", "60", "hehe"));
        System.out.println("(" + SEPARATOR + ")");
    }
}
