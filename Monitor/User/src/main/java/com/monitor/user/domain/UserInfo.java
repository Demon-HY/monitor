package com.monitor.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.monitor.baseservice.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/16 0016.
 */
@Entity
@Table(name = "user")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户 Id */
    @Id
    @GeneratedValue
    private Long uid;

    /** 用户名,网页端注册的账号,同时会绑定邮箱 */
    @Column(name = "name", nullable = false)
    private String name;

    /** 手机号 */
    @Column(name = "phone", nullable = false)
    private String phone;

    /** 邮箱 */
    @Column(name = "email", nullable = false)
    private String email;

    /** 昵称 */
    @Column(name = "nick", nullable = false)
    private String nick;

    /** 密码 */
    @Column(name = "password")
    private String password;

    /** QQ 号 */
    @Column(name = "qq", nullable = false)
    private String qq;

    /** 用户状态:1-正常,2-删除  */
    @Column(name = "status", columnDefinition = "1")
    private Integer status;

    /** 用户类型:1-超级管理员,2-普通用户 */
    @Column(name = "type")
    private Integer type;

    /** 扩展属性集合 */
    @Column(name = "exattr", nullable = false)
    private Map<String, Object> exattr;

    /** 创建时间 */
    @DateTimeFormat(pattern = DateUtils.GENERAL_PATTERN)
    @JsonFormat(pattern = DateUtils.GENERAL_PATTERN)
    @Column(name = "ctime")
    private Timestamp ctime;

    /** 修改时间 */
    @DateTimeFormat(pattern = DateUtils.GENERAL_PATTERN)
    @JsonFormat(pattern = DateUtils.GENERAL_PATTERN)
    @Column(name = "mtime")
    private Timestamp mtime;

    /** 最后一次登录时间 */
    @DateTimeFormat(pattern = DateUtils.GENERAL_PATTERN)
    @JsonFormat(pattern = DateUtils.GENERAL_PATTERN)
    @Column(name = "last_time", nullable = false)
    private Timestamp last_time;

    public Long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public String getQq() {
        return qq;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getType() {
        return type;
    }

    public Map<String, Object> getExattr() {
        return exattr;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public Timestamp getMtime() {
        return mtime;
    }

    public Timestamp getLast_time() {
        return last_time;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setExattr(Map<String, Object> exattr) {
        this.exattr = exattr;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    public void setMtime(Timestamp mtime) {
        this.mtime = mtime;
    }

    public void setLast_time(Timestamp last_time) {
        this.last_time = last_time;
    }
}
