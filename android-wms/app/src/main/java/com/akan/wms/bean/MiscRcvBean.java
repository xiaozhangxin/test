package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class MiscRcvBean implements Serializable{



    /**
     * rcv_id : 30
     * rcv_no : SCTL2019071655251
     * staff_id : 6003
     * memo : dsvsv
     * doc_no : 1111
     * account_period : 222
     * benefit_org : 444
     * org_id : 1001512200010027
     * is_delete : 0
     * distribution : 21
     * ex_warehouse :
     * rcv_status : 0
     * create_time : 2019-07-16 10:58:06
     * edit_time : 2019-07-16 10:58:06
     * infoList : [{"info_id":3,"info_wh_id":4,"rcv_id":30,"item_id":2,"number":3,"benefit_dept":{},"pub_desc_seg2":"使用中心code","pub_desc_seg3":"负责部门code","pub_desc_seg1":"负责中心code","cost_num":3,"info_num":5,"benefit_org_name":"","pub_desc_seg2_name":"","pub_desc_seg3_name":"","pub_desc_seg1_name":"","create_time":"2019-07-16 10:58:06","codeList":[],"info_wh_name":"","info_spec":"","info_name":""}]
     * rcv_status_show : 未处理
     * staff_name : 文云会
     * benefit_dept_name :
     * benefit_org_name :
     * distribution_name : 地方
     * ex_warehouse_name : 地方
     * doc_no_name :
     * start_time :
     * end_time :

     */

    private String rcv_id;
    private String rcv_no;
    private String staff_id;
    private String memo;
    private String doc_no;
    private Object account_period;
    private String benefit_org;
    private String org_id;
    private String is_delete;
    private String distribution;
    private String ex_warehouse;
    private String rcv_status;
    private String create_time;
    private String edit_time;
    private String rcv_status_show;
    private String staff_name;
    private String benefit_dept_name;
    private String benefit_org_name;
    private String distribution_name;
    private String ex_warehouse_name;
    private String doc_no_name;
    private String start_time;
    private String end_time;
    private String pub_desc_seg2_name;
    private String pub_desc_seg3_name;
    private String pub_desc_seg1_name;

    public List<RecordsBean> getRecordsBeans() {
        return recordsBeans;
    }

    public void setRecordsBeans(List<RecordsBean> recordsBeans) {
        this.recordsBeans = recordsBeans;
    }

    private List<RecordsBean> recordsBeans;

    public List<BarBean> getBarList() {
        return barList;
    }

    public void setBarList(List<BarBean> barList) {
        this.barList = barList;
    }

    private String is_valid;
    private List<BarBean> barList;

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
    }

    public String getPub_desc_seg2_name() {
        return pub_desc_seg2_name;
    }

    public void setPub_desc_seg2_name(String pub_desc_seg2_name) {
        this.pub_desc_seg2_name = pub_desc_seg2_name;
    }

    public String getPub_desc_seg3_name() {
        return pub_desc_seg3_name;
    }

    public void setPub_desc_seg3_name(String pub_desc_seg3_name) {
        this.pub_desc_seg3_name = pub_desc_seg3_name;
    }

    public String getPub_desc_seg1_name() {
        return pub_desc_seg1_name;
    }

    public void setPub_desc_seg1_name(String pub_desc_seg1_name) {
        this.pub_desc_seg1_name = pub_desc_seg1_name;
    }
    private List<InforListBean> infoList;

    public String getRcv_id() {
        return rcv_id;
    }

    public void setRcv_id(String rcv_id) {
        this.rcv_id = rcv_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getBenefit_dept_name() {
        return benefit_dept_name;
    }

    public void setBenefit_dept_name(String benefit_dept_name) {
        this.benefit_dept_name = benefit_dept_name;
    }

    public String getDoc_no_name() {
        return doc_no_name;
    }

    public void setDoc_no_name(String doc_no_name) {
        this.doc_no_name = doc_no_name;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getRcv_status() {
        return rcv_status;
    }

    public void setRcv_status(String rcv_status) {
        this.rcv_status = rcv_status;
    }

    public String getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(String edit_time) {
        this.edit_time = edit_time;
    }

    public String getDistribution_name() {
        return distribution_name;
    }

    public void setDistribution_name(String distribution_name) {
        this.distribution_name = distribution_name;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getRcv_no() {
        return rcv_no;

    }

    public void setRcv_no(String rcv_no) {
        this.rcv_no = rcv_no;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getEx_warehouse() {
        return ex_warehouse;
    }

    public void setEx_warehouse(String ex_warehouse) {
        this.ex_warehouse = ex_warehouse;
    }

    public String getRcv_status_show() {
        return rcv_status_show;
    }

    public void setRcv_status_show(String rcv_status_show) {
        this.rcv_status_show = rcv_status_show;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getBenefit_org_name() {
        return benefit_org_name;
    }

    public void setBenefit_org_name(String benefit_org_name) {
        this.benefit_org_name = benefit_org_name;
    }

    public String getEx_warehouse_name() {
        return ex_warehouse_name;
    }

    public void setEx_warehouse_name(String ex_warehouse_name) {
        this.ex_warehouse_name = ex_warehouse_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public List<InforListBean> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<InforListBean> infoList) {
        this.infoList = infoList;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getBenefit_org() {
        return benefit_org;
    }

    public void setBenefit_org(String benefit_org) {
        this.benefit_org = benefit_org;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public Object getAccount_period() {
        return account_period;
    }

    public void setAccount_period(Object account_period) {
        this.account_period = account_period;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }
}
