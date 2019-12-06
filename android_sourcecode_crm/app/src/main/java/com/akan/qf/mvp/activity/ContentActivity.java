package com.akan.qf.mvp.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.AdManagementBean;
import com.akan.qf.bean.LableBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.ArchivesApplyBean;
import com.akan.qf.bean.CompanyBean;
import com.akan.qf.bean.ContractApplyBean;
import com.akan.qf.bean.ContributeBean;
import com.akan.qf.bean.DailyBean;
import com.akan.qf.bean.DebtApplyBean;
import com.akan.qf.bean.FinancialBean;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.LeaveBean;
import com.akan.qf.bean.NetworkBean;
import com.akan.qf.bean.NewApplyBean;
import com.akan.qf.bean.NoticeBean;
import com.akan.qf.bean.PayApplyBean;
import com.akan.qf.bean.PeopleJionBean;
import com.akan.qf.bean.PeopleLeaveBean;
import com.akan.qf.bean.PeopleNewBean;
import com.akan.qf.bean.PolicyBean;
import com.akan.qf.mvp.fragment.qifei.PressurePageBean;
import com.akan.qf.bean.ProfitBean;
import com.akan.qf.bean.ReimbursementInfoBean;
import com.akan.qf.bean.ReprotedBean;
import com.akan.qf.bean.RetnrnBean;
import com.akan.qf.bean.SaleDataBean;
import com.akan.qf.bean.SaleDataContrastBean;
import com.akan.qf.bean.SaleForecastBean;
import com.akan.qf.bean.SignBean;
import com.akan.qf.bean.StoreApplyBean;
import com.akan.qf.bean.TaskBean;
import com.akan.qf.bean.TemporaryBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.bean.VisitorBean;
import com.akan.qf.bean.WaterBean;
import com.akan.qf.mvp.fragment.ChooseCheckPersonFragment;
import com.akan.qf.mvp.fragment.ChooseDepartmentFragmrnt;
import com.akan.qf.mvp.fragment.ChooseTableFragmrnt;
import com.akan.qf.mvp.fragment.DepartmentPermissionFragment;
import com.akan.qf.mvp.fragment.ForgetPwdFragment;
import com.akan.qf.mvp.fragment.LoginFragment;
import com.akan.qf.mvp.fragment.VerificationFragment;
import com.akan.qf.mvp.fragment.WebFragment;
import com.akan.qf.mvp.fragment.WebLoginFragment;
import com.akan.qf.mvp.fragment.adaily.DailyAddFragment;
import com.akan.qf.mvp.fragment.adaily.DailyDetailFragment;
import com.akan.qf.mvp.fragment.adaily.LeaveAddFragment;
import com.akan.qf.mvp.fragment.adaily.LeaveDetailFragment;
import com.akan.qf.mvp.fragment.adaily.MonthAddFragment;
import com.akan.qf.mvp.fragment.adaily.MonthDetailFragment;
import com.akan.qf.mvp.fragment.adaily.SignDetailNewFragment;
import com.akan.qf.mvp.fragment.adaily.WeeklyAddFragment;
import com.akan.qf.mvp.fragment.adaily.WeeklyDetailFragment;
import com.akan.qf.mvp.fragment.bapproval.ArrearsAddFragment;
import com.akan.qf.mvp.fragment.bapproval.ArrearsDetailFragment;
import com.akan.qf.mvp.fragment.bapproval.CostStatisticsAddFragment;
import com.akan.qf.mvp.fragment.bapproval.CostStatisticsDetailFragmrnt;
import com.akan.qf.mvp.fragment.bapproval.PayRequestAddFragment;
import com.akan.qf.mvp.fragment.bapproval.PayRequestDetailFragment;
import com.akan.qf.mvp.fragment.bapproval.PolicyAddFragment;
import com.akan.qf.mvp.fragment.bapproval.PolicyDetailFragment;
import com.akan.qf.mvp.fragment.bapproval.ProblemAddFragment;
import com.akan.qf.mvp.fragment.bapproval.ProblemDetailFragment;
import com.akan.qf.mvp.fragment.bapproval.ReimburseAddFragment;
import com.akan.qf.mvp.fragment.bapproval.ReimburseDetailFragment;
import com.akan.qf.mvp.fragment.bapproval.ReportAddFragment;
import com.akan.qf.mvp.fragment.bapproval.ReportedDetailFragment;
import com.akan.qf.mvp.fragment.bapproval.ReturnAddFragment;
import com.akan.qf.mvp.fragment.bapproval.ReturnDetailFragment;
import com.akan.qf.mvp.fragment.bapproval.TemporaryAddFragment;
import com.akan.qf.mvp.fragment.bapproval.TemraryDetailFragment;
import com.akan.qf.mvp.fragment.bapproval.VisitAddFragment;
import com.akan.qf.mvp.fragment.bapproval.VisitDetailFragment;
import com.akan.qf.mvp.fragment.cpeople.PeopleJoinAddFragment;
import com.akan.qf.mvp.fragment.cpeople.PeopleJoinDetailFragment;
import com.akan.qf.mvp.fragment.cpeople.PeopleLeaveAddFragment;
import com.akan.qf.mvp.fragment.cpeople.PeopleLeaveDetailFragment;
import com.akan.qf.mvp.fragment.cpeople.PeopleNewAddFragment;
import com.akan.qf.mvp.fragment.cpeople.PeopleNewDetailFragment;
import com.akan.qf.mvp.fragment.fsales.AnalysisDetailFragment;
import com.akan.qf.mvp.fragment.fsales.AnalysisFragment;
import com.akan.qf.mvp.fragment.qifei.AreaTestPressureFragment;
import com.akan.qf.mvp.fragment.qifei.BigTestPressureFragment;
import com.akan.qf.mvp.fragment.fsales.CustomerContractAddFragment;
import com.akan.qf.mvp.fragment.fsales.CustomerContractDetailFragment;
import com.akan.qf.mvp.fragment.fsales.CustomerFileAddFragment;
import com.akan.qf.mvp.fragment.fsales.CustomerFileDetailFragment;
import com.akan.qf.mvp.fragment.fsales.DispatchDetailFragment;
import com.akan.qf.mvp.fragment.fsales.DispatchFragment;
import com.akan.qf.mvp.fragment.fsales.ProfitReportDetailFragment;
import com.akan.qf.mvp.fragment.fsales.ProfitReportFragment;
import com.akan.qf.mvp.fragment.fsales.SaleDataDetailFragment;
import com.akan.qf.mvp.fragment.fsales.SaleDataFragment;
import com.akan.qf.mvp.fragment.fsales.SaleForecastDetailFragment;
import com.akan.qf.mvp.fragment.qifei.TestPressureDetailFragment;
import com.akan.qf.mvp.fragment.qifei.TestPressureFragment;
import com.akan.qf.mvp.fragment.gad.ADImageAddFragment;
import com.akan.qf.mvp.fragment.gad.ADImageDetailFragment;
import com.akan.qf.mvp.fragment.gad.ADPromotionAddFragment;
import com.akan.qf.mvp.fragment.gad.ADPromotionDetailFragment;
import com.akan.qf.mvp.fragment.gad.ADShopAddFragment;
import com.akan.qf.mvp.fragment.gad.ADShopDetailFragment;
import com.akan.qf.mvp.fragment.hchannel.CompanyAddFragment;
import com.akan.qf.mvp.fragment.hchannel.CompanyDetailFragment;
import com.akan.qf.mvp.fragment.hchannel.DistributionAddFragment;
import com.akan.qf.mvp.fragment.hchannel.DistributionDetailFragment;
import com.akan.qf.mvp.fragment.hchannel.EngineerAddFragment;
import com.akan.qf.mvp.fragment.hchannel.EngineerDetailFragment;
import com.akan.qf.mvp.fragment.hchannel.ProjectAddFragment;
import com.akan.qf.mvp.fragment.hchannel.ProjectDetailFragment;
import com.akan.qf.mvp.fragment.hchannel.WaterAddFragment;
import com.akan.qf.mvp.fragment.hchannel.WaterDetailFragment;
import com.akan.qf.mvp.fragment.home.BookListFragment;
import com.akan.qf.mvp.fragment.home.MessageDetailFragment;
import com.akan.qf.mvp.fragment.home.MessageFragment;
import com.akan.qf.mvp.fragment.home.OfficeDocumentFragment;
import com.akan.qf.mvp.fragment.home.OfficeSearchFragment;
import com.akan.qf.mvp.fragment.home.OrderImgTwoFragment;
import com.akan.qf.mvp.fragment.home.PersonInfoDetailFragment;
import com.akan.qf.mvp.fragment.home.PersonInfoFragment;
import com.akan.qf.mvp.fragment.home.SignDetailFragment;
import com.akan.qf.mvp.fragment.mine.AccountFragment;
import com.akan.qf.mvp.fragment.mine.ArticleAddFragment;
import com.akan.qf.mvp.fragment.mine.ArticleClassFragment;
import com.akan.qf.mvp.fragment.mine.ArticleDetailFragment;
import com.akan.qf.mvp.fragment.mine.CardFragment;
import com.akan.qf.mvp.fragment.mine.ChangePwdFragment;
import com.akan.qf.mvp.fragment.mine.ArticleListFragment;
import com.akan.qf.mvp.fragment.mine.CoordinationLetterFragment;
import com.akan.qf.mvp.fragment.mine.NoticeDetailFragment;
import com.akan.qf.mvp.fragment.mine.NoticeFileFragment;
import com.akan.qf.mvp.fragment.mine.NoticeSearchFragment;
import com.akan.qf.mvp.fragment.mine.NoticeNewFragment;
import com.akan.qf.mvp.fragment.mine.PersonalInfoFragment;
import com.akan.qf.mvp.fragment.mine.SaftwareFragment;
import com.akan.qf.mvp.fragment.mine.SecurityCheckFragment;
import com.akan.qf.mvp.fragment.mine.SettingFragment;
import com.akan.qf.mvp.fragment.mine.SuggestFragment;
import com.akan.qf.mvp.fragment.statistics.OrderCompleteDetailFragment;
import com.akan.qf.mvp.fragment.statistics.OrderCompleteFragment;
import com.akan.qf.mvp.fragment.statistics.PhotoListBean;
import com.githang.statusbar.StatusBarCompat;
import com.king.base.util.LogUtils;
import com.umeng.socialize.UMShareAPI;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


