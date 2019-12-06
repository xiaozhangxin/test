package com.akan.qf.mvp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.akan.qf.App;
import com.akan.qf.Constants;
import com.akan.qf.bean.AdManagementBean;
import com.akan.qf.bean.ArchivesApplyBean;
import com.akan.qf.bean.CompanyBean;
import com.akan.qf.bean.ContractApplyBean;
import com.akan.qf.bean.ContributeBean;
import com.akan.qf.bean.DailyBean;
import com.akan.qf.bean.DebtApplyBean;
import com.akan.qf.bean.FinancialBean;
import com.akan.qf.bean.LableBean;
import com.akan.qf.bean.LeaveBean;
import com.akan.qf.bean.NetworkBean;
import com.akan.qf.bean.NewApplyBean;
import com.akan.qf.bean.NoticeBean;
import com.akan.qf.bean.PayApplyBean;
import com.akan.qf.bean.PeopleJionBean;
import com.akan.qf.bean.PeopleLeaveBean;
import com.akan.qf.bean.PeopleNewBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.PolicyBean;
import com.akan.qf.bean.ProfitBean;
import com.akan.qf.bean.ReimbursementInfoBean;
import com.akan.qf.bean.ReprotedBean;
import com.akan.qf.bean.RetnrnBean;
import com.akan.qf.bean.SaleDataBean;
import com.akan.qf.bean.SaleDataContrastBean;
import com.akan.qf.bean.SaleForecastBean;
import com.akan.qf.bean.StoreApplyBean;
import com.akan.qf.bean.TaskBean;
import com.akan.qf.bean.TemporaryBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.bean.VisitorBean;
import com.akan.qf.bean.WaterBean;
import com.akan.qf.mvp.activity.ContentActivity;
import com.akan.qf.mvp.fragment.qifei.PressurePageBean;
import com.akan.qf.mvp.fragment.statistics.PhotoListBean;
import com.akan.qf.util.SPUtils;
import com.akan.qf.util.SpSingleInstance;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.king.base.util.LogUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends MvpFragment<V, P> {

    protected Context context;

    private View rootView;

    private Unbinder mUnbinder;

    public static void registerEvent(Object obj) {
        EventBus.getDefault().register(obj);
    }

    public static void unregisterEvent(Object obj) {
        EventBus.getDefault().unregister(obj);
    }

    public static void sendEvent(Object obj) {
        EventBus.getDefault().post(obj);
    }

    public <T extends View> T findView(@IdRes int id) {
        return (T) rootView.findViewById(id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        rootView = inflater.inflate(getRootViewId(), container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        LogUtils.d("onCreateView");
        initUI();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }

    public View getRootView() {
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public void replaceFragment(@IdRes int id, Fragment fragment) {
        getFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public void replaceChildFragment(@IdRes int id, Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public App getApp() {
        return (App) getActivity().getApplication();
    }

    public <T> void toSetList(List<T> list, List<T> newList, boolean isMore) {

        if (list != null && newList != null) {
            synchronized (BaseFragment.class) {
                if (!isMore) {
                    list.clear();
                }
                list.addAll(newList);
            }
        }
    }


/*
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        switch (event.getMsg()) {
            case "expired":
                saveUser(null);
                break;
        }
    }
*/

    //保存个人信息
    protected void saveUser(UserBean userBean) {
        SPUtils.saveObJ1(getContext(), Constants.USER_BEAN, userBean);
        SpSingleInstance.getSpSingleInstance().setUserBean(userBean);
        SPUtils.saveString(getActivity(), "uid", userBean.getToken_staff_id());
        SPUtils.saveString(getActivity(), "token", userBean.getStaff_token());
    }

    //--------------------------------

    protected Intent getIntent() {
        return getActivity().getIntent();
    }


    protected Intent getFragmentIntent(int fragmentKey) {
        Intent intent = getContentActivityIntent();
        intent.putExtra(Constants.KEY_FRAGMENT, fragmentKey);
        return intent;
    }

    protected Intent getContentActivityIntent() {
        return new Intent(context, ContentActivity.class);
    }

    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(context, cls));
    }


    protected void finish() {
        getActivity().finish();
    }


    protected void startWeb(String title, String url) {
        Intent intent = getFragmentIntent(Constants.WEB_FRAGMENT);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_URL, url);
        startActivity(intent);
    }


    protected void startWeb(String title, String url, String type) {
        Intent intent = getFragmentIntent(Constants.WEB_FRAGMENT);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_URL, url);
        intent.putExtra(Constants.KEY_SLUG, type);
        startActivity(intent);
    }

    //退出登陆返回登陆页面
    protected void exitLogin() {
        SPUtils.saveObJ1(getContext(), Constants.USER_BEAN, null);
        startLogin();
        finish();
    }

    //登录
    protected void startLogin() {
        Intent intent = getFragmentIntent(Constants.LOGIN_FRAGMENT);
        startActivity(intent);
    }

    protected void startForgetPwdFragment(String detail_id) {
        Intent intent = getFragmentIntent(Constants.FORGOT_PWD);
        intent.putExtra(Constants.DETAIL_ID, detail_id);
        startActivity(intent);
    }

    //登录验证
    protected void startVerificationfragment(UserBean bean, String id) {
        Intent intent = getFragmentIntent(Constants.LOGIN_VERIFICATION);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startOrderImgTwoFragment(List<PressurePageBean.PiptbBaseTypeBean> serviceOverImgBeans, String imgName) {
        Intent intent = getFragmentIntent(Constants.ORDER_IMG_TWO);
        intent.putExtra(Constants.IMG_LIST, (Serializable) serviceOverImgBeans);
        intent.putExtra(Constants.IMG_NAME, imgName);
        startActivity(intent);
    }

    protected void startOrderImgTwoFragment(List<PhotoListBean> list) {
        Intent intent = getFragmentIntent(Constants.IMG_TWO);
        intent.putExtra(Constants.IMG_LIST, (Serializable) list);
        startActivity(intent);
    }

    protected void startNoticeFileFragment(List<NoticeBean.NoticeFileBeans> list, String imgName, List<String> fileList, String id) {
        Intent intent = getFragmentIntent(Constants.NOTICE_FILE);
        intent.putExtra(Constants.IMG_LIST, (Serializable) list);
        intent.putExtra(Constants.FILE_LIST, (Serializable) fileList);
        intent.putExtra(Constants.IMG_NAME, imgName);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }


    protected void startNoticeSearchFragment() {
        Intent intent = getFragmentIntent(Constants.NOTICE_SEARCH);
        startActivity(intent);
    }


    protected void StartChooseDepartmentFragment(String type) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_DEPARTMENT);
        intent.putExtra(Constants.DEPARTMENT_TYPE, type);
        startActivity(intent);
    }

    protected void startDepartmentPermissionFragment(String id, String type) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_DEPARTMENT_PERMISSION);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.DEPARTMENT_TYPE, type);
        startActivity(intent);
    }

    protected void startDailyDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.DAILY_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startProjectDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.PROJECT_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startMonthDetailFragment(String staff_id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.MONTH_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, staff_id);
        startActivity(intent);
    }

    protected void startCostStatisticsDetailFragment(String staff_id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.COSTSTATISTIC);
        intent.putExtra(Constants.DETAIL_ID, staff_id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startTestPressureDetailFragment(String id) {
        Intent intent = getFragmentIntent(Constants.PRESSURE_QF_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startOrderCompleteDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.COMPLETE_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startWebLoginFragment(String id) {
        Intent intent = getFragmentIntent(Constants.WEB_LOGIN);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void staretNoticeDetailFragment(NoticeBean bean) {
        Intent intent = getFragmentIntent(Constants.NOTICE_DETAIL);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        startActivity(intent);
    }


    protected void startSignDetailNewFragment(String id, String head_img) {
        Intent intent = getFragmentIntent(Constants.Sign_DETAIL_NEW);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.HEAD_IMG, head_img);
        startActivity(intent);
    }

    protected void startProblemDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.PROBLEM_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startReturnDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.RETURN_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startPolicyDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.POLICY);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startWeeklyDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.WEEKLY_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startVisitDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.VISIT_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startPeopleJoinDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.JOIN_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startPeopleNewDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.NEW_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startPeopleLeaveDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.PEOPLE_LEAVE_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }


    protected void startADShopDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.AD_SHOP_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startADImageDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.AD_IMAGE_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startADPromotionDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.AD_PROMOTION_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }


    protected void startReportDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.REPORT_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startArrearsDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ARREARS_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }


    protected void startPersonInfoDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.PERSON_INFO_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startDispatchDetailFragment(TaskBean bean) {
        Intent intent = getFragmentIntent(Constants.DISPATCH_DETAIL);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        startActivity(intent);
    }

    protected void startSaleForecastDetailFragment(SaleForecastBean bean, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.SALE_FORECAST_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        startActivity(intent);
    }

    protected void startCustomerContractDetailFragment(ContractApplyBean bean, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.CONTRACT_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        startActivity(intent);
    }

    protected void startCustomerFileDetailFragment(ArchivesApplyBean bean) {
        Intent intent = getFragmentIntent(Constants.FILE_DETAIL);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        startActivity(intent);
    }

    protected void startLeaveAddFragment(LeaveBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.LEAVE_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startPolicyAddFragment(PolicyBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.POLICY_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startTemraryAddFragment(TemporaryBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.TEMARAY_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startPayRequestAddFragment(PayApplyBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.PAYREQUEST_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startReturnAddFragment(RetnrnBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.RETURN_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startVisitAddFragment(VisitorBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.VISIT_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startReimburseAddFragment(ReimbursementInfoBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.REIMBURSE_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startArrearsAddFragment(DebtApplyBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ARREARS_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startReportedAddFragment(ReprotedBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.REPORTED_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startDailyAddFragment(DailyBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.DAILY_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startProblemAddFragment(NewApplyBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.PROBLEM_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startWeeklyAddFragment(DailyBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.WEEKLY_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startCompanyAddFragment(CompanyBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.COMPANY_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startEngineerAddFragment(StoreApplyBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ENGINEER_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startWaterAddFragment(WaterBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.WATER_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startDistributionAddFragment(NetworkBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.DISTRIBUTION_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startProjectAddFragment(CompanyBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.PROJECT_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startContractAddFragment(ContractApplyBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.Contract_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startFileAddFragment(ArchivesApplyBean bean, String id) {
        Intent intent = getFragmentIntent(Constants.File_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startMonthAddFragment(DailyBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.MONTH_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }


    protected void startPeopleJoinAddFragment(PeopleJionBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.JOIN_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startPeopleNewAddFragment(PeopleNewBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.NEW_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startADShopAddFragment(AdManagementBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.AD_SHOP_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startADImageAddFragment(AdManagementBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.AD_Image_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startADPromotionAddFragment(AdManagementBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.AD_PROMOTION_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startPeopleLeaveAddFragment(PeopleLeaveBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.PEOPLE_LEAVE_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startCostStatisticsAddFragment(FinancialBean bean, String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.COSTSTATICTIS_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startDispatchFragment(PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.DISPATCH);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    //投稿详情
    protected void startArticleDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ARTICLE_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    //投稿添加
    protected void startArticleAddFragment(String type, String ids, String id, ContributeBean bean, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ARTICLE_ADD);
        intent.putExtra(Constants.DEPARTMENT_TYPE, type);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_IDS, ids);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        startActivity(intent);
    }

    protected void startSaleDataDetailFragment(SaleDataBean bean) {
        Intent intent = getFragmentIntent(Constants.SALEDATE_DETAIL);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        startActivity(intent);
    }

    protected void startAnalysisDetailFragment(SaleDataContrastBean bean) {
        Intent intent = getFragmentIntent(Constants.ANALYSIS_DETAIL);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        startActivity(intent);
    }

    protected void startProfitReportDetailFragment(ProfitBean bean) {
        Intent intent = getFragmentIntent(Constants.PROFIT_DETAIL);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        startActivity(intent);
    }

    protected void startSaleDataFragment(PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.SALEDATE);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void StartChooseTableFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_TABLE);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }


    protected void startAnalysisFragment(PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ANALYSIS);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startTestPressureFragment(int type, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.TEST_PRESSURE);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.STATISTICS_TYPE, type);
        startActivity(intent);
    }

    protected void startAreaTestPressureFragment(int type, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.AREA_PRESSURE);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.STATISTICS_TYPE, type);
        startActivity(intent);
    }

    protected void startBigTestPressureFragment(int type) {
        Intent intent = getFragmentIntent(Constants.BIG_PRESSURE);
        intent.putExtra(Constants.STATISTICS_TYPE, type);
        startActivity(intent);
    }

    protected void startProfitReportFragment(PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.PROFIT_REPORT);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startPersonInfoFragment(PermissionsBean permissionsBean, List<LableBean> signList) {
        Intent intent = getFragmentIntent(Constants.PERSONINFO);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.FILE_LIST, (Serializable) signList);
        startActivity(intent);
    }

    protected void startOfficeDocumentFragment() {
        Intent intent = getFragmentIntent(Constants.OFFICE_DOCUMENT);
        startActivity(intent);
    }

    protected void staretMessageDetailFragment(String id, String is_red, String type) {
        Intent intent = getFragmentIntent(Constants.MESSAGE_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.IS_RED, is_red);
        intent.putExtra(Constants.DEPARTMENT_TYPE, type);
        startActivity(intent);
    }

    protected void startLeaveDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.LEAVE_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startPayRequestDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.PAY_REQUEST);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startPayTemraryDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.TEMRARY);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void staretCompanyDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.COMPANY);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startEngineerDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ENGINEER);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startWaterDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.WATER);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startDistribtionDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.DISTRIBUTION);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startReimburseDetailFragment(String id, PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.REIMBURSE_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }


    protected void startChooseCheckPersonFragment(String id) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_CHECK_PERSON);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startChooseCopyPersonFragment() {
        Intent intent = getFragmentIntent(Constants.CHOOSE_COPY_PERSON);
        startActivity(intent);
    }

    //设置
    protected void startSettingFragment() {
        Intent intent = getFragmentIntent(Constants.SZ);
        startActivity(intent);
    } //设置

    protected void startCardFragment() {
        Intent intent = getFragmentIntent(Constants.CARD);
        startActivity(intent);
    }

    //个人资料
    protected void startPersonalnfoFragment() {
        Intent intent = getFragmentIntent(Constants.PERSONAL_INFO);
        startActivity(intent);
    }

    protected void startAccountFragment() {
        Intent intent = getFragmentIntent(Constants.ACCOUNT);
        startActivity(intent);
    }

    protected void startOfficeSerachFragment() {
        Intent intent = getFragmentIntent(Constants.OFFICE_SEARCH);
        startActivity(intent);
    }

    protected void startNoticeFragment() {
        Intent intent = getFragmentIntent(Constants.NOTICE);
        startActivity(intent);
    }

    //修改密码
    protected void startChangePwdFragment() {
        Intent intent = getFragmentIntent(Constants.CHANGE_PWD);
        startActivity(intent);
    }


    //区域投稿
    protected void startContributionsFragment(PermissionsBean permissionsBean, List<LableBean> signList) {
        Intent intent = getFragmentIntent(Constants.CONTRIBUTIONS);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.FILE_LIST, (Serializable) signList);
        startActivity(intent);
    }

    //通讯录
    protected void startBookListFragment(PermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.BOOK_LIST);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startCoordinationLetterFragment() {
        Intent intent = getFragmentIntent(Constants.COORDINATION);
        startActivity(intent);
    }

    protected void startSecurityCheckFragment() {
        Intent intent = getFragmentIntent(Constants.CHECK);
        startActivity(intent);
    }

    //用户反馈
    protected void startSuggestFragment() {
        Intent intent = getFragmentIntent(Constants.SUGGEST);
        startActivity(intent);
    }

    protected void startSaftwareFragment() {
        Intent intent = getFragmentIntent(Constants.SAFTWARE);
        startActivity(intent);
    }
    //--------------------------------

    /**
     * 隐藏软键盘
     *
     * @param v
     */
    public void hideInputMethod(final EditText v) {
        InputMethodManager imm = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        v.clearFocus();
    }

    /**
     * 显示软键盘
     *
     * @param v
     */
    public void showInputMethod(final EditText v) {
        v.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }

    public abstract int getRootViewId();

    public abstract void initUI();

    public abstract void initData();


}
