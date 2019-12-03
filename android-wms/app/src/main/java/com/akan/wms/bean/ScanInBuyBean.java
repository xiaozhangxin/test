package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class ScanInBuyBean  implements Serializable {
    private List<ScanInfoBean> list;

    public ScanInBuyBean(List<ScanInfoBean> list) {
        this.list = list;
    }

    public List<ScanInfoBean> getList() {
        return list;
    }

    public void setList(List<ScanInfoBean> list) {
        this.list = list;
    }
}
