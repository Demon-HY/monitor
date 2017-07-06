package com.monitor.baseservice.utils;

import java.security.MessageDigest;

/**
 * MD5
 *
 * Created by Administrator on 2017/7/1 0001.
 */
public class MD5Util {

    /**
     * get data md5
     * @param dataStr
     * @return
     */
    public static String getMD5(String dataStr) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (byte value : s) {
                result += Integer.toHexString((0x000000FF & value) | 0xFFFFFF00)
                        .substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void main(String[] args) {
        System.out.println(getMD5("P@ssw0rd"));
    }
}
