package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/29.
 */

public class PeopleBean implements Serializable {


    /**
     * add_id : 11
     * add_no : RYTJ2019052982025
     * staff_id : 163
     * staff_name : 水工
     * department_name : 南方营销中心
     * group_no : 001001
     * add_city : 滴滴滴哦
     * add_shop : 哦LOL墨迹
     * shop_name : 哦咯咯
     * shop_tel : 哦咯咯
     * serve_name : 女哦咯咯
     * serve_tel : 12898865995
     * person_num : 56
     * pressure_num : 666
     * photo_tool : 一种
     * vehicle : 农民
     * next_audit_id : 4
     * add_state : wait_audit
     * is_delete :
     * update_time : 2019-05-29 16:57:55
     * create_time : 2019-05-29 16:57:55
     * staff_audit : ,163,4,
     * recordList : [{"record_id":9,"add_id":"","staff_id":163,"record_name":"水工","record_remark":"","record_state":"wait_audit","record_create_time":"2019-05-29 16:57:55","record_state_show":"发出申请"},{"record_id":10,"add_id":"","staff_id":163,"record_name":"石磊","record_remark":"","record_state":"unAuditing","record_create_time":"","record_state_show":"审核中"}]
     * infoList : [{"info_id":13,"add_id":11,"info_name":"女哦咯咯","info_tel":"94961679","type":"pressure","create_time":"2019-05-29 16:57:55"}]
     * infoClerkList : [{"info_id":12,"add_id":11,"info_name":"民工","info_tel":"13948564","type":"clerk","create_time":"2019-05-29 16:57:55"}]
     * job_name_list : []
     * remark :
     * is_audit :
     * add_state_show : 未审核
     * job_name : 外部水工
     * start_time :
     * end_time :
     * staff_uuid :
     * group_parent_uuid :
     * all_name :
     */

    private String add_id;
    private String add_no;
    private String staff_id;
    private String staff_name;
    private String department_name;
    private String group_no;
    private String add_city;
    private String add_shop;
    private String shop_name;
    private String shop_tel;
    private String serve_name;
    private String serve_tel;
    private String person_num;
    private String pressure_num;
    private String photo_tool;
    private String vehicle;
    private String next_audit_id;
    private String add_state;
    private String is_delete;
    private String update_time;
    private String create_time;
    private String staff_audit;
    private String remark;
    private String is_audit;
    private String add_state_show;
    private String job_name;
    private String start_time;
    private String end_time;
    private String staff_uuid;
    private String group_parent_uuid;
    private String all_name;
    private List<PeopleRecordListBean> recordList;
    private List<PeopleAddBean> infoList;
    private List<PeopleAddBean> infoClerkList;


    public String getBig_area() {
        return big_area;
    }

    public void setBig_area(String big_area) {
        this.big_area = big_area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    //  大区
    private String big_area;
    //  区域
    private String area;

    public String getAdd_id() {
        return add_id;
    }

    public void setAdd_id(String add_id) {
        this.add_id = add_id;
    }

    public String getAdd_no() {
        return add_no;
    }

    public void setAdd_no(String add_no) {
        this.add_no = add_no;
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

    public String getAdd_city() {
        return add_city;
    }

    public void setAdd_city(String add_city) {
        this.add_city = add_city;
    }

    public String getAdd_shop() {
        return add_shop;
    }

    public void setAdd_shop(String add_shop) {
        this.add_shop = add_shop;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_tel() {
        return shop_tel;
    }

    public void setShop_tel(String shop_tel) {
        this.shop_tel = shop_tel;
    }

    public String getServe_name() {
        return serve_name;
    }

    public void setServe_name(String serve_name) {
        this.serve_name = serve_name;
    }

    public String getServe_tel() {
        return serve_tel;
    }

    public void setServe_tel(String serve_tel) {
        this.serve_tel = serve_tel;
    }

    public String getPerson_num() {
        return person_num;
    }

    public void setPerson_num(String person_num) {
        this.person_num = person_num;
    }

    public String getPressure_num() {
        return pressure_num;
    }

    public void setPressure_num(String pressure_num) {
        this.pressure_num = pressure_num;
    }

    public String getPhoto_tool() {
        return photo_tool;
    }

    public void setPhoto_tool(String photo_tool) {
        this.photo_tool = photo_tool;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getNext_audit_id() {
        return next_audit_id;
    }

    public void setNext_audit_id(String next_audit_id) {
        this.next_audit_id = next_audit_id;
    }

    public String getAdd_state() {
        return add_state;
    }

    public void setAdd_state(String add_state) {
        this.add_state = add_state;
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

    public String getStaff_audit() {
        return staff_audit;
    }

    public void setStaff_audit(String staff_audit) {
        this.staff_audit = staff_audit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIs_audit() {
        return is_audit;
    }

    public void setIs_audit(String is_audit) {
        this.is_audit = is_audit;
    }

    public String getAdd_state_show() {
        return add_state_show;
    }

    public void setAdd_state_show(String add_state_show) {
        this.add_state_show = add_state_show;
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

    public List<PeopleRecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<PeopleRecordListBean> recordList) {
        this.recordList = recordList;
    }

    public List<PeopleAddBean> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<PeopleAddBean> infoList) {
        this.infoList = infoList;
    }

    public List<PeopleAddBean> getInfoClerkList() {
        return infoClerkList;
    }

    public void setInfoClerkList(List<PeopleAddBean> infoClerkList) {
        this.infoClerkList = infoClerkList;
    }


    public static class RecordListBean {
        /**
         * record_id : 9
         * add_id :
         * staff_id : 163
         * record_name : 水工
         * record_remark :
         * record_state : wait_audit
         * record_create_time : 2019-05-29 16:57:55
         * record_state_show : 发出申请
         */

        private String record_id;
        private String add_id;
        private String staff_id;
        private String record_name;
        private String record_remark;
        private String record_state;
        private String record_create_time;
        private String record_state_show;

        public String getRecord_id() {
            return record_id;
        }

        public void setRecord_id(String record_id) {
            this.record_id = record_id;
        }

        public String getAdd_id() {
            return add_id;
        }

        public void setAdd_id(String add_id) {
            this.add_id = add_id;
        }

        public String getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(String staff_id) {
            this.staff_id = staff_id;
        }

        public String getRecord_name() {
            return record_name;
        }

        public void setRecord_name(String record_name) {
            this.record_name = record_name;
        }

        public String getRecord_remark() {
            return record_remark;
        }

        public void setRecord_remark(String record_remark) {
            this.record_remark = record_remark;
        }

        public String getRecord_state() {
            return record_state;
        }

        public void setRecord_state(String record_state) {
            this.record_state = record_state;
        }

        public String getRecord_create_time() {
            return record_create_time;
        }

        public void setRecord_create_time(String record_create_time) {
            this.record_create_time = record_create_time;
        }

        public String getRecord_state_show() {
            return record_state_show;
        }

        public void setRecord_state_show(String record_state_show) {
            this.record_state_show = record_state_show;
        }
    }


}
