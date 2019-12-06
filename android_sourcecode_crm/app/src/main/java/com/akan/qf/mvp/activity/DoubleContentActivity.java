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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoubleContentActivity extends PureActivity {

    @BindView(R.id.rg)
    RadioGroup rg;

    private Fragment signFragment;
    private Fragment signRecordFragment;

    private PermissionsBean permissionsBean;
    private List<LableBean> signList;

    @Override
    public int getRootViewId() {
        return R.layout.activity_public;
    }

    @Override
    public void initUI() {
        ButterKnife.bind(this);
        permissionsBean = (PermissionsBean) getIntent().getSerializableExtra("permissions");
        signList = (List<LableBean>) getIntent().getSerializableExtra("signList");
        String fragmentKey = getIntent().getStringExtra(Constants.KEY_FRAGMENT);

        boolean isDouble = isHave("0", permissionsBean.getApp_operation().split(","));

        switchFragments(isDouble, fragmentKey);
        attachFragments();
    }

    private void switchFragments(boolean isDouble, String fragmentKey) {
        rg.setVisibility(isDouble ? View.VISIBLE : View.GONE);
        switch (fragmentKey) {
            case Constants.SIGN_ACTIVITY:
                // TODO: 2019/10/23 会改变位置
                ((RadioButton)rg.getChildAt(0)).setText("签到");
                ((RadioButton)rg.getChildAt(0)).setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_tabbar_home_selector_sign, 0, 0);
                signFragment = isDouble ? SignFragment.newInstance(permissionsBean) : null;
                signRecordFragment = SignRecordFragment.newInstance(permissionsBean,signList);
                break;
            case Constants.LEAVE_ACTIVITY:
                signFragment = isDouble ? LeaveAddFragment.newInstance(new LeaveBean(), "0", permissionsBean) : null;
                signRecordFragment = LeaveListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.DAILY_ACTIVITY:
                ((RadioButton)rg.getChildAt(0)).setText("日报");
                signFragment = isDouble ? DailyAddFragment.newInstance(new DailyBean(), "0", permissionsBean) : null;
                signRecordFragment = DailyListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.WEEK_ACTIVITY:
                ((RadioButton)rg.getChildAt(0)).setText("周报");
                signFragment = isDouble ? WeeklyAddFragment.newInstance(new DailyBean(), "0", permissionsBean) : null;
                signRecordFragment = WeeklyListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.MONTH_ACTIVITY:
                ((RadioButton)rg.getChildAt(0)).setText("月报");
                signFragment = isDouble ? MonthAddFragment.newInstance(new DailyBean(), "0", permissionsBean) : null;
                signRecordFragment = MonthListFragment.newInstance(permissionsBean, signList);
                break;

            case Constants.POLICY_APPLY_ACTIVITY:
                signFragment = isDouble ? PolicyAddFragment.newInstance(new PolicyBean(), "0", permissionsBean) : null;
                signRecordFragment = PolicyListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.TEMPORARY_SUPPORT_ACTIVITY:
                signFragment = isDouble ? TemporaryAddFragment.newInstance(new TemporaryBean(), "0", permissionsBean) : null;
                signRecordFragment = TemporaryListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.PAY_APPLY_ACTIVITY:
                signFragment = isDouble ? PayRequestAddFragment.newInstance(new PayApplyBean(), "0", permissionsBean) : null;
                signRecordFragment = PayRequestListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.EXPENSE_REIMBURSEMENT_ACTIVITY:
                signFragment = isDouble ? ReimburseAddFragment.newInstance(new ReimbursementInfoBean(), "0", permissionsBean) : null;
                signRecordFragment = ReimburseListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.DEBT_APPLY_ACTIVITY:
                signFragment = isDouble ? ArrearsAddFragment.newInstance(new DebtApplyBean(),"0",permissionsBean) : null;
                signRecordFragment = ArrearsListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.COST_STATISTICS_ACTIVITY:
                signFragment = isDouble ? CostStatisticsAddFragment.newInstance(new FinancialBean(),"0",permissionsBean) : null;
                signRecordFragment = CostStatisticsListFragment.newInstance(permissionsBean, signList);
                break;

            case Constants.RECRUITMENT_ACTIVITY:
                signFragment = isDouble ? PeopleJoinAddFragment.newInstance(new PeopleJionBean(),"0",permissionsBean) : null;
                signRecordFragment = PeopleJoinListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.NEWCOMER_ACTIVITY:
                signFragment = isDouble ? PeopleNewAddFragment.newInstance(new PeopleNewBean(),"0",permissionsBean) : null;
                signRecordFragment = PeopleNewListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.RESIGNATION_LETTER_ACTIVITY:
                signFragment = isDouble ? PeopleLeaveAddFragment.newInstance(new PeopleLeaveBean(),"0",permissionsBean) : null;
                signRecordFragment = PeopleLeaveListFragment.newInstance(permissionsBean, signList);
                break;

            case Constants.PROJECT_ACTIVITY:
                signFragment = isDouble ? ReportAddFragment.newInstance(new ReprotedBean(),"0",permissionsBean) : null;
                signRecordFragment = ReportListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.NEW_APPLY_ACTIVITY:
                signFragment = isDouble ? ProblemAddFragment.newInstance(new NewApplyBean(),"0",permissionsBean) : null;
                signRecordFragment = ProblemListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.GOOD_APPLY_ACTIVITY:
                signFragment = isDouble ? ReturnAddFragment.newInstance(new RetnrnBean(),"0",permissionsBean) : null;
                signRecordFragment = ReturnListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.VISITOR_ACTIVITY:
                signFragment = isDouble ? VisitAddFragment.newInstance(new VisitorBean(),"0",permissionsBean) : null;
                signRecordFragment = VisitListFragment.newInstance(permissionsBean, signList);
                break;

            case Constants.CONTRACT_APPLY:
                signFragment = isDouble ? CustomerContractAddFragment.newInstance(new ContractApplyBean(),"0",permissionsBean) : null;
                signRecordFragment = CustomerContractListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.CHANNEL_CUSTOMER:
                signFragment = isDouble ? CompanyAddFragment.newInstance(new CompanyBean(),"0",permissionsBean) : null;
                signRecordFragment = CompanyListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.CHANNEL_WATER:
                signFragment = isDouble ? WaterAddFragment.newInstance(new WaterBean(),"0",permissionsBean) : null;
                signRecordFragment = WaterListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.CHANNEL_DISTRIBUTION:
                signFragment = isDouble ? DistributionAddFragment.newInstance(new NetworkBean(),"0",permissionsBean) : null;
                signRecordFragment = DistributionListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.NUAN_TONG:
                signFragment = isDouble ? ProjectAddFragment.newInstance(new CompanyBean(),"0",permissionsBean) : null;
                signRecordFragment = ProjectListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.PROJECT:
                signFragment = isDouble ? EngineerAddFragment.newInstance(new StoreApplyBean(),"0",permissionsBean) : null;
                signRecordFragment = EngineerListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.SHOP_ADVERTISEMENT:
                signFragment = isDouble ? ADShopAddFragment.newInstance(new AdManagementBean(),"0",permissionsBean) : null;
                signRecordFragment = ADShopListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.IMAGE_ADVERTISEMENT:
                signFragment = isDouble ? ADImageAddFragment.newInstance(new AdManagementBean(), "0",permissionsBean) : null;
                signRecordFragment = ADImageListFragment.newInstance(permissionsBean, signList);
                break;
            case Constants.PROMOTION_ADVERTISEMENT:
                signFragment = isDouble ? ADPromotionAddFragment.newInstance(new AdManagementBean(),"0",permissionsBean) : null;
                signRecordFragment = ADPromotionListFragment.newInstance(permissionsBean, signList);
                break;

            default:
                finish();
                break;
        }

    }

    private void attachFragments() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentContent, signRecordFragment);
        if (signFragment != null) {
            fragmentTransaction.add(R.id.fragmentContent, signFragment)
                    .hide(signRecordFragment).show(signFragment);
        } else {
            fragmentTransaction.show(signRecordFragment);
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
        fragmentTransaction.hide(signRecordFragment).show(signFragment).commit();
    }

    public void showSignRecordFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(signFragment).show(signRecordFragment).commit();
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
