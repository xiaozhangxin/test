package com.akan.wms;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.akan.wms.di.component.AppComponent;
import com.akan.wms.di.component.DaggerAppComponent;
import com.akan.wms.di.module.AppModule;
import com.akan.wms.impl.BoxingGlideLoader;
import com.akan.wms.impl.BoxingUcrop;
import com.bilibili.boxing.BoxingCrop;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/13
 */

public class App extends Application {


    private AppComponent mAppComponent;

    private static Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        setStrictMode();

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this, Constants.BASE_URL)).build();
        //boxing图片裁剪
        IBoxingMediaLoader loader = new BoxingGlideLoader();
        BoxingMediaLoader.getInstance().init(loader);
        BoxingCrop.getInstance().init(new BoxingUcrop());

        CrashReport.initCrashReport(getApplicationContext(), "7f0c9d8b5f", false);
        //Leak内存泄露检测工具
        //LeakCanary.install(this);

        //异常处理
/*        NeverCrash.init(new NeverCrash.CrashHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {

            }
        });*/

    }


    public AppComponent getAppCommponent() {
        return mAppComponent;
    }


    @TargetApi(9)
    protected void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }

    public static Context getGlobalContext() {
        return mContext;
    }

}



