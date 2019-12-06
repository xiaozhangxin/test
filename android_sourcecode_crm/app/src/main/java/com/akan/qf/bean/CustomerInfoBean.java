package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/11/7.
 */

public class CustomerInfoBean implements Serializable{


    /**
     * info_id : 28
     * staff_id : 52
     * staff_name : 大哥
     * staff_department : 爱康企业集团（上海）有限公司/南方营销中心/苏皖区域
     * info_no : KHT2018110812776
     * area_manage : 我是负责人
     * customer_ground : 我是抬头
     * customer_tel : 13949176775
     * customer_no : 41152419930707321X
     * customer_name : 我是联系人
     * customer_address : 上海市 上海市 浦东新区
     * customer_number : 2
     * info_remark : 我是备注
     * is_wall : accept
     * info_category : customer
     * next_staff_id :
     * info_state : accept
     * record_no : 1
     * info_is_delete : 0
     * audit_staff_ids : 52
     * info_update_time : 2018-11-08 12:09:10
     * info_create_time : 2018-11-08 10:32:53
     * recordList : [{"record_id":56,"staff_id":52,"info_id":28,"record_name":"大哥","record_department":"爱康企业集团（上海）有限公司/南方营销中心/苏皖区域","record_state":"","record_remark":"Fghhw","record_create_time":"2018-11-08 12:09:10"}]
     * is_wall_show :
     * info_state_show : 展墙
     * staff_uuid :
     * group_parent_uuid :
     * group_uuid :
     * start_time :
     * end_time :
     */

    private String info_id;
    private String staff_id;
    private String staff_name;
    private String staff_department;
    private String info_no;
    private String area_manage;
    private String customer_ground;
    private String customer_tel;
    private String customer_no;
    private String customer_name;
    private String customer_address;
    private String customer_number;
    private String info_remark;
    private String is_wall;
    private String info_category;
    private String next_staff_id;
    private String info_state;
    private String record_no;
    private String info_is_delete;
    private String audit_staff_ids;
    private String info_update_time;
    private String info_create_time;
    private String is_wall_show;
    private String info_state_show;
    private String staff_uuid;
    private String group_parent_uuid;
    private String group_uuid;
    private String start_time;
    private String end_time;
    private List<RecordListBean> recordList;




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

    public String getInfo_no() {
        return info_no;
    }

    public void setInfo_no(String info_no) {
        this.info_no = info_no;
    }

    public String getArea_manage() {
        return area_manage;
    }

    public void setArea_manage(String area_manage) {
        this.area_manage = area_manage;
    }

    public String getCustomer_ground() {
        return customer_ground;
    }

    public void setCustomer_ground(String customer_ground) {
        this.customer_ground = customer_ground;
    }

    public String getCustomer_tel() {
        return customer_tel;
    }

    public void setCustomer_tel(String customer_tel) {
        this.customer_tel = customer_tel;
    }

    public String getCustomer_no() {
        return customer_no;
    }

    public void setCustomer_no(String customer_no) {
        this.customer_no = customer_no;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(String customer_number) {
        this.customer_number = customer_number;
    }

    public String getInfo_remark() {
        return info_remark;
    }

    public void setInfo_remark(String info_remark) {
        this.info_remark = info_remark;
    }

    public String getIs_wall() {
        return is_wall;
    }

    public void setIs_wall(String is_wall) {
        this.is_wall = is_wall;
    }

    public String getInfo_category() {
        return info_category;
    }

    public void setInfo_category(String info_category) {
        this.info_category = info_category;
    }

    public String getNext_staff_id() {
        return next_staff_id;
    }

    public void setNext_staff_id(String next_staff_id) {
        this.next_staff_id = next_staff_id;
    }

    public String getInfo_state() {
        return info_state;
    }

    public void setInfo_state(String info_state) {
        this.info_state = info_state;
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

    public String getRecord_no() {
        return record_no;
    }

    public void setRecord_no(String record_no) {
        this.record_no = record_no;
    }

    public String getInfo_is_delete() {
        return info_is_delete;
    }

    public void setInfo_is_delete(String info_is_delete) {
        this.info_is_delete = info_is_delete;
    }

    public String getAudit_staff_ids() {
        return audit_staff_ids;
    }

    public void setAudit_staff_ids(String audit_staff_ids) {
        this.audit_staff_ids = audit_staff_ids;
    }

    public String getInfo_update_time() {
        return info_update_time;
    }

    public void setInfo_update_time(String info_update_time) {
        this.info_update_time = info_update_time;
    }

    public String getInfo_create_time() {
        return info_create_time;
    }

    public void setInfo_create_time(String info_create_time) {
        this.info_create_time = info_create_time;
    }

    public String getIs_wall_show() {
        return is_wall_show;
    }

    public void setIs_wall_show(String is_wall_show) {
        this.is_wall_show = is_wall_show;
    }

    public String getInfo_state_show() {
        return info_state_show;
    }

    public void setInfo_state_show(String info_state_show) {
        this.info_state_show = info_state_show;
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

    public static class RecordListBean {
        /**
         * record_id : 56
         * staff_id : 52
         * info_id : 28
         * record_name : 大哥
         * record_department : 爱康企业集团（上海）有限公司/南方营销中心/苏皖区域
         * record_state :
         * record_remark : Fghhw
         * record_create_time : 2018-11-08 12:09:10
         */

        private int record_id;
        private int staff_id;
        private int info_id;
        private String record_name;
        private String record_department;
        private String record_state;
        private String record_remark;
        private String record_create_time;

        public int getRecord_id() {
            return record_id;
        }

        public void setRecord_id(int record_id) {
            this.record_id = record_id;
        }

        public int getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(int staff_id) {
            this.staff_id = staff_id;
        }

        public int getInfo_id() {
            return info_id;
        }

        public void setInfo_id(int info_id) {
            this.info_id = info_id;
        }

        public String getRecord_name() {
            return record_name;
        }

        public void setRecord_name(String record_name) {
            this.record_name = record_name;
        }

        public String getRecord_department() {
            return record_department;
        }

        public void setRecord_department(String record_department) {
            this.record_department = record_department;
        }

        public String getRecord_state() {
            return record_state;
        }

        public void setRecord_state(String record_state) {
            this.record_state = record_state;
        }

        public String getRecord_remark() {
            return record_remark;
        }

        public void setRecord_remark(String record_remark) {
            this.record_remark = record_remark;
        }

        public String getRecord_create_time() {
            return record_create_time;
        }

        public void setRecord_create_time(String record_create_time) {
            this.record_create_time = record_create_time;
        }
    }
}
