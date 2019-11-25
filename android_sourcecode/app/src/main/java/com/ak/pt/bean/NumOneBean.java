package com.ak.pt.bean;

/**
 * Created by admin on 2019/1/21.
 */

public class NumOneBean {
    public NumOneBean(String kgAll, boolean isCheck) {
        this.kgAll = kgAll;
        this.isCheck = isCheck;
    }

    String kgAll;
    boolean isCheck;

    public String getKgAll() {
        return kgAll;
    }

    public void setKgAll(String kgAll) {
        this.kgAll = kgAll;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
