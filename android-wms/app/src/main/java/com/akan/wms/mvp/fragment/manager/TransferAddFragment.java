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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.BarBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.ItemWhQohBean;
import com.akan.wms.bean.MfcBean;
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.TransferBarBean;
import com.akan.wms.bean.TransferBean;
import com.akan.wms.bean.TransferLineListBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.mvp.adapter.home.TransferAddAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.TransferPresenter;
import com.akan.wms.mvp.view.home.ITransferView;
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

public class TransferAddFragment extends BaseFragment<ITransferView, TransferPresenter> implements ITransferView, FragmentBackHandler {

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
    EditText tvFive;
    @BindView(R.id.tvDelete)
    ImageView tvDelete;
    @BindView(R.id.tvScan)
    ImageView tvScan;
    @BindView(R.id.llbg)
    LinearLayout llbg;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.llAdd)
    LinearLayout llAdd;


    private List<ItemWhQohBean> list;
    private TransferAddAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int mPosition;

    private String out_wh_id;
    private String in_wh_id;

    public static TransferAddFragment newInstance() {
        Bundle args = new Bundle();
        TransferAddFragment fragment = new TransferAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_transfer_add;
    }

    @Override
    public void initUI() {
        tvTitle.setText("新增调拨单");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new TransferAddAdapter(context, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                onDelete(position);
                return false;
            }
        });

        adapter.setOnStockListener(new TransferAddAdapter.OnSelectClickListener() {
            @Override
            public void onChooseMfc(int position, String itemId) {
                mPosition = position;
                startChooseMfcFragment(itemId);

            }
        });

    }


    //长按移除料品
    private void onDelete(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("是否移除料品(" + adapter.getItem(position).getItem_name() + ")？");
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.remove(position);
                if (adapter.getAllData().size() <= 0) {
                    tvDelete.setVisibility(View.GONE);
                    tvScan.setVisibility(View.GONE);
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

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        tvOne.setText(userBean.getStaff_name());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTwo.setText(str);
    }


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tvThree, R.id.tvFour, R.id.llAdd, R.id.tvDelete, R.id.tvScan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                showCloseDialog();
                break;
            case R.id.tvDelete:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("提示");
                builder.setMessage("是否确认删除所以已选料品？");
                builder.setCancelable(true);
                builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.clear();
                        tvScan.setVisibility(View.GONE);
                        tvDelete.setVisibility(View.GONE);

                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
                break;
            case R.id.tvScan:
                checkCameraPermissions("transfer_add");
                break;
            case R.id.tvRight:
                if (TextUtils.isEmpty(out_wh_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择调出仓库");
                    return;
                }
                if (TextUtils.isEmpty(in_wh_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择调入仓库");
                    return;
                }
                if (adapter.getAllData().size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请添调拨申请单");
                    return;
                }

                if (out_wh_id.equals(in_wh_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "调拨仓库不能为同一个仓库");
                    return;
                }
                showDialog();
                break;
            case R.id.tvThree://选择调出仓库
                if (adapter.getAllData().size() > 0) {
                    final AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setTitle("提示");
                    builder1.setMessage("重新选择仓库会清空已选料品列表,是否继续？");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.removeAll();
                        }
                    });
                    builder1.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder1.create().show();
                }
                startChooseDepotFragment("21");
                break;
            case R.id.tvFour://选择调入仓库
                startChooseDepotFragment("22");
                break;
            case R.id.llAdd:
                if (TextUtils.isEmpty(out_wh_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择调出仓库");
                    return;
                }
                startChooseItemFragment(out_wh_id);
                break;
        }
    }


    //操作确认弹框
    private void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("是否确认完成？");
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toCommit();
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
        List<ItemWhQohBean> allData = adapter.getAllData();
        map.clear();
        map.put("out_wh_id", out_wh_id);
        map.put("out_wh_name", tvThree.getText().toString());
        map.put("in_wh_id", in_wh_id);
        map.put("in_wh_name", tvFour.getText().toString());
        map.put("remark", tvFive.getText().toString());

        List<TransferLineListBean> mList = new ArrayList<>();
        List<TransferBarBean> mBarList = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            ItemWhQohBean itemInfoBean = allData.get(i);
            if (itemInfoBean.getNum() <= 0) {
                showNotDialog("料品（" + itemInfoBean.getItem_name() + "）配货数量为0,请扫码");
                return;
            }

            TransferLineListBean mAddBean = new TransferLineListBean();
            mAddBean.setApply_line_id("");
            mAddBean.setItem_id(itemInfoBean.getItem_id() + "");
            mAddBean.setQty(itemInfoBean.getNum() + "");
            mAddBean.setSupplier_code(itemInfoBean.getSupplier_code());
            mAddBean.setSupplier_id(itemInfoBean.getSupplier_id());
            mAddBean.setSupplier_name(itemInfoBean.getSupplier_name());
            mList.add(mAddBean);

            for (int m = 0; m < barBeanList.size(); m++) {
                BarBean barBean = barBeanList.get(m);
                if (barBean.getItem_code().equals(itemInfoBean.getItem_code())) {
                    TransferBarBean mBarBean = new TransferBarBean();
                    mBarBean.setItem_id(barBean.getItem_id() + "");
                    mBarBean.setItem_name(barBean.getItem_name());
                    mBarBean.setItem_spec(barBean.getItem_spec());
                    mBarBean.setItem_code(barBean.getItem_code());
                    mBarBean.setItem_bar(barBean.getBar_code());
                    mBarBean.setQty(barBean.getQty() + "");
                    mBarList.add(mBarBean);
                }
            }


        }

        Gson gson = new Gson();
        String json = gson.toJson(mList);
        String barJson = gson.toJson(mBarList);
        map.put("lineList", json);
        map.put("barList", barJson);
        getPresenter().addOutAndIn(userBean.getStaff_token(), map);
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
            List<ItemWhQohBean> allData = adapter.getAllData();
            List<ScanInfoBean> scanList = new ArrayList<>();
            for (int i = 0; i < allData.size(); i++) {
                ScanInfoBean scanBean = new ScanInfoBean();
                ItemWhQohBean detail = allData.get(i);
                scanBean.setItem_code(detail.getItem_code());
                scanBean.setItem_id(detail.getItem_id());
                scanBean.setItem_name(detail.getItem_name());
                scanBean.setItem_spec(detail.getItem_spec());
                scanBean.setArrive_qty(detail.getNum());
                scanBean.setBarList(barBeanList);//历史条码
                scanList.add(scanBean);
            }
            startInBuyScanFragment(scanList, type);
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera), RC_CAMERA, perms);
        }
    }

    @Override
    public TransferPresenter createPresenter() {
        return new TransferPresenter(getApp());
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

            case "14"://选择料品
                List<ItemWhQohBean> list = event.getmGoodsItenBean().getItemWhQohBeanList();
                List<ItemWhQohBean> allData = adapter.getAllData();
                if (allData.size() > 0) {
                    for (int i = 0; i < allData.size(); i++) {
                        for (int j = 0; j < list.size(); j++) {
                            if (allData.get(i).getItem_id().equals(list.get(j).getItem_id())) {
                                list.remove(j);
                            }
                        }
                    }
                    adapter.addAll(list);
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.addAll(list);
                }
                if (adapter.getAllData().size() > 0) {
                    tvScan.setVisibility(View.VISIBLE);
                    tvDelete.setVisibility(View.VISIBLE);
                }
                break;
            case "21"://调出仓库
                WareHouseBean beanOut = event.getmWareHouseBean();
                tvThree.setText(beanOut.getWarehouse_name());
                out_wh_id = beanOut.getWarehouse_id();
                break;
            case "23"://选择厂牌
                MfcBean mfcBean = event.getmMfcBean();
                adapter.getItem(mPosition).setSupplier_code(mfcBean.getMfc_code());
                adapter.getItem(mPosition).setSupplier_id(mfcBean.getMfc());
                adapter.getItem(mPosition).setSupplier_name(mfcBean.getMfc_name());
                adapter.notifyDataSetChanged();
                break;
            case "22"://调入仓库
                WareHouseBean beanIn = event.getmWareHouseBean();
                tvFour.setText(beanIn.getWarehouse_name());
                in_wh_id = beanIn.getWarehouse_id();
                break;
            case "transfer_add"://扫码
                List<ScanInfoBean> listTwo = event.getmScanInBuyBean().getList();
                List<ItemWhQohBean> allData1 = adapter.getAllData();
                for (int i = 0; i < allData1.size(); i++) {
                    ItemWhQohBean listBean = allData1.get(i);
                    for (int m = 0; m < listTwo.size(); m++) {
                        if (listBean.getItem_code().equals(listTwo.get(m).getItem_code())) {
                            listBean.setNum(listTwo.get(m).getArrive_qty());
                            listBean.setItem_bar(listTwo.get(m).getBar_code());
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            case "24":
                barBeanList.clear();
                barBeanList.addAll(event.getmScanListBean().getList());
                break;
        }
    }


    List<BarBean> barBeanList=new ArrayList<>();
    @Override
    public void onQueryTransferOutInPage(List<TransferBean> data) {

    }

    @Override
    public void onQueryTransferOutInById(TransferBean data) {

    }

    @Override
    public void OnAuditOutAndIn(String data) {

    }

    @Override
    public void OnAddOutAndIn(String data) {
        ToastUtil.showToast(context.getApplicationContext(), "提交成功");
        EventBus.getDefault().post(new FirstEvent("refresh"));
        finish();
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
