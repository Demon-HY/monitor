package com.monitor.auth.core;

import com.monitor.baseservice.exception.LogicalException;
import com.monitor.baseservice.utils.SSHAUtils;
import com.monitor.baseservice.utils.XCodeUtil;
import com.monitor.user.core.UserRetStat;
import com.monitor.user.domain.UserInfo;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.text.ParseException;

public class AuthUtils {
	
	private AuthUtils() {}
	
	/**
     * 检查登录账号
     * 
     * @param type
     *            账号类型
     * @param account
     *            账号
     * @return
     * @throws LogicalException
     */
    public static boolean checkAccount(String type, String account) throws LogicalException {
        if (!(AuthConfig.LOGINID_NAME.equals(type) || AuthConfig.LOGINID_EMAIL.equals(type) || AuthConfig.LOGINID_PHONE.equals(type))) {
            throw new LogicalException(UserRetStat.ERR_ILLEGAL_ACCOUNT_TYPE,
                    UserRetStat.getMsgByStat(UserRetStat.ERR_ILLEGAL_ACCOUNT_TYPE));
        }
        if (AuthConfig.LOGINID_EMAIL.equals(type) && !account.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$")) {
            throw new LogicalException(UserRetStat.ERR_ILLEGAL_EMAIL_ACCOUNT,
                    UserRetStat.getMsgByStat(UserRetStat.ERR_ILLEGAL_EMAIL_ACCOUNT));
        }
        if (AuthConfig.LOGINID_PHONE.equals(type) && !account.matches("^1[34578][0-9]{9}")) {
            throw new LogicalException(UserRetStat.ERR_ILLEGAL_PHONE_ACCOUNT,
                    UserRetStat.getMsgByStat(UserRetStat.ERR_ILLEGAL_PHONE_ACCOUNT));
        }

        return true;
    }
    
    /**
     * 验证用户密码
     * 
     * @param user 用户信息
     * @param password 密码
     * @return
     * @throws LogicalException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws ParseException
     */
    public static boolean checkPassword(UserInfo user, String password)
            throws LogicalException, NoSuchAlgorithmException,
            UnsupportedEncodingException, ParseException {
        if (null == user || password == null) {
            throw new IllegalArgumentException();
        }

        String psw = user.getPassword();

        if (psw == null || psw.length() == 0) {
            throw new LogicalException(UserRetStat.ERR_USER_INFO_BROKEN,
                    UserRetStat.getMsgByStat(UserRetStat.ERR_USER_INFO_BROKEN));
        }

        String format = "'{'{0}'}'{1}";
        MessageFormat mf = new MessageFormat(format);
        Object[] objs = mf.parse(psw);
        String algorithm = (String) objs[0];
        String shadow = (String) objs[1];

        if (AuthConfig.ALG_MD5.equals(algorithm)) {
            String tmp = XCodeUtil.base64ToBase16(shadow);
            password = XCodeUtil.getMD5(password);
            if (tmp.equals(password)) {
                return true;
            }
        } else if (AuthConfig.ALG_SSHA.equals(algorithm)) {
            return SSHAUtils.verifySaltedPassword(password.getBytes(), psw);
        }

        return false;
    }
}
