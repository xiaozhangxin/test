package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/11/5.
 */

public class PayApplyBean implements Serializable{


    /**
     * apply_id : 18
     * staff_id : 47
     * apply_name : lv1
     * apply_coin : 255
     * apply_up : 255
     * apply_number :
     * apply_category : office
     * apply_way : telegraph
     * apply_no : FKD2018110609846
     * apply_bank : 2566
     * apply_department : 爱康企业集团（上海）有限公司/南方营销中心
     * apply_accept : 好了……不
     * apply_accept_department : 爱康企业集团（上海）有限公司/南方营销中心
     * next_audit_staff_id : 58
     * apply_state : wait_audit
     * record_no : 0
     * apply_remark : 在线等急电话联系！不过现在也有自己了，好了、在一起就是这么简单吗来袭！你的孩子都没有什么地方要好好学习的
     * apply_use : 买衣服
     * apply_bank_no : 1235841
     * apply_is_delete : 0
     * apply_update_time : 2018-11-06 12:26:47
     * apply_create_time : 2018-11-06 12:26:47
     * recordList : [{"record_id":"","apply_id":"","staff_id":47,"record_name":"","record_department":"","record_state":"","record_remark":"","record_create_time":"","record_state_show":""}]
     * fileList : []
     * apply_state_show : 待审核
     * is_egis :
     * staff_uuid :
     * group_parent_uuid :
     * group_uuid :
     * start_time :
     * end_time :
     * apply_category_show :
     * apply_way_show :
     */

    private List<StaffSignUnionBean> staffSignUnionList;

    public List<StaffSignUnionBean> getStaffSignUnionList() {
        return staffSignUnionList;
    }

    public void setStaffSignUnionList(List<StaffSignUnionBean> staffSignUnionList) {
        this.staffSignUnionList = staffSignUnionList;
    }
    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    private String job_name;
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
    private String staff_sign_name;
    private String next_audit_staff_name;

    public String getStaff_sign_name() {
        return staff_sign_name;
    }

    public void setStaff_sign_name(String staff_sign_name) {
        this.staff_sign_name = staff_sign_name;
    }

    private String next_audit_staff_head_img;
    private String apply_id;
    private String staff_id;
    private String apply_name;
    private String apply_coin;
    private String apply_up;
    private String apply_number;
    private String apply_category;
    private String apply_way;
    private String apply_no;
    private String apply_bank;
    private String apply_department;
    private String apply_accept;
    private String apply_accept_department;
    private String next_audit_staff_id;
    private String apply_state;
    private String record_no;
    private String apply_remark;
    private String apply_use;
    private String apply_bank_no;
    private String apply_is_delete;
    private String apply_update_time;
    private String apply_create_time;
    private String apply_state_show;
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

    public String getNext_audit_staff_id() {
        return next_audit_staff_id;
    }

    public void setNext_audit_staff_id(String next_audit_staff_id) {
        this.next_audit_staff_id = next_audit_staff_id;
    }

    public String getRecord_no() {
        return record_no;
    }

    public void setRecord_no(String record_no) {
        this.record_no = record_no;
    }

    public String getApply_name() {
        return apply_name;
    }

    public void setApply_name(String apply_name) {
        this.apply_name = apply_name;
    }

    public String getApply_coin() {
        return apply_coin;
    }

    public void setApply_coin(String apply_coin) {
        this.apply_coin = apply_coin;
    }

    public String getApply_up() {
        return apply_up;
    }

    public void setApply_up(String apply_up) {
        this.apply_up = apply_up;
    }

    public String getApply_number() {
        return apply_number;
    }

    public void setApply_number(String apply_number) {
        this.apply_number = apply_number;
    }

    public String getApply_category() {
        return apply_category;
    }

    public void setApply_category(String apply_category) {
        this.apply_category = apply_category;
    }

    public String getApply_way() {
        return apply_way;
    }

    public void setApply_way(String apply_way) {
        this.apply_way = apply_way;
    }

    public String getApply_no() {
        return apply_no;
    }

    public void setApply_no(String apply_no) {
        this.apply_no = apply_no;
    }

    public String getApply_bank() {
        return apply_bank;
    }

    public void setApply_bank(String apply_bank) {
        this.apply_bank = apply_bank;
    }

    public String getApply_department() {
        return apply_department;
    }

    public void setApply_department(String apply_department) {
        this.apply_department = apply_department;
    }

    public String getApply_accept() {
        return apply_accept;
    }

    public void setApply_accept(String apply_accept) {
        this.apply_accept = apply_accept;
    }

    public String getApply_accept_department() {
        return apply_accept_department;
    }

    public void setApply_accept_department(String apply_accept_department) {
        this.apply_accept_department = apply_accept_department;
    }



    public String getApply_state() {
        return apply_state;
    }

    public void setApply_state(String apply_state) {
        this.apply_state = apply_state;
    }



    public String getApply_remark() {
        return apply_remark;
    }

    public void setApply_remark(String apply_remark) {
        this.apply_remark = apply_remark;
    }

    public String getApply_use() {
        return apply_use;
    }

    public void setApply_use(String apply_use) {
        this.apply_use = apply_use;
    }

    public String getApply_bank_no() {
        return apply_bank_no;
    }

    public void setApply_bank_no(String apply_bank_no) {
        this.apply_bank_no = apply_bank_no;
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
