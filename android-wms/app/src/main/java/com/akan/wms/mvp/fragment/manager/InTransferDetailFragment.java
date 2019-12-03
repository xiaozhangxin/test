package com.akan.wms.mvp.fragment.manager;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.InforListBean;
import com.akan.wms.bean.RecordsBean;
import com.akan.wms.bean.TransferInBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.RecordAdapter;
import com.akan.wms.mvp.adapter.home.InTransferDetailAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.InTransferPresenter;
import com.akan.wms.mvp.view.home.IInTransferView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.akan.wms.view.BottomPopWindow;

import org.greenrobot.eventbus.EventBus;

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

public class InTransferDetailFragment extends BaseFragment<IInTransferView, InTransferPresenter> implements IInTransferView {

    Unbinder unbinder;

    public static final int RC_CAMERA = 0X01;
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

    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.tvDelete)
    ImageView tvDelete;
    @BindView(R.id.tvScan)
    ImageView tvScan;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvSeven)
    TextView tvSeven;
    @BindView(R.id.addImg)
    ConstraintLayout addImg;
    @BindView(R.id.tvAddOne)
    TextView tvAddOne;
    @BindView(R.id.tvAddTwo)
    TextView tvAddTwo;
    @BindView(R.id.recordRecyclerView)
    RecyclerView recordRecyclerView;
    private List<TransferInBean.LineBeanListBean> list;
    private InTransferDetailAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mId;
    private BottomPopWindow popWindow;
    private List<RecordsBean> listRecord;
    private RecordAdapter adapterRecord;

    public static InTransferDetailFragment newInstance(String id) {
        Bundle args = new Bundle();
        InTransferDetailFragment fragment = new InTransferDetailFragment();
        fragment.mId = id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_detail_in_transfer;
    }

    @Override
    public void initUI() {
        tvTitle.setText("调拨入库详情");
        stateOne.setText("已点收");
        stateTwo.setText("已入库");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new InTransferDetailAdapter(context, list);
        recyclerView.setAdapter(adapter);

        //审核记录
        recordRecyclerView.setNestedScrollingEnabled(false);
        listRecord = new ArrayList<>();
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapterRecord = new RecordAdapter(context, this.listRecord);
        recordRecyclerView.setAdapter(adapterRecord);
    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getData();

    }

    private void getData() {
        map.put("id", mId);
        getPresenter().queryTransferInById(userBean.getStaff_token(), map);
    }


    @Override
    public void onQueryTransferInById(TransferInBean data) {
        // 0 已配货 1已审核
        updateState(data.getStatus() + "");
        tvOne.setText(data.getDoc_no());
        tvTwo.setText(data.getCreate_name());
        tvThree.setText(data.getCreate_time());
        tvFour.setText(data.getDoc_type_name());
        tvAddOne.setText(data.getOut_org_name());
        tvAddTwo.setText(data.getIn_org_name());
        tvSeven.setText(data.getRemark());
        tvTittle.setText("调出单  " + data.getOut_no());
        adapter.clear();
        adapter.addAll(data.getLineBeanList());
        adapter.notifyDataSetChanged();
        adapterRecord.clear();
        adapterRecord.addAll(data.getRecordsBeans());
        adapterRecord.notifyDataSetChanged();
    }


    //更新顶部状态
    private void updateState(String mState) {
        switch (mState) {
            case "0":
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("入库");
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("操作");
                break;
            case "1":
                tvRight.setVisibility(View.GONE);
                ivTwo.setImageResource(R.drawable.state_h);
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                ivTwo.setScaleY(1.5f);
                ivTwo.setScaleX(1.5f);
                lineOne.setBackgroundResource(R.color.colorPrimary);
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
                showDialog();
                break;
        }
    }


    //流程变更弹框
    private void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("是否确认入库？");

        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("id", mId);
                getPresenter().transferInWh(userBean.getStaff_token(), map);
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


    //检测拍摄权限
    @AfterPermissionGranted(RC_CAMERA)
    private void checkCameraPermissions(String type) {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(context, perms)) {
            //开启扫码
            startScanFragment(new ArrayList<InforListBean>(), type);
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera), RC_CAMERA, perms);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public InTransferPresenter createPresenter() {
        return new InTransferPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void onQueryTransferInPage(List<TransferInBean> data) {

    }


    @Override
    public void onAddTransferIn(String data) {

    }

    @Override
    public void onTransferInWh(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        getData();
    }
}

