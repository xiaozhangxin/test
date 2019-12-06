package com.akan.qf.mvp.fragment.statistics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.akan.qf.R;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.base.SimpleFragment;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/3/18.
 */

public class OrderImgTwoFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    Unbinder unbinder;
    private List<PhotoListBean> imgList;
    private ImgTwoAdapter numAdapter;

    public static OrderImgTwoFragment newInstance(List<PhotoListBean> imgList) {
        Bundle args = new Bundle();
        OrderImgTwoFragment fragment = new OrderImgTwoFragment();
        fragment.imgList = imgList;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_order_img;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.pressure_img);
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        numAdapter = new ImgTwoAdapter(context, imgList);
        recycleView.setAdapter(numAdapter);
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

