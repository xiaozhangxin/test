package com.ak.pt.mvp.fragment.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.AppHomeMenuBeansBean;
import com.ak.pt.bean.AppHomeMenuTreeBean;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.AppVersionBean;
import com.ak.pt.bean.BannerBean;
import com.ak.pt.bean.FirstEvent;
import com.ak.pt.bean.NoticeBean;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.MenuListAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.fragment.adaily.DailyActivity;
import com.ak.pt.mvp.fragment.adaily.MonthActivity;
import com.ak.pt.mvp.fragment.adaily.SignActivity;
import com.ak.pt.mvp.fragment.adaily.WeekActivity;
import com.ak.pt.mvp.fragment.area.InterviewActivity;
import com.ak.pt.mvp.fragment.area.MonthlySummaryActivity;
import com.ak.pt.mvp.fragment.area.PeopleActivity;
import com.ak.pt.mvp.fragment.area.RegionActivity;
import com.ak.pt.mvp.fragment.area.ReworkActivity;
import com.ak.pt.mvp.fragment.people.CloseActivity;
import com.ak.pt.mvp.fragment.people.EntryActivity;
import com.ak.pt.mvp.fragment.people.LeaveActivity;
import com.ak.pt.mvp.fragment.water.FeedbackActivity;
import com.ak.pt.mvp.fragment.water.FilterReplaceActivity;
import com.ak.pt.mvp.fragment.water.FixRecordActivity;
import com.ak.pt.mvp.fragment.water.WarrantyActivity;
import com.ak.pt.mvp.presenter.HomePresenter;
import com.ak.pt.mvp.view.IHomeView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.GlideRoundTransform;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
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
import com.uniquext.android.widget.view.CornerImageView;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by admin on 2019/1/7.
 */

public class HomeFragment extends BaseFragment<IHomeView, HomePresenter> implements IHomeView {


