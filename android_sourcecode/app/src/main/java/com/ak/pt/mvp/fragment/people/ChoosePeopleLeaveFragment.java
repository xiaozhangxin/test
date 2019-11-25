package com.ak.pt.mvp.fragment.people;

import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.FirstEventNew;
import com.ak.pt.bean.StaffInfoLeaveBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.people.ChooseLeavePeopleAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.people.ChoosePeopleLeavePresenter;
import com.ak.pt.mvp.view.people.IChoosePeopleLeaveView;
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

public class ChoosePeopleLeaveFragment extends BaseFragment<IChoosePeopleLeaveView, ChoosePeopleLeavePresenter> implements IChoosePeopleLeaveView {
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
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;

    private List<StaffInfoLeaveBean> list;
    private ChooseLeavePeopleAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String type;//类型
    private String uuid;//部门uuid
    private AppPermissionsBean permissionsBean;
    private int page = 1;

    public static ChoosePeopleLeaveFragment newInstance(String type, String uuid, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ChoosePeopleLeaveFragment fragment = new ChoosePeopleLeaveFragment();
        fragment.type = type;
        fragment.uuid = uuid;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_choose_leave_people;
    }

    @Override
    public void initUI() {
        switch (type) {
            case "1":
                tvTitle.setText("选择文员");
                break;
            case "0":
                tvTitle.setText("选择试压工");
                break;
        }
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ChooseLeavePeopleAdapter(context, list);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                StaffInfoLeaveBean item = adapter.getItem(position);
                EventBus.getDefault().post(new FirstEventNew("choosePeople", type, item));
                finish();
            }
        });
        recycleView.setAdapterWithProgress(adapter);
        adapter.setNoMore(R.layout.view_nomore);
        //下拉刷新
        recycleView.setRefreshingColorResources(R.color.colorPrimaryNew);
        recycleView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                refresh();
            }
        });
        //上拉加载
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                refresh();
            }
        });

        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
    }

    private void refresh() {
        map.put("page", page + "");
        getPresenter().getStaffInfoList(userBean.getStaff_token(), map);
    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("login_type", type);
        map.put("group_id", uuid);
        map.put("is_select", "1");
        map.put("is_app", "1");
        map.put("operation", "1000");
        map.put("module_id", permissionsBean.getMenu_id());
        refresh();


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
    public ChoosePeopleLeavePresenter createPresenter() {
        return new ChoosePeopleLeavePresenter(getApp());
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
    public void getStaffInfoList(List<StaffInfoLeaveBean> data) {
        if (page==1){
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }
}