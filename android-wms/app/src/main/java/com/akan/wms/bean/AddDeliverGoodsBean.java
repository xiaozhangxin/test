package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class AddDeliverGoodsBean implements Serializable{

    private String supplier_id;
    private String id;
    private String org_id;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private List<SupplierReceivesBean> supplier_receives;

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public List<SupplierReceivesBean> getSupplier_receives() {
        return supplier_receives;
    }

    public void setSupplier_receives(List<SupplierReceivesBean> supplier_receives) {
        this.supplier_receives = supplier_receives;
    }
}
