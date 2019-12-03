package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class ReturnBean implements Serializable {
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private List<ReturnNumBean>  returnNumBeanList;

    public List<ReturnNumBean> getReturnNumBeanList() {
        return returnNumBeanList;
    }

    public void setReturnNumBeanList(List<ReturnNumBean> returnNumBeanList) {
        this.returnNumBeanList = returnNumBeanList;
    }
}
