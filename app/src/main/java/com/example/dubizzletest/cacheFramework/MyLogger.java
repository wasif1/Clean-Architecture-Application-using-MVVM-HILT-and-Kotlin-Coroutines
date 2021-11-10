package com.example.dubizzletest.cacheFramework;

import android.util.Log;

/**
 * Created by WASIF on 2021/11/10.
 * Logging Tool Class
 */
public final class MyLogger {

    //switch
    //true test false on line
    private final static boolean flag = true;

    public static void v(String tag,String msg){
        if(flag){
            Log.v(tag, msg);
        }
    }
    public static void d(String tag,String msg){
        if(flag){
            Log.d(tag, msg);
        }
    }
    public static void i(String tag,String msg){
        if(flag){
            Log.i(tag, msg);
        }
    }
    public static void w(String tag,String msg){
        if(flag){
            Log.w(tag, msg);
        }
    }
    public static void e(String tag,String msg){
        if(flag){
            Log.e(tag, msg);
        }
    }

}