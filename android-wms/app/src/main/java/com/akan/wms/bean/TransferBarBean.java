package com.akan.wms.bean;

import java.io.Serializable;

public class TransferBarBean implements Serializable{


    /**
     * item_bar : 条码
     * qty : 数量
     * item_id : 料品id
     * item_code : 编号
     * item_name : 名称
     * item_spec : 规格
     */

    private String item_bar;
    private String qty;
    private String item_id;
    private String item_code;
    private String item_name;
    private String item_spec;

    public String getItem_bar() {
        return item_bar;
    }

    public void setItem_bar(String item_bar) {
        this.item_bar = item_bar;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_spec() {
        return item_spec;
    }

    public void setItem_spec(String item_spec) {
        this.item_spec = item_spec;
    }
}
