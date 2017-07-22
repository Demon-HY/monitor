package com.monitor.user.domain;

import com.monitor.baseservice.common.db.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 映射SQL语句的接口，无逻辑实现
 *
 * Created by Administrator on 2017/7/22 0022.
 */
@Repository
@Mapper
public interface UserMapper extends MyMapper<UserInfo> {
}
