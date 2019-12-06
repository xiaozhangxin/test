package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/11/9.
 */

public class TemporaryBean implements Serializable{


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
    private String staff_sign_name;
    private String job_name;
    private String next_audit_staff_name;
    private String next_audit_staff_head_img;

    public String getStaff_sign_name() {
        return staff_sign_name;
    }

    public void setStaff_sign_name(String staff_sign_name) {
        this.staff_sign_name = staff_sign_name;
    }

    /**
     * support_id : 14
     * support_no : ZZD2018110939682
     * staff_id : 52
     * staff_name : 大哥
     * staff_department : 爱康企业集团（上海）有限公司/南方营销中心/苏皖区域
     * support_price : 5666.86
     * support_project : 来咯哦哦弄科目
     * support_accept : 二弟
     * support_result : 事由嗨咯我在吃饭了
     * support_department : 爱康企业集团（上海）有限公司/南方营销中心/苏皖区域
     * support_remark : 备注哈哈
     * record_no : 0
     * support_state : wait_audit
     * next_staff_id : 4
     * support_is_delete : 0
     * support_back_time : 2018-11-09 00:00:00
     * support_update_time : 2018-11-09 12:03:02
     * support_create_time : 2018-11-09 12:03:02
     * recordList : [{"record_id":35,"support_id":14,"staff_id":52,"record_name":"大哥","record_department":"爱康企业集团（上海）有限公司/南方营销中心/苏皖区域","record_remark":"备注哈哈","record_state":"wait_audit","record_create_time":"2018-11-09 12:03:02","record_state_show":"发出申请"},{"record_id":36,"support_id":14,"staff_id":4,"record_name":"石磊","record_department":"爱康企业集团（上海）有限公司/南方营销中心","record_remark":"","record_state":"unauditing","record_create_time":"2018-11-09 12:03:03","record_state_show":"审核中"}]
     * fileList : []
     * support_state_show : 待审核
     * is_egis :
     * staff_uuid :
     * group_parent_uuid :
     * group_uuid :
     * start_time :
     * end_time :
     * apply_category_show :
     * apply_way_show :
     */

    private String support_id;
    private String support_no;
    private String staff_id;
    private String staff_name;
    private String staff_department;
    private String support_price;
    private String support_project;
    private String support_accept;
    private String support_result;
    private String support_department;
    private String support_remark;
    private int record_no;
    private String support_state;
    private String next_staff_id;
    private String support_is_delete;
    private String support_back_time;
    private String support_update_time;
    private String support_create_time;
    private String support_state_show;
    private String is_egis;
    private String staff_uuid;
    private String group_parent_uuid;
    private String group_uuid;
    private String start_time;
    private String end_time;
    private String apply_category_show;
    private String apply_way_show;
    private List<RecordListBean> recordList;
    private List<FileListBean> fileList;

    public String getSupport_id() {
        return support_id;
    }

    public void setSupport_id(String support_id) {
        this.support_id = support_id;
    }

    public String getSupport_no() {
        return support_no;
    }

    public void setSupport_no(String support_no) {
        this.support_no = support_no;
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

    public String getSupport_price() {
        return support_price;
    }

    public void setSupport_price(String support_price) {
        this.support_price = support_price;
    }

    public String getSupport_project() {
        return support_project;
    }

    public void setSupport_project(String support_project) {
        this.support_project = support_project;
    }

    public String getSupport_accept() {
        return support_accept;
    }

    public void setSupport_accept(String support_accept) {
        this.support_accept = support_accept;
    }

    public String getSupport_result() {
        return support_result;
    }

    public void setSupport_result(String support_result) {
        this.support_result = support_result;
    }

    public String getSupport_department() {
        return support_department;
    }

    public void setSupport_department(String support_department) {
        this.support_department = support_department;
    }

    public String getSupport_remark() {
        return support_remark;
    }

    public void setSupport_remark(String support_remark) {
        this.support_remark = support_remark;
    }

    public int getRecord_no() {
        return record_no;
    }

    public void setRecord_no(int record_no) {
        this.record_no = record_no;
    }

    public String getSupport_state() {
        return support_state;
    }

    public void setSupport_state(String support_state) {
        this.support_state = support_state;
    }

    public String getNext_staff_id() {
        return next_staff_id;
    }

    public void setNext_staff_id(String next_staff_id) {
        this.next_staff_id = next_staff_id;
    }

    public String getSupport_is_delete() {
        return support_is_delete;
    }

    public void setSupport_is_delete(String support_is_delete) {
        this.support_is_delete = support_is_delete;
    }

    public String getSupport_back_time() {
        return support_back_time;
    }

    public void setSupport_back_time(String support_back_time) {
        this.support_back_time = support_back_time;
    }

    public String getSupport_update_time() {
        return support_update_time;
    }

    public void setSupport_update_time(String support_update_time) {
        this.support_update_time = support_update_time;
    }

    public String getSupport_create_time() {
        return support_create_time;
    }

    public void setSupport_create_time(String support_create_time) {
        this.support_create_time = support_create_time;
    }

    public String getSupport_state_show() {
        return support_state_show;
    }

    public void setSupport_state_show(String support_state_show) {
        this.support_state_show = support_state_show;
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

    public String getApply_category_show() {
        return apply_category_show;
    }

    public void setApply_category_show(String apply_category_show) {
        this.apply_category_show = apply_category_show;
    }

    public String getApply_way_show() {
        return apply_way_show;
    }

    public void setApply_way_show(String apply_way_show) {
        this.apply_way_show = apply_way_show;
    }

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }

    public List<FileListBean> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileListBean> fileList) {
        this.fileList = fileList;
    }


}
