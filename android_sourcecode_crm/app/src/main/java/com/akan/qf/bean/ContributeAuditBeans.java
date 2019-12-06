package com.akan.qf.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/2/15.
 */

public class ContributeAuditBeans implements Serializable{

    /**
     * audit_id : 5
     * contribute_id : 44
     * staff_id : 47
     * staff_name : lv1
     * audit_content : Uuuuuuiii
     * create_time : 2019-02-14 17:08:36
     */

    private String audit_id;
    private String contribute_id;
    private String staff_id;
    private String staff_name;
    private String audit_content;
    private String create_time;

    public String getAudit_id() {
        return audit_id;
    }

    public void setAudit_id(String audit_id) {
        this.audit_id = audit_id;
    }

    public String getContribute_id() {
        return contribute_id;
    }

    public void setContribute_id(String contribute_id) {
        this.contribute_id = contribute_id;
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

    public String getAudit_content() {
        return audit_content;
    }

    public void setAudit_content(String audit_content) {
        this.audit_content = audit_content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
