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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.BarBean;
import com.akan.wms.bean.BarVerificationListsBean;
import com.akan.wms.bean.CodeListBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.InforListBean;
import com.akan.wms.bean.MiscRcvBean;
import com.akan.wms.bean.RecordsBean;
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.ShipCodeListBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.RecordAdapter;
import com.akan.wms.mvp.adapter.home.ProduceReceiveDetailAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.ProduceReturnPresenter;
import com.akan.wms.mvp.view.home.IProduceReturnView;
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

public class ProduceReturnDetailFragment extends BaseFragment<IProduceReturnView, ProduceReturnPresenter> implements IProduceReturnView {

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
    @BindView(R.id.tvOneAdd)
    TextView tvOneAdd;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.lineMan)
    View lineMan;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.llMan)
    LinearLayout llMan;
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.tvFiveAdd)
    TextView tvFiveAdd;
    @BindView(R.id.tvSix)
    TextView tvSix;
    @BindView(R.id.tvSevenAdd)
    TextView tvSevenAdd;
    @BindView(R.id.tvSeven)
    TextView tvSeven;
    @BindView(R.id.tvEight)
    TextView tvEight;
    @BindView(R.id.tvScan)
    ImageView tvScan;
    @BindView(R.id.tvThird)
    TextView tvThird;
    @BindView(R.id.llbg)
    LinearLayout llbg;
    @BindView(R.id.addImg)
    ConstraintLayout addImg;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recordRecyclerView)
    RecyclerView recordRecyclerView;

    private List<RecordsBean> listRecord;
    private RecordAdapter adapterRecord;
    private List<InforListBean> list;
    private ProduceReceiveDetailAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mId;
    private String mState;
    private BottomPopWindow popWindow;
    private MiscRcvBean miscShipBean;


    public static ProduceReturnDetailFragment newInstance(String id) {
        Bundle args = new Bundle();
        ProduceReturnDetailFragment fragment = new ProduceReturnDetailFragment();
        fragment.mId = id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_detail_produce_receive;
    }

    @Override
    public void initUI() {
        tvTitle.setText("生产退料详情");
        stateOne.setText("未处理");
        stateTwo.setText("已点收");
        stateThree.setText("已入库");
        lineMan.setVisibility(View.GONE);
        llMan.setVisibility(View.GONE);

        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ProduceReceiveDetailAdapter(context, list);
        recyclerView.setAdapter(adapter);

        //审核记录
        recordRecyclerView.setNestedScrollingEnabled(false);
        listRecord = new ArrayList<>();
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapterRecord = new RecordAdapter(context, this.listRecord);
        recordRecyclerView.setAdapter(adapterRecord);

        popWindow = new BottomPopWindow(getActivity());
        popWindow.setOnItemClickListener(new BottomPopWindow.OnItemClickListener() {
            @Override
            public void setOnItemClick(View v) {
                switch (v.getId()) {
                    case R.id.btOne:
                        showDialog("作废");
                        popWindow.dismiss();
                        break;
                    case R.id.btTwo:
                        checkCameraPermissions("pro_return_in");
                        popWindow.dismiss();
                        break;
                    case R.id.btThree:
                        showDialog("入库");
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
        map.put("rcv_id", mId);
        getPresenter().getMiscRcvDetail(userBean.getStaff_token(), map);
    }

    @Override
    public void OnGetMiscRcvDetail(MiscRcvBean data) {
        miscShipBean = data;
        mState = data.getRcv_status();
        updateState(data.getRcv_status());
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

        tvOneAdd.setText(data.getRcv_no());
        tvOne.setText(data.getStaff_name());
        tvTwo.setText(data.getCreate_time());
        tvThree.setText(data.getDoc_no_name());//单据类型
        tvFive.setText(data.getBenefit_org_name());//受益组织
        tvFiveAdd.setText(data.getBenefit_dept_name());//受益部门
        tvSix.setText(data.getPub_desc_seg2_name());//使用中心
        tvSevenAdd.setText(data.getPub_desc_seg1_name());//负责中心
        tvSeven.setText(data.getPub_desc_seg3_name());//负责部门
        if (TextUtils.isEmpty(data.getMemo())) {
            tvEight.setText("暂无备注");
        } else {
            tvEight.setText(data.getMemo());
        }
        adapter.clear();
        adapter.addAll(data.getInfoList());
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
                tvRight.setText("点收");
                break;
            case "1":
                tvScan.setVisibility(View.GONE);
                tvThird.setVisibility(View.VISIBLE);
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("操作");
                adapter.setShowThree(true, "2");
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
                tvScan.setVisibility(View.GONE);
                tvThird.setVisibility(View.VISIBLE);
                adapter.setShowThree(true, "2");
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
                stateThree.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                break;

        }
    }


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tvScan})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvScan:
                checkCameraPermissions("pro_return_point");
                break;
            case R.id.tvRight:
                String toString = tvRight.getText().toString();
                switch (toString) {
                    case "点收":
                        tvScan.setVisibility(View.VISIBLE);
                        tvThird.setVisibility(View.VISIBLE);
                        tvRight.setText("提交");
                        adapter.setShowThree(true, "1");
                        adapter.notifyDataSetChanged();
                        ToastUtil.showToast(context.getApplicationContext(), "请扫码");
                        break;
                    case "提交":
                        showDialog("点收");
                        break;
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

    //确认配货弹框
    private void showDialog(final String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        switch (type) {
            case "作废":
                builder.setMessage("是否确认作废？");
                break;
            case "入库":
                builder.setMessage("是否确认入库？");
                break;
            case "点收":
                builder.setMessage("是否确认点收？");
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
                        toRefuse();
                        break;
                    case "入库":
                        outDeport();
                        break;
                    case "点收":
                        toWith();
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

    //1:点收2:出库3:拒绝
    private void toWith() {
        map.clear();
        map.put("rcv_id", mId);
        map.put("rcv_status", "1");
        List<InforListBean> allData = adapter.getAllData();
        List<BarBean> barList = miscShipBean.getBarScanList();
        List<ShipCodeListBean> list = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            InforListBean listBean = allData.get(i);
            if (listBean.getScan_num() <= 0) {
                showNotDialog("料品（" + listBean.getInfo_name() + "）扫码核定数量为空，请扫码");
                return;
            }
            ShipCodeListBean bean = new ShipCodeListBean();
            bean.setInfo_id(listBean.getInfo_id());
            bean.setNum(String.valueOf(listBean.getScan_num()));
            ArrayList<CodeListBean> childList = new ArrayList<>();
            for (int m = 0; m < barList.size(); m++) {
                BarBean barBean = barList.get(m);
                if (barBean.getItem_code().equals(listBean.getItem_code())) {
                    CodeListBean codeListBean = new CodeListBean();
                    codeListBean.setInfo_id(listBean.getInfo_id());
                    codeListBean.setBar_code(barBean.getBar_code());
                    codeListBean.setCode(barBean.getItem_code());
                    codeListBean.setCode_num(String.valueOf(barBean.getQty()));
                    codeListBean.setName(barBean.getItem_name());
                    childList.add(codeListBean);
                }

            }
            bean.setCodeList(childList);
            list.add(bean);
        }
        Gson gson = new Gson();
        String toJson = gson.toJson(list);
        map.put("rcvCodeList", toJson);
        getPresenter().updateMiscRcvCode(userBean.getStaff_token(), map);

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

    //同意出库
    private void outDeport() {
        map.clear();
        map.put("rcv_id", mId);
        map.put("rcv_status", "2");
        getPresenter().updateMiscRcvCode(userBean.getStaff_token(), map);
    }

    //作废
    private void toRefuse() {
        map.clear();
        map.put("rcv_id", mId);
        map.put("rcv_status", "3");
        getPresenter().updateMiscRcvCode(userBean.getStaff_token(), map);
    }

    //删除
    private void toDelete() {
        map.clear();
        map.put("rcv_id", mId);
        getPresenter().deleteMiscRcv(userBean.getStaff_token(), map);
    }

    //----------------------------------------扫码--------------------------------------------------
    public static final int RC_CAMERA = 0X01;

    //检测拍摄权限
    @AfterPermissionGranted(RC_CAMERA)
    private void checkCameraPermissions(String type) {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(context, perms)) {
            switch (type) {
                case "pro_return_point":
                    List<InforListBean> allData = adapter.getAllData();
                    List<ScanInfoBean> scanList = new ArrayList<>();
                    for (int i = 0; i < allData.size(); i++) {
                        ScanInfoBean scanBean = new ScanInfoBean();
                        InforListBean detail = allData.get(i);
                        scanBean.setItem_spec(detail.getInfo_spec());
                        scanBean.setItem_code(detail.getItem_code());
                        scanBean.setItem_id(detail.getItem_id());
                        scanBean.setItem_name(detail.getInfo_name());
                        scanBean.setSend_qty(detail.getNumber());//申请数量
                        scanBean.setArrive_qty(detail.getScan_num());//核定数量
                        scanBean.setBarList(miscShipBean.getBarScanList());//历史条码
                        scanList.add(scanBean);
                    }
                    startInBuyScanFragment(scanList, type,new ArrayList<BarVerificationListsBean>());
                    break;
                case "pro_return_in":
                    List<InforListBean> allData1 = adapter.getAllData();
                    List<ScanInfoBean> scanList1 = new ArrayList<>();
                    for (int i = 0; i < allData1.size(); i++) {
                        ScanInfoBean scanBean = new ScanInfoBean();
                        InforListBean detail = allData1.get(i);
                        scanBean.setItem_spec(detail.getInfo_spec());
                        scanBean.setItem_code(detail.getItem_code());
                        scanBean.setItem_id(detail.getItem_id());
                        scanBean.setItem_name(detail.getInfo_name());
                        scanBean.setSend_qty(detail.getNumber());//申请数量
                        scanBean.setArrive_qty(detail.getCheck_num());//核定数量
                        scanBean.setBarList(miscShipBean.getBarScanList());//历史条码
                        scanList1.add(scanBean);
                    }
                    startInBuyScanFragment(scanList1, type,miscShipBean.getBarList());
                    break;
            }

        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera), RC_CAMERA, perms);
        }
    }


//--------------------------------------------------------------------------------------------------

    @Override
    public ProduceReturnPresenter createPresenter() {
        return new ProduceReturnPresenter(getApp());
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
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    //扫码数据返回
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getMsg();
        switch (msg) {
            case "pro_return_point"://点收扫码
                List<ScanInfoBean> listTwo = event.getmScanInBuyBean().getList();
                List<InforListBean> allData = adapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    InforListBean listBean = allData.get(i);
                    for (int m = 0; m < listTwo.size(); m++) {
                        if (listBean.getItem_code().equals(listTwo.get(m).getItem_code())) {
                            listBean.setScan_num(listTwo.get(m).getArrive_qty());
                            listBean.setBar_code(listTwo.get(m).getBar_code());
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            case "24":
                List<BarBean> barBeanList = event.getmScanListBean().getList();
                miscShipBean.setBarScanList(barBeanList);
                break;

        }

    }

    private List<BarBean> barList;//扫描返回条码列表

    @Override
    public void onGetMiscRcvList(List<MiscRcvBean> data) {

    }


    @Override
    public void OnInsertMiscRcv(String data) {

    }

    @Override
    public void OnUpdateMiscRcvCode(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        getData();
    }

    @Override
    public void OnDeleteMiscRcv(String data) {
        EventBus.getDefault().post(new FirstEvent("delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }
}

