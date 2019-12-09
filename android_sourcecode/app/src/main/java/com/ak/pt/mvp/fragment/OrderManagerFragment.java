package com.ak.pt.mvp.fragment;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.mvp.base.SimpleFragment;
import com.king.base.adapter.ViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/1/17.
 */

public class OrderManagerFragment extends SimpleFragment {

    Unbinder unbinder;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder1;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    private List<Fragment> listData;
    private List<CharSequence> listTitle;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private AppPermissionsBean permissionsBean;

    public static OrderManagerFragment newInstance(AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        OrderManagerFragment fragment = new OrderManagerFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_order_accept;
    }

    @Override
    public void initUI() {

        if (permissionsBean.getApp_operation().contains("0")) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("新增");
        }
        listTitle = new ArrayList<>();
        listData = new ArrayList<>();
        listTitle.add("待发出");
        listTitle.add("待接单");
        listTitle.add("已接单");
        listTitle.add("待审核");
        listTitle.add("已拒绝");
        tvTitle.setText("未完成试压记录");
        for (int i = 0; i < 5; i++) {
            listData.add(new OrderManagerChildFragment().newInstance(i + "", permissionsBean));
        }
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData, listTitle);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        viewPager.setCurrentItem(0);
        //设置预加载个数
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                startOrderAddFragment(new PressurePageBean(), "0", permissionsBean);
                break;

        }
    }

    //设置统计数
    public void setTitleTotal(int index, String total) {
        tabLayout.getTabAt(index).setText(total);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
