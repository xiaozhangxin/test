package com.akan.wms.bean;

import java.io.Serializable;

public class BarListInTransferBean implements Serializable {


    /**
     * item_id : 1001812180389892
     * qty : 10
     * item_name : 绿色PPR双色管(内本色)S2.5
     * wh_id : 1001512260168339
     * item_spec : Φ25×4.2(3m)
     * wh_name : 内销成品仓-PPR管材
     * item_bar : 16738091273499406538
     * item_code : 11.11.101.302542—3(00)
     */

    private String item_id;
    private String qty;
    private String item_name;
    private String wh_id;
    private String item_spec;
    private String wh_name;
    private String item_bar;
    private String item_code;

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

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getWh_id() {
        return wh_id;
    }

    public void setWh_id(String wh_id) {
        this.wh_id = wh_id;
    }

    public String getItem_spec() {
        return item_spec;
    }

    public void setItem_spec(String item_spec) {
        this.item_spec = item_spec;
    }

    public String getWh_name() {
        return wh_name;
    }

    public void setWh_name(String wh_name) {
        this.wh_name = wh_name;
    }

    public String getItem_bar() {
        return item_bar;
    }

    public void setItem_bar(String item_bar) {
        this.item_bar = item_bar;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }
}
