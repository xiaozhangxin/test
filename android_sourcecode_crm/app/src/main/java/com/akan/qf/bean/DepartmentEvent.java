package com.akan.qf.bean;

/**
 * Created by sh-lx on 2017/6/7.
 */

public class DepartmentEvent {
    private String mMsg;

    public DepartmentEvent(String mMsg) {
        this.mMsg = mMsg;
    }

    private DepartmentBean mBean;
    private LableBean lableBean;

    public LableBean getLableBean() {
        return lableBean;
    }

    public DepartmentEvent(String msg, LableBean lableBean) {
        this.mMsg = msg;
        this.lableBean = lableBean;
    }

    public void setLableBean(LableBean lableBean) {
        this.lableBean = lableBean;
    }

    public DepartmentEvent(String msg, DepartmentBean bean) {
        mMsg = msg;
        mBean = bean;

    }

    public String getmMsg() {
        return mMsg;
    }

    public void setmMsg(String mMsg) {
        this.mMsg = mMsg;
    }

    public DepartmentBean getmBean() {
        return mBean;
    }

    public void setmBean(DepartmentBean mBean) {
        this.mBean = mBean;
    }
}
