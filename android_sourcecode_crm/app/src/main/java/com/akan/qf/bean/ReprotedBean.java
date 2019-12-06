package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/11/19.
 */

public class ReprotedBean implements Serializable{
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
    private String staff_sign_name;
    private String next_audit_staff_name;

    public String getStaff_sign_name() {
        return staff_sign_name;
    }

    public void setStaff_sign_name(String staff_sign_name) {
        this.staff_sign_name = staff_sign_name;
    }

    private String next_audit_staff_head_img;
    /**
     * apply_id : 14
     * apply_no : GCD2018111916672
     * staff_id : 52
     * staff_name : 大哥
     * staff_department : 爱康企业集团（上海）有限公司/南方营销中心/苏皖区域
     * apply_department : 爱康企业集团（上海）有限公司/南方营销中心/苏皖区域
     * apply_category :
     * apply_name :
     * apply_title :
     * apply_address : 上海市浦东新区开元大道110
     * apply_first : 校长
     * apply_second : 方林
     * apply_size : 100
     * apply_shop : 师傅上门
     * apply_join : 经销商管关系
     * apply_number : 200000
     * apply_compete : 去了防水
     * apply_area : 同意
     * apply_suggest : 同意
     * apply_company : 同意
     * apply_remark : 被窝咯哦哦，好的，谢谢了
     * record_no : 0
     * apply_state : wait_audit
     * next_staff_id : 4
     * apply_is_delete : 0
     * apply_update_time : 2018-11-19 14:17:38
     * apply_create_time : 2018-11-19 14:17:38
     * recordList : []
     * apply_category_show :
     * apply_state_show : 待审核
     * is_egis :
     * staff_uuid :
     * group_parent_uuid :
     * group_uuid :
     * start_time :
     * end_time :
     */

    private String apply_id;
    private String apply_no;
    private String staff_id;
    private String staff_name;
    private String staff_department;
    private String apply_department;
    private String apply_category;
    private String apply_name;
    private String apply_title;
    private String apply_address;
    private String apply_first;
    private String apply_second;
    private String apply_size;
    private String apply_shop;
    private String apply_join;
    private String apply_number;
    private String apply_compete;
    private String apply_area;
    private String apply_suggest;
    private String apply_company;
    private String apply_remark;
    private String record_no;
    private String apply_state;
    private String next_staff_id;
    private String apply_is_delete;
    private String apply_update_time;
    private String apply_create_time;
    private String apply_category_show;
    private String apply_state_show;
    private String is_egis;
    private String staff_uuid;
    private String group_parent_uuid;
    private String apply_second_tel;
    private String apply_first_tel;
    private String group_uuid;
    private String start_time;
    private String end_time;

    public String getApply_second_tel() {
        return apply_second_tel;
    }

    public void setApply_second_tel(String apply_second_tel) {
        this.apply_second_tel = apply_second_tel;
    }

    public String getApply_first_tel() {
        return apply_first_tel;
    }

    public void setApply_first_tel(String apply_first_tel) {
        this.apply_first_tel = apply_first_tel;
    }

    private List<RecordListBean> recordList;


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

    public String getApply_name() {
        return apply_name;
    }

    public void setApply_name(String apply_name) {
        this.apply_name = apply_name;
    }

    public String getApply_title() {
        return apply_title;
    }

    public void setApply_title(String apply_title) {
        this.apply_title = apply_title;
    }

    public String getApply_address() {
        return apply_address;
    }

    public void setApply_address(String apply_address) {
        this.apply_address = apply_address;
    }

    public String getApply_first() {
        return apply_first;
    }

    public void setApply_first(String apply_first) {
        this.apply_first = apply_first;
    }

    public String getApply_second() {
        return apply_second;
    }

    public void setApply_second(String apply_second) {
        this.apply_second = apply_second;
    }

    public String getApply_size() {
        return apply_size;
    }

    public void setApply_size(String apply_size) {
        this.apply_size = apply_size;
    }

    public String getApply_shop() {
        return apply_shop;
    }

    public void setApply_shop(String apply_shop) {
        this.apply_shop = apply_shop;
    }

    public String getApply_join() {
        return apply_join;
    }

    public void setApply_join(String apply_join) {
        this.apply_join = apply_join;
    }

    public String getApply_number() {
        return apply_number;
    }

    public void setApply_number(String apply_number) {
        this.apply_number = apply_number;
    }

    public String getApply_compete() {
        return apply_compete;
    }

    public void setApply_compete(String apply_compete) {
        this.apply_compete = apply_compete;
    }

    public String getApply_area() {
        return apply_area;
    }

    public void setApply_area(String apply_area) {
        this.apply_area = apply_area;
    }

    public String getApply_suggest() {
        return apply_suggest;
    }

    public void setApply_suggest(String apply_suggest) {
        this.apply_suggest = apply_suggest;
    }

    public String getApply_company() {
        return apply_company;
    }

    public void setApply_company(String apply_company) {
        this.apply_company = apply_company;
    }

    public String getApply_remark() {
        return apply_remark;
    }

    public void setApply_remark(String apply_remark) {
        this.apply_remark = apply_remark;
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

    public String getApply_category_show() {
        return apply_category_show;
    }

    public void setApply_category_show(String apply_category_show) {
        this.apply_category_show = apply_category_show;
    }

    public String getApply_state_show() {
        return apply_state_show;
    }

    public void setApply_state_show(String apply_state_show) {
        this.apply_state_show = apply_state_show;
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

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getRecord_no() {
        return record_no;
    }

    public void setRecord_no(String record_no) {
        this.record_no = record_no;
    }

    public String getNext_staff_id() {
        return next_staff_id;
    }

    public void setNext_staff_id(String next_staff_id) {
        this.next_staff_id = next_staff_id;
    }

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }
}
