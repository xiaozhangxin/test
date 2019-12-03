package com.akan.wms.bean;

import java.io.Serializable;

/**
 * Created by sh-lx on 2017/6/7.
 */

public class FirstEvent implements Serializable{
    //mMsg = 1 选择组织架构
    //mMsg = 2 选择多个商品
    //mMsg = 3 退出登录
    //mMsg = scan 扫码结果
    //mMsg = scan_over 完工申报扫码结果
    //mMsg = scan_info 完工申报扫码结果
    //mMsg = 4 选择供应商
    //mMsg = 5 选择采购单列表
    //mMsg = 6 选择仓库
    //mMsg = 7 选择收货仓库
    //mMsg = 8 选择单据类型(添加生产领料)
    //mMsg = 9 选择库管员(添加生产领料)
    //mMsg = 10选择使用中心(添加生产领料)
    //mMsg = 11 选择负责部门(添加生产领料)
    //mMsg = 12 采购退货申请单
    //mMsg = 13 销售退货申请单
    //mMsg = 14 盘点单料品
    //mMsg = 15 组织内调拨申请单
    //mMsg = 16 调出单单据类型
    //mMsg = 17 调拨出库单
    //mMsg = 18 生产订单
    //mMsg = 19 操作员
    //mMsg = 20 班组
    //mMsg = 21 模具编号
    //mMsg = 22 出货单单据类型
    //mMsg = 23 选择厂牌
    //mMsg = 24 扫码列表
    private String mMsg;
    private StaffOrgsBean mBean;//组织
    private GoodsListBean goodsListBean;
    private ScanBean mScanBean;
    private SupplierBean mSupplierBean;
    private PurchaseBean mPurchaseBean;
    private WareHouseBean mWareHouseBean;
    private WhBean mWhBean;
    private MiscShipDocTypeBean mMiscShipDocTypeBean;//单据类型
    private OperatorBean mOperatorBean;//库管员
    private DefineValueBean mDefineValueBean;//使用中心
    private StaffGroupBean mStaffGroupBean;//负责部门
    private OutSaleRtuBean mOutSaleRtuBean;
    private SaleReturnBean mSaleReturnBean;
    private GoodsItenBean mGoodsItenBean;
    private WhTransferApplyBean mWhTransferApplyBean;
    private OutTypeLBean mOutTypeLBean;
    private TransferOutBean mTransferOutBean;
    private ProductionOrderBean mProductionOrderBean;

    private OperatorStaffBean mOperatorStaffBean;
    private ClassTeamBean mClassTeamBean;
    private ModNoBean mModNoBean;
    private ScanFinishBean mScanFinishBean;
    private ScanInBuyBean mScanInBuyBean;
    private SaleShipTypeBean mSaleShipTypeBean;
    private MfcBean mMfcBean;
    private ScanListBean mScanListBean;
    private TransferUnCompleteBean mTransferUnCompleteBean;

    public TransferUnCompleteBean getmTransferUnCompleteBean() {
        return mTransferUnCompleteBean;
    }

    public void setmTransferUnCompleteBean(TransferUnCompleteBean mTransferUnCompleteBean) {
        this.mTransferUnCompleteBean = mTransferUnCompleteBean;
    }

    public GoodsItenBean getmGoodsItenBean() {
        return mGoodsItenBean;
    }

    public void setmGoodsItenBean(GoodsItenBean mGoodsItenBean) {
        this.mGoodsItenBean = mGoodsItenBean;
    }

    public SaleReturnBean getmSaleReturnBean() {
        return mSaleReturnBean;
    }

    public void setmSaleReturnBean(SaleReturnBean mSaleReturnBean) {
        this.mSaleReturnBean = mSaleReturnBean;
    }

    public OutSaleRtuBean getmOutSaleRtuBean() {
        return mOutSaleRtuBean;
    }

    public void setmOutSaleRtuBean(OutSaleRtuBean mOutSaleRtuBean) {
        this.mOutSaleRtuBean = mOutSaleRtuBean;
    }

    public MiscShipDocTypeBean getmMiscShipDocTypeBean() {
        return mMiscShipDocTypeBean;
    }

    public void setmMiscShipDocTypeBean(MiscShipDocTypeBean mMiscShipDocTypeBean) {
        this.mMiscShipDocTypeBean = mMiscShipDocTypeBean;
    }

    public OperatorBean getmOperatorBean() {
        return mOperatorBean;
    }

    public void setmOperatorBean(OperatorBean mOperatorBean) {
        this.mOperatorBean = mOperatorBean;
    }


