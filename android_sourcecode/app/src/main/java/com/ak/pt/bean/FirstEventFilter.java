package com.ak.pt.bean;

/**
 * Created by sh-lx on 2017/6/7.
 */

public class FirstEventFilter {
    private String msg;

    private FilterBean filterBean;

    public FirstEventFilter(String msg, FilterBean filterBean) {
        this.msg = msg;
        this.filterBean = filterBean;
    } public FirstEventFilter(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public FilterBean getFilterBean() {
        return filterBean;
    }

    public void setFilterBean(FilterBean filterBean) {
        this.filterBean = filterBean;
    }
}
