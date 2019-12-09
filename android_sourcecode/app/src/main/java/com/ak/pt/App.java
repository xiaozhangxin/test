package com.ak.pt;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.ak.pt.di.component.AppComponent;
import com.ak.pt.di.component.DaggerAppComponent;
import com.ak.pt.di.module.AppModule;
import com.ak.pt.impl.BoxingGlideLoader;
import com.ak.pt.impl.BoxingUcrop;
import com.ak.pt.mvp.fragment.LoginActivity;
import com.bilibili.boxing.BoxingCrop;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.clj.fastble.BleManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/13
 */

public class App extends Application {

    private static final String BUGLY_ID = "f9c091d483";
    private AppComponent mAppComponent;

    private static App mApp;
    private static Context mContext;
    private List<Activity> activities = new ArrayList<>();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
        //Beta.installTinker();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        mContext = getApplicationContext();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activities.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                activities.remove(activity);
            }
        });
        closeAndroidPDialog();

        //子线程初始化第三方组件
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                initTbs();
                initJPush();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //bugly初始化
        CrashReport.initCrashReport(getApplicationContext(), BUGLY_ID, true);

        IBoxingMediaLoader loader = new BoxingGlideLoader();
        BoxingMediaLoader.getInstance().init(loader);
        BoxingCrop.getInstance().init(new BoxingUcrop());

        setStrictMode();

        //初始化蓝牙
        BleManager.getInstance().init(this);
        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(1, 5000)
                .setConnectOverTime(10000)
                .setOperateTimeout(5000);

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this, Constants.BASE_URL)).build();

        ZXingLibrary.initDisplayOpinion(this);

        //内存泄露
        //  LeakCanary.install(this);
        //异常处理
/*        NeverCrash.init(new NeverCrash.CrashHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
            }
        });*/
    }


    //初始化极光推送
    private void initJPush() {
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);
    }


    //初始化tbs
    private void initTbs() {
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {

            @Override
            public void onCoreInitFinished() {
                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。
            }

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                //Logger.e("x5", "加载内核是否成功:" + b);//打印出来可排错
            }
        });
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


    private void closeAndroidPDialog() {
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backLogin(){
        for (Activity activity : activities) {
            if (activity != null && !activity.isFinishing() && !LoginActivity.class.equals(activity.getClass())){
                activity.finish();
            }
        }
    }

    public static App getInstance(){
        return mApp;
    }

}



