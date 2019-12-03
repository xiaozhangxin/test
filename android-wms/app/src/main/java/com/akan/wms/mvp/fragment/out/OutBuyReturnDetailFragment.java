package com.akan.wms.mvp.fragment.out;

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
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.RecordsBean;
import com.akan.wms.bean.RtnedGoodsBean;
import com.akan.wms.bean.RtnedLinesBean;
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.RecordAdapter;
import com.akan.wms.mvp.adapter.home.OutBuyReturnDetailAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.OutBuyReturnPresenter;
import com.akan.wms.mvp.view.home.IOutBuyReturnView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.akan.wms.view.BottomPopWindow;

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

public class OutBuyReturnDetailFragment extends BaseFragment<IOutBuyReturnView, OutBuyReturnPresenter> implements IOutBuyReturnView, View.OnClickListener {

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
    @BindView(R.id.lineOne)
    View lineOne;
    @BindView(R.id.ivTwo)
    ImageView ivTwo;
    @BindView(R.id.stateTwo)
    TextView stateTwo;
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
    @BindView(R.id.tvSix)
    TextView tvSix;
    @BindView(R.id.addImg)
    ConstraintLayout addImg;
    @BindView(R.id.recordRecyclerView)
    RecyclerView recordRecyclerView;

    private List<RecordsBean> listRecord;
    private RecordAdapter adapterRecord;
    private List<RtnedLinesBean> list;
    private OutBuyReturnDetailAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mId;
    private BottomPopWindow popWindow;
    private int mScanPosition;

    public static OutBuyReturnDetailFragment newInstance(String id) {
        Bundle args = new Bundle();
        OutBuyReturnDetailFragment fragment = new OutBuyReturnDetailFragment();
        fragment.mId = id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_detail_out_buy_return;
    }

    @Override
    public void initUI() {
        tvTitle.setText("采购退货单详情");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new OutBuyReturnDetailAdapter(context, this.list);
        recyclerView.setAdapter(adapter);
        adapter.setOnStockListener(new OutBuyReturnDetailAdapter.onSelectStockClickListener() {
            @Override
            public void onScan(int position) {
                mScanPosition = position;
                checkCameraPermissions("out_buy_detail");
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
                    case R.id.btTwo:
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
        map.put("id", mId);
        getPresenter().queryRtnedGoodsById(userBean.getStaff_token(), map);
    }

    //流程变更弹框
    private void showDialog(final String type) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        switch (type) {
            case "作废":
                builder.setMessage("是否作废此单据？");
                break;
            case "同意":
                builder.setMessage("是否同意出库？");
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
                    case "作废":
                        refuseOut();
                        break;
                    case "同意":
                        agreeOut();
                        break;
                    case "删除":
                        map.clear();
                        map.put("id", mId);
                        getPresenter().delRtnedGoods(userBean.getStaff_token(), map);
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


    //同意出库
    private void agreeOut() {
        map.clear();
        map.put("id", mId);
        getPresenter().pastRtnedGoods(userBean.getStaff_token(), map);
    }

    //作废
    private void refuseOut() {
        map.clear();
        map.put("id", mId);
        getPresenter().invalidRtnedGoods(userBean.getStaff_token(), map);
    }

    @Override
    public void onQueryRtnedGoodsById(RtnedGoodsBean data) {
        // 0 已配货 1已出库  2作废
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
        tvOne.setText(data.getDoc_no().toString());
        tvTwo.setText(data.getCreator_name());
        tvThree.setText(data.getCreate_time());
        tvFour.setText(data.getSupplier_name());
        tvFive.setText(data.getSupplier_code() + "");
        if (TextUtils.isEmpty(data.getRtn_memo())) {
            tvSix.setText("暂无备注");
        } else {
            tvSix.setText(data.getRtn_memo());
        }
        adapter.clear();
        adapter.addAll(data.getRtned_lines());
        adapter.notifyDataSetChanged();
        adapterRecord.clear();
        adapterRecord.addAll(data.getRecordsBeans());
        adapterRecord.notifyDataSetChanged();
    }

    @Override
    public void onDelRtnedGoods(String data) {
        EventBus.getDefault().post(new FirstEvent("delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    //更新顶部状态
    private void updateState(String mState) {
        switch (mState) {
            case "0":
                adapter.setState("1");
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("操作");
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                break;
            case "1":
                adapter.setState("2");
                tvRight.setVisibility(View.GONE);
                ivTwo.setImageResource(R.drawable.state_h);
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                ivTwo.setScaleY(1.5f);
                ivTwo.setScaleX(1.5f);
                lineOne.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                stateTwo.setTextColor(getResources().getColor(R.color.colorPrimary));
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
                    case "操作":
                        popWindow.showAtLocation(tvRight, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        break;
                }
                break;
        }
    }


    //----------------------------------------扫码--------------------------------------------------
    public static final int RC_CAMERA = 0X01;

    //检测拍摄权限
    @AfterPermissionGranted(RC_CAMERA)
    private void checkCameraPermissions(String type) {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(context, perms)) {
            RtnedLinesBean rtnedLinesBean = adapter.getItem(mScanPosition);
            List<ScanInfoBean> scanList = new ArrayList<>();
            List<RtnedLinesBean.RtnedGodsLinesBean> rtn_lines = rtnedLinesBean.getRtned_gods_lines();
            for (int i = 0; i < rtn_lines.size(); i++) {
                ScanInfoBean scanBean = new ScanInfoBean();
                RtnedLinesBean.RtnedGodsLinesBean detail = rtn_lines.get(i);
                scanBean.setItem_id(detail.getItem_code());
                scanBean.setItem_name(detail.getItem_name());
                scanBean.setItem_spec(detail.getItem_spec());
                scanBean.setSend_qty(detail.getAlloc_qty());//配货数量
                scanBean.setArrive_qty(detail.getCheck_qty());//核定数量
                scanBean.setBarList(rtnedLinesBean.getBarList());//历史条码
                scanList.add(scanBean);
            }
            startInBuyScanFragment(scanList, type);
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera), RC_CAMERA, perms);
        }
    }


    @Override
    public OutBuyReturnPresenter createPresenter() {
        return new OutBuyReturnPresenter(getApp());
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
            case "24":
                List<BarBean> barBeanList = event.getmScanListBean().getList();
                adapter.getItem(mScanPosition).setBarList(barBeanList);
                break;

        }
    }

    @Override
    public void OnAddRtnedGoods(String data) {

    }

    @Override
    public void onInvalidRtnedGoods(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        getData();
    }

    @Override
    public void onPastRtnedGoods(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        getData();
    }

    @Override
    public void OnQueryRtnedGoodsList(List<RtnedGoodsBean> data) {

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
}
