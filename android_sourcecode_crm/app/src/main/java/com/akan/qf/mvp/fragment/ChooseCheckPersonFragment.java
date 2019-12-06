package com.akan.qf.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.FirstEventNew;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.home.MyAndParentDepartmentAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.ChooseCheckPersonPresenter;
import com.akan.qf.mvp.view.home.IChooseCheckPersonView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
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

/**
 * Created by admin on 2018/10/23.
 */

public class ChooseCheckPersonFragment extends BaseFragment<IChooseCheckPersonView, ChooseCheckPersonPresenter> implements IChooseCheckPersonView {
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private List<MeParentBean> list;
    private MyAndParentDepartmentAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    List<MeParentBean> mList = new ArrayList<>();
    private String type;

    public static ChooseCheckPersonFragment newInstance(String type) {
        Bundle args = new Bundle();
        ChooseCheckPersonFragment fragment = new ChooseCheckPersonFragment();
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_choose_checkperson;
    }

    @Override
    public void initUI() {
        switch (type) {
            case "1":
                tvTitle.setText("选择审批人");
                break;
            case "2":
                tvTitle.setText("选择抄送人");
                break;
        }

        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MyAndParentDepartmentAdapter(context, list);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                EventBus.getDefault().post(new FirstEventNew(type, adapter.getItem(position)));
                finish();
            }
        });
        recycleView.setAdapter(adapter);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(s.toString())) {
                    adapter.clear();
                    adapter.addAll(mList);
                    adapter.notifyDataSetChanged();
                    return;
                }
                List<MeParentBean> AllList = new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    String s1 = mList.get(i).getStaff_name() + mList.get(i).getJob_name();
                    if (s1.contains(s.toString())) {
                        AllList.add(mList.get(i));
                    }
                }
                if (AllList.size() > 0) {
                    adapter.clear();
                    adapter.addAll(AllList);
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.clear();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("group_id", userBean.getDepartment_id());
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getMyAndParentDepartmentStaffList(userBean.getStaff_token(), map);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onGetMyAndParentDepartmentStaffList(List<MeParentBean> data) {
        mList.addAll(data);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public ChooseCheckPersonPresenter createPresenter() {
        return new ChooseCheckPersonPresenter(getApp());
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