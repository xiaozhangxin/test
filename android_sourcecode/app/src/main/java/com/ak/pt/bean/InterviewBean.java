package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/28.
 */

public class InterviewBean implements Serializable{


    /**
     * interview_id : 1
     * interview_no : QYZF2019052820647
     * staff_id : 113
     * staff_name : 二哥
     * department_name : 南方营销中心
     * group_no : 001001
     * shop_name : 名称
     * shop_serve : 数量
     * shop_pressure : 试压量
     * shop_status : 现状
     * shop_need : 足
     * shop_suggest : 联系
     * remark : 试压量
     * interview_state : wait_audit
     * is_delete : 0
     * interview_time : 2019-05-28
     * create_time : 2019-05-28 14:31:38
     * update_time : 2019-05-28 14:31:38
     * fileList : [{"file_id":1,"interview_id":1,"file_name":"","file_url":"/images/area/20190528/1559025098647243130311.jpg","create_time":"2019-05-28 14:31:38"}]
     * recordList : []
     * job_name_list : []
     * interview_state_show : 待审阅
     * job_name : 外部水工
     * start_time : 
     * end_time : 
     * staff_uuid : 
     * group_parent_uuid : 
     */

    private String interview_id;
    private String interview_no;
    private String staff_id;
    private String staff_name;
    private String department_name;
    private String group_no;
    private String shop_name;
    private String shop_serve;
    private String shop_pressure;
    private String shop_status;
    private String shop_need;
    private String shop_suggest;
    private String remark;
    private String interview_state;
    private String is_delete;
    private String interview_time;
    private String create_time;
    private String update_time;
    private String interview_state_show;
    private String job_name;
    private String start_time;
    private String end_time;
    private String staff_uuid;
    private String group_parent_uuid;
    private List<FixFileBean> fileList;
    private List<RecordBean> recordList;
    private List<?> job_name_list;

    public String getInterview_id() {
        return interview_id;
    }

    public void setInterview_id(String interview_id) {
        this.interview_id = interview_id;
    }

    public String getInterview_no() {
        return interview_no;
    }

    public void setInterview_no(String interview_no) {
        this.interview_no = interview_no;
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

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_serve() {
        return shop_serve;
    }

    public void setShop_serve(String shop_serve) {
        this.shop_serve = shop_serve;
    }

    public String getShop_pressure() {
        return shop_pressure;
    }

    public void setShop_pressure(String shop_pressure) {
        this.shop_pressure = shop_pressure;
    }

    public String getShop_status() {
        return shop_status;
    }

    public void setShop_status(String shop_status) {
        this.shop_status = shop_status;
    }

    public String getShop_need() {
        return shop_need;
    }

    public void setShop_need(String shop_need) {
        this.shop_need = shop_need;
    }

    public String getShop_suggest() {
        return shop_suggest;
    }

    public void setShop_suggest(String shop_suggest) {
        this.shop_suggest = shop_suggest;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInterview_state() {
        return interview_state;
    }

    public void setInterview_state(String interview_state) {
        this.interview_state = interview_state;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getInterview_time() {
        return interview_time;
    }

    public void setInterview_time(String interview_time) {
        this.interview_time = interview_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getInterview_state_show() {
        return interview_state_show;
    }

    public void setInterview_state_show(String interview_state_show) {
        this.interview_state_show = interview_state_show;
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

    public List<FixFileBean> getFileList() {
        return fileList;
    }

    public void setFileList(List<FixFileBean> fileList) {
        this.fileList = fileList;
    }

    public List<RecordBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordBean> recordList) {
        this.recordList = recordList;
    }

    public List<?> getJob_name_list() {
        return job_name_list;
    }

    public void setJob_name_list(List<?> job_name_list) {
        this.job_name_list = job_name_list;
    }

   
}
