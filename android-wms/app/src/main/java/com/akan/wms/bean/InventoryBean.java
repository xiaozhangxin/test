package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class InventoryBean implements Serializable{


    /**
     * inventory_id : 10
     * staff_id : 6003
     * org_id : 1001512200010027
     * inventory_no : PDLP2019071988884
     * inventory_wh : 1001512260168424
     * memo : 盘点测试
     * audit_id :
     * is_delete : 0
     * inventory_status : 0
     * create_time : 2019-07-19 12:02:12
     * edit_time : 2019-07-19 12:02:12
     * infoList : []
     * staff_name : 文云会
     * audit_name :
     * inventory_wh_name : 内销成品仓-PPR管件
     * inventory_status_name : 作废
     * start_time :
     * end_time :
     */

    private int inventory_id;
    private String staff_id;
    private long org_id;
    private String inventory_no;
    private long inventory_wh;
    private String memo;
    private String audit_id;
    private int is_delete;
    private String inventory_status;
    private String create_time;
    private String edit_time;
    private String staff_name;
    private String audit_name;
    private String inventory_wh_name;
    private String inventory_status_name;
    private String start_time;
    private String end_time;
    private String is_valid;

    public List<RecordsBean> getRecordsBeans() {
        return recordsBeans;
    }

    public void setRecordsBeans(List<RecordsBean> recordsBeans) {
        this.recordsBeans = recordsBeans;
    }

    private List<InfoBeanList> infoList;
    private List<RecordsBean> recordsBeans;

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public String getInventory_no() {
        return inventory_no;
    }

    public void setInventory_no(String inventory_no) {
        this.inventory_no = inventory_no;
    }

    public long getInventory_wh() {
        return inventory_wh;
    }

    public void setInventory_wh(long inventory_wh) {
        this.inventory_wh = inventory_wh;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getAudit_id() {
        return audit_id;
    }

    public void setAudit_id(String audit_id) {
        this.audit_id = audit_id;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public String getInventory_status() {
        return inventory_status;
    }

    public void setInventory_status(String inventory_status) {
        this.inventory_status = inventory_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(String edit_time) {
        this.edit_time = edit_time;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getAudit_name() {
        return audit_name;
    }

    public void setAudit_name(String audit_name) {
        this.audit_name = audit_name;
    }

    public String getInventory_wh_name() {
        return inventory_wh_name;
    }

    public void setInventory_wh_name(String inventory_wh_name) {
        this.inventory_wh_name = inventory_wh_name;
    }

    public String getInventory_status_name() {
        return inventory_status_name;
    }

    public void setInventory_status_name(String inventory_status_name) {
        this.inventory_status_name = inventory_status_name;
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

    public List<InfoBeanList> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<InfoBeanList> infoList) {
        this.infoList = infoList;
    }
}
