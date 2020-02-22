package com.akan.wms.mvp.fragment.manager;

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
import com.akan.wms.bean.BarListOutTransferBean;
import com.akan.wms.bean.BarVerificationListsBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.OutSaleLineBean;
import com.akan.wms.bean.OutTypeLBean;
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.StaffOrgsBean;
import com.akan.wms.bean.TransferOutBean;
import com.akan.wms.bean.TransferUnCompleteBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.mvp.adapter.home.OutSaleAddAdapter;
import com.akan.wms.mvp.adapter.home.OutTransferAddAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.OutTransferPresenter;
import com.akan.wms.mvp.view.home.IOutTransferView;
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

public class OutTransferAddFragment extends BaseFragment<IOutTransferView, OutTransferPresenter> implements IOutTransferView , FragmentBackHandler {

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
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.tvSix)
    TextView tvSix;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.llAdd)
    LinearLayout llAdd;
    @BindView(R.id.tvAddOne)
    TextView tvAddOne;


    private List<TransferUnCompleteBean> list;
    private OutTransferAddAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mDoc_type_id;//单据类型id
    private String in_org_id;//组织id
    private String transfer_direction="0";//调拨方向

    private int mDeletePosition;
    private int mPosition;
    private int mChildPostion;
    private int mScanPosition;


    public static OutTransferAddFragment newInstance() {
        Bundle args = new Bundle();
        OutTransferAddFragment fragment = new OutTransferAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_out_transfer_add;
    }

    @Override
    public void initUI() {
        tvTitle.setText("新增调拨出库单");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new OutTransferAddAdapter(context, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startTransferApplyChildFragment(mChooseBean, "1","add");
            }
        });
        adapter.setOnStockListener(new OutSaleAddAdapter.onSelectStockClickListener() {
            @Override
            public void onSelect(int position, int childPosition, String itemId) {
                mPosition = position;
                mChildPostion = childPosition;
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
                checkCameraPermissions("out_transfer_add");
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
        tvAddOne.setText(userBean.getOrg_name());
    }


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tvThree, R.id.tvFour, R.id.tvFive, R.id.llAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                showCloseDialog();
                break;
            case R.id.tvRight:
                if (TextUtils.isEmpty(tvThree.getText().toString().trim())) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择单据类型");
                    return;
                }
                if (TextUtils.isEmpty(tvFour.getText().toString().trim() )) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择调入组织");
                    return;
                }
                String mTvFive = tvFive.getText().toString();
                if (TextUtils.isEmpty(mTvFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择调拨方向");
                    return;
                }

                if (adapter.getAllData().size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请添加调拨申请");
                    return;
                }
                showDialog("完成");
                break;
            case R.id.tvThree://选择单据类型
                startOutApplyTypeFragment();
                break;
            case R.id.tvFour://选择调入组织
                if (adapter.getAllData().size() > 0) {
                    //操作确认弹框
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("重新选择调入组织会清空已选申请单,是否继续？");
                    builder.setCancelable(true);
                    builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.clear();
                            adapter.notifyDataSetChanged();
                            startOutApplyTypeAllFragment();

                        }
                    });
                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();

                }else {
                    startOutApplyTypeAllFragment();
                }

                break;
            case R.id.tvFive://调拨方向
                showSingleDialog();
                break;
            case R.id.llAdd:
                startTransferApplyListFragment("","add");
                break;
        }
    }


    private AlertDialog alertDialog;
    private int choosePosition = 0;

    public void showSingleDialog() {
        final String[] items = {"普通", "退货"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle("选择调拨方向");
        alertBuilder.setSingleChoiceItems(items, choosePosition, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choosePosition = i;
            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvFive.setText(items[choosePosition]);
                if ("普通".equals(items[choosePosition])) {
                    transfer_direction = "0";
                } else {
                    transfer_direction = "1";
                }
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alertDialog = alertBuilder.create();
        alertDialog.show();

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
                        tvThree.setText("");
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
        List<TransferUnCompleteBean> allData = adapter.getAllData();
        TransferUnCompleteBean applyBean = allData.get(0);
        map.clear();
        map.put("apply_id", mChooseBean.getId());
        map.put("apply_no", mChooseBean.getDoc_no());

        map.put("doc_type_id", mChooseBean.getDoc_type_id());
        map.put("doc_type_name", mChooseBean.getDoc_type_name());
        map.put("in_org_id", mChooseBean.getIn_org_id());
        map.put("in_org_name", mChooseBean.getIn_org_name());
        map.put("org_id", userBean.getOrg_id());
        map.put("staff_id", userBean.getStaff_id());
        map.put("remark", tvSix.getText().toString());
        map.put("transfer_direction", transfer_direction);

        List<TransferUnCompleteBean.LineBeanListBean> lineBeanList = applyBean.getLineBeanList();
        List<OutSaleLineBean> mList = new ArrayList<>();
        List<BarBean> barList = applyBean.getBarList();
        List<BarListOutTransferBean> mBarList = new ArrayList<>();
        for (int j = 0; j < lineBeanList.size(); j++) {
            TransferUnCompleteBean.LineBeanListBean lineBeanListBean = lineBeanList.get(j);
            if (lineBeanListBean.getPoint_qty() <= 0) {
                showNotDialog("料品（" + lineBeanListBean.getItem_name() + "）配货数量为零，请扫码");
                return;
            }
            if (TextUtils.isEmpty(lineBeanListBean.getOut_wh_name())) {
                ToastUtil.showToast(context.getApplicationContext(), "请选择仓库");
                return;
            }
            OutSaleLineBean mBean = new OutSaleLineBean();
            mBean.setItem_id(lineBeanListBean.getItem_id() + "");
            mBean.setApply_line_id(lineBeanListBean.getId() + "");
            mBean.setWh_id(lineBeanListBean.getOut_wh() + "");
            mBean.setQty(lineBeanListBean.getPoint_qty() + "");
            mBean.setWh_name(lineBeanListBean.getOut_wh_name() + "");
            mList.add(mBean);
            for (int m = 0; m < barList.size(); m++) {
                BarBean barBean = barList.get(m);
                if (barBean.getItem_code().equals(lineBeanListBean.getItem_code())) {
                    BarListOutTransferBean mBarbean = new BarListOutTransferBean();
                    mBarbean.setItem_id(barBean.getItem_id());
                    mBarbean.setQty(barBean.getQty() + "");
                    mBarbean.setItem_name(barBean.getItem_name());
                    mBarbean.setItem_spec(barBean.getItem_spec());
                    mBarbean.setItem_bar(barBean.getBar_code());
                    mBarbean.setItem_code(barBean.getItem_code());
                    mBarbean.setWh_id(lineBeanListBean.getOut_wh());
                    mBarbean.setWh_name(lineBeanListBean.getOut_wh_name());
                    mBarList.add(mBarbean);
                }
            }

        }
        Gson gson = new Gson();
        String json = gson.toJson(mList);
        String barJson = gson.toJson(mBarList);
        map.put("lineList", json);
        map.put("barList", barJson);
        getPresenter().addTransferOut(userBean.getStaff_token(), map);
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
            TransferUnCompleteBean item = adapter.getItem(mScanPosition);
            List<TransferUnCompleteBean.LineBeanListBean> detailList = item.getLineBeanList();
            List<ScanInfoBean> scanList = new ArrayList<>();
            for (int j = 0; j < detailList.size(); j++) {
                TransferUnCompleteBean.LineBeanListBean detail = detailList.get(j);
                ScanInfoBean scanBean = new ScanInfoBean();
                scanBean.setItem_code(detail.getItem_code());
                scanBean.setItem_id(detail.getItem_id());
                scanBean.setItem_spec(detail.getItem_spec());
                scanBean.setItem_name(detail.getItem_name());
                scanBean.setSend_qty(detail.getApply_qty());
                scanBean.setArrive_qty(detail.getPoint_qty());
                scanBean.setBarList(item.getBarList());//历史条码
                scanList.add(scanBean);

            }
            startInBuyScanFragment(scanList, type,new ArrayList<BarVerificationListsBean>());

        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera), RC_CAMERA, perms);
        }
    }


    @Override
    public OutTransferPresenter createPresenter() {
        return new OutTransferPresenter(getApp());
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
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getmMsg();
        switch (msg) {
            case "11"://选择组织
                StaffOrgsBean staffOrgsBean = event.getmBean();
                tvFour.setText(staffOrgsBean.getName());
                in_org_id = staffOrgsBean.getId();
                break;
            case "16"://选择单据类型
                OutTypeLBean typeBean = event.getmOutTypeLBean();
                tvThree.setText(typeBean.getName());
                mDoc_type_id = typeBean.getId() + "";
                break;
            case "15"://选择调拨申请单
                TransferUnCompleteBean bean = event.getmTransferUnCompleteBean();
                mChooseBean.setLineBeanList(bean.getLineBeanList());
                mChooseBean.setDoc_no(bean.getDoc_no());
                mChooseBean.setId(bean.getId());
                mChooseBean.setDoc_type_name(bean.getDoc_type_name());
                mChooseBean.setDoc_type_id(bean.getDoc_type_id());
                mChooseBean.setIn_org_id(bean.getIn_org_id());
                mChooseBean.setIn_org_name(bean.getIn_org_name());
                tvFour.setText(bean.getIn_org_name());
                tvThree.setText(bean.getDoc_type_name());

                List<TransferUnCompleteBean.LineBeanListBean> list = new ArrayList<>();
                for (int i = 0; i < bean.getLineBeanList().size(); i++) {
                    if (bean.getLineBeanList().get(i).isCheck()) {
                        list.add(bean.getLineBeanList().get(i));
                    }
                }
                bean.setLineBeanList(list);
                adapter.clear();
                adapter.add(bean);
                adapter.notifyDataSetChanged();
                break;
            case "6"://选择仓库
                WareHouseBean houseBean = event.getmWareHouseBean();
                adapter.getItem(mPosition).getLineBeanList().get(mChildPostion).setOut_wh_name(houseBean.getWarehouse_name());
                adapter.getItem(mPosition).getLineBeanList().get(mChildPostion).setOut_wh(houseBean.getWarehouse_id());
                adapter.notifyDataSetChanged();
                break;
            case "out_transfer_add"://扫码返回
                List<ScanInfoBean> listScan = event.getmScanInBuyBean().getList();
                List<TransferUnCompleteBean> allData = adapter.getAllData();
                if (allData.size() > 0) {
                    List<TransferUnCompleteBean.LineBeanListBean> rtn_lines = allData.get(0).getLineBeanList();
                    for (int i = 0; i < rtn_lines.size(); i++) {
                        for (int j = 0; j < listScan.size(); j++) {
                            if (rtn_lines.get(i).getItem_code().equals(listScan.get(j).getItem_code())) {
                                rtn_lines.get(i).setPoint_qty(listScan.get(j).getArrive_qty());
                                rtn_lines.get(i).setItem_bar(listScan.get(j).getBar_code());
                            }
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

    private TransferUnCompleteBean mChooseBean = new TransferUnCompleteBean();

    @Override
    public void onQueryTransferOutPage(List<TransferOutBean> data) {

    }

    @Override
    public void onQueryTransferById(TransferOutBean data) {

    }

    @Override
    public void onAddTransferOut(String data) {
        ToastUtil.showToast(context.getApplicationContext(), "添加成功");
        EventBus.getDefault().post(new FirstEvent("refresh"));
        finish();
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
