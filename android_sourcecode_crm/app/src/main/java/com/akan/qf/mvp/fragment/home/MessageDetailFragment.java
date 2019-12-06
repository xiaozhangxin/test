package com.akan.qf.mvp.fragment.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.AppHomeMenuBean;
import com.akan.qf.bean.AppHomeMenuTreeBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.StaffMessageBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.message.MessageDetailPresenter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.view.message.IMessageDetailView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class MessageDetailFragment extends BaseFragment<IMessageDetailView, MessageDetailPresenter> implements IMessageDetailView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.ivAvatar)
    CircleImageView ivAvatar;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvTime)
    TextView tvTime;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String msg_id;
    private String is_red;
    private String type;
    private String role_id;

    public static MessageDetailFragment newInstance(String msg_id, String is_red,String type) {
        Bundle args = new Bundle();
        MessageDetailFragment fragment = new MessageDetailFragment();
        fragment.setArguments(args);
        fragment.msg_id = msg_id;
        fragment.is_red = is_red;
        fragment.type=type;
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_message_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("消息详情");

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getAppHomeMenuTreeByStaff(userBean.getStaff_token(), map);//组织架构树
        map.put("msg_id", msg_id);
        getPresenter().getStaffMessageDetail(userBean.getStaff_token(), map);
    }

    @Override
    public void onGetStaffMessageDetail(StaffMessageBean data) {
        role_id = data.getRole_id();
        tvTime.setText(data.getCreate_time_simple());
        tvType.setText(data.getMsg_title());
        tvContent.setText(data.getMsg_content());

        if ("0".equals(is_red)) {
            EventBus.getDefault().post(new FirstEvent("refreshNews"));
        }

    }
    private PermissionsBean permissionsBean;

    private void choosePermission(String type, AppHomeMenuBean appHomeMenuBeansBean) {
        if (type.equals(appHomeMenuBeansBean.getMenu_key())) {
            permissionsBean = appHomeMenuBeansBean.getAppPermissionsBeans();
        }
    }
    @Override
    public void onGetAppHomeMenuTreeByStaff(List<AppHomeMenuTreeBean> data) {
        for (int i = 0; i < data.size(); i++) {
            List<AppHomeMenuBean> appHomeMenuBeans = data.get(i).getAppHomeMenuBeans();
            for (int j = 0; j < appHomeMenuBeans.size(); j++) {
                AppHomeMenuBean appHomeMenuBeansBean = appHomeMenuBeans.get(j);
                switch (type) {
                    case "daily"://日报
                        choosePermission("Daily", appHomeMenuBeansBean);
                        break;
                    case "monthly_report"://月报
                        choosePermission("Month", appHomeMenuBeansBean);
                        break;
                    case "weekly"://周报
                        choosePermission("Week", appHomeMenuBeansBean);
                        break;
                    case "ask_leave"://离职申请
                        choosePermission("AskLeave", appHomeMenuBeansBean);
                        break;
                    case "fk"://付款申请
                        choosePermission("PayApply", appHomeMenuBeansBean);
                        break;
                    case "bx"://报销申请
                        choosePermission("ExpenseReimbursement", appHomeMenuBeansBean);
                        break;
                    case "zc"://政策申请
                        choosePermission("PolicyApply", appHomeMenuBeansBean);
                        break;
                    case "jz"://借支申请
                        choosePermission("TemporarySupport", appHomeMenuBeansBean);
                        break;
                    case "qk":
                        choosePermission("DebtApply", appHomeMenuBeansBean);
                        break;
                    case "th":
                        choosePermission("GoodsApply", appHomeMenuBeansBean);
                        break;
                    case "wt":
                        choosePermission("NewApply", appHomeMenuBeansBean);
                        break;
                    case "gb"://工程报备
                        choosePermission("ProjectApply", appHomeMenuBeansBean);
                        break;
                    case "kh"://客户来访
                        choosePermission("VisitorApply", appHomeMenuBeansBean);
                        break;
                    case "sg"://水工
                        choosePermission("ChannelWater", appHomeMenuBeansBean);
                        break;
                    case "fx"://分销
                        choosePermission("ChannelDistribution", appHomeMenuBeansBean);
                        break;
                    case "jg"://家装公司
                        choosePermission("ChannelCustomer", appHomeMenuBeansBean);
                        break;
                    case "zr"://招人
                        choosePermission("Recruitment", appHomeMenuBeansBean);
                        break;
                    case "xr"://新人
                        choosePermission("NewcomerJoin", appHomeMenuBeansBean);
                        break;
                    case "lz"://离职
                        choosePermission("ResignationLetter", appHomeMenuBeansBean);
                        break;
                    case "nt"://暖通公司
                        choosePermission("NuantongCompany", appHomeMenuBeansBean);
                        break;
                    case "gz"://水工
                        choosePermission("ChannelWater", appHomeMenuBeansBean);
                        break;
                    case "fw"://分销
                        choosePermission("ChannelDistribution", appHomeMenuBeansBean);
                        break;
                    case "tg"://推广广告
                        choosePermission("PromotionAdv", appHomeMenuBeansBean);
                        break;
                    case "dz"://店招广告
                        choosePermission("ShopAdv", appHomeMenuBeansBean);
                        break;
                    case "xx"://形象广告
                        choosePermission("ImageAdv", appHomeMenuBeansBean);
                        break;
                    case "gx"://工程项目
                        choosePermission("project", appHomeMenuBeansBean);
                        break;
                }


            }
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
    public MessageDetailPresenter createPresenter() {
        return new MessageDetailPresenter(getApp());
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

    @OnClick({R.id.ivLeft, R.id.view2})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.view2:
                if (permissionsBean == null) {
                    return;
                }
                if (TextUtils.isEmpty(role_id)) {
                    return;
                }
                switch (type) {
                    case "daily":
                        startDailyDetailFragment(role_id,permissionsBean);
                        break;
                    case "monthly_report":
                        startMonthDetailFragment(role_id,permissionsBean);
                        break;
                    case "weekly":
                        startWeeklyDetailFragment(role_id,permissionsBean);
                        break;
                    case "ask_leave":
                        startLeaveDetailFragment(role_id,permissionsBean);
                        break;
                    case "fk":
                        startPayRequestDetailFragment(role_id,permissionsBean);
                        break;
                    case "bx":
                        startReimburseDetailFragment(role_id,permissionsBean);
                        break;
                    case "zc":
                        startPolicyDetailFragment(role_id,permissionsBean);
                        break;
                    case "jz":
                        startPayTemraryDetailFragment(role_id,permissionsBean);
                        break;
                    case "qk":
                        startArrearsDetailFragment(role_id,permissionsBean);
                        break;
                    case "th":
                        startReturnDetailFragment(role_id,permissionsBean);
                        break;
                    case "wt":
                        startProblemDetailFragment(role_id,permissionsBean);
                        break;
                    case "gb":
                        startReportDetailFragment(role_id,permissionsBean);
                        break;
                    case "kh":
                        startVisitDetailFragment(role_id,permissionsBean);
                        break;
                    case "sg":
                        startWaterDetailFragment(role_id,permissionsBean);
                        break;
                    case "fx":
                        startDistribtionDetailFragment(role_id,permissionsBean);
                        break;
                    case "jg":
                        staretCompanyDetailFragment(role_id,permissionsBean);
                        break;
                    case "zr":
                        startPeopleJoinDetailFragment(role_id,permissionsBean);
                        break;
                    case "xr":
                        startPeopleNewDetailFragment(role_id,permissionsBean);
                        break;
                    case "lz"://离职
                        startPeopleLeaveDetailFragment(role_id,permissionsBean);
                        break;
                    case "nt"://暖通公司
                        startProjectDetailFragment(role_id,permissionsBean);
                        break;
                    case "gz"://水工
                        startWaterDetailFragment(role_id,permissionsBean);
                        break;
                    case "fw"://分销
                        startDistribtionDetailFragment(role_id,permissionsBean);
                        break;
                    case "tg"://推广广告
                        startADPromotionDetailFragment(role_id,permissionsBean);
                        break;
                    case "dz"://店招广告
                        startADShopDetailFragment(role_id,permissionsBean);
                        break;
                    case "xx"://形象广告
                        startADImageDetailFragment(role_id,permissionsBean);
                        break;
                    case "gx"://工程项目
                        startEngineerDetailFragment(role_id,permissionsBean);
                        break;
                }
                break;
        }
    }

}
