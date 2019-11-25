package com.ak.pt.mvp.activity;

import android.content.Intent;
import android.view.animation.Animation;

import com.ak.pt.Constants;
import com.ak.pt.MainActivity;
import com.ak.pt.R;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.fragment.LoginActivity;
import com.ak.pt.util.SPUtils;
import com.ak.pt.util.SpSingleInstance;
import com.king.base.SplashActivity;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/15
 */

public class WelcomeActivity extends SplashActivity {
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

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    protected void startLogin() {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
    }
}
