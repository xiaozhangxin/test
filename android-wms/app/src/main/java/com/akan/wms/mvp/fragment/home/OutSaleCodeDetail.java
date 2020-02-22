package com.akan.wms.mvp.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.BarMsgBean;
import com.akan.wms.bean.PlanLineBeanDetail;
import com.akan.wms.bean.RecordsBean;
import com.akan.wms.bean.SaleShipBean;
import com.akan.wms.bean.SlaesBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.RecordAdapter;
import com.akan.wms.mvp.adapter.home.OutSaleDetailAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.OutSalePresenter;
import com.akan.wms.mvp.view.home.IOutSaleView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OutSaleCodeDetail extends BaseFragment<IOutSaleView, OutSalePresenter> implements IOutSaleView {


    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.tvDelete)
    ImageView tvDelete;
    @BindView(R.id.tvScan)
    ImageView tvScan;
    @BindView(R.id.tvNoOne)
    TextView tvNoOne;
    @BindView(R.id.tvNoTwo)
    TextView tvNoTwo;
    @BindView(R.id.tvNoThree)
    TextView tvNoThree;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private BarMsgBean scanBean;
    private List<PlanLineBeanDetail> list;
    private OutSaleDetailAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static OutSaleCodeDetail newInstance(BarMsgBean scanBean) {
        Bundle args = new Bundle();
        OutSaleCodeDetail fragment = new OutSaleCodeDetail();
        fragment.scanBean = scanBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_home_scan_out_sale;
    }

    @Override
    public void initUI() {
        tvTitle.setText("物流状态查询");
        if (scanBean == null) {
            ToastUtil.showToast(getContext().getApplicationContext(), "查询条码有误,清重新扫码");
            return;
        }

        SlaesBean slaesBean = scanBean.getSales();
        tvTittle.setText(slaesBean.getCustomer_name());
        tvNoOne.setText(slaesBean.getShip_plan_no());
        tvNoTwo.setText(slaesBean.getU9_code());
        tvNoThree.setText(slaesBean.getDoc_no());

        recyclerView.setNestedScrollingEnabled(false);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new OutSaleDetailAdapter(context, this.list);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.clear();
        map.put("id", scanBean.getSales().getId());
        getPresenter().querySaleShipByCode(userBean.getStaff_token(), map);
    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
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
    public OutSalePresenter createPresenter() {
        return new OutSalePresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onQuerySaleShipPage(List<SaleShipBean> data) {

    }

    @Override
    public void OnQuerySaleShipByCode(SaleShipBean data) {
        tvNoOne.setText(data.getShip_plan_no());
        tvNoTwo.setText(data.getU9_code());
        tvNoThree.setText(data.getShip_src_plan_no());
        adapter.clear();
        adapter.addAll(data.getShipLineBeanList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnAddSaleShip(String data) {

    }

    @Override
    public void OninvalidSaleShip(String data) {

    }

    @Override
    public void OnoutOfShip(String data) {

    }

    @Override
    public void OnsendSaleShip(String data) {

    }

    @Override
    public void OnDeleteSaleShip(String data) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }
}
