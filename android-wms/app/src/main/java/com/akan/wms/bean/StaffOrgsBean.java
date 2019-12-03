package com.akan.wms.bean;

import java.io.Serializable;

public class StaffOrgsBean implements Serializable{


    /**
     * id : 1001512200010455
     * code : 102
     * name : 爱康企业集团河南分公司
     */

    private String id;
    private String code;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
