package com.monitor.user;

import com.monitor.user.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户表(user) 数据库接口
 *
 * Created by Administrator on 2017/7/16 0016.
 */
@Repository
public interface IUserDao extends JpaRepository<UserInfo, Long> {

    /**
     * 通过 uid 查询用户信息
     * @param uid
     * @return
     */
    UserInfo findUserInfoByUid(Long uid);

    UserInfo findUserInfoByName(String name);
}
