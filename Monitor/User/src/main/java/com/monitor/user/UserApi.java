package com.monitor.user;

import com.monitor.user.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户 API 接口，用来对接 Controller 和 Dao，业务逻辑都放在这里
 *
 * Created by Administrator on 2017/7/16 0016.
 */
@Service
public class UserApi {

    @Autowired
    IUserDao userDao;

    public void userRegister(UserInfo userInfo) {
        userDao.save(userInfo);
    }
}
