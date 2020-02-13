package com.akan.wms.mvp.fragment.in;

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
import com.akan.wms.bean.AddDeliverGoodsBean;
import com.akan.wms.bean.DeliverGoodsBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.MfcBean;
import com.akan.wms.bean.OperatorBean;
import com.akan.wms.bean.PurchaseBean;
import com.akan.wms.bean.PurchaseLinesBean;
import com.akan.wms.bean.SupplierBean;
import com.akan.wms.bean.SupplierReceivesBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WhBean;
import com.akan.wms.mvp.adapter.home.SendAddAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.InBuyPresenter;
import com.akan.wms.mvp.view.home.IInBuyView;
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

public class InBuyAddFragment extends BaseFragment<IInBuyView, InBuyPresenter> implements IInBuyView, FragmentBackHandler {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.llAdd)
    LinearLayout llAdd;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.llThree)
    LinearLayout llThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.llFour)
    LinearLayout llFour;

    private List<PurchaseBean> list;
    private SendAddAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mSupplier_id = "";
    private int mPosition;
    private int mChildPosition;
    private int mDeletePosition;
    private int mMfcPosition;
    private int mMFCChildPosition;
    private int mWhPosition;
    private int mWhCChildPosition;

    public static InBuyAddFragment newInstance() {
        Bundle args = new Bundle();
        InBuyAddFragment fragment = new InBuyAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_send_add;
    }

    @Override
    public void initUI() {
        tvTitle.setText("新增送货单");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new SendAddAdapter(context, list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
        adapter.setOnStockListener(new SendAddAdapter.onSelectStockClickListener() {

            @Override
            public void onSelect(int position, int childPosition, String itemId) {
                mPosition = position;
                mChildPosition = childPosition;
                //影藏软键盘
                //hideInputMethod();
                startChooseReceiptReportFragment(itemId);
            }

            @Override
            public void onMfc(int position, int childPosition, String itemId) {
                mMfcPosition = position;
                mMFCChildPosition = childPosition;
                startChooseMfcFragment(itemId);
            }

            @Override
            public void onDelete(int position) {
                mDeletePosition = position;
                showDialog("删除");

            }

            @Override
            public void onWhManager(int position, int childPosition) {
                mWhPosition = position;
                mWhCChildPosition = childPosition;
                startProduceChooseFragment("2");
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

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.llAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                showCloseDialog();
                break;
            case R.id.tvRight:
/*                if (TextUtils.isEmpty(mSupplier_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择供应商");
                    return;
                }*/
                if (adapter.getAllData().size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择采购单");
                    return;
                }

                showDialog("完成");
                break;
/*            case R.id.tvThree:
                if (TextUtils.isEmpty(mSupplier_id) && adapter.getAllData().size() > 0) {
                    showDialog("供应商");
                    return;
                }
                startChooseSupplierFragment();
                break;*/
            case R.id.llAdd:
/*                if (TextUtils.isEmpty(mSupplier_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择供应商");
                    return;
                }*/
                startStockListFragment(mSupplier_id,"add");
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
        AddDeliverGoodsBean bean = new AddDeliverGoodsBean();
        bean.setOrg_id(userBean.getOrg_id());
        bean.setSupplier_id(mSupplier_id);
        List<PurchaseBean> allData = adapter.getAllData();
        List<SupplierReceivesBean> mList = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            List<PurchaseLinesBean> pList = allData.get(i).getPurchase_lines();
            for (int j = 0; j < pList.size(); j++) {
                SupplierReceivesBean receivesBean = new SupplierReceivesBean();
                PurchaseLinesBean linesBean = pList.get(j);
                if (linesBean.getSend_qty() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入送货数量");
                    return;
                }
                if (linesBean.getSend_qty() > linesBean.getPerOfOvertopQty()) {
                    ToastUtil.showToast(context.getApplicationContext(), "送货数量大于采购数量");
                    return;
                }

                receivesBean.setItem_id(linesBean.getItem_id());
                receivesBean.setMfc(linesBean.getMfc());
                receivesBean.setPur_id(linesBean.getPur_id() + "");
                receivesBean.setPur_qty(linesBean.getPerOfOvertopQty() + "");
                receivesBean.setSend_qty(linesBean.getSend_qty() + "");
                receivesBean.setWh_id(linesBean.getWh_id());
                receivesBean.setWh_staff_id(linesBean.getWh_manager_id());
                mList.add(receivesBean);
            }
        }
        bean.setSupplier_receives(mList);
        Gson gson = new Gson();
        String json = gson.toJson(bean);
        map.clear();
        map.put("addDeliverGoods", json);
        getPresenter().addDeliverGoods(userBean.getStaff_token(), map);
    }


    @Override
    public InBuyPresenter createPresenter() {
        return new InBuyPresenter(getApp());
    }


    @Override
    public void OnAddDeliverGoods(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent("refresh"));
        finish();

    }

    @Override
    public void OnQueryDeliverGoodsLists(List<DeliverGoodsBean> data) {

    }

    @Override
    public void OnQuerySupplierDeliverById(DeliverGoodsBean data) {

    }


    @Override
    public void qualityReceiveGoods(String data) {

    }

    @Override
    public void pastWh(String data) {

    }

    @Override
    public void rejectWh(String data) {

    }

    @Override
    public void onAddReceiveGoods(String data) {

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
        String msg = event.getMsg();
        switch (msg) {
  /*          case "4"://选择供应商
                SupplierBean bean = event.getmSupplierBean();
                tvThree.setText(bean.getName());
                tvFour.setText(bean.getCode());
                mSupplier_id = bean.getCode();
                break;*/
            case "5"://选择采购单
                PurchaseBean purchaseBean = event.getmPurchaseBean();
                tvThree.setText(purchaseBean.getSupplier_name());
                tvFour.setText(purchaseBean.getSupplier_code());
                mSupplier_id=purchaseBean.getSupplier_id();
                List<PurchaseBean> allData = adapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    if (purchaseBean.getDoc_no().equals(allData.get(i).getDoc_no())) {
                        ToastUtil.showToast(context.getApplicationContext(), "此单据已被选择");
                        return;
                    }
                }
                adapter.add(purchaseBean);
                break;
            case "7"://选择收货仓库
                WhBean whBean = event.getmWhBean();
                adapter.getItem(mPosition).getPurchase_lines().get(mChildPosition).setWh_name(whBean.getWarehouse_name());
                adapter.getItem(mPosition).getPurchase_lines().get(mChildPosition).setWh_id(whBean.getWh_id());
                adapter.notifyDataSetChanged();
                break;
            case "23"://选择厂牌
                MfcBean mfcBean = event.getmMfcBean();
                adapter.getItem(mMfcPosition).getPurchase_lines().get(mMFCChildPosition).setMfc_name(mfcBean.getMfc_name());
                adapter.getItem(mMfcPosition).getPurchase_lines().get(mMFCChildPosition).setMfc(mfcBean.getMfc());
                adapter.notifyDataSetChanged();
                break;

            case "9"://选择库管员
                OperatorBean operatorBean = event.getmOperatorBean();
                adapter.getItem(mWhPosition).getPurchase_lines().get(mWhCChildPosition).setWh_manager(operatorBean.getOperator_name());
                adapter.getItem(mWhPosition).getPurchase_lines().get(mWhCChildPosition).setWh_manager_id(operatorBean.getOperator_code());
                adapter.notifyDataSetChanged();
                break;
        }
    }


}
