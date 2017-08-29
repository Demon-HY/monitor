package com.monitor.auth.service;

import com.monitor.auth.core.AuthUtils;
import com.monitor.auth.dao.LoginIdMapper;
import com.monitor.auth.domain.LoginIdInfo;
import com.monitor.baseservice.exception.LogicalException;
import com.monitor.user.core.UserRetStat;
import com.monitor.user.service.UserService;
import com.monitor.user.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

/**
 * 登录认证逻辑处理层
 *
 * Created by yhe.abcft on 2017/8/16.
 */
@Service
public class AuthService {

    private Logger logger = LoggerFactory.getLogger(LoginIdService.class);

    @Autowired
    UserService userService;

    @Autowired
    LoginIdMapper loginIdMapper;

    public UserInfo login(String account, String password, String type) throws LogicalException, ParseException, NoSuchAlgorithmException, UnsupportedEncodingException {
        LoginIdInfo loginIdInfo = loginIdMapper.selectOne(new LoginIdInfo(account, type));
        if (loginIdInfo == null) {
            throw new LogicalException(UserRetStat.ERR_NO_SUCH_ACCOUNT,
                    UserRetStat.getMsgByStat(UserRetStat.ERR_NO_SUCH_ACCOUNT));
        }

        UserInfo userInfo = userService.selectOne(account);
        if (userInfo == null) {
            throw new LogicalException(UserRetStat.ERR_NO_SUCH_ACCOUNT,
                    UserRetStat.getMsgByStat(UserRetStat.ERR_NO_SUCH_ACCOUNT));
        }
        // 校验用户状态
        userService.checkUserStatus(userInfo);

        // 校验密码
        if (!AuthUtils.checkPassword(userInfo, password)) {
            throw new LogicalException(UserRetStat.ERR_INVALID_PASSWORD,
                    UserRetStat.getMsgByStat(UserRetStat.ERR_INVALID_PASSWORD));
        }

        return userInfo;
    }
}
