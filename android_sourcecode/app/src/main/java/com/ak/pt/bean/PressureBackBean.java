package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/30.
 */

public class PressureBackBean implements Serializable {


    /**
     * back_id : 1
     * back_no : SYFX2019053072279
     * staff_id : 113
     * staff_name : 二哥
     * department_name : 南方营销中心
     * group_no : 001001
     * send_name : fghh
     * send_address : 地址给
     * send_tel : 5566
     * tool_name : 摩托车
     * order_no : 15875555566412
     * shop_name : 哈哈
     * back_remark : 9666
     * receipt_name : 85666
     * receipt_tel : 太可怜了
     * receipt_address : 上海市静安区
     * remark : 备注
     * next_audit_id : 4
     * is_delete : 0
     * back_state : wait_audit
     * staff_audit : ,113,4,
     * create_time : 2019-05-30 11:32:44
     * update_time : 2019-05-30 11:32:44
     * job_name_list : []
     * is_audit : 
     * back_state_show : 未审核
     * job_name : 外部水工
     * start_time : 
     * end_time : 
     * staff_uuid : 
     * group_parent_uuid : 
     * all_name : 
     */

    private String back_id;
    private String back_no;
    private String staff_id;
    private String staff_name;
    private String department_name;
    private String group_no;
    private String send_name;
    private String send_address;
    private String send_tel;
    private String tool_name;
    private String order_no;
    private String shop_name;
    private String back_remark;
    private String receipt_name;
    private String receipt_tel;
    private String receipt_address;
    private String remark;
    private String next_audit_id;
    private String is_delete;
    private String back_state;
    private String staff_audit;
    private String create_time;
    private String update_time;
    private String is_audit;
    private String back_state_show;

    public List<PeopleRecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<PeopleRecordListBean> recordList) {
        this.recordList = recordList;
    }

    private String job_name;
    private String start_time;
    private String end_time;
    private String staff_uuid;
    private String group_parent_uuid;
    private String all_name;
    private List<?> job_name_list;
    private List<PeopleRecordListBean> recordList;

    public String getBack_id() {
        return back_id;
    }

    public void setBack_id(String back_id) {
        this.back_id = back_id;
    }

    public String getBack_no() {
        return back_no;
    }

    public void setBack_no(String back_no) {
        this.back_no = back_no;
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

    public String getSend_name() {
        return send_name;
    }

    public void setSend_name(String send_name) {
        this.send_name = send_name;
    }

    public String getSend_address() {
        return send_address;
    }

    public void setSend_address(String send_address) {
        this.send_address = send_address;
    }

    public String getSend_tel() {
        return send_tel;
    }

    public void setSend_tel(String send_tel) {
        this.send_tel = send_tel;
    }

    public String getTool_name() {
        return tool_name;
    }

    public void setTool_name(String tool_name) {
        this.tool_name = tool_name;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getBack_remark() {
        return back_remark;
    }

    public void setBack_remark(String back_remark) {
        this.back_remark = back_remark;
    }

    public String getReceipt_name() {
        return receipt_name;
    }

    public void setReceipt_name(String receipt_name) {
        this.receipt_name = receipt_name;
    }

    public String getReceipt_tel() {
        return receipt_tel;
    }

    public void setReceipt_tel(String receipt_tel) {
        this.receipt_tel = receipt_tel;
    }

    public String getReceipt_address() {
        return receipt_address;
    }

    public void setReceipt_address(String receipt_address) {
        this.receipt_address = receipt_address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNext_audit_id() {
        return next_audit_id;
    }

    public void setNext_audit_id(String next_audit_id) {
        this.next_audit_id = next_audit_id;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getBack_state() {
        return back_state;
    }

    public void setBack_state(String back_state) {
        this.back_state = back_state;
    }

    public String getStaff_audit() {
        return staff_audit;
    }

    public void setStaff_audit(String staff_audit) {
        this.staff_audit = staff_audit;
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

    public String getIs_audit() {
        return is_audit;
    }

    public void setIs_audit(String is_audit) {
        this.is_audit = is_audit;
    }

    public String getBack_state_show() {
        return back_state_show;
    }

    public void setBack_state_show(String back_state_show) {
        this.back_state_show = back_state_show;
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

    public String getAll_name() {
        return all_name;
    }

    public void setAll_name(String all_name) {
        this.all_name = all_name;
    }

    public List<?> getJob_name_list() {
        return job_name_list;
    }

    public void setJob_name_list(List<?> job_name_list) {
        this.job_name_list = job_name_list;
    }
}
