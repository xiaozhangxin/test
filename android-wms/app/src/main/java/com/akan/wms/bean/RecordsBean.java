package com.akan.wms.bean;

import java.io.Serializable;

public  class RecordsBean implements Serializable{


    /**
     * id : 200
     * org_id : 1001512200010027
     * doc_id : 66
     * create_id : 6002
     * create_name : AK116
     * remark : 装车
     * create_time : 2019-08-21 14:29:56
     * doc_type : XSCK
     * head_img : /images/others/20190821/156635540744080432795.jpg
     */

    private String id;
    private String org_id;
    private String doc_id;
    private String create_id;
    private String create_name;
    private String remark;
    private String create_time;
    private String doc_type;
    private String head_img;
    private String staff_name;

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getCreate_id() {
        return create_id;
    }

    public void setCreate_id(String create_id) {
        this.create_id = create_id;
    }

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }
}
