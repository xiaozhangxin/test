package com.akan.wms.bean;

import java.io.Serializable;

public class TransferLineListBean implements Serializable{


    /**
     * apply_line_id : 调出申请单行
     * item_id : 料品
     * qty : 调出数量
     */

    private String apply_line_id;
    private String item_id;
    private String qty;
    private String supplier_id;
    private String supplier_code;
    private String supplier_name;

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_code() {
        return supplier_code;
    }

    public void setSupplier_code(String supplier_code) {
        this.supplier_code = supplier_code;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getApply_line_id() {
        return apply_line_id;
    }

    public void setApply_line_id(String apply_line_id) {
        this.apply_line_id = apply_line_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
