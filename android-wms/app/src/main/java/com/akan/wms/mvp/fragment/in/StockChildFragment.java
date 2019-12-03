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
import com.akan.wms.bean.PurchaseBean;
import com.akan.wms.bean.PurchaseLinesBean;
import com.akan.wms.mvp.adapter.home.StockChildAdapter;
import com.akan.wms.mvp.base.SimpleFragment;
import com.akan.wms.util.ToastUtil;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class StockChildFragment extends SimpleFragment {

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

    private PurchaseBean mBean;
    private StockChildAdapter adapter;
    private boolean isAllCheck = false;
    private String childType;

    public static StockChildFragment newInstance(PurchaseBean bean, String childType) {
        Bundle args = new Bundle();
        StockChildFragment fragment = new StockChildFragment();
        fragment.mBean = bean;
        fragment.childType = childType;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_stock_child;
    }

    @Override
    public void initUI() {
        tvTitle.setText("采购单");
        if ("home".equals(childType)) {
            llBottom.setVisibility(View.GONE);
            ckTop.setVisibility(View.GONE);

        }
        tvNo.setText(mBean.getDoc_no());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new StockChildAdapter(context, mBean.getPurchase_lines(), childType);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                boolean check = adapter.getItem(position).isCheck();
                adapter.getItem(position).setCheck(!check);
                adapter.notifyItemChanged(position);

                int checkNum = 0;
                List<PurchaseLinesBean> allData = adapter.getAllData();
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

    @OnClick({R.id.ivLeft, R.id.allTop, R.id.tvOk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.allTop:
                ckTop.setChecked(!isAllCheck);
                List<PurchaseLinesBean> allData = adapter.getAllData();
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
                List<PurchaseLinesBean> allData1 = adapter.getAllData();
                List<PurchaseLinesBean> list = new ArrayList<>();
                for (int i = 0; i < allData1.size(); i++) {
                    if (allData1.get(i).isCheck()) {
                        list.add(allData1.get(i));
                    }

                }
                if (list.size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择商品");
                    return;
                }
                mBean.setPurchase_lines(list);
                EventBus.getDefault().post(new FirstEvent("stock_list_finish"));
                EventBus.getDefault().post(new FirstEvent("5", mBean));
                finish();
                break;
        }
    }
}
