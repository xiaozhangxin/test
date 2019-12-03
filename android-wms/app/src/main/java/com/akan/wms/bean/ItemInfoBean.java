package com.akan.wms.bean;

import java.io.Serializable;

public class ItemInfoBean implements Serializable{


    /**
     * item_id : 1001812180044580
     * item_code : 00.01.011.0002
     * item_name : PPR主料—绿色混配料
     * item_spec :
     * item_category_id : 1001812140011990
     * trade_mark : {}
     * item_sku : 公斤
     * weight : 0
     * item_form : 9
     * itemWhBean : {"id":1001812180044700,"org_id":1001512200010027,"item_id":1001812180044580,"wh_id":{},"whList":[{"warehouse_id":1001512260168343,"warehouse_name":"材料仓","warehouse_code":"11","org_id":{},"is_effective":"0"}]}
     * org_id : {}
     * type :
     * all_select :
     * is_effective : 1
     */

    private String item_id;
    private String item_code;
    private String item_name;
    private String item_spec;
    private String item_category_id;
    private String item_sku;
    private String weight;
    private String item_form;
    private ItemWhBean itemWhBean;
    private String type;
    private String all_select;
    private String is_effective;
    private String num;
    private String warehouse_name;
    private String mark_code;

    public String getTrade_mark_name() {
        return trade_mark_name;
    }

    public void setTrade_mark_name(String trade_mark_name) {
        this.trade_mark_name = trade_mark_name;
    }

    public String getTrade_mark() {
        return trade_mark;
    }

    public void setTrade_mark(String trade_mark) {
        this.trade_mark = trade_mark;
    }

    private String mark_id;
    private String trade_mark_name;
    private String trade_mark;

    public String getMark_id() {
        return mark_id;
    }

    public void setMark_id(String mark_id) {
        this.mark_id = mark_id;
    }

    private String mark_name;

    public String getMark_name() {
        return mark_name;
    }

    public void setMark_name(String mark_name) {
        this.mark_name = mark_name;
    }

    public String getMark_code() {
        return mark_code;
    }

    public void setMark_code(String mark_code) {
        this.mark_code = mark_code;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    private String warehouse_id;

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getItem_id() {
        return item_id;
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

    public String getItem_category_id() {
        return item_category_id;
    }

    public void setItem_category_id(String item_category_id) {
        this.item_category_id = item_category_id;
    }



    public String getItem_sku() {
        return item_sku;
    }

    public void setItem_sku(String item_sku) {
        this.item_sku = item_sku;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getItem_form() {
        return item_form;
    }

    public void setItem_form(String item_form) {
        this.item_form = item_form;
    }

    public ItemWhBean getItemWhBean() {
        return itemWhBean;
    }

    public void setItemWhBean(ItemWhBean itemWhBean) {
        this.itemWhBean = itemWhBean;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAll_select() {
        return all_select;
    }

    public void setAll_select(String all_select) {
        this.all_select = all_select;
    }

    public String getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(String is_effective) {
        this.is_effective = is_effective;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }


}
