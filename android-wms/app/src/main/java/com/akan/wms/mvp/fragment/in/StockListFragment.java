package com.akan.wms.mvp.fragment.in;

import android.content.DialogInterface;
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
import com.akan.wms.bean.PurchaseBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.home.StockistAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.StockPresenter;
import com.akan.wms.mvp.view.home.IStockView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.akan.wms.view.CustomDialog;
import com.akan.wms.view.SonnyJackDragView;
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

public class StockListFragment extends BaseFragment<IStockView, StockPresenter> implements IStockView {
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

    private List<PurchaseBean> list;
    private StockistAdapter adapter;
    private int page = 1;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> map1 = new HashMap<>();
    private UserBean userBean;

    private String supId;
    private String stokType;

    public static StockListFragment newInstance(String supId, String stokType) {
        Bundle args = new Bundle();
        StockListFragment fragment = new StockListFragment();
        fragment.supId = supId;
        fragment.stokType = stokType;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_list_search;
    }

    @Override
    public void initUI() {
        tvTitle.setText("选择采购单");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new StockistAdapter(context, list);
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startStockChildFragment(adapter.getItem(position), stokType);
            }
        });
        adapter.setNoMore(R.layout.view_nomore);
        recyclerView.setRefreshingColorResources(R.color.colorPrimary);

/*
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
*/


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
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    refresh();
                    hideInputMethod();
                }
                return false;
            }
        });


        //悬浮可拖动按钮
        ImageView imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.sync);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().importPurchaseData(userBean.getStaff_token(), map1);
            }
        });
        SonnyJackDragView build = new SonnyJackDragView.Builder()
                .setActivity(getActivity())
                .setNeedNearEdge(false)
                .setView(imageView)
                .build();
    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();
    }

    private void refresh() {
        map.put("org_id", userBean.getOrg_id());
        map.put("supplier_id", supId);
        map.put("doc_no", etSearch.getText().toString());
        getPresenter().queryPurchaseLists(userBean.getStaff_token(), map);
    }

    @Override
    public void onQueryPurchaseLists(List<PurchaseBean> data) {
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void importPurchaseData(String data) {
        syncSuccess();
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
    public StockPresenter createPresenter() {
        return new StockPresenter(getApp());
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
            case "stock_list_finish":
                finish();
                break;

        }

    }

    private void syncSuccess() {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(getString(R.string.point));
        builder.setMessage(getString(R.string.sync_success));
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.onCreate().show();
    }

}
