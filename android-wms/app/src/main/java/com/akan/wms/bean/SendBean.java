package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class SendBean implements Serializable {

    private boolean isHave;
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public boolean isHave() {
        return isHave;
    }

    public void setHave(boolean have) {
        isHave = have;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
