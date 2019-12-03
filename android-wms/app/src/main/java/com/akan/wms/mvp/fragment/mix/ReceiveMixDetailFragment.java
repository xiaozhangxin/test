package com.akan.wms.mvp.fragment.mix;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.RcvBean;
import com.akan.wms.bean.RecordsBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.RecordAdapter;
import com.akan.wms.mvp.adapter.mix.ReceiveMixDetailAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.mix.ReceiveMixPresenter;
import com.akan.wms.mvp.view.mix.IReceiveMixView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ReceiveMixDetailFragment extends BaseFragment<IReceiveMixView, ReceiveMixPresenter> implements IReceiveMixView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.ivOne)
    ImageView ivOne;
    @BindView(R.id.stateOne)
    TextView stateOne;
    @BindView(R.id.lineOne)
    View lineOne;
    @BindView(R.id.ivTwo)
    ImageView ivTwo;
    @BindView(R.id.stateTwo)
    TextView stateTwo;
    @BindView(R.id.addImg)
    ConstraintLayout addImg;
    @BindView(R.id.tvOneAdd)
    TextView tvOneAdd;
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
    @BindView(R.id.tvThird)
    TextView tvThird;
    @BindView(R.id.llbg)
    LinearLayout llbg;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recordRecyclerView)
    RecyclerView recordRecyclerView;

    private List<RecordsBean> listRecord;
    private RecordAdapter adapterRecord;
    private List<RcvBean.LineBeanListBean> list;
    private ReceiveMixDetailAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mId;

    public static ReceiveMixDetailFragment newInstance(String id) {
        Bundle args = new Bundle();
        ReceiveMixDetailFragment fragment = new ReceiveMixDetailFragment();
        fragment.mId = id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_detail_send_mix;
    }

    @Override
    public void initUI() {
        tvTitle.setText("杂收单详情");
        stateOne.setText("审核中");
        stateTwo.setText("已审核");

        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ReceiveMixDetailAdapter(context, list);
        recyclerView.setAdapter(adapter);
        //审核记录
        recordRecyclerView.setNestedScrollingEnabled(false);
        listRecord = new ArrayList<>();
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapterRecord = new RecordAdapter(context, this.listRecord);
        recordRecyclerView.setAdapter(adapterRecord);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getData();

    }

    private void getData() {
        map.put("id", mId);
        getPresenter().queryRcvById(userBean.getStaff_token(), map);
    }

    @Override
    public void onQueryRcvById(RcvBean data) {
        updateState(data.getStatus() + "");
        tvOneAdd.setText(data.getDoc_no());
        tvOne.setText(data.getWh_man_name());
        tvTwo.setText(data.getUpdate_time());
        tvThree.setText(data.getDoc_type_name());//单据类型
        tvFour.setText(data.getWh_man_name());//受益组织
        //tvFive.setText(data.getBenefit_org_name());//受益部门
        tvSix.setText(data.getCenter_def_name());//负责中心
        tvSeven.setText(data.getUse_def_name());//使用中心
        tvEight.setText(data.getManage_group_name());//负责部门
        adapter.clear();
        adapter.addAll(data.getLineBeanList());
        adapter.notifyDataSetChanged();
        adapterRecord.clear();
        adapterRecord.addAll(data.getRecordsBeans());
        adapterRecord.notifyDataSetChanged();
    }


    //更新顶部状态
    private void updateState(String mState) {
        switch (mState) {
            case "1":
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("审核");
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                break;
            case "2":
                tvRight.setVisibility(View.GONE);
                ivTwo.setImageResource(R.drawable.state_h);
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                ivTwo.setScaleY(1.5f);
                ivTwo.setScaleX(1.5f);
                lineOne.setBackgroundResource(R.color.colorPrimary);
                stateTwo.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                break;

        }
    }


    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                showDialog();
                break;
        }
    }

    //确认配货弹框
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("是否确认审核通过？");
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("id", mId);
                getPresenter().auditRcv(userBean.getStaff_token(), map);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }


    @Override
    public ReceiveMixPresenter createPresenter() {
        return new ReceiveMixPresenter(getApp());
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


    @Override
    public void onQueryRcvPage(List<RcvBean> data) {

    }


    @Override
    public void onAuditRcv(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        getData();
    }

    @Override
    public void onSyncMix(String data) {

    }
}

