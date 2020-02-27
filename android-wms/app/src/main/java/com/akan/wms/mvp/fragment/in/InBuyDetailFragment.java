package com.akan.wms.mvp.fragment.in;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
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
import com.akan.wms.bean.AddDeliverGoodsBean;
import com.akan.wms.bean.BarBean;
import com.akan.wms.bean.BarListBean;
import com.akan.wms.bean.BarListInBuyPointBean;
import com.akan.wms.bean.BarVerificationListsBean;
import com.akan.wms.bean.DeliverGoodsBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.OperatorBean;
import com.akan.wms.bean.PurchasesBean;
import com.akan.wms.bean.RecordsBean;
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.SupplierReceivesBean;
import com.akan.wms.bean.SupplierReceivesBeanDetail;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.mvp.adapter.RecordAdapter;
import com.akan.wms.mvp.adapter.home.InBuyDetailAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.InBuyPresenter;
import com.akan.wms.mvp.view.home.IInBuyView;
import com.akan.wms.util.LoadingUtil;
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

public class InBuyDetailFragment extends BaseFragment<IInBuyView, InBuyPresenter> implements IInBuyView {

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
    @BindView(R.id.ivTwo)
    ImageView ivTwo;
    @BindView(R.id.ivThree)
    ImageView ivThree;
    @BindView(R.id.ivFour)
    ImageView ivFour;
    @BindView(R.id.lineOne)
    View lineOne;
    @BindView(R.id.lineTwo)
    View lineTwo;
    @BindView(R.id.lineThree)
    View lineThree;
    @BindView(R.id.stateOne)
    TextView stateOne;
    @BindView(R.id.stateTwo)
    TextView stateTwo;
    @BindView(R.id.stateThree)
    TextView stateThree;
    @BindView(R.id.stateFour)
    TextView stateFour;
    @BindView(R.id.relativeLayout3)
    ConstraintLayout relativeLayout3;
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
    @BindView(R.id.recordRecyclerView)
    RecyclerView recordRecyclerView;
    @BindView(R.id.lineU9)
    View lineU9;
    @BindView(R.id.llU9)
    LinearLayout llU9;
    @BindView(R.id.tvU9No)
    TextView tvU9No;

    private List<RecordsBean> listRecord;
    private RecordAdapter adapterRecord;
    private List<PurchasesBean> list;
    private InBuyDetailAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mId;
    private BottomPopWindow popWindow;
    private DeliverGoodsBean mBean;
    private int mScanPosition;
    private int mPosition;
    private int mChildPosition;
    private int mWhPosition;
    private int mWhChildPosition;

    public static InBuyDetailFragment newInstance(String id) {
        Bundle args = new Bundle();
        InBuyDetailFragment fragment = new InBuyDetailFragment();
        fragment.mId = id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_detail_in_buy;
    }

