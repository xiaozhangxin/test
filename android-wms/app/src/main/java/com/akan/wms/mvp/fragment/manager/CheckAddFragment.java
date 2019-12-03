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
import com.akan.wms.bean.InfoBeanListBean;
import com.akan.wms.bean.InfoCodeBean;
import com.akan.wms.bean.InventoryBean;
import com.akan.wms.bean.ItemWhQohBean;
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.mvp.adapter.home.CheckAddAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.CheckPresenter;
import com.akan.wms.mvp.view.home.ICheckView;
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

public class CheckAddFragment extends BaseFragment<ICheckView, CheckPresenter> implements ICheckView, FragmentBackHandler {

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
    EditText tvFour;
    @BindView(R.id.tvDelete)
    ImageView tvDelete;
    @BindView(R.id.tvScan)
    ImageView tvScan;
    @BindView(R.id.llbg)
    LinearLayout llbg;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvAddOne)
    TextView tvAddOne;
    @BindView(R.id.tvAddTwo)
    TextView tvAddTwo;
    @BindView(R.id.llAdd)
    LinearLayout llAdd;


    private List<ItemWhQohBean> list;
    private CheckAddAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private String wh_id;

    public static CheckAddFragment newInstance() {
        Bundle args = new Bundle();
        CheckAddFragment fragment = new CheckAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_check;
    }

    @Override
    public void initUI() {
        tvTitle.setText("新增盘点单");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");
        list = new ArrayList<>();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new CheckAddAdapter(context, list);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("提示");
                builder.setMessage("是否移除料品(" + adapter.getItem(position).getItem_name() + ")？");
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.remove(position);
                        if (adapter.getAllData().size() <= 0) {
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
        });
        recyclerView.setAdapter(adapter);
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


    @Override
    public CheckPresenter createPresenter() {
        return new CheckPresenter(getApp());
    }


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tvThree, R.id.tvAddOne, R.id.tvAddTwo, R.id.tvDelete, R.id.tvScan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                showCloseDialog();
                break;
            case R.id.tvRight:
                String mThree = tvThree.getText().toString();
                if (TextUtils.isEmpty(mThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择仓库");
                    return;
                }

                List<ItemWhQohBean> allData = adapter.getAllData();
                if (allData.size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请添加料品");
                    return;
                }
                showDialog(allData);

                break;
            case R.id.tvThree:
                if (!TextUtils.isEmpty(tvThree.getText().toString()) && adapter.getAllData().size() > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("切换仓库会清空已选料品，是否继续？");
                    builder.setCancelable(true);
                    builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.clear();
                            tvScan.setVisibility(View.GONE);
                            tvDelete.setVisibility(View.GONE);
                            startChooseDeportFragment();

                        }
                    });
                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();
                } else {
                    startChooseDeportFragment();
                }
                break;
            case R.id.tvDelete:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("提示");
                builder.setMessage("是否确认删除所有已选料品？");
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
                checkCameraPermissions("check_add");
                break;

            case R.id.tvAddOne:
                if (TextUtils.isEmpty(wh_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择仓库");
                    return;
                }
                startChooseItemFragment(wh_id);
                break;
            case R.id.tvAddTwo:
                if (TextUtils.isEmpty(wh_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择仓库");
                    return;
                }
                map.clear();
                map.put("wh_id", wh_id);
                map.put("org_id", userBean.getOrg_id());
                getPresenter().getItemWhQohByRandomList(userBean.getStaff_token(), map);
                break;
        }
    }


    //确认提交弹框
    private void showDialog(final List<ItemWhQohBean> allData) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("是否确认提交此单据？");
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("org_id", userBean.getOrg_id());
                map.put("inventory_wh", wh_id);//备注
                map.put("memo", tvFour.getText().toString());

                ArrayList<InfoBeanListBean> mList = new ArrayList<>();
                ArrayList<InfoCodeBean> mBarList = new ArrayList<>();


                for (int i = 0; i < allData.size(); i++) {
                    ItemWhQohBean adapterBean = allData.get(i);
                    InfoBeanListBean mBean = new InfoBeanListBean();
                    mBean.setItem_id(adapterBean.getItem_id());
                    mBean.setInfo_num(adapterBean.getNum() + "");
                    mList.add(mBean);

                    for (int m = 0; m < barBeanList.size(); m++) {
                        BarBean barBean = barBeanList.get(m);
                        if (barBean.getItem_id().equals(adapterBean.getItem_id())) {
                            InfoCodeBean mBarBean = new InfoCodeBean();
                            mBarBean.setItem_id(barBean.getItem_id());
                            mBarBean.setCode_num(barBean.getQty() + "");
                            mBarBean.setBar_code(barBean.getBar_code());
                            mBarBean.setCode(barBean.getItem_code());
                            mBarBean.setName(barBean.getItem_name());
                            mBarList.add(mBarBean);
                        }
                    }


                }
                Gson gson = new Gson();
                String toJson = gson.toJson(mList);
                String Json = gson.toJson(mBarList);
                map.put("inventoryInfoList", toJson);
                map.put("infoCodeList", Json);
                getPresenter().insertInventory(userBean.getStaff_token(), map);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
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
                scanBean.setItem_id(detail.getItem_code());
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

    List<BarBean> barBeanList = new ArrayList<>();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getMsg();
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
            case "6"://选择仓库
                WareHouseBean houseBean = event.getmWareHouseBean();
                tvThree.setText(houseBean.getWarehouse_name());
                wh_id = houseBean.getWarehouse_id();
                break;
            case "check_add"://扫码
                List<ScanInfoBean> listTwo = event.getmScanInBuyBean().getList();
                List<ItemWhQohBean> allData1 = adapter.getAllData();
                for (int i = 0; i < allData1.size(); i++) {
                    ItemWhQohBean listBean = allData1.get(i);
                    for (int m = 0; m < listTwo.size(); m++) {
                        if (listBean.getItem_code().equals(listTwo.get(m).getItem_id())) {
                            listBean.setNum(listTwo.get(m).getArrive_qty());
                            listBean.setItem_code(listTwo.get(m).getItem_code());
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

    @Override
    public void onGetInventoryList(List<InventoryBean> data) {

    }

    @Override
    public void OnGetInventoryDetail(InventoryBean data) {

    }

    @Override
    public void OnUpdateInventoryStatus(String data) {

    }

    @Override
    public void OnInsertInventory(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent("refresh"));
        finish();
    }

    @Override
    public void onGetItemWhQohByRandomList(List<ItemWhQohBean> data) {
        LoadingUtil.closeDialog(loading);
        if (data.size() <= 0) {
            ToastUtil.showToast(context.getApplicationContext(), "暂无随机数据");
        } else {
            adapter.clear();
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
            if (adapter.getAllData().size() > 0) {
                tvScan.setVisibility(View.VISIBLE);
                tvDelete.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    public void OnDeleteInventory(String data) {

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
