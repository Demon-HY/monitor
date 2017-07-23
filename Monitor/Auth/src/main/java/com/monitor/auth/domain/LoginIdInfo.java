package com.monitor.auth.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
@Entity
@Table(name = "login_id")
public class LoginIdInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    // 自增 ID
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    // 用户 ID
    @Column(name = "uid")
    private Long uid;

    // 登录类型：name/phone/email
    @Column(name = "type")
    private String type;

    // 用户登录名
    @Column(name = "value")
    private String value;

    public Long getId() {
        return id;
    }

    public Long getUid() {
        return uid;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
