package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/7/5.
 */

public class staffGroupBeans implements Serializable{


    /**
     * group_id : 118
     * group_no : 001002003002
     * group_name : 山东民用
     * group_name_staff_number : 山东民用(在职1人)
     * parent_name : 爱康企业集团（上海）有限公司/北方营销中心/山东/山东民用
     * parent_id : 116
     * is_delete : 0
     * group_type : 1
     * sort : 0
     * create_time : 2018-06-25 10:35:50
     * group_uuid : 7a915be5-7820-11e8-a4a1-309c230d890b
     * parent_uuid : 7a6c1907-7820-11e8-a4a1-309c230d890b#7a88d781-7820-11e8-a4a1-309c230d890b#7a90d3c2-7820-11e8-a4a1-309c230d890b#7a915be5-7820-11e8-a4a1-309c230d890b#
     * staff_number : 1
     * chinese_name :
     * english_name :
     * company_address :
     * zip_code :
     * company_tel :
     * fax :
     * open_bank_name :
     * bank_no :
     * ein :
     * head :
     * email :
     * url :
     * logo :
     * note :
     * staffGroupBeans : []
     */

    private String group_id;
    private String parent_id;
    private String group_no;
    private String group_name;
    private String group_uuid;
    private String group_type;

    private List<staffGroupBeans> staffGroupBeans;


    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }



    public String getGroup_type() {
        return group_type;
    }

    public void setGroup_type(String group_type) {
        this.group_type = group_type;
    }


    public String getGroup_uuid() {
        return group_uuid;
    }

    public void setGroup_uuid(String group_uuid) {
        this.group_uuid = group_uuid;
    }



    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }


    public List<staffGroupBeans> getStaffGroupBeans() {
        return staffGroupBeans;
    }

    public void setStaffGroupBeans(List<staffGroupBeans> staffGroupBeans) {
        this.staffGroupBeans = staffGroupBeans;
    }
}
