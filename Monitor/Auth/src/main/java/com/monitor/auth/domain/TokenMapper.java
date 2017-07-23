package com.monitor.auth.domain;

import com.monitor.baseservice.common.db.ICommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 映射SQL语句的接口，无逻辑实现
 *
 * Created by Administrator on 2017/7/22 0022.
 */
@Repository
@Mapper
public interface TokenMapper extends ICommonMapper<TokenInfo> {
}
