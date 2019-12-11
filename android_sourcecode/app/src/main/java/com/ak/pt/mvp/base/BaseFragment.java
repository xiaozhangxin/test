package com.ak.pt.mvp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ak.pt.App;
import com.ak.pt.Constants;
import com.ak.pt.bean.AddressBean;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.AreaStudyBean;
import com.ak.pt.bean.DailyBean;
import com.ak.pt.bean.FeedBackBean;
import com.ak.pt.bean.FilterReplaceBean;
import com.ak.pt.bean.FixRecordBean;
import com.ak.pt.bean.GoodsSpecificationBeans;
import com.ak.pt.bean.InterviewBean;
import com.ak.pt.bean.MonthTotalBean;
import com.ak.pt.bean.NoticeBean;
import com.ak.pt.bean.PeopleBean;
import com.ak.pt.bean.PhotoListBean;
import com.ak.pt.bean.PressureBackBean;
import com.ak.pt.bean.PressureDropBean;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.bean.QuitJobBean;
import com.ak.pt.bean.ShopCloseBean;
import com.ak.pt.bean.SignBean;
import com.ak.pt.bean.StaffApplyBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.bean.WarrantyBean;
import com.ak.pt.mvp.activity.ContentActivity;
import com.ak.pt.mvp.fragment.LoginActivity;
import com.ak.pt.util.SPUtils;
import com.ak.pt.util.SpSingleInstance;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.king.base.util.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/20
 */

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends MvpFragment<V, P> {

    protected Context context;

    private View rootView;

    private Unbinder mUnbinder;

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


    public static void registerEvent(Object obj) {
        EventBus.getDefault().register(obj);
    }

    public static void unregisterEvent(Object obj) {
        EventBus.getDefault().unregister(obj);
    }

    public static void sendEvent(Object obj) {
        EventBus.getDefault().post(obj);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Object obj) {

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

    //保存个人信息
    protected void saveUser(UserBean userBean) {
        // 登录成功
        SPUtils.saveObJ1(getContext(), Constants.USER_BEAN, userBean);
        SpSingleInstance.getSpSingleInstance().setUserBean(userBean);
        //SPUtils.saveString(getActivity(), "uid", userBean.getToken_staff_id());
        //SPUtils.saveString(getActivity(), "token", userBean.getStaff_token());
    }

    //退出登陆返回登陆页面
    protected void exitLogin() {
        SPUtils.saveObJ1(getContext(), Constants.USER_BEAN, null);
        startLogin();
        finish();

    }

    protected void startSignDetailFragment(SignBean bean) {
        Intent intent = getFragmentIntent(Constants.Sign_DETAIL);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        startActivity(intent);
    }

    //登录
    protected void startLogin() {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
    }

    protected void startForgetPwdFragment() {
        Intent intent = getFragmentIntent(Constants.FORGOT_PWD);
        startActivity(intent);
    }

    protected void startDepartmentPermissionFragment(String id, String type) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_DEPARTMENT_PERMISSION);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.TYPE, type);
        startActivity(intent);
    }

    protected void startChooseWorkerFragment(AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_WORKER);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startChooseCheckPersonFragment(String id) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_CHECK_PERSON);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startChooseCheckFragment(String id) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_CHECK);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startIntegralSearchFragment() {
        Intent intent = getFragmentIntent(Constants.INTEGRAL_SEARCH);
        startActivity(intent);
    }

    protected void startSecurityListFragment(AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.SECURITY_LIST);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    //离职申请选择人员
    protected void startChoosePeopleFragment(String type, String uuid, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ChHOOSE_PEOPLE_LEAVE_LIST);
        intent.putExtra(Constants.TYPE, type);
        intent.putExtra(Constants.DETAIL_ID, uuid);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startAllOrderFragment() {
        Intent intent = getFragmentIntent(Constants.ALL_ORDER);
        startActivity(intent);
    }

    protected void startOfficeSerachFragment() {
        Intent intent = getFragmentIntent(Constants.OFFICE_SEARCH);
        startActivity(intent);
    }

    protected void startDocumentSerachFragment(AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.DOCUMENT_SEARCH);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startMonthDetailFragment(String staff_id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.MONTH_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, staff_id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startOrderDetailFragment(String staff_id) {
        Intent intent = getFragmentIntent(Constants.ORDER_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, staff_id);
        startActivity(intent);
    }

    protected void startAllTypeTableFragment() {
        Intent intent = getFragmentIntent(Constants.ALL_TABLE);
        startActivity(intent);
    }

    protected void startTableTwoFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_TWO);
        startActivity(intent);
    }

    protected void startTableThreeFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_THREE);
        startActivity(intent);
    }

    protected void startTableFourFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_FOUR);
        startActivity(intent);
    }

    protected void startTableFiveFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_FIVE);
        startActivity(intent);
    }

    protected void startTableSixFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_SIX);
        startActivity(intent);
    }

    protected void startTableSevenFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_SEVEN);
        startActivity(intent);
    }

    protected void startTableEightFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_EIGHT);
        startActivity(intent);
    }

    protected void startTableNineFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_NINE);
        startActivity(intent);
    }

    protected void startTableTenFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_TEN);
        startActivity(intent);
    }

    protected void startTableElevenFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_ELEVEN);
        startActivity(intent);
    }

    protected void startTableTwelveFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_TWELVE);
        startActivity(intent);
    }

    protected void startTableThirteenFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_THIRTEEN);
        startActivity(intent);
    }

    protected void startTableforteenFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_FORTEEN);
        startActivity(intent);
    }

    protected void startTablefifteenFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_FIFTEEN);
        startActivity(intent);
    }

    protected void startTableSixteenFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE_SIXTEEN);
        startActivity(intent);
    }

    protected void staretNoticeDetailFragment(NoticeBean bean) {
        Intent intent = getFragmentIntent(Constants.NOTICE_DETAIL);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        startActivity(intent);
    }

    protected void startDailyDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.DAILY_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.DETAIL_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startGoodsDetailFragment(String id) {
        Intent intent = getFragmentIntent(Constants.GOODS_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startFilterReplaceDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.FILTER_REPLACE_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startWarrantyDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.WARRANTY_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startPeopleDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.PEOPLE_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startEntryDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ENTRY_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startLeaveDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.LEAVE_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }   protected void startCloseDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.CLOSE_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startreworkDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.REWORK_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startFeedBackDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.FEED_BACK_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startRegionDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.REGION_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startInterviewDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.INTERVIEW_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startMonthlySummaryDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.MONTH_SUMMARY_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startFixRecordDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.FIX_RECORD_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startConfirmOrderFragment(GoodsSpecificationBeans spBean, String id) {
        Intent intent = getFragmentIntent(Constants.CONFIRM_ORDER);
        intent.putExtra(Constants.DETAIL_BEAN, spBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startPeopleAddFragment(PeopleBean spBean, String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.PEOPLE_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, spBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startEntryAddFragment(StaffApplyBean spBean, String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ENTRY_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, spBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startLeaveAddFragment(QuitJobBean spBean, String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.LEAVE_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, spBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }  protected void startCloseAddFragment(ShopCloseBean spBean, String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.CLOSE_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, spBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startreworkAddFragment(PressureBackBean spBean, String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.REWORK_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, spBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startWarrantyAddFragment(WarrantyBean spBean, String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.WARRANTY_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, spBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startInterviewAddFragment(InterviewBean spBean, String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.INTERVIEW_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, spBean);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    //收货地址
    protected void startAddressFragment() {
        Intent intent = getFragmentIntent(Constants.MALL_ADDRESS);
        startActivity(intent);
    }

    protected void startOrderCompleteFragment(String id) {
        Intent intent = getFragmentIntent(Constants.ORDER_COMPLETE);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startAddressAddFragment(AddressBean bean, String id) {
        Intent intent = getFragmentIntent(Constants.MALL_ADDRESS_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startMonthAddFragment(DailyBean bean, String id, AppPermissionsBean permissionBean) {
        Intent intent = getFragmentIntent(Constants.MONTH_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startWeeklyAddFragment(DailyBean bean, String id, AppPermissionsBean permissionBean) {
        Intent intent = getFragmentIntent(Constants.WEEKLY_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startWeeklyDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.WEEKLY_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startDailyAddFragment(DailyBean bean, String id, AppPermissionsBean permissionBean) {
        Intent intent = getFragmentIntent(Constants.DAILY_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startFilterReplaceAddFragment(FilterReplaceBean bean, String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.FILTER_REPLACE_ADD_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startFixRecordAddFragment(FixRecordBean bean, String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.FIX_RECORD_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startRegionAddFragment(AreaStudyBean bean, String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.REGION__ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startMonthSummaryAddFragment(MonthTotalBean bean, String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.SUMMARY_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startFeedBackAddFragment(FeedBackBean bean, String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.FEED_BACK_ADD);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startNoticeFileFragment(List<NoticeBean.NoticeFileBeans> list, String imgName) {
        Intent intent = getFragmentIntent(Constants.NOTICE_FILE);
        intent.putExtra(Constants.IMG_LIST, (Serializable) list);
        intent.putExtra(Constants.IMG_NAME, imgName);
        startActivity(intent);
    }

    protected void startOrderImgTwoFragment(List<PhotoListBean> list) {
        Intent intent = getFragmentIntent(Constants.IMG_TWO);
        intent.putExtra(Constants.IMG_LIST, (Serializable) list);
        startActivity(intent);
    }

    protected void startQFOrderImgTwoFragment(List<com.ak.pt.mvp.fragment.qifei.PressurePageBean.PiptbBaseTypeBean> serviceOverImgBeans, String imgName) {
        Intent intent = getFragmentIntent(Constants.ORDER_IMG_TWO);
        intent.putExtra(Constants.IMG_LIST, (Serializable) serviceOverImgBeans);
        intent.putExtra(Constants.IMG_NAME, imgName);
        startActivity(intent);
    }

    protected void startNoticeSearchFragment() {
        Intent intent = getFragmentIntent(Constants.NOTICE_SEARCH);
        startActivity(intent);
    }

    protected void staretMessageDetailFragment(String id, String is_red, String type) {
        Intent intent = getFragmentIntent(Constants.MESSAGE_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.IS_RED, is_red);
        intent.putExtra(Constants.TYPE, type);
        startActivity(intent);
    }

    protected void startWeb(String title, String url) {
        Intent intent = getFragmentIntent(Constants.WEB_FRAGMENT);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_URL, url);
        startActivity(intent);
    }

    protected void startSignDetailNewFragment(String id, String head_img) {
        Intent intent = getFragmentIntent(Constants.Sign_DETAIL_NEW);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.HEAD_IMG, head_img);
        startActivity(intent);
    }

    protected void startWeb(String title, String code, String url) {
        Intent intent = getFragmentIntent(Constants.WEB_FRAGMENT);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.DETAIL_ID, code);
        intent.putExtra(Constants.KEY_URL, url);
        startActivity(intent);
    }


    //个人资料
    protected void startPersonalnfoFragment() {
        Intent intent = getFragmentIntent(Constants.PERSONAL_INFO);
        startActivity(intent);
    }

    //修改密码
    protected void startChangePwdFragment() {
        Intent intent = getFragmentIntent(Constants.CHANGE_PWD);
        startActivity(intent);
    }

    protected void startUpImgFragment(String id, String address) {
        Intent intent = getFragmentIntent(Constants.UP_IMG);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.ADDRESS, address);
        startActivity(intent);
    }

    protected void startSaftwareFragment() {
        Intent intent = getFragmentIntent(Constants.SAFTWARE);
        startActivity(intent);
    }

    protected void startAccountFragment() {
        Intent intent = getFragmentIntent(Constants.ACCOUNT);
        startActivity(intent);
    }

    protected void startSecurityCheckFragment(AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.CHECK);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startTestPressureFragment(int type, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.TEST_PRESSURE);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.STATISTICS_TYPE, type);
        startActivity(intent);
    }

    protected void startOrderAcceptFragment(AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ORDER_ACCEPT);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }


    protected void startOrderManagerFragment(AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ORDER_MANAGER);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startOrderAddWorkerFragment(AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ORDER_ADD_WORKER);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }
  protected void startOrderSearchFragment(String id,AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ORDER_SEARCH);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startOrderAddFragment(PressurePageBean bean, String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.ORDER_ADD);
        intent.putExtra(Constants.DETAIL_BEAN, bean);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startBleListFragment(PressurePageBean bean) {
        Intent intent = getFragmentIntent(Constants.BLE_LIST);
        intent.putExtra(Constants.DETAIL_BEAN, bean);

        startActivity(intent);
    }

    protected void startPTSettingChildFragment(PressureDropBean pressureDropBean) {
        Intent intent = getFragmentIntent(Constants.PT_SETTING_CHILD);
        intent.putExtra(Constants.DETAIL_BEAN, pressureDropBean);
        startActivity(intent);
    }

    protected void startAreaTestPressureFragment(int type, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.AREA_PRESSURE);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.STATISTICS_TYPE, type);
        startActivity(intent);
    }

    protected void startBookFragment(AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.BOOK);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startBigTestPressureFragment(int type) {
        Intent intent = getFragmentIntent(Constants.BIG_PRESSURE);
        intent.putExtra(Constants.STATISTICS_TYPE, type);
        startActivity(intent);
    }

    protected void startIntegralFragment() {
        Intent intent = getFragmentIntent(Constants.INTEGRAL);
        startActivity(intent);
    }

    protected void startTestPressureDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.PRESSURE_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startQFTestPressureDetailFragment(String id) {
        Intent intent = getFragmentIntent(Constants.PRESSURE_QF_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startOfficeDocumentFragment() {
        Intent intent = getFragmentIntent(Constants.OFFICE_DOCUMENT);
        startActivity(intent);
    }

    protected void startDocumentFragment(AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.DOCUMENT);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startMallFragment(AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.MALL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        startActivity(intent);
    }

    protected void startTableFragment() {
        Intent intent = getFragmentIntent(Constants.TABLE);
        startActivity(intent);
    }

    protected void startFilterReplaceListFragment() {
        Intent intent = getFragmentIntent(Constants.FILTER_REPLACE_LIST);
        startActivity(intent);
    }

    protected void startOrderManagerDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.MANAGER_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startOrderCompleteDetailFragment(String id, AppPermissionsBean permissionsBean) {
        Intent intent = getFragmentIntent(Constants.COMPLETE_DETAIL);
        intent.putExtra(Constants.PERMISSION_BEAN, permissionsBean);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }


    //消息
    protected void startMessageFragment() {
        Intent intent = getFragmentIntent(Constants.MESSAGE);
        startActivity(intent);
    }

    protected void startNoticeFragment() {
        Intent intent = getFragmentIntent(Constants.NOTICE);
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
