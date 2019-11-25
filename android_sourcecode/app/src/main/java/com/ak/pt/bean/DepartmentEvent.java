package com.ak.pt.bean;

/**
 * Created by admin on 2019/4/16.
 */

public class DepartmentEvent {
    private String mMsg;
    private DepartmentBean mBean;

    public DepartmentEvent(String msg, DepartmentBean bean) {
        // TODO Auto-generated constructor stub
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
