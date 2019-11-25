package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/23.
 */

public class TwoChooseBean implements Serializable{

    private String name;
    private List<TwoChooseChildBean> childList;
    private  boolean isCheck;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TwoChooseChildBean> getChildList() {
        return childList;
    }

    public void setChildList(List<TwoChooseChildBean> childList) {
        this.childList = childList;
    }

    public TwoChooseBean(String name, List<TwoChooseChildBean> childList) {
        this.name = name;
        this.childList = childList;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