    public DefineValueBean getmDefineValueBean() {
        return mDefineValueBean;
    }

    public void setmDefineValueBean(DefineValueBean mDefineValueBean) {
        this.mDefineValueBean = mDefineValueBean;
    }

    public StaffGroupBean getmStaffGroupBean() {
        return mStaffGroupBean;
    }

    public void setmStaffGroupBean(StaffGroupBean mStaffGroupBean) {
        this.mStaffGroupBean = mStaffGroupBean;
    }

    public FirstEvent(String msg, MiscShipDocTypeBean bean) {
        mMsg = msg;
        mMiscShipDocTypeBean = bean;

    }

    public FirstEvent(String msg, MfcBean bean) {
        mMsg = msg;
        mMfcBean = bean;

    }

    public FirstEvent(String msg, ScanListBean bean) {
        mMsg = msg;
        mScanListBean = bean;

    }

    public ScanListBean getmScanListBean() {
        return mScanListBean;
    }

    public void setmScanListBean(ScanListBean mScanListBean) {
        this.mScanListBean = mScanListBean;
    }

    public MfcBean getmMfcBean() {
        return mMfcBean;
    }

    public void setmMfcBean(MfcBean mMfcBean) {
        this.mMfcBean = mMfcBean;
    }

    public FirstEvent(String msg, GoodsItenBean bean) {
        mMsg = msg;
        mGoodsItenBean = bean;

    }

    public FirstEvent(String msg, OperatorStaffBean bean) {
        mMsg = msg;
        mOperatorStaffBean = bean;

    }

    public FirstEvent(String msg, ScanFinishBean bean) {
        mMsg = msg;
        mScanFinishBean = bean;

    }

    public FirstEvent(String msg, ScanInBuyBean bean) {
        mMsg = msg;
        mScanInBuyBean = bean;

    }   public FirstEvent(String msg, TransferUnCompleteBean bean) {
        mMsg = msg;
        mTransferUnCompleteBean = bean;

    }

    public ScanInBuyBean getmScanInBuyBean() {
        return mScanInBuyBean;
    }

    public void setmScanInBuyBean(ScanInBuyBean mScanInBuyBean) {
        this.mScanInBuyBean = mScanInBuyBean;
    }

    public ScanFinishBean getmScanFinishBean() {
        return mScanFinishBean;
    }

    public void setmScanFinishBean(ScanFinishBean mScanFinishBean) {
        this.mScanFinishBean = mScanFinishBean;
    }

    public FirstEvent(String msg, ClassTeamBean bean) {
        mMsg = msg;
        mClassTeamBean = bean;

    }

    public FirstEvent(String msg, ModNoBean bean) {
        mMsg = msg;
        mModNoBean = bean;

    }

    public OperatorStaffBean getmOperatorStaffBean() {
        return mOperatorStaffBean;
    }

    public void setmOperatorStaffBean(OperatorStaffBean mOperatorStaffBean) {
        this.mOperatorStaffBean = mOperatorStaffBean;
    }

    public ClassTeamBean getmClassTeamBean() {
        return mClassTeamBean;
    }

    public void setmClassTeamBean(ClassTeamBean mClassTeamBean) {
        this.mClassTeamBean = mClassTeamBean;
    }

    public ModNoBean getmModNoBean() {
        return mModNoBean;
    }

    public void setmModNoBean(ModNoBean mModNoBean) {
        this.mModNoBean = mModNoBean;
    }

    public OutTypeLBean getmOutTypeLBean() {
        return mOutTypeLBean;
    }

    public void setmOutTypeLBean(OutTypeLBean mOutTypeLBean) {
        this.mOutTypeLBean = mOutTypeLBean;
    }

    public TransferOutBean getmTransferOutBean() {
        return mTransferOutBean;
    }

    public void setmTransferOutBean(TransferOutBean mTransferOutBean) {
        this.mTransferOutBean = mTransferOutBean;
    }

    public FirstEvent(String msg, WhTransferApplyBean bean) {
        mMsg = msg;
        mWhTransferApplyBean = bean;

    }

    public FirstEvent(String msg, OutTypeLBean bean) {
        mMsg = msg;
        mOutTypeLBean = bean;

    }

    public FirstEvent(String msg, SaleShipTypeBean bean) {
        mMsg = msg;
        mSaleShipTypeBean = bean;

    }

    public FirstEvent(String msg, TransferOutBean bean) {
        mMsg = msg;
        mTransferOutBean = bean;

    }

    public SaleShipTypeBean getmSaleShipTypeBean() {
        return mSaleShipTypeBean;
    }

