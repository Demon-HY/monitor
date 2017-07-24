package com.monitor.user.domain;

import com.monitor.baseservice.common.db.ICommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 映射SQL语句的接口，无逻辑实现
 *
 * Created by Demon on 2017/7/22 0022.
 */
@Repository
@Mapper
public interface UserMapper extends ICommonMapper<UserInfo> {

    public UserInfo findByName(String name);
}
