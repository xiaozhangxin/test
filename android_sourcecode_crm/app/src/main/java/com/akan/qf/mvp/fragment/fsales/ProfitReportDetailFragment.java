package com.akan.qf.mvp.fragment.fsales;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.ProfitBean;
import com.akan.qf.mvp.base.SimpleFragment;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProfitReportDetailFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvONe)
    TextView tvONe;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.tvSix)
    TextView tvSix;
    @BindView(R.id.tvSeven)
    TextView tvSeven;
    @BindView(R.id.tvEight)
    TextView tvEight;
    @BindView(R.id.tvNine)
    TextView tvNine;
    @BindView(R.id.tvTen)
    TextView tvTen;
    @BindView(R.id.tvEleven)
    TextView tvEleven;
    Unbinder unbinder;

    private ProfitBean bean;

    public static ProfitReportDetailFragment newInstance(ProfitBean bean) {
        Bundle args = new Bundle();
        ProfitReportDetailFragment fragment = new ProfitReportDetailFragment();
        fragment.bean = bean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_profit_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("利润表详情");
        tvONe.setText(bean.getGroup_name());
        tvTwo.setText(bean.getYear());
        tvThree.setText(bean.getTax_total_price()+"");
        tvFour.setText(bean.getCost_total_price()+"");
        tvFive.setText(bean.getCost_rate()+"");
        tvSix.setText(bean.getBusiness_total_price()+"");
        tvSeven.setText(bean.getBusiness_rate()+"");
        tvEight.setText(bean.getGross_price()+"");
        tvNine.setText(bean.getGross_rate()+"");
        tvTen.setText(bean.getNet_price()+"");
        tvEleven.setText(bean.getNet_rate()+"");
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
