package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/2/25.
 */

public class WaterBean implements Serializable{

    private List<StaffSignUnionBean> staffSignUnionList;

    public List<StaffSignUnionBean> getStaffSignUnionList() {
        return staffSignUnionList;
    }

    public void setStaffSignUnionList(List<StaffSignUnionBean> staffSignUnionList) {
        this.staffSignUnionList = staffSignUnionList;
    }

    public String getStaff_sign_name() {
        return staff_sign_name;
    }

    public void setStaff_sign_name(String staff_sign_name) {
        this.staff_sign_name = staff_sign_name;
    }

    /**
     * apply_id : 3
     * apply_no : GZXX2019022553319
     * staff_id : 53
     * staff_name : 二哥
     * staff_department : 皖北区域
     * apply_department : 皖北区域
     * apply_name : 两手空空
     * apply_tel : 妥投
     * apply_native : 
     * apply_num : 
     * apply_brand : 
     * apply_shop : 
     * apply_reward : 
     * apply_remark : 新明
     * apply_time : 理论题
     * apply_is_delete : 0
     * record_no : 0
     * apply_state : wait_audit
     * audit_staff_ids : 53
     * apply_create_time : 2019-02-25 16:03:58
     * recordList : []
     * staff_uuid : 
     * group_parent_uuid : 
     * group_uuid : 
     * start_time : 
     * end_time :

     * apply_state_show : 待审阅
     */
    public String getJob_name() {
        return job_name;
    }
    private String staff_sign_name;
    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }
    private String apply_city;
    private String apply_customer;
    private String apply_quality;

    public String getApply_city() {
        return apply_city;
    }

    public void setApply_city(String apply_city) {
        this.apply_city = apply_city;
    }

    public String getApply_customer() {
        return apply_customer;
    }

    public void setApply_customer(String apply_customer) {
        this.apply_customer = apply_customer;
    }

    public String getApply_quality() {
        return apply_quality;
    }

    public void setApply_quality(String apply_quality) {
        this.apply_quality = apply_quality;
    }

    private String job_name;
    private String apply_id;
    private String apply_no;
    private String staff_id;
    private String staff_name;
    private String staff_department;
    private String apply_department;
    private String apply_name;
    private String apply_tel;
    private String apply_native;
    private String apply_num;
    private String apply_brand;
    private String apply_shop;
    private String apply_reward;
    private String apply_remark;
    private String apply_time;
    private String apply_is_delete;
    private String record_no;
    private String apply_state;
    private String audit_staff_ids;
    private String apply_create_time;
    private String staff_uuid;
    private String group_parent_uuid;
    private String group_uuid;
    private String start_time;
    private String end_time;
    private String apply_state_show;
    private List<RecordListBean> recordList;
    private List<TrackListBean> trackList;

    public List<TrackListBean> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<TrackListBean> trackList) {
        this.trackList = trackList;
    }
    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getApply_no() {
        return apply_no;
    }

    public void setApply_no(String apply_no) {
        this.apply_no = apply_no;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getStaff_department() {
        return staff_department;
    }

    public void setStaff_department(String staff_department) {
        this.staff_department = staff_department;
    }

    public String getApply_department() {
        return apply_department;
    }

    public void setApply_department(String apply_department) {
        this.apply_department = apply_department;
    }

    public String getApply_name() {
        return apply_name;
    }

    public void setApply_name(String apply_name) {
        this.apply_name = apply_name;
    }

    public String getApply_tel() {
        return apply_tel;
    }

    public void setApply_tel(String apply_tel) {
        this.apply_tel = apply_tel;
    }

    public String getApply_native() {
        return apply_native;
    }

    public void setApply_native(String apply_native) {
        this.apply_native = apply_native;
    }

    public String getApply_num() {
        return apply_num;
    }

    public void setApply_num(String apply_num) {
        this.apply_num = apply_num;
    }

    public String getApply_brand() {
        return apply_brand;
    }

    public void setApply_brand(String apply_brand) {
        this.apply_brand = apply_brand;
    }

    public String getApply_shop() {
        return apply_shop;
    }

    public void setApply_shop(String apply_shop) {
        this.apply_shop = apply_shop;
    }

    public String getApply_reward() {
        return apply_reward;
    }

    public void setApply_reward(String apply_reward) {
        this.apply_reward = apply_reward;
    }

    public String getApply_remark() {
        return apply_remark;
    }

    public void setApply_remark(String apply_remark) {
        this.apply_remark = apply_remark;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public String getApply_is_delete() {
        return apply_is_delete;
    }

    public void setApply_is_delete(String apply_is_delete) {
        this.apply_is_delete = apply_is_delete;
    }

    public String getRecord_no() {
        return record_no;
    }

    public void setRecord_no(String record_no) {
        this.record_no = record_no;
    }

    public String getApply_state() {
        return apply_state;
    }

    public void setApply_state(String apply_state) {
        this.apply_state = apply_state;
    }

    public String getAudit_staff_ids() {
        return audit_staff_ids;
    }

    public void setAudit_staff_ids(String audit_staff_ids) {
        this.audit_staff_ids = audit_staff_ids;
    }

    public String getApply_create_time() {
        return apply_create_time;
    }

    public void setApply_create_time(String apply_create_time) {
        this.apply_create_time = apply_create_time;
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

    public String getGroup_uuid() {
        return group_uuid;
    }

    public void setGroup_uuid(String group_uuid) {
        this.group_uuid = group_uuid;
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

    public String getApply_state_show() {
        return apply_state_show;
    }

    public void setApply_state_show(String apply_state_show) {
        this.apply_state_show = apply_state_show;
    }

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }
}
