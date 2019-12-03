package com.akan.wms.bean;

import java.util.List;

public class ScanListBean extends MiscShipDocTypeBean {
    private List<BarBean> list;

    public ScanListBean(List<BarBean> list) {
        this.list = list;
    }

    public List<BarBean> getList() {
        return list;
    }

    public void setList(List<BarBean> list) {
        this.list = list;
    }
}
