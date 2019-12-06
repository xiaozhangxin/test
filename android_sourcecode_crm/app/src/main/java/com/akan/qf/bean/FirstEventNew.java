package com.akan.qf.bean;

/**
 * Created by sh-lx on 2017/6/7.
 */

public class FirstEventNew {
    private String mMsg;

    public FirstEventNew(String msg, MeParentBean bean) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
        mBean = bean;

    }

    public String getMsg() {
        return mMsg;
    }

    public MeParentBean mBean;

    public MeParentBean getmBean() {
        return mBean;
    }


}
