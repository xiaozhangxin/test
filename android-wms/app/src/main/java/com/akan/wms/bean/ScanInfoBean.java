package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class ScanInfoBean implements Serializable{


    /**
     * item_bar : 条码
     * wh_id : 仓库
     * qty : 数量
     * item_id : 料品id
     * item_code : 编号
     * item_name : 名称
     * item_spec : 规格
     */

    private String item_bar;
    private String wh_id;
    private String item_id;
    private String item_code;
    private String item_name;
    private String item_spec;
    private String bar_code;//条码

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    private int qty;
    private int arrive_qty;//采购入库实收数量
    private int send_qty;//采购入库送货数量
    private int in_qty;//采购入库同意数量
    private int apply_qty;//退料申请数量
    private int sure_qty;//退料核定数量
    private String info_id;//退料id

    private List<BarBean> barList;

    public List<BarBean> getBarList() {
        return barList;
    }

    public void setBarList(List<BarBean> barList) {
        this.barList = barList;
    }

    public int getApply_qty() {
        return apply_qty;
    }

    public void setApply_qty(int apply_qty) {
        this.apply_qty = apply_qty;
    }

    public int getSure_qty() {
        return sure_qty;
    }

    public void setSure_qty(int sure_qty) {
        this.sure_qty = sure_qty;
    }

    public int getIn_qty() {
        return in_qty;
    }

    public void setIn_qty(int in_qty) {
        this.in_qty = in_qty;
    }

    public int getArrive_qty() {
        return arrive_qty;
    }

    public void setArrive_qty(int arrive_qty) {
        this.arrive_qty = arrive_qty;
    }
    public String getItem_bar() {
        return item_bar;
    }

    public void setItem_bar(String item_bar) {
        this.item_bar = item_bar;
    }

    public int getSend_qty() {
        return send_qty;
    }

    public void setSend_qty(int send_qty) {
        this.send_qty = send_qty;
    }

    public String getWh_id() {
        return wh_id;

    }

    public void setWh_id(String wh_id) {
        this.wh_id = wh_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
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
