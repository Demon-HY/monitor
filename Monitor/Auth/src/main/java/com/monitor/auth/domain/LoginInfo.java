package com.monitor.auth.domain;

import com.monitor.user.domain.UserInfo;

/**
 * 登录信息
 *
 * Created by yhe.abcft on 2017/8/16.
 */
public class LoginInfo {

    public LoginInfo(){}

    /** token信息对象 */
    public TokenInfo tokenInfo;

    /** 用户信息对象 */
    public UserInfo userInfo;

    public LoginInfo(TokenInfo tokenInfo, UserInfo userInfo) {
        this.tokenInfo = tokenInfo;
        this.userInfo = userInfo;
    }
}
