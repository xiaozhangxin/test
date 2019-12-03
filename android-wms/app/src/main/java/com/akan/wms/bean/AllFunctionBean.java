package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class AllFunctionBean implements Serializable{
    public AllFunctionBean(String type, List<String> childList) {
        this.type = type;
        this.childList = childList;
    }
    private String type;
    private  boolean isCkeck;

    public boolean isCkeck() {
        return isCkeck;
    }

    public void setCkeck(boolean ckeck) {
        isCkeck = ckeck;
    }

    private List<String> childList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getChildList() {
        return childList;
    }

    public void setChildList(List<String> childList) {
        this.childList = childList;
    }
}
