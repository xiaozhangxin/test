package com.akan.wms.bean;

import java.io.Serializable;

public class FirstEventTwo implements Serializable{
    //mMsg = 1 销售出库单类型
    //mMsg = 2 选择出货计划
    private String mMsg;
    private SaleShipTypeBean mBean;
    private ShipPlanBean mShipPlanBean;
    private WareHouseBean mWareHouseBean;
    private ScanInBuyBean mScanInBuyBean;
    private ScanListBean mScanListBean;

    public ScanListBean getmScanListBean() {
        return mScanListBean;
    }

    public void setmScanListBean(ScanListBean mScanListBean) {
        this.mScanListBean = mScanListBean;
    }

    public FirstEventTwo(String mMsg) {
        this.mMsg = mMsg;

    }

    public FirstEventTwo(String mMsg, SaleShipTypeBean mBean) {
        this.mMsg = mMsg;
        this.mBean = mBean;
    }

    public FirstEventTwo(String msg, ScanListBean bean) {
        mMsg = msg;
        mScanListBean = bean;

    }

    public ScanInBuyBean getmScanInBuyBean() {
        return mScanInBuyBean;
    }

    public void setmScanInBuyBean(ScanInBuyBean mScanInBuyBean) {
        this.mScanInBuyBean = mScanInBuyBean;
    }

    public FirstEventTwo(String msg, ScanInBuyBean bean) {
        mMsg = msg;
        mScanInBuyBean = bean;

    }

    public FirstEventTwo(String mMsg, ShipPlanBean mBean) {
        this.mMsg = mMsg;
        this.mShipPlanBean = mBean;
    }

    public ShipPlanBean getmShipPlanBean() {
        return mShipPlanBean;
    }

    public void setmShipPlanBean(ShipPlanBean mShipPlanBean) {
        this.mShipPlanBean = mShipPlanBean;
    }

    public String getmMsg() {
        return mMsg;
    }

    public void setmMsg(String mMsg) {
        this.mMsg = mMsg;
    }

    public SaleShipTypeBean getmBean() {
        return mBean;
    }

    public void setmBean(SaleShipTypeBean mBean) {
        this.mBean = mBean;
    }

    public FirstEventTwo(String msg, WareHouseBean bean) {
        mMsg = msg;
        mWareHouseBean = bean;
    }

    public WareHouseBean getmWareHouseBean() {
        return mWareHouseBean;
    }

    public void setmWareHouseBean(WareHouseBean mWareHouseBean) {
        this.mWareHouseBean = mWareHouseBean;
    }


}
