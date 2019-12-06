package com.akan.qf.bean;

/**
 * Created by admin on 2018/11/15.
 */

public class CkeckBean {
    private String name;
    private boolean isCkeck;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCkeck() {
        return isCkeck;
    }

    public void setCkeck(boolean ckeck) {
        isCkeck = ckeck;
    }

    public CkeckBean(String name, boolean isCkeck) {
        this.name = name;
        this.isCkeck = isCkeck;
    }
}
