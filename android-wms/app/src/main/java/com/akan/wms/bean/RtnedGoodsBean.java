package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class RtnedGoodsBean implements Serializable {


    /**
     * id : 1190719140535680
     * creator_id : 5998
     * creator_name : 系统管理员
     * supplier_code : 13003
     * supplier_name : 北京北化高科新技术股份有限公司
     * business_date : 2019-07-18 10:21:12
     * doc_no : null
     * org : 1001512200010027
     * org_name : null
     * is_delete : 1
     * create_time : 2019-07-19 14:06:18
     * update_time : null
     * status : 0
     * status_name : 已配货
     * rtn_memo : 1001a
     * bar_code : 100ww0010027
     * rtned_lines : [{"rtn_id":1001907170049096,"rtned_gods_lines":[{"id":1190719140535651,"rtned_goods_id":1190719140535680,"rtned_goods_line_id":"","item_id":1001812190210713,"item_code":"00.01.013.0001","item_name":"白色PPR色母(R1610C)","item_spec":"","src_doc_type":0,"src_doc_no":null,"doc_no":null,"rtn_qty":0,"rtn_order_qty":0,"rtn_ar_qty":0,"alloc_qty":1,"rtn":1001907170049096,"wh_id":123,"wh_code":0,"wh_name":null,"rtn_line_no":0,"org_id":1001512200010027,"org_name":null,"bar_code":"1001b","description":"1001a","status":0,"status_name":"已配货"}]}]
     * rtned_gods_lines : null
     */

    private long id;
    private String creator_id;
    private String creator_name;
    private int supplier_code;
    private String supplier_name;
    private Object doc_no;
    private long org;
    private Object org_name;
    private int is_delete;
    private String create_time;
    private Object update_time;
    private String status;
    private String status_name;
    private String rtn_memo;
    private String is_valid;
    private Object rtned_gods_lines;
    private List<RtnedLinesBean> rtned_lines;
    private List<RecordsBean> recordsBeans;
    private List<BarVerificationListsBean> bar_lists;

    public List<BarVerificationListsBean> getBar_lists() {
        return bar_lists;
    }

    public void setBar_lists(List<BarVerificationListsBean> bar_lists) {
        this.bar_lists = bar_lists;
    }

    public List<RecordsBean> getRecordsBeans() {
        return recordsBeans;
    }

    public void setRecordsBeans(List<RecordsBean> recordsBeans) {
        this.recordsBeans = recordsBeans;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public int getSupplier_code() {
        return supplier_code;
    }

    public void setSupplier_code(int supplier_code) {
        this.supplier_code = supplier_code;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }



    public Object getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(Object doc_no) {
        this.doc_no = doc_no;
    }

    public long getOrg() {
        return org;
    }

    public void setOrg(long org) {
        this.org = org;
    }

    public Object getOrg_name() {
        return org_name;
    }

    public void setOrg_name(Object org_name) {
        this.org_name = org_name;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Object getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Object update_time) {
        this.update_time = update_time;
    }

    public String getStatus() {
        return status;
    }

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getRtn_memo() {
        return rtn_memo;
    }

    public void setRtn_memo(String rtn_memo) {
        this.rtn_memo = rtn_memo;
    }


    public Object getRtned_gods_lines() {
        return rtned_gods_lines;
    }

    public void setRtned_gods_lines(Object rtned_gods_lines) {
        this.rtned_gods_lines = rtned_gods_lines;
    }

    public List<RtnedLinesBean> getRtned_lines() {
        return rtned_lines;
    }

    public void setRtned_lines(List<RtnedLinesBean> rtned_lines) {
        this.rtned_lines = rtned_lines;
    }
}
