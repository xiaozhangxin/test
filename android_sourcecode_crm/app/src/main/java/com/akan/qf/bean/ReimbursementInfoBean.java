package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/10/29.
 */

public class ReimbursementInfoBean implements Serializable{


    /**
     * info_id : 77
     * staff_id : 47
     * info_number : FYBX2018102964961
     * info_name : lv1
     * info_department : 爱康企业集团（上海）有限公司/南方营销中心/苏皖区域/皖北区域
     * info_price : 118.0
     * info_remarks : 你好、你好、好了……这里也很de
     * info_state : wait_audit
     * info_is_delete : 0
     * audit_no : 47
     * audit_state : 0
     * info_img : 
     * info_refuse_text : 
     * info_appoString_audit : 45
     * info_application_time : 2018-10-29 16:18:38
     * info_reimbursement_time : 2018-10-29 00:00:00
     * info_create_time : 2018-10-29 16:18:38
     * textList : [{"text_id":186,"info_id":77,"text_price":"59","text_subject":"饿的e","text_number":2,"text_info":"好了\u2026\u2026这里也很美呢\u2026\u2026你好！你好、在下我不","text_is_delete":"0","text_create_time":"2018-10-29 16:18:38"}]
     * fileList : [{"file_id":78,"info_id":77,"file_name":"","file_info":"","file_size":"258 KB","file_url":"/images/others/20181029/15408011133981495914456.jpg","up_name":"lv1","file_is_delete":"0","file_create_time":"2018-10-29 16:18:38"},{"file_id":79,"info_id":77,"file_name":"","file_info":"","file_size":"253 KB","file_url":"/images/others/20181029/15408011134011608382054.jpg","up_name":"lv1","file_is_delete":"0","file_create_time":"2018-10-29 16:18:38"}]
     * recordList : [{"record_id":111,"info_id":77,"record_name":"lv1","record_remark":"你好、你好、好了\u2026\u2026这里也很de","record_state":"wait_audit","depart_name":"爱康企业集团（上海）有限公司/南方营销中心/苏皖区域/皖北区域","record_is_delete":"0","record_create_time":"2018-10-29 16:18:38","record_state_show":"发出申请"}]
     * info_state_text : 待审核
     * audit_name : 
     * audit_department : 
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

    public String getStaff_sign_name() {
        return staff_sign_name;
    }

    public void setStaff_sign_name(String staff_sign_name) {
        this.staff_sign_name = staff_sign_name;
    }

    private String staff_sign_name;
    private String next_audit_staff_name;
    private String next_audit_staff_head_img;
    private String info_id;
    private String staff_id;
    private String info_number;
    private String info_name;
    private String info_department;
    private String info_price;
    private String info_remarks;
    private String info_state;
    private String info_is_delete;
    private String audit_no;
    private String audit_state;
    private String info_img;
    private String info_refuse_text;
    private String info_appoint_audit;
    private String info_application_time;
    private String info_reimbursement_time;
    private String info_create_time;
    private String info_state_text;
    private String audit_name;
    private String audit_department;
    private String is_egis;
    private String staff_uuid;
    private String group_parent_uuid;
    private String group_uuid;
    private String start_time;
    private String end_time;
    private List<TextListBean> textList;
    private List<FileListBean> fileList;
    private List<RecordListBean> recordList;



    public String getInfo_number() {
        return info_number;
    }

    public void setInfo_number(String info_number) {
        this.info_number = info_number;
    }

    public String getInfo_name() {
        return info_name;
    }

    public void setInfo_name(String info_name) {
        this.info_name = info_name;
    }

    public String getInfo_department() {
        return info_department;
    }

    public void setInfo_department(String info_department) {
        this.info_department = info_department;
    }

    public String getInfo_price() {
        return info_price;
    }

    public void setInfo_price(String info_price) {
        this.info_price = info_price;
    }

    public String getInfo_remarks() {
        return info_remarks;
    }

    public void setInfo_remarks(String info_remarks) {
        this.info_remarks = info_remarks;
    }

    public String getInfo_state() {
        return info_state;
    }

    public void setInfo_state(String info_state) {
        this.info_state = info_state;
    }

    public String getInfo_is_delete() {
        return info_is_delete;
    }

    public void setInfo_is_delete(String info_is_delete) {
        this.info_is_delete = info_is_delete;
    }

    public String getAudit_no() {
        return audit_no;
    }

    public void setAudit_no(String audit_no) {
        this.audit_no = audit_no;
    }

    public String getAudit_state() {
        return audit_state;
    }

    public void setAudit_state(String audit_state) {
        this.audit_state = audit_state;
    }

    public String getInfo_img() {
        return info_img;
    }

    public void setInfo_img(String info_img) {
        this.info_img = info_img;
    }

    public String getInfo_refuse_text() {
        return info_refuse_text;
    }

    public void setInfo_refuse_text(String info_refuse_text) {
        this.info_refuse_text = info_refuse_text;
    }

 

    public String getInfo_application_time() {
        return info_application_time;
    }

    public void setInfo_application_time(String info_application_time) {
        this.info_application_time = info_application_time;
    }

    public String getInfo_reimbursement_time() {
        return info_reimbursement_time;
    }

    public void setInfo_reimbursement_time(String info_reimbursement_time) {
        this.info_reimbursement_time = info_reimbursement_time;
    }

    public String getInfo_create_time() {
        return info_create_time;
    }

    public void setInfo_create_time(String info_create_time) {
        this.info_create_time = info_create_time;
    }

    public String getInfo_state_text() {
        return info_state_text;
    }

    public void setInfo_state_text(String info_state_text) {
        this.info_state_text = info_state_text;
    }

    public String getAudit_name() {
        return audit_name;
    }

    public void setAudit_name(String audit_name) {
        this.audit_name = audit_name;
    }

    public String getAudit_department() {
        return audit_department;
    }

    public void setAudit_department(String audit_department) {
        this.audit_department = audit_department;
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

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getInfo_appoint_audit() {
        return info_appoint_audit;
    }

    public void setInfo_appoint_audit(String info_appoint_audit) {
        this.info_appoint_audit = info_appoint_audit;
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

    public List<TextListBean> getTextList() {
        return textList;
    }

    public void setTextList(List<TextListBean> textList) {
        this.textList = textList;
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
