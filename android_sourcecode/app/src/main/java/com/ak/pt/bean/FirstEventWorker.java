package com.ak.pt.bean;

/**
 * Created by sh-lx on 2017/6/7.
 */

public class FirstEventWorker {
    private String msg;

    private WorkerBean filterBean;
    private DepartmentBean mDepartmentBean;

    public DepartmentBean getmDepartmentBean() {
        return mDepartmentBean;
    }

    public void setmDepartmentBean(DepartmentBean mDepartmentBean) {
        this.mDepartmentBean = mDepartmentBean;
    }

    public FirstEventWorker(String msg, WorkerBean filterBean) {
        this.msg = msg;
        this.filterBean = filterBean;
    }

    public FirstEventWorker(String msg, DepartmentBean mDepartmentBean) {
        this.msg = msg;
        this.mDepartmentBean = mDepartmentBean;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public WorkerBean getFilterBean() {
        return filterBean;
    }

    public void setFilterBean(WorkerBean filterBean) {
        this.filterBean = filterBean;
    }
}
