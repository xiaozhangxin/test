package com.akan.wms.bean;

import java.io.Serializable;

public class InfoCodeBean implements Serializable{


    /**
     * item_id : 对应行id
     * bar_code : 条码
     * code : 料品code
     * name : 料品名
     */

    private String item_id;
    private String code_num;
    private String bar_code;
    private String code;

    public String getCode_num() {
        return code_num;
    }

    public void setCode_num(String code_num) {
        this.code_num = code_num;
    }

    private String name;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
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
