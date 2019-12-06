package com.akan.qf.mvp.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.akan.qf.R;
import com.akan.qf.bean.DocumentBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.activity.TbsFileActivity;
import com.akan.qf.mvp.adapter.home.OfficeAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.OfficePresenter;
import com.akan.qf.mvp.view.home.IOfficeView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.DialogLoadding;
import com.akan.qf.view.img.ShowPictureActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/2/27.
 */

public class OfficeSearchFragment extends BaseFragment<IOfficeView, OfficePresenter> implements IOfficeView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    View loadMore;
    Unbinder unbinder;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;
    private List<DocumentBean> list;
    private OfficeAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private DialogLoadding dialogLoadding;

    public static OfficeSearchFragment newInstance() {
        Bundle args = new Bundle();
        OfficeSearchFragment fragment = new OfficeSearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_article_search;
    }

    @Override
    public void initUI() {
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new OfficeAdapter(context, list);
        recycleView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String upUrl = adapter.getItem(position).getDocument_url();
                if (upUrl.endsWith(".png") | upUrl.endsWith(".jpg") | upUrl.endsWith(".jpeg")) {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(upUrl);
                    Intent intent = new Intent(getActivity(), ShowPictureActivity.class);
                    intent.putExtra("imagelist", (Serializable) list);
                    intent.putExtra("position", position);
                    getActivity().startActivity(intent);
                } else {
                    Intent intentVisit = new Intent(getActivity(), TbsFileActivity.class);
                    intentVisit.putExtra("file_url", upUrl);
                    intentVisit.putExtra("file_name", adapter.getItem(position).getDocument_name());
                    startActivity(intentVisit);
                }
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

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();

    }

    private void refresh() {
        map.put("page", page + "");
        map.put("document_name", editText.getText().toString());
        map.put("group_parent_uuid", userBean.getGroup_parent_uuid());
        getPresenter().getDocumentList(userBean.getStaff_token(), map);
    }

    @Override
    public void OnGetDocumentList(List<DocumentBean> data,String total) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }


    @OnClick({R.id.ivLeft, R.id.ivSearch})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivSearch:
                String string = editText.getText().toString();
                if (TextUtils.isEmpty(string)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入文件名搜索");
                    return;
                }
                page = 1;
                map.put("document_name", string);
                refresh();

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
    public OfficePresenter createPresenter() {
        return new OfficePresenter(getApp());
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

    }


}

