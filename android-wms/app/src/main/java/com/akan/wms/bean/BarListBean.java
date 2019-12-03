package com.akan.wms.bean;

import java.io.Serializable;

public class BarListBean implements Serializable {


    /**
     * item_id : 料品
     * item_bar : 料品条码
     * qty : 条码对应的数量
     * wh_id : 仓库
     */

    private String item_id;
    private String item_bar;
    private String qty;
    private String wh_id;

    private String item_code;
    private String item_name;
    private String item_spec;
    private String code;
    private String name;
    private String bar_code;
    private String rtn_id;//采购退货添加
    private String pur_id;//采购入库同意
    private String in_qty;//入库扫码数量

    public String getIn_qty() {
        return in_qty;
    }

    public void setIn_qty(String in_qty) {
        this.in_qty = in_qty;
    }

    public String getPur_id() {
        return pur_id;
    }

    public void setPur_id(String pur_id) {
        this.pur_id = pur_id;
    }

    public String getRtn_id() {
        return rtn_id;
    }

    public void setRtn_id(String rtn_id) {
        this.rtn_id = rtn_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
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

    public String getWh_id() {
        return wh_id;
    }

    public void setWh_id(String wh_id) {
        this.wh_id = wh_id;
    }
}
