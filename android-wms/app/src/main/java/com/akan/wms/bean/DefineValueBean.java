package com.akan.wms.bean;

import java.io.Serializable;

public class DefineValueBean implements Serializable {


    /**
     * define_id : 1001601050164782
     * define_code : 01
     * define_name : 营销中心
     * value_set_def : 1001512200010446
     * is_effective : 1
     */

    private long define_id;
    private String define_code;
    private String define_name;
    private long value_set_def;
    private String is_effective;

    public long getDefine_id() {
        return define_id;
    }

    public void setDefine_id(long define_id) {
        this.define_id = define_id;
    }

    public String getDefine_code() {
        return define_code;
    }

    public void setDefine_code(String define_code) {
        this.define_code = define_code;
    }

    public String getDefine_name() {
        return define_name;
    }

    public void setDefine_name(String define_name) {
        this.define_name = define_name;
    }

    public long getValue_set_def() {
        return value_set_def;
    }

    public void setValue_set_def(long value_set_def) {
        this.value_set_def = value_set_def;
    }

    public String getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(String is_effective) {
        this.is_effective = is_effective;
    }
}
