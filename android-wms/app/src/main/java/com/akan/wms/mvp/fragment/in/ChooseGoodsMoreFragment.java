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
import com.akan.wms.bean.GoodsListBean;
import com.akan.wms.bean.ItemInfoBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.home.ChooseGoodsMoreAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.ChoosePresenter;
import com.akan.wms.mvp.view.home.IChooseView;
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

public class ChooseGoodsMoreFragment extends BaseFragment<IChooseView, ChoosePresenter> implements IChooseView {
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
    @BindView(R.id.ivLog)
    ImageView ivLog;



    private List<ItemInfoBean> list;
    private ChooseGoodsMoreAdapter adapter;
    private int page = 1;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> map1 = new HashMap<>();
    private UserBean userBean;
    private List<ItemInfoBean> chooseList = new ArrayList<>();


    public static ChooseGoodsMoreFragment newInstance() {
        Bundle args = new Bundle();
        ChooseGoodsMoreFragment fragment = new ChooseGoodsMoreFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_choose_goods_more;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.choose_goods);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    page = 1;
                    refresh();
                    recyclerView.setRefreshing(true);
                }
                return false;
            }
        });
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ChooseGoodsMoreAdapter(context, list);
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setOnChangeClickListener(new ChooseGoodsMoreAdapter.OnChangeClickListener() {
            @Override
            public void jiaOrJian(int position) {
                ItemInfoBean item = adapter.getItem(position);
                if (chooseList.size() <= 0) {
                    chooseList.add(item);
                    tvChooseNum.setText(getString(R.string.already_choose) + item.getNum());
                } else {
                    boolean isHave = false;
                    int total = 0;
                    for (int i = 0; i < chooseList.size(); i++) {

                        if (chooseList.get(i).getItem_id().equals(item.getItem_id())) {
                            isHave = true;
                            chooseList.set(i, item);
                        }
                        total = total + Integer.parseInt(chooseList.get(i).getNum());
                    }
                    if (!isHave) {
                        chooseList.add(item);
                    }
                    tvChooseNum.setText(getString(R.string.already_choose) + total);
                }

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





        //悬浮可拖动按钮
        ImageView imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.sync);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().syncItemInfoList(userBean.getStaff_token(), map1);
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
        map.put("all_select", etSearch.getText().toString());
        map.put("org_id", userBean.getOrg_id());
        map.put("page", page + "");
        getPresenter().getItemInfoList(userBean.getStaff_token(), map);

    }


    @OnClick({R.id.ivLeft, R.id.ok, R.id.ivLog, R.id.tvChooseNum, R.id.etSearch})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.etSearch:
                showInputMethod(etSearch);
                break;
            case R.id.ivLog:
                if (chooseList.size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_chooose_goods));
                    return;
                }
                startChooseMoreChildFragment(chooseList);
                break;
            case R.id.tvChooseNum:
                break;
            case R.id.ok:
                if (chooseList.size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_chooose_goods));
                    return;
                }
                EventBus.getDefault().post(new FirstEvent("2", new GoodsListBean(chooseList)));
                finish();
                break;

        }
    }

    private void startChooseMoreChildFragment(List<ItemInfoBean> list) {
        ChooseMoreChildFragment fragment = ChooseMoreChildFragment.newInstance(list, tvChooseNum.getText().toString());
        fragment.setAddOnclickListener(new ChooseMoreChildFragment.OnOkChangeclickListener() {
            @Override
            public void onOk(List<ItemInfoBean> list, String type) {
                chooseList = list;
                if (list.size() <= 0) {
                    tvChooseNum.setText(R.string.choose_zero);
                }
                if ("2".equals(type)) {
                    EventBus.getDefault().post(new FirstEvent("2", new GoodsListBean(list)));
                    finish();
                }

            }
        });
        fragment.show(getFragmentManager(), ChooseGoodsMoreFragment.class.getSimpleName());
    }


    @Override
    public ChoosePresenter createPresenter() {
        return new ChoosePresenter(getApp());
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
                chooseList.clear();
                page = 1;
                refresh();

                break;

        }

    }


    @Override
    public void onGetItemInfoList(List<ItemInfoBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void syncItemInfoList(String data) {
        syncSuccess();
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
