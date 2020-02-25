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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.BarBean;
import com.akan.wms.bean.BarListBean;
import com.akan.wms.bean.BarVerificationListsBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.FirstEventTwo;
import com.akan.wms.bean.OutSaleLineBean;
import com.akan.wms.bean.PlanLineBean;
import com.akan.wms.bean.SaleShipBean;
import com.akan.wms.bean.SaleShipTypeBean;
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.ShipPlanBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.mvp.adapter.home.OutSaleAddAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.OutSalePresenter;
import com.akan.wms.mvp.view.home.IOutSaleView;
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

public class OutSaleAddNewFragment extends BaseFragment<IOutSaleView, OutSalePresenter> implements IOutSaleView, FragmentBackHandler {

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
    @BindView(R.id.lineOne)
    View lineOne;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.llAdd)
    LinearLayout llAdd;


    private List<ShipPlanBean> list;
    private OutSaleAddAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mDoc_type_id = "";
    private int mPosition;
    private int mChildPosition;
    private int mDeletePosition;
    private String mCustomer_id;//客户id
    private int mScanPosition;

    private ShipPlanBean mChooseBean;

    public static OutSaleAddNewFragment newInstance(ShipPlanBean outPlanAddBean) {
        Bundle args = new Bundle();
        OutSaleAddNewFragment fragment = new OutSaleAddNewFragment();
        fragment.mChooseBean = outPlanAddBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_out_sale_add;
    }

    @Override
    public void initUI() {
        tvTitle.setText("新增销售出货单");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new OutSaleAddAdapter(context, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startChooseOutPlanChildFragment(mChooseBean, "1", "have");
            }
        });
        adapter.setOnStockListener(new OutSaleAddAdapter.onSelectStockClickListener() {
            @Override
            public void onSelect(int position, int childPosition, String itemId) {
                //选择仓库
                mPosition = position;
                mChildPosition = childPosition;
                startChooseDeportFragment();
            }

            @Override
            public void onDelete(int position) {
                mDeletePosition = position;
                showDialog("删除");
            }

            @Override
            public void onScan(int position) {
                mScanPosition = position;
                checkCameraPermissions("out_sale_add");
            }
        });


        List<PlanLineBean> list = new ArrayList<>();
        ShipPlanBean shipPlanBean = new ShipPlanBean();
        for (int i = 0; i < mChooseBean.getPlanLineBeans().size(); i++) {
            if (mChooseBean.getPlanLineBeans().get(i).isCheck()) {
                list.add(mChooseBean.getPlanLineBeans().get(i));
            }
        }
        shipPlanBean.setPlanLineBeans(list);
        shipPlanBean.setDoc_no(mChooseBean.getDoc_no());
        adapter.clear();
        adapter.add(shipPlanBean);
        adapter.notifyDataSetChanged();

        if (list.size() > 0) {
            PlanLineBean lineBean = list.get(0);
            tvFour.setText(mChooseBean.getDoc_type_name());
            mDoc_type_id = mChooseBean.getDoc_type_id();
            tvThree.setText(lineBean.getCustomer_name());
            mCustomer_id = lineBean.getCustomer_id() + "";
        }

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


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tvFour, R.id.llAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                showCloseDialog();
                break;
            case R.id.tvRight:
                if (TextUtils.isEmpty(mDoc_type_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择单据类型");
                    return;
                }
                if (adapter.getAllData().size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请添加出货计划");
                    return;
                }
                showDialog("完成");
                break;

            case R.id.llAdd:
                startChooseOutPlanListFragment("add");
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
                        mCustomer_id = "";
                        tvThree.setText("");
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
        List<ShipPlanBean> allData = adapter.getAllData();
        for (int i = 0; i < allData.size(); i++) {


            map.clear();
            map.put("org_id", userBean.getOrg_id());
            map.put("ship_plan_id", mChooseBean.getId());//出货计划id
            map.put("ship_plan_no", mChooseBean.getDoc_no());//出库计划单号
            map.put("doc_type_id", mDoc_type_id);//单据类型id
            map.put("doc_type_name", tvFour.getText().toString());//单据类型名称
            map.put("customer_id", mCustomer_id);//	客户id
            map.put("customer_name", tvThree.getText().toString());//客户名称

            ShipPlanBean shipPlanBean = allData.get(i);
            List<PlanLineBean> lineBeans = shipPlanBean.getPlanLineBeans();
            List<BarBean> barList = shipPlanBean.getBarList();
            List<OutSaleLineBean> mList = new ArrayList<>();
            List<BarListBean> mBarList = new ArrayList<>();

            for (int j = 0; j < lineBeans.size(); j++) {
                PlanLineBean planLineBean = lineBeans.get(j);

                if (planLineBean.getAdd_qty() <= 0) {
                    showNotDialog("料品（" + planLineBean.getItem_name() + "）配货数量为0,请扫码");
                    return;
                }

                if (planLineBean.getPlan_qty() < planLineBean.getAdd_qty()) {
                    showNotDialog("料品（" + planLineBean.getItem_name() + "）配货数量大于计划数量,请修改扫码");
                    return;
                }

                if (TextUtils.isEmpty(planLineBean.getDefault_wh_id())) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择仓库");
                    return;
                }

                String wh_id = planLineBean.getDefault_wh_id();
                OutSaleLineBean mBean = new OutSaleLineBean();
                mBean.setItem_id(planLineBean.getItem_id());
                mBean.setPlan_line_id(planLineBean.getId());
                mBean.setWh_id(wh_id);
                mBean.setQty(planLineBean.getAdd_qty() + "");
                mList.add(mBean);
                for (int m = 0; m < barList.size(); m++) {
                    BarBean barBean = barList.get(m);
                    if (barBean.getItem_code().equals(planLineBean.getItem_code())) {
                        BarListBean mBarbean = new BarListBean();
                        mBarbean.setItem_bar(barBean.getBar_code());
                        mBarbean.setItem_id(barBean.getItem_id());
                        mBarbean.setQty(barBean.getQty() + "");
                        mBarbean.setWh_id(wh_id);
                        mBarList.add(mBarbean);
                    }
                }
            }
            Gson gson = new Gson();
            String json = gson.toJson(mList);
            String barJson = gson.toJson(mBarList);
            map.put("lineList", json);
            map.put("barList", barJson);
            getPresenter().addSaleShip(userBean.getStaff_token(), map);

        }


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
            //开启扫码
            ShipPlanBean item = adapter.getItem(mScanPosition);
            List<PlanLineBean> rtn_lines = item.getPlanLineBeans();
            List<ScanInfoBean> scanList = new ArrayList<>();
            for (int i = 0; i < rtn_lines.size(); i++) {
                ScanInfoBean scanBean = new ScanInfoBean();
                PlanLineBean detail = rtn_lines.get(i);
                scanBean.setItem_code(detail.getItem_code());
                scanBean.setItem_id(detail.getItem_id());//料品id
                scanBean.setItem_name(detail.getItem_name());//料品名称
                scanBean.setItem_spec(detail.getItem_spec());//料品规格
                scanBean.setSend_qty((int) detail.getPlan_qty());//申请数量
                scanBean.setArrive_qty(detail.getAdd_qty());//配货数量
                scanBean.setBarList(item.getBarList());//历史条码
                scanList.add(scanBean);
            }
            startInBuyScanFragment(scanList, type,new ArrayList<BarVerificationListsBean>());
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera), RC_CAMERA, perms);
        }
    }


    @Override
    public OutSalePresenter createPresenter() {
        return new OutSalePresenter(getApp());
    }


    @Override
    public void onQuerySaleShipPage(List<SaleShipBean> data) {

    }

    @Override
    public void OnQuerySaleShipByCode(SaleShipBean data) {

    }

    @Override
    public void OnAddSaleShip(String data) {
        ToastUtil.showToast(context.getApplicationContext(), "添加成功");
        EventBus.getDefault().post(new FirstEvent("refresh"));
        finish();
    }

    @Override
    public void OninvalidSaleShip(String data) {

    }

    @Override
    public void OnoutOfShip(String data) {

    }

    @Override
    public void OnsendSaleShip(String data) {

    }

    @Override
    public void OnDeleteSaleShip(String data) {

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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventTwo event) {
        String msg = event.getmMsg();
        switch (msg) {
            case "1"://选择单据类型
                SaleShipTypeBean typeBean = event.getmBean();
                tvFour.setText(typeBean.getName());
                mDoc_type_id = typeBean.getId();
                break;
            case "2"://选择出货计划
                ShipPlanBean bean = event.getmShipPlanBean();
                tvFour.setText(bean.getDoc_type_name());
                mDoc_type_id = bean.getDoc_type_id();
                mChooseBean.setPlanLineBeans(bean.getPlanLineBeans());
                mChooseBean.setId(bean.getId());
                mChooseBean.setDoc_no(bean.getDoc_no());
                mChooseBean.setDoc_type_name(bean.getDoc_type_name());
                List<PlanLineBean> list = new ArrayList<>();
                for (int i = 0; i < bean.getPlanLineBeans().size(); i++) {
                    if (bean.getPlanLineBeans().get(i).isCheck()) {
                        list.add(bean.getPlanLineBeans().get(i));
                    }
                }
                bean.setPlanLineBeans(list);
                adapter.clear();
                adapter.add(bean);
                adapter.notifyDataSetChanged();

                if (list.size() > 0) {
                    PlanLineBean lineBean = list.get(0);
                    tvThree.setText(lineBean.getCustomer_name());
                    mCustomer_id = lineBean.getCustomer_id() + "";


                }
                break;
            case "6"://选择仓库
                WareHouseBean houseBean = event.getmWareHouseBean();
                adapter.getItem(mPosition).getPlanLineBeans().get(mChildPosition).setDefault_wh_name(houseBean.getWarehouse_name());
                adapter.getItem(mPosition).getPlanLineBeans().get(mChildPosition).setDefault_wh_id(houseBean.getWarehouse_id());
                adapter.notifyDataSetChanged();
                break;

            case "out_sale_add"://扫码返回
                List<ScanInfoBean> listScan = event.getmScanInBuyBean().getList();
                List<PlanLineBean> rtn_lines = adapter.getItem(mScanPosition).getPlanLineBeans();
                for (int i = 0; i < rtn_lines.size(); i++) {
                    for (int j = 0; j < listScan.size(); j++) {
                        if (rtn_lines.get(i).getItem_code().equals(listScan.get(j).getItem_code())) {
                            rtn_lines.get(i).setAdd_qty(listScan.get(j).getArrive_qty());
                            rtn_lines.get(i).setBar_code(listScan.get(j).getBar_code());
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            case "24":
                List<BarBean> barBeanList = event.getmScanListBean().getList();
                adapter.getItem(mScanPosition).setBarList(barBeanList);
                break;
        }
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
