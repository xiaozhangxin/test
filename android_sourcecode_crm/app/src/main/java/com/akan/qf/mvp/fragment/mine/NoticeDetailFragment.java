package com.akan.qf.mvp.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.NoticeBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.mine.NoticePresenter;
import com.akan.qf.mvp.view.mine.INoticeView;
import com.akan.qf.util.SpSingleInstance;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/11/28.
 */

public class NoticeDetailFragment extends BaseFragment<INoticeView, NoticePresenter> implements INoticeView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.annex)
    RelativeLayout annex;
    Unbinder unbinder;
    private NoticeBean bean;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static NoticeDetailFragment newInstance(NoticeBean bean) {
        Bundle args = new Bundle();
        NoticeDetailFragment fragment = new NoticeDetailFragment();
        fragment.bean = bean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_notice_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("公告通知详情");
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("notice_id", bean.getNotice_id());
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getNoticeDetail(userBean.getStaff_token(), map);
    }

    @Override
    public void OnGetNoticeDetail(NoticeBean data) {
        bean = data;
        EventBus.getDefault().post(new FirstEvent("refreshNotice"));
        tvTime.setText(data.getCreate_time());
        tvTittle.setText(data.getNotice_title());
        tvContent.setText(data.getNotice_content());
        tvNo.setText((data.getRead_ids().split(",").length - 1) + "人已读");
        tvName.setText(data.getCreate_name());
        if (data.getNoticeFileBeans().size() <= 0) {
            annex.setVisibility(View.GONE);
        } else {
            annex.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.ivLeft, R.id.annex})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.annex:
                startNoticeFileFragment(bean.getNoticeFileBeans(), bean.getCreate_name(),new ArrayList<String>(),"1");

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
    public NoticePresenter createPresenter() {
        return new NoticePresenter(getApp());
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

    }

    @Override
    public void OnGetNoticeList(List<NoticeBean> data, String total) {

    }


    @Override
    public void OnGetNotReadNoticeCount(String data) {

    }
}