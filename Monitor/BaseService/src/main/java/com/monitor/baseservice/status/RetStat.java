package com.monitor.baseservice.status;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误返回码。其他的模块错误码都需要继承该模块
 */
public class RetStat {

    public static final String OK = "OK";

    /**
     * 错误码：参数错误
     */
    public static final String ERR_BAD_PARAMS = "ERR_BAD_PARAMS";

    /**
     * 错误码：无访问权限
     */
    public static final String ERR_FORBIDDEN = "ERR_FORBIDDEN";

    /**
     * 错误码：非法JSON串
     */
    public static final String ERR_INVALID_JSON = "ERR_INVALID_JSON";

    /**
     * 错误码：资源不存在
     */
    public static final String ERR_NOT_FOUND = "ERR_NOT_FOUND";

    /**
     * 错误码：无法解析post数据
     */
    public static final String ERR_READ_POST_EXCEPTION = "ERR_READ_POST_EXCEPTION";

    /**
     * 错误码：没有返回码
     */
    public static final String ERR_STAT_NOT_SET = "ERR_STAT_NOT_SET";

    /**
     * 错误码：服务端异常
     */
    public static final String ERR_SERVER_EXCEPTION = "ERR_SERVER_EXCEPTION";

    /**
     * 错误码：事件中断
     */
    public static final String ERR_EVENT_INTERRUPT = "ERR_EVENT_INTERRUPT";

    /**
     * 错误码：用户没有登录
     */
    public static final String ERR_USER_NOT_LOGIN = "ERR_USER_NOT_LOGIN";

    /**
     * 错误码：邮件队列已满
     */
    public static final String ERR_MAILBOX_FULL = "ERR_MAILBOX_FULL";

    /**
     * 错误码：不支持该操作
     */
    public static final String ERR_OPERATION_NOT_SUPPORTED = "ERR_OPERATION_NOT_SUPPORTED";

    public static String getMsgByStat(String stat) {
        switch (stat) {
            case ERR_BAD_PARAMS : return "参数错误";
            case ERR_FORBIDDEN : return "无访问权限";
            case ERR_INVALID_JSON : return "非法JSON串";
            case ERR_NOT_FOUND : return "资源不存在";
            case ERR_READ_POST_EXCEPTION : return "无法解析post数据";
            case ERR_STAT_NOT_SET : return "没有返回码";
            case ERR_SERVER_EXCEPTION : return "服务端异常";
            case ERR_EVENT_INTERRUPT : return "事件中断";
            case ERR_USER_NOT_LOGIN : return "用户没有登录";
            case ERR_MAILBOX_FULL : return "邮件队列已满";
            case ERR_OPERATION_NOT_SUPPORTED : return "不支持该操作";
            default : return null;
        }
    }

}
