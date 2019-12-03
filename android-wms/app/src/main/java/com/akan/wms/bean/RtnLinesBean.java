package com.akan.wms.bean;

import java.io.Serializable;

public class RtnLinesBean implements Serializable {

    /**
     * id : 1001907190049108
     * item_id : 1001812190210713
     * rtned_gods_id :
     * item_code : 00.01.013.0001
     * item_name : 白色PPR色母(R1610C)
     * item_spec :
     * src_doc_type : 0
     * src_doc_no : 101-WIN-201903-0089
     * rtn_qty : 11
     * rtn_order_qty : 0
     * rtn_ar_qty : 11
     * rtn : 1001907190049107
     * wh_id : 1001512260168343
     * wh_name : 材料仓
     * rtn_line_no : 10
     * org_id : 1001512200010027
     * org_name : null
     * status : 2
     * status_name : null
     */

    private String id;
    private String item_id;
    private String rtned_gods_id;
    private String item_code;
    private String item_name;
    private String item_spec;
    private int src_doc_type;
    private String src_doc_no;
    private double rtn_qty;
    private int send_qty;
    private String rtn;
    private String wh_id;
    private String wh_name;
    private int rtn_line_no;
    private String org_id;
    private String item_bar;
    private String mfc;
    private String mfc_name;
    private int status;

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

    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getRtned_gods_id() {
        return rtned_gods_id;
    }

    public void setRtned_gods_id(String rtned_gods_id) {
        this.rtned_gods_id = rtned_gods_id;
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

    public int getSrc_doc_type() {
        return src_doc_type;
    }

    public void setSrc_doc_type(int src_doc_type) {
        this.src_doc_type = src_doc_type;
    }

    public String getSrc_doc_no() {
        return src_doc_no;
    }

    public void setSrc_doc_no(String src_doc_no) {
        this.src_doc_no = src_doc_no;
    }

    public double getRtn_qty() {
        return rtn_qty;
    }

    public void setRtn_qty(int rtn_qty) {
        this.rtn_qty = rtn_qty;
    }



    public String getRtn() {
        return rtn;
    }

    public void setRtn(String rtn) {
        this.rtn = rtn;
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

    public int getRtn_line_no() {
        return rtn_line_no;
    }

    public void setRtn_line_no(int rtn_line_no) {
        this.rtn_line_no = rtn_line_no;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}