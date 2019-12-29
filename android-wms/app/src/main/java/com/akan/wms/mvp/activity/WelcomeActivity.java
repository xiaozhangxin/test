package com.akan.wms.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;

import com.akan.wms.Constants;
import com.akan.wms.MainActivity;
import com.akan.wms.R;
import com.akan.wms.bean.UserBean;
import com.akan.wms.util.SPUtils;
import com.akan.wms.util.SpSingleInstance;
import com.king.base.SplashActivity;
import com.king.base.util.LogUtils;

import static com.akan.wms.Constants.LOGIN_FRAGMENT;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/15
 */

public class WelcomeActivity extends SplashActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeStatusBarTransparent(this);
    }
    public static void makeStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {

            return;
        }
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    public Animation.AnimationListener getAnimationListener() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SharedPreferences sharedPre = context.getSharedPreferences("config", MODE_PRIVATE);
                boolean isFirst = sharedPre.getBoolean("is_first", true);
                if (isFirst) {
                    Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    UserBean userBean = SPUtils.getObj1(WelcomeActivity.this, Constants.USER_BEAN);
                    Log.e("ZZZ",userBean.getStaff_token());
                    Log.e("ZZZ",userBean.getOrg_id());
                    if (userBean == null) {
                        startLogin();

                    } else {
                        SPUtils.saveObJ1(context, Constants.USER_BEAN, userBean);
                        SpSingleInstance.getSpSingleInstance().setUserBean(userBean);
                        startActivityFinish(MainActivity.class);
                    }
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    protected void startLogin() {
        Intent intent = new Intent(WelcomeActivity.this, ContentActivity.class);
        intent.putExtra(Constants.KEY_FRAGMENT, LOGIN_FRAGMENT);
        startActivity(intent);
    }
}
