package com.ak.pt.mvp.fragment.mall;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.FirstEvent;
import com.ak.pt.mvp.base.SimpleFragment;
import com.githang.statusbar.StatusBarCompat;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/7.
 */

public class OrderOverFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRightTwo)
    ImageView ivRightTwo;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvOrder)
    TextView tvOrder;
    @BindView(R.id.tvMall)
    TextView tvMall;
    Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.white));
        } else {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private String order_id;

    public static OrderOverFragment newInstance(String order_id) {

        Bundle args = new Bundle();
        OrderOverFragment fragment = new OrderOverFragment();
        fragment.order_id = order_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_order_over;
    }

    @Override
    public void initUI() {
        tvTitle.setText("支付成功");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ivLeft, R.id.tvOrder, R.id.tvMall})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvOrder:
                //startAllOrderFragment();
                if (!TextUtils.isEmpty(order_id)) {
                    startOrderDetailFragment(order_id);
                    finish();
                }
                break;
            case R.id.tvMall:
                EventBus.getDefault().post(new FirstEvent("toMall"));
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