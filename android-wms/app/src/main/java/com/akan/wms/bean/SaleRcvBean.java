package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class SaleRcvBean implements Serializable {


    /**
     * id : 7
     * org_id : 1001512200010027
     * doc_no : 2304283647467848712
     * customer_id : 1001812180041498
     * customer_name : 赣州市经济技术开发区利保管道运营店
     * status : 0
     * doc_type_id : 
     * doc_type_name : 
     * business_date : 
     * update_time : 
     * status_show : 开立
     * revLines : []
     */

    private String id;
    private String org_id;
    private String doc_no;
    private String customer_id;
    private String customer_name;
    private String status;
    private String doc_type_id;
    private String doc_type_name;
    private String business_date;
    private String update_time;
    private String status_show;
    private List<RevLinesBean> revLines;
    private List<RecordsBean> recordsBeans;

    public List<RecordsBean> getRecordsBeans() {
        return recordsBeans;
    }

    public void setRecordsBeans(List<RecordsBean> recordsBeans) {
        this.recordsBeans = recordsBeans;
    }

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

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoc_type_id() {
        return doc_type_id;
    }

    public void setDoc_type_id(String doc_type_id) {
        this.doc_type_id = doc_type_id;
    }

    public String getDoc_type_name() {
        return doc_type_name;
    }

    public void setDoc_type_name(String doc_type_name) {
        this.doc_type_name = doc_type_name;
    }

    public String getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(String business_date) {
        this.business_date = business_date;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getStatus_show() {
        return status_show;
    }

    public void setStatus_show(String status_show) {
        this.status_show = status_show;
    }

    public List<RevLinesBean> getRevLines() {
        return revLines;
    }

    public void setRevLines(List<RevLinesBean> revLines) {
        this.revLines = revLines;
    }
}