    public void setmSaleShipTypeBean(SaleShipTypeBean mSaleShipTypeBean) {
        this.mSaleShipTypeBean = mSaleShipTypeBean;
    }

    public FirstEvent(String msg, ProductionOrderBean bean) {
        mMsg = msg;
        mProductionOrderBean = bean;

    }

    public ProductionOrderBean getmProductionOrderBean() {
        return mProductionOrderBean;
    }

    public void setmProductionOrderBean(ProductionOrderBean mProductionOrderBean) {
        this.mProductionOrderBean = mProductionOrderBean;
    }

    public WhTransferApplyBean getmWhTransferApplyBean() {
        return mWhTransferApplyBean;
    }

    public void setmWhTransferApplyBean(WhTransferApplyBean mWhTransferApplyBean) {
        this.mWhTransferApplyBean = mWhTransferApplyBean;
    }

    public FirstEvent(String msg, OutSaleRtuBean bean) {
        mMsg = msg;
        mOutSaleRtuBean = bean;

    }

    public FirstEvent(String msg, SaleReturnBean bean) {
        mMsg = msg;
        mSaleReturnBean = bean;

    }

    public FirstEvent(String msg, OperatorBean bean) {
        mMsg = msg;
        mOperatorBean = bean;

    }

    public FirstEvent(String msg, DefineValueBean bean) {
        mMsg = msg;
        mDefineValueBean = bean;

    }

    public FirstEvent(String msg, StaffGroupBean bean) {
        mMsg = msg;
        mStaffGroupBean = bean;

    }

//--------------------------------------------------------------------------------

    public FirstEvent(String msg) {
        mMsg = msg;
    }

    public String getmMsg() {
        return mMsg;
    }


    public String getMsg() {
        return mMsg;
    }

    public void setmMsg(String mMsg) {
        this.mMsg = mMsg;
    }


    //----------------------------------选择组织------------------------------------------------
    public FirstEvent(String msg, StaffOrgsBean bean) {
        mMsg = msg;
        mBean = bean;
    }

    public StaffOrgsBean getmBean() {
        return mBean;
    }

    public void setmBean(StaffOrgsBean mBean) {
        this.mBean = mBean;
    }




    //----------------------------------选择商品------------------------------------------------


    public FirstEvent(String msg, GoodsListBean bean) {
        mMsg = msg;
        goodsListBean = bean;
    }

    public GoodsListBean getGoodsListBean() {
        return goodsListBean;
    }

    public void setGoodsListBean(GoodsListBean goodsListBean) {
        this.goodsListBean = goodsListBean;
    }
    //----------------------------------扫码结果商品------------------------------------------------

    public FirstEvent(String msg, ScanBean bean) {
        mMsg = msg;
        mScanBean = bean;
    }


    public ScanBean getmScanBean() {
        return mScanBean;
    }

    public void setmScanBean(ScanBean mScanBean) {
        this.mScanBean = mScanBean;
    }
    //----------------------------------供应商------------------------------------------------

    public FirstEvent(String msg, SupplierBean bean) {
        mMsg = msg;
        mSupplierBean = bean;
    }

    public SupplierBean getmSupplierBean() {
        return mSupplierBean;
    }

    public void setmSupplierBean(SupplierBean mSupplierBean) {
        this.mSupplierBean = mSupplierBean;
    }
    //---------------------------------------------------------------------------------------

    public FirstEvent(String msg, PurchaseBean bean) {

        mMsg = msg;
        mPurchaseBean = bean;
    }

    public PurchaseBean getmPurchaseBean() {
        return mPurchaseBean;
    }

    public void setmPurchaseBean(PurchaseBean mPurchaseBean) {
        this.mPurchaseBean = mPurchaseBean;
    }

    //--------------------------------------------------------------------------------------

    public FirstEvent(String msg, WareHouseBean bean) {
        mMsg = msg;
        mWareHouseBean = bean;
    }

    public WareHouseBean getmWareHouseBean() {
        return mWareHouseBean;
    }

    public void setmWareHouseBean(WareHouseBean mWareHouseBean) {
        this.mWareHouseBean = mWareHouseBean;
    }

    //----------------------------------------------------------------------------------

    public WhBean getmWhBean() {
        return mWhBean;
    }

    public void setmWhBean(WhBean mWhBean) {
        this.mWhBean = mWhBean;
    }

    public FirstEvent(String msg, WhBean bean) {
        mMsg = msg;
        mWhBean = bean;
    }

    //----------------------------------------------------------------------------------


}
