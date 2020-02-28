package com.akan.wms.mvp.fragment.in;

import android.Manifest;
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
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.BarBean;
import com.akan.wms.bean.BarListBean;
import com.akan.wms.bean.FinishNumBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.RecordsBean;
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.StoragingProBean;
import com.akan.wms.bean.StoragingProListBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.bean.WhAndIdBean;
import com.akan.wms.mvp.adapter.RecordAdapter;
import com.akan.wms.mvp.adapter.home.FinishDetailAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.FinishPresenter;
import com.akan.wms.mvp.view.home.IFinishView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.akan.wms.view.BottomPopWindow;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

public class FinishDetailFragment extends BaseFragment<IFinishView, FinishPresenter> implements IFinishView {

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
    TextView stateLineOne;
    @BindView(R.id.ivTwo)
    ImageView ivTwo;
    @BindView(R.id.stateTwo)
    TextView stateTwo;
    @BindView(R.id.stateLineTwo)
    TextView stateLineTwo;
    @BindView(R.id.ivThree)
    ImageView ivThree;
    @BindView(R.id.stateThree)
    TextView stateThree;
    @BindView(R.id.addImg)
    ConstraintLayout addImg;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.tvSeven)
    TextView tvSeven;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recordRecyclerView)
    RecyclerView recordRecyclerView;


    private List<RecordsBean> listRecord;
    private RecordAdapter adapterRecord;
    private List<StoragingProBean.StoragingProLinesBean> list;
    private FinishDetailAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mId;
    private BottomPopWindow popWindow;
    private int mScanPosition;
    private int mDeportPosition;//选择仓库
    private StoragingProBean mBean;

    public static FinishDetailFragment newInstance(String id) {
        Bundle args = new Bundle();
        FinishDetailFragment fragment = new FinishDetailFragment();
        fragment.mId = id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_detail_finish;
    }

    @Override
    public void initUI() {
        tvTitle.setText("成品入库详情");
        stateOne.setText("未处理");
        stateTwo.setText("已质检");
        stateThree.setText("已审核");

        list = new ArrayList<>();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new FinishDetailAdapter(context, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnStockListener(new FinishDetailAdapter.onSelectStockClickListener() {
            @Override
            public void onScan(int position, String state) {
                mScanPosition = position;
                checkCameraPermissions("finish_check");
            }

            @Override
            public void onChooseWh(int position, String itemId) {
                mDeportPosition = position;
                startChooseDeportByIdFragment(itemId);
            }
        });


        //审核记录
        recordRecyclerView.setNestedScrollingEnabled(false);
        listRecord = new ArrayList<>();
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapterRecord = new RecordAdapter(context, this.listRecord);
        recordRecyclerView.setAdapter(adapterRecord);

        popWindow = new BottomPopWindow(getActivity());
        popWindow.setTwoGone();
        popWindow.setOnItemClickListener(new BottomPopWindow.OnItemClickListener() {
            @Override
            public void setOnItemClick(View v) {
                switch (v.getId()) {
                    case R.id.btOne:
                        showDialog("作废");
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

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getData();

    }

    private void getData() {
        map.clear();
        map.put("id", mId);
        getPresenter().queryStoragingPro(userBean.getStaff_token(), map);
    }

    @Override
    public void onQueryStoragingPro(StoragingProBean data) {
        mBean=data;
        updateState(data.getStatus());
        if (isValidImg != null) {
            if ("1".equals(data.getIs_valid())) {
                isValidImg.setVisibility(View.VISIBLE);
                if (userBean.getStaff_id().equals(data.getCreator_id())) {
                    tvRight.setVisibility(View.VISIBLE);
                    tvRight.setText("删除");
                } else {
                    tvRight.setVisibility(View.GONE);
                }
            } else {
                isValidImg.setVisibility(View.GONE);
            }
        }
        tvOne.setText(data.getDoc_no());
        tvTwo.setText(data.getCreator_name());
        tvThree.setText(data.getCreate_time());
        tvFour.setText(data.getReport_name());
        if (TextUtils.isEmpty(data.getRemark())) {
            tvSeven.setText("暂无备注");
        } else {
            tvSeven.setText(data.getRemark());
        }

        adapter.clear();
        adapter.addAll(data.getStoraging_pro_lines());
        adapter.notifyDataSetChanged();
        adapterRecord.clear();
        adapterRecord.addAll(data.getRecordsBeans());
        adapterRecord.notifyDataSetChanged();
    }

    //更新顶部状态
    private void updateState(String mState) {
        switch (mState) {
            case "0":
                adapter.setState("0");
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("质检");
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                break;
            case "1":
                adapter.setState("2");
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("操作");
                ivTwo.setImageResource(R.drawable.state_h);
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                ivTwo.setScaleY(1.5f);
                ivTwo.setScaleX(1.5f);
                stateLineOne.setBackgroundResource(R.color.colorPrimary);
                stateTwo.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case "2":
                adapter.setState("3");
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
                stateTwo.setTextColor(getResources().getColor(R.color.colorPrimary));
                stateLineTwo.setBackgroundResource(R.color.colorPrimary);
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
                    case "删除":
                        showDialog("删除");
                        break;
                    case "完成":
                        toFinish();
                        break;
                    case "质检":
                        tvRight.setText("完成");
                        adapter.setState("1");
                        adapter.notifyDataSetChanged();
                        break;
                    case "操作":
                        popWindow.showAtLocation(tvRight, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        break;
                }
                break;
        }
    }


    //质检
    private void toFinish() {
        List<StoragingProBean.StoragingProLinesBean> allData = adapter.getAllData();
        List<FinishNumBean> mNumList = new ArrayList<>();
        List<WhAndIdBean> mWhList = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            StoragingProBean.StoragingProLinesBean linesBean = allData.get(i);

            FinishNumBean numBean = new FinishNumBean();
            numBean.setId(linesBean.getId());
            numBean.setQualify_qty(linesBean.getQualify_qty()+"");
            numBean.setUn_qualify_qty(linesBean.getUn_qualify_qty()+"");
            mNumList.add(numBean);

            WhAndIdBean whAndIdBean = new WhAndIdBean();
            whAndIdBean.setId(linesBean.getId());
            whAndIdBean.setWh_id(linesBean.getWh_id());
            mWhList.add(whAndIdBean);
        }

        Gson gson = new Gson();
        String jsonNum = gson.toJson(mNumList);
        String jsonWh = gson.toJson(mWhList);
        map.clear();
        map.put("id", mId);
        map.put("storagingProLineQty", jsonNum);
        map.put("storagingProLines", jsonWh);
        getPresenter().validStoragingPro(userBean.getStaff_token(), map);

    }


    //流程变更弹框
    private void showDialog(final String type) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        switch (type) {
            case "同意":
                builder.setMessage("是否确认审核通过？");
                break;
            case "作废":
                builder.setMessage("是否确认审作废此单据？");
                break;
            case "删除":
                builder.setMessage("是否确认审删除此单据？");
                break;
        }


        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (type) {
                    case "同意":
                        onAgree();
                        break;
                    case "作废":
                        map.clear();
                        map.put("id", mId);
                        getPresenter().invalidStoragingPro(userBean.getStaff_token(), map);
                        break;
                    case "删除":
                        map.clear();
                        map.put("id", mId);
                        getPresenter().delStoragingPro(userBean.getStaff_token(), map);
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

    //审核通过
    private void onAgree() {

        List<StoragingProBean.StoragingProLinesBean> allData = adapter.getAllData();
        List<BarListBean> mBarList = new ArrayList<>();
        List<WhAndIdBean> mWhList = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            StoragingProBean.StoragingProLinesBean linesBean = allData.get(i);

            if (linesBean.getQualify_qty() != linesBean.getCheck_qty()) {
                showNotDialog(linesBean.getItem_name());
                return;
            }
            List<BarBean> barList = linesBean.getBarList();//获取条码信息
            for (int m = 0; m < barList.size(); m++) {
                BarBean barBean = barList.get(m);
                BarListBean mBarBean = new BarListBean();
                mBarBean.setItem_id(barBean.getItem_id());
                mBarBean.setItem_spec(barBean.getItem_spec());
                mBarBean.setItem_name(barBean.getItem_name());
                mBarBean.setItem_bar(barBean.getBar_code());
                mBarBean.setItem_code(barBean.getItem_code());
                mBarBean.setWh_id(linesBean.getWh_id());
                mBarBean.setQty(barBean.getQty() + "");
                mBarList.add(mBarBean);
            }
            WhAndIdBean whAndIdBean = new WhAndIdBean();
            whAndIdBean.setId(linesBean.getId() + "");
            whAndIdBean.setWh_id(linesBean.getWh_id());
            mWhList.add(whAndIdBean);
        }

        Gson gson = new Gson();
        String jsonBar = gson.toJson(mBarList);
        String jsonWh = gson.toJson(mWhList);
        map.clear();
        map.put("id", mId);
        map.put("barList", jsonBar);
        map.put("storagingProLines", jsonWh);
        getPresenter().pastStoragingPro(userBean.getStaff_token(), map);

    }

    //扫码不完整弹框
    private void showNotDialog(String result) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("料品（" + result + "）扫码数量不等于入库数量,请扫码");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();

    }
    //----------------------------------------扫码--------------------------------------------------

    public static final int RC_CAMERA = 0X01;

    //检测拍摄权限 开启扫码
    @AfterPermissionGranted(RC_CAMERA)
    private void checkCameraPermissions(String type) {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(context, perms)) {
            List<ScanInfoBean> scanList = new ArrayList<>();
            ScanInfoBean scanBean = new ScanInfoBean();
            StoragingProBean.StoragingProLinesBean detail = adapter.getItem(mScanPosition);
            scanBean.setItem_code(detail.getItem_code());
            scanBean.setItem_id(detail.getItem_id());
            scanBean.setItem_name(detail.getItem_name());
            scanBean.setItem_spec(detail.getItem_spec());
            scanBean.setSend_qty(detail.getWh_qty());//入库数量
            scanBean.setArrive_qty(detail.getCheck_qty());//核定数量
            scanBean.setBarList(detail.getBarList());//历史条码
            scanList.add(scanBean);
            startInBuyScanFragment(scanList, type,mBean.getBar_lists());
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera), RC_CAMERA, perms);
        }
    }


    @Override
    public FinishPresenter createPresenter() {
        return new FinishPresenter(getApp());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getMsg();
        switch (msg) {
            case "finish_check"://扫码
                List<ScanInfoBean> list = event.getmScanInBuyBean().getList();
                StoragingProBean.StoragingProLinesBean adapterItem = adapter.getItem(mScanPosition);
                for (int j = 0; j < list.size(); j++) {
                    if (adapterItem.getItem_code().equals(list.get(j).getItem_code())) {
                        adapterItem.setCheck_qty(list.get(j).getArrive_qty());
                        adapterItem.setBar_code(list.get(j).getBar_code());
                    }
                }
                adapter.notifyItemChanged(mScanPosition);
                break;
            case "24":
                List<BarBean> barList = event.getmScanListBean().getList();
                adapter.getItem(mScanPosition).setBarList(barList);
                break;
            case "6"://选择仓库
                WareHouseBean houseBean = event.getmWareHouseBean();
                adapter.getItem(mDeportPosition).setWh_name(houseBean.getWarehouse_name());
                adapter.getItem(mDeportPosition).setWh_id(houseBean.getWarehouse_id());
                adapter.notifyDataSetChanged();
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
    public void onQueryStoragingProList(List<StoragingProListBean> data) {

    }


    @Override
    public void onAddStoragingPro(String data) {

    }

    @Override
    public void onPastStoragingPro(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        getData();
    }

    @Override
    public void onInvalidStoragingPro(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        getData();
    }

    @Override
    public void onDelStoragingPro(String data) {
        EventBus.getDefault().post(new FirstEvent("delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onValidStoragingPro(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        getData();
    }
}
