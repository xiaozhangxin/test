package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class ScanBean implements Serializable{
    private List<InforListBean> list;

    public ScanBean(List<InforListBean> list) {
        this.list = list;
    }

    public List<InforListBean> getList() {
        return list;
    }

    public void setList(List<InforListBean> list) {
        this.list = list;
    }
}
