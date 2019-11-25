package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

public class ShopCloseBean implements Serializable{


    /**
     * close_id : 5
     * close_no : JXSG2019102119894
     * staff_id : 291
     * group_id : 2
     * large_area_id : 300
     * region_id : 301
     * shop_id : 302
     * remark : 
     * next_audit_id : 176
     * is_delete : 
     * close_state : wait_audit
     * staff_audit : 
     * create_time : 2019-10-21 14:45:07
     * edit_time : 2019-10-21 14:45:07
     * recordList : [{"record_id":"","close_id":5,"staff_id":291,"staff_name":"周试压","record_state":"wait_audit","record_remark":"","create_time":"2019-10-21 14:45:07","record_state_show":"发出申请"},{"record_id":"","close_id":5,"staff_id":176,"staff_name":"余长兵","record_state":"unAuditing","record_remark":"","create_time":"2019-10-21 14:45:07","record_state_show":"审核中"}]
     * job_name_list : []
     * staff_name : 周试压
     * group_name : 南方营销中心
     * large_area_name : 两广二部
     * region_name : 桂林直营
     * shop_name : 南方营销中心
     * is_audit : 
     * close_state_show : 未审核
     * job_name : 试压工
     * start_time : 
     * end_time : 
     * staff_uuid : 
     * group_parent_uuid : 
     * all_name : 
     * is_app : 
     * data_sign : 
     * group_ids : 
     * module_id : 
     * group_uuid : 
     * operation : 
     */

    private String close_id;
    private String close_no;
    private String staff_id;
    private String group_id;
    private String large_area_id;
    private String region_id;
    private String shop_id;
    private String remark;
    private String next_audit_id;
    private String is_delete;
    private String close_state;
    private String staff_audit;
    private String create_time;
    private String edit_time;
    private String staff_name;
    private String group_name;
    private String large_area_name;
    private String region_name;
    private String shop_name;
    private String is_audit;
    private String close_state_show;
    private String job_name;
    private String start_time;
    private String end_time;
    private String staff_uuid;
    private String group_parent_uuid;
    private String all_name;
    private String is_app;
    private String data_sign;
    private String group_ids;
    private String module_id;
    private String group_uuid;
    private String operation;
    private List<RecordListBean> recordList;

    public String getClose_id() {
        return close_id;
    }

    public void setClose_id(String close_id) {
        this.close_id = close_id;
    }

    public String getClose_no() {
        return close_no;
    }

    public void setClose_no(String close_no) {
        this.close_no = close_no;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getLarge_area_id() {
        return large_area_id;
    }

    public void setLarge_area_id(String large_area_id) {
        this.large_area_id = large_area_id;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNext_audit_id() {
        return next_audit_id;
    }

    public void setNext_audit_id(String next_audit_id) {
        this.next_audit_id = next_audit_id;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getClose_state() {
        return close_state;
    }

    public void setClose_state(String close_state) {
        this.close_state = close_state;
    }

    public String getStaff_audit() {
        return staff_audit;
    }

    public void setStaff_audit(String staff_audit) {
        this.staff_audit = staff_audit;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(String edit_time) {
        this.edit_time = edit_time;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getLarge_area_name() {
        return large_area_name;
    }

    public void setLarge_area_name(String large_area_name) {
        this.large_area_name = large_area_name;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getIs_audit() {
        return is_audit;
    }

    public void setIs_audit(String is_audit) {
        this.is_audit = is_audit;
    }

    public String getClose_state_show() {
        return close_state_show;
    }

    public void setClose_state_show(String close_state_show) {
        this.close_state_show = close_state_show;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
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

    public String getStaff_uuid() {
        return staff_uuid;
    }

    public void setStaff_uuid(String staff_uuid) {
        this.staff_uuid = staff_uuid;
    }

    public String getGroup_parent_uuid() {
        return group_parent_uuid;
    }

    public void setGroup_parent_uuid(String group_parent_uuid) {
        this.group_parent_uuid = group_parent_uuid;
    }

    public String getAll_name() {
        return all_name;
    }

    public void setAll_name(String all_name) {
        this.all_name = all_name;
    }

    public String getIs_app() {
        return is_app;
    }

    public void setIs_app(String is_app) {
        this.is_app = is_app;
    }

    public String getData_sign() {
        return data_sign;
    }

    public void setData_sign(String data_sign) {
        this.data_sign = data_sign;
    }

    public String getGroup_ids() {
        return group_ids;
    }

    public void setGroup_ids(String group_ids) {
        this.group_ids = group_ids;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getGroup_uuid() {
        return group_uuid;
    }

    public void setGroup_uuid(String group_uuid) {
        this.group_uuid = group_uuid;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }



}
