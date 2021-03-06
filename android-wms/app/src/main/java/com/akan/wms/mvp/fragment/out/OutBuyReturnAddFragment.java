package com.akan.wms.mvp.fragment.out;

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
import com.akan.wms.bean.AddBuyRturnGoodsBean;
import com.akan.wms.bean.AddRtnedGoodsBean;
import com.akan.wms.bean.BarBean;
import com.akan.wms.bean.BarListBean;
import com.akan.wms.bean.BarVerificationListsBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.MfcBean;
import com.akan.wms.bean.OperatorBean;
import com.akan.wms.bean.OutSaleRtuBean;
import com.akan.wms.bean.RtnLinesBean;
import com.akan.wms.bean.RtnedGoodsBean;
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.SupplierBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.mvp.adapter.home.OutBuyReturnAddAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.OutBuyReturnPresenter;
import com.akan.wms.mvp.view.home.IOutBuyReturnView;
import com.akan.wms.util.LoadingUtil;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.akan.wms.util.keyback.FragmentBackHandler;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

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

public class OutBuyReturnAddFragment extends BaseFragment<IOutBuyReturnView, OutBuyReturnPresenter> implements IOutBuyReturnView, FragmentBackHandler {

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
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.llAdd)
    LinearLayout llAdd;


    private List<OutSaleRtuBean> list;
    private OutBuyReturnAddAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int mPosition;
    private int mChildPosition;
    private int mManagerPosition;
    private int mManagerChildPosition;
    private int mDeletePosition;
    private int mMfcPosition;
    private int mMfcChildPosition;
    private String mSupplier_id = "";//供应商code
    private int mScanPosition;

    public static OutBuyReturnAddFragment newInstance() {
        Bundle args = new Bundle();
        OutBuyReturnAddFragment fragment = new OutBuyReturnAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_out_buy_return_add;
    }

    @Override
    public void initUI() {
        tvTitle.setText("新增采购退货单");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new OutBuyReturnAddAdapter(context, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startChooseBuyReturnChildFragment(mChooseBean, "1", "add");
            }
        });
        adapter.setOnStockListener(new OutBuyReturnAddAdapter.onSelectStockClickListener() {
            @Override
            public void onScan(int position) {
                mScanPosition = position;
                checkCameraPermissions("out_buy_add");
            }

            @Override
            public void onChooseDeport(int position, int childPositon) {
                //选择仓库
                mPosition = position;
                mChildPosition = childPositon;
                startChooseDeportFragment();
            }

            @Override
            public void onChooseWhMAnager(int position, int childPositon) {
                //选择库管员
                mManagerPosition = position;
                mManagerChildPosition = childPositon;
                startProduceChooseFragment("2");
            }

            @Override
            public void onChooseMfc(int position, int childPositon, String item_id) {
                mMfcPosition = position;
                mMfcChildPosition = childPositon;
                startChooseMfcFragment(item_id);
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


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.llAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                showCloseDialog();
                break;
            case R.id.tvRight:
                if (adapter.getAllData().size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请添加退货申请单");
                    return;
                }
                showDialog("完成");
                break;
            case R.id.llAdd:
                startChooseBuyReturnListFragment("", "add");
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
            case "供应商":
                builder.setMessage("重新选择供应商会清空已选择的送货单,是否继续？");
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
                    case "供应商":
                        startChooseSupplierFragment();
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
        List<OutSaleRtuBean> allData = adapter.getAllData();
        AddBuyRturnGoodsBean mAddBean = new AddBuyRturnGoodsBean();
        ArrayList<AddBuyRturnGoodsBean.RtnedLinesBean> mList = new ArrayList<>();//退货清单列表
        List<BarListBean> mBarList = new ArrayList<>();//条码列表

        for (int i = 0; i < allData.size(); i++) {
            String wh_id = "";
            OutSaleRtuBean shipPlanBean = allData.get(i);
            AddBuyRturnGoodsBean.RtnedLinesBean mBean = new AddBuyRturnGoodsBean.RtnedLinesBean();//退货bean
            List<RtnLinesBean> rtnLines = shipPlanBean.getRtn_lines();
            if (rtnLines.size() > 0) {
                wh_id = rtnLines.get(0).getWh_id();
            }

            ArrayList<AddBuyRturnGoodsBean.RtnedLinesBean.RtnedGodsLinesBean> mChilList = new ArrayList<>();
            for (int j = 0; j < rtnLines.size(); j++) {
                RtnLinesBean linesBean = rtnLines.get(j);
                if (linesBean.getSend_qty() > linesBean.getRtn_qty()) {
                    showNotDialog("料品（" + linesBean.getItem_name() + "）配货数量大于申请数量,请扫码");
                    return;
                }
                if (linesBean.getSend_qty() <= 0) {
                    showNotDialog("料品（" + linesBean.getItem_name() + "）配货数量为0,请扫码");
                    return;
                }
                AddBuyRturnGoodsBean.RtnedLinesBean.RtnedGodsLinesBean mChildBean = new AddBuyRturnGoodsBean.RtnedLinesBean.RtnedGodsLinesBean();
                mChildBean.setId(linesBean.getId());
                mChildBean.setWh_id(linesBean.getWh_id());
                mChildBean.setAlloc_qty(linesBean.getSend_qty() + "");//配货数量
                mChildBean.setRtn_line_no(linesBean.getRtn_line_no());
                mChildBean.setRtn(linesBean.getRtn());
                mChildBean.setWh_name(linesBean.getWh_name());
                mChildBean.setItem_id(linesBean.getItem_id());
                mChildBean.setItem_code(linesBean.getItem_code());
                mChildBean.setMfc(linesBean.getMfc());
                mChildBean.setMfc_code(linesBean.getMfc_code());
                mChildBean.setMfc_name(linesBean.getMfc_name());
                mChildBean.setWh_staff_id(linesBean.getWh_manager_id());
                mChildBean.setWh_staff_name(linesBean.getWh_manager_id());
                mChilList.add(mChildBean);

            }
            mBean.setRtn_id(shipPlanBean.getId());
            mBean.setRtned_gods_lines(mChilList);
            mList.add(mBean);
            List<BarBean> barList = shipPlanBean.getBarList();
            for (int m = 0; m < barList.size(); m++) {
                BarBean barBean = barList.get(m);
                BarListBean mBarBean = new BarListBean();
                mBarBean.setRtn_id(shipPlanBean.getId());
                mBarBean.setItem_id(barBean.getItem_id());
                mBarBean.setItem_code(barBean.getItem_code());
                mBarBean.setItem_name(barBean.getItem_name());
                mBarBean.setItem_spec(barBean.getItem_spec());
                mBarBean.setItem_bar(barBean.getBar_code());
                mBarBean.setQty(barBean.getQty() + "");
                mBarBean.setWh_id(wh_id);
                mBarList.add(mBarBean);
            }
        }
        mAddBean.setSupplier_code(tvFour.getText().toString());
        mAddBean.setRtned_lines(mList);

        Gson gson = new Gson();
        String json = gson.toJson(mAddBean);
        String barJson = gson.toJson(mBarList);
        map.clear();
        map.put("staff_id", userBean.getStaff_id());
        map.put("org_id", userBean.getOrg_id());
        map.put("addRtnedGoods", json);
        map.put("barList", barJson);
        getPresenter().addRtnedGoods(userBean.getStaff_token(), map);
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


    //----------------------------------------扫码--------------------------------------------------

    public static final int RC_CAMERA = 0X01;

    //检测拍摄权限
    @AfterPermissionGranted(RC_CAMERA)
    private void checkCameraPermissions(String type) {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(context, perms)) {
            OutSaleRtuBean item = adapter.getItem(mScanPosition);
            List<RtnLinesBean> rtnLines = item.getRtn_lines();

            List<ScanInfoBean> scanList = new ArrayList<>();
            for (int i = 0; i < rtnLines.size(); i++) {
                RtnLinesBean detail = rtnLines.get(i);
                ScanInfoBean scanBean = new ScanInfoBean();
                scanBean.setItem_spec(detail.getItem_spec());
                scanBean.setItem_code(detail.getItem_code());
                scanBean.setItem_id(detail.getItem_id());
                scanBean.setItem_name(detail.getItem_name());
                scanBean.setSend_qty((int) detail.getRtn_qty());//申请数量
                scanBean.setArrive_qty(detail.getSend_qty());//配货数量
                scanBean.setBarList(item.getBarList());//历史条码
                scanList.add(scanBean);
            }
            startInBuyScanFragment(scanList, type, new ArrayList<BarVerificationListsBean>());

        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera), RC_CAMERA, perms);
        }
    }


    @Override
    public OutBuyReturnPresenter createPresenter() {
        return new OutBuyReturnPresenter(getApp());
    }


    private Dialog loading;

    @Override
    public void showProgress() {
        loading = LoadingUtil.createLoading(getActivity(), "加载中...");

    }

    @Override
    public void onCompleted() {
        LoadingUtil.closeDialog(loading);
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

    private OutSaleRtuBean mChooseBean = new OutSaleRtuBean();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getmMsg();
        switch (msg) {
            case "6"://选择仓库
                WareHouseBean houseBean = event.getmWareHouseBean();
                adapter.getItem(mPosition).getRtn_lines().get(mChildPosition).setWh_name(houseBean.getWarehouse_name());
                adapter.getItem(mPosition).getRtn_lines().get(mChildPosition).setWh_id(houseBean.getWarehouse_id());
                adapter.notifyDataSetChanged();
                break;
            case "23"://选择厂牌
                MfcBean mfcBean = event.getmMfcBean();
                adapter.getItem(mMfcPosition).getRtn_lines().get(mMfcChildPosition).setMfc_name(mfcBean.getMfc_name());
                adapter.getItem(mMfcPosition).getRtn_lines().get(mMfcChildPosition).setMfc(mfcBean.getMfc());
                adapter.notifyDataSetChanged();
                break;
            case "12"://选择出货计划
                OutSaleRtuBean bean = event.getmOutSaleRtuBean();
                tvThree.setText(bean.getSupplier_name());
                tvFour.setText(bean.getSupplier_code());
                mSupplier_id = bean.getSupplier_code();
                mChooseBean.setRtn_lines(bean.getRtn_lines());
                mChooseBean.setDoc_no(bean.getDoc_no());
                List<RtnLinesBean> list = new ArrayList<>();
                for (int i = 0; i < bean.getRtn_lines().size(); i++) {
                    if (bean.getRtn_lines().get(i).isCheck()) {
                        list.add(bean.getRtn_lines().get(i));
                    }
                }
                bean.setRtn_lines(list);
                adapter.clear();
                adapter.add(bean);
                adapter.notifyDataSetChanged();
                break;
            case "out_buy_add"://扫码返回
                List<ScanInfoBean> listScan = event.getmScanInBuyBean().getList();
                List<RtnLinesBean> rtn_lines = adapter.getItem(mScanPosition).getRtn_lines();
                for (int i = 0; i < rtn_lines.size(); i++) {
                    for (int j = 0; j < listScan.size(); j++) {
                        if (rtn_lines.get(i).getItem_code().equals(listScan.get(j).getItem_code())) {
                            rtn_lines.get(i).setSend_qty(listScan.get(j).getArrive_qty());
                            rtn_lines.get(i).setItem_bar(listScan.get(j).getBar_code());
                        }
                    }
                }
                adapter.notifyItemChanged(mScanPosition);
                break;
            case "24":
                List<BarBean> barBeanList = event.getmScanListBean().getList();
                adapter.getItem(mScanPosition).setBarList(barBeanList);
                break;
            case "9"://选择库管员
                OperatorBean operatorBean = event.getmOperatorBean();
                adapter.getItem(mManagerPosition).getRtn_lines().get(mManagerChildPosition).setWh_manager(operatorBean.getOperator_name());
                adapter.getItem(mManagerPosition).getRtn_lines().get(mManagerChildPosition).setWh_manager_id(operatorBean.getOperator_code());
                adapter.notifyDataSetChanged();
                break;

        }
    }


    @Override
    public void OnAddRtnedGoods(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent("refresh"));
        finish();
    }

    @Override
    public void onInvalidRtnedGoods(String data) {

    }

    @Override
    public void onPastRtnedGoods(String data) {

    }

    @Override
    public void OnQueryRtnedGoodsList(List<RtnedGoodsBean> data) {

    }

    @Override
    public void onQueryRtnedGoodsById(RtnedGoodsBean data) {

    }

    @Override
    public void onDelRtnedGoods(String data) {

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
