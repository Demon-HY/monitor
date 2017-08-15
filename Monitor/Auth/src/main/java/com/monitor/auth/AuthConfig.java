package com.monitor.auth;

public class AuthConfig {
    
    /**
     * 默认token的寿命
     */
    public static long defaultTokenAge;
    
    public static final String LOGINID_EMAIL = "email";
    public static final String LOGINID_PHONE = "phone";
    public static final String LOGINID_NAME = "name";
    
    /**
     * MD5加密标识
     */
    public static final String ALG_MD5 = "MD5";
    
    /**
     * SSHA加密标识
     */
    public static final String ALG_SSHA = "SSHA";
}
