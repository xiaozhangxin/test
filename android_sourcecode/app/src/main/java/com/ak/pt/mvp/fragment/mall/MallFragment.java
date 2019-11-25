package com.ak.pt.mvp.fragment.mall;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.DepartmentBean;
import com.ak.pt.bean.DepartmentEvent;
import com.ak.pt.bean.GoodsBean;
import com.ak.pt.bean.ShopBannerBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.MallListAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.mall.MallPresenter;
import com.ak.pt.mvp.view.mall.IMallView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.bumptech.glide.Glide;
import com.githang.statusbar.StatusBarCompat;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

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

/**
 * Created by admin on 2019/5/5.
 */

public class MallFragment extends BaseFragment<IMallView, MallPresenter> implements IMallView {

    Unbinder unbinder;
    View loadMore;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRightTwo)
    ImageView ivRightTwo;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    MZBannerView banner;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;
    TextView tvIntegral;
    private List<GoodsBean> list;
    private List<String> bannerList;
    private MallListAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> map1 = new HashMap<>();
    private Map<String, String> map2 = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private AppPermissionsBean permissionsBean;

    public static MallFragment newInstance(AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        MallFragment fragment = new MallFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.white));
        } else {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_mall;
    }

    @Override
    public void initUI() {
        tvTitle.setText("积分商城");
        tvRight.setTextColor(getResources().getColor(R.color.colorPrimary));

        list = new ArrayList<>();
        bannerList = new ArrayList<>();
        adapter = new MallListAdapter(context, list);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapterWithProgress(adapter);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startGoodsDetailFragment(adapter.getItem(position).getGoods_id());
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
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.mall_top, null);
                banner = inflate.findViewById(R.id.detailBanner);
                map2.clear();
                map2.put("group_id", userBean.getDepartment_no());
                getPresenter().getShopBannerList(userBean.getStaff_token(), map2);
                tvIntegral = inflate.findViewById(R.id.tvIntegral);
                map1.clear();
                map1.put("staff_id", userBean.getStaff_id());
                getPresenter().getWaterIntegral(userBean.getStaff_token(), map1);
                TextView tvAllOrder = inflate.findViewById(R.id.tvAllOrder);
                tvAllOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startAllOrderFragment();
                    }
                });
                return inflate;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        tvGroupuuid = userBean.getDepartment_no();
        switch (permissionsBean.getApp_data()) {
            case "1":
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText(userBean.getSimple_department_name());
                break;
            case "2":
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("选择部门");
                break;
        }
        refresh();
    }

    private void refresh() {
        map.put("group_no", tvGroupuuid);
        map.put("page", page + "");
        map.put("is_select", "1");
        map.put("is_app", "1");
        map.put("operation", "1000");
        map.put("module_id", permissionsBean.getMenu_id());
        getPresenter().querySaleGoodsList(userBean.getStaff_token(), map);

    }


    @Override
    public void onResume() {
        super.onResume();
        map1.clear();
        map1.put("staff_id", userBean.getStaff_id());
        map1.put("is_app", "1");
        getPresenter().getWaterIntegral(userBean.getStaff_token(), map1);
        if (banner != null) {
            banner.start();
        }//开始轮播
    }

    @Override
    public void onPause() {
        super.onPause();
        if (banner != null) {
            banner.pause();
        }//暂停轮播
    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                startDepartmentPermissionFragment(permissionsBean.getMenu_id(), "mall");
                break;
        }
    }

    @Override
    public void OnQuerySaleGoodsList(List<GoodsBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetWaterIntegral(String data) {
        if (tvIntegral != null) {
            tvIntegral.setText(data);
        }
    }

    @Override
    public void OnGetShopBannerList(List<ShopBannerBean> data) {
        //商品banner
        bannerList.clear();
        for (int i = 0; i < data.size(); i++) {
            bannerList.add(Constants.BASE_URL + data.get(i).getBanner_img());
        }
        banner.setPages(bannerList, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        banner.start();

    }

    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int i, String s) {
            Glide.with(context).load(s).error(R.drawable.error_img).into(mImageView);
        }

    }


    @Override
    public MallPresenter createPresenter() {
        return new MallPresenter(getApp());
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


    private String tvGroupuuid = "";

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(DepartmentEvent event) {
        switch (event.getmMsg()) {
            case "1":
                DepartmentBean departmentBean = event.getmBean();
                tvRight.setText(departmentBean.getName());
                tvGroupuuid = departmentBean.getUuid();
                page = 1;
                refresh();
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


}
