package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class SaleShipBean implements Serializable{


    /**
     * id : 2
     * doc_type_id : 1001512205190401
     * doc_type_name : 正常销售
     * u9_id :
     * doc_no :
     * create_id : 5998
     * create_time : 2019-07-12 13:29:49
     * ship_plan_id : 1001901020042311
     * status : 2
     * org_id : 1001512200010027
     * is_valid : 0
     * customer_id :
     * customer_name :
     * create_name : 系统管理员
     * status_show : 装车
     * shipLineBeanList : []
     * shipBarBeanList : []
     */

    private String id;
    private long doc_type_id;
    private String doc_type_name;
    private String u9_id;
    private String doc_no;
    private String create_id;
    private String create_time;
    private long ship_plan_id;
    private String status;
    private long org_id;
    private String is_valid;
    private String customer_id;
    private String customer_name;
    private String create_name;
    private String status_show;
    private String ship_plan_no;
    private List<PlanLineBeanDetail> shipLineBeanList;
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

    public String getShip_plan_no() {
        return ship_plan_no;
    }

    public void setShip_plan_no(String ship_plan_no) {
        this.ship_plan_no = ship_plan_no;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDoc_type_id() {
        return doc_type_id;
    }

    public void setDoc_type_id(long doc_type_id) {
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

    public long getShip_plan_id() {
        return ship_plan_id;
    }

    public void setShip_plan_id(long ship_plan_id) {
        this.ship_plan_id = ship_plan_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
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

    public List<PlanLineBeanDetail> getShipLineBeanList() {
        return shipLineBeanList;
    }

    public void setShipLineBeanList(List<PlanLineBeanDetail> shipLineBeanList) {
        this.shipLineBeanList = shipLineBeanList;
    }


}
