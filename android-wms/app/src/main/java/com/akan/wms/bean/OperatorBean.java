package com.akan.wms.bean;

import java.io.Serializable;

public class OperatorBean implements Serializable {


    /**
     * operator_id : 1001712281047821
     * operator_name : 陈小珺
     * operator_org : 1001512200010027
     * operator_code : 18014
     * is_effective : 1
     * operator_type : 2
     * dept : 1001712280796493
     * org_id : {}
     */

    private long operator_id;
    private String operator_name;
    private long operator_org;
    private String operator_code;
    private String is_effective;
    private String operator_type;
    private long dept;
    private OrgIdBean org_id;

    public long getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(long operator_id) {
        this.operator_id = operator_id;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    public long getOperator_org() {
        return operator_org;
    }

    public void setOperator_org(long operator_org) {
        this.operator_org = operator_org;
    }

    public String getOperator_code() {
        return operator_code;
    }

    public void setOperator_code(String operator_code) {
        this.operator_code = operator_code;
    }

    public String getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(String is_effective) {
        this.is_effective = is_effective;
    }

    public String getOperator_type() {
        return operator_type;
    }

    public void setOperator_type(String operator_type) {
        this.operator_type = operator_type;
    }

    public long getDept() {
        return dept;
    }

    public void setDept(long dept) {
        this.dept = dept;
    }

    public OrgIdBean getOrg_id() {
        return org_id;
    }

    public void setOrg_id(OrgIdBean org_id) {
        this.org_id = org_id;
    }

    public static class OrgIdBean {
    }
}
