package com.monitor.user;

import com.google.gson.Gson;
import com.monitor.baseservice.exception.LogicalException;
import com.monitor.baseservice.utils.SSHAUtils;
import com.monitor.user.domain.UserInfo;
import com.monitor.user.domain.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户逻辑处理类
 *
 * Created by Demon on 2017/7/22 0022.
 */
@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserMapper userMapper;

    /**
     * 创建用户
     * @param userInfo
     * @param type 用户类型：name/email/phone
     * @return
     */
    public int createUser(UserInfo userInfo, String type) throws LogicalException {
        int result;
        try {
            // 验证用户名是否有重复
            UserInfo _user = selectOne(userInfo.getName());
            if (_user != null) {
                throw new LogicalException(UserRetStat.ERR_ACCOUNT_EXIST,
                        UserRetStat.getMsgByStat(UserRetStat.ERR_ACCOUNT_EXIST));
            }

            // 验证密码有效性
            checkPasswordIsLegal(userInfo.getPassword());
            userInfo.setPassword(SSHAUtils.getSaltedPassword(userInfo.getPassword()));

            result = userMapper.insert(userInfo);
            if (result == 1) {
                UserInfo user = selectOne(userInfo.getName());

//                LoginIdInfo loginIdInfo = new LoginIdInfo();
//                loginIdInfo.setUid(user.getUid());
//                loginIdInfo.setType(type);
//                switch (type) {
//                    case "name":
//                        loginIdInfo.setName(user.getName());
//                        break;
//                    case "email":
//                        loginIdInfo.setName(user.getEmail());
//                        break;
//                    case "phone":
//                        loginIdInfo.setName(user.getPhone());
//                        break;
//                    default:
//                        loginIdInfo.setName(user.getName());
//                }
//                result = loginIdMapper.insert(loginIdInfo);
                userInfo.setPassword("");
                logger.info("Create user success.UserInfo=" + new Gson().toJson(userInfo));
            }
        } catch (Exception e) {
            logger.error("Create user failed.", e);
            throw new LogicalException(UserRetStat.ERR_CREATE_USER_FAILED,
                    UserRetStat.getMsgByStat(UserRetStat.ERR_CREATE_USER_FAILED, userInfo.getName()));
        }

        return result;
    }

    /**
     * 验证密码有效性
     * @param password
     * @return
     * @throws LogicalException
     * @throws Exception
     */
    private static boolean checkPasswordIsLegal(String password) throws LogicalException  {
        if(password == null || password.length() == 0 || password.matches("[u4e00-u9fa5]"))
            throw new LogicalException(UserRetStat.ERR_ILLEGAL_PASSWORD,
                    UserRetStat.getMsgByStat(UserRetStat.ERR_ILLEGAL_PASSWORD));
        return true;
    }

    /**
     * 更新用户
     * @param userInfo
     */
    public void updateUser(UserInfo userInfo) throws LogicalException {
        try {
            userMapper.updateByPrimaryKey(userInfo);
            userInfo.setPassword("");
            logger.info("Update user success.UserInfo=" + new Gson().toJson(userInfo));
        } catch (Exception e) {
            logger.error("Update user failed.", e);
            throw new LogicalException(UserRetStat.ERR_UPDATE_USER_FAILED,
                    UserRetStat.getMsgByStat(UserRetStat.ERR_UPDATE_USER_FAILED, userInfo.getName()));
        }
    }

    public UserInfo selectOne(String name) {
        return userMapper.selectOne(new UserInfo(name));
    }
}
