package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/23.
 */

public class TwoChooseChildBean implements Serializable{
    private boolean isCheck;
    private String name;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TwoChooseChildBean(String name) {
        this.name = name;
    }
}
