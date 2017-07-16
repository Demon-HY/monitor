package com.monitor.auth.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
@Entity
@Table(name = "token")
public class TokenInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户登录凭据 */
    @Id
    @GeneratedValue
    private String token;

    /** 用户ID */
    @Column(name = "uid")
    private long uid;

    /** token的有效期限 */
    @Column(name = "expires")
    private Timestamp expires;

    /** 创建时间 */
    @Column(name = "ctime")
    private Timestamp ctime;

    /** 客户端IP */
    @Column(name = "ip", nullable = false)
    private String ip;

    /** 客户端设备 */
    @Column(name = "device", nullable = false)
    private String device;

    public String getToken() {
        return token;
    }

    public long getUid() {
        return uid;
    }

    public Timestamp getExpires() {
        return expires;
    }

    public Timestamp getCtime() {
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

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setExpires(Timestamp expires) {
        this.expires = expires;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
