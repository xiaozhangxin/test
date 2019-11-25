package com.ak.pt.mvp.fragment.home;

import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ak.pt.R;
import com.ak.pt.bean.NoticeBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.NoticeAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.NoticePresenter;
import com.ak.pt.mvp.view.INoticeView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/1/17.
 */

public class NoticeMyFragment extends BaseFragment<INoticeView, NoticePresenter> implements INoticeView {


    Unbinder unbinder;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;
    private List<NoticeBean> list;
    private NoticeAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;

    public static NoticeMyFragment newInstance() {
        Bundle args = new Bundle();
        NoticeMyFragment fragment = new NoticeMyFragment();
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
        adapter.setOnItemClickListener(position -> {
            staretNoticeDetailFragment(adapter.getItem(position));
            map.clear();
            map.put("notice_id", adapter.getItem(position).getNotice_id());
            map.put("staff_id", userBean.getStaff_id());
            getPresenter().getNoticeDetail(userBean.getStaff_token(), map);
            adapter.getItem(position).setIs_read("1");
            adapter.notifyItemChanged(position);


        });
        recycleView.setAdapterWithProgress(adapter);
        adapter.setNoMore(R.layout.view_nomore);
        //下拉刷新
        recycleView.setRefreshingColorResources(R.color.colorPrimaryNew);
        recycleView.setRefreshListener(() -> {
            page = 1;
            refresh();
        });
        //上拉加载
        adapter.setMore(R.layout.view_more, () -> {
            page++;
            refresh();
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();
    }

    private void refresh() {
        map.put("page", page + "");
        map.put("create_id", userBean.getStaff_id());
        getPresenter().getNoticeList(userBean.getStaff_token(), map);
    }

    @Override
    public void OnGetNoticeList(List<NoticeBean> data, String total) {

        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();

        ((NoticeFragment)getParentFragment()).setTitleTotal(1, String.format(Locale.CHINA, "我的公告(%s)", total));

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
