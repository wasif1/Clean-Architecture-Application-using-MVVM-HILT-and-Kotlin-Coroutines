package com.example.dubizzletest.cacheFramework;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by WASIF on 2021/11/10.
 * Logging Tool Class
 */
public class Md5Utils {
    public static String encode(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for(byte b : result){
                int number = (int)(b & 0xff) ;
                String str = Integer.toHexString(number);
                if(str.length()==1){
                    sb.append("0");
                }
                sb.append(str);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //can't reach
            return "";
        }
    }
}