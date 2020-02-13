package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class OutSaleRtuBean implements Serializable{


    /**
     * id : 1001907190049122
     * doc_no : RTN1907190003
     * supplier_code : 13003
     * supplier_name : 北京北化高科新技术股份有限公司
     * business_date : 2019-07-19 00:00:00
     * org : 1001512200010027
     * org_name :
     * status : 2
     * status_name : null
     * rtn_memo : null
     * rtn_lines : [{"id":1001907190049123,"item_id":1001812190210713,"rtned_gods_id":"","item_code":"00.01.013.0001","item_name":"白色PPR色母(R1610C)","item_spec":"","src_doc_type":0,"src_doc_no":"101-WIN-201903-0089","rtn_qty":15,"rtn_order_qty":0,"rtn_ar_qty":15,"rtn":1001907190049122,"wh_id":1001512260168343,"wh_name":"材料仓","rtn_line_no":10,"org_id":1001512200010027,"org_name":null,"status":2,"status_name":null}]
     */

    private String id;
    private String doc_no;
    private String supplier_code;
    private String supplier_name;
    private String business_date;
    private String org;
    private String org_name;
    private int status;
    private Object status_name;
    private Object rtn_memo;
    private List<RtnLinesBean> rtn_lines;
    private List<BarBean> barList;

    public List<BarBean> getBarList() {
        return barList;
    }

    public void setBarList(List<BarBean> barList) {
        this.barList = barList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
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

    public String getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(String business_date) {
        this.business_date = business_date;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getStatus_name() {
        return status_name;
    }

    public void setStatus_name(Object status_name) {
        this.status_name = status_name;
    }

    public Object getRtn_memo() {
        return rtn_memo;
    }

    public void setRtn_memo(Object rtn_memo) {
        this.rtn_memo = rtn_memo;
    }

    public List<RtnLinesBean> getRtn_lines() {
        return rtn_lines;
    }

    public void setRtn_lines(List<RtnLinesBean> rtn_lines) {
        this.rtn_lines = rtn_lines;
    }


}
