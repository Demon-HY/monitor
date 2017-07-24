package com.monitor.baseservice.utils;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 有效验证
 *
 * Created by Demon on 2017/7/22 0022.
 */
public class ValidateUtils {

    private ValidateUtils() {
    }

    /**
     *
     * 判断一个数据对象是否为空
     *
     * @param data
     * @return boolean
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object data) {
        if (null == data)
            return true;
        if (data instanceof String) {
            return String.valueOf(data).trim().isEmpty();
        } else if (data instanceof Map) {
            return ((Map) data).isEmpty();
        } else if (data instanceof Collection) {
            Collection coll = (Collection) data;
            return coll.isEmpty();
        } else if (data.getClass().isArray()) {
            return Array.getLength(data) == 0;
        } else if (data instanceof Long) {
            Long l = (Long) data;
            return l == 0;
        }
        return false;
    }

    /**
     * 判断一个数据对象不会空
     *
     * @param data
     * @return boolean
     */
    public static boolean isNotEmpty(Object data) {
        return !isEmpty(data);
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return boolean
     */
    public static boolean collEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * 判断 Map 是否非空
     *
     * @param map
     * @return boolean
     */
    public static boolean mapEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 是否是int类型的数字
     *
     * @param text
     * @return boolean
     */
    public static boolean isInt(String text) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(text);
        return isNum.matches();
    }

    /**
     * 检验时间格式
     * @param str
     * @param format "yyyy/MM/dd HH:mm:ss" "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static boolean isValidDate(String str, String format) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(str);
        } catch (ParseException e) {
            convertSuccess=false;
        }
        return convertSuccess;
    }

    /**
     * 检查 email输入是否正确 正确的书写格 式为 username@domain
     *
     * @param text
     * @return
     */
    public static boolean checkEmail(String text, int length) {
        return !isEmpty(text) && text.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
                && text.length() <= length;
    }

    /**
     * 检查电话输入 是否正确 正确格 式 012-87654321、0123-87654321、0123－7654321
     *
     * @param text
     * @return
     */
    public static boolean checkTelephone(String text) {
        return !isEmpty(text) && text.matches(
        "(0\\d{2,3}-\\d{7,8})|" +
                "(0\\d{9,11})|" +
                "(\\d{7})|" +
                "(\\d{8})|" +
                "(4\\d{2}\\d{7})|" +
                "(4\\d{2}-\\d{7})|" +
                "(4\\d{2}-\\d{3}-\\d{4})|" +
                "(4\\d{2}-\\d{4}-\\d{3})");
    }

    /**
     * 检查手机输入 是否正确
     *
     * @param text
     * @return
     */
    public static boolean checkMobilephone(String text) {
        return !isEmpty(text) && text.matches("((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}");
    }

    /**
     * 检查中文名输 入是否正确
     *
     * @param text
     * @return
     */
    public static boolean checkChineseName(String text, int length) {
        return !isEmpty(text) && text.matches("^[\u4e00-\u9fa5]+$") && text.length() <= length;
    }

    /**
     * 检查字符串中是否有空格，包括中间空格或者首尾空格
     *
     * @param text
     * @return
     */
    public static boolean checkBlank(String text) {
        return !isEmpty(text) && text.matches("^\\s*|\\s*$");
    }

    /**
     * 检查字符串是 否含有HTML标签
     *
     * @param text
     * @return
     */

    public static boolean checkHtmlTag(String text) {
        return !isEmpty(text) && text.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />");
    }

    /**
     * 检查URL是 否合法
     *
     * @param text
     * @return
     */
    public static boolean checkURL(String text) {
        return !isEmpty(text) && text.matches("[a-zA-z]+://[^\\s]*");
    }

    /**
     * 检查IP是否 合法
     *
     * @param text
     * @return
     */
    public static boolean checkIP(String text) {
        return !isEmpty(text) && text.matches("\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}");
    }

    /**
     * 检查QQ是否 合法，必须是数字，且首位不能为0，最长15位
     *
     * @param text
     * @return
     */

    public static boolean checkQQ(String text) {
        return !isEmpty(text) && text.matches("[1-9][0-9]{4,13}");
    }

    /**
     * 检查邮编是否 合法
     *
     * @param text
     * @return
     */
    public static boolean checkPostCode(String text) {
        return !isEmpty(text) && text.matches("[1-9]\\d{5}(?!\\d)");
    }

    /**
     * 检查身份证是 否合法,15位或18位(或者最后一位为X)
     *
     * @param text
     * @return
     */
    public static boolean checkIDCard(String text) {
        return !isEmpty(text) && text.matches("\\d{15}|\\d{18}|(\\d{17}[x|X])");
    }

    /**
     * 检查输入是否 超出规定长度
     *
     * @param length
     * @param text
     * @return
     */
    public static boolean checkLength(String text, int length) {
        return ((isEmpty(text)) ? 0 : text.length()) <= length;
    }

    /**
     * 判断是否为数字
     * @param text
     * @return
     */
    public static boolean isNumber(String text) {
        return !isEmpty(text) && text.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }
}
