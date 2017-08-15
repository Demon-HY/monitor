package com.monitor.user;

import com.monitor.baseservice.status.RetStat;

import java.util.Date;

/**
 * 用户模块错误码
 *
 * Created by Demon on 2017/7/23 0023.
 */
public class UserRetStat extends RetStat {

    /** 该账号已被使用 */
    public static final String ERR_ACCOUNT_EXIST = "ERR_ACCOUNT_EXIST";
    /** 手机号已经被绑定 */
    public static final String ERR_PHONE_ALREADY_BOUND = "ERR_PHONE_ALREADY_BOUND";
    /** 清除用户数据失败 */
    public static final String ERR_DELETE_ACCOUNT_FAILED = "ERR_DELETE_ACCOUNT_FAILED";
    /** 注册登录信息失败 */
    public static final String ERR_ADD_LOGIN_ID_FAILED = "ERR_ADD_LOGIN_ID_FAILED";
    /** 账号不存在 */
    public static final String ERR_NO_SUCH_ACCOUNT = "ERR_NO_SUCH_ACCOUNT";
    /** 用户不存在 */
    public static final String ERR_USER_NOT_FOUND = "ERR_USER_NOT_FOUND";
    /** 用户信息损坏 */
    public static final String ERR_USER_INFO_BROKEN = "ERR_USER_INFO_BROKEN";
    /** 密码错误 */
    public static final String ERR_INVALID_PASSWORD = "ERR_INVALID_PASSWORD";
    /** 非法账号类型 */
    public static final String ERR_ILLEGAL_ACCOUNT_TYPE = "ERR_ILLEGAL_ACCOUNT_TYPE";
    /** 非法邮箱 */
    public static final String ERR_ILLEGAL_EMAIL_ACCOUNT = "ERR_ILLEGAL_EMAIL_ACCOUNT";
    /** 非法手机号 */
    public static final String ERR_ILLEGAL_PHONE_ACCOUNT = "ERR_ILLEGAL_PHONE_ACCOUNT";
    /** 错误码：非法密码 */
    public static final String ERR_ILLEGAL_PASSWORD = "ERR_ILLEGAL_PASSWORD";
    /** 用户还未登录 */
    public static final String ERR_TOKEN_NOT_FOUND = "ERR_TOKEN_NOT_FOUND";
    /** 用户登录超时 */
    public static final String ERR_TOKEN_EXPIRED = "ERR_TOKEN_EXPIRED";
//    /** 验证码错误 */
//    public static final String ERR_CODE = "ERR_CODE";
//    /** 请不要重复发送验证码 */
//    public static final String ERR_SEND_CODE_FREQUENTLY = "ERR_SEND_CODE_FREQUENTLY";
//    /** 验证码过期 */
//    public static final String ERR_VALIDATE_CODE_EXPIRED = "ERR_VALIDATE_CODE_EXPIRED";
//    /** 验证码错误 */
//    public static final String ERR_INVALID_VALIDATE_CODE = "ERR_INVALID_VALIDATE_CODE";
    /** 登录的用户与新建token用户不为一个用户 */
    public static final String ERR_TOKEN_UID_MISMATCHING = "ERR_TOKEN_UID_MISMATCHING";
    /** 错误码：用户已被锁定 */
    public static final String ERR_USER_LOCKED = "ERR_USER_LOCKED";
    /** 错误码：创建用户失败 */
    public static final String ERR_CREATE_USER_FAILED = "ERR_CREATE_USER_FAILED";
    /** 错误码：创建用户失败 */
    public static final String ERR_UPDATE_USER_FAILED = "ERR_UPDATE_USER_FAILED";

    public static String getMsgByStat(String stat, Object... params) {
//        // 先去查找基础错误码
//        String statMessage = UserRetStat.getMsgByStat(stat);
//        if (statMessage != null) {
//            return statMessage;
//        }
        switch (stat) {
            case ERR_TOKEN_NOT_FOUND : return null;
            case ERR_TOKEN_EXPIRED : return null;
            case ERR_NO_SUCH_ACCOUNT : return String.format("账号 '%s' 不存在", params);
            case ERR_USER_NOT_FOUND : return String.format("用户 '%s' 不存在", params);
            case ERR_USER_INFO_BROKEN : return String.format("%s 用户信息损坏", params);
            case ERR_INVALID_PASSWORD : return String.format("密码错误", params);
            case ERR_ILLEGAL_ACCOUNT_TYPE : return String.format(" 非法账号类型", params);
            case ERR_ILLEGAL_EMAIL_ACCOUNT : return String.format("非法邮箱 '%s'", params);
            case ERR_ACCOUNT_EXIST : return String.format("账号 '%s' 已存在", params);
            case ERR_ADD_LOGIN_ID_FAILED : return String.format("'%s' 注册登录信息失败", params);
            case ERR_DELETE_ACCOUNT_FAILED : return String.format("删除用户 %s 失败", params);
            case ERR_TOKEN_UID_MISMATCHING : return String.format("登录的用户 %s 与新建token用户 %s 不为同一个用户", params);
            case ERR_ILLEGAL_PHONE_ACCOUNT: return String.format("非法手机号 %s", params);
            case ERR_USER_LOCKED : return String.format("用户 %s 已被锁定", params);
            case ERR_CREATE_USER_FAILED : return String.format("创建用户 %s 失败", params);
            case ERR_UPDATE_USER_FAILED : return String.format("更新用户 %s 失败", params);
            case ERR_PHONE_ALREADY_BOUND : return String.format("手机号 %s 已被绑定", params);
            case ERR_ILLEGAL_PASSWORD : return String.format("无效密码: %s", params);
            default : return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Date().getTime());
    }
}
