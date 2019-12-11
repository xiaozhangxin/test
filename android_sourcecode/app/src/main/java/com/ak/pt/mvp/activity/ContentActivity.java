package com.ak.pt.mvp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import com.ak.pt.Constants;
import com.ak.pt.R;
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
import com.ak.pt.mvp.fragment.OrderSearchFragment;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.bean.QuitJobBean;
import com.ak.pt.bean.ShopCloseBean;
import com.ak.pt.bean.StaffApplyBean;
import com.ak.pt.bean.WarrantyBean;
import com.ak.pt.mvp.fragment.AccountFragment;
import com.ak.pt.mvp.fragment.ChooseCheckFragment;
import com.ak.pt.mvp.fragment.ChooseCheckPersonFragment;
import com.ak.pt.mvp.fragment.ChooseWorkerFragment;
import com.ak.pt.mvp.fragment.DepartmentPermissionFragment;
import com.ak.pt.mvp.fragment.ForgetPwdFragment;
import com.ak.pt.mvp.fragment.OrderAddFragment;
import com.ak.pt.mvp.fragment.OrderAddWorkerFragment;
import com.ak.pt.mvp.fragment.statistics.OrderImgTwoFragment;
import com.ak.pt.mvp.fragment.OrderManagerDetailFragment;
import com.ak.pt.mvp.fragment.OrderManagerFragment;
import com.ak.pt.mvp.fragment.OrderWorkerDetailFragment;
import com.ak.pt.mvp.fragment.OrderWorkerFragment;
import com.ak.pt.mvp.fragment.UpImgFragment;
import com.ak.pt.mvp.fragment.WebFragment;
import com.ak.pt.mvp.fragment.adaily.DailyAddFragment;
import com.ak.pt.mvp.fragment.adaily.DailyDetailFragment;
import com.ak.pt.mvp.fragment.adaily.MonthAddFragment;
import com.ak.pt.mvp.fragment.adaily.MonthDetailFragment;
import com.ak.pt.mvp.fragment.adaily.SignDetailNewFragment;
import com.ak.pt.mvp.fragment.adaily.WeeklyAddFragment;
import com.ak.pt.mvp.fragment.adaily.WeeklyDetailFragment;
import com.ak.pt.mvp.fragment.area.InterviewAddFragment;
import com.ak.pt.mvp.fragment.area.InterviewDetailFragmrnt;
import com.ak.pt.mvp.fragment.area.MonthlySummaryAddFragment;
import com.ak.pt.mvp.fragment.area.MonthlySummaryDetailFragment;
import com.ak.pt.mvp.fragment.area.PeopleAddFragment;
import com.ak.pt.mvp.fragment.area.PeopleDetailFragment;
import com.ak.pt.mvp.fragment.area.RegionAddFragment;
import com.ak.pt.mvp.fragment.area.RegionDetailFragmrnt;
import com.ak.pt.mvp.fragment.area.ReworkAddFragment;
import com.ak.pt.mvp.fragment.area.ReworkDetailFragment;
import com.ak.pt.mvp.fragment.ble.BleListFragment;
import com.ak.pt.mvp.fragment.ble.PTSettingChildFragment;
import com.ak.pt.mvp.fragment.ble.PressureResultFragmrnt;
import com.ak.pt.mvp.fragment.ble.PtSettingFragment;
import com.ak.pt.mvp.fragment.statistics.AreaTestPressureFragment;
import com.ak.pt.mvp.fragment.statistics.BigTestPressureFragment;
import com.ak.pt.mvp.fragment.home.BookFragment;
import com.ak.pt.mvp.fragment.home.MessageDetailFragment;
import com.ak.pt.mvp.fragment.home.MessageFragment;
import com.ak.pt.mvp.fragment.home.NoticeDetailFragment;
import com.ak.pt.mvp.fragment.home.NoticeFileFragment;
import com.ak.pt.mvp.fragment.home.NoticeFragment;
import com.ak.pt.mvp.fragment.home.NoticeSearchFragment;
import com.ak.pt.mvp.fragment.statistics.OrderCompleteDetailFragment;
import com.ak.pt.mvp.fragment.statistics.OrderCompleteFragment;
import com.ak.pt.mvp.fragment.home.SecurityCheckFragment;
import com.ak.pt.mvp.fragment.home.SecurityListFragment;
import com.ak.pt.mvp.fragment.mall.AddressAddFragment;
import com.ak.pt.mvp.fragment.mall.AddressFragment;
import com.ak.pt.mvp.fragment.mall.AllOrderFragment;
import com.ak.pt.mvp.fragment.mall.ConfirmOrderFragment;
import com.ak.pt.mvp.fragment.mall.GoodsDetailFragment;
import com.ak.pt.mvp.fragment.mall.MallFragment;
import com.ak.pt.mvp.fragment.mall.OrderDetailFragmrnt;
import com.ak.pt.mvp.fragment.mall.OrderOverFragment;
import com.ak.pt.mvp.fragment.people.ChoosePeopleLeaveFragment;
import com.ak.pt.mvp.fragment.people.CloseAddFragment;
import com.ak.pt.mvp.fragment.people.CloseDetailFragment;
import com.ak.pt.mvp.fragment.people.EntryAddFragment;
import com.ak.pt.mvp.fragment.people.EntryDetailFragment;
import com.ak.pt.mvp.fragment.people.LeaveAddFragment;
import com.ak.pt.mvp.fragment.people.LeaveDetailFragment;
import com.ak.pt.mvp.fragment.qifei.TestPressureDetailFragment;
import com.ak.pt.mvp.fragment.qifei.TestPressureFragment;
import com.ak.pt.mvp.fragment.setting.ChangePwdFragment;
import com.ak.pt.mvp.fragment.setting.DocumentNewFragment;
import com.ak.pt.mvp.fragment.setting.DocumentSerachFragment;
import com.ak.pt.mvp.fragment.setting.IntegralFragment;
import com.ak.pt.mvp.fragment.setting.OfficeDocumentFragment;
import com.ak.pt.mvp.fragment.setting.OfficeSearchFragment;
import com.ak.pt.mvp.fragment.setting.PersonalInfoFragment;
import com.ak.pt.mvp.fragment.setting.SaftwareFragment;
import com.ak.pt.mvp.fragment.table.TableAllTypeFragment;
import com.ak.pt.mvp.fragment.table.TableEightFragment;
import com.ak.pt.mvp.fragment.table.TableElevenFragment;
import com.ak.pt.mvp.fragment.table.TableFifteenFragment;
import com.ak.pt.mvp.fragment.table.TableFiveFragment;
import com.ak.pt.mvp.fragment.table.TableForteenFragment;
import com.ak.pt.mvp.fragment.table.TableFourFragment;
import com.ak.pt.mvp.fragment.table.TableFragmrnt;
import com.ak.pt.mvp.fragment.table.TableNineFragment;
import com.ak.pt.mvp.fragment.table.TableSevenFragment;
import com.ak.pt.mvp.fragment.table.TableSixFragment;
import com.ak.pt.mvp.fragment.table.TableSixteenFragment;
import com.ak.pt.mvp.fragment.table.TableTenFragment;
import com.ak.pt.mvp.fragment.table.TableThirteenFragment;
import com.ak.pt.mvp.fragment.table.TableThreeFragment;
import com.ak.pt.mvp.fragment.table.TableTwelveFragment;
import com.ak.pt.mvp.fragment.table.TableTwoFragment;
import com.ak.pt.mvp.fragment.water.FeedBackDetailFragment;
import com.ak.pt.mvp.fragment.water.FeedbackAddFragment;
import com.ak.pt.mvp.fragment.water.FilterReplaceAddFragment;
import com.ak.pt.mvp.fragment.water.FilterReplaceDetailFragment;
import com.ak.pt.mvp.fragment.water.FilterReplaceListFragment;
import com.ak.pt.mvp.fragment.water.FixRecordAddFragment;
import com.ak.pt.mvp.fragment.water.FixRecordDetailFragment;
import com.ak.pt.mvp.fragment.water.WarrantyAddFragment;
import com.ak.pt.mvp.fragment.water.WarrantyDetailFragment;
import com.king.base.util.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/20
 */

