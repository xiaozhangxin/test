package com.ak.pt.mvp.fragment.adaily;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.SignBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.SignRecordChildAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.SignPresenter;
import com.ak.pt.mvp.view.ISignView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.bumptech.glide.Glide;
import com.uniquext.android.widget.view.CornerImageView;;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.uniquext.android.widget.view.CornerImageView;

/**
 * Created by admin on 2018/11/12.
 */

public class SignDetailNewFragment extends BaseFragment<ISignView, SignPresenter> implements ISignView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRightTwo)
    ImageView ivRightTwo;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.ivAvatar)
    CornerImageView ivAvatar;
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
        map.put("sign_id", detail_id);
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new SignRecordChildAdapter(context, list, new ArrayList<String>());
        recycleView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();

        getPresenter().getDaySignList(userBean.getStaff_token(), map);
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

    @Override
    public void onUploadFile(String data) {

    }

    @Override
    public void onInsertSign(String data) {

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.showToast(context.getApplicationContext(),e.getMessage());
    }


    @Override
    public void onUploadFiles(String[] data) {

    }

    @Override
    public void onGetSignList(List<SignBean> data) {

    }


    @Override
    public SignPresenter createPresenter() {
        return new SignPresenter(getApp());
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
