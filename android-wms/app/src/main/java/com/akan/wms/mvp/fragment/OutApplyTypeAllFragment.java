package com.akan.wms.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.StaffOrgsBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.home.ProduceThreeAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.ChooseGroupPresenter;
import com.akan.wms.mvp.view.IChooseGroupView;
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

public class OutApplyTypeAllFragment extends BaseFragment<IChooseGroupView, ChooseGroupPresenter> implements IChooseGroupView {
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;


    private List<StaffOrgsBean> listThree;
    private ProduceThreeAdapter adapterThree;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static OutApplyTypeAllFragment newInstance() {
        Bundle args = new Bundle();
        OutApplyTypeAllFragment fragment = new OutApplyTypeAllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_choose_deport;
    }

    @Override
    public void initUI() {
        tvTitle.setText("选择组织");
        listThree = new ArrayList<>();
        adapterThree = new ProduceThreeAdapter(context, listThree);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapterWithProgress(adapterThree);
        recyclerView.setAdapter(adapterThree);
        adapterThree.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                EventBus.getDefault().post(new FirstEvent("11", adapterThree.getItem(position)));
                finish();
            }
        });
        adapterThree.setNoMore(R.layout.view_nomore);


    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getPresenter().queryAllOrg(userBean.getStaff_token(), map);
    }

    @Override
    public void onQueryAllOrg(List<StaffOrgsBean> data) {
        adapterThree.clear();
        adapterThree.addAll(data);
        adapterThree.notifyDataSetChanged();
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
    public ChooseGroupPresenter createPresenter() {
        return new ChooseGroupPresenter(getApp());
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
