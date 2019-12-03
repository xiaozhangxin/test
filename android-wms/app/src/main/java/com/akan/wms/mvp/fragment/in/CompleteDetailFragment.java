package com.akan.wms.mvp.fragment.in;

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
import com.akan.wms.bean.CompleteDecBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.RecordsBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.RecordAdapter;
import com.akan.wms.mvp.adapter.home.CompleteDetailAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.CompletePresenter;
import com.akan.wms.mvp.view.home.ICompleteView;
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

public class CompleteDetailFragment extends BaseFragment<ICompleteView, CompletePresenter> implements ICompleteView {

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
    @BindView(R.id.tvSeven)
    TextView tvSeven;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.addImg)
    ConstraintLayout addImg;
    @BindView(R.id.recordRecyclerView)
    RecyclerView recordRecyclerView;
    private List<RecordsBean> listRecord;
    private RecordAdapter adapterRecord;
    private List<CompleteDecBean.CompleteDecLinesBean> list;
    private CompleteDetailAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mId;
    private BottomPopWindow popWindow;

    public static CompleteDetailFragment newInstance(String id) {
        Bundle args = new Bundle();
        CompleteDetailFragment fragment = new CompleteDetailFragment();
        fragment.mId = id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_detail_complete;
    }

    @Override
    public void initUI() {
        tvTitle.setText("完工申报详情");
        stateOne.setText("未处理");
        stateTwo.setText("已审核");
        list = new ArrayList<>();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new CompleteDetailAdapter(context, list);
        recyclerView.setAdapter(adapter);

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

        //审核记录
        recordRecyclerView.setNestedScrollingEnabled(false);
        listRecord = new ArrayList<>();
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapterRecord = new RecordAdapter(context, this.listRecord);
        recordRecyclerView.setAdapter(adapterRecord);

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
        map.put("org_id", userBean.getOrg_id());
        getPresenter().queryCompleteDec(userBean.getStaff_token(), map);
    }

    @Override
    public void onQueryCompleteDec(CompleteDecBean data) {
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


        tvOne.setText(data.getDoc_no());
        tvTwo.setText(data.getCreator_name());
        tvThree.setText(data.getCreate_time());
        tvFour.setText(data.getReport_name());
        if (TextUtils.isEmpty(data.getRemark())) {
            tvSeven.setText("暂无备注");
        } else {
            tvSeven.setText(data.getRemark());
        }
        adapter.setType(data.getReport_type());
        adapter.clear();
        adapter.addAll(data.getComplete_dec_lines());
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
                tvRight.setText("操作");
                ivOne.setScaleY(1.5f);
                ivOne.setScaleX(1.5f);
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


    //流程变更弹框
    private void showDialog(final String type) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        switch (type) {
            case "同意":
                builder.setMessage("是否确认审核通过？");
                break;
            case "作废":
                builder.setMessage("是否确认审作废此单据？");
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
                    case "同意":
                        map.clear();
                        map.put("id", mId);
                        getPresenter().pastCompleteDec(userBean.getStaff_token(), map);
                        break;
                    case "作废":
                        map.clear();
                        map.put("id", mId);
                        getPresenter().invalidCompleteDec(userBean.getStaff_token(), map);
                        break;
                    case "删除":
                        map.clear();
                        map.put("id", mId);
                        getPresenter().delCompleteDec(userBean.getStaff_token(), map);
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public CompletePresenter createPresenter() {
        return new CompletePresenter(getApp());
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
    public void onQueryCompleteDecList(List<CompleteDecBean> data) {

    }


    @Override
    public void onAddCompleteDec(String data) {

    }

    @Override
    public void onPastCompleteDec(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        getData();
    }

    @Override
    public void onInvalidCompleteDec(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        getData();
    }

    @Override
    public void onDelCompleteDec(String data) {
        EventBus.getDefault().post(new FirstEvent("delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }
}
