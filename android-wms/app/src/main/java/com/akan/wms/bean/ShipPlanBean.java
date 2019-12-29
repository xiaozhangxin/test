package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class ShipPlanBean implements Serializable{


    /**
     * id : 1001901020011592
     * doc_no : 101-SEOUT-201901-00001
     * doc_type_id : 1001512205190301
     * doc_type_name : 出货计划-正常销售
     * org_id : 1001512200010027
     * ship_org_id : 1001512200010027
     * business_date : 2019-01-02 00:00:00
     * update_time : 2019-01-03 10:49:49
     * status : 3
     * ship_org_name : 爱康企业集团（上海）
     * status_show : 已核准
     * planLineBeans : []
     */

    private String id;
    private String doc_no;
    private String doc_type_id;
    private String doc_type_name;
    private String org_id;
    private String ship_org_id;
    private String business_date;
    private String update_time;
    private String status;
    private String ship_org_name;

    public String getSrc_doc_no() {
        return src_doc_no;
    }

    public void setSrc_doc_no(String src_doc_no) {
        this.src_doc_no = src_doc_no;
    }

    private String status_show;
    private String src_doc_no;
    private List<PlanLineBean> planLineBeans;

    public List<BarBean> getBarList() {
        return barList;
    }

    public void setBarList(List<BarBean> barList) {
        this.barList = barList;
    }

    private List<BarBean> barList;
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

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getShip_org_id() {
        return ship_org_id;
    }

    public void setShip_org_id(String ship_org_id) {
        this.ship_org_id = ship_org_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShip_org_name() {
        return ship_org_name;
    }

    public void setShip_org_name(String ship_org_name) {
        this.ship_org_name = ship_org_name;
    }

    public String getStatus_show() {
        return status_show;
    }

    public void setStatus_show(String status_show) {
        this.status_show = status_show;
    }

    public List<PlanLineBean> getPlanLineBeans() {
        return planLineBeans;
    }

    public void setPlanLineBeans(List<PlanLineBean> planLineBeans) {
        this.planLineBeans = planLineBeans;
    }
}
