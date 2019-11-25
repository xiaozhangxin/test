package com.ak.pt.bean;

/**
 * Created by admin on 2019/6/11.
 */

public class NumOldBean {
    private boolean isCheck;
    private String  num;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public NumOldBean(String num) {
        this.num = num;
    }
}
