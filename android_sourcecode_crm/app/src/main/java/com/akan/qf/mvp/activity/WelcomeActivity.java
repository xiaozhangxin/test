package com.akan.qf.mvp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.akan.qf.Constants;
import com.akan.qf.MainActivity;
import com.akan.qf.R;
import com.akan.qf.bean.UserBean;
import com.akan.qf.util.SPUtils;
import com.akan.qf.util.SpSingleInstance;
import com.king.base.SplashActivity;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

import static com.akan.qf.Constants.LOGIN_FRAGMENT;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/15
 */

public class WelcomeActivity extends SplashActivity {


    public  class mayhnew extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


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
