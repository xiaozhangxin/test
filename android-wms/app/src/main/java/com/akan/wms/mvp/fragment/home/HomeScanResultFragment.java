package com.akan.wms.mvp.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.BarMsgBean;
import com.akan.wms.mvp.base.SimpleFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeScanResultFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    private BarMsgBean scanBean;
    public static HomeScanResultFragment newInstance(BarMsgBean scanBean) {
        Bundle args = new Bundle();
        HomeScanResultFragment fragment = new HomeScanResultFragment();
        fragment.scanBean=scanBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_home_scan;
    }

    @Override
    public void initUI() {
        tvTitle.setText("物流信息");
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
}
