package com.monitor.user;

import com.monitor.user.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/22 0022.
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
}
