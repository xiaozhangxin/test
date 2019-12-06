package com.akan.qf.mvp.fragment.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.ContributeClassBean;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.UserBean;
import com.akan.qf.bean.staffGroupBeans;
import com.akan.qf.mvp.adapter.mine.ArticleClassListAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.treeview.ChooseDepartmentPresenter;
import com.akan.qf.mvp.view.treeview.IChooseDepartmentView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;

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
 * Created by admin on 2018/11/26.
 */

public class ArticleClassFragment extends BaseFragment<IChooseDepartmentView, ChooseDepartmentPresenter> implements IChooseDepartmentView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    TextView ivLeft;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private List<ContributeClassBean> list;
    private ArticleClassListAdapter adapter;
    private String table_id;
    public static ArticleClassFragment newInstance(String table_id) {
        Bundle args = new Bundle();
        ArticleClassFragment fragment = new ArticleClassFragment();
        fragment.table_id=table_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_choose_class;
    }

    @Override
    public void initUI() {
        if ("0".equals(table_id)){
            list = new ArrayList<>();
            recycleView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new ArticleClassListAdapter(context, list);
            recycleView.setAdapter(adapter);
            adapter.setOnOpenClick(new ArticleClassListAdapter.OnOpenClick() {
                @Override
                public void onOpenClick(int position) {
                    adapter.notifyItemChanged(position);
                }

                @Override
                public void onAddClick(String class_ids,String class_id,String name) {
                   // startArticleAddFragment(class_ids,class_id);
                    finish();
                }
            });
        }else {
            list = new ArrayList<>();
            recycleView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new ArticleClassListAdapter(context, list);
            recycleView.setAdapter(adapter);
            adapter.setOnOpenClick(new ArticleClassListAdapter.OnOpenClick() {
                @Override
                public void onOpenClick(int position) {
                    adapter.notifyItemChanged(position);
                }

                @Override
                public void onAddClick(String class_ids,String class_id,String name) {
                    EventBus.getDefault().post(new FirstEvent("article_table" + name + "+" +class_id));
                    finish();
                }
            });
        }

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getPresenter().getContributeClassTree(userBean.getStaff_token(), map);
    }

    @Override
    public void onGetStaffGroupTreeByStaff(List<staffGroupBeans> data) {

    }

    @Override
    public void OnGetContributeClassTree(List<ContributeClassBean> data) {
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
    public ChooseDepartmentPresenter createPresenter() {
        return new ChooseDepartmentPresenter(getApp());
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