public class ContentActivity extends AppCompatActivity {
    private static final int INVALID_VAL = -1;
    private static final int COLOR_DEFAULT = Color.parseColor("#7f000000");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        swichFragment(getIntent());
        EventBus.getDefault().register(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
        } else {
            setStatusViewColor(getResources().getColor(R.color.white));
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusViewColor(int statusColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (statusColor != INVALID_VAL) {
                getWindow().setStatusBarColor(statusColor);
            }
            return;
        }
//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            int color = COLOR_DEFAULT;
            ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
            if (statusColor != INVALID_VAL) {
                color = statusColor;
            }
            View statusBarView = new View(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(this));
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView, lp);
        }
    }

    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventExit(Boolean isBool) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        switch (event.getMsg()) {
            case "exit":
                finish();
                break;
            case "expired_two":
                finish();
                break;
        }
    }


    public void swichFragment(Intent intent) {

        int fragmentKey = intent.getIntExtra(Constants.KEY_FRAGMENT, 0);
        PermissionsBean permissionsBean = (PermissionsBean) intent.getSerializableExtra(Constants.PERMISSION_BEAN);
        switch (fragmentKey) {
            case Constants.MESSAGE:
                String sign_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(MessageFragment.newInstance(sign_id));
                break;
            case Constants.WEB_FRAGMENT:
                String tittle = getIntent().getStringExtra(Constants.KEY_TITLE);
                String url = getIntent().getStringExtra(Constants.KEY_URL);
                replaceFragment(WebFragment.newInstance(url, tittle, "0"));
                break;
            case Constants.FORGOT_PWD:
                String account = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ForgetPwdFragment.newInstance(account));
                break;
            case Constants.NOTICE:
                replaceFragment(NoticeNewFragment.newInstance());
                break;
            case Constants.PERSONAL_INFO:
                replaceFragment(PersonalInfoFragment.newInstance());
                break;
            case Constants.PROFIT_REPORT:
                replaceFragment(ProfitReportFragment.newInstance(permissionsBean));
                break;
            case Constants.LOGIN_VERIFICATION://登录验证
                UserBean ver_bean = (UserBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String uuid = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(VerificationFragment.newInstance(ver_bean, uuid));
                break;
            case Constants.PROFIT_DETAIL:
                ProfitBean fit_bean = (ProfitBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                replaceFragment(ProfitReportDetailFragment.newInstance(fit_bean));
                break;
            case Constants.CHOOSE_TABLE:
                String table_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ChooseTableFragmrnt.newInstance(table_id, permissionsBean));
                break;
            case Constants.COSTSTATISTIC:
                String CostStatistics_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(CostStatisticsDetailFragmrnt.newInstance(CostStatistics_id, permissionsBean));
                break;
            case Constants.BOOK_LIST:
                replaceFragment(BookListFragment.newInstance(permissionsBean));
                break;
            case Constants.LOGIN_FRAGMENT:
                replaceFragment(LoginFragment.newInstance());
                break;
            case Constants.SZ:
                replaceFragment(SettingFragment.newInstance());
                break;
            case Constants.OFFICE_DOCUMENT:
                replaceFragment(OfficeDocumentFragment.newInstance());
                break;
            case Constants.OFFICE_SEARCH:
                replaceFragment(OfficeSearchFragment.newInstance());
                break;

            case Constants.CARD:
                replaceFragment(CardFragment.newInstance());
                break;
            case Constants.DISPATCH:
                replaceFragment(DispatchFragment.newInstance(permissionsBean));
                break;
            case Constants.COORDINATION:
                replaceFragment(CoordinationLetterFragment.newInstance());
                break;
            case Constants.CHANGE_PWD://修改密码
                replaceFragment(ChangePwdFragment.newIntance());
                break;
            case Constants.ARTICLE_ADD:
                String type = getIntent().getStringExtra(Constants.DEPARTMENT_TYPE);
                String class_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                String class_ids = getIntent().getStringExtra(Constants.DETAIL_IDS);
                ContributeBean articleBean = (ContributeBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                replaceFragment(ArticleAddFragment.newInstance(type, class_ids, class_id, articleBean, permissionsBean));
                break;
            case Constants.PERSONINFO:
                List<LableBean> pSignList = (List<LableBean>) intent.getSerializableExtra(Constants.FILE_LIST);
                replaceFragment(PersonInfoFragment.newInstance(permissionsBean, pSignList));
                break;
            case Constants.CONTRIBUTIONS:
                List<LableBean> cSignList = (List<LableBean>) intent.getSerializableExtra(Constants.FILE_LIST);
                replaceFragment(ArticleListFragment.newInstance(permissionsBean, cSignList));
                break;
            case Constants.ACCOUNT:
                replaceFragment(AccountFragment.newInstance());
                break;
            case Constants.NOTICE_SEARCH:
                replaceFragment(NoticeSearchFragment.newInstance());
                break;
            case Constants.SUGGEST://用户反馈
                replaceFragment(SuggestFragment.newInstance());
                break;
            case Constants.DISPATCH_DETAIL:
                TaskBean task_bean = (TaskBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                replaceFragment(DispatchDetailFragment.newInstance(task_bean));
                break;
            case Constants.SALE_FORECAST_DETAIL:
                SaleForecastBean forcastr_bean = (SaleForecastBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                replaceFragment(SaleForecastDetailFragment.newInstance(forcastr_bean, permissionsBean));
                break;
            case Constants.NOTICE_DETAIL:
                NoticeBean notice_bean = (NoticeBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                replaceFragment(NoticeDetailFragment.newInstance(notice_bean));
                break;
            case Constants.FILE_DETAIL:
                ArchivesApplyBean file_bean = (ArchivesApplyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                replaceFragment(CustomerFileDetailFragment.newInstance(file_bean));
                break;
            case Constants.CONTRACT_DETAIL:
                ContractApplyBean xontract_bean = (ContractApplyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                replaceFragment(CustomerContractDetailFragment.newInstance(xontract_bean, permissionsBean));
                break;
            case Constants.ANALYSIS_DETAIL:
                SaleDataContrastBean analysis_bean = (SaleDataContrastBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                replaceFragment(AnalysisDetailFragment.newInstance(analysis_bean));
                break;
            case Constants.SALEDATE:
                replaceFragment(SaleDataFragment.newInstance(permissionsBean));
                break;
            case Constants.TEST_PRESSURE:
                int type1 = getIntent().getIntExtra(Constants.STATISTICS_TYPE, Constants.PRESSURE_RECORD_QIFEI);
                if (type1 == Constants.PRESSURE_RECORD_DEFAULT){
                    replaceFragment(OrderCompleteFragment.newInstance(permissionsBean));
                } else if (type1 == Constants.PRESSURE_RECORD_QIFEI){
                    replaceFragment(TestPressureFragment.newInstance());
                }
                break;
            case Constants.ANALYSIS:
                replaceFragment(AnalysisFragment.newInstance(permissionsBean));
                break;
            case Constants.AREA_PRESSURE:
                int type2 = getIntent().getIntExtra(Constants.STATISTICS_TYPE, Constants.PRESSURE_RECORD_QIFEI);
                if (type2 == Constants.PRESSURE_RECORD_DEFAULT){
                    replaceFragment(com.akan.qf.mvp.fragment.statistics.AreaTestPressureFragment.newInstance(permissionsBean));
                } else if (type2 == Constants.PRESSURE_RECORD_QIFEI){
                    replaceFragment(AreaTestPressureFragment.newInstance());
                }
                break;
            case Constants.BIG_PRESSURE:
                replaceFragment(BigTestPressureFragment.newInstance());
                int type3 = getIntent().getIntExtra(Constants.STATISTICS_TYPE, Constants.PRESSURE_RECORD_QIFEI);
                if (type3 == Constants.PRESSURE_RECORD_DEFAULT){
                    replaceFragment(com.akan.qf.mvp.fragment.statistics.BigTestPressureFragment.newInstance());
                } else if (type3 == Constants.PRESSURE_RECORD_QIFEI){
                    replaceFragment(BigTestPressureFragment.newInstance());
                }
                break;
            case Constants.ARTICLE_CLASS:
                String table = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ArticleClassFragment.newInstance(table));
                break;
            case Constants.ENGINEER:
                String en_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(EngineerDetailFragment.newInstance(en_id, permissionsBean));
                break;
            case Constants.SALEDATE_DETAIL:

                SaleDataBean sale_bean = (SaleDataBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                replaceFragment(SaleDataDetailFragment.newInstance(sale_bean));
                break;
            case Constants.LEAVE_ADD:
                LeaveBean leave_bean = (LeaveBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String leave_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(LeaveAddFragment.newInstance(leave_bean, leave_id, permissionsBean));
                break;
            case Constants.VISIT_ADD:
                VisitorBean vis_bean = (VisitorBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String vis_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(VisitAddFragment.newInstance(vis_bean, vis_id, permissionsBean));
                break;
            case Constants.COMPANY_ADD:
                CompanyBean com_bean = (CompanyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String com_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(CompanyAddFragment.newInstance(com_bean, com_id, permissionsBean));
                break;
            case Constants.ENGINEER_ADD:
                StoreApplyBean en_bean = (StoreApplyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String eng_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(EngineerAddFragment.newInstance(en_bean, eng_id, permissionsBean));
                break;
            case Constants.File_ADD:
                ArchivesApplyBean fil_bean = (ArchivesApplyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String fil_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(CustomerFileAddFragment.newInstance(fil_bean, fil_id));
                break;
            case Constants.Contract_ADD:
                ContractApplyBean cons_bean = (ContractApplyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String cons_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(CustomerContractAddFragment.newInstance(cons_bean, cons_id, permissionsBean));
                break;
            case Constants.WATER_ADD:
                WaterBean wat_bean = (WaterBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String wat_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(WaterAddFragment.newInstance(wat_bean, wat_id, permissionsBean));
                break;
            case Constants.DISTRIBUTION_ADD:
                NetworkBean dist_bean = (NetworkBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String dist_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(DistributionAddFragment.newInstance(dist_bean, dist_id, permissionsBean));
                break;
            case Constants.PROJECT_ADD:
                CompanyBean projec_bean = (CompanyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String projec_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ProjectAddFragment.newInstance(projec_bean, projec_id, permissionsBean));
                break;
            case Constants.COSTSTATICTIS_ADD:
                FinancialBean nci_bean = (FinancialBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String nci_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(CostStatisticsAddFragment.newInstance(nci_bean, nci_id, permissionsBean));
                break;
            case Constants.RETURN_ADD:
                RetnrnBean return_bean = (RetnrnBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String ret_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ReturnAddFragment.newInstance(return_bean, ret_id, permissionsBean));
                break;
            case Constants.POLICY_ADD:
                PolicyBean policy_bean = (PolicyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String policy_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(PolicyAddFragment.newInstance(policy_bean, policy_id, permissionsBean));
                break;
            case Constants.TEMARAY_ADD:
                TemporaryBean tm_bean = (TemporaryBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String tm_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(TemporaryAddFragment.newInstance(tm_bean, tm_id, permissionsBean));
                break;
            case Constants.PAYREQUEST_ADD:
                PayApplyBean re_bean = (PayApplyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String re_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(PayRequestAddFragment.newInstance(re_bean, re_id, permissionsBean));
                break;
            case Constants.PROBLEM_ADD:
                NewApplyBean pro_bean = (NewApplyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String pro_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ProblemAddFragment.newInstance(pro_bean, pro_id, permissionsBean));
                break;
            case Constants.REIMBURSE_ADD:
                ReimbursementInfoBean mb_bean = (ReimbursementInfoBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String mb_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ReimburseAddFragment.newInstance(mb_bean, mb_id, permissionsBean));
                break;
            case Constants.ARREARS_ADD:
                DebtApplyBean arr_bean = (DebtApplyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String arr_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ArrearsAddFragment.newInstance(arr_bean, arr_id, permissionsBean));
                break;
            case Constants.REPORTED_ADD:
                ReprotedBean rep_bean = (ReprotedBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String rep_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ReportAddFragment.newInstance(rep_bean, rep_id, permissionsBean));
                break;
            case Constants.DAILY_ADD:
                DailyBean daily_bean = (DailyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String daiyl_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(DailyAddFragment.newInstance(daily_bean, daiyl_id, permissionsBean));
                break;
            case Constants.WEEKLY_ADD:
                DailyBean Weekly_bean = (DailyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String Weekyl_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(WeeklyAddFragment.newInstance(Weekly_bean, Weekyl_id, permissionsBean));
                break;
            case Constants.MONTH_ADD:
                DailyBean onth_bean = (DailyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String onth_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(MonthAddFragment.newInstance(onth_bean, onth_id, permissionsBean));
                break;

            case Constants.AD_SHOP_ADD:
                AdManagementBean ad_shop_bean = (AdManagementBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String ad_shop_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ADShopAddFragment.newInstance(ad_shop_bean, ad_shop_id, permissionsBean));
                break;
            case Constants.AD_Image_ADD:

                AdManagementBean ad_img_bean = (AdManagementBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String ad_img_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ADImageAddFragment.newInstance(ad_img_bean, ad_img_id, permissionsBean));
                break;
            case Constants.AD_PROMOTION_ADD:
                AdManagementBean ad_p_bean = (AdManagementBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String ad_p_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ADPromotionAddFragment.newInstance(ad_p_bean, ad_p_id, permissionsBean));
                break;
            case Constants.JOIN_ADD:
                PeopleJionBean join_bean = (PeopleJionBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String join_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(PeopleJoinAddFragment.newInstance(join_bean, join_id, permissionsBean));
                break;
            case Constants.NEW_ADD:
                PeopleNewBean new_bean = (PeopleNewBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String new_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(PeopleNewAddFragment.newInstance(new_bean, new_id, permissionsBean));
                break;
            case Constants.PEOPLE_LEAVE_ADD:
                PeopleLeaveBean people_leave_bean = (PeopleLeaveBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String people_leave_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(PeopleLeaveAddFragment.newInstance(people_leave_bean, people_leave_id, permissionsBean));
                break;
            case Constants.PRESSURE_QF_DETAIL:
                String doc_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(TestPressureDetailFragment.newInstance(doc_id));
                break;

            case Constants.ORDER_IMG_TWO:
                List<PressurePageBean.PiptbBaseTypeBean> imgListTwo = (List<PressurePageBean.PiptbBaseTypeBean>) intent.getSerializableExtra(Constants.IMG_LIST);
                String imgNameTwo = intent.getStringExtra(Constants.IMG_NAME);
                replaceFragment(OrderImgTwoFragment.newInstance(imgListTwo, imgNameTwo));
                break;
            case Constants.NOTICE_FILE:
                List<NoticeBean.NoticeFileBeans> fileList = (List<NoticeBean.NoticeFileBeans>) intent.getSerializableExtra(Constants.IMG_LIST);
                List<String> fileListString = (List<String>) intent.getSerializableExtra(Constants.FILE_LIST);
                String fileName = intent.getStringExtra(Constants.IMG_NAME);
                String article_type = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(NoticeFileFragment.newInstance(fileList, fileName, fileListString, article_type));
                break;
            case Constants.DAILY_DETAIL:
                String staff_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(DailyDetailFragment.newInstance(staff_id, permissionsBean));
                break;
            case Constants.PERSON_INFO_DETAIL:
                String info_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(PersonInfoDetailFragment.newInstance(info_id, permissionsBean));
                break;
            case Constants.ARTICLE_DETAIL:
                String id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ArticleDetailFragment.newInstance(id, permissionsBean));
                break;
            case Constants.REPORT_DETAIL:
                String reported_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ReportedDetailFragment.newInstance(reported_id, permissionsBean));
                break;

            case Constants.PROBLEM_DETAIL:
                String problem_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ProblemDetailFragment.newInstance(problem_id, permissionsBean));
                break;
            case Constants.VISIT_DETAIL:
                String visit_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(VisitDetailFragment.newInstance(visit_id, permissionsBean));
                break;
            case Constants.RETURN_DETAIL:
                String return_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ReturnDetailFragment.newInstance(return_id, permissionsBean));
                break;
            case Constants.POLICY:
                String poliy_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(PolicyDetailFragment.newInstance(poliy_id, permissionsBean));
                break;
            case Constants.WEB_LOGIN:
                String value = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(WebLoginFragment.newInstance(value));
                break;
            case Constants.Sign_DETAIL:
                SignBean sign_bean = (SignBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                replaceFragment(SignDetailFragment.newInstance(sign_bean));
                break;
            case Constants.TEMRARY:
                String temrary_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(TemraryDetailFragment.newInstance(temrary_id, permissionsBean));
                break;
            case Constants.COMPANY:
                String company_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(CompanyDetailFragment.newInstance(company_id, permissionsBean));
                break;
            case Constants.WATER:
                String water_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(WaterDetailFragment.newInstance(water_id, permissionsBean));
                break;
            case Constants.DISTRIBUTION:
                String distribution_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(DistributionDetailFragment.newInstance(distribution_id, permissionsBean));
                break;
            case Constants.ARREARS_DETAIL:
                String arrs_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ArrearsDetailFragment.newInstance(arrs_id, permissionsBean));
                break;
            case Constants.PAY_REQUEST:
                String pay_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(PayRequestDetailFragment.newInstance(pay_id, permissionsBean));
                break;
            case Constants.WEEKLY_DETAIL:
                String weekly_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(WeeklyDetailFragment.newInstance(weekly_id, permissionsBean));
                break;
            case Constants.MONTH_DETAIL:
                String month_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(MonthDetailFragment.newInstance(month_id, permissionsBean));
                break;
            case Constants.JOIN_DETAIL:
                String join_detail = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(PeopleJoinDetailFragment.newInstance(join_detail, permissionsBean));
                break;
            case Constants.NEW_DETAIL:
                String news_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(PeopleNewDetailFragment.newInstance(news_id, permissionsBean));
                break;
            case Constants.PEOPLE_LEAVE_DETAIL:
                String people_leave = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(PeopleLeaveDetailFragment.newInstance(people_leave, permissionsBean));
                break;
            case Constants.AD_SHOP_DETAIL:
                String ad_shop = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ADShopDetailFragment.newInstance(ad_shop, permissionsBean));
                break;
            case Constants.CHOOSE_DEPARTMENT_PERMISSION:
                String permission_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                String perType = getIntent().getStringExtra(Constants.DEPARTMENT_TYPE);
                replaceFragment(DepartmentPermissionFragment.newInstance(permission_id, perType));
                break;
            case Constants.AD_IMAGE_DETAIL:
                String ad_img = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ADImageDetailFragment.newInstance(ad_img, permissionsBean));
                break;
            case Constants.AD_PROMOTION_DETAIL:
                String ad_pro = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ADPromotionDetailFragment.newInstance(ad_pro, permissionsBean));
                break;
            case Constants.MESSAGE_DETAIL:
                String msg_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                String is_red = getIntent().getStringExtra(Constants.IS_RED);
                String msgType = getIntent().getStringExtra(Constants.DEPARTMENT_TYPE);
                replaceFragment(MessageDetailFragment.newInstance(msg_id, is_red, msgType));
                break;
            case Constants.REIMBURSE_DETAIL:
                String reimburse_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ReimburseDetailFragment.newInstance(reimburse_id, permissionsBean));
                break;
            case Constants.CHECK:
                replaceFragment(SecurityCheckFragment.newInstance());
                break;
            case Constants.SAFTWARE:
                replaceFragment(SaftwareFragment.newInstance());
                break;
            case Constants.CHOOSE_DEPARTMENT:
                String typeOne = getIntent().getStringExtra(Constants.DEPARTMENT_TYPE);
                replaceFragment(ChooseDepartmentFragmrnt.newInstance(typeOne));
                break;
            case Constants.LEAVE_DETAIL:
                String ask_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(LeaveDetailFragment.newInstance(ask_id, permissionsBean));
                break;
            case Constants.Sign_DETAIL_NEW:
                String sign_id_new = getIntent().getStringExtra(Constants.DETAIL_ID);
                String head_img = getIntent().getStringExtra(Constants.HEAD_IMG);
                replaceFragment(SignDetailNewFragment.newInstance(sign_id_new, head_img));
                break;
            case Constants.PROJECT_DETAIL:
                String problm_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ProjectDetailFragment.newInstance(problm_id, permissionsBean));
                break;
            case Constants.CHOOSE_CHECK_PERSON:
                String check_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ChooseCheckPersonFragment.newInstance(check_id));
                break;

            case Constants.COMPLETE_DETAIL:
                String comId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(OrderCompleteDetailFragment.newInstance(comId, permissionsBean));
                break;
            case Constants.IMG_TWO:
                List<PhotoListBean> photoBeanList = (List<PhotoListBean>) intent.getSerializableExtra(Constants.IMG_LIST);
                replaceFragment(com.akan.qf.mvp.fragment.statistics.OrderImgTwoFragment.newInstance(photoBeanList));
                break;

            default:
                LogUtils.d("Not found fragment:" + Integer.toHexString(fragmentKey));
                break;
        }
    }


    public void replaceFragment(Fragment fragmnet) {
        replaceFragment(R.id.fragmentContent, fragmnet);
    }

    public void replaceFragment(@IdRes int id, Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