    @Override
    public void initUI() {
        tvTitle.setText("送货单详情");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new InBuyDetailAdapter(context, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnStockListener(new InBuyDetailAdapter.onSelectStockClickListener() {
            @Override
            public void onScan(int position, String state) {
                switch (state) {
                    case "1":
                        mScanPosition = position;
                        checkCameraPermissions("in_buy_point");
                        break;
                    case "3":
                        mScanPosition = position;
                        checkCameraPermissions("in_buy_agree");
                        break;
                }

            }

            @Override
            public void onChooseDeport(int position, int childPosition) {
                mPosition = position;
                mChildPosition = childPosition;
                startChooseDeportFragment();
            }

            @Override
            public void onChooseWhManager(int position, int childPosition) {
                mWhPosition = position;
                mWhChildPosition = childPosition;
                startProduceChooseFragment("2");

            }
        });

        //审核记录
        recordRecyclerView.setNestedScrollingEnabled(false);
        listRecord = new ArrayList<>();
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapterRecord = new RecordAdapter(context, this.listRecord);
        recordRecyclerView.setAdapter(adapterRecord);

        initPop();
    }

    //初始化底部弹窗
    private void initPop() {
        popWindow = new BottomPopWindow(context);
        popWindow.setOneName("拒绝");
        popWindow.setTwoGone();
        popWindow.setOnItemClickListener(new BottomPopWindow.OnItemClickListener() {
            @Override
            public void setOnItemClick(View v) {
                switch (v.getId()) {
                    case R.id.btOne:
                        showDialog("拒绝");
                        popWindow.dismiss();
                        break;
                    case R.id.btTwo:
                        checkCameraPermissions("in_buy_agree");
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
        map.clear();
        map.put("id", mId);
        getPresenter().querySupplierDeliverById(userBean.getStaff_token(), map);
    }

    @Override
    public void OnQuerySupplierDeliverById(DeliverGoodsBean data) {
        LoadingUtil.closeDialog(loading);
        mBean = data;
        updateState(data.getStatus());
        tvOne.setText(data.getDoc_no());
        tvTwo.setText(data.getCreator_name());
        tvThree.setText(data.getSend_time());
        tvFour.setText(data.getSupplier_name());
        tvFive.setText(data.getSupplier_id());
        adapter.clear();
        adapter.addAll(data.getPurchases());
        adapter.notifyDataSetChanged();
        adapterRecord.clear();
        adapterRecord.addAll(data.getRecordsBeans());
        adapterRecord.notifyDataSetChanged();

    }

    //点收成功
    @Override
    public void onAddReceiveGoods(String data) {
        LoadingUtil.closeDialog(loading);
        upDetail(data);
    }

    //质检成功
    @Override
    public void qualityReceiveGoods(String data) {
        LoadingUtil.closeDialog(loading);
        upDetail(data);
    }

    //同意入库
    @Override
    public void pastWh(String data) {
        LoadingUtil.closeDialog(loading);
        upDetail(data);
    }

    //拒绝入库
    @Override
    public void rejectWh(String data) {
        LoadingUtil.closeDialog(loading);
        upDetail(data);
    }

    //流程变更并更新详情和列表
    private void upDetail(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent("refresh"));
        getData();
    }


    //流程变更弹框
    private void showDialog(final String type) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        switch (type) {
            case "拒绝":
                builder.setMessage("是否拒绝入库？");
                break;
            case "同意":
                builder.setMessage("是否同意入库？");
                break;
            case "提交":
                builder.setMessage("是否确认点收？");
                break;
            case "完成":
                builder.setMessage("是否确认质检？");
                break;
        }
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (type) {
                    case "拒绝":
                        refuseOut();
                        break;
                    case "同意":
                        agreeOut();
                        break;
                    case "提交":
                        toAccept();
                        break;
                    case "完成":
                        toQuality();
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

    //拒绝出库
    private void refuseOut() {
        map.clear();
        map.put("id", mBean.getId() + "");
        getPresenter().rejectWh(userBean.getStaff_token(), map);

    }

    //点收
    private void toAccept() {
        AddDeliverGoodsBean bean = new AddDeliverGoodsBean();
        bean.setId(mBean.getId());
        bean.setOrg_id(userBean.getOrg_id());
        bean.setSupplier_id(mBean.getSupplier_id());
        List<PurchasesBean> allData = adapter.getAllData();
        List<SupplierReceivesBean> mList = new ArrayList<>();//储存料品信息
        List<BarListInBuyPointBean> mBarList = new ArrayList<>();//储存条码信息
        for (int i = 0; i < allData.size(); i++) {
            List<SupplierReceivesBeanDetail> receives = allData.get(i).getSupplier_receives();//获取料品信息
            String wh_id = receives.get(i).getWh_id();
            for (int j = 0; j < receives.size(); j++) {
                SupplierReceivesBean receivesBean = new SupplierReceivesBean();
                SupplierReceivesBeanDetail beanDetail = receives.get(j);
                int arriveNum = beanDetail.getArrive_qty();//实收数量
                if (arriveNum <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.actual_more_send));
                    return;
                }
                if (TextUtils.isEmpty(beanDetail.getWh_id())) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_choose_deport));
                    return;
                }
                if (TextUtils.isEmpty(beanDetail.getWh_staff_id())) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_choose_librarian));
                    return;
                }
                receivesBean.setPur_id(beanDetail.getPur_id());
                receivesBean.setId(beanDetail.getId());
                receivesBean.setArrive_qty(String.valueOf(arriveNum));
                receivesBean.setWh_staff_id(beanDetail.getWh_staff_id());
                receivesBean.setWh_staff_name(beanDetail.getWh_staff_name());
                receivesBean.setItem_id(beanDetail.getItem_id());
                receivesBean.setWh_id(beanDetail.getWh_id());
                mList.add(receivesBean);
            }

            List<BarBean> barList = allData.get(i).getBarList();//获取条码信息
            if (barList == null) {
                ToastUtil.showToast(context.getApplicationContext(), "请先扫码点收！");
                return;
            }

            for (int m = 0; m < barList.size(); m++) {
                BarBean barBean = barList.get(m);
                BarListInBuyPointBean mBarBean = new BarListInBuyPointBean();
                mBarBean.setItem_code(barBean.getItem_code());
                mBarBean.setQty(String.valueOf(barBean.getQty()));
                mBarBean.setItem_name(barBean.getItem_name());
                mBarBean.setWh_id(wh_id);
                mBarBean.setItem_id(barBean.getItem_id());
                mBarBean.setItem_spec(barBean.getItem_spec());
                mBarBean.setItem_bar(barBean.getBar_code());
                mBarList.add(mBarBean);
            }

        }
        loading = LoadingUtil.createLoading(getActivity(), "点收中...");
        bean.setSupplier_receives(mList);
        Gson gson = new Gson();
        String json = gson.toJson(bean);
        String jsonBar = gson.toJson(mBarList);
        map.clear();
        map.put("staff_id", userBean.getStaff_id());
        map.put("org_id", userBean.getOrg_id());
        map.put("addReceiveGoods", json);
        map.put("barList", jsonBar);
        getPresenter().addReceiveGoods(userBean.getStaff_token(), map);
    }


    //质检
    private void toQuality() {
        AddDeliverGoodsBean bean = new AddDeliverGoodsBean();
        bean.setId(mBean.getId());
        bean.setOrg_id(mBean.getOrg_id());
        bean.setSupplier_id(mBean.getSupplier_id());
        List<PurchasesBean> allData = adapter.getAllData();
        List<SupplierReceivesBean> mList = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            List<SupplierReceivesBeanDetail> receives = allData.get(i).getSupplier_receives();
            for (int j = 0; j < receives.size(); j++) {
                SupplierReceivesBean receivesBean = new SupplierReceivesBean();
                SupplierReceivesBeanDetail beanDetail = receives.get(j);
                int sendNum = beanDetail.getSend_qty();//送货数量
                int arriveNum = beanDetail.getArrive_qty();//实收数量
                int passNum = beanDetail.getQualified_qty();//合格数量
                int notPassNum = beanDetail.getUnqualified_qty();//不合格数量

                if (TextUtils.isEmpty(beanDetail.getWh_id())) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_choose_deport));
                    return;
                }
                if (TextUtils.isEmpty(beanDetail.getWh_staff_id())) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_choose_operator));
                    return;
                }
                if (arriveNum != (passNum + notPassNum)) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.check_actual_different));
                    return;
                }
                receivesBean.setArrive_qty(String.valueOf(arriveNum));
                receivesBean.setQualified_qty(String.valueOf(passNum));
                receivesBean.setUnqualified_qty(String.valueOf(notPassNum));
                receivesBean.setId(beanDetail.getId());
                receivesBean.setItem_id(beanDetail.getItem_id());
                receivesBean.setPur_id(beanDetail.getPur_id());
                receivesBean.setWh_id(beanDetail.getWh_id());
                receivesBean.setWh_staff_id(beanDetail.getWh_staff_id());
                mList.add(receivesBean);
            }
        }

        loading = LoadingUtil.createLoading(getActivity(), "质检中...");
        bean.setSupplier_receives(mList);
        Gson gson = new Gson();
        String json = gson.toJson(bean);
        map.clear();
        map.put("staff_id", userBean.getStaff_id());
        map.put("org_id", userBean.getOrg_id());
        map.put("qualityReceiveGoods", json);
        getPresenter().qualityReceiveGoods(userBean.getStaff_token(), map);
    }


    //同意入库
    private void agreeOut() {
        List<PurchasesBean> allData = adapter.getAllData();
        List<BarListBean> mBarList = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            String wh_id = "";
            PurchasesBean purchasesBean = allData.get(i);
            String pur_id = purchasesBean.getPur_id();
            List<SupplierReceivesBeanDetail> receives = purchasesBean.getSupplier_receives();
            if (receives.size() > 0) {
                wh_id = receives.get(0).getWh_id();
            }
            List<BarBean> barList = allData.get(i).getBarList();
            if (barList == null || barList.size() <= 0) {
                map.clear();
                map.put("id", mBean.getId());
                getPresenter().pastWh(userBean.getStaff_token(), map);
                return;
            }
            for (int j = 0; j < barList.size(); j++) {
                BarBean barBean = barList.get(j);
                BarListBean mBarBean = new BarListBean();
                mBarBean.setPur_id(pur_id);
                mBarBean.setWh_id(wh_id);
                mBarBean.setItem_code(barBean.getItem_code());
                mBarBean.setQty(String.valueOf(barBean.getQty()));
                mBarBean.setItem_spec(barBean.getItem_spec());
                mBarBean.setItem_id(barBean.getItem_id());
                mBarBean.setItem_name(barBean.getItem_name());
                mBarBean.setItem_bar(barBean.getBar_code());
                mBarList.add(mBarBean);
            }
        }
        map.clear();
        Gson gson = new Gson();
        String jsonBar = gson.toJson(mBarList);
        map.put("id", mBean.getId());
        map.put("staff_id", userBean.getStaff_id());
        map.put("org_id", userBean.getOrg_id());
        map.put("barList", jsonBar);
        getPresenter().pastWh(userBean.getStaff_token(), map);
    }

    //扫码不完整弹框
    private void showNotDialog(String result) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("请先扫码");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();

    }

    //按钮点击事件
    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                String toString = tvRight.getText().toString();
                switch (toString) {
                    case "点收":
                        tvRight.setText("提交");
                        adapter.setState("1", true);
                        adapter.notifyDataSetChanged();
                        break;
                    case "提交":
                        showDialog("提交");
                        break;
                    case "质检":
                        tvRight.setText("完成");
                        adapter.setState("2", true);
                        adapter.notifyDataSetChanged();
                        break;
                    case "完成":
                        showDialog("完成");
                        break;
                    case "操作":
                        popWindow.showAtLocation(tvRight, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        break;
                }

                break;
        }
    }


    //----------------------------------------扫码--------------------------------------------------

    public static final int RC_CAMERA = 0X01;

    //检测拍摄权限 开启扫码
    @AfterPermissionGranted(RC_CAMERA)
    private void checkCameraPermissions(String type) {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(context, perms)) {
            switch (type) {
                case "in_buy_point":
                    PurchasesBean adapterItem = adapter.getItem(mScanPosition);
                    List<SupplierReceivesBeanDetail> supplierReceives = adapterItem.getSupplier_receives();
                    List<ScanInfoBean> scanList = new ArrayList<>();
                    for (int i = 0; i < supplierReceives.size(); i++) {
                        ScanInfoBean scanBean = new ScanInfoBean();
                        SupplierReceivesBeanDetail detail = supplierReceives.get(i);
                        scanBean.setItem_code(detail.getItem_code());
                        scanBean.setItem_id(detail.getItem_id());
                        scanBean.setItem_name(detail.getItem_name());
                        scanBean.setItem_spec(detail.getItem_spec());
                        scanBean.setSend_qty(detail.getSend_qty());//送货数量
                        scanBean.setArrive_qty(detail.getArrive_qty());//实收数量
                        scanBean.setBarList(adapterItem.getBarList());//历史条码
                        scanList.add(scanBean);
                    }
                    startInBuyScanFragment(scanList, type, new ArrayList<BarVerificationListsBean>());
                    break;
                case "in_buy_agree":
                    PurchasesBean adapterItemTwo = adapter.getItem(mScanPosition);
                    List<SupplierReceivesBeanDetail> supplierReceives1 = adapterItemTwo.getSupplier_receives();
                    List<ScanInfoBean> scanListTwo = new ArrayList<>();
                    for (int j = 0; j < supplierReceives1.size(); j++) {
                        SupplierReceivesBeanDetail detail = supplierReceives1.get(j);

                        ScanInfoBean scanBean = new ScanInfoBean();
                        scanBean.setItem_code(detail.getItem_code());
                        scanBean.setItem_id(detail.getItem_id());
                        scanBean.setItem_name(detail.getItem_name());
                        scanBean.setItem_spec(detail.getItem_spec());
                        scanBean.setArrive_qty(detail.getQualified_qty());//合格数量
                        scanBean.setIn_qty(detail.getIn_qty());//核定数量
                        scanBean.setBarList(adapterItemTwo.getBarList());//历史条码
                        scanListTwo.add(scanBean);

                    }
                    startInBuyScanFragment(scanListTwo, type, new ArrayList<BarVerificationListsBean>());
                    break;


            }

        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera), RC_CAMERA, perms);
        }
    }


    @Override
    public InBuyPresenter createPresenter() {
        return new InBuyPresenter(getApp());
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
            case "6"://选择仓库
                WareHouseBean houseBean = event.getmWareHouseBean();
                adapter.getItem(mPosition).getSupplier_receives().get(mChildPosition).setWh_id(houseBean.getWarehouse_id());
                adapter.getItem(mPosition).getSupplier_receives().get(mChildPosition).setWh_name(houseBean.getWarehouse_name());
                adapter.notifyDataSetChanged();
                break;

            case "in_buy_point"://点收扫码
                List<ScanInfoBean> list = event.getmScanInBuyBean().getList();
                List<SupplierReceivesBeanDetail> receives = adapter.getItem(mScanPosition).getSupplier_receives();
                for (int i = 0; i < receives.size(); i++) {
                    for (int j = 0; j < list.size(); j++) {
                        if (receives.get(i).getItem_code().equals(list.get(j).getItem_code())) {
                            receives.get(i).setArrive_qty(list.get(j).getArrive_qty());
                            receives.get(i).setBar_code(list.get(j).getBar_code());
                        }
                    }
                }
                adapter.notifyItemChanged(mScanPosition);
                break;
            case "in_buy_agree"://入库扫码
                List<ScanInfoBean> listTwo = event.getmScanInBuyBean().getList();
                List<PurchasesBean> allData = adapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    List<SupplierReceivesBeanDetail> supplier_receives = allData.get(i).getSupplier_receives();
                    for (int j = 0; j < supplier_receives.size(); j++) {
                        for (int m = 0; m < listTwo.size(); m++) {
                            if (supplier_receives.get(j).getItem_code().equals(listTwo.get(m).getItem_code())) {
                                supplier_receives.get(j).setIn_qty(listTwo.get(m).getIn_qty());
                                supplier_receives.get(j).setBar_code(listTwo.get(m).getBar_code());
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            case "24":
                List<BarBean> barList = event.getmScanListBean().getList();
                adapter.getItem(mScanPosition).setBarList(barList);
                adapter.notifyDataSetChanged();
                break;
            case "9"://选择库管员
                OperatorBean operatorBean = event.getmOperatorBean();
                adapter.getItem(mWhPosition).getSupplier_receives().get(mWhChildPosition).setWh_staff_name(operatorBean.getOperator_name());
                adapter.getItem(mWhPosition).getSupplier_receives().get(mWhChildPosition).setWh_staff_id(operatorBean.getOperator_code());
                adapter.notifyDataSetChanged();
                break;

        }
    }

    @Override
    public void OnAddDeliverGoods(String data) {

    }

    @Override
    public void OnQueryDeliverGoodsLists(List<DeliverGoodsBean> data) {

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

    //更新顶部状态
    private void updateState(String mState) {
        switch (mState) {
            case "0":
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("点收");
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                break;
            case "1":
                adapter.setState("1", false);
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("质检");
                ivTwo.setImageResource(R.drawable.state_h);
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                ivTwo.setScaleY(1.5f);
                ivTwo.setScaleX(1.5f);
                lineOne.setBackgroundResource(R.color.colorPrimary);
                stateTwo.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                break;
            case "2":
                adapter.setState("3", true);
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("操作");
                ivTwo.setImageResource(R.drawable.state_h);
                ivThree.setImageResource(R.drawable.state_h);
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                ivTwo.setScaleY(1.5f);
                ivTwo.setScaleX(1.5f);
                ivThree.setScaleY(1.5f);
                ivThree.setScaleX(1.5f);
                lineOne.setBackgroundResource(R.color.colorPrimary);
                lineTwo.setBackgroundResource(R.color.colorPrimary);
                stateTwo.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                stateThree.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                break;
            case "3":
                adapter.setState("2", false);
                tvRight.setVisibility(View.GONE);
                ivTwo.setImageResource(R.drawable.state_h);
                ivThree.setImageResource(R.drawable.state_h);
                ivFour.setImageResource(R.drawable.state_h);
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                ivTwo.setScaleY(1.5f);
                ivTwo.setScaleX(1.5f);
                ivThree.setScaleY(1.5f);
                ivThree.setScaleX(1.5f);
                ivFour.setScaleY(1.5f);
                ivFour.setScaleX(1.5f);
                lineOne.setBackgroundResource(R.color.colorPrimary);
                lineTwo.setBackgroundResource(R.color.colorPrimary);
                lineThree.setBackgroundResource(R.color.colorPrimary);
                stateTwo.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                stateThree.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                stateFour.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                break;

        }
    }


}
