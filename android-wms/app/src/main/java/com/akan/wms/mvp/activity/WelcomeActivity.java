package com.akan.wms.mvp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
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

import static com.akan.wms.Constants.LOGIN_FRAGMENT;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/15
 */

public class WelcomeActivity extends SplashActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
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
