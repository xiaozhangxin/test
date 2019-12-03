package com.akan.wms.mvp.fragment.in;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.RecordsBean;
import com.akan.wms.bean.RevLinesBean;
import com.akan.wms.bean.SaleRcvBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.RecordAdapter;
import com.akan.wms.mvp.adapter.home.InSaleReturnDetailAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.InSalePresenter;
import com.akan.wms.mvp.view.home.IInSaleView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class InSaleReturnDetailFragment extends BaseFragment<IInSaleView, InSalePresenter> implements IInSaleView {

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
    @BindView(R.id.tvScan)
    ImageView tvScan;
    @BindView(R.id.llbg)
    LinearLayout llbg;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.addImg)
    ConstraintLayout addImg;
    @BindView(R.id.recordRecyclerView)
    RecyclerView recordRecyclerView;


    private List<RecordsBean> listRecord;
    private RecordAdapter adapterRecord;
    private List<RevLinesBean> list;
    private InSaleReturnDetailAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mId;

    public static InSaleReturnDetailFragment newInstance(String id) {
        Bundle args = new Bundle();
        InSaleReturnDetailFragment fragment = new InSaleReturnDetailFragment();
        fragment.mId = id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_detail_in_sale_return;
    }

    @Override
    public void initUI() {
        tvTitle.setText("销售退货单详情");
        stateOne.setText("审核中");
        stateTwo.setText("已审核");
        list = new ArrayList<>();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new InSaleReturnDetailAdapter(context, this.list);
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
        getPresenter().querySaleRcv(userBean.getStaff_token(), map);
    }

    //流程变更弹框
    private void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("确认审核通过？");
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                agreeOut();
            }

        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();

    }


    //同意入库
    private void agreeOut() {
        map.clear();
        map.put("id", mId);
        getPresenter().whSaleRcv(userBean.getStaff_token(), map);
    }


    @Override
    public void onQuerySaleRcvPage(List<SaleRcvBean> data) {
        ToastUtil.showToast(context.getApplicationContext(), "已审核");
        getData();
    }

    @Override
    public void OnQuerySaleRcv(SaleRcvBean data) {
        // 0 已点收 1已入库
        updateState(data.getStatus() + "");
        tvOne.setText(data.getDoc_no());
        tvTwo.setText(data.getDoc_type_name());
        tvThree.setText(data.getBusiness_date());
        tvFour.setText(data.getCustomer_name());
        adapter.clear();
        adapter.addAll(data.getRevLines());
        adapter.notifyDataSetChanged();
        adapterRecord.clear();
        adapterRecord.addAll(data.getRecordsBeans());
        adapterRecord.notifyDataSetChanged();
    }

    @Override
    public void OnWhSaleRcv(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent("refresh"));
        getData();
    }


    //更新顶部状态
    private void updateState(String mState) {
        switch (mState) {
            case "3":
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("审核");
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                break;
            case "5":
                tvRight.setVisibility(View.GONE);
                lineOne.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                ivTwo.setImageResource(R.drawable.state_h);
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
                ivTwo.setScaleY(1.5f);
                ivTwo.setScaleX(1.5f);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public InSalePresenter createPresenter() {
        return new InSalePresenter(getApp());
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


}
