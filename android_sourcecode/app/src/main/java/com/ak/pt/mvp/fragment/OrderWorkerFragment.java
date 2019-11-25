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
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.SimpleFragment;
import com.ak.pt.util.SpSingleInstance;
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

public class OrderWorkerFragment extends SimpleFragment {

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
    private UserBean userBean;
    private AppPermissionsBean permissionsBean;

    public static OrderWorkerFragment newInstance(AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        OrderWorkerFragment fragment = new OrderWorkerFragment();
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

        listTitle = new ArrayList<>();
        listData = new ArrayList<>();
        listTitle.add("待接单");
        listTitle.add("已接单");
        listTitle.add("待审核");
        listTitle.add("已完成");
        listTitle.add("已拒绝");
        tvTitle.setText("正在试压记录");
        for (int i = 0; i < 5; i++) {
            listData.add(new OrderWorkerChildFragment().newInstance(i + "", permissionsBean));
        }
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData, listTitle);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        //试压工建单用权限控制
        if (isHave("0", permissionsBean.getApp_operation().split(","))) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.add_new);
        } else {
            tvRight.setVisibility(View.GONE);
        }

    }
    public boolean isHave(String index, String[] split) {
        for (int i = 0; i < split.length; i++) {
            if (index.equals(split[i])) {
                return true;
            }
        }
        return false;
    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                startOrderAddWorkerFragment(permissionsBean);
                break;

        }
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
