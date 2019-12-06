package com.akan.qf.mvp.fragment.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.mvp.base.SimpleFragment;
import com.king.base.adapter.ViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/12/19.
 */

public class NoticeNewFragment extends SimpleFragment {

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


    public static NoticeNewFragment newInstance() {
        Bundle args = new Bundle();
        NoticeNewFragment fragment = new NoticeNewFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_regional_submission_new;
    }

    @Override
    public void initUI() {
        listTitle = new ArrayList<>();
        listData = new ArrayList<>();
        listTitle.add("全部公告");
        listTitle.add("我的公告");
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.drawable.search);
        tvTitle.setText("公告");
        listData.add(new NoticeAllFragment().newInstance());
        listData.add(new NoticeMyFragment().newInstance());
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData, listTitle);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.ivLeft, R.id.ivRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivRight:
                startNoticeSearchFragment();
                break;

        }
    }

    public void setTitleTotal(int index, String total){
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
