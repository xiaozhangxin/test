package com.akan.wms.bean;

import java.io.Serializable;

public class ItemWhQohBean implements Serializable{


    /**
     * qoh_id : 1001812310329147
     * org_id : 1001512200010027
     * wh_id : 1001512260168339
     * item_code : 11.11.172.712542—4
     * mark_code :
     * story_qty : 0
     * story_qty_cu : 0
     * edit_time : 2019-04-01 09:35:49
     * item_id : 1001812180515402
     * item_name : 灰黄色PPR给水管S2.5(AG系列)
     * item_spec : Φ25×4.2(4m)
     * wh_name : 内销成品仓-PPR管材
     * item_num :
     * item_sku :
     * wh_code :
     * wh_num : 0
     */

    private String qoh_id;
    private String org_id;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private String wh_id;
    private String item_code;
    private String mark_code;
    private String story_qty;
    private String story_qty_cu;
    private String edit_time;
    private String item_id;
    private String item_name;
    private String item_spec;
    private String wh_name;
    private String item_num;
    private String item_sku;
    private String wh_code;
    private String wh_num;
    private String supplier_code;
    private String supplier_name;
    private String supplier_id;
    private String item_bar;

    public String getItem_bar() {
        return item_bar;
    }

    public void setItem_bar(String item_bar) {
        this.item_bar = item_bar;
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

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    private int num;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getQoh_id() {
        return qoh_id;
    }

    public void setQoh_id(String qoh_id) {
        this.qoh_id = qoh_id;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
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

    public String getMark_code() {
        return mark_code;
    }

    public void setMark_code(String mark_code) {
        this.mark_code = mark_code;
    }

    public String getStory_qty() {
        return story_qty;
    }

    public void setStory_qty(String story_qty) {
        this.story_qty = story_qty;
    }

    public String getStory_qty_cu() {
        return story_qty_cu;
    }

    public void setStory_qty_cu(String story_qty_cu) {
        this.story_qty_cu = story_qty_cu;
    }

    public String getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(String edit_time) {
        this.edit_time = edit_time;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
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

    public String getItem_num() {
        return item_num;
    }

    public void setItem_num(String item_num) {
        this.item_num = item_num;
    }

    public String getItem_sku() {
        return item_sku;
    }

    public void setItem_sku(String item_sku) {
        this.item_sku = item_sku;
    }

    public String getWh_code() {
        return wh_code;
    }

    public void setWh_code(String wh_code) {
        this.wh_code = wh_code;
    }

    public String getWh_num() {
        return wh_num;
    }

    public void setWh_num(String wh_num) {
        this.wh_num = wh_num;
    }
}
