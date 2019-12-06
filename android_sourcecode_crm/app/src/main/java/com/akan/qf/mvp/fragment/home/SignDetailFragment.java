package com.akan.qf.mvp.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.SignBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.SignRecordPresenter;
import com.akan.qf.mvp.view.ISignRecordView;
import com.akan.qf.util.GlideRoundTransform;
import com.akan.qf.view.img.ShowPictureActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/11/12.
 */

public class SignDetailFragment extends BaseFragment<ISignRecordView, SignRecordPresenter> implements ISignRecordView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvContent)
    TextView tvContent;
    Unbinder unbinder;
    @BindView(R.id.tvTop)
    TextView tvTop;
    @BindView(R.id.tvImgTittle)
    TextView tvImgTittle;
    @BindView(R.id.ivImg)
    ImageView ivImg;
    private SignBean bean;

    public static SignDetailFragment newInstance(SignBean bean) {
        Bundle args = new Bundle();
        SignDetailFragment fragment = new SignDetailFragment();
        fragment.bean = bean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_sign_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("签到详情");
        tvName.setText(bean.getStaff_name());
        tvDepartment.setText(bean.getDepartment_name());
        tvTime.setText(bean.getCreate_time());
        tvAddress.setText(bean.getSign_address());
        tvContent.setText(bean.getSign_content());
        tvNum.setText(bean.getSign_number() + "次");
        if (TextUtils.isEmpty(bean.getSign_image())){
            tvImgTittle.setVisibility(View.GONE);
            ivImg.setVisibility(View.GONE);
        }else {
            tvImgTittle.setVisibility(View.VISIBLE);
            ivImg.setVisibility(View.VISIBLE);
            Glide.with(getContext())
                    .load(Constants.BASE_URL + bean.getSign_image())
                    .error(R.drawable.error_img).transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 12))
                    .into(ivImg);
            ivImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List mImgList=new ArrayList();
                    mImgList.clear();
                    mImgList.add(bean.getSign_image());
                    Intent intent = new Intent(getContext(), ShowPictureActivity.class);
                    intent.putExtra("imagelist", (Serializable) mImgList);
                    intent.putExtra("position", 0);
                    getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public void initData() {

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

    }


    @Override
    public void onGetSignList(List<SignBean> data, String total) {

    }

    @Override
    public void onGetDaySignList(List<SignBean> data) {

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
