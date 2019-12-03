package com.akan.wms.mvp.fragment.manager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.InfoBeanList;
import com.akan.wms.bean.InventoryBean;
import com.akan.wms.bean.ItemWhQohBean;
import com.akan.wms.bean.RecordsBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.RecordAdapter;
import com.akan.wms.mvp.adapter.home.CheckDetailAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.CheckPresenter;
import com.akan.wms.mvp.view.home.ICheckView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.akan.wms.view.BottomPopWindow;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CheckDetailFragment extends BaseFragment<ICheckView, CheckPresenter> implements ICheckView {

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
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.tvScan)
    ImageView tvScan;
    @BindView(R.id.llbg)
    LinearLayout llbg;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.addImg)
    ConstraintLayout addImg;
    @BindView(R.id.recordRecyclerView)
    RecyclerView recordRecyclerView;

    private List<RecordsBean> listRecord;
    private RecordAdapter adapterRecord;
    private List<InfoBeanList> list;
    private CheckDetailAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mId;
    private BottomPopWindow popWindow;

    public static CheckDetailFragment newInstance(String id) {
        Bundle args = new Bundle();
        CheckDetailFragment fragment = new CheckDetailFragment();
        fragment.mId = id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_detail_check;
    }

    @Override
    public void initUI() {
        tvTitle.setText("盘点单详情");
        stateOne.setText("未处理");
        stateTwo.setText("已审核");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new CheckDetailAdapter(context, list);
        recyclerView.setAdapter(adapter);
        //审核记录
        recordRecyclerView.setNestedScrollingEnabled(false);
        listRecord = new ArrayList<>();
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapterRecord = new RecordAdapter(context, this.listRecord);
        recordRecyclerView.setAdapter(adapterRecord);

        initPop();
        initInvalidImg();
    }

    //动态添加作废图片
    private ImageView isValidImg;

    private void initInvalidImg() {
        isValidImg = new ImageView(context);
        isValidImg.setVisibility(View.GONE);
        isValidImg.setImageResource(R.drawable.invalid);
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        lp.rightToRight = addImg.getRight();
        addImg.addView(isValidImg, lp);
    }


    //初始化底部弹窗
    private void initPop() {
        popWindow = new BottomPopWindow(context);
        popWindow.setTwoName("拒绝");
        popWindow.setOnItemClickListener(new BottomPopWindow.OnItemClickListener() {
            @Override
            public void setOnItemClick(View v) {
                switch (v.getId()) {
                    case R.id.btOne:
                        showDialog("作废");
                        popWindow.dismiss();
                        break;
                    case R.id.btTwo:
                        showDialog("拒绝");
                        popWindow.dismiss();
                        break;
                    case R.id.btThree:
                        showDialog("同意");
                        popWindow.dismiss();
                        break;
                    case R.id.btFour:
                        popWindow.dismiss();
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getData();
    }

    private void getData() {
        map.put("inventory_id", mId);
        getPresenter().getInventoryDetail(userBean.getStaff_token(), map);
    }

    @Override
    public void OnGetInventoryDetail(InventoryBean data) {
        // 0 已配货 1已入库
        updateState(data.getInventory_status());
        if (isValidImg != null) {
            if ("1".equals(data.getIs_valid())) {
                isValidImg.setVisibility(View.VISIBLE);
                if (userBean.getStaff_id().equals(data.getStaff_id())) {
                    tvRight.setVisibility(View.VISIBLE);
                    tvRight.setText("删除");
                } else {
                    tvRight.setVisibility(View.GONE);
                }
            } else {
                isValidImg.setVisibility(View.GONE);
            }
        }

        tvOne.setText(data.getInventory_no());
        tvTwo.setText(data.getStaff_name());
        tvThree.setText(data.getCreate_time());
        if (TextUtils.isEmpty(data.getMemo())) {
            tvFour.setText("暂无备注");
        } else {
            tvFour.setText(data.getMemo());
        }

        adapter.clear();
        adapter.addAll(data.getInfoList());
        adapter.notifyDataSetChanged();

        adapterRecord.clear();
        adapterRecord.addAll(data.getRecordsBeans());
        adapterRecord.notifyDataSetChanged();
    }


    //流程变更弹框
    private void showDialog(final String type) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        switch (type) {
            case "作废":
                builder.setMessage("是否作废此单据？");
                break;
            case "同意":
                builder.setMessage("是否审核通过？");
                break;
            case "拒绝":
                builder.setMessage("是否拒绝此单据？");
                break;
            case "删除":
                builder.setMessage("是否确认删除此单据？");
                break;
        }
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (type) {
                    case "作废":
                        refuseOut();
                        break;
                    case "同意":
                        agreeOut();
                        break;
                    case "拒绝":
                        notAccept();
                        break;
                    case "删除":
                        toDelete();
                        break;

                }
            }


        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();

    }

    //删除
    private void toDelete() {
        map.clear();
        map.put("inventory_id", mId);
        getPresenter().deleteInventory(userBean.getStaff_token(), map);
    }


    //同意
    private void agreeOut() {
        map.clear();
        map.put("inventory_id", mId);
        map.put("inventory_status", "1");
        getPresenter().updateInventoryStatus(userBean.getStaff_token(), map);
    }

    //拒绝
    private void notAccept() {
        map.clear();
        map.put("inventory_id", mId);
        map.put("inventory_status", "3");
        getPresenter().updateInventoryStatus(userBean.getStaff_token(), map);
    }

    //作废
    private void refuseOut() {
        map.clear();
        map.put("inventory_id", mId);
        map.put("inventory_status", "2");
        getPresenter().updateInventoryStatus(userBean.getStaff_token(), map);
    }


    //更新顶部状态
    private void updateState(String mState) {
        switch (mState) {
            case "0":
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("操作");
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                break;
            case "1":
                tvRight.setVisibility(View.GONE);
                lineOne.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                ivTwo.setImageResource(R.drawable.state_h);
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                ivTwo.setScaleY(1.5f);
                ivTwo.setScaleX(1.5f);
                stateTwo.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case "3":
                tvRight.setVisibility(View.GONE);
                stateTwo.setTextColor(getResources().getColor(R.color.red));
                stateTwo.setText("拒绝");
                lineOne.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                ivTwo.setImageResource(R.drawable.state_error);
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                ivTwo.setScaleY(1.5f);
                ivTwo.setScaleX(1.5f);
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
                switch (tvRight.getText().toString()) {
                    case "操作":
                        popWindow.showAtLocation(tvRight, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        break;
                    case "删除":
                        showDialog("删除");
                        break;
                }

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
    public CheckPresenter createPresenter() {
        return new CheckPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void onGetInventoryList(List<InventoryBean> data) {
    }


    @Override
    public void OnUpdateInventoryStatus(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent("refresh"));
        getData();
    }

    @Override
    public void OnInsertInventory(String data) {

    }

    @Override
    public void onGetItemWhQohByRandomList(List<ItemWhQohBean> data) {

    }

    @Override
    public void OnDeleteInventory(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent("delete"));
        finish();
    }
}

