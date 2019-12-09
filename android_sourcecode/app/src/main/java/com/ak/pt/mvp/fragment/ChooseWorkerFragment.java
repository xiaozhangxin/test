package com.ak.pt.mvp.fragment;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.FirstEventWorker;
import com.ak.pt.bean.UserBean;
import com.ak.pt.bean.WorkerBean;
import com.ak.pt.bean.staffGroupBeans;
import com.ak.pt.mvp.adapter.WorkerListAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.ChoosePresenter;
import com.ak.pt.mvp.view.IChooseView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
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

/**
 * Created by admin on 2019/1/16.
 */

public class ChooseWorkerFragment extends BaseFragment<IChooseView, ChoosePresenter> implements IChooseView {
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
    EasyRecyclerView recycleView;
    private List<WorkerBean> list;
    private WorkerListAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private List<WorkerBean> mList = new ArrayList<>();
    private AppPermissionsBean permissionsBean;

    public static ChooseWorkerFragment newInstance(AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ChooseWorkerFragment fragment = new ChooseWorkerFragment();
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_choose_worker;
    }

    @Override
    public void initUI() {
        tvTitle.setText("选择试压水工");
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new WorkerListAdapter(context, list);
        adapter.setOnItemClickListener(position -> {
            EventBus.getDefault().post(new FirstEventWorker("1",
                    new WorkerBean(adapter.getItem(position).getStaff_id(),
                            adapter.getItem(position).getStaff_name(),
                            adapter.getItem(position).getPhone())));
            finish();
        });
        recycleView.setAdapterWithProgress(adapter);
        adapter.setNoMore(R.layout.view_nomore);
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
                List<WorkerBean> AllList = new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    String s1 = mList.get(i).getStaff_name() + mList.get(i).getPhone();
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
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("staff_id", userBean.getStaff_id());
        map.put("is_select", "1");
        map.put("is_app", "1");
        map.put("operation", "1000");
        map.put("module_id", permissionsBean.getMenu_id());
        getPresenter().getWaterList(userBean.getStaff_token(), map);
    }

    @Override
    public void OnGetWaterList(List<WorkerBean> data) {
        mList.addAll(data);
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetStaffGroupTreeByStaff(List<staffGroupBeans> data) {

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
    public ChoosePresenter createPresenter() {
        return new ChoosePresenter(getApp());
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