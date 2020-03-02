package com.akan.wms.mvp.fragment.in;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.AddStoragingProBean;
import com.akan.wms.bean.BarBean;
import com.akan.wms.bean.BarListBean;
import com.akan.wms.bean.BarVerificationListsBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.ProductionOrderBean;
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.StoragingProBean;
import com.akan.wms.bean.StoragingProListBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.mvp.adapter.home.FinishAddAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.FinishPresenter;
import com.akan.wms.mvp.view.home.IFinishView;
import com.akan.wms.util.LoadingUtil;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.akan.wms.util.keyback.FragmentBackHandler;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class FinishAddFragment extends BaseFragment<IFinishView, FinishPresenter> implements IFinishView, FragmentBackHandler {

    Unbinder unbinder;
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
    @BindView(R.id.tvSix)
    EditText tvSix;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.llAdd)
    LinearLayout llAdd;


    private List<ProductionOrderBean> list;
    private FinishAddAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private int mDeletePosition;
    private int mDeportPosition;
    private int mScanPosition;


    public static FinishAddFragment newInstance() {
        Bundle args = new Bundle();
        FinishAddFragment fragment = new FinishAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_finish_add;
    }

    @Override
    public void initUI() {
        tvTitle.setText("新增成品入库单");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("提交");
        list = new ArrayList<>();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new FinishAddAdapter(context, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnStockListener(new FinishAddAdapter.OnSelectClickListener() {


            @Override
            public void onScan(int position) {
                mScanPosition = position;
                checkCameraPermissions("finish_add");
            }

            @Override
            public void onChooseDeport(int position) {
                mDeportPosition = position;
                startChooseDeportFragment();
            }

            @Override
            public void onDelete(int position) {
                mDeletePosition = position;
                showDialog("删除");
            }

        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        tvOne.setText(userBean.getStaff_name());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTwo.setText(str);
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
            ProductionOrderBean detail = adapter.getItem(mScanPosition);
            scanBean.setItem_code(detail.getItem_code());
            scanBean.setItem_id(detail.getItem_id());
            scanBean.setItem_name(detail.getItem_name());
            scanBean.setItem_spec(detail.getItem_spec());
            scanBean.setSend_qty((int)detail.getComplete_qty());//可入库数量
            scanBean.setArrive_qty(detail.getSend_qty());//扫码入库数量
            scanBean.setBarList(detail.getBarList());//历史条码
            scanList.add(scanBean);
            startInBuyScanFragment(scanList, type,new ArrayList<BarVerificationListsBean>());
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera), RC_CAMERA, perms);
        }
    }

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.llAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                showCloseDialog();
                break;
            case R.id.tvRight:
                if (adapter.getAllData().size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请添加生产订单");
                    return;
                }
                showDialog("完成");
                break;
            case R.id.llAdd:
                startFinishChooseFragment();
                break;
        }
    }


    //操作确认弹框
    private void showDialog(final String type) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        switch (type) {
            case "完成":
                builder.setMessage("是否确认完成？");
                break;
            case "删除":
                builder.setMessage("是否确认删除？");
                break;

        }

        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (type) {
                    case "完成":
                        toCommit();
                        break;
                    case "删除":
                        adapter.remove(mDeletePosition);
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

    //提交数据
    private void toCommit() {
        AddStoragingProBean mBean = new AddStoragingProBean();
        mBean.setRemark(tvSix.getText().toString());
        List<ProductionOrderBean> allData = adapter.getAllData();
        List<AddStoragingProBean.StoragingProLinesBean> mList = new ArrayList<>();
        List<BarListBean> mBarList = new ArrayList<>();
        for (int j = 0; j < allData.size(); j++) {
            ProductionOrderBean orderBean = allData.get(j);
            if (orderBean.getSend_qty() <= 0) {
                showNotDialog("料品（" + orderBean.getItem_name() + "）请扫码加入入库数量");
                return;
            }
            if (orderBean.getSend_qty() > orderBean.getQualified_qty()) {
                showNotDialog("料品（" + orderBean.getItem_name() + "）入库数量大于可入库数量,请修改条码");
                return;
            }
            if (TextUtils.isEmpty(orderBean.getWh_id())) {
                ToastUtil.showToast(context.getApplicationContext(), "请选择仓库");
                return;
            }
            loading = LoadingUtil.createLoading(getActivity(), "加载中...");


            AddStoragingProBean.StoragingProLinesBean mChildBean = new AddStoragingProBean.StoragingProLinesBean();
            mChildBean.setItem_id(orderBean.getItem_id());
            mChildBean.setPro_id(orderBean.getId());
            mChildBean.setWh_id(orderBean.getWh_id());
            mChildBean.setWh_qty(orderBean.getSend_qty() + "");
            mChildBean.setBar_code(orderBean.getBar_code());
            mList.add(mChildBean);

            List<BarBean> barList = orderBean.getBarList();
            for (int m = 0; m < barList.size(); m++) {
                BarBean barBean = barList.get(m);
                BarListBean mBarBean = new BarListBean();
                mBarBean.setItem_bar(barBean.getBar_code());
                mBarBean.setWh_id(orderBean.getWh_id());
                mBarBean.setQty(barBean.getQty() + "");
                mBarBean.setItem_id(barBean.getItem_id());
                mBarBean.setItem_spec(barBean.getItem_spec());
                mBarBean.setItem_name(barBean.getItem_name());
                mBarBean.setItem_code(barBean.getItem_code());
                mBarList.add(mBarBean);
            }
        }

        mBean.setStoraging_pro_lines(mList);
        Gson gson = new Gson();
        String json = gson.toJson(mBean);
        String barJson = gson.toJson(mBarList);
        map.put("addStoragingPro", json);
        map.put("barList", barJson);
        getPresenter().addStoragingPro(userBean.getStaff_token(), map);
    }

    //扫码不完整弹框
    private void showNotDialog(String result) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(result);
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();

    }

    @Override
    public FinishPresenter createPresenter() {
        return new FinishPresenter(getApp());
    }


    private Dialog loading;

    @Override
    public void showProgress() {


    }

    @Override
    public void onCompleted() {
    }


    @Override
    public void onError(Throwable e) {
        LoadingUtil.closeDialog(loading);
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());

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
        String msg = event.getmMsg();
        switch (msg) {
            case "18":
                ProductionOrderBean produceBean = event.getmProductionOrderBean();
                List<ProductionOrderBean> allData = adapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    if (produceBean.getDoc_no().equals(allData.get(i).getDoc_no())) {
                        ToastUtil.showToast(context.getApplicationContext(), "此单据已被选择");
                        return;
                    }
                }
                adapter.add(produceBean);
                break;
            case "6"://选择仓库
                WareHouseBean houseBean = event.getmWareHouseBean();
                adapter.getItem(mDeportPosition).setWh_name(houseBean.getWarehouse_name());
                adapter.getItem(mDeportPosition).setWh_id(houseBean.getWarehouse_id());
                adapter.notifyDataSetChanged();
                break;
            case "scan_over":
                List<ProductionOrderBean> list1 = event.getmScanFinishBean().getList();
                adapter.getItem(mScanPosition).setSend_qty(list1.get(0).getSend_qty());
                adapter.getItem(mScanPosition).setItem_code(list1.get(0).getItem_code());
                adapter.notifyDataSetChanged();
                break;

            case "finish_add"://扫码
                List<ScanInfoBean> list = event.getmScanInBuyBean().getList();
                ProductionOrderBean item = adapter.getItem(mScanPosition);
                for (int j = 0; j < list.size(); j++) {
                    if (item.getItem_code().equals(list.get(j).getItem_code())) {
                        item.setSend_qty(list.get(j).getArrive_qty());
                        item.setBar_code(list.get(j).getBar_code());
                    }
                }
                adapter.notifyItemChanged(mScanPosition);
                break;
            case "24":
                List<BarBean> barList = event.getmScanListBean().getList();
                adapter.getItem(mScanPosition).setBarList(barList);
                break;


        }
    }



    @Override
    public void onQueryStoragingProList(List<StoragingProListBean> data) {

    }

    @Override
    public void onQueryStoragingPro(StoragingProBean data) {

    }

    @Override
    public void onAddStoragingPro(String data) {
        LoadingUtil.closeDialog(loading);
        ToastUtil.showToast(context.getApplicationContext(), "添加成功");
        EventBus.getDefault().post(new FirstEvent("refresh"));
        finish();
    }

    @Override
    public void onPastStoragingPro(String data) {

    }

    @Override
    public void onInvalidStoragingPro(String data) {

    }

    @Override
    public void onDelStoragingPro(String data) {

    }

    @Override
    public void onValidStoragingPro(String data) {

    }

    @Override
    public boolean onBackPressed() {
        showCloseDialog();
        return true;
    }

    //操作确认弹框
    private void showCloseDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.point);
        builder.setMessage(R.string.close_sure);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();

    }
}
