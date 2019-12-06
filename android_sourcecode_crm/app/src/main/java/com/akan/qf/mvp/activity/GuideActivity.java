package com.akan.qf.mvp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.akan.qf.Constants;
import com.akan.qf.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by admin on 2018/8/27.
 */

public class GuideActivity extends AppCompatActivity {
    private int lastPosition;
    private Button ok;
    public static final int LOGIN_FRAGMENT = 0X04;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //api>21,全透明状态栏和导航栏;api>19,半透明状态栏和导航栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            // window.setStatusBarColor(Color.TRANSPARENT);
            //window.setStatusBarColor(getResources().getColor(R.color.translate));

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (true) {
                //半透明导航栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
            //半透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_guid);
        ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPr = getApplicationContext().getSharedPreferences("config", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPr.edit();
                editor.putBoolean("is_first", false);
                editor.commit();
                startLogin();
                finish();
            }
        });
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        View view1 = getLayoutInflater().inflate(R.layout.guide_one, null);
        View view2 = getLayoutInflater().inflate(R.layout.guide_two, null);
        View view3 = getLayoutInflater().inflate(R.layout.guide_three, null);
        final List<View> list = new ArrayList<>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        PagerAdapter myPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {

                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(list.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(list.get(position));
                return list.get(position);
            }
        };
        viewPager.setAdapter(myPagerAdapter);

        final LinearLayout ll = (LinearLayout) findViewById(R.id.linearlayout);
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(R.drawable.selector_guide_img);
            if (i == 0) {
                imageView.setEnabled(true);
            } else {
                imageView.setEnabled(false);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, -2);
            layoutParams.leftMargin = 70; // 左边，15个象素的边距
            layoutParams.rightMargin = 70;
            ll.addView(imageView, layoutParams);
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // ll.getChildAt(position).setEnabled(true);
                position = position % list.size();
                ll.getChildAt(lastPosition).setEnabled(false);
                ll.getChildAt(position).setEnabled(true);
                lastPosition = position;
                if (2 == position) {
                    ok.setVisibility(View.VISIBLE);
                } else {
                    ok.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    protected void startLogin() {
        Intent intent = new Intent(GuideActivity.this, ContentActivity.class);
        intent.putExtra(Constants.KEY_FRAGMENT, LOGIN_FRAGMENT);
        startActivity(intent);
    }
}

