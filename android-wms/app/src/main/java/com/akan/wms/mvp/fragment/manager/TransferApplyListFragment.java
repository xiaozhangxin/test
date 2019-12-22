package com.akan.wms.mvp.fragment.manager;

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
import com.akan.wms.bean.TransferUnCompleteBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.home.TransferApplyListAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.TransferApplyPresenter;
import com.akan.wms.mvp.view.home.ITransferApplyView;
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

public class TransferApplyListFragment extends BaseFragment<ITransferApplyView, TransferApplyPresenter> implements ITransferApplyView {
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

    private List<TransferUnCompleteBean> list;
    private TransferApplyListAdapter adapter;
    private int page = 1;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private Map<String, String> map1 = new HashMap<>();
    private String type;
    private String mIn;


    public static TransferApplyListFragment newInstance(String type,String mIn) {
        Bundle args = new Bundle();
        TransferApplyListFragment fragment = new TransferApplyListFragment();
        fragment.setArguments(args);
        fragment.type=type;
        fragment.mIn=mIn;
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_list_search;
    }

    @Override
    public void initUI() {
        tvTitle.setText("调拨申请列表");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new TransferApplyListAdapter(context, list);
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startTransferApplyChildFragment(adapter.getItem(position),"0",mIn);
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
                    page = 1;
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
                getPresenter().sync(userBean.getStaff_token(),map1);
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
        map.put("in_org_name", type);
        map.put("org_id", userBean.getOrg_id());
        map.put("doc_no", etSearch.getText().toString());
        map.put("page", page + "");
        getPresenter().queryUnClosedApplyPage(userBean.getStaff_token(), map);
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
    public TransferApplyPresenter createPresenter() {
        return new TransferApplyPresenter(getApp());
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
        String msg = event.getmMsg();
        switch (msg) {
            case "list_finish":
                finish();
                break;

        }

    }






    @Override
    public void onQueryApplyDocNo(TransferUnCompleteBean data) {

    }

    @Override
    public void onQueryUnClosedApplyPage(List<TransferUnCompleteBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSync(String data) {
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
