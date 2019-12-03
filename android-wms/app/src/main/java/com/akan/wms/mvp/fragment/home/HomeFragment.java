package com.akan.wms.mvp.fragment.home;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WarnTwoBean;
import com.akan.wms.mvp.adapter.HomeAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.HomePresenter;
import com.akan.wms.mvp.view.home.IHomeView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.akan.wms.util.shadow.ShadowLayout;
import com.bilibili.boxing_impl.view.SpacesItemDecoration;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.zxing.CaptureActivity;
import com.king.zxing.Intents;

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
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class HomeFragment extends BaseFragment<IHomeView, HomePresenter> implements IHomeView {


    Unbinder unbinder;
    @BindView(R.id.ivScan)
    TextView ivScan;
    @BindView(R.id.ivMsg)
    TextView ivMsg;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.tvBigOne)
    ImageView tvBigOne;
    @BindView(R.id.tvBigTwo)
    ImageView tvBigTwo;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvSOne)
    TextView tvSOne;
    @BindView(R.id.tvSTwo)
    TextView tvSTwo;
    @BindView(R.id.tvSThree)
    TextView tvSThree;
    @BindView(R.id.tvSFour)
    TextView tvSFour;
    @BindView(R.id.llInOne)
    TextView llInOne;
    @BindView(R.id.llOne)
    RelativeLayout llOne;
    @BindView(R.id.shaOne)
    ShadowLayout shaOne;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private List<WarnTwoBean> list;
    private HomeAdapter adapter;
    private int page = 1;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI() {
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new HomeAdapter(context, list);
        recyclerView.addItemDecoration(new SpacesItemDecoration(1));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startStockFindDetailFragment(adapter.getItem(position));
            }
        });
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
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
        map.put("org_id", userBean.getOrg_id());
        getPresenter().queryBoardWarnings(userBean.getStaff_token(), map);
    }


    public static final int REQUEST_CODE_SCAN = 0X02;
    public static final int RC_CAMERA = 0X01;

    /**
     * 检测拍摄权限
     */
    @AfterPermissionGranted(RC_CAMERA)
    private void checkCameraPermissions() {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(context, perms)) {//有权限
            startScan();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.permission_camera), RC_CAMERA, perms);
        }
    }

    /**
     * 扫码
     */
    private void startScan() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SCAN:
                    String result = data.getStringExtra(Intents.Scan.RESULT);
                    ToastUtil.showToast(context.getApplicationContext(), result);
                    break;
            }
        }
    }


    @OnClick({R.id.ivScan, R.id.ivMsg, R.id.tvSearch, R.id.tvSOne, R.id.tvSTwo, R.id.tvSThree,
            R.id.tvSFour, R.id.tvBigOne, R.id.tvBigTwo, R.id.tvOne, R.id.tvTwo, R.id.tvThree, R.id.llOne})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivScan:
                ToastUtil.showToast(context.getApplicationContext(), getString(R.string.no_function));
                //  checkCameraPermissions();
                break;
            case R.id.tvSearch:
               // startStockFindListFragment();
                EventBus.getDefault().post(new FirstEvent("homeCode"));
                break;
            case R.id.ivMsg://消息
               // startMessageFragment();
                EventBus.getDefault().post(new FirstEvent("homeMessage"));
                break;
            case R.id.tvBigOne://入库
                startAllFunctionFragment("1");
                break;
            case R.id.tvBigTwo://出库
                startAllFunctionFragment("2");
                break;
            case R.id.tvOne://基础
                startAllFunctionFragment("3");
                break;
            case R.id.tvTwo://库管
                startAllFunctionFragment("4");
                break;
            case R.id.tvThree://统计
                startAllFunctionFragment("5");
                break;
            case R.id.tvSOne://盘点
                startCheckListFragment();
                break;
            case R.id.tvSTwo://调拨
                startTransferListFragment();
                break;
            case R.id.tvSThree://杂发
                startSendMixListFragment();
                break;
            case R.id.tvSFour://杂收
                startReceiveMixListFragment();
                break;
            case R.id.llOne:
                startInventoryWarningFragment();
                break;
        }
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
    public HomePresenter createPresenter() {
        return new HomePresenter(getApp());
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
            case "org_change":
                initData();
                break;

        }

    }

    @Override
    public void onQueryBoardWarnings(List<WarnTwoBean> data) {
        refreshLayout.setRefreshing(false);
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        refreshLayout.setRefreshing(false);
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());

    }
}
