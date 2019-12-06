package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/11/9.
 */

public class PolicyBean implements Serializable {


    /**
     * apply_id : 8
     * apply_no : ZCD2018110999973
     * staff_id : 52
     * staff_name : 大哥
     * staff_department : 爱康企业集团（上海）有限公司/南方营销中心/苏皖区域
     * apply_remark : 好了
     * apply_accept : 监控
     * apply_accept_remark : 看到
     * apply_department : 爱康企业集团（上海）有限公司/南方营销中心/苏皖区域
     * apply_category : 折扣申请
     * next_staff_id : 4
     * record_no : 0
     * apply_state : wait_audit
     * apply_is_delete : 0
     * apply_update_time : 2018-11-09 14:30:50
     * apply_create_time : 2018-11-09 14:30:50
     * applyFileList : []
     * applyRecordList : []
     * apply_state_show : 待审核
     * apply_category_show : 发票申请
     * is_egis :
     * staff_uuid :
     * group_parent_uuid :
     * group_uuid :
     * start_time :
     * end_time :
     */
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

    public String getStaff_sign_name() {
        return staff_sign_name;
    }

    public void setStaff_sign_name(String staff_sign_name) {
        this.staff_sign_name = staff_sign_name;
    }

    private String staff_sign_name;
    private String job_name;
    private String next_audit_staff_name;
    private String next_audit_staff_head_img;
    private String apply_id;
    private String apply_no;
    private String staff_id;
    private String staff_name;
    private String staff_department;
    private String apply_remark;
    private String apply_accept;
    private String apply_accept_remark;
    private String apply_department;
    private String apply_category;
    private String next_staff_id;
    private String record_no;
    private String apply_state;
    private String apply_is_delete;
    private String apply_update_time;
    private String apply_create_time;
    private String apply_state_show;
    private String apply_category_show;
    private String is_egis;
    private String staff_uuid;
    private String group_parent_uuid;
    private String group_uuid;
    private String start_time;
    private String end_time;
    private List<FileListBean> fileList;
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

    public String getApply_remark() {
        return apply_remark;
    }

    public void setApply_remark(String apply_remark) {
        this.apply_remark = apply_remark;
    }

    public String getApply_accept() {
        return apply_accept;
    }

    public void setApply_accept(String apply_accept) {
        this.apply_accept = apply_accept;
    }

    public String getApply_accept_remark() {
        return apply_accept_remark;
    }

    public void setApply_accept_remark(String apply_accept_remark) {
        this.apply_accept_remark = apply_accept_remark;
    }

    public String getApply_department() {
        return apply_department;
    }

    public void setApply_department(String apply_department) {
        this.apply_department = apply_department;
    }

    public String getApply_category() {
        return apply_category;
    }

    public void setApply_category(String apply_category) {
        this.apply_category = apply_category;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getNext_staff_id() {
        return next_staff_id;
    }

    public void setNext_staff_id(String next_staff_id) {
        this.next_staff_id = next_staff_id;
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

    public String getApply_is_delete() {
        return apply_is_delete;
    }

    public void setApply_is_delete(String apply_is_delete) {
        this.apply_is_delete = apply_is_delete;
    }

    public String getApply_update_time() {
        return apply_update_time;
    }

    public void setApply_update_time(String apply_update_time) {
        this.apply_update_time = apply_update_time;
    }

    public String getApply_create_time() {
        return apply_create_time;
    }

    public void setApply_create_time(String apply_create_time) {
        this.apply_create_time = apply_create_time;
    }

    public String getApply_state_show() {
        return apply_state_show;
    }

    public void setApply_state_show(String apply_state_show) {
        this.apply_state_show = apply_state_show;
    }

    public String getApply_category_show() {
        return apply_category_show;
    }

    public void setApply_category_show(String apply_category_show) {
        this.apply_category_show = apply_category_show;
    }

    public String getIs_egis() {
        return is_egis;
    }

    public void setIs_egis(String is_egis) {
        this.is_egis = is_egis;
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

    public List<FileListBean> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileListBean> fileList) {
        this.fileList = fileList;
    }

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }
}
