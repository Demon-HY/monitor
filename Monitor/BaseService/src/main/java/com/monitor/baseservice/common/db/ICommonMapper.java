package com.monitor.baseservice.common.db;

/**
 * Created by Administrator on 2017/7/22 0022.
 */

import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * 被继承的Mapper，一般业务Mapper继承它
 *
 */
public interface ICommonMapper<T>{
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错

    public List<T> findAll();
    public PageInfo<T> find(int pageNum, int pageSize);
    public T findById(int id);
    public int deleteById(int id);
    public int create(T t);
    public int update(T t);

}
