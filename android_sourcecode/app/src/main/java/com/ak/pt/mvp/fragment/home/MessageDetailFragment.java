package com.ak.pt.mvp.fragment.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppHomeMenuBeansBean;
import com.ak.pt.bean.AppHomeMenuTreeBean;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.FirstEvent;
import com.ak.pt.bean.StaffMessageBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.MessagePresenter;
import com.ak.pt.mvp.view.IMessageView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.uniquext.android.widget.view.CornerImageView;

public class MessageDetailFragment extends BaseFragment<IMessageView, MessagePresenter> implements IMessageView {

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
    CornerImageView ivAvatar;
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
    private String form_no;

    public static MessageDetailFragment newInstance(String msg_id, String is_red, String type) {
        Bundle args = new Bundle();
        MessageDetailFragment fragment = new MessageDetailFragment();
        fragment.setArguments(args);
        fragment.msg_id = msg_id;
        fragment.is_red = is_red;
        fragment.type = type;
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
        getPresenter().getAppSystemModuleTree(userBean.getStaff_token(), map);//组织架构树
        map.put("msg_id", msg_id);
        getPresenter().getStaffMessageDetail(userBean.getStaff_token(), map);
    }

    @Override
    public void onGetStaffMessageDetail(StaffMessageBean data) {
        role_id = data.getRole_id();
        tvTime.setText(data.getCreate_time_simple());
        tvType.setText(data.getMsg_title());
        tvContent.setText(data.getMsg_content());
        form_no = data.getForm_no();

        if ("0".equals(is_red)) {
            EventBus.getDefault().post(new FirstEvent("refreshNews"));
        }

    }

    @Override
    public void onGetAppSystemModuleTree(List<AppHomeMenuTreeBean> data) {
        for (int i = 0; i < data.size(); i++) {
            List<AppHomeMenuBeansBean> appHomeMenuBeans = data.get(i).getAppHomeMenuBeans();
            for (int j = 0; j < appHomeMenuBeans.size(); j++) {
                AppHomeMenuBeansBean appHomeMenuBeansBean = appHomeMenuBeans.get(j);
                switch (type) {
                    //zlf 质量反馈 wxj 维修记录 lxg 滤芯更换
                    case "zlf":
                        choosePermission("ProductQFeedback", appHomeMenuBeansBean);
                        break;
                    case "wxj":
                        choosePermission("RepairRecords", appHomeMenuBeansBean);
                        break;
                    case "lxg":
                        choosePermission("FilterRpReceipt", appHomeMenuBeansBean);
                        break;
                    case "daily":
                        choosePermission("Daily", appHomeMenuBeansBean);
                        break;
                    case "monthly_report":
                        choosePermission("Month", appHomeMenuBeansBean);
                        break;
                    case "weekly":
                        choosePermission("Week", appHomeMenuBeansBean);
                        break;
                    case "ry":
                        choosePermission("AddPeople", appHomeMenuBeansBean);
                        break;
                    case "qz":
                        choosePermission("RegionVisit", appHomeMenuBeansBean);
                        break;
                    case "qp":
                        choosePermission("RegionTraining", appHomeMenuBeansBean);
                        break;
                    case "yd":
                        choosePermission("MonthlySummary", appHomeMenuBeansBean);
                        break;
                    case "fx":
                        choosePermission("PressureTesterReturn", appHomeMenuBeansBean);
                        break;
                    case "sy":
                        choosePermission("PressureTestAdd", appHomeMenuBeansBean);
                        break;
                    case "lzs"://离职
                        choosePermission("LeaveApplication", appHomeMenuBeansBean);
                        break;
                    case "rs"://入职
                        choosePermission("EntryApplication", appHomeMenuBeansBean);
                        break;
                    case "jxs"://经销商
                        choosePermission("DealerClose", appHomeMenuBeansBean);
                        break;
                }


            }
        }
    }

    private AppPermissionsBean permissionsBean;

    private void choosePermission(String type, AppHomeMenuBeansBean appHomeMenuBeansBean) {
        if (type.equals(appHomeMenuBeansBean.getMenu_key())) {
            permissionsBean = appHomeMenuBeansBean.getAppPermissionsBeans();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public MessagePresenter createPresenter() {
        return new MessagePresenter(getApp());
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
                if ("sy".equals(type)) {
                    if ("2".equals(userBean.getStaff_sign())) {
                        startTestPressureDetailFragment(form_no, permissionsBean);
                    } else {
                        startOrderManagerDetailFragment(form_no, permissionsBean);
                    }
                    return;
                }
                if (TextUtils.isEmpty(role_id)) {
                    return;
                }
                switch (type) {
                    //zlf 质量反馈 wxj 维修记录 lxg 滤芯更换
                    case "zlf":
                        startFeedBackDetailFragment(role_id, permissionsBean);
                        break;
                    case "wxj":
                        startFixRecordDetailFragment(role_id, permissionsBean);
                        break;
                    case "lxg":
                        startFilterReplaceDetailFragment(role_id, permissionsBean);
                        break;
                    case "daily":
                        startDailyDetailFragment(role_id, permissionsBean);
                        break;
                    case "monthly_report":
                        startMonthDetailFragment(role_id, permissionsBean);
                        break;
                    case "weekly":
                        startWeeklyDetailFragment(role_id, permissionsBean);
                        break;
                    case "ry":
                        startPeopleDetailFragment(role_id, permissionsBean);
                        break;
                    case "qz":
                        startInterviewDetailFragment(role_id, permissionsBean);
                        break;
                    case "qp":
                        startRegionDetailFragment(role_id, permissionsBean);
                        break;
                    case "yd":
                        startMonthlySummaryDetailFragment(role_id, permissionsBean);
                        break;
                    case "fx":
                        startreworkDetailFragment(role_id, permissionsBean);
                        break;
                    case "lzs"://离职
                        startLeaveDetailFragment(role_id, permissionsBean);
                        break;
                    case "rs"://入职
                        startEntryDetailFragment(role_id, permissionsBean);
                        break;
                    case "jxs"://经销商
                        startCloseDetailFragment(role_id, permissionsBean);
                        break;
                }
                break;

        }
    }

    @Override
    public void onGetStaffMessageList(List<StaffMessageBean> data) {

    }

    @Override
    public void onGetNotReadMessageCount(String data) {

    }

    @Override
    public void onDeleteStaffMessages(String data) {

    }
}
