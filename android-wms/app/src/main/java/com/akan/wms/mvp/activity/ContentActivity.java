package com.akan.wms.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.akan.wms.Constants;
import com.akan.wms.R;
import com.akan.wms.bean.BarBean;
import com.akan.wms.bean.BarMsgBean;
import com.akan.wms.bean.BarVerificationListsBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.InforListBean;
import com.akan.wms.bean.OutSaleRtuBean;
import com.akan.wms.bean.ProductionOrderBean;
import com.akan.wms.bean.PurchaseBean;
import com.akan.wms.bean.SaleReturnBean;
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.ShipPlanBean;
import com.akan.wms.bean.TransferOutBean;
import com.akan.wms.bean.TransferUnCompleteBean;
import com.akan.wms.bean.WarnTwoBean;
import com.akan.wms.mvp.fragment.ChooseDeportByIdFragment;
import com.akan.wms.mvp.fragment.ChooseDeportFragment;
import com.akan.wms.mvp.fragment.ChooseMfcFragment;
import com.akan.wms.mvp.fragment.ChooseOrganizationFragment;
import com.akan.wms.mvp.fragment.ForgetPwdFragment;
import com.akan.wms.mvp.fragment.LoginFragment;
import com.akan.wms.mvp.fragment.MessageFragment;
import com.akan.wms.mvp.fragment.OutApplyTypeAllFragment;
import com.akan.wms.mvp.fragment.base.CustomerFragment;
import com.akan.wms.mvp.fragment.base.DeportBaseFragment;
import com.akan.wms.mvp.fragment.base.FlowListFragment;
import com.akan.wms.mvp.fragment.base.StockFindDetailFragment;
import com.akan.wms.mvp.fragment.base.StockFindFragment;
import com.akan.wms.mvp.fragment.base.SupplierBaseFragment;
import com.akan.wms.mvp.fragment.home.AllFunctionFragment;
import com.akan.wms.mvp.fragment.home.ChooseDepotFragment;
import com.akan.wms.mvp.fragment.home.HomeScanChildResultFragment;
import com.akan.wms.mvp.fragment.home.HomeScanInputFragment;
import com.akan.wms.mvp.fragment.home.HomeScanResultFragment;
import com.akan.wms.mvp.fragment.home.InventoryWarningFragment;
import com.akan.wms.mvp.fragment.home.OutSaleCodeDetail;
import com.akan.wms.mvp.fragment.in.ChooseCompleteParamFragment;
import com.akan.wms.mvp.fragment.in.ChooseGoodsMoreFragment;
import com.akan.wms.mvp.fragment.in.ChooseReceiptReportFragment;
import com.akan.wms.mvp.fragment.in.ChooseSaleReturnChildFragment;
import com.akan.wms.mvp.fragment.in.ChooseSaleReturnListFragment;
import com.akan.wms.mvp.fragment.in.ChooseSupplierFragment;
import com.akan.wms.mvp.fragment.in.CompleteAddFragment;
import com.akan.wms.mvp.fragment.in.CompleteAddNewFragment;
import com.akan.wms.mvp.fragment.in.CompleteChooseFragment;
import com.akan.wms.mvp.fragment.in.CompleteDetailFragment;
import com.akan.wms.mvp.fragment.in.CompleteListFragment;
import com.akan.wms.mvp.fragment.in.FinishAddFragment;
import com.akan.wms.mvp.fragment.in.FinishChooseFragment;
import com.akan.wms.mvp.fragment.in.FinishDetailFragment;
import com.akan.wms.mvp.fragment.in.FinishListFragment;
import com.akan.wms.mvp.fragment.in.InBuyAddFragment;
import com.akan.wms.mvp.fragment.in.InBuyDetailFragment;
import com.akan.wms.mvp.fragment.in.InBuyListFragment;
import com.akan.wms.mvp.fragment.in.InSaleReturnAddFragment;
import com.akan.wms.mvp.fragment.in.InSaleReturnDetailFragment;
import com.akan.wms.mvp.fragment.in.InSaleReturnListFragment;
import com.akan.wms.mvp.fragment.in.ProduceReturnAddFragment;
import com.akan.wms.mvp.fragment.in.ProduceReturnDetailFragment;
import com.akan.wms.mvp.fragment.in.ProduceReturnListFragment;
import com.akan.wms.mvp.fragment.in.StockChildFragment;
import com.akan.wms.mvp.fragment.in.StockListFragment;
import com.akan.wms.mvp.fragment.manager.CheckAddFragment;
import com.akan.wms.mvp.fragment.manager.CheckDetailFragment;
import com.akan.wms.mvp.fragment.manager.CheckListFragment;
import com.akan.wms.mvp.fragment.manager.ChooseItemFragment;
import com.akan.wms.mvp.fragment.manager.ChooseTransferOutChildFragment;
import com.akan.wms.mvp.fragment.manager.ChooseTransferOutFragment;
import com.akan.wms.mvp.fragment.manager.InApplyTypeFragment;
import com.akan.wms.mvp.fragment.manager.InTransferAddFragment;
import com.akan.wms.mvp.fragment.manager.InTransferDetailFragment;
import com.akan.wms.mvp.fragment.manager.InTransferListFragment;
import com.akan.wms.mvp.fragment.manager.OutApplyTypeFragment;
import com.akan.wms.mvp.fragment.manager.OutTransferAddFragment;
import com.akan.wms.mvp.fragment.manager.OutTransferAddNewFragment;
import com.akan.wms.mvp.fragment.manager.OutTransferDetailFragment;
import com.akan.wms.mvp.fragment.manager.OutTransferListFragment;
import com.akan.wms.mvp.fragment.manager.TransferAddFragment;
import com.akan.wms.mvp.fragment.manager.TransferApplyChildFragment;
import com.akan.wms.mvp.fragment.manager.TransferApplyListFragment;
import com.akan.wms.mvp.fragment.manager.TransferDetailFragment;
import com.akan.wms.mvp.fragment.manager.TransferListFragment;
import com.akan.wms.mvp.fragment.mine.AccountFragment;
import com.akan.wms.mvp.fragment.mine.ChangePwdFragment;
import com.akan.wms.mvp.fragment.mine.PersonalInfoFragment;
import com.akan.wms.mvp.fragment.mine.SaftwareFragment;
import com.akan.wms.mvp.fragment.mine.SettingFragment;
import com.akan.wms.mvp.fragment.mine.SuggestFragment;
import com.akan.wms.mvp.fragment.mix.ReceiveMixDetailFragment;
import com.akan.wms.mvp.fragment.mix.ReceiveMixListFragment;
import com.akan.wms.mvp.fragment.mix.SendMixDetailFragment;
import com.akan.wms.mvp.fragment.mix.SendMixListFragment;
import com.akan.wms.mvp.fragment.out.ChooseBuyReturnChildFragment;
import com.akan.wms.mvp.fragment.out.ChooseBuyReturnListFragment;
import com.akan.wms.mvp.fragment.out.ChooseOutPlanChildFragment;
import com.akan.wms.mvp.fragment.out.ChooseOutPlanListFragment;
import com.akan.wms.mvp.fragment.out.OutBuyReturnAddFragment;
import com.akan.wms.mvp.fragment.out.OutBuyReturnDetailFragment;
import com.akan.wms.mvp.fragment.out.OutBuyReturnListFragment;
import com.akan.wms.mvp.fragment.out.OutSaleAddFragment;
import com.akan.wms.mvp.fragment.out.OutSaleDetailFragment;
import com.akan.wms.mvp.fragment.out.OutSaleListFragment;
import com.akan.wms.mvp.fragment.out.ProduceChooseFragment;
import com.akan.wms.mvp.fragment.out.ProduceReceiveAddFragment;
import com.akan.wms.mvp.fragment.out.ProduceReceiveDetailFragment;
import com.akan.wms.mvp.fragment.out.ProduceReceiveListFragment;
import com.akan.wms.mvp.fragment.out.SaleOutTypeFragment;
import com.akan.wms.mvp.fragment.scan.ScanInBuyFragment;
import com.akan.wms.mvp.fragment.scan.ScanInBuyResultFragment;
import com.akan.wms.util.keyback.BackHandlerHelper;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);


        swichFragment(getIntent());
        EventBus.getDefault().register(this);
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
            case "3":
                finish();
                break;
            case "token_fail_two":
                finish();
                break;
        }
    }


    public void swichFragment(Intent intent) {

        int fragmentKey = intent.getIntExtra(Constants.KEY_FRAGMENT, 0);
        switch (fragmentKey) {
            case Constants.LOGIN_FRAGMENT:
                replaceFragment(LoginFragment.newInstance());
                break;
            case Constants.INVENTORY_WARNING:
                replaceFragment(InventoryWarningFragment.newInstance());
                break;
            case Constants.SUPPLIER:
                replaceFragment(SupplierBaseFragment.newInstance());
                break;
            case Constants.DEPORT_BASE:
                replaceFragment(DeportBaseFragment.newInstance());
                break;
            case Constants.HOME_SCAN_INPUT:
                replaceFragment(HomeScanInputFragment.newInstance());
                break;
            case Constants.COMPLETE_CHOOSE:
                String reportType = getIntent().getStringExtra(Constants.DETAIL_ID);
                String addType = getIntent().getStringExtra(Constants.DETAIL_TYPE);
                replaceFragment(CompleteChooseFragment.newInstance(reportType, addType));
                break;
            case Constants.FLOW_LIST:
                String itemId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(FlowListFragment.newInstance(itemId));
                break;
            case Constants.CHECK_LIST:
                replaceFragment(CheckListFragment.newInstance());
                break;
            case Constants.RECEIVE_MIX_LIST:
                replaceFragment(ReceiveMixListFragment.newInstance());
                break;
            case Constants.SEND_MIX_LIST:
                replaceFragment(SendMixListFragment.newInstance());
                break;
            case Constants.STOCK_FIND:
                replaceFragment(StockFindFragment.newInstance());
                break;
            case Constants.CHOOSE_DEPOT:
                String chooseType = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ChooseDepotFragment.newInstance(chooseType));
                break;
            case Constants.RECEIVE_MIX_DETAIL:
                String recId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ReceiveMixDetailFragment.newInstance(recId));
                break;
            case Constants.SEND_MIX_DETAIL:
                String sendId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(SendMixDetailFragment.newInstance(sendId));
                break;
            case Constants.FINISH_DETAIL:
                String finishId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(FinishDetailFragment.newInstance(finishId));
                break;
            case Constants.PRODUCE_CHOOSE:
                String produceChooseType = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ProduceChooseFragment.newInstance(produceChooseType));
                break;
            case Constants.SAFTWARE:
                replaceFragment(SaftwareFragment.newInstance());
                break;
            case Constants.TRANSFER_APPLY_LIST:
                String tranfeAddType = getIntent().getStringExtra(Constants.DETAIL_ID);
                String inType = getIntent().getStringExtra(Constants.DETAIL_TYPE);
                replaceFragment(TransferApplyListFragment.newInstance(tranfeAddType, inType));
                break;
            case Constants.OUT_TRANSFER_LIST:
                replaceFragment(OutTransferListFragment.newInstance());
                break;
            case Constants.OUT_TRANSFER_ADD:
                replaceFragment(OutTransferAddFragment.newInstance());
                break;
            case Constants.ACCOUNT:
                replaceFragment(AccountFragment.newInstance());
                break;
            case Constants.CHOOSE_SALE_TYPE:
                replaceFragment(SaleOutTypeFragment.newInstance());
                break;
            case Constants.OUT_APPLY_TYPE:
                replaceFragment(OutApplyTypeFragment.newInstance());
                break;
            case Constants.PRODUCE_RECEIVE_LIST:
                replaceFragment(ProduceReceiveListFragment.newInstance());
                break;
            case Constants.FINISH_CHOOSE:
                replaceFragment(FinishChooseFragment.newInstance());
                break;
            case Constants.FORGOT_PWD:
                String account = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ForgetPwdFragment.newInstance(account));
                break;
            case Constants.CHOOSE_COMPLETE_PARAM:
                String paramType = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ChooseCompleteParamFragment.newInstance(paramType));
                break;
            case Constants.CHECK_DETAIL:
                String checkId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(CheckDetailFragment.newInstance(checkId));
                break;
            case Constants.CHOOSE_ORGANIZATION:
                String staffAccount = getIntent().getStringExtra(Constants.DETAIL_ID);
                String orgName = getIntent().getStringExtra(Constants.DETAIL_TYPE);
                replaceFragment(ChooseOrganizationFragment.newInstance(staffAccount, orgName));
                break;
            case Constants.MESSAGE:
                replaceFragment(MessageFragment.newInstance());
                break;
            case Constants.CHOOSE_DEPORT:
                replaceFragment(ChooseDeportFragment.newInstance("", ""));
                break;
            case Constants.CHOOSE_DEPORT_BY_ID:
                String departItemId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ChooseDeportByIdFragment.newInstance(departItemId, ""));
                break;
            case Constants.PURCHASE_RECEIVE_ADD:
                replaceFragment(ProduceReceiveAddFragment.newInstance());
                break;
            case Constants.SUGGEST:
                replaceFragment(SuggestFragment.newInstance());
                break;
            case Constants.CHECK_ADD:
                replaceFragment(CheckAddFragment.newInstance());
                break;
            case Constants.SEND_ADD:
                replaceFragment(InBuyAddFragment.newInstance());
                break;
            case Constants.TRANSFER_ADD:
                replaceFragment(TransferAddFragment.newInstance());
                break;
            case Constants.IN_TRANSFER_ADD:
                replaceFragment(InTransferAddFragment.newInstance());
                break;

            case Constants.IN_APPLY_TYPE:
                replaceFragment(InApplyTypeFragment.newInstance());
                break;
            case Constants.CHOOSE_TRANSFER_OUT:
                String outType = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ChooseTransferOutFragment.newInstance(outType));
                break;
            case Constants.CHOOSE_BUY_RETURN_LIST:
                String supId = getIntent().getStringExtra(Constants.DETAIL_ID);
                String inReturnType = getIntent().getStringExtra(Constants.DETAIL_TYPE);
                replaceFragment(ChooseBuyReturnListFragment.newInstance(supId, inReturnType));
                break;
            case Constants.OUT_TRANSFER_DETAIL:
                String outId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(OutTransferDetailFragment.newInstance(outId));
                break;
            case Constants.ALL_FUNCTION:
                String functionType = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(AllFunctionFragment.newInstance(functionType));
                break;
            case Constants.PRODUCE_RECEIVE_DETAIL:
                String receiveId = getIntent().getStringExtra(Constants.DETAIL_ID);
                String receiveState = getIntent().getStringExtra(Constants.STATE);
                replaceFragment(ProduceReceiveDetailFragment.newInstance(receiveId, receiveState));
                break;
            case Constants.OUT_BUY_RETURN_DETAIL:
                String outBuyReturnId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(OutBuyReturnDetailFragment.newInstance(outBuyReturnId));
                break;
            case Constants.IN_SALE_RETURN_DETAIL:
                String saleReturnId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(InSaleReturnDetailFragment.newInstance(saleReturnId));
                break;
            case Constants.CHOOSE_RECEIPT_REPORT:
                String mItemId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ChooseReceiptReportFragment.newInstance(mItemId));
                break;
            case Constants.SZ:
                replaceFragment(SettingFragment.newInstance());
                break;
            case Constants.IN_TRANSFER_LIST:
                replaceFragment(InTransferListFragment.newInstance());
                break;
            case Constants.OUT_BUY_RETURN_ADD:
                replaceFragment(OutBuyReturnAddFragment.newInstance());
                break;
            case Constants.OUT_SALE_ADD:
                replaceFragment(OutSaleAddFragment.newInstance());
                break;
            case Constants.STOCK_LIST:
                String supllId = getIntent().getStringExtra(Constants.DETAIL_ID);
                String stokType = getIntent().getStringExtra(Constants.DETAIL_TYPE);
                replaceFragment(StockListFragment.newInstance(supllId, stokType));
                break;
            case Constants.CHANGE_PWD://修改密码
                replaceFragment(ChangePwdFragment.newIntance());
                break;
            case Constants.OUT_BUG_RETURN:
                replaceFragment(OutBuyReturnListFragment.newInstance());
                break;
            case Constants.OUT_SALE:
                replaceFragment(OutSaleListFragment.newInstance());
                break;

            case Constants.SCAN_RESULT:
                List<InforListBean> list = (List<InforListBean>) getIntent().getSerializableExtra(Constants.LIST_DATA);
                List<BarBean> barList = (List<BarBean>) getIntent().getSerializableExtra(Constants.LIST_DATA_TWO);
                String scanResultType = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ScanResultFragment.newInstance(list, barList, scanResultType));
                break;
            case Constants.SCAN:
                List<InforListBean> listTwo = (List<InforListBean>) getIntent().getSerializableExtra(Constants.LIST_DATA);
                String scanType = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(CustomActivity.newInstance(listTwo, scanType));
                break;
            case Constants.IN_SALE_RETURN_ADD:
                replaceFragment(InSaleReturnAddFragment.newInstance());
                break;
            case Constants.IN_BUY:
                replaceFragment(InBuyListFragment.newInstance());
                break;
            case Constants.CHOOSE_SUPPLIER:
                replaceFragment(ChooseSupplierFragment.newInstance());
                break;
            case Constants.CHOOSE_SALE_RETURN_LIST:
                replaceFragment(ChooseSaleReturnListFragment.newInstance());
                break;
            case Constants.IN_SALE_RETURN:
                replaceFragment(InSaleReturnListFragment.newInstance());
                break;

            case Constants.CHOOSE_OUT_PLAN_LIST:
                String typeName = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ChooseOutPlanListFragment.newInstance(typeName));
                break;
            case Constants.COMPLETE_ADD:
                replaceFragment(CompleteAddFragment.newInstance());
                break;

            case Constants.CHOOSE_GOODS_MORE:
                replaceFragment(ChooseGoodsMoreFragment.newInstance());
                break;
            case Constants.TRANSFER_LIST:
                replaceFragment(TransferListFragment.newInstance());
                break;
            case Constants.PRODUCE_RETURN_ADD:
                replaceFragment(ProduceReturnAddFragment.newInstance());
                break;
            case Constants.CUSTOMER:
                replaceFragment(CustomerFragment.newInstance());
                break;
            case Constants.FINISH_LIST:
                replaceFragment(FinishListFragment.newInstance());
                break;
            case Constants.FINISH_ADD:
                replaceFragment(FinishAddFragment.newInstance());
                break;
            case Constants.PRODUCE_RETURN_LIST:
                replaceFragment(ProduceReturnListFragment.newInstance());
                break;

            case Constants.PRODUCE_RETURN_DETAIL:
                String produceReturnId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ProduceReturnDetailFragment.newInstance(produceReturnId));
                break;
            case Constants.TRANSFER_DETAIL:
                String transferNo = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(TransferDetailFragment.newInstance(transferNo));
                break;
            case Constants.CHOOSE_MFC:
                String mfcId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ChooseMfcFragment.newInstance(mfcId));
                break;
            case Constants.IN_TRANSFER_DETAIL:
                String inTransferId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(InTransferDetailFragment.newInstance(inTransferId));
                break;
            case Constants.COMPLETE_DETAIL:
                String completeId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(CompleteDetailFragment.newInstance(completeId));
                break;
            case Constants.CHOOSE_ITEM:
                String wh_id = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ChooseItemFragment.newInstance(wh_id));
                break;
            case Constants.OUT_SALE_DETAIL:
                String outSaleId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(OutSaleDetailFragment.newInstance(outSaleId));
                break;
            case Constants.IN_BUY_DETAIL:
                String inBuyId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(InBuyDetailFragment.newInstance(inBuyId));
                break;
            case Constants.COMPLETE_LIST:
                replaceFragment(CompleteListFragment.newInstance());
                break;

            case Constants.PERSONAL_INFO:
                replaceFragment(PersonalInfoFragment.newInstance());
                break;
            case Constants.STOCK_CHILD:
                PurchaseBean pushBean = (PurchaseBean) intent.getSerializableExtra(Constants.BEAN);
                String childType = getIntent().getStringExtra(Constants.DETAIL_TYPE);
                replaceFragment(StockChildFragment.newInstance(pushBean, childType));
                break;
            case Constants.HOME_SCAN_OUT_SALE:
                BarMsgBean scanOutSaleBean = (BarMsgBean) intent.getSerializableExtra(Constants.BEAN);
                replaceFragment(OutSaleCodeDetail.newInstance(scanOutSaleBean));
                break;
            case Constants.HOME_SCAN:
                BarMsgBean scanBean = (BarMsgBean) intent.getSerializableExtra(Constants.BEAN);
                replaceFragment(HomeScanResultFragment.newInstance(scanBean));
                break;

            case Constants.SCAN_IN_BUY:
                List<ScanInfoBean> scanInfoList = (List<ScanInfoBean>) getIntent().getSerializableExtra(Constants.LIST_DATA);
                List<BarVerificationListsBean> BarVerificationList = (List<BarVerificationListsBean>) getIntent().getSerializableExtra(Constants.LIST_BAR);
                String scanInfoType = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ScanInBuyFragment.newInstance(scanInfoList, scanInfoType,BarVerificationList));
                break;
            case Constants.SCAN_RESULT_CHILD:
                List<String> scanChildCode = (List<String>) getIntent().getSerializableExtra(Constants.LIST_DATA);
                replaceFragment(HomeScanChildResultFragment.newInstance(scanChildCode));
                break;
            case Constants.SCAN_IN_BUY_RESULT:
                List<ScanInfoBean> inBuyList = (List<ScanInfoBean>) getIntent().getSerializableExtra(Constants.LIST_DATA);
                List<BarBean> barInBuyList = (List<BarBean>) getIntent().getSerializableExtra(Constants.LIST_DATA_TWO);
                String inBuyResultType = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ScanInBuyResultFragment.newInstance(inBuyList, barInBuyList, inBuyResultType));
                break;
            case Constants.CHOOSE_SALE_RETURN_CHILD:
                SaleReturnBean pshBean = (SaleReturnBean) getIntent().getSerializableExtra(Constants.BEAN);
                replaceFragment(ChooseSaleReturnChildFragment.newInstance(pshBean));
                break;
            case Constants.CHOOSE_BUY_RETURN_CHILD:
                String outReturnChildId = getIntent().getStringExtra(Constants.DETAIL_ID);
                String inReturnChildType = getIntent().getStringExtra(Constants.DETAIL_TYPE);
                OutSaleRtuBean applyedRtnBean = (OutSaleRtuBean) getIntent().getSerializableExtra(Constants.BEAN);
                replaceFragment(ChooseBuyReturnChildFragment.newInstance(applyedRtnBean, outReturnChildId, inReturnChildType));
                break;
            case Constants.CHOOSE_OUT_PLAN_CHILD:
                ShipPlanBean shipPlanBean = (ShipPlanBean) getIntent().getSerializableExtra(Constants.BEAN);
                String childPlanType = getIntent().getStringExtra(Constants.STATE);
                String mId = getIntent().getStringExtra(Constants.DETAIL_ID);
                replaceFragment(ChooseOutPlanChildFragment.newInstance(shipPlanBean, childPlanType, mId));
                break;
            case Constants.CHOOSE_TRANFER_APPLY_CHILD:
                TransferUnCompleteBean applyBean = (TransferUnCompleteBean) getIntent().getSerializableExtra(Constants.BEAN);
                String applyType = getIntent().getStringExtra(Constants.STATE);
                String mIn = getIntent().getStringExtra(Constants.DETAIL_TYPE);
                replaceFragment(TransferApplyChildFragment.newInstance(applyBean, applyType, mIn));
                break;
            case Constants.CHOOSE_TRANFER_OUT_CHILD:
                TransferOutBean outBean = (TransferOutBean) getIntent().getSerializableExtra(Constants.BEAN);
                String outTranferType = getIntent().getStringExtra(Constants.STATE);
                replaceFragment(ChooseTransferOutChildFragment.newInstance(outBean, outTranferType));
                break;
            case Constants.STOCK_FIND_DETAIL:
                WarnTwoBean warnBean = (WarnTwoBean) getIntent().getSerializableExtra(Constants.BEAN);
                replaceFragment(StockFindDetailFragment.newInstance(warnBean));
                break;
            case Constants.OUT_TRANSFER_ADD_NEW:
                TransferUnCompleteBean outTransferAddBean = (TransferUnCompleteBean) getIntent().getSerializableExtra(Constants.BEAN);
                replaceFragment(OutTransferAddNewFragment.newInstance(outTransferAddBean));
                break;
            case Constants.COMPLETE_ADD_NEW:
                ProductionOrderBean produceAddBean = (ProductionOrderBean) getIntent().getSerializableExtra(Constants.BEAN);
                replaceFragment(CompleteAddNewFragment.newInstance(produceAddBean));
                break;

            case Constants.OUT_APPLY_TYPE_ALL:
                replaceFragment(OutApplyTypeAllFragment.newInstance());
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
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}
