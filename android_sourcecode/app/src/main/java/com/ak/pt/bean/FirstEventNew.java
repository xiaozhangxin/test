package com.ak.pt.bean;

/**
 * Created by admin on 2019/5/29.
 */

public class FirstEventNew {
    private String mMsg;
    public MeParentBean mBean;
    private DepartmentBean mDepartBean;

    private String type;//离职人员类型
    private StaffInfoLeaveBean mStaffInfoLeaveBean;//离职申请选择人员信息bean


    public FirstEventNew(String msg, MeParentBean bean) {
        this.mMsg = msg;
        this.mBean = bean;

    }

    public FirstEventNew(String msg, DepartmentBean bean) {
        this.mMsg = msg;
        this.mDepartBean = bean;

    }

    public FirstEventNew(String msg, String type, StaffInfoLeaveBean mStaffInfoLeaveBean) {
        this.mMsg = msg;
        this.type = type;
        this.mStaffInfoLeaveBean = mStaffInfoLeaveBean;

    }


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setmDepartBean(DepartmentBean mDepartBean) {
        this.mDepartBean = mDepartBean;
    }

    public DepartmentBean getmDepartBean() {
        return mDepartBean;
    }

    public void setmBean(MeParentBean mBean) {
        this.mBean = mBean;
    }

    public MeParentBean getmBean() {
        return mBean;
    }

    public void setmMsg(String mMsg) {
        this.mMsg = mMsg;
    }

    public String getMsg() {
        return mMsg;
    }

    public String getmMsg() {
        return mMsg;
    }

    public StaffInfoLeaveBean getmStaffInfoLeaveBean() {
        return mStaffInfoLeaveBean;
    }

    public void setmStaffInfoLeaveBean(StaffInfoLeaveBean mStaffInfoLeaveBean) {
        this.mStaffInfoLeaveBean = mStaffInfoLeaveBean;
    }


}
