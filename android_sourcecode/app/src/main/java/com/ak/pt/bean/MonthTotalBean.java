package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/28.
 */

public class MonthTotalBean implements Serializable{


    /**
     * month_id : 2
     * staff_id : 42
     * staff_name : 陈艳涛
     * department_name : 南方营销中心
     * group_no : 001001
     * month_no : YZJB2019052726993
     * year : 2019
     * month : 03
     * month_state : wait_audit
     * is_delete : 0
     * update_time : 2019-05-27 18:05:21
     * create_time : 2019-05-27 18:05:21
     * fileList : [{"file_id":1,"month_id":2,"file_name":"附件名","file_url":"附件地址","create_time":"2019-05-27 18:05:22"}]
     * recordList : []
     * infoList : [{"info_id":1,"month_id":2,"month_department":"V型菜单","month_name":"发的","month_test":3,"month_finish":1,"finish_rate":"地方","remark":"的发v","create_time":"2019-05-28 18:13:53"}]
     * job_name_list : []
     * remark : 
     * month_state_show : 待审阅
     * job_name : 营销总监
     * start_time : 
     * end_time : 
     * staff_uuid : 
     * group_parent_uuid : 
     */

    private String month_id;
    private String staff_id;
    private String staff_name;
    private String department_name;
    private String group_no;
    private String month_no;
    private String year;
    private String month;
    private String month_state;
    private String is_delete;
    private String update_time;
    private String create_time;
    private String remark;
    private String month_state_show;
    private String job_name;
    private String start_time;
    private String end_time;
    private String staff_uuid;
    private String group_parent_uuid;
    private List<FixFileBean> fileList;
    private List<RecordBean> recordList;
    private List<SummaryInfoBean> infoList;

    public String getMonth_id() {
        return month_id;
    }

    public void setMonth_id(String month_id) {
        this.month_id = month_id;
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

    public String getMonth_no() {
        return month_no;
    }

    public void setMonth_no(String month_no) {
        this.month_no = month_no;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonth_state() {
        return month_state;
    }

    public void setMonth_state(String month_state) {
        this.month_state = month_state;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMonth_state_show() {
        return month_state_show;
    }

    public void setMonth_state_show(String month_state_show) {
        this.month_state_show = month_state_show;
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

    public List<SummaryInfoBean> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<SummaryInfoBean> infoList) {
        this.infoList = infoList;
    }


}
