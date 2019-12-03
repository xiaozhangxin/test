package com.akan.wms.bean;

import java.io.Serializable;

public class OutSaleLineBean implements Serializable {


    /**
     * item_id : 料品
     * qty : 数量
     * wh_id : 仓库
     * plan_line_id : 出货计划行id
     */

    private String item_id;
    private String qty;
    private String wh_id;
    private String plan_line_id;
    private String wh_name;
    private String apply_line_id;
    private String out_line_id;

    public String getOut_line_id() {
        return out_line_id;
    }

    public void setOut_line_id(String out_line_id) {
        this.out_line_id = out_line_id;
    }

    public String getWh_name() {
        return wh_name;
    }

    public void setWh_name(String wh_name) {
        this.wh_name = wh_name;
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

    public String getWh_id() {
        return wh_id;
    }

    public void setWh_id(String wh_id) {
        this.wh_id = wh_id;
    }

    public String getPlan_line_id() {
        return plan_line_id;
    }

    public void setPlan_line_id(String plan_line_id) {
        this.plan_line_id = plan_line_id;
    }
}
