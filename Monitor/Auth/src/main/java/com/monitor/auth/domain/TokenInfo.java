package com.monitor.auth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.monitor.baseservice.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * 用户登录认证信息实体类
 *
 * Created by Demon on 2017/7/15 0015.
 */
@Entity
@Table(name = "token")
public class TokenInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户登录凭据 */
    @Id
    @Column(name = "token")
    private String token;

    /** 用户ID */
    @Column(name = "uid")
    private Long uid;

    /** token的有效期限 */
    @DateTimeFormat(pattern = DateUtils.GENERAL_PATTERN)
    @JsonFormat(pattern = DateUtils.GENERAL_PATTERN)
    @Column(name = "expires")
    private Date expires;

    /** 创建时间 */
    @DateTimeFormat(pattern = DateUtils.GENERAL_PATTERN)
    @JsonFormat(pattern = DateUtils.GENERAL_PATTERN)
    @Column(name = "ctime")
    private Date ctime;

    /** 客户端IP */
    @Column(name = "ip", nullable = false)
    private String ip;

    /** 客户端设备 */
    @Column(name = "device", nullable = false)
    private String device;

    public TokenInfo() {
    }

    public TokenInfo(String token) {
        this.token = token;
    }

    @GeneratedValue(generator = "token")
    public String getToken() {
        return token;
    }

    public Long getUid() {
        return uid;
    }

    public Date getExpires() {
        return expires;
    }

    public Date getCtime() {
        return ctime;
    }

    public String getIp() {
        return ip;
    }

    public String getDevice() {
        return device;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