public class ContentActivity extends AppCompatActivity {
    private static final int INVALID_VAL = -1;
    private static final int COLOR_DEFAULT = Color.parseColor("#7f000000");

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        swichFragment(getIntent());
        EventBus.getDefault().register(this);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void swichFragment(Intent intent) {
        int fragmentKey = intent.getIntExtra(Constants.KEY_FRAGMENT, 0);
        AppPermissionsBean permissionsBean = (AppPermissionsBean) intent.getSerializableExtra(Constants.PERMISSION_BEAN);
        switch (fragmentKey) {
//            case Constants.LOGIN_FRAGMENT:
//                replaceFragment(LoginActivity.newInstance());
//                break;
            case Constants.NOTICE_SEARCH:
                replaceFragment(NoticeSearchFragment.newInstance());
                break;
            case Constants.BOOK:
                replaceFragment(BookFragment.newInstance(permissionsBean));
                break;
            case Constants.ChHOOSE_PEOPLE_LEAVE_LIST:
                String leaveType = getIntent().getStringExtra(Constants.TYPE);
                String uuid = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ChoosePeopleLeaveFragment.newInstance(leaveType, uuid, permissionsBean));
                break;
            case Constants.ACCOUNT:
                replaceFragment(AccountFragment.newInstance());
                break;
            case Constants.FILTER_REPLACE_LIST:
                replaceFragment(FilterReplaceListFragment.newInstance(permissionsBean));
                break;
            case Constants.WEB_FRAGMENT:
                String code = getIntent().getStringExtra(Constants.DETAIL_ID);
                String tittle = getIntent().getStringExtra(Constants.KEY_TITLE);
                String url = getIntent().getStringExtra(Constants.KEY_URL);
                replaceFragment(WebFragment.newInstance(url, tittle, code));
                break;
            case Constants.ORDER_ACCEPT:
                replaceFragment(OrderWorkerFragment.newInstance(permissionsBean));
                break;
             case Constants.ORDER_SEARCH:
                String searchType = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(OrderSearchFragment.newInstance(searchType,permissionsBean));
                break;
            case Constants.INTEGRAL:
                replaceFragment(IntegralFragment.newInstance());
                break;
            case Constants.ORDER_MANAGER:
                replaceFragment(OrderManagerFragment.newInstance(permissionsBean));
                break;
            case Constants.SECURITY_LIST:
                replaceFragment(SecurityListFragment.newInstance(permissionsBean));
                break;
//            case Constants.CHOOSE_DEPARTMENT:
//                String type = getIntent().getStringExtra(Constants.DEPARTMENT_TYPE);
//                replaceFragment(ChooseDepartmentFragmrnt.newInstance(type));
//                break;
            case Constants.ORDER_DETAIL:
                String order_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(OrderDetailFragmrnt.newInstance(order_id));
                break;
            case Constants.CHOOSE_DEPARTMENT_PERMISSION:
                String permission_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                String perType = getIntent().getStringExtra(Constants.TYPE);
                replaceFragment(DepartmentPermissionFragment.newInstance(permission_id, perType));
                break;
            case Constants.REGION_DETAIL:
                String regr_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(RegionDetailFragmrnt.newInstance(regr_id, permissionsBean));
                break;
            case Constants.LEAVE_DETAIL:
                String quit_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(LeaveDetailFragment.newInstance(quit_id, permissionsBean));
                break;
            case Constants.TABLE:
                replaceFragment(TableFragmrnt.newInstance());
                break;
            case Constants.INTERVIEW_DETAIL:
                String inter_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(InterviewDetailFragmrnt.newInstance(inter_id, permissionsBean));
                break;
            case Constants.OFFICE_DOCUMENT:
                replaceFragment(OfficeDocumentFragment.newInstance());
                break;
            case Constants.DOCUMENT:
                replaceFragment(DocumentNewFragment.newInstance(permissionsBean));
                break;
            case Constants.CHOOSE_CHECK_PERSON:
                String ordevjsn = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ChooseCheckPersonFragment.newInstance(ordevjsn));
                break;
            case Constants.CHOOSE_CHECK:
                String vjsn = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ChooseCheckFragment.newInstance(vjsn));
                break;
            case Constants.ALL_TABLE:
                replaceFragment(TableAllTypeFragment.newInstance());
                break;
            case Constants.TABLE_TWO:
                replaceFragment(TableTwoFragment.newInstance());
                break;
            case Constants.TABLE_THREE:
                replaceFragment(TableThreeFragment.newInstance());
                break;
            case Constants.TABLE_FOUR:
                replaceFragment(TableFourFragment.newInstance());
                break;
            case Constants.TABLE_FIVE:
                replaceFragment(TableFiveFragment.newInstance());
                break;
            case Constants.TABLE_SIX:
                replaceFragment(TableSixFragment.newInstance());
                break;
            case Constants.TABLE_SEVEN:
                replaceFragment(TableSevenFragment.newInstance());
                break;
            case Constants.TABLE_EIGHT:
                replaceFragment(TableEightFragment.newInstance());
                break;
            case Constants.TABLE_NINE:
                replaceFragment(TableNineFragment.newInstance());
                break;
            case Constants.TABLE_TEN:
                replaceFragment(TableTenFragment.newInstance());
                break;
            case Constants.TABLE_ELEVEN:
                replaceFragment(TableElevenFragment.newInstance());
                break;
            case Constants.TABLE_TWELVE:
                replaceFragment(TableTwelveFragment.newInstance());
                break;
            case Constants.TABLE_THIRTEEN:
                replaceFragment(TableThirteenFragment.newInstance());
                break;
            case Constants.TABLE_FORTEEN:
                replaceFragment(TableForteenFragment.newInstance());
                break;
            case Constants.TABLE_FIFTEEN:
                replaceFragment(TableFifteenFragment.newInstance());
                break;
            case Constants.TABLE_SIXTEEN:
                replaceFragment(TableSixteenFragment.newInstance());
                break;

            case Constants.PEOPLE_DETAIL:
                String add_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(PeopleDetailFragment.newInstance(add_id, permissionsBean));
                break;case Constants.CLOSE_DETAIL:
                String close_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(CloseDetailFragment.newInstance(close_id, permissionsBean));
                break;
            case Constants.ENTRY_DETAIL:
                String entry_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(EntryDetailFragment.newInstance(entry_id, permissionsBean));
                break;
            case Constants.REWORK_DETAIL:
                String rework_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ReworkDetailFragment.newInstance(rework_id, permissionsBean));
                break;
            case Constants.ORDER_COMPLETE:
                String order_id_con = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(OrderOverFragment.newInstance(order_id_con));
                break;
            case Constants.MONTH_SUMMARY_DETAIL:
                String summaid_con = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(MonthlySummaryDetailFragment.newInstance(summaid_con, permissionsBean));
                break;
            case Constants.OFFICE_SEARCH:
                replaceFragment(OfficeSearchFragment.newInstance());
                break;
            case Constants.MALL_ADDRESS_ADD:
                AddressBean addressBean = (AddressBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String addressType = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(AddressAddFragment.newInstance(addressBean, addressType));
                break;
            case Constants.REGION__ADD:
                AreaStudyBean aregBean = (AreaStudyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String aregtype = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(RegionAddFragment.newInstance(aregBean, aregtype, permissionsBean));
                break;
            case Constants.INTERVIEW_ADD:
                InterviewBean intervBean = (InterviewBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String ainrtgtype = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(InterviewAddFragment.newInstance(intervBean, ainrtgtype, permissionsBean));
                break;case Constants.CLOSE_ADD:
                ShopCloseBean closeDetailBean = (ShopCloseBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String close_add_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(CloseAddFragment.newInstance(closeDetailBean, close_add_id, permissionsBean));
                break;
            case Constants.REWORK_ADD:
                PressureBackBean reworkBean = (PressureBackBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String rew_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ReworkAddFragment.newInstance(reworkBean, rew_id, permissionsBean));
                break;
            case Constants.SUMMARY_ADD:
                MonthTotalBean intasdervBean = (MonthTotalBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String ainrtgtypsae = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(MonthlySummaryAddFragment.newInstance(intasdervBean, ainrtgtypsae, permissionsBean));
                break;
            case Constants.PEOPLE_ADD:
                PeopleBean inpeoBean = (PeopleBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String aiadd_ie = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(PeopleAddFragment.newInstance(inpeoBean, aiadd_ie, permissionsBean));
                break;
            case Constants.ENTRY_ADD:
                StaffApplyBean entryBean = (StaffApplyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String entry_add_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(EntryAddFragment.newInstance(entryBean, entry_add_id, permissionsBean));
                break;
            case Constants.LEAVE_ADD:
                QuitJobBean quitJobBeanBean = (QuitJobBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String quit_add_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(LeaveAddFragment.newInstance(quitJobBeanBean, quit_add_id, permissionsBean));
                break;
            case Constants.ORDER_ADD_WORKER:
                replaceFragment(OrderAddWorkerFragment.newInstance(permissionsBean));
                break;
            case Constants.ALL_ORDER:
                replaceFragment(AllOrderFragment.newInstance());
                break;
            case Constants.MALL_ADDRESS:
                //收货地址
                replaceFragment(AddressFragment.newIntance());
                break;
            case Constants.DOCUMENT_SEARCH:
                replaceFragment(DocumentSerachFragment.newInstance(permissionsBean));
                break;
            case Constants.Sign_DETAIL_NEW:
                String sign_id_new = getIntent().getStringExtra(Constants.DETAIL_ID);
                String head_img = getIntent().getStringExtra(Constants.HEAD_IMG);
                replaceFragment(SignDetailNewFragment.newInstance(sign_id_new, head_img));
                break;
            case Constants.DAILY_DETAIL:
                String staff_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                AppPermissionsBean dailyPermissionBean = (AppPermissionsBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                replaceFragment(DailyDetailFragment.newInstance(staff_id, dailyPermissionBean));
                break;
            case Constants.FILTER_REPLACE_DETAIL:
                String filter_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(FilterReplaceDetailFragment.newInstance(filter_id, permissionsBean));
                break;
            case Constants.FIX_RECORD_DETAIL:
                String fix_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(FixRecordDetailFragment.newInstance(fix_id, permissionsBean));
                break;
            case Constants.CONFIRM_ORDER:
                GoodsSpecificationBeans spBean = (GoodsSpecificationBeans) intent.getSerializableExtra(Constants.DETAIL_BEAN);

                String num = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ConfirmOrderFragment.newInstance(spBean, num));
                break;
            case Constants.FIX_RECORD_ADD:
                FixRecordBean fix_bean = (FixRecordBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String rep_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(FixRecordAddFragment.newInstance(fix_bean, rep_id, permissionsBean));
                break;
            case Constants.GOODS_DETAIL:
                String goods_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(GoodsDetailFragment.newInstance(goods_id));
                break;
            case Constants.DAILY_ADD:
                DailyBean dailyBean = (DailyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String daiylId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(DailyAddFragment.newInstance(dailyBean, daiylId, permissionsBean));
                break;
            case Constants.FILTER_REPLACE_ADD_ADD:
                FilterReplaceBean filter_bean = (FilterReplaceBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String filter_id_two = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(FilterReplaceAddFragment.newInstance(filter_bean, filter_id_two, permissionsBean));
                break;
            case Constants.FEED_BACK_ADD:
                FeedBackBean feed_bean = (FeedBackBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String filter_d_two = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(FeedbackAddFragment.newInstance(feed_bean, filter_d_two, permissionsBean));
                break;
            case Constants.WARRANTY_ADD:
                WarrantyBean waa_bean = (WarrantyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String fwa_d_two = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(WarrantyAddFragment.newInstance(waa_bean, fwa_d_two, permissionsBean));
                break;
            case Constants.MONTH_DETAIL:
                String month_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(MonthDetailFragment.newInstance(month_id, permissionsBean));
                break;
            case Constants.FEED_BACK_DETAIL:
                String feed_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(FeedBackDetailFragment.newInstance(feed_id, permissionsBean));
                break;
            case Constants.PT_RESULT:
                PressurePageBean ptBean = (PressurePageBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String mes = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(PressureResultFragmrnt.newInstance(mes, ptBean));
                break;
            case Constants.MONTH_ADD:
                DailyBean onth_bean = (DailyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String onth_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(MonthAddFragment.newInstance(onth_bean, onth_id, permissionsBean));
                break;
            case Constants.WEEKLY_DETAIL:
                String weekly_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(WeeklyDetailFragment.newInstance(weekly_id, permissionsBean));
                break;
            case Constants.WARRANTY_DETAIL:
                String weaay_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(WarrantyDetailFragment.newInstance(weaay_id, permissionsBean));
                break;
            case Constants.WEEKLY_ADD:
                DailyBean weekBean = (DailyBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String weekId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(WeeklyAddFragment.newInstance(weekBean, weekId, permissionsBean));
                break;
            case Constants.NOTICE_FILE:
                List<NoticeBean.NoticeFileBeans> fileList = (List<NoticeBean.NoticeFileBeans>) intent.getSerializableExtra(Constants.IMG_LIST);
                String fileName = intent.getStringExtra(Constants.IMG_NAME);

                replaceFragment(NoticeFileFragment.newInstance(fileList, fileName));
                break;
            case Constants.NOTICE_DETAIL:
                NoticeBean notice_bean = (NoticeBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                replaceFragment(NoticeDetailFragment.newInstance(notice_bean));
                break;
            case Constants.NOTICE:
                replaceFragment(NoticeFragment.newInstance());
                break;

            case Constants.TEST_PRESSURE:
                int type1 = getIntent().getIntExtra(Constants.STATISTICS_TYPE, Constants.PRESSURE_RECORD_DEFAULT);
                if (type1 == Constants.PRESSURE_RECORD_DEFAULT){
                    replaceFragment(OrderCompleteFragment.newInstance(permissionsBean));
                } else if (type1 == Constants.PRESSURE_RECORD_QIFEI){
                    replaceFragment(TestPressureFragment.newInstance());
                }
                break;
            case Constants.AREA_PRESSURE:
                int type2 = getIntent().getIntExtra(Constants.STATISTICS_TYPE, Constants.PRESSURE_RECORD_DEFAULT);
                if (type2 == Constants.PRESSURE_RECORD_DEFAULT){
                    replaceFragment(AreaTestPressureFragment.newInstance(permissionsBean));
                } else if (type2 == Constants.PRESSURE_RECORD_QIFEI){
                    replaceFragment(com.ak.pt.mvp.fragment.qifei.AreaTestPressureFragment.newInstance());
                }
                break;
            case Constants.BIG_PRESSURE:
                int type3 = getIntent().getIntExtra(Constants.STATISTICS_TYPE, Constants.PRESSURE_RECORD_DEFAULT);
                if (type3 == Constants.PRESSURE_RECORD_DEFAULT){
                    replaceFragment(BigTestPressureFragment.newInstance());
                } else if (type3 == Constants.PRESSURE_RECORD_QIFEI){
                    replaceFragment(com.ak.pt.mvp.fragment.qifei.BigTestPressureFragment.newInstance());
                }
                break;
            case Constants.MESSAGE:
                replaceFragment(MessageFragment.newInstance());
                break;
            case Constants.CHOOSE_WORKER:
                replaceFragment(ChooseWorkerFragment.newInstance(permissionsBean));
                break;
            case Constants.MESSAGE_DETAIL:
                String msg_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                String is_red = getIntent().getStringExtra(Constants.IS_RED);
                String msgType = getIntent().getStringExtra(Constants.TYPE);
                replaceFragment(MessageDetailFragment.newInstance(msg_id, is_red, msgType));
                break;
            case Constants.PERSONAL_INFO:
                replaceFragment(PersonalInfoFragment.newInstance());
                break;
            case Constants.CHANGE_PWD://修改密码
                replaceFragment(ChangePwdFragment.newIntance());
                break;
            case Constants.SAFTWARE:
                replaceFragment(SaftwareFragment.newInstance());
                break;
            case Constants.CHECK:
                replaceFragment(SecurityCheckFragment.newInstance(permissionsBean));
                break;
            case Constants.BLE_LIST:
                PressurePageBean ptBeanONE = (PressurePageBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                replaceFragment(BleListFragment.newInstance(ptBeanONE));
                break;
            case Constants.FORGOT_PWD:
                String changeType = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ForgetPwdFragment.newInstance(changeType));
                break;
            case Constants.UP_IMG:
                String doc_no = getIntent().getStringExtra(Constants.DETAIL_ID);
                String address = getIntent().getStringExtra(Constants.ADDRESS);
                replaceFragment(UpImgFragment.newInstance(doc_no, address));
                break;
            case Constants.MALL:
                replaceFragment(MallFragment.newInstance(permissionsBean));
                break;
            case Constants.PT_SETTING:
                replaceFragment(PtSettingFragment.newInstance());
                break;
            case Constants.PT_SETTING_CHILD:
                PressureDropBean dropBean = (PressureDropBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                replaceFragment(PTSettingChildFragment.newInstance(dropBean));
                break;
            case Constants.ORDER_ADD:
                PressurePageBean pressureBean = (PressurePageBean) intent.getSerializableExtra(Constants.DETAIL_BEAN);
                String addType = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(OrderAddFragment.newInstance(pressureBean, addType, permissionsBean));
                break;
            case Constants.IMG_TWO:
                List<PhotoListBean> photoBeanList = (List<PhotoListBean>) intent.getSerializableExtra(Constants.IMG_LIST);
                replaceFragment(OrderImgTwoFragment.newInstance(photoBeanList));
                break;
            case Constants.PRESSURE_DETAIL:
                String doc_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(OrderWorkerDetailFragment.newInstance(doc_id, permissionsBean));
                break;
            case Constants.PRESSURE_QF_DETAIL:
                String qf_doc_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(TestPressureDetailFragment.newInstance(qf_doc_id));
                break;
            case Constants.ORDER_IMG_TWO:
                List<com.ak.pt.mvp.fragment.qifei.PressurePageBean.PiptbBaseTypeBean> imgListTwo = (List<com.ak.pt.mvp.fragment.qifei.PressurePageBean.PiptbBaseTypeBean>) intent.getSerializableExtra(Constants.IMG_LIST);
                String imgNameTwo = intent.getStringExtra(Constants.IMG_NAME);
                replaceFragment(com.ak.pt.mvp.fragment.qifei.OrderImgTwoFragment.newInstance(imgListTwo, imgNameTwo));
                break;
            case Constants.MANAGER_DETAIL:
                String manager_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(OrderManagerDetailFragment.newInstance(manager_id, permissionsBean));
                break;
            case Constants.COMPLETE_DETAIL:
                String comId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(OrderCompleteDetailFragment.newInstance(comId, permissionsBean));
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(com.ak.pt.bean.FirstEvent event) {
        switch (event.getMsg()) {
            case "exit":
                finish();
                break;
//            case "expired_two":
//                finish();
//                break;
        }
    }


}