    Unbinder unbinder;
    @BindView(R.id.topBg)
    View topBg;
    @BindView(R.id.ivAvatar)
    CornerImageView ivAvatar;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.ivMsg)
    ImageView ivMsg;
    @BindView(R.id.ivRed)
    TextView ivRed;
    @BindView(R.id.topBg1)
    View topBg1;
    @BindView(R.id.banner)
    MZBannerView banner;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.vf)
    ViewFlipper vf;
    @BindView(R.id.btnPressureNow)
    TextView btnPressureNow;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.llButton)
    LinearLayout llButton;
    @BindView(R.id.ivWait)
    ImageView ivWait;


    private CornerImageView ivAvatarHead;
    private TextView tvNameHead;
    private TextView tvTypeHead;
    private TextView tvDepartmentHead;
    private ArrayList<String> list;//bannerList
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> map1 = new HashMap<>();
    private Map<String, String> map2 = new HashMap<>();
    private Map<String, String> map3 = new HashMap<>();
    private List<AppHomeMenuTreeBean> menuList;
    private MenuListAdapter adapter;

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

        //showChangePwdDialog();
        list = new ArrayList<>();
        //初始化菜单
        menuList = new ArrayList<>();
        recycleView.setHasFixedSize(true);
        recycleView.setNestedScrollingEnabled(false);
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MenuListAdapter(context, menuList);
        recycleView.setAdapter(adapter);
        adapter.setOnClickImgListener(new MenuListAdapter.OnClickImgListener() {
            @Override
            public void OnClick(String tittle, int position, int childPosition) {
                AppPermissionsBean permissionsBean = adapter.getItem(position).getAppHomeMenuBeans().get(childPosition).getAppPermissionsBeans();
                Intent intent;
                switch (tittle) {
                    case "PressureTestAdd"://试压报单
                        startOrderManagerFragment(permissionsBean);
                        break;
                    case "SecurityCheck"://防伪查询
                        startSecurityCheckFragment(permissionsBean);
                        break;
                    case "AddressBook"://通讯录
                        startBookFragment(permissionsBean);
                        break;
                    case "Sign"://签到
                        intent = new Intent(getActivity(), SignActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        startActivity(intent);
                        break;
                    case "Daily"://日报
                        intent = new Intent(getActivity(), DailyActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        startActivity(intent);
                        break;
                    case "Week"://周报
                        intent = new Intent(getActivity(), WeekActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        startActivity(intent);
                        break;
                    case "Month"://月报
                        intent = new Intent(getActivity(), MonthActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        startActivity(intent);
                        break;
                    case "AnnouncementNotice"://公告
                        startNoticeFragment();
                        break;
                    case "PressurePage"://试压记录
                        startTestPressureFragment(Constants.PRESSURE_RECORD_DEFAULT, permissionsBean);
                        break;
                    case "AreaPressurePage"://区域试压排行
                        startAreaTestPressureFragment(Constants.PRESSURE_RECORD_DEFAULT, permissionsBean);
                        break;
                    case "BigAreaPressurePage"://大区试压排行
                        startBigTestPressureFragment(Constants.PRESSURE_RECORD_DEFAULT);
                        break;
                    case "OfficialDocument"://公文
                        startOfficeDocumentFragment();
                        break;
                    case "IntegralView"://积分查询
                        startIntegralFragment();
                        break;
                    case "Document"://文档
                        startDocumentFragment(permissionsBean);
                        break;
                    case "IntegralMall"://积分商城
                        startMallFragment(permissionsBean);
                        break;
                    case "Table"://报表
                        startTableFragment();
                        break;

                    case "FilterRpReceipt"://滤芯更换回执单
                        intent = new Intent(getActivity(), FilterReplaceActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        startActivity(intent);
                        break;
                    case "RepairRecords"://维修记录表
                        intent = new Intent(getActivity(), FixRecordActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        startActivity(intent);
                        break;
                    case "ProductQFeedback"://产品质量反馈表
                        intent = new Intent(getActivity(), FeedbackActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        startActivity(intent);
                        break;
                    case "WarrantyCard"://电子保修卡
                        intent = new Intent(getActivity(), WarrantyActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        startActivity(intent);
                        break;

                    case "MonthlySummary"://月度总结
                        intent = new Intent(getActivity(), MonthlySummaryActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        startActivity(intent);
                        break;
                    case "RegionTraining"://区域培训记录
                        intent = new Intent(getActivity(), RegionActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        startActivity(intent);
                        break;
                    case "RegionVisit"://区域走访记录
                        intent = new Intent(getActivity(), InterviewActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        startActivity(intent);
                        break;
                    case "AddPeople"://人员添加申请
                        Intent intentPeople = new Intent(getActivity(), PeopleActivity.class);
                        intentPeople.putExtra("permissions", permissionsBean);
                        startActivity(intentPeople);
                        break;
                    case "PressureTesterReturn"://试压仪返修
                        intent = new Intent(getActivity(), ReworkActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        startActivity(intent);
                        break;
                    case "EntryApplication"://入职申请
                        Intent intentRework = new Intent(getActivity(), EntryActivity.class);
                        intentRework.putExtra("permissions", permissionsBean);
                        startActivity(intentRework);
                        break;
                    case "LeaveApplication"://离职申请
                        intent = new Intent(getActivity(), LeaveActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        startActivity(intent);
                        break;
                    case "DealerClose"://经销商关闭
                        intent = new Intent(getActivity(), CloseActivity.class);
                        intent.putExtra("permissions", permissionsBean);
                        startActivity(intent);
                        break;


                    case "QF_PressureRecord":   //启飞试压记录
                        startTestPressureFragment(Constants.PRESSURE_RECORD_QIFEI, permissionsBean);
                        break;
                    case "QF_AreaPressurePage": //  启飞区域试压排行
                        startAreaTestPressureFragment(Constants.PRESSURE_RECORD_QIFEI, permissionsBean);
                        break;
                    case "QF_BigPressurePage":  //  启飞大区试压排行
                        startBigTestPressureFragment(Constants.PRESSURE_RECORD_QIFEI);
                        break;
                }
            }
        });

        initDrawLayout();
        // initPopwindow();

    }

    //初始化侧滑栏
    private void initDrawLayout() {
        ivAvatarHead = navView.getHeaderView(0).findViewById(R.id.ivAvatarHead);
        tvNameHead = navView.getHeaderView(0).findViewById(R.id.tvNameHead);
        tvTypeHead = navView.getHeaderView(0).findViewById(R.id.tvTypeHead);
        tvDepartmentHead = navView.getHeaderView(0).findViewById(R.id.tvDepartmentHead);

        navView.getMenu().findItem(R.id.info).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startPersonalnfoFragment();
                return true;
            }
        });
        navView.getMenu().findItem(R.id.changePwd).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startChangePwdFragment();
                return true;
            }
        });
        navView.getMenu().findItem(R.id.software).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startSaftwareFragment();
                return true;
            }
        });
        navView.getMenu().findItem(R.id.Security).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // startSecurityCheckFragment();
                return true;
            }
        });
        navView.getMenu().findItem(R.id.AccountChange).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startAccountFragment();
                return true;
            }
        });
        navView.getMenu().findItem(R.id.exit).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                exitHome();
                return true;
            }
        });
    }

    //密码过于简单弹框
    private void showChangePwdDialog() {
        SharedPreferences sp = context.getSharedPreferences("login", MODE_PRIVATE);
        String password = sp.getString("pwd", "");
        if ("a123456".equals(password)) {
            final CustomDialog.Builder builder = new CustomDialog.Builder(context);
            builder.setMessage(getString(R.string.please_change_pwd));
            builder.setPositiveButton(getString(R.string.fix_rightnow), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startChangePwdFragment();
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton(getString(R.string.talk_about_later), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.onCreate().show();
        }
    }

    private PopupWindow mPopupWindow;
    private View mPopView;
    private List<PressurePageBean> serverList = new ArrayList();//正在服务的试压单

    //底部正在试压单据轮播
    private void initPopwindow() {
        mPopView = getActivity().getLayoutInflater().inflate(R.layout.home_pop, null);
        mPopupWindow = new PopupWindow(mPopView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(false);
        ViewFlipper mPopVf = mPopView.findViewById(R.id.vfPop);
        mPopVf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = serverList.size();
                if (size > 0) {
                    int displayedChild = vf.getDisplayedChild();
                    int id = displayedChild % size;
                    if (id < size) {
                        startTestPressureDetailFragment(serverList.get(id).getDoc_no(), workerPermissionsBean);
                    }
                }
            }
        });
        ImageView mPOpClose = mPopView.findViewById(R.id.popClose);
        mPOpClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null || mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
            }
        });


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        isHavaWaitAcceptOrder("1");
        //版本更新
        map2.clear();
        map2.put("version_type", "android");
        getPresenter().getAppVersionDetail(map2);
        //轮播图
        map.clear();
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getBannerList(userBean.getStaff_token(), map);


    }


    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        banner.start();
        isHavaWaitAcceptOrder("2");
        refreshPersonalInfor();
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getAppSystemModuleTree(userBean.getStaff_token(), map);//组织架构树
        getPresenter().getNoticeList(userBean.getStaff_token(), map);//公告列表
        getPresenter().getNotReadMessageCount(userBean.getStaff_token(), map);//未读消息数量
        getPresenter().getNotReadNoticeCount(userBean.getStaff_token(), map);//未读公告数量
    }

    // 是否为试压工是否有代接试压单
    private void isHavaWaitAcceptOrder(String state) {
        if (!TextUtils.isEmpty(userBean.getStaff_sign())) {
            if ("2".equals(userBean.getStaff_sign())) {
                llButton.setVisibility(View.VISIBLE);
                map3.clear();
                map3.put("staff_id", userBean.getStaff_id());
                map3.put("job_name", userBean.getJob_name());
                map3.put("flow_state", "accept");
                getPresenter().getAppTestPressureList(userBean.getStaff_token(), map3);//是否有待试压单
                if ("1".equals(state)) {
                    map1.clear();
                    map1.put("staff_id", userBean.getStaff_id());
                    map1.put("job_name", userBean.getJob_name());
                    map1.put("flow_state", "plan");
                    getPresenter().getTestPressureListTwo(userBean.getStaff_token(), map1);//是否有已接单试压单
                }
            } else {
                llButton.setVisibility(View.GONE);
/*                if (mPopupWindow != null || mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }*/
            }
        }
    }

    //刷新首页个人信息
    private void refreshPersonalInfor() {
        tvNameHead.setText(userBean.getStaff_name());
        tvTypeHead.setText(userBean.getJob_name());
        tvDepartmentHead.setText(userBean.getDepartment_name());
        Glide.with(getContext())
                .load(Constants.BASE_URL + userBean.getHead_img())
                .placeholder(R.drawable.error_img)
                .error(R.drawable.default_logo)
                .into(ivAvatar);
        Glide.with(getContext())
                .load(Constants.BASE_URL + userBean.getHead_img())
                .placeholder(R.drawable.error_img)
                .error(R.drawable.default_logo)
                .into(ivAvatarHead);
    }


    private AppPermissionsBean workerPermissionsBean;//试压报单权限

    @OnClick({R.id.ivAvatar, R.id.ivMsg, R.id.llButton, R.id.vf})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivAvatar://侧边栏
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.ivMsg://消息列表
                startMessageFragment();
                break;
            case R.id.llButton://试压工试压单
                if (workerPermissionsBean == null) {
                    ToastUtil.showToast(context.getApplicationContext(),"暂无权限");
                    return;
                }
                startOrderAcceptFragment(workerPermissionsBean);
                break;
            case R.id.vf://公告列表
                startNoticeFragment();
                break;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        banner.pause();//暂停轮播
    }

    //未读消息
    @Override
    public void onGetNotReadMessageCount(String data) {
        if (!TextUtils.isEmpty(data)) {
            int i = Integer.parseInt(data);
            if (i == 0) {
                ivRed.setVisibility(View.GONE);
            } else if (i > 0 & i <= 99) {
                ivRed.setVisibility(View.VISIBLE);
                ivRed.setText(data);
            } else {
                ivRed.setVisibility(View.VISIBLE);
                ivRed.setText("99+");
            }
        } else {
            ivRed.setVisibility(View.GONE);
        }
    }

    //退出登录弹框
    private void exitHome() {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage(getString(R.string.sure_exit));
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                exitLogin();
                EventBus.getDefault().post(new FirstEvent("exit"));
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
    }

    //公告列表
    @Override
    public void OnGetNoticeList(List<NoticeBean> data) {
        vf.removeAllViews();
        for (int i = 0; i < data.size(); i++) {
            View v = View.inflate(context, R.layout.home_filpper_item, null);
            TextView tvOne = v.findViewById(R.id.tvOne);
            tvOne.setText(data.get(i).getNotice_title());
            vf.addView(v);
        }
        vf.startFlipping();
    }

    //banner列表
    @Override
    public void OnGetBannerList(List<BannerBean> data) {
        list.clear();
        for (int i = 0; i < data.size(); i++) {
            list.add(Constants.BASE_URL + data.get(i).getBanner_img());
        }
        banner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        banner.start();
    }


    @Override
    public void onGetAppSystemModuleTree(List<AppHomeMenuTreeBean> data) {
        //获取试压报单权限 用于控制试压工建单
        for (int i = 0; i < data.size(); i++) {
            List<AppHomeMenuBeansBean> homeMenuBeans = data.get(i).getAppHomeMenuBeans();
            for (int j = 0; j < homeMenuBeans.size(); j++) {
                AppHomeMenuBeansBean beansBean = homeMenuBeans.get(j);
                if ("PressureTest".equals( beansBean.getMenu_key())) {
                    workerPermissionsBean =beansBean.getAppPermissionsBeans();
                    data.get(i).getAppHomeMenuBeans().remove(j);
                }
            }
        }

        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void OnGetAppTestPressureList(List<PressurePageBean> data) {
        if (data.size() <= 0) {
            ivWait.setVisibility(View.GONE);
        } else {
            ivWait.setVisibility(View.VISIBLE);
        }
    }


    //正在进行的试压单
    @Override
    public void OnGetTestPressureListTwo(List<PressurePageBean> data) {
        serverList = data;
      /*  if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                View v = View.inflate(context, R.layout.home_filpper_item_pop, null);
                TextView tv1 = v.findViewById(R.id.tvOne);
                tv1.setText(data.get(i).getPressure_type() + "-" + data.get(i).getAddress());
                mPopVf.addView(v);
            }
            mPopVf.startFlipping();
              mPopupWindow.showAtLocation(mPopView, Gravity.BOTTOM, 0, 30);


        }*/
    }

    //版本更新
    @Override
    public void OnGetAppVersionDetail(AppVersionBean data) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String versionName = info.versionName;
        if (!TextUtils.isEmpty(versionName) && "1".equals(data.getMust_update())) {
            if (!versionName.equals(data.getVersion_no())) {
                final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                builder.setMessage(getString(R.string.updata_now));
                builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startSaftwareFragment();
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.onCreate().show();
            }
        }
    }


    //未读公告数量
    @Override
    public void OnGetNotReadNoticeCount(String data) {
        if ("0".equals(data)) {
            Glide.with(context).load(R.drawable.xiaolaba).asBitmap().into(ivLeft);
        } else {
            Glide.with(context).load(R.drawable.xiaolaba).asGif().into(ivLeft);
        }
    }


    //轮播图
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
            Glide.with(context)
                    .load(s)
                    .placeholder(R.drawable.error_img)
                    .error(R.drawable.error_img)
                    .transform(new CenterCrop(context), new GlideRoundTransform(context, 20))
                    .into(mImageView);
        }
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(getApp());
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
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
            case "refreshNews":
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                getPresenter().getNotReadMessageCount(userBean.getStaff_token(), map);
                break;
            case "isNetWork":
                onResume();
                break;
            case "changeAccount":
                userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
                isHavaWaitAcceptOrder("1");
                break;
            case "pushRefresh":
                map3.clear();
                map3.put("staff_id", userBean.getStaff_id());
                map3.put("job_name", userBean.getJob_name());
                map3.put("flow_state", "accept");
                getPresenter().getAppTestPressureList(userBean.getStaff_token(), map3);//是否有待试压单
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                getPresenter().getNotReadMessageCount(userBean.getStaff_token(), map);
                break;
        }

    }


}
