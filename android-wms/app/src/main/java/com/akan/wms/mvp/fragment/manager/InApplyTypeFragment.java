package com.akan.wms.mvp.fragment.manager;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.OutTypeLBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.home.OutApplyTypeAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.OutApplyTypePresenter;
import com.akan.wms.mvp.view.home.IOutApplyTypeView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class InApplyTypeFragment extends BaseFragment<IOutApplyTypeView, OutApplyTypePresenter> implements IOutApplyTypeView {
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;


    private List<OutTypeLBean> list;
    private OutApplyTypeAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static InApplyTypeFragment newInstance() {
        Bundle args = new Bundle();
        InApplyTypeFragment fragment = new InApplyTypeFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_choose_deport;
    }

    @Override
    public void initUI() {
        tvTitle.setText("选择调入单单据类型");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new OutApplyTypeAdapter(context, list);
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                EventBus.getDefault().post(new FirstEvent("16", adapter.getItem(position)));
                finish();
            }
        });
        adapter.setNoMore(R.layout.view_nomore);


    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("org_id", userBean.getOrg_id());
        getPresenter().queryInTypeList(userBean.getStaff_token(), map);
    }


    @Override
    public void queryOutTypeList(List<OutTypeLBean> data) {

    }

    @Override
    public void queryInTypeList(List<OutTypeLBean> data) {
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

        }
    }


    @Override
    public OutApplyTypePresenter createPresenter() {
        return new OutApplyTypePresenter(getApp());
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
