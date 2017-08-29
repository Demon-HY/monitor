package com.monitor.auth.service;

import com.monitor.auth.domain.LoginIdInfo;
import com.monitor.auth.dao.LoginIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginIdService {

    private Logger logger = LoggerFactory.getLogger(LoginIdService.class);

    @Autowired
    private LoginIdMapper loginIdMapper;

    public LoginIdInfo selectOne(LoginIdInfo loginIdInfo) {
        try {
            return loginIdMapper.selectOne(loginIdInfo);
        } catch (Exception e) {
            logger.info("select login_id where " + loginIdInfo.getType() + " and "
                    + loginIdInfo.getName() + "not found.");
            return null;
        }
    }
}
