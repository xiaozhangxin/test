package com.akan.qf.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.NoticeBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.mine.NoticeAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.mine.NoticePresenter;
import com.akan.qf.mvp.view.mine.INoticeView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/11/7.
 */

public class NoticeSearchFragment extends BaseFragment<INoticeView, NoticePresenter> implements INoticeView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;


    private List<NoticeBean> list;
    private NoticeAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;

    public static NoticeSearchFragment newInstance() {
        Bundle args = new Bundle();
        NoticeSearchFragment fragment = new NoticeSearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_notice_search;
    }

    @Override
    public void initUI() {
        tvTitle.setText("公告搜索");
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new NoticeAdapter(context, list);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                staretNoticeDetailFragment(adapter.getItem(position));
                map.clear();
                map.put("notice_id", adapter.getItem(position).getNotice_id());
                map.put("staff_id", userBean.getStaff_id());
                getPresenter().getNoticeDetail(userBean.getStaff_token(), map);
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
        etName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String s = etName.getText().toString();
                    if (TextUtils.isEmpty(s)) {
                        map.put("notice_title", "");
                    } else {
                        map.put("notice_title", s);
                    }
                    page = 1;
                    refresh();
                }
                return false;
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
        map.put("notice_title", etName.getText().toString());
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

    }

    @Override
    public void OnGetNoticeDetail(NoticeBean data) {

    }

    @Override
    public void OnGetNotReadNoticeCount(String data) {

    }

    @OnClick(R.id.ivLeft)
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
