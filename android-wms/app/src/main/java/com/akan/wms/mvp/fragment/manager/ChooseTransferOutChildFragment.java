package com.akan.wms.mvp.fragment.manager;

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
import com.akan.wms.bean.TransferOutBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.home.ChooseTransferOutChildAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.OutTransferPresenter;
import com.akan.wms.mvp.view.home.IOutTransferView;
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

public class ChooseTransferOutChildFragment extends BaseFragment<IOutTransferView, OutTransferPresenter> implements IOutTransferView {

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

    private TransferOutBean mBean;
    private List<TransferOutBean.LineBeanListBean> list;
    private ChooseTransferOutChildAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private boolean isAllCheck = false;
    private String mState;
    private int checkNum=0;

    public static ChooseTransferOutChildFragment newInstance(TransferOutBean bean, String state) {
        Bundle args = new Bundle();
        ChooseTransferOutChildFragment fragment = new ChooseTransferOutChildFragment();
        fragment.mBean = bean;
        fragment.mState = state;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_stock_child;
    }

    @Override
    public void initUI() {
        tvTitle.setText("调拨出库单详情");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ChooseTransferOutChildAdapter(context, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                boolean check = adapter.getItem(position).isCheck();
                adapter.getItem(position).setCheck(!check);
                adapter.notifyItemChanged(position);
                checkNum = 0;
                List<TransferOutBean.LineBeanListBean> allData = adapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    if (allData.get(i).isCheck()) {
                        checkNum++;
                    }
                }
                tvChooseNum.setText("已选择(" + checkNum + ")");
                if (checkNum == allData.size()) {
                    ckTop.setChecked(true);
                    isAllCheck = true;
                } else  {
                    ckTop.setChecked(false);
                    isAllCheck = false;
                }

            }
        });

        switch (mState) {
            case "1":
                addData(mBean);
                break;
        }


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        switch (mState) {
            case "0":
                map.put("id", mBean.getId() + "");
                getPresenter().queryTransferById(userBean.getStaff_token(), map);
                break;
        }
    }

    @Override
    public void onQueryTransferById(TransferOutBean data) {
        addData(data);
    }

 

    private void addData(TransferOutBean data) {
        mBean.setBarBeanList(data.getBarBeanList());
        tvNo.setText("调出组织:"+data.getOut_org_name());
        tvtittle.setText(data.getDoc_no()+"\n申请单单号："+data.getApply_no());
        adapter.clear();
        adapter.addAll(data.getLineBeanList());
        adapter.notifyDataSetChanged();
        checkNum = 0;
        List<TransferOutBean.LineBeanListBean> allData = adapter.getAllData();
        for (int i = 0; i < allData.size(); i++) {
            if (adapter.getItem(i).isCheck()) {
                checkNum++;
            }
        }
        if (checkNum == adapter.getAllData().size()) {
            isAllCheck = true;
            ckTop.setChecked(true);
        }
        tvChooseNum.setText("已选择(" + checkNum + ")");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public OutTransferPresenter createPresenter() {
        return new OutTransferPresenter(getApp());
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
                List<TransferOutBean.LineBeanListBean> allData = adapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    adapter.getItem(i).setCheck(!isAllCheck);
                }
                adapter.notifyDataSetChanged();
                isAllCheck = !isAllCheck;
                if (isAllCheck) {
                    checkNum=allData.size();

                } else {
                    checkNum=0;
                }
                tvChooseNum.setText("已选择(" + checkNum + ")");
                break;
            case R.id.tvOk:
                if (checkNum <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择商品");
                    return;
                }
                mBean.setLineBeanList(adapter.getAllData());
                EventBus.getDefault().post(new FirstEvent("list_finish"));
                EventBus.getDefault().post(new FirstEvent("17", mBean));
                finish();
                break;
        }
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());

    }


    @Override
    public void onQueryTransferOutPage(List<TransferOutBean> data) {

    }



    @Override
    public void onAddTransferOut(String data) {

    }

    @Override
    public void onTransferOutWh(String data) {

    }

    @Override
    public void onQueryTransferOutPageTwo(List<TransferOutBean> data) {

    }

    @Override
    public void onSync(String data) {

    }
}
