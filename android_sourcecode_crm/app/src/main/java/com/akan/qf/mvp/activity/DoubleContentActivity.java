package com.akan.qf.mvp.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.AdManagementBean;
import com.akan.qf.bean.CompanyBean;
import com.akan.qf.bean.ContractApplyBean;
import com.akan.qf.bean.DailyBean;
import com.akan.qf.bean.DebtApplyBean;
import com.akan.qf.bean.FinancialBean;
import com.akan.qf.bean.LableBean;
import com.akan.qf.bean.LeaveBean;
import com.akan.qf.bean.NetworkBean;
import com.akan.qf.bean.NewApplyBean;
import com.akan.qf.bean.PayApplyBean;
import com.akan.qf.bean.PeopleJionBean;
import com.akan.qf.bean.PeopleLeaveBean;
import com.akan.qf.bean.PeopleNewBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.PolicyBean;
import com.akan.qf.bean.ReimbursementInfoBean;
import com.akan.qf.bean.ReprotedBean;
import com.akan.qf.bean.RetnrnBean;
import com.akan.qf.bean.StoreApplyBean;
import com.akan.qf.bean.TemporaryBean;
import com.akan.qf.bean.VisitorBean;
import com.akan.qf.bean.WaterBean;
import com.akan.qf.mvp.base.PureActivity;
import com.akan.qf.mvp.fragment.adaily.DailyAddFragment;
import com.akan.qf.mvp.fragment.adaily.DailyListFragment;
import com.akan.qf.mvp.fragment.adaily.LeaveAddFragment;
import com.akan.qf.mvp.fragment.adaily.LeaveListFragment;
import com.akan.qf.mvp.fragment.adaily.MonthAddFragment;
import com.akan.qf.mvp.fragment.adaily.MonthListFragment;
import com.akan.qf.mvp.fragment.adaily.SignFragment;
import com.akan.qf.mvp.fragment.adaily.SignRecordFragment;
import com.akan.qf.mvp.fragment.adaily.WeeklyAddFragment;
import com.akan.qf.mvp.fragment.adaily.WeeklyListFragment;
import com.akan.qf.mvp.fragment.bapproval.ArrearsAddFragment;
import com.akan.qf.mvp.fragment.bapproval.ArrearsListFragment;
import com.akan.qf.mvp.fragment.bapproval.CostStatisticsAddFragment;
import com.akan.qf.mvp.fragment.bapproval.CostStatisticsListFragment;
import com.akan.qf.mvp.fragment.bapproval.PayRequestAddFragment;
import com.akan.qf.mvp.fragment.bapproval.PayRequestListFragment;
import com.akan.qf.mvp.fragment.bapproval.PolicyAddFragment;
import com.akan.qf.mvp.fragment.bapproval.PolicyListFragment;
import com.akan.qf.mvp.fragment.bapproval.ProblemAddFragment;
import com.akan.qf.mvp.fragment.bapproval.ProblemListFragment;
import com.akan.qf.mvp.fragment.bapproval.ReimburseAddFragment;
import com.akan.qf.mvp.fragment.bapproval.ReimburseListFragment;
import com.akan.qf.mvp.fragment.bapproval.ReportAddFragment;
import com.akan.qf.mvp.fragment.bapproval.ReportListFragment;
import com.akan.qf.mvp.fragment.bapproval.ReturnAddFragment;
import com.akan.qf.mvp.fragment.bapproval.ReturnListFragment;
import com.akan.qf.mvp.fragment.bapproval.TemporaryAddFragment;
import com.akan.qf.mvp.fragment.bapproval.TemporaryListFragment;
import com.akan.qf.mvp.fragment.bapproval.VisitAddFragment;
import com.akan.qf.mvp.fragment.bapproval.VisitListFragment;
import com.akan.qf.mvp.fragment.cpeople.PeopleJoinAddFragment;
import com.akan.qf.mvp.fragment.cpeople.PeopleJoinListFragment;
import com.akan.qf.mvp.fragment.cpeople.PeopleLeaveAddFragment;
import com.akan.qf.mvp.fragment.cpeople.PeopleLeaveListFragment;
import com.akan.qf.mvp.fragment.cpeople.PeopleNewAddFragment;
import com.akan.qf.mvp.fragment.cpeople.PeopleNewListFragment;
import com.akan.qf.mvp.fragment.fsales.CustomerContractAddFragment;
import com.akan.qf.mvp.fragment.fsales.CustomerContractListFragment;
import com.akan.qf.mvp.fragment.gad.ADImageAddFragment;
import com.akan.qf.mvp.fragment.gad.ADImageListFragment;
import com.akan.qf.mvp.fragment.gad.ADPromotionAddFragment;
import com.akan.qf.mvp.fragment.gad.ADPromotionListFragment;
import com.akan.qf.mvp.fragment.gad.ADShopAddFragment;
import com.akan.qf.mvp.fragment.gad.ADShopListFragment;
import com.akan.qf.mvp.fragment.hchannel.CompanyAddFragment;
import com.akan.qf.mvp.fragment.hchannel.CompanyListFragment;
import com.akan.qf.mvp.fragment.hchannel.DistributionAddFragment;
import com.akan.qf.mvp.fragment.hchannel.DistributionListFragment;
import com.akan.qf.mvp.fragment.hchannel.EngineerAddFragment;
import com.akan.qf.mvp.fragment.hchannel.EngineerListFragment;
import com.akan.qf.mvp.fragment.hchannel.ProjectAddFragment;
import com.akan.qf.mvp.fragment.hchannel.ProjectListFragment;
import com.akan.qf.mvp.fragment.hchannel.WaterAddFragment;
import com.akan.qf.mvp.fragment.hchannel.WaterListFragment;
import com.akan.qf.mvp.fragment.polygon.CostPaymentAddFragment;
import com.akan.qf.mvp.fragment.polygon.CostPaymentListFragment;
import com.akan.qf.mvp.fragment.polygon.CustomerDiscountAddFragment;
import com.akan.qf.mvp.fragment.polygon.CustomerDiscountListFragment;
import com.akan.qf.mvp.fragment.polygon.DisableMaterialAddFragment;
import com.akan.qf.mvp.fragment.polygon.DisableMaterialListFragment;
import com.akan.qf.mvp.fragment.polygon.EnterInvoiceAddFragment;
import com.akan.qf.mvp.fragment.polygon.EnterInvoiceListFragment;
import com.akan.qf.mvp.fragment.polygon.SalesInvoiceAddFragment;
import com.akan.qf.mvp.fragment.polygon.SalesInvoiceListFragment;
import com.akan.qf.mvp.fragment.polygon.SalesInvoiceRefundAddFragment;
import com.akan.qf.mvp.fragment.polygon.SalesInvoiceRefundListFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoubleContentActivity extends PureActivity {

    @BindView(R.id.rg)
    RadioGroup rg;

    private Fragment mAddFragment;
    private Fragment mListFragment;

    private PermissionsBean mPBean;
    private List<LableBean> signList;

    @Override
    public int getRootViewId() {
        return R.layout.activity_public;
    }

    @Override
    public void initUI() {
        ButterKnife.bind(this);
        mPBean = (PermissionsBean) getIntent().getSerializableExtra("permissions");
        signList = (List<LableBean>) getIntent().getSerializableExtra("signList");
        String fragmentKey = getIntent().getStringExtra(Constants.KEY_FRAGMENT);

        boolean isDouble = isHave("0", mPBean.getApp_operation().split(","));

        switchFragments(isDouble, fragmentKey);
        attachFragments();
    }

    private void switchFragments(boolean isDouble, String fragmentKey) {
        rg.setVisibility(isDouble ? View.VISIBLE : View.GONE);
        switch (fragmentKey) {
            case Constants.SIGN_ACTIVITY:
                ((RadioButton) rg.getChildAt(0)).setText("签到");
                ((RadioButton) rg.getChildAt(0)).setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_tabbar_home_selector_sign, 0, 0);
                mAddFragment = isDouble ? SignFragment.newInstance(mPBean) : null;
                mListFragment = SignRecordFragment.newInstance(mPBean, signList);
                break;
            case Constants.LEAVE_ACTIVITY:
                mAddFragment = isDouble ? LeaveAddFragment.newInstance(new LeaveBean(), "0", mPBean) : null;
                mListFragment = LeaveListFragment.newInstance(mPBean, signList);
                break;
            case Constants.DAILY_ACTIVITY:
                ((RadioButton) rg.getChildAt(0)).setText("日报");
                mAddFragment = isDouble ? DailyAddFragment.newInstance(new DailyBean(), "0", mPBean) : null;
                mListFragment = DailyListFragment.newInstance(mPBean, signList);
                break;
            case Constants.WEEK_ACTIVITY:
                ((RadioButton) rg.getChildAt(0)).setText("周报");
                mAddFragment = isDouble ? WeeklyAddFragment.newInstance(new DailyBean(), "0", mPBean) : null;
                mListFragment = WeeklyListFragment.newInstance(mPBean, signList);
                break;
            case Constants.MONTH_ACTIVITY:
                ((RadioButton) rg.getChildAt(0)).setText("月报");
                mAddFragment = isDouble ? MonthAddFragment.newInstance(new DailyBean(), "0", mPBean) : null;
                mListFragment = MonthListFragment.newInstance(mPBean, signList);
                break;

            case Constants.POLICY_APPLY_ACTIVITY:
                mAddFragment = isDouble ? PolicyAddFragment.newInstance(new PolicyBean(), "0", mPBean) : null;
                mListFragment = PolicyListFragment.newInstance(mPBean, signList);
                break;
            case Constants.TEMPORARY_SUPPORT_ACTIVITY:
                mAddFragment = isDouble ? TemporaryAddFragment.newInstance(new TemporaryBean(), "0", mPBean) : null;
                mListFragment = TemporaryListFragment.newInstance(mPBean, signList);
                break;
            case Constants.PAY_APPLY_ACTIVITY:
                mAddFragment = isDouble ? PayRequestAddFragment.newInstance(new PayApplyBean(), "0", mPBean) : null;
                mListFragment = PayRequestListFragment.newInstance(mPBean, signList);
                break;
            case Constants.EXPENSE_REIMBURSEMENT_ACTIVITY:
                mAddFragment = isDouble ? ReimburseAddFragment.newInstance(new ReimbursementInfoBean(), "0", mPBean) : null;
                mListFragment = ReimburseListFragment.newInstance(mPBean, signList);
                break;
            case Constants.DEBT_APPLY_ACTIVITY:
                mAddFragment = isDouble ? ArrearsAddFragment.newInstance(new DebtApplyBean(), "0", mPBean) : null;
                mListFragment = ArrearsListFragment.newInstance(mPBean, signList);
                break;
            case Constants.COST_STATISTICS_ACTIVITY:
                mAddFragment = isDouble ? CostStatisticsAddFragment.newInstance(new FinancialBean(), "0", mPBean) : null;
                mListFragment = CostStatisticsListFragment.newInstance(mPBean, signList);
                break;

            case Constants.RECRUITMENT_ACTIVITY:
                mAddFragment = isDouble ? PeopleJoinAddFragment.newInstance(new PeopleJionBean(), "0", mPBean) : null;
                mListFragment = PeopleJoinListFragment.newInstance(mPBean, signList);
                break;
            case Constants.NEWCOMER_ACTIVITY:
                mAddFragment = isDouble ? PeopleNewAddFragment.newInstance(new PeopleNewBean(), "0", mPBean) : null;
                mListFragment = PeopleNewListFragment.newInstance(mPBean, signList);
                break;
            case Constants.RESIGNATION_LETTER_ACTIVITY:
                mAddFragment = isDouble ? PeopleLeaveAddFragment.newInstance(new PeopleLeaveBean(), "0", mPBean) : null;
                mListFragment = PeopleLeaveListFragment.newInstance(mPBean, signList);
                break;
            case Constants.PROJECT_ACTIVITY:
                mAddFragment = isDouble ? ReportAddFragment.newInstance(new ReprotedBean(), "0", mPBean) : null;
                mListFragment = ReportListFragment.newInstance(mPBean, signList);
                break;
            case Constants.NEW_APPLY_ACTIVITY:
                mAddFragment = isDouble ? ProblemAddFragment.newInstance(new NewApplyBean(), "0", mPBean) : null;
                mListFragment = ProblemListFragment.newInstance(mPBean, signList);
                break;
            case Constants.GOOD_APPLY_ACTIVITY:
                mAddFragment = isDouble ? ReturnAddFragment.newInstance(new RetnrnBean(), "0", mPBean) : null;
                mListFragment = ReturnListFragment.newInstance(mPBean, signList);
                break;
            case Constants.VISITOR_ACTIVITY:
                mAddFragment = isDouble ? VisitAddFragment.newInstance(new VisitorBean(), "0", mPBean) : null;
                mListFragment = VisitListFragment.newInstance(mPBean, signList);
                break;
            case Constants.CONTRACT_APPLY:
                mAddFragment = isDouble ? CustomerContractAddFragment.newInstance(new ContractApplyBean(), "0", mPBean) : null;
                mListFragment = CustomerContractListFragment.newInstance(mPBean, signList);
                break;
            case Constants.CHANNEL_CUSTOMER:
                mAddFragment = isDouble ? CompanyAddFragment.newInstance(new CompanyBean(), "0", mPBean) : null;
                mListFragment = CompanyListFragment.newInstance(mPBean, signList);
                break;
            case Constants.CHANNEL_WATER:
                mAddFragment = isDouble ? WaterAddFragment.newInstance(new WaterBean(), "0", mPBean) : null;
                mListFragment = WaterListFragment.newInstance(mPBean, signList);
                break;
            case Constants.CHANNEL_DISTRIBUTION:
                mAddFragment = isDouble ? DistributionAddFragment.newInstance(new NetworkBean(), "0", mPBean) : null;
                mListFragment = DistributionListFragment.newInstance(mPBean, signList);
                break;
            case Constants.NUAN_TONG:
                mAddFragment = isDouble ? ProjectAddFragment.newInstance(new CompanyBean(), "0", mPBean) : null;
                mListFragment = ProjectListFragment.newInstance(mPBean, signList);
                break;
            case Constants.PROJECT:
                mAddFragment = isDouble ? EngineerAddFragment.newInstance(new StoreApplyBean(), "0", mPBean) : null;
                mListFragment = EngineerListFragment.newInstance(mPBean, signList);
                break;
            case Constants.SHOP_ADVERTISEMENT:
                mAddFragment = isDouble ? ADShopAddFragment.newInstance(new AdManagementBean(), "0", mPBean) : null;
                mListFragment = ADShopListFragment.newInstance(mPBean, signList);
                break;
            case Constants.IMAGE_ADVERTISEMENT:
                mAddFragment = isDouble ? ADImageAddFragment.newInstance(new AdManagementBean(), "0", mPBean) : null;
                mListFragment = ADImageListFragment.newInstance(mPBean, signList);
                break;
            case Constants.PROMOTION_ADVERTISEMENT:
                mAddFragment = isDouble ? ADPromotionAddFragment.newInstance(new AdManagementBean(), "0", mPBean) : null;
                mListFragment = ADPromotionListFragment.newInstance(mPBean, signList);
                break;

            case Constants.COST_PAYMENT://费用付款
                mAddFragment = isDouble ? CostPaymentAddFragment.newInstance(new AdManagementBean(), "0", mPBean) : null;
                mListFragment = CostPaymentListFragment.newInstance(mPBean, signList);
                break;
            case Constants.CUSTOMER_DISCOUNT://客户折扣
                mAddFragment = isDouble ? CustomerDiscountAddFragment.newInstance(new AdManagementBean(), "0", mPBean) : null;
                mListFragment = CustomerDiscountListFragment.newInstance(mPBean, signList);
                break;
            case Constants.DISABLE_MATERIAL://禁用物料
                mAddFragment = isDouble ? DisableMaterialAddFragment.newInstance(new AdManagementBean(), "0", mPBean) : null;
                mListFragment = DisableMaterialListFragment.newInstance(mPBean, signList);
                break;
            case Constants.SALES_INVIOCE://销项发票
                mAddFragment = isDouble ? SalesInvoiceAddFragment.newInstance(new AdManagementBean(), "0", mPBean) : null;
                mListFragment = SalesInvoiceListFragment.newInstance(mPBean, signList);
                break;
            case Constants.SALES_INVIOCE_REFUND://销项发票退票
                mAddFragment = isDouble ? SalesInvoiceRefundAddFragment.newInstance(new AdManagementBean(), "0", mPBean) : null;
                mListFragment = SalesInvoiceRefundListFragment.newInstance(mPBean, signList);
                break;
            case Constants.ENTER_INVIOCE://进项发票
                mAddFragment = isDouble ? EnterInvoiceAddFragment.newInstance(new AdManagementBean(), "0", mPBean) : null;
                mListFragment = EnterInvoiceListFragment.newInstance(mPBean, signList);
                break;

            default:
                finish();
                break;
        }

    }

    private void attachFragments() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentContent, mListFragment);
        if (mAddFragment != null) {
            fragmentTransaction.add(R.id.fragmentContent, mAddFragment)
                    .hide(mListFragment).show(mAddFragment);
        } else {
            fragmentTransaction.show(mListFragment);
        }
        fragmentTransaction.commit();
    }

    private boolean isHave(String index, String[] split) {
        for (String s : split) {
            if (index.equals(s)) {
                return true;
            }
        }
        return false;
    }


    public void showSignFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(mListFragment).show(mAddFragment).commit();
    }

    public void showSignRecordFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(mAddFragment).show(mListFragment).commit();
    }

    @OnClick({R.id.sign, R.id.signRecord})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sign:
                showSignFragment();
                break;
            case R.id.signRecord:
                showSignRecordFragment();
                break;
        }
    }
}
