package com.akan.wms.bean;

import java.io.Serializable;

public class ClassTeamBean implements Serializable{


    /**
     * code : 1
     * name : A
     * id : 1001601100177740
     */

    private String code;
    private String name;
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
