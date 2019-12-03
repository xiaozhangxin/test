package com.akan.wms.bean;

import java.io.Serializable;

public class BarBeanListBean implements Serializable {
    /**
     * id : 194
     * out_in_id : 9
     * item_id : 1001812190658887
     * item_bar : 41.11.011.1001
     * item_name : 一体式柔性保温管
     * item_code : 41.11.011.1001
     * item_spec : EIT-20-D25（黄色）
     * in_wh_id : 1001512260168341
     * out_wh_id : 1001512260168339
     */

    private int id;
    private int out_in_id;
    private long item_id;
    private String item_bar;
    private String item_name;
    private String item_code;
    private String item_spec;
    private long in_wh_id;
    private long out_wh_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOut_in_id() {
        return out_in_id;
    }

    public void setOut_in_id(int out_in_id) {
        this.out_in_id = out_in_id;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public String getItem_bar() {
        return item_bar;
    }

    public void setItem_bar(String item_bar) {
        this.item_bar = item_bar;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_spec() {
        return item_spec;
    }

    public void setItem_spec(String item_spec) {
        this.item_spec = item_spec;
    }

    public long getIn_wh_id() {
        return in_wh_id;
    }

    public void setIn_wh_id(long in_wh_id) {
        this.in_wh_id = in_wh_id;
    }

    public long getOut_wh_id() {
        return out_wh_id;
    }

    public void setOut_wh_id(long out_wh_id) {
        this.out_wh_id = out_wh_id;
    }
}