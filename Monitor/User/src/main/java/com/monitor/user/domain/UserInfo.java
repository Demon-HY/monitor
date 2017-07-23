package com.monitor.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.monitor.baseservice.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/16 0016.
 */
@Entity
@Table(name = "user")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户 Id */
    @Id
    @Column(name = "uid")
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

    /** 用户状态:1-正常,2-锁定，3-用户信息损坏，4-用户已删除 */
    @Column(name = "status", columnDefinition = "0")
    private Integer status;

    /** 用户类型:1-超级管理员,2-普通用户 */
    @Column(name = "type")
    private Integer type;

    /** 扩展属性集合 */
    @Column(name = "exattr", nullable = false)
    private String exattr;

    /** 创建时间 */
    @DateTimeFormat(pattern = DateUtils.GENERAL_PATTERN)
    @JsonFormat(pattern = DateUtils.GENERAL_PATTERN)
    @Column(name = "ctime")
    private Date ctime;

    /** 修改时间 */
    @DateTimeFormat(pattern = DateUtils.GENERAL_PATTERN)
    @JsonFormat(pattern = DateUtils.GENERAL_PATTERN)
    @Column(name = "mtime")
    private Date mtime;

    /** 最后一次登录时间 */
    @DateTimeFormat(pattern = DateUtils.GENERAL_PATTERN)
    @JsonFormat(pattern = DateUtils.GENERAL_PATTERN)
    @Column(name = "last_time", nullable = false)
    private Date last_time;

    public UserInfo() {
    }

    public UserInfo(String name) {
        this.name = name;
    }

    @GeneratedValue(generator = "uid")
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

    public Integer getStatus() {
        return status;
    }

    public Integer getType() {
        return type;
    }

    public String getExattr() {
        return exattr;
    }

    public Date getCtime() {
        return ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public Date getLast_time() {
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

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setExattr(String exattr) {
        this.exattr = exattr;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public void setLast_time(Date last_time) {
        this.last_time = last_time;
    }
}
