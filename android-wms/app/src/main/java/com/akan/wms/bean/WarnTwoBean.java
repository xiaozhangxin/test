package com.akan.wms.bean;

import java.io.Serializable;

public class WarnTwoBean implements Serializable {


    /**
     * id : 138766
     * org_id : 1001512200010027
     * org_name :
     * wh_id : 1001512260168339
     * wh_name : 内销成品仓-PPR管材
     * item_id : 1001812180389891
     * item_code : 11.11.101.302542—3(00)
     * item_name : 绿色PPR双色管(内本色)S2.5
     * item_spec : Φ25×4.2(3m)
     * ceiling_qty : 1000
     * floor_qty : 10
     * qty : 45300
     * status : 2
     * status_name : 过高
     * remain_status : 1
     * remain_status_name : 呆滞
     * wh_time : 2019-07-12 16:36:55
     * remain_time : 2019-08-01 10:52:46
     */

    private String id;
    private String org_id;
    private String org_name;
    private String wh_id;
    private String wh_name;
    private String item_id;
    private String item_code;
    private String item_name;
    private String item_spec;
    private String ceiling_qty;
    private String floor_qty;
    private double qty;
    private String status;
    private String status_name;
    private String remain_status;
    private String remain_status_name;

    public String getWarning_time() {
        return warning_time;
    }

    public void setWarning_time(String warning_time) {
        this.warning_time = warning_time;
    }

    private String warning_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getWh_id() {
        return wh_id;
    }

    public void setWh_id(String wh_id) {
        this.wh_id = wh_id;
    }

    public String getWh_name() {
        return wh_name;
    }

    public void setWh_name(String wh_name) {
        this.wh_name = wh_name;
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

    public String getCeiling_qty() {
        return ceiling_qty;
    }

    public void setCeiling_qty(String ceiling_qty) {
        this.ceiling_qty = ceiling_qty;
    }

    public String getFloor_qty() {
        return floor_qty;
    }

    public void setFloor_qty(String floor_qty) {
        this.floor_qty = floor_qty;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getRemain_status() {
        return remain_status;
    }

    public void setRemain_status(String remain_status) {
        this.remain_status = remain_status;
    }

    public String getRemain_status_name() {
        return remain_status_name;
    }

    public void setRemain_status_name(String remain_status_name) {
        this.remain_status_name = remain_status_name;
    }



}
