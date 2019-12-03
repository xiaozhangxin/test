package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class PurchaseBean implements Serializable{


    /**
     * id : 1001812270017730
     * doc_no : 101-AKPO-201804-00242
     * doc_type : 1001512205300214
     * supplier_id : 12009
     * business_date : 2019-01-01 00:00:00
     * description :
     * doc_status : 0
     * org_id : 1001512200010027
     * purchase_lines : [{"id":1001812270017738,"pur_id":1001812270017730,"line_no":10,"item_id":1001812181119014,"item_name":"灰黄色暗阀(AG系列)","pur_qty":350,"receive_qty":0,"un_receive_qty":{},"rtn_deduct_qty":0,"rtn_fill_qty":0,"status":2,"description":"","item_spec":"Φ25","send_qty":null}]
     * tag : 1
     */

    private long id;
    private String doc_no;
    private long doc_type;
    private int supplier_id;
    private String business_date;
    private String description;
    private int doc_status;
    private long org_id;
    private String tag;
    private List<PurchaseLinesBean> purchase_lines;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public long getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(long doc_type) {
        this.doc_type = doc_type;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(String business_date) {
        this.business_date = business_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDoc_status() {
        return doc_status;
    }

    public void setDoc_status(int doc_status) {
        this.doc_status = doc_status;
    }

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<PurchaseLinesBean> getPurchase_lines() {
        return purchase_lines;
    }

    public void setPurchase_lines(List<PurchaseLinesBean> purchase_lines) {
        this.purchase_lines = purchase_lines;
    }


}
