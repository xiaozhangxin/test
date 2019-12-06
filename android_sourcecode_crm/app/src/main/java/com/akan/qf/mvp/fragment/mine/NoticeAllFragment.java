package com.akan.qf.mvp.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.NoticeBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.activity.ContentActivity;
import com.akan.qf.mvp.adapter.mine.NoticeAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.mine.NoticePresenter;
import com.akan.qf.mvp.view.mine.INoticeView;
import com.akan.qf.util.DynamicTimeFormat;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.akan.qf.Constants.LOGIN_FRAGMENT;

/**
 * Created by admin on 2018/12/19.
 */

public class NoticeAllFragment extends BaseFragment<INoticeView, NoticePresenter> implements INoticeView {


    Unbinder unbinder;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;
    private List<NoticeBean> list;
    private NoticeAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private ClassicsHeader mClassicsHeader;

    public static NoticeAllFragment newInstance() {
        Bundle args = new Bundle();
        NoticeAllFragment fragment = new NoticeAllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_notice;
    }

    @Override
    public void initUI() {
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new NoticeAdapter(context, list);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                staretNoticeDetailFragment(adapter.getItem(position));
                adapter.getItem(position).setIs_read("1");
                adapter.notifyItemChanged(position);
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


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();
    }

    private void refresh() {
        map.put("page", page + "");
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getNoticeList(userBean.getStaff_token(), map);
    }

    @Override
    public void OnGetNoticeList(List<NoticeBean> data, String total) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();

        ((NoticeNewFragment)getParentFragment()).setTitleTotal(0, String.format(Locale.CHINA, "全部公告(%s)", total));
    }

    @Override
    public void OnGetNoticeDetail(NoticeBean data) {

    }

    @Override
    public void OnGetNotReadNoticeCount(String data) {

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

        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());

    }


}
