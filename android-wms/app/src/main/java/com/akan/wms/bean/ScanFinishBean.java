package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class ScanFinishBean implements Serializable {
    private List<ProductionOrderBean> list;

    public ScanFinishBean(List<ProductionOrderBean> list) {
        this.list = list;
    }

    public List<ProductionOrderBean> getList() {
        return list;
    }

    public void setList(List<ProductionOrderBean> list) {
        this.list = list;
    }
}
