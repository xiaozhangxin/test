package com.akan.qf.mvp.fragment.adaily;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.SignBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.home.SignRecordChildAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.SignRecordPresenter;
import com.akan.qf.mvp.view.ISignRecordView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 2018/11/12.
 */

public class SignDetailNewFragment extends BaseFragment<ISignRecordView, SignRecordPresenter> implements ISignRecordView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvSignTimes)
    TextView tvSignTimes;
    @BindView(R.id.tvDay)
    TextView tvDay;
    @BindView(R.id.tvMonth)
    TextView tvMonth;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.ivAvatar)
    CircleImageView ivAvatar;

    private String detail_id;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private List<SignBean> list;
    private SignRecordChildAdapter adapter;
    private String head_img;

    public static SignDetailNewFragment newInstance(String detail_id, String head_img) {
        Bundle args = new Bundle();
        SignDetailNewFragment fragment = new SignDetailNewFragment();
        fragment.detail_id = detail_id;
        fragment.head_img = head_img;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_sign_detail_new;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.sign_detail);
        Glide.with(getContext())
                .load(Constants.BASE_URL + head_img)
                .error(R.drawable.error_img)
                .into(ivAvatar);
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new SignRecordChildAdapter(context, list, new ArrayList<String>());
        recycleView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("sign_id", detail_id);
        getPresenter().getDaySignList(userBean.getStaff_token(), map);
    }


    @Override
    public void onGetDaySignList(List<SignBean> data) {
        if (data.size() > 0) {
            SignBean signBean = data.get(0);
            tvName.setText(signBean.getStaff_name());
            tvDepartment.setText(signBean.getDepartment_name());
            String createTime = signBean.getCreate_time();
            tvTime.setText(createTime.substring(0, 10));
            tvSignTimes.setText(data.size() + "");
            tvDay.setText(createTime.substring(8, 10));
            String subDay = createTime.substring(5, 7);
            if (subDay.startsWith("0")) {
                tvMonth.setText(subDay.substring(1, 2) + "月");
            } else {
                tvMonth.setText(subDay + "月");
            }
        }
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.ivLeft)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
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
    public void onGetSignList(List<SignBean> data, String total) {

    }


    @Override
    public SignRecordPresenter createPresenter() {
        return new SignRecordPresenter(getApp());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
