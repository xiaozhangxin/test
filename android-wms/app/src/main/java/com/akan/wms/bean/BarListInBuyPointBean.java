package com.akan.wms.bean;

import java.io.Serializable;

public class BarListInBuyPointBean implements Serializable {


    /**
     * pur_id : 1001812190218597
     * item_spec : &2
     * item_code : 00.01.023.0008
     * item_name : PERT银灰色母
     * item_id : 1001812190218597
     * item_bar : 00.01.023.0008
     * qty : 30
     */

    private String pur_id;
    private String item_spec;
    private String item_code;
    private String item_name;
    private String item_id;

    public String getWh_id() {
        return wh_id;
    }

    public void setWh_id(String wh_id) {
        this.wh_id = wh_id;
    }

    private String item_bar;
    private String wh_id;
    private String qty;

    public String getPur_id() {
        return pur_id;
    }

    public void setPur_id(String pur_id) {
        this.pur_id = pur_id;
    }

    public String getItem_spec() {
        return item_spec;
    }

    public void setItem_spec(String item_spec) {
        this.item_spec = item_spec;
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

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

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
}
