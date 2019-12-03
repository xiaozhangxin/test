package com.akan.wms.bean;

import java.io.Serializable;

public class WarnHomeBean implements Serializable{


    /**
     * id : 393221
     * org_id : 1001512200010027
     * org_name : 爱康企业集团（上海）
     * wh_id : 1001512260168424
     * wh_name : 内销成品仓-PPR管件
     * item_id : 1001812182487878
     * item_code : 21.14.346.312012
     * item_name : 墨绿色高端外丝三通
     * item_spec : Φ20×1/2
     * ceiling_qty : 1000
     * floor_qty : 10
     * qty : 0
     * status : 1
     * status_name : 过低
     * remain_status : 0
     * remain_status_name : 正常
     * wh_time : 2019-08-08 14:10:38
     * prod_time :
     * warning_time :
     * remain_time : 30
     */

    private int id;
    private long org_id;
    private String org_name;
    private long wh_id;
    private String wh_name;
    private long item_id;
    private String item_code;
    private String item_name;
    private String item_spec;
    private int ceiling_qty;
    private int floor_qty;
    private int qty;
    private int status;
    private String status_name;
    private int remain_status;
    private String remain_status_name;
    private String wh_time;
    private String prod_time;
    private String warning_time;
    private int remain_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public long getWh_id() {
        return wh_id;
    }

    public void setWh_id(long wh_id) {
        this.wh_id = wh_id;
    }

    public String getWh_name() {
        return wh_name;
    }

    public void setWh_name(String wh_name) {
        this.wh_name = wh_name;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
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

    public int getCeiling_qty() {
        return ceiling_qty;
    }

    public void setCeiling_qty(int ceiling_qty) {
        this.ceiling_qty = ceiling_qty;
    }

    public int getFloor_qty() {
        return floor_qty;
    }

    public void setFloor_qty(int floor_qty) {
        this.floor_qty = floor_qty;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public int getRemain_status() {
        return remain_status;
    }

    public void setRemain_status(int remain_status) {
        this.remain_status = remain_status;
    }

    public String getRemain_status_name() {
        return remain_status_name;
    }

    public void setRemain_status_name(String remain_status_name) {
        this.remain_status_name = remain_status_name;
    }

    public String getWh_time() {
        return wh_time;
    }

    public void setWh_time(String wh_time) {
        this.wh_time = wh_time;
    }

    public String getProd_time() {
        return prod_time;
    }

    public void setProd_time(String prod_time) {
        this.prod_time = prod_time;
    }

    public String getWarning_time() {
        return warning_time;
    }

    public void setWarning_time(String warning_time) {
        this.warning_time = warning_time;
    }

    public int getRemain_time() {
        return remain_time;
    }

    public void setRemain_time(int remain_time) {
        this.remain_time = remain_time;
    }
}
