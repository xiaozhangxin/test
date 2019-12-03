package com.akan.wms.mvp.fragment.out;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.PlanLineBeanDetail;
import com.akan.wms.bean.RecordsBean;
import com.akan.wms.bean.SaleShipBean;
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.RecordAdapter;
import com.akan.wms.mvp.adapter.home.OutSaleDetailAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.OutSalePresenter;
import com.akan.wms.mvp.view.home.IOutSaleView;
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
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class OutSaleDetailFragment extends BaseFragment<IOutSaleView, OutSalePresenter> implements IOutSaleView {

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
    @BindView(R.id.stateLineOne)
    View stateLineOne;
    @BindView(R.id.ivTwo)
    ImageView ivTwo;
    @BindView(R.id.stateTwo)
    TextView stateTwo;
    @BindView(R.id.stateLineTwo)
    View stateLineTwo;
    @BindView(R.id.ivThree)
    ImageView ivThree;
    @BindView(R.id.stateThree)
    TextView stateThree;
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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.tvDelete)
    ImageView tvDelete;
    @BindView(R.id.tvScan)
    ImageView tvScan;
    @BindView(R.id.addImg)
    ConstraintLayout addImg;
    @BindView(R.id.recordRecyclerView)
    RecyclerView recordRecyclerView;
    private List<PlanLineBeanDetail> list;
    private OutSaleDetailAdapter adapter;


    private List<RecordsBean> listRecord;
    private RecordAdapter adapterRecord;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mId;
    private BottomPopWindow popWindow;

    public static OutSaleDetailFragment newInstance(String id) {
        Bundle args = new Bundle();
        OutSaleDetailFragment fragment = new OutSaleDetailFragment();
        fragment.mId = id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_detail_out_sale;
    }

    @Override
    public void initUI() {
        tvTitle.setText("销售出库单详情");
        stateOne.setText("已配货");
        stateTwo.setText("已出库");
        stateThree.setText("已装车");

        recyclerView.setNestedScrollingEnabled(false);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new OutSaleDetailAdapter(context, this.list);
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
        popWindow.setTwoGone();
        popWindow.setOnItemClickListener(new BottomPopWindow.OnItemClickListener() {
            @Override
            public void setOnItemClick(View v) {
                switch (v.getId()) {
                    case R.id.btOne:
                        showDialog("作废");
                        popWindow.dismiss();
                        break;
                    case R.id.btTwo:
                        checkCameraPermissions("out_sale_detail");
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
        map.put("id", mId);
        getPresenter().querySaleShipByCode(userBean.getStaff_token(), map);
    }

    @Override
    public void OnQuerySaleShipByCode(SaleShipBean data) {
        // 0 已配货 1已出库  2已装车
        updateState(data.getStatus());

        if (isValidImg != null) {
            if ("1".equals(data.getIs_valid())) {
                isValidImg.setVisibility(View.VISIBLE);
                if (userBean.getStaff_id().equals(data.getCreate_id())) {//作费时可以删除自己的单据
                    tvRight.setVisibility(View.VISIBLE);
                    tvRight.setText("删除");
                } else {
                    tvRight.setVisibility(View.GONE);
                }
            } else {
                isValidImg.setVisibility(View.GONE);
            }
        }
        tvTittle.setText("出货计划单 " + data.getShip_plan_no());
        tvOne.setText(data.getDoc_no());
        tvTwo.setText(data.getCreate_name());
        tvThree.setText(data.getCreate_time());
        tvFour.setText(data.getCustomer_name());
        tvFive.setText(data.getDoc_type_name());
        adapter.clear();
        adapter.addAll(data.getShipLineBeanList());
        adapter.notifyDataSetChanged();

        adapterRecord.clear();
        adapterRecord.addAll(data.getRecordsBeans());
        adapterRecord.notifyDataSetChanged();

    }

    //更新顶部状态
    private void updateState(String mState) {
        switch (mState) {
            case "0":
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("操作");
                break;
            case "1":
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("确认装车");
                ivTwo.setImageResource(R.drawable.state_h);
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                ivTwo.setScaleY(1.5f);
                ivTwo.setScaleX(1.5f);
                stateLineOne.setBackgroundResource(R.color.colorPrimary);
                stateTwo.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                break;
            case "2":
                tvRight.setVisibility(View.GONE);
                ivTwo.setImageResource(R.drawable.state_h);
                ivThree.setImageResource(R.drawable.state_h);
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                ivTwo.setScaleY(1.5f);
                ivTwo.setScaleX(1.5f);
                ivThree.setScaleY(1.5f);
                ivThree.setScaleX(1.5f);
                stateLineOne.setBackgroundResource(R.color.colorPrimary);
                stateLineTwo.setBackgroundResource(R.color.colorPrimary);
                stateTwo.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                stateThree.setTextColor(getResources().getColor(R.color.colorPrimary));
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
                String toString = tvRight.getText().toString();
                switch (toString) {
                    case "确认装车":
                        showDialog("装车");
                        break;
                    case "删除":
                        showDialog("删除");
                        break;
                    case "操作":
                        popWindow.showAtLocation(tvRight, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        break;
                }
                break;
        }
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
                builder.setMessage("是否同意出库？");
                break;
            case "装车":
                builder.setMessage("是否确认装车？");
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
                    case "装车":
                        toCar();
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
        map.put("id", mId);
        getPresenter().deleteSaleShip(userBean.getStaff_token(), map);
    }

    //装车
    private void toCar() {
        map.clear();
        map.put("sale_ship_id", mId);
        getPresenter().sendSaleShip(userBean.getStaff_token(), map);
    }

    //同意出库
    private void agreeOut() {
        map.clear();
        map.put("sale_ship_id", mId);
        getPresenter().outOfShip(userBean.getStaff_token(), map);
    }

    //作废
    private void refuseOut() {
        map.clear();
        map.put("id", mId);
        getPresenter().invalidSaleShip(userBean.getStaff_token(), map);
    }

    //----------------------------------------扫码--------------------------------------------------

    public static final int RC_CAMERA = 0X01;

    //检测拍摄权限
    @AfterPermissionGranted(RC_CAMERA)
    private void checkCameraPermissions(String type) {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(context, perms)) {
            //开启扫码
            List<PlanLineBeanDetail> allData = adapter.getAllData();
            List<ScanInfoBean> scanList = new ArrayList<>();
            for (int i = 0; i < allData.size(); i++) {
                ScanInfoBean scanBean = new ScanInfoBean();
                PlanLineBeanDetail detail = allData.get(i);
                scanBean.setItem_id(detail.getItem_code());
                scanBean.setItem_name(detail.getItem_name());
                scanBean.setItem_spec(detail.getItem_spec());
                scanBean.setSend_qty(detail.getQty());
                scanBean.setArrive_qty(0);
                scanList.add(scanBean);
            }

            startInBuyScanFragment(scanList, type);
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera), RC_CAMERA, perms);
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
    public void OnAddSaleShip(String data) {

    }

    @Override
    public void OninvalidSaleShip(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        getData();
    }

    @Override
    public void OnoutOfShip(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        getData();
    }

    @Override
    public void OnsendSaleShip(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        getData();
    }

    @Override
    public void OnDeleteSaleShip(String data) {
        EventBus.getDefault().post(new FirstEvent("delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
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
}
