package com.akan.qf.view.img;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.akan.qf.R;

import java.util.ArrayList;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/15
 */

public class ShowPictureActivity extends FragmentActivity {


    private HackyViewPager pager;
    private ImageViewPagerAdapter adapter;
    private ArrayList<String> imagelist;
    private LinearLayout ll_point_group;
    private int lastPosition;
    private int position;
    private static final String STATE_POSITION = "STATE_POSITION";

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_showbigpicture);

        initView();
        initData();
        initListener();
        if (savedInstanceState != null) {
            position = savedInstanceState.getInt(STATE_POSITION);
        }
        pager.setCurrentItem(position);
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, pager.getCurrentItem());
    }

    private void initView() {
        // 接收传过来的图片集合，position为第几个，默认为0，也可以认为点击的第几张图片</span><span style="font-size:14px;">
        imagelist = getIntent().getStringArrayListExtra("imagelist");
        position = getIntent().getIntExtra("position", 0);

        pager = (HackyViewPager) findViewById(R.id.pager);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
    }

    private void initData() {
        if (imagelist.size() > 1) {
            for (int i = 0; i < imagelist.size(); i++) {
                // 动态添加指示点
                ImageView point = new ImageView(this);
                point.setBackgroundResource(R.drawable.btn_banner_selector);
                // 让第一个是黄点，其他的是白点
                if (i == position) {
                    point.setEnabled(true);
                } else {
                    point.setEnabled(false);
                }

                // 布局是一个类，子View也是一个类，布局参数也是一个独立的类
                // 任何一个布局，都有一个对应的静态类：布局参数
                // 当布局添加子view时，布局参数一定要和布局的类型保持一致
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, -2);
                layoutParams.leftMargin = 15; // 左边，15个象素的边距
                ll_point_group.addView(point, layoutParams);
            }
        }

        adapter = new ImageViewPagerAdapter(getSupportFragmentManager(), imagelist, position,"0");
        pager.setAdapter(adapter);

    }

    private void initListener() {
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                position = position % imagelist.size();
                ll_point_group.getChildAt(lastPosition).setEnabled(false);
                ll_point_group.getChildAt(position).setEnabled(true);

                lastPosition = position;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
