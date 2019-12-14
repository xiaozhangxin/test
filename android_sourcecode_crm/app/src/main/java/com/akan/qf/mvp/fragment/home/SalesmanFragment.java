package com.akan.qf.mvp.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.AppHomeMenuTreeBean;
import com.akan.qf.bean.BannerBean;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.LableBean;
import com.akan.qf.bean.NoticeBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.StaffSignListBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.activity.DoubleContentActivity;
import com.akan.qf.mvp.adapter.home.SalesmanListAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.HomePresenter;
import com.akan.qf.mvp.view.home.IHomeView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/10/19.
 */

public class SalesmanFragment extends BaseFragment<IHomeView, HomePresenter> implements IHomeView {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    Unbinder unbinder;
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.vf)
    ViewFlipper vf;
    List<NoticeBean> noticeList = new ArrayList<>();
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private List<AppHomeMenuTreeBean> list;
    private SalesmanListAdapter adapter;
    private List<BannerBean> listBanner;
    private List<LableBean> signList = new ArrayList<>();//标签列表

    public static SalesmanFragment newInstance() {
        Bundle args = new Bundle();
        SalesmanFragment fragment = new SalesmanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_salesman;
    }

    @Override
    public void initUI() {

        vf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNoticeFragment();
            }
        });

        list = new ArrayList<>();
        recycleView.setHasFixedSize(true);
        recycleView.setNestedScrollingEnabled(false);
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        recycleView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        adapter = new SalesmanListAdapter(context, list);
        recycleView.setAdapter(adapter);
        adapter.setOnClickImgListener(new SalesmanListAdapter.OnClickImgListener() {
            @Override
            public void OnClick(String tittle, int position, int childPosition) {
                Intent intent;
                PermissionsBean permissionsBean = adapter.getItem(position).getAppHomeMenuBeans().get(childPosition).getAppPermissionsBeans();
                switch (tittle) {
                    case "Staff"://人员信息
                        startPersonInfoFragment(permissionsBean, signList);
                        break;
                    case "AreaContribute"://区域投稿
                        startContributionsFragment(permissionsBean, signList);
                        break;
                    case "AnnouncementNotice"://公告通知
                        startNoticeFragment();
                        break;
                    case "OfficialDocument"://公文
                        startOfficeDocumentFragment();
                        break;
                    case "Contact"://通讯录
                        startBookListFragment(permissionsBean);
                        break;
                    default:
                        intent = new Intent(getActivity(), DoubleContentActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        intent.putExtra("signList", (Serializable) signList);
                        intent.putExtra(Constants.KEY_FRAGMENT, tittle);
                        startActivity(intent);
                        break;
                }
            }
        });
        listBanner = new ArrayList<>();
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                clickBannerItem(listBanner.get(position));
            }
        });

    }

    @Override


    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getPresenter().getBannerList(userBean.getStaff_token(), map);
        getPresenter().getStaffSignList(userBean.getStaff_token(), map);

        map.put("menu_group", "0");
        getPresenter().getAppHomeMenuTreeByStaff(userBean.getStaff_token(), map);
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getNotReadNoticeCount(userBean.getStaff_token(), map);
        getPresenter().getNoticeList(userBean.getStaff_token(), map);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (vf != null) {
            if (hidden) {
                vf.stopFlipping();
            } else {

                vf.startFlipping();
            }
        }
    }

    @Override
    public void onGetAppHomeMenuTreeByStaff(List<AppHomeMenuTreeBean> data) {
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetBannerListt(List<BannerBean> data) {
        if (convenientBanner != null) {
            toSetList(listBanner, data, false);
            convenientBanner.setPages(new CBViewHolderCreator() {
                @Override
                public Holder<BannerBean> createHolder() {
                    return new ImageHolder();
                }
            }, listBanner)
                    .setPageIndicator(new int[]{R.drawable.icon_dot_nor, R.drawable.icon_dot_pre})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);

            if (!convenientBanner.isTurning()) {
                convenientBanner.startTurning(4000);
            }
            convenientBanner.notifyDataSetChanged();
        }


    }

    @Override
    public void OnGetNoticeList(final List<NoticeBean> data) {
        noticeList = data;
        for (int i = 0; i < data.size(); i++) {
            View v = View.inflate(context, R.layout.home_filpper_item, null);
            TextView tv1 = v.findViewById(R.id.tvOne);
            tv1.setText(data.get(i).getNotice_title());
            vf.addView(v);


        }
        vf.startFlipping();

    }

    @Override
    public void OnGetNotReadNoticeCount(String data) {
        if ("0".equals(data)) {
            Glide.with(context).load(R.drawable.xiaolaba).asBitmap().into(ivLeft);
        } else {
            Glide.with(context).load(R.drawable.xiaolaba).asGif().into(ivLeft);
        }
    }


    @Override
    public void OnGetStaffSignList(List<LableBean> data) {
        signList.clear();
        LableBean lableBean = new LableBean();
        lableBean.setSign_id("");
        lableBean.setSign_name("全部");
        List<StaffSignListBean> childList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            childList.addAll(data.get(i).getStaffSignList());
        }
        lableBean.setStaffSignList(childList);
        signList.add(lableBean);
        signList.addAll(data);
    }

    //banner点击返回处理
    private void clickBannerItem(BannerBean banner) {
        //1不跳转；2web链接;3个人中心；4商品
        if (banner != null) {
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
    public HomePresenter createPresenter() {
        return new HomePresenter(getApp());
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
            case "changeAccount":
                onResume();
                break;
        }


    }

    public class ImageHolder implements Holder<BannerBean> {
        private ImageView iv;

        @Override
        public View createView(Context context) {
            iv = new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return iv;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerBean data) {
            Glide.with(context)
                    .load(Constants.BASE_URL + data.getBanner_img())
                    .error(R.drawable.home_top)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);
        }
    }


}
