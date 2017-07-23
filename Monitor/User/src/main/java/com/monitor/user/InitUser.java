package com.monitor.user;

import com.alibaba.fastjson.JSONObject;
import com.monitor.auth.LoginIdService;
import com.monitor.auth.domain.LoginIdInfo;
import com.monitor.baseservice.exception.LogicalException;
import com.monitor.user.domain.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户初始化
 *
 * Created by Administrator on 2017/7/15 0015.
 */
@Service
public class InitUser {

    public void initUser(UserConfig userConfig, UserService userService, LoginIdService loginIdService) throws LogicalException {
        String _userNames = userConfig.env.getProperty("monitor.user.init.name");
        String[] names = _userNames.split(",");
        for (String name : names) {
            try {
                if (name.trim().length() == 0) {
                    continue;
                }
                String password = userConfig.env.getProperty(String.format("monitor.user.init.%s.password", name));
                String attrStr  = userConfig.env.getProperty(String.format("monitor.user.init.%s.attrs", name));

                JSONObject attrs = JSONObject.parseObject(attrStr);
                LoginIdInfo loginIdInfo = loginIdService.selectOne(new LoginIdInfo("name", name));

                UserInfo userInfo = new UserInfo();
                userInfo.setName(name);
                userInfo.setPassword(password);
                userInfo.setNick(attrs.getString("nick"));
                userInfo.setEmail(attrs.getString("email"));
                userInfo.setStatus(attrs.getInteger("status"));
                userInfo.setType(attrs.getInteger("type"));
                userInfo.setMtime(new Date());

                if (loginIdInfo == null) {
                    userInfo.setCtime(new Date());
                    userService.createUser(userInfo, "name");
                    System.out.println("创建用户" + userInfo.getName() + "成功");
                } else {
                    userService.updateUser(userInfo);
                    System.out.println("更新用户" + userInfo.getName() + "成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
