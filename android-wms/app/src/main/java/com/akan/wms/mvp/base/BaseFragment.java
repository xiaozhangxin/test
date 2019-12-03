package com.akan.wms.mvp.base;

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

import com.akan.wms.App;
import com.akan.wms.Constants;
import com.akan.wms.bean.BarBean;
import com.akan.wms.bean.InforListBean;
import com.akan.wms.bean.OutSaleRtuBean;
import com.akan.wms.bean.ProductionOrderBean;
import com.akan.wms.bean.PurchaseBean;
import com.akan.wms.bean.ReturnBean;
import com.akan.wms.bean.SaleReturnBean;
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.ShipPlanBean;
import com.akan.wms.bean.TransferOutBean;
import com.akan.wms.bean.TransferUnCompleteBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WarnTwoBean;
import com.akan.wms.mvp.activity.ContentActivity;
import com.akan.wms.util.SPUtils;
import com.akan.wms.util.SpSingleInstance;
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

    //保存个人信息
    protected void saveUser(UserBean userBean) {
        SPUtils.saveObJ1(getContext(), Constants.USER_BEAN, userBean);
        SpSingleInstance.getSpSingleInstance().setUserBean(userBean);
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


    protected void startMessageFragment() {
        Intent intent = getFragmentIntent(Constants.MESSAGE);
        startActivity(intent);
    }

    protected void startDepotInFragment() {
        Intent intent = getFragmentIntent(Constants.DEPORT_IN);
        startActivity(intent);
    }

    protected void startChooseOrganizationFragment(String id, String type) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_ORGANIZATION);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.DETAIL_TYPE, type);
        startActivity(intent);
    }

    protected void startForgetPwdFragment(String detail_id) {
        Intent intent = getFragmentIntent(Constants.FORGOT_PWD);
        intent.putExtra(Constants.DETAIL_ID, detail_id);
        startActivity(intent);
    }

    protected void startScanFragment(List<InforListBean> list, String detail_id) {
        Intent intent = getFragmentIntent(Constants.SCAN);
        intent.putExtra(Constants.LIST_DATA, (Serializable) list);
        intent.putExtra(Constants.DETAIL_ID, detail_id);
        startActivity(intent);
    }

    protected void startScanBeanFragment(ProductionOrderBean bean, String detail_id) {
        Intent intent = getFragmentIntent(Constants.SCAN_BEAN);
        intent.putExtra(Constants.BEAN, bean);
        intent.putExtra(Constants.DETAIL_ID, detail_id);
        startActivity(intent);
    }

    protected void startScanResultFragment(List<InforListBean> list, List<BarBean> barList, String detail_id) {
        Intent intent = getFragmentIntent(Constants.SCAN_RESULT);
        intent.putExtra(Constants.LIST_DATA, (Serializable) list);
        intent.putExtra(Constants.LIST_DATA_TWO, (Serializable) barList);
        intent.putExtra(Constants.DETAIL_ID, detail_id);
        startActivity(intent);
    }

    protected void startScanBeanResultFragment(List<ProductionOrderBean> list, List<BarBean> barList, String detail_id) {
        Intent intent = getFragmentIntent(Constants.SCAN_BEAN_RESULT);
        intent.putExtra(Constants.LIST_DATA, (Serializable) list);
        intent.putExtra(Constants.LIST_DATA_TWO, (Serializable) barList);
        intent.putExtra(Constants.DETAIL_ID, detail_id);
        startActivity(intent);
    }

    protected void startScanInBUyResultFragment(List<ScanInfoBean> list, List<BarBean> barList, String detail_id) {
        Intent intent = getFragmentIntent(Constants.SCAN_IN_BUY_RESULT);
        intent.putExtra(Constants.LIST_DATA, (Serializable) list);
        intent.putExtra(Constants.LIST_DATA_TWO, (Serializable) barList);
        intent.putExtra(Constants.DETAIL_ID, detail_id);
        startActivity(intent);
    }

    protected void startCompleteAddFragment() {
        Intent intent = getFragmentIntent(Constants.COMPLETE_ADD);
        startActivity(intent);
    }

    protected void startCompleteAddNewFragment(ProductionOrderBean bean) {
        Intent intent = getFragmentIntent(Constants.COMPLETE_ADD_NEW);
        intent.putExtra(Constants.BEAN, bean);
        startActivity(intent);
    }

    protected void startFinishAddFragment() {
        Intent intent = getFragmentIntent(Constants.FINISH_ADD);
        startActivity(intent);
    }

    protected void startOutSaleAddFragment() {
        Intent intent = getFragmentIntent(Constants.OUT_SALE_ADD);
        startActivity(intent);
    }

    protected void startSendAddFragment() {
        Intent intent = getFragmentIntent(Constants.SEND_ADD);
        startActivity(intent);
    }

    protected void startCheckAddFragment() {
        Intent intent = getFragmentIntent(Constants.CHECK_ADD);
        startActivity(intent);
    }

    protected void startChooseBuyReturnListFragment(String id,String inType) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_BUY_RETURN_LIST);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.DETAIL_TYPE, inType);
        startActivity(intent);
    }

    protected void startStockListFragment(String id,String type) {
        Intent intent = getFragmentIntent(Constants.STOCK_LIST);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.DETAIL_TYPE, type);
        startActivity(intent);
    }

    protected void startChooseReceiptReportFragment(String id) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_RECEIPT_REPORT);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startChooseMfcFragment(String id) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_MFC);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startChooseSupplierFragment() {
        Intent intent = getFragmentIntent(Constants.CHOOSE_SUPPLIER);
        startActivity(intent);
    }

    protected void startChooseOperatorFragment() {
        Intent intent = getFragmentIntent(Constants.CHOOSE_OPERATOR);
        startActivity(intent);
    }

    protected void startDeportOutFragment() {
        Intent intent = getFragmentIntent(Constants.DEPORT_OUT);
        startActivity(intent);
    }

    protected void startProduceReturnAddFragment() {
        Intent intent = getFragmentIntent(Constants.PRODUCE_RETURN_ADD);
        startActivity(intent);
    }

    protected void startChooseSaleReturnListFragment() {
        Intent intent = getFragmentIntent(Constants.CHOOSE_SALE_RETURN_LIST);
        startActivity(intent);
    }

    protected void startChooseOutPlanListFragment(String id) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_OUT_PLAN_LIST);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startChooseSaleTypeFragment() {
        Intent intent = getFragmentIntent(Constants.CHOOSE_SALE_TYPE);
        startActivity(intent);
    }

    protected void startChooseGoodsMoreFragment() {
        Intent intent = getFragmentIntent(Constants.CHOOSE_GOODS_MORE);
        startActivity(intent);
    }

    protected void startChooseDeportFragment() {
        Intent intent = getFragmentIntent(Constants.CHOOSE_DEPORT);
        startActivity(intent);
    }


    protected void startOutApplyTypeFragment() {
        Intent intent = getFragmentIntent(Constants.OUT_APPLY_TYPE);
        startActivity(intent);
    }

    protected void startOutApplyTypeAllFragment() {
        Intent intent = getFragmentIntent(Constants.OUT_APPLY_TYPE_ALL);
        startActivity(intent);
    }

    protected void startInApplyTypeFragment() {
        Intent intent = getFragmentIntent(Constants.IN_APPLY_TYPE);
        startActivity(intent);
    }

    protected void startProduceChooseFragment(String id) {
        Intent intent = getFragmentIntent(Constants.PRODUCE_CHOOSE);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startAllFunctionFragment(String id) {
        Intent intent = getFragmentIntent(Constants.ALL_FUNCTION);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startChooseItemFragment(String id) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_ITEM);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startInSaleReturnDetailFragment(String id) {
        Intent intent = getFragmentIntent(Constants.IN_SALE_RETURN_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startCheckDetailFragment(String id) {
        Intent intent = getFragmentIntent(Constants.CHECK_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startSendMixDetailFragment(String id) {
        Intent intent = getFragmentIntent(Constants.SEND_MIX_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startReceiveMixDetailFragment(String id) {
        Intent intent = getFragmentIntent(Constants.RECEIVE_MIX_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startTransferDetailFragment(String id) {
        Intent intent = getFragmentIntent(Constants.TRANSFER_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startOutTransferFragment(String id) {
        Intent intent = getFragmentIntent(Constants.OUT_TRANSFER_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startChooseCompleteParamFragment(String id) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_COMPLETE_PARAM);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startInTransferFragment(String id) {
        Intent intent = getFragmentIntent(Constants.IN_TRANSFER_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startProduceReturnDetailFragment(String id) {
        Intent intent = getFragmentIntent(Constants.PRODUCE_RETURN_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startPurchaseReturnListFragment() {
        Intent intent = getFragmentIntent(Constants.PURCHASE_RETURN_LIST);
        startActivity(intent);
    }

    protected void startProduceReceiveAddFragment() {
        Intent intent = getFragmentIntent(Constants.PURCHASE_RECEIVE_ADD);
        startActivity(intent);
    }

    protected void startTransferAddFragment() {
        Intent intent = getFragmentIntent(Constants.TRANSFER_ADD);
        startActivity(intent);
    }

    protected void startOutTransferAddFragment() {
        Intent intent = getFragmentIntent(Constants.OUT_TRANSFER_ADD);
        startActivity(intent);
    }

    protected void startInTransferAddFragment() {
        Intent intent = getFragmentIntent(Constants.IN_TRANSFER_ADD);
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

    //修改密码
    protected void startChangePwdFragment() {
        Intent intent = getFragmentIntent(Constants.CHANGE_PWD);
        startActivity(intent);
    }

    //采购退货
    protected void startOutBuyReturnListFragment() {
        Intent intent = getFragmentIntent(Constants.OUT_BUG_RETURN);
        startActivity(intent);
    }

    protected void startOutSaleListFragment() {
        Intent intent = getFragmentIntent(Constants.OUT_SALE);
        startActivity(intent);
    }

    protected void startInBuyListFragment() {
        Intent intent = getFragmentIntent(Constants.IN_BUY);
        startActivity(intent);
    }

    protected void startInSaleReturnListFragment() {
        Intent intent = getFragmentIntent(Constants.IN_SALE_RETURN);
        startActivity(intent);
    }

    protected void startProduceReturnListFragment() {
        Intent intent = getFragmentIntent(Constants.PRODUCE_RETURN_LIST);
        startActivity(intent);
    }

    protected void startProduceReceiveListFragment() {
        Intent intent = getFragmentIntent(Constants.PRODUCE_RECEIVE_LIST);
        startActivity(intent);
    }

    protected void startSupplierFragment() {
        Intent intent = getFragmentIntent(Constants.SUPPLIER);
        startActivity(intent);
    }

    protected void startCustomerFragment() {
        Intent intent = getFragmentIntent(Constants.CUSTOMER);
        startActivity(intent);
    }

    protected void startDeportBaseFragment() {
        Intent intent = getFragmentIntent(Constants.DEPORT_BASE);
        startActivity(intent);
    }

    protected void startCheckListFragment() {
        Intent intent = getFragmentIntent(Constants.CHECK_LIST);
        startActivity(intent);
    }

    protected void startSendMixListFragment() {
        Intent intent = getFragmentIntent(Constants.SEND_MIX_LIST);
        startActivity(intent);
    }

    protected void startReceiveMixListFragment() {
        Intent intent = getFragmentIntent(Constants.RECEIVE_MIX_LIST);
        startActivity(intent);
    }

    protected void startOutTransferListFragment() {
        Intent intent = getFragmentIntent(Constants.OUT_TRANSFER_LIST);
        startActivity(intent);
    }

    protected void startFlowListFragment(String id) {
        Intent intent = getFragmentIntent(Constants.FLOW_LIST);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startInTransferListFragment() {
        Intent intent = getFragmentIntent(Constants.IN_TRANSFER_LIST);
        startActivity(intent);
    }

    protected void startStockFindListFragment() {
        Intent intent = getFragmentIntent(Constants.STOCK_FIND);
        startActivity(intent);
    }

    protected void startInventoryWarningFragment() {
        Intent intent = getFragmentIntent(Constants.INVENTORY_WARNING);
        startActivity(intent);
    }

    protected void startCompleteListFragment() {
        Intent intent = getFragmentIntent(Constants.COMPLETE_LIST);
        startActivity(intent);
    }

    protected void startFinishListFragment() {
        Intent intent = getFragmentIntent(Constants.FINISH_LIST);
        startActivity(intent);
    }

    protected void InSaleReturnAddFragment() {
        Intent intent = getFragmentIntent(Constants.IN_SALE_RETURN_ADD);
        startActivity(intent);
    }

    protected void startOutBuyReturnAddFragment() {
        Intent intent = getFragmentIntent(Constants.OUT_BUY_RETURN_ADD);
        startActivity(intent);
    }

    protected void startTransferListFragment() {
        Intent intent = getFragmentIntent(Constants.TRANSFER_LIST);
        startActivity(intent);
    }

    protected void startTransferApplyListFragment(String id, String type) {
        Intent intent = getFragmentIntent(Constants.TRANSFER_APPLY_LIST);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.DETAIL_TYPE, type);
        startActivity(intent);
    }

    protected void startCompleteChooseFragment(String id, String type) {
        Intent intent = getFragmentIntent(Constants.COMPLETE_CHOOSE);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.DETAIL_TYPE, type);
        startActivity(intent);
    }

    protected void startFinishChooseFragment() {
        Intent intent = getFragmentIntent(Constants.FINISH_CHOOSE);
        startActivity(intent);
    }

    protected void startChooseTransferOutFragment(String id) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_TRANSFER_OUT);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    //设置
    protected void startSettingFragment() {
        Intent intent = getFragmentIntent(Constants.SZ);
        startActivity(intent);
    }

    protected void startSuggestFragment() {
        Intent intent = getFragmentIntent(Constants.SUGGEST);
        startActivity(intent);
    }

    protected void startSaftwareFragment() {
        Intent intent = getFragmentIntent(Constants.SAFTWARE);
        startActivity(intent);
    }

    protected void startPuschaseReturnDetailFragment(ReturnBean bean) {
        Intent intent = getFragmentIntent(Constants.PUSHASE_RETURN_DETAIL);
        intent.putExtra(Constants.BEAN, bean);
        startActivity(intent);
    }

    protected void startInBuyScanFragment(List<ScanInfoBean> list, String detail_id) {
        Intent intent = getFragmentIntent(Constants.SCAN_IN_BUY);
        intent.putExtra(Constants.LIST_DATA, (Serializable) list);
        intent.putExtra(Constants.DETAIL_ID, detail_id);
        startActivity(intent);
    }


    protected void startStockChildFragment(PurchaseBean bean,String childType) {
        Intent intent = getFragmentIntent(Constants.STOCK_CHILD);
        intent.putExtra(Constants.BEAN, bean);
        intent.putExtra(Constants.DETAIL_TYPE, childType);
        startActivity(intent);
    }

    protected void startChooseSaleReturnChildFragment(SaleReturnBean bean) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_SALE_RETURN_CHILD);
        intent.putExtra(Constants.BEAN, bean);
        startActivity(intent);
    }

    protected void startStockFindDetailFragment(WarnTwoBean bean) {
        Intent intent = getFragmentIntent(Constants.STOCK_FIND_DETAIL);
        intent.putExtra(Constants.BEAN, bean);
        startActivity(intent);
    }

    protected void startChooseOutPlanChildFragment(ShipPlanBean bean, String state, String id) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_OUT_PLAN_CHILD);
        intent.putExtra(Constants.BEAN, bean);
        intent.putExtra(Constants.STATE, state);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startTransferApplyChildFragment(TransferUnCompleteBean bean, String state, String mIn) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_TRANFER_APPLY_CHILD);
        intent.putExtra(Constants.BEAN, bean);
        intent.putExtra(Constants.STATE, state);
        intent.putExtra(Constants.DETAIL_TYPE, mIn);
        startActivity(intent);
    }

    protected void startChooseTransferOutChildFragment(TransferOutBean bean, String state) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_TRANFER_OUT_CHILD);
        intent.putExtra(Constants.BEAN, bean);
        intent.putExtra(Constants.STATE, state);
        startActivity(intent);
    }

    protected void startOutSaleAddNewFragment(ShipPlanBean bean) {
        Intent intent = getFragmentIntent(Constants.OUT_SALE_ADD_NEW);
        intent.putExtra(Constants.BEAN, bean);
        startActivity(intent);
    }

    protected void startOutTransferAddNewFragment(TransferUnCompleteBean bean) {
        Intent intent = getFragmentIntent(Constants.OUT_TRANSFER_ADD_NEW);
        intent.putExtra(Constants.BEAN, bean);
        startActivity(intent);
    }

    protected void startChooseBuyReturnChildFragment(OutSaleRtuBean bean, String id,String inReturnType) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_BUY_RETURN_CHILD);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.DETAIL_TYPE, inReturnType);
        intent.putExtra(Constants.BEAN, bean);
        startActivity(intent);
    }

    protected void startChooseProduceChildFragment(ReturnBean bean) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_PRODUCE_CHILD);
        intent.putExtra(Constants.BEAN, bean);
        startActivity(intent);
    }

    protected void startPuschaseReturnEditFragment(ReturnBean bean) {
        Intent intent = getFragmentIntent(Constants.PUSHASE_RETURN_EDIT);
        intent.putExtra(Constants.BEAN, bean);
        startActivity(intent);
    }

    protected void startChooseDepotFragment(String id) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_DEPOT);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startProduceReceiveDetailFragment(String id, String state) {
        Intent intent = getFragmentIntent(Constants.PRODUCE_RECEIVE_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        intent.putExtra(Constants.STATE, state);
        startActivity(intent);
    }

    protected void startOutBuyReturnDetailFragment(String id) {
        Intent intent = getFragmentIntent(Constants.OUT_BUY_RETURN_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startCompleteDetailFragment(String id) {
        Intent intent = getFragmentIntent(Constants.COMPLETE_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startFinishDetailFragment(String id) {
        Intent intent = getFragmentIntent(Constants.FINISH_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startOutSaleDetailFragment(String id) {
        Intent intent = getFragmentIntent(Constants.OUT_SALE_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startChooseGoodsFragment(String id) {
        Intent intent = getFragmentIntent(Constants.CHOOSE_GOODS);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }

    protected void startInBuyDetailFragment(String id) {
        Intent intent = getFragmentIntent(Constants.IN_BUY_DETAIL);
        intent.putExtra(Constants.DETAIL_ID, id);
        startActivity(intent);
    }


    //--------------------------------


    /**
     * 隐藏键盘
     */
    protected void hideInputMethod() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        View v = getActivity().getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
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
