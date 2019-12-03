package com.akan.wms.bean;

import java.io.Serializable;

public class CodeListBean implements Serializable {
    /**
     * info_id : 对应料品id
     * bar_code : 二维码
     * code : 料品号
     * name : 料品名
     */

    private String info_id;
    private String bar_code;
    private String code;
    private String name;
    private String code_num;

    public String getCode_num() {
        return code_num;
    }

    public void setCode_num(String code_num) {
        this.code_num = code_num;
    }

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
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