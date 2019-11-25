package com.ak.pt.mvp.fragment.water;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.FeedBackBean;
import com.ak.pt.bean.FilterBean;
import com.ak.pt.bean.FirstEventFilter;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.water.FeedBackListAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.fragment.FilterFragment;
import com.ak.pt.mvp.presenter.water.FeedbackPresenter;
import com.ak.pt.mvp.view.water.IFeedbackView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/23.
 */

public class FeedbackListFragment extends BaseFragment<IFeedbackView, FeedbackPresenter> implements IFeedbackView {

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
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;
    @BindView(R.id.drawer_content)
    FrameLayout drawerContent;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private List<FeedBackBean> list;
    private FeedBackListAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private int deletePosition;
    private AppPermissionsBean permissionsBean;

    public static FeedbackListFragment newInstance(AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        FeedbackListFragment fragment = new FeedbackListFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_list_public;
    }

    @Override
    public void initUI() {
        tvTitle.setText("产品质量反馈列表");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(R.string.filter);

        //初始化筛选界面
        Fragment fragment = new FilterFragment();
        FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString("stateType", "3");
        bundle.putString("app_data", permissionsBean.getApp_data());
        bundle.putString("permission", permissionsBean.getApp_department());
        bundle.putString("module_id", permissionsBean.getMenu_id());
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commit();

        etName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    page = 1;
                    refresh();
                }
                return false;
            }
        });

        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new FeedBackListAdapter(context, list);
        recycleView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                deletePosition = position;
                startFeedBackDetailFragment(adapter.getItem(position).getQuality_id(),permissionsBean);
            }
        });
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
    }

    private void refresh() {
        map.put("page", page + "");
        map.put("all_name", etName.getText().toString());

        getPresenter().getQualityFeedbackList(userBean.getStaff_token(), map);

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("is_select", "1");
        map.put("is_app", "1");
        map.put("operation", "1000");
        map.put("module_id", permissionsBean.getMenu_id());
        refresh();

    }


    @Override
    public void onInsertQualityFeedback(String data) {

    }

    @Override
    public void onDeleteQualityFeedback(String data) {

    }

    @Override
    public void onDeleteQualityFile(String data) {

    }

    @Override
    public void onUpdateQualityFeedback(String data) {

    }

    @Override
    public void OnGetQualityFeedbackList(List<FeedBackBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetQualityFeedbackDetail(FeedBackBean data) {

    }

    @Override
    public void onUploadFiles(String[] data) {

    }


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.ivSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                drawerLayout.openDrawer(drawerContent);
                break;
            case R.id.ivSearch:
                page = 1;
                recycleView.setRefreshing(true);
                refresh();
                break;
        }
    }


    @Override
    public FeedbackPresenter createPresenter() {
        return new FeedbackPresenter(getApp());
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
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventFilter event) {
        drawerLayout.closeDrawer(drawerContent);
        switch (event.getMsg()) {
            case "1":
                FilterBean filterBean = event.getFilterBean();
                map.put("start_time", filterBean.getStartTime());
                map.put("end_time", filterBean.getEndTime());
                map.put("job_name", filterBean.getPostNames());
                map.put("group_parent_uuid", filterBean.getUuid());
                page = 1;
                recycleView.setRefreshing(true);
                refresh();
                break;
            case "filter_delete":
                adapter.remove(deletePosition);
                break;
        }

    }


}
