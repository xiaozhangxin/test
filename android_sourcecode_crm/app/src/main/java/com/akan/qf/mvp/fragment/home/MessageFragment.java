package com.akan.qf.mvp.fragment.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
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
import com.akan.qf.bean.AppHomeMenuTreeBean;
import com.akan.qf.bean.AppVersionBean;
import com.akan.qf.bean.BookBean;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.StaffMessageBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.message.MessageAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.message.MessagePresenter;
import com.akan.qf.mvp.view.message.IMessageView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MessageFragment extends BaseFragment<IMessageView, MessagePresenter> implements IMessageView {


    Unbinder unbinder;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;

    private List<StaffMessageBean> list;
    private MessageAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private String type;

    public static MessageFragment newInstance(String type) {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initUI() {
        if ("1".equals(type)) {
            imgBack.setVisibility(View.VISIBLE);
            imgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            imgBack.setVisibility(View.GONE);
        }
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MessageAdapter(context, list);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                staretMessageDetailFragment(adapter.getItem(position).getMsg_id(), adapter.getItem(position).getIs_read(),adapter.getItem(position).getType());
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
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("你确定要删除此消息吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        map.clear();
                        map.put("ids", adapter.getItem(position).getMsg_id());
                        getPresenter().deleteStaffMessages(userBean.getStaff_token(), map);
                    }
                });
                builder.setNegativeButton("取消", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return false;
            }
        });


        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String s = etSearch.getText().toString();
                    if (TextUtils.isEmpty(s)) {
                        map.put("msg_title", "");
                    } else {
                        map.put("msg_title", s);
                    }
                    page = 1;
                    refresh();
                }
                return false;
            }
        });

    }

    private void refresh() {
        map.put("page", page + "");
        map.put("msg_title", etSearch.getText().toString());
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getStaffMessageList(userBean.getStaff_token(), map);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onGetStaffMessageList(List<StaffMessageBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
        EventBus.getDefault().post(new FirstEvent("refreshNews"));
    }

    @Override
    public void onGetAddressBookList(List<BookBean> data, String total) {

    }

    @Override
    public void onGetNotReadMessageCount(String data) {

    }

    @Override
    public void onDeleteStaffMessages(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        page = 1;
        refresh();

    }

    @Override
    public void OnGetAppVersionDetail(AppVersionBean data) {

    }

    @Override
    public void onGetAppHomeMenuTreeByStaff(List<AppHomeMenuTreeBean> data) {

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
    public MessagePresenter createPresenter() {
        return new MessagePresenter(getApp());
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
    public void onEventMainThread(FirstEvent event) {
        switch (event.getMsg()) {
            case "pushRefresh":
                page = 1;
                refresh();
                break;
            case "changeAccount":
                userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
                page = 1;
                refresh();
                break;
        }


    }


}
