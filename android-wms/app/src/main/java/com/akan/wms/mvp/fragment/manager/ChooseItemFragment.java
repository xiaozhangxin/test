package com.akan.wms.mvp.fragment.manager;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.GoodsItenBean;
import com.akan.wms.bean.ItemInfoBean;
import com.akan.wms.bean.ItemWhQohBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.home.ChooseItemAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.ChooseItemPresenter;
import com.akan.wms.mvp.view.home.IChooseItemView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
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

public class ChooseItemFragment extends BaseFragment<IChooseItemView, ChooseItemPresenter> implements IChooseItemView {
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
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.tvChooseNum)
    TextView tvChooseNum;

    private List<ItemWhQohBean> list;
    private ChooseItemAdapter adapter;
    private int page = 1;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int mPosition = 0;
    private List<ItemWhQohBean> chooseList = new ArrayList<>();
    private String wh_id;


    public static ChooseItemFragment newInstance(String wh_id) {
        Bundle args = new Bundle();
        ChooseItemFragment fragment = new ChooseItemFragment();
        fragment.wh_id = wh_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_choose_goods_more;
    }

    @Override
    public void initUI() {
        tvTitle.setText("选择商品");
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    page = 1;
                    refresh();
                    hideInputMethod();
                }
                return false;
            }
        });
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ChooseItemAdapter(context, list);
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                boolean check = adapter.getItem(position).isCheck();
                adapter.getItem(position).setCheck(!check);
                adapter.notifyItemChanged(position);

                int checkNum = 0;
                List<ItemWhQohBean> allData = adapter.getAllData();
                chooseList.clear();
                for (int i = 0; i < allData.size(); i++) {
                    if (allData.get(i).isCheck()) {
                        checkNum++;
                        chooseList.add(allData.get(i));
                    }
                }
                tvChooseNum.setText("已选择：" + checkNum);
            }
        });

        adapter.setNoMore(R.layout.view_nomore);
        recyclerView.setRefreshingColorResources(R.color.colorPrimary);
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                refresh();

            }
        });

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

    //刷新请求数据
    private void refresh() {
        String mAllSelect = etSearch.getText().toString();
        map.put("all_select", mAllSelect);
        map.put("org_id", userBean.getOrg_id());
        map.put("wh_id", wh_id);
        map.put("page", page + "");
        getPresenter().getItemWhQohByWhList(userBean.getStaff_token(), map);

    }


    @OnClick({R.id.ivLeft, R.id.ok, R.id.ivLog, R.id.tvChooseNum})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivLog:
            case R.id.tvChooseNum:
                if (chooseList.size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择商品");
                    return;
                }
                startChooseMoreChildFragment(chooseList);
                break;
            case R.id.ok:
                if (chooseList.size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择商品");
                    return;
                }
                EventBus.getDefault().post(new FirstEvent("14", new GoodsItenBean(chooseList)));
                finish();
                break;

        }
    }

    private void startChooseMoreChildFragment(List<ItemWhQohBean> list) {
        ChooseItemChildFragment fragment = ChooseItemChildFragment.newInstance(list, tvChooseNum.getText().toString());
        fragment.setAddOnclickListener(new ChooseItemChildFragment.OnOkChangeclickListener() {
            @Override
            public void onOk(List<ItemWhQohBean> list, String type) {

                switch (type){
                    case "1":
                        chooseList.clear();
                        chooseList.addAll(list);
                        tvChooseNum.setText("已选择："+list.size());
                        page=1;
                        refresh();
                        break;
                    case "2":
                        EventBus.getDefault().post(new FirstEvent("14", new GoodsItenBean(list)));
                        finish();
                        break;
                }
            }
        });
        fragment.show(getFragmentManager(), ChooseItemFragment.class.getSimpleName());
    }


    @Override
    public ChooseItemPresenter createPresenter() {
        return new ChooseItemPresenter(getApp());
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
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getMsg();
        switch (msg) {
            case "clear":
                tvChooseNum.setText("已选择：0" );
                chooseList.clear();
                page = 1;
                refresh();
                break;

        }

    }


    @Override
    public void onGetItemWhQohByWhList(List<ItemWhQohBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetItemInfoList(List<ItemInfoBean> data) {

    }
}
