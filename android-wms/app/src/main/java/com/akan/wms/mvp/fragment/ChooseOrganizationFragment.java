package com.akan.wms.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.StaffOrgsBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.ChooseOrgListAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.ChooseOrgPresenter;
import com.akan.wms.mvp.view.IChooseOrgView;
import com.akan.wms.util.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChooseOrganizationFragment extends BaseFragment<IChooseOrgView, ChooseOrgPresenter> implements IChooseOrgView {
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
    @BindView(R.id.tvNow)
    TextView tvNow;
    @BindView(R.id.tvNowTittle)
    TextView tvNowTittle;

    private List<StaffOrgsBean> list;
    private ChooseOrgListAdapter adapter;
    private int page = 0;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private String detail_id;
    private String orgName;


    public static ChooseOrganizationFragment newInstance(String detail_id, String orgName) {
        Bundle args = new Bundle();
        ChooseOrganizationFragment fragment = new ChooseOrganizationFragment();
        fragment.detail_id = detail_id;
        fragment.orgName = orgName;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_choose_org;
    }

    @Override
    public void initUI() {
        tvTitle.setText("组织管理");
        if (TextUtils.isEmpty(orgName)) {
            tvNowTittle.setVisibility(View.GONE);
            tvNow.setVisibility(View.GONE);
        }
        tvNow.setText(orgName);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ChooseOrgListAdapter(context, list, orgName);
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setOnStockListener(new ChooseOrgListAdapter.OnChangeClickListener() {
            @Override
            public void onChange(int position) {
                EventBus.getDefault().post(new FirstEvent("change_org", adapter.getItem(position)));
                finish();
            }
        });

    }


    @Override
    public void initData() {
        map.clear();
        map.put("staff_account", detail_id);
        getPresenter().queryStaffOrgs(map);
    }

    @Override
    public void queryStaffOrgs(List<StaffOrgsBean> data) {


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
    public ChooseOrgPresenter createPresenter() {
        return new ChooseOrgPresenter(getApp());
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
