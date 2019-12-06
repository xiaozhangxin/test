package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/2/20.
 */

public class PeopleNewBean implements Serializable{

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
     * apply_id : 16
     * apply_no : XRSQ2019022708350
     * staff_id : 53
     * staff_name : 二哥
     * staff_department : 皖北区域
     * apply_area : 皖北区域
     * apply_job : Hhju
     * apply_name : Hhhh
     * apply_sex : 男
     * apply_nation :
     * apply_birthday : 2019-02-27
     * apply_marriage :
     * apply_address :
     * apply_tel :
     * apply_num :
     * apply_home :
     * apply_contact :
     * apply_contact_tel :
     * apply_education :
     * apply_graduate :
     * apply_experience :
     * apply_number :
     * apply_images :
     * apply_is_delete : 0
     * apply_state : wait_audit
     * cc_person :
     * next_staff_id : 46
     * record_no : 0
     * audit_staff_ids : 53,46
     * apply_time : 2019-02-27
     * apply_create_time : 2019-02-27 12:02:50
     * apply_update_time : 2019-02-27 12:02:50
     * recordList : []
     * staff_uuid :
     * group_parent_uuid :
     * group_uuid :
     * start_time :
     * end_time :
     * apply_state_show : 未审核
     * apply_remark :

     * is_egis :
     */
    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }
    private String staff_sign_name;
    private String job_name;
    private String apply_id;
    private String apply_no;
    private String staff_id;
    private String staff_name;
    private String staff_department;
    private String apply_area;
    private String apply_job;
    private String apply_name;
    private String apply_sex;
    private String apply_nation;
    private String apply_birthday;
    private String apply_marriage;
    private String apply_address;
    private String apply_tel;
    private String apply_num;
    private String apply_home;
    private String apply_contact;
    private String apply_contact_tel;
    private String apply_education;
    private String apply_graduate;
    private String apply_experience;
    private String apply_number;
    private String apply_images;
    private String apply_is_delete;
    private String apply_state;
    private String cc_person;
    private String next_staff_id;
    private String record_no;
    private String audit_staff_ids;
    private String apply_time;
    private String apply_create_time;
    private String apply_update_time;
    private String staff_uuid;
    private String group_parent_uuid;
    private String group_uuid;
    private String start_time;
    private String end_time;

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

    private String apply_state_show;
    private String apply_remark;
    private String is_egis;
    private String next_audit_staff_name;
    private String next_audit_staff_head_img;
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

    public String getApply_sex() {
        return apply_sex;
    }

    public void setApply_sex(String apply_sex) {
        this.apply_sex = apply_sex;
    }

    public String getApply_nation() {
        return apply_nation;
    }

    public void setApply_nation(String apply_nation) {
        this.apply_nation = apply_nation;
    }

    public String getApply_birthday() {
        return apply_birthday;
    }

    public void setApply_birthday(String apply_birthday) {
        this.apply_birthday = apply_birthday;
    }

    public String getApply_marriage() {
        return apply_marriage;
    }

    public void setApply_marriage(String apply_marriage) {
        this.apply_marriage = apply_marriage;
    }

    public String getApply_address() {
        return apply_address;
    }

    public void setApply_address(String apply_address) {
        this.apply_address = apply_address;
    }

    public String getApply_tel() {
        return apply_tel;
    }

    public void setApply_tel(String apply_tel) {
        this.apply_tel = apply_tel;
    }

    public String getApply_num() {
        return apply_num;
    }

    public void setApply_num(String apply_num) {
        this.apply_num = apply_num;
    }

    public String getApply_home() {
        return apply_home;
    }

    public void setApply_home(String apply_home) {
        this.apply_home = apply_home;
    }

    public String getApply_contact() {
        return apply_contact;
    }

    public void setApply_contact(String apply_contact) {
        this.apply_contact = apply_contact;
    }

    public String getApply_contact_tel() {
        return apply_contact_tel;
    }

    public void setApply_contact_tel(String apply_contact_tel) {
        this.apply_contact_tel = apply_contact_tel;
    }

    public String getApply_education() {
        return apply_education;
    }

    public void setApply_education(String apply_education) {
        this.apply_education = apply_education;
    }

    public String getApply_graduate() {
        return apply_graduate;
    }

    public void setApply_graduate(String apply_graduate) {
        this.apply_graduate = apply_graduate;
    }

    public String getApply_experience() {
        return apply_experience;
    }

    public void setApply_experience(String apply_experience) {
        this.apply_experience = apply_experience;
    }

    public String getApply_number() {
        return apply_number;
    }

    public void setApply_number(String apply_number) {
        this.apply_number = apply_number;
    }

    public String getApply_images() {
        return apply_images;
    }

    public void setApply_images(String apply_images) {
        this.apply_images = apply_images;
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

    public String getCc_person() {
        return cc_person;
    }

    public void setCc_person(String cc_person) {
        this.cc_person = cc_person;
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

    public String getApply_create_time() {
        return apply_create_time;
    }

    public void setApply_create_time(String apply_create_time) {
        this.apply_create_time = apply_create_time;
    }

    public String getApply_update_time() {
        return apply_update_time;
    }

    public void setApply_update_time(String apply_update_time) {
        this.apply_update_time = apply_update_time;
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

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }
}
