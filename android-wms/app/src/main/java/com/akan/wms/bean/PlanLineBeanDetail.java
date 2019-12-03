package com.akan.wms.bean;

import java.io.Serializable;

public class PlanLineBeanDetail implements Serializable{


    /**
     * id : 3
     * u9_id : 
     * sale_ship_id : 3
     * plan_line_id : 1001901020011693
     * item_id : 1001812181474364
     * qty : 0
     * wh_id : 1001512260168575
     * item_code : 11.52.220.10150
     * item_name : 白色PVC加长伸缩节
     * item_spec : Φ50
     * wh_name : 内销成品仓-PVC管件
     * plan_qty : 20
     */

    private String id;
    private String u9_id;
    private String sale_ship_id;
    private String plan_line_id;
    private String item_id;
    private int qty;
    private String wh_id;
    private String item_code;
    private String item_name;
    private String item_spec;
    private String wh_name;
    private String plan_qty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getU9_id() {
        return u9_id;
    }

    public void setU9_id(String u9_id) {
        this.u9_id = u9_id;
    }

    public String getSale_ship_id() {
        return sale_ship_id;
    }

    public void setSale_ship_id(String sale_ship_id) {
        this.sale_ship_id = sale_ship_id;
    }

    public String getPlan_line_id() {
        return plan_line_id;
    }

    public void setPlan_line_id(String plan_line_id) {
        this.plan_line_id = plan_line_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getWh_id() {
        return wh_id;
    }

    public void setWh_id(String wh_id) {
        this.wh_id = wh_id;
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

    public String getWh_name() {
        return wh_name;
    }

    public void setWh_name(String wh_name) {
        this.wh_name = wh_name;
    }

    public String getPlan_qty() {
        return plan_qty;
    }

    public void setPlan_qty(String plan_qty) {
        this.plan_qty = plan_qty;
    }
}
