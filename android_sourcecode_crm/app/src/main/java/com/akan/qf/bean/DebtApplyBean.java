package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/11/30.
 */

public class DebtApplyBean implements Serializable {


    /**
     * apply_id : 7
     * apply_no : QFD2018112986553
     * staff_id : 42
     * staff_name : 陈艳涛
     * staff_department : 爱康企业集团（上海）有限公司/南方营销中心/苏皖区域
     * customer_no : sds31315544564
     * apply_name : 单是否伺候srfs
     * apply_number : 4545577864
     * apply_assure : 地址
     * assure_no : 146545345644
     * apply_goods : 所用产品/大体数量
     * apply_money : 235
     * record_no : 1
     * apply_state : accept
     * next_staff_id : 43
     * apply_is_delete : 0
     * apply_update_time : 2018-11-29 15:01:03
     * apply_create_time : 2018-11-29 14:58:02
     * recordList : [{"record_id":22,"apply_id":7,"staff_id":42,"record_name":"陈艳涛","record_department":"爱康企业集团（上海）有限公司/南方营销中心/苏皖区域","record_remark":"","record_state":"wait_audit","record_create_time":"2018-11-29 14:58:02","record_state_show":"发出申请"},{"record_id":23,"apply_id":7,"staff_id":43,"record_name":"电饭锅","record_department":"爱康企业集团（上海）有限公司/南方营销中心/苏皖区域","record_remark":"心情不好","record_state":"accept","record_create_time":"2018-11-29 15:01:03","record_state_show":"审核通过"}]
     * apply_state_show : 已审核
     * apply_remark : 
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

    public String getStaff_sign_name() {
        return staff_sign_name;
    }

    public void setStaff_sign_name(String staff_sign_name) {
        this.staff_sign_name = staff_sign_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
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
    private String customer_no;
    private String apply_name;
    private String apply_number;
    private String apply_assure;
    private String assure_no;
    private String apply_goods;
    private String apply_money;
    private String record_no;
    private String apply_state;
    private String next_staff_id;
    private String apply_is_delete;
    private String apply_update_time;
    private String apply_create_time;
    private String apply_state_show;
    private String apply_remark;
    private String is_egis;
    private String staff_uuid;
    private String group_parent_uuid;
    private String group_uuid;
    private String start_time;
    private String end_time;
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

    public String getCustomer_no() {
        return customer_no;
    }

    public void setCustomer_no(String customer_no) {
        this.customer_no = customer_no;
    }

    public String getApply_name() {
        return apply_name;
    }

    public void setApply_name(String apply_name) {
        this.apply_name = apply_name;
    }

    public String getApply_number() {
        return apply_number;
    }

    public void setApply_number(String apply_number) {
        this.apply_number = apply_number;
    }

    public String getApply_assure() {
        return apply_assure;
    }

    public void setApply_assure(String apply_assure) {
        this.apply_assure = apply_assure;
    }

    public String getAssure_no() {
        return assure_no;
    }

    public void setAssure_no(String assure_no) {
        this.assure_no = assure_no;
    }

    public String getApply_goods() {
        return apply_goods;
    }

    public void setApply_goods(String apply_goods) {
        this.apply_goods = apply_goods;
    }

    public String getApply_money() {
        return apply_money;
    }

    public void setApply_money(String apply_money) {
        this.apply_money = apply_money;
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

    public String getNext_staff_id() {
        return next_staff_id;
    }

    public void setNext_staff_id(String next_staff_id) {
        this.next_staff_id = next_staff_id;
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

    public String getApply_remark() {
        return apply_remark;
    }

    public void setApply_remark(String apply_remark) {
        this.apply_remark = apply_remark;
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

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }


}
