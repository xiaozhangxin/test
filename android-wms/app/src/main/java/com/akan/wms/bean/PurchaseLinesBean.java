package com.akan.wms.bean;

import java.io.Serializable;

public class PurchaseLinesBean implements Serializable {

    /**
     * id : 1001812270017738
     * pur_id : 1001812270017730
     * line_no : 10
     * item_id : 1001812181119014
     * item_name : 灰黄色暗阀(AG系列)
     * pur_qty : 350
     * receive_qty : 0
     * un_receive_qty : {}
     * rtn_deduct_qty : 0
     * rtn_fill_qty : 0
     * status : 2
     * description :
     * item_spec : Φ25
     * send_qty : null
     */

    private long id;
    private long pur_id;
    private int line_no;
    private String item_id;
    private String item_name;
    private double pur_qty;
    private double perOfOvertopQty;
    private String wh_name;
    private String wh_id;
    private String mfc;

    public double getPerOfOvertopQty() {
        return perOfOvertopQty;
    }

    public void setPerOfOvertopQty(double perOfOvertopQty) {
        this.perOfOvertopQty = perOfOvertopQty;
    }

    private String mfc_name;

    public String getMfc() {
        return mfc;
    }

    public void setMfc(String mfc) {
        this.mfc = mfc;
    }

    public String getMfc_name() {
        return mfc_name;
    }

    public void setMfc_name(String mfc_name) {
        this.mfc_name = mfc_name;
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


    public int getSend_qty() {
        return send_qty;
    }

    public void setSend_qty(int send_qty) {
        this.send_qty = send_qty;
    }

    private int send_qty;
    private double receive_qty;
    private int rtn_deduct_qty;
    private int rtn_fill_qty;
    private int status;
    private String description;
    private String item_spec;
    private String wh_manager;
    private String wh_manager_id;

    public String getWh_manager_id() {
        return wh_manager_id;
    }

    public void setWh_manager_id(String wh_manager_id) {
        this.wh_manager_id = wh_manager_id;
    }

    public String getWh_manager() {

        return wh_manager;
    }

    public void setWh_manager(String wh_manager) {
        this.wh_manager = wh_manager;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private boolean isCheck;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPur_id() {
        return pur_id;
    }

    public void setPur_id(long pur_id) {
        this.pur_id = pur_id;
    }

    public int getLine_no() {
        return line_no;
    }

    public void setLine_no(int line_no) {
        this.line_no = line_no;
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

    public double getPur_qty() {
        return pur_qty;
    }

    public void setPur_qty(double pur_qty) {
        this.pur_qty = pur_qty;
    }

    public double getReceive_qty() {
        return receive_qty;
    }

    public void setReceive_qty(double receive_qty) {
        this.receive_qty = receive_qty;
    }


    public int getRtn_deduct_qty() {
        return rtn_deduct_qty;
    }

    public void setRtn_deduct_qty(int rtn_deduct_qty) {
        this.rtn_deduct_qty = rtn_deduct_qty;
    }

    public int getRtn_fill_qty() {
        return rtn_fill_qty;
    }

    public void setRtn_fill_qty(int rtn_fill_qty) {
        this.rtn_fill_qty = rtn_fill_qty;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItem_spec() {
        return item_spec;
    }

    public void setItem_spec(String item_spec) {
        this.item_spec = item_spec;
    }


    public static class UnReceiveQtyBean {
    }
}