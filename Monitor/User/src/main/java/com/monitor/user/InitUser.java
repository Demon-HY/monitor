package com.monitor.user;

import com.alibaba.fastjson.JSONObject;
import com.monitor.auth.ILoginIdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
public class InitUser {

    @Autowired
    UserConfig userConfig;

    @Autowired
    ILoginIdDao loginIdDao;

    public void initUser() {
        String _userNames = userConfig.env.getProperty("monitor.user.init.userlist");
        String[] names = _userNames.split(",");
        for (String name : names) {
            if (name.trim().length() == 0) {
                continue;
            }
            String password = userConfig.env.getProperty(String.format("monitor.user.init.%s.password", name));
            String attrStr  = userConfig.env.getProperty(String.format("monitor.user.init.%s.attrs", name));

            @SuppressWarnings("unchecked")
            Map<String, Object> attrs = (Map<String, Object>) JSONObject.parse(attrStr);
            loginIdDao.findByTypeAndValue("name", name);
//            Long uid = beans.getAuthApi().getAuthModel().checkLoginId("name", name);
//            UserInfo user = new UserInfo();
//            user.name = name;
//            user.password = password;
//            user.nick = (String) attrs.get("nick");
//            user.email = (String) attrs.get("email");
//            user.status = (int) attrs.get("status");
//            user.type = (int) attrs.get("type");
//            if (null == uid) {
//                user = beans.getUserApi().userRegister(env, user);
//            } else {
//                Object status = attrs.get("status");
//                if (status != null) {
//                    beans.getUserApi().setUserAttr(env, uid, "status", status);
//                }
//            }
        }
    }
}
