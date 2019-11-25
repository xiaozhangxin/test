package com.ak.pt.mvp.activity;

import android.util.Log;

/**
 * Created by efan on 2017/4/13.
 */

public class Logger {

    //设为false关闭日志
    private static final boolean LOG_ENABLE = false;

    public static void i(String tag, String msg){
        if (LOG_ENABLE){
            Log.d(tag, msg);
        }
    }
    public static void v(String tag, String msg){
        if (LOG_ENABLE){
            Log.d(tag, msg);
        }
    }
    public static void d(String tag, String msg){
        if (LOG_ENABLE){
            Log.d(tag, msg);
        }
    }
    public static void w(String tag, String msg){
        if (LOG_ENABLE){
            Log.d(tag, msg);
        }
    }
    public static void e(String tag, String msg){
        if (LOG_ENABLE){
            Log.d(tag, msg);
        }
    }

}
