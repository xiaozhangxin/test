package com.akan.wms.mvp.fragment.base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.StoragingProListBean;
import com.akan.wms.bean.WarnTwoBean;
import com.akan.wms.mvp.adapter.home.FinishListtAdapter;
import com.akan.wms.mvp.adapter.home.NumListtAdapter;
import com.akan.wms.mvp.base.SimpleFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class StockFindDetailFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvOne)
    TextView tvOne;
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
    @BindView(R.id.tvBottom)
    TextView tvBottom;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private WarnTwoBean mBean;

    private List<WarnTwoBean.MfcStockBean> list;
    private NumListtAdapter adapter;

    public static StockFindDetailFragment newInstance(WarnTwoBean bean) {
        Bundle args = new Bundle();
        StockFindDetailFragment fragment = new StockFindDetailFragment();
        fragment.mBean = bean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_detail_stock_find;
    }

    @Override
    public void initUI() {
        tvTitle.setText("料品详情");
        tvOne.setText(mBean.getItem_code());
        tvTwo.setText(mBean.getItem_name());
        if (TextUtils.isEmpty(mBean.getItem_spec())) {
            tvThree.setText("暂无规格");
        } else {
            tvThree.setText(mBean.getItem_spec());
        }
        tvFour.setText(mBean.getWh_name());
/*        if (mBean.getQty() < mBean.getFloor_qty()) {
            tvFive.setTextColor(getResources().getColor(R.color.color_yellow));
        } else if (mBean.getQty() > mBean.getCeiling_qty()) {
            tvFive.setTextColor(getResources().getColor(R.color.red));
        } else {
            tvFive.setTextColor(getResources().getColor(R.color.colorPrimary));
        }*/
        tvFive.setText(mBean.getQty() + "");
        List<WarnTwoBean.MfcStockBean> mfcStock = mBean.getMfcStock();
        if (mfcStock!=null && mfcStock.size()>0){
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new NumListtAdapter(context, mfcStock);
            recyclerView.setAdapter(adapter);
        }else {
            recyclerView.setVisibility(View.GONE);
        }



        tvSix.setText(mBean.getCeiling_qty() + "");
        tvSeven.setText(mBean.getFloor_qty() + "");
        // tvEight.setText(mBean.getWh_time());
        // tvNine.setText(mBean.getRemain_time());


    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ivLeft, R.id.tvBottom})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvBottom:
                startFlowListFragment(mBean.getItem_id());
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
