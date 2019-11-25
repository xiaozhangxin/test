package com.ak.pt.mvp.fragment.qifei;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ak.pt.R;
import com.ak.pt.mvp.base.SimpleFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by admin on 2018/7/31.
 */

public class OrderImgTwoFragment extends SimpleFragment {


    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    private List<PressurePageBean.PiptbBaseTypeBean> imgList;
    private String listName;
    private ImageTwoAdapter adapter;

    public static OrderImgTwoFragment newInstance(List<PressurePageBean.PiptbBaseTypeBean> imgList, String listName) {

        Bundle args = new Bundle();

        OrderImgTwoFragment fragment = new OrderImgTwoFragment();
        fragment.imgList = imgList;
        fragment.listName = listName;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_order_img;
    }

    @Override
    public void initUI() {
        tvTitle.setText(listName);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ImageTwoAdapter(getContext(), imgList);
        recycleView.setAdapter(adapter);

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.ivLeft)
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