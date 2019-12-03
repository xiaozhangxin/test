package com.akan.wms.mvp.fragment.in;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.ReturnLineBean;
import com.akan.wms.bean.SaleReturnBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.home.SaleReturnChildAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.ChooseSaleReturnPresenter;
import com.akan.wms.mvp.view.home.IChooseSaleReturnView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChooseSaleReturnChildFragment extends BaseFragment<IChooseSaleReturnView, ChooseSaleReturnPresenter> implements IChooseSaleReturnView {


    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.ivImg)
    ImageView ivImg;
    @BindView(R.id.tvtittle)
    TextView tvtittle;
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.ckTop)
    CheckBox ckTop;
    @BindView(R.id.allTop)
    ConstraintLayout allTop;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvChooseNum)
    TextView tvChooseNum;
    @BindView(R.id.tvOk)
    TextView tvOk;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;


    private SaleReturnBean mBean;

    private List<ReturnLineBean> list;
    private SaleReturnChildAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private boolean isAllCheck = false;


    public static ChooseSaleReturnChildFragment newInstance(SaleReturnBean bean) {
        Bundle args = new Bundle();
        ChooseSaleReturnChildFragment fragment = new ChooseSaleReturnChildFragment();
        fragment.mBean = bean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_stock_child;
    }

    @Override
    public void initUI() {
        tvTitle.setText("退货申请单详情");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new SaleReturnChildAdapter(context, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                boolean check = adapter.getItem(position).isCheck();
                adapter.getItem(position).setCheck(!check);
                adapter.notifyItemChanged(position);
                int checkNum = 0;
                List<ReturnLineBean> allData = adapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    if (allData.get(i).isCheck()) {
                        checkNum++;
                    }
                }
                tvChooseNum.setText("已选择(" + checkNum + ")");
                if (checkNum == 0) {
                    ckTop.setChecked(false);
                    isAllCheck = false;
                } else if (checkNum == allData.size()) {
                    ckTop.setChecked(true);
                    isAllCheck = true;
                }

            }
        });

    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("org_id", userBean.getOrg_id());
        map.put("id", mBean.getId() + "");
        getPresenter().querySaleReturnDetail(userBean.getStaff_token(), map);

    }
    @Override
    public void OnQuerySaleReturnDetail(SaleReturnBean data) {
        addData(data);
    }

    @Override
    public void OnWhSaleRcv(String data) {

    }

    private void addData(SaleReturnBean data) {
        tvtittle.setText(data.getDoc_type_name());
        tvNo.setText(data.getDoc_no());
        adapter.clear();
        adapter.addAll(data.getReturnLineBeans());
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public ChooseSaleReturnPresenter createPresenter() {
        return new ChooseSaleReturnPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ivLeft, R.id.tvOk, R.id.allTop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.allTop:
                ckTop.setChecked(!isAllCheck);
                List<ReturnLineBean> allData = adapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    adapter.getItem(i).setCheck(!isAllCheck);
                }
                adapter.notifyDataSetChanged();
                isAllCheck = !isAllCheck;
                if (isAllCheck) {
                    tvChooseNum.setText("已选择(" + allData.size() + ")");
                } else {
                    tvChooseNum.setText("已选择(0)");
                }

                break;
            case R.id.tvOk:
                List<ReturnLineBean> allData1 = adapter.getAllData();
                List<ReturnLineBean> list=new ArrayList<>();
                for (int i=0;i<allData1.size();i++){
                    if (allData1.get(i).isCheck()){
                        list.add(allData1.get(i));
                    }

                }
                if (list.size()<=0){
                    ToastUtil.showToast(context.getApplicationContext(),"请选择商品");
                    return;
                }
                mBean.setReturnLineBeans(list);
                EventBus.getDefault().post(new FirstEvent("list_finish"));
                EventBus.getDefault().post(new FirstEvent("13",mBean));
                finish();
                break;
        }
    }

    @Override
    public void onQuerySaleReturnPage(List<SaleReturnBean> data) {

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
