package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public  class SlaesBean implements Serializable {


    /**
     * id : 97
     * doc_type_id : 1001512271224579
     * doc_type_name : 出货计划-上海市场部
     * u9_id : 1002002220000209
     * u9_code : 101-XOUT-202002-00003
     * doc_no : XSCK-101-20200222-00002
     * create_id : 150704
     * create_time : 2020-02-22 17:51:15
     * ship_plan_id : 1001912234164115
     * ship_plan_no : 101-SEOUT-201912-04491
     * ship_src_plan_no :
     * status : 2
     * org_id : 1001512200010027
     * is_valid : 0
     * customer_id : 1001812210017026
     * customer_name : 上海市场部-松江施工仓库
     * is_delete : 0
     * create_name :
     * status_show : 已装车
     * start_time :
     * end_time :
     * shipLineBeanList : []
     * shipBarBeanList : []
     * recordsBeans : []
     */

    private String id;
    private String doc_type_id;
    private String doc_type_name;
    private String u9_id;
    private String u9_code;
    private String doc_no;
    private String create_id;
    private String create_time;
    private String ship_plan_id;
    private String ship_plan_no;
    private String ship_src_plan_no;
    private String status;
    private String org_id;
    private String is_valid;
    private String customer_id;
    private String customer_name;
    private String is_delete;
    private String create_name;
    private String status_show;
    private String start_time;
    private String end_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getU9_id() {
        return u9_id;
    }

    public void setU9_id(String u9_id) {
        this.u9_id = u9_id;
    }

    public String getU9_code() {
        return u9_code;
    }

    public void setU9_code(String u9_code) {
        this.u9_code = u9_code;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getCreate_id() {
        return create_id;
    }

    public void setCreate_id(String create_id) {
        this.create_id = create_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getShip_plan_id() {
        return ship_plan_id;
    }

    public void setShip_plan_id(String ship_plan_id) {
        this.ship_plan_id = ship_plan_id;
    }

    public String getShip_plan_no() {
        return ship_plan_no;
    }

    public void setShip_plan_no(String ship_plan_no) {
        this.ship_plan_no = ship_plan_no;
    }

    public String getShip_src_plan_no() {
        return ship_src_plan_no;
    }

    public void setShip_src_plan_no(String ship_src_plan_no) {
        this.ship_src_plan_no = ship_src_plan_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
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

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }

    public String getStatus_show() {
        return status_show;
    }

    public void setStatus_show(String status_show) {
        this.status_show = status_show;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }


}
