package com.monitor.auth;

import com.monitor.auth.domain.LoginIdInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 登录 ID 表数据库接口
 *
 * Created by demon on 2017/7/1 0001.
 */
@Repository
public interface ILoginIdDao extends JpaRepository<LoginIdInfo, Long> {

//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    public List<Map<String, Object>> checkLoginId(String type, String value) {
//        if (null == type || value == null) {
//            throw new IllegalArgumentException();
//        }
//
//        String sql = " select `uid` from `" + TABLE_LOGIN_ID + "` where `type`=? and `value`=? ";
//
//        List<Object> params = new ArrayList<>();
//        params.add(type);
//        params.add(value);
//
//        return jdbcTemplate.queryForList(sql, params.toArray());
//    }

    /**
     * 通过类型和用户名，查询用户Id
     * @param type
     * @param value
     * @return
     */
    @Query("select uid from login_id where type=:type and value=:value ")
    long findByTypeAndValue(@Param("type") String type, @Param("value") String value);
}
