package com.monitor.baseservice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间相关操作类
 *
 * Created by Administrator on 2017/7/1 0001.
 */
public class DateUtils {

    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年

    public final static String DATE_SEQUENCE = "yyyyMMddHHmmssSSS";

    /**
     * 获取时间序列，格式：
     * @return yyyyMMddHHmmssSSS
     */
    public static String getDateSequence() {
        return new SimpleDateFormat(DATE_SEQUENCE).format(new Date());
    }

    /**
     * 将 Date 类型时间转成 unix 时间戳
     *
     * @param date java.util.Date
     * @return unix 时间戳
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 将 unix 时间戳格式化
     *
     * @param time
     *            unix 时间戳
     * @return 时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static String longTimeToString(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(time);
    }

    /**
     * 将 unix 时间戳格式化成指定格式
     *
     * @param time
     *            unix 时间戳
     * @param format
     *            常用类型如下 <blockquote> <br>
     *            yyyy-MM-dd HH:mm:ss <br>
     *            yyyy-MM-dd <br>
     *            HH:mm:ss <br>
     *            HH:mm:ss yyyy-MM-dd <br>
     *            MM-dd-yyyy HH:mm:ss </blockquote>
     * @return 格式化时间
     */
    public static String longTimeToString(long time, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        return formatter.format(time);
    }

    /**
     * 将 Date 时间格式化成指定格式
     *
     * @param date
     *            java.util.Date
     * @param format
     *            常用类型如下 <blockquote> <br>
     *            yyyy-MM-dd HH:mm:ss <br>
     *            yyyy-MM-dd <br>
     *            HH:mm:ss <br>
     *            HH:mm:ss yyyy-MM-dd <br>
     *            MM-dd-yyyy HH:mm:ss </blockquote>
     */
    public static String dateToString(java.util.Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        return formatter.format(date);
    }

    /**
     * 获取当前系统时间
     * @return java.util.Date
     */
    public static Date getCurrentTimeDate() {
        return new Date();
    }

    /**
     * 获取当前系统时间
     *
     * @return unix 时间戳
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取时间距离当前时间的描述
     * @param date java.util.Date
     * @return
     */
    public static String getTimeFormatText(Long date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date;
        long r;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 将时间戳转换成当天0点
     * @param timestamp unix 时间戳
     * @return timestamp
     */
    public static long getDayBegin(long timestamp) {
        String format = "yyyy-MM-DD";
        String toDayString = new SimpleDateFormat(format).format(new Date(timestamp));
        Date toDay;
        try {
            toDay = org.apache.commons.lang3.time.DateUtils.parseDate(toDayString, format);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toDay.getTime();
    }

    /**
     * 获取一个月之前的时间戳
     * @return unix 时间戳
     */
    public static long getLastMonthTime() {
        return getDayBegin(getCurrentTimeMillis()) - (86400000L *30);
    }


    public static void main(String[] args) {

        System.out.println(longTimeToString(14999990610451L));

        System.out.println(longTimeToString(1463705675664L, "HH:mm:ss yyyy-MM-dd"));
        System.currentTimeMillis();
        System.out.println(getCurrentTimeDate());
    }
}
