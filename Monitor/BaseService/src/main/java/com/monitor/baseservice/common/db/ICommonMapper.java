package com.monitor.baseservice.common.db;

/**
 * 所有的 Mapper 继承它，里面定义了一些通用的方法
 *
 * Created by Demon on 2017/7/22 0022.
 */

import tk.mybatis.mapper.common.*;

/**
 * 被继承的Mapper，一般业务Mapper继承它
 *
 */
public interface ICommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
