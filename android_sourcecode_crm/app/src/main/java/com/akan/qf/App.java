package com.akan.qf;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.akan.qf.di.component.AppComponent;
import com.akan.qf.di.component.DaggerAppComponent;
import com.akan.qf.di.module.AppModule;
import com.akan.qf.impl.BoxingGlideLoader;
import com.akan.qf.impl.BoxingUcrop;
import com.bilibili.boxing.BoxingCrop;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.king.thread.nevercrash.NeverCrash;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;

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
        //Beta.installTinker();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        setStrictMode();

        //子线程初始化第三方组件 启动优化
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    initTBS();
                    initUMeng();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        //初始化极光推送
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);
        //boxing图片裁剪
        IBoxingMediaLoader loader = new BoxingGlideLoader();
        BoxingMediaLoader.getInstance().init(loader);
        BoxingCrop.getInstance().init(new BoxingUcrop());


        //异常处理
      NeverCrash.init(new NeverCrash.CrashHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {

            }
        });

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this, Constants.BASE_URL)).build();
        //初始化Leak内存泄露检测工具
        //LeakCanary.install(this);

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


    //初始化tbs
    private void initTBS() {
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，
                // 有可能特殊情况下x5内核加载失败，切换到系统内核。
            }

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                //Log.e("x5","加载内核是否成功:" + b);//打印出来可排错
            }
        });
    }

    //初始化友盟分享
    private void initUMeng() {
        UMConfigure.init(this, "5c04979af1f55673ca0000ba", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.isDebugLog();
        UMConfigure.setLogEnabled(false);
        PlatformConfig.setWeixin(Constants.WEIXIN_SHARE_ID, Constants.WEIXIN_SHARE_SECRECT);
        PlatformConfig.setQQZone(Constants.QQZONE_SHARE_ID, Constants.QQZONE_SHARE_SECRECT);
    }


}



