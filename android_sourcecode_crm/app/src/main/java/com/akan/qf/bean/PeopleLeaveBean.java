package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/2/21.
 */

public class PeopleLeaveBean implements Serializable {

    private List<StaffSignUnionBean> staffSignUnionList;

    public List<StaffSignUnionBean> getStaffSignUnionList() {
        return staffSignUnionList;
    }

    public void setStaffSignUnionList(List<StaffSignUnionBean> staffSignUnionList) {
        this.staffSignUnionList = staffSignUnionList;
    }

    public String getNext_audit_staff_name() {
        return next_audit_staff_name;
    }

    public void setNext_audit_staff_name(String next_audit_staff_name) {
        this.next_audit_staff_name = next_audit_staff_name;
    }

    public String getNext_audit_staff_head_img() {
        return next_audit_staff_head_img;
    }

    public void setNext_audit_staff_head_img(String next_audit_staff_head_img) {
        this.next_audit_staff_head_img = next_audit_staff_head_img;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    private String job_name;

    public String getNext_staff_id() {
        return next_staff_id;
    }

    public void setNext_staff_id(String next_staff_id) {
        this.next_staff_id = next_staff_id;
    }

    public String getStaff_sign_name() {
        return staff_sign_name;
    }

    public void setStaff_sign_name(String staff_sign_name) {
        this.staff_sign_name = staff_sign_name;
    }

    /**
     * apply_id : 6
     * apply_no : LZSQ2019022109861
     * staff_id : 53
     * staff_name : 二哥
     * staff_department : 皖北区域
     * apply_area : 皖北区域
     * apply_job : 啦啦
     * apply_name : 就看看
     * apply_remark : 太累了
     * apply_file : /images/peopleLeave/20190221/1550732473635394855564.jpg
     * apply_is_delete : 0
     * apply_state : wait_audit
     * record_no : 0
     * audit_staff_ids : 53
     * apply_time : 2019-02-21
     * apply_entry_time : 2019-02-21
     * apply_create_time : 2019-02-21 15:01:13
     * recordList : []
     * staff_uuid :
     * group_parent_uuid :
     * group_uuid :
     * <p>
     * start_time :
     * end_time :
     * <p>
     * <p>
     * apply_state_show : 待审阅
     */
    private String staff_sign_name;
    private String next_staff_id;
    private String next_audit_staff_name;
    private String next_audit_staff_head_img;
    private String apply_id;
    private String apply_no;
    private String staff_id;
    private String staff_name;
    private String staff_department;
    private String apply_area;
    private String apply_job;
    private String apply_name;
    private String apply_remark;
    private String apply_file;
    private String apply_is_delete;
    private String apply_state;
    private String record_no;
    private String audit_staff_ids;
    private String apply_time;
    private String apply_entry_time;
    private String apply_create_time;
    private String staff_uuid;
    private String group_parent_uuid;
    private String group_uuid;
    private String start_time;
    private String end_time;
    private String apply_state_show;
    private String staff_account;
    private String apply_form;
    private List<RecordListBean> recordList;

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

    public String getStaff_account() {
        return staff_account;
    }

    public void setStaff_account(String staff_account) {
        this.staff_account = staff_account;
    }

    public String getApply_form() {
        return apply_form;
    }

    public void setApply_form(String apply_form) {
        this.apply_form = apply_form;
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

    public String getApply_area() {
        return apply_area;
    }

    public void setApply_area(String apply_area) {
        this.apply_area = apply_area;
    }

    public String getApply_job() {
        return apply_job;
    }

    public void setApply_job(String apply_job) {
        this.apply_job = apply_job;
    }

    public String getApply_name() {
        return apply_name;
    }

    public void setApply_name(String apply_name) {
        this.apply_name = apply_name;
    }

    public String getApply_remark() {
        return apply_remark;
    }

    public void setApply_remark(String apply_remark) {
        this.apply_remark = apply_remark;
    }

    public String getApply_file() {
        return apply_file;
    }

    public void setApply_file(String apply_file) {
        this.apply_file = apply_file;
    }

    public String getApply_is_delete() {
        return apply_is_delete;
    }

    public void setApply_is_delete(String apply_is_delete) {
        this.apply_is_delete = apply_is_delete;
    }

    public String getApply_state() {
        return apply_state;
    }

    public void setApply_state(String apply_state) {
        this.apply_state = apply_state;
    }

    public String getRecord_no() {
        return record_no;
    }

    public void setRecord_no(String record_no) {
        this.record_no = record_no;
    }

    public String getAudit_staff_ids() {
        return audit_staff_ids;
    }

    public void setAudit_staff_ids(String audit_staff_ids) {
        this.audit_staff_ids = audit_staff_ids;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public String getApply_entry_time() {
        return apply_entry_time;
    }

    public void setApply_entry_time(String apply_entry_time) {
        this.apply_entry_time = apply_entry_time;
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
