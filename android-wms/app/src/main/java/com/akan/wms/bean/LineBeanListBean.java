package com.akan.wms.bean;

import java.io.Serializable;

public class LineBeanListBean implements Serializable {
    /**
     * id : 194
     * out_in_id : 9
     * apply_line_id : 1001906058213615
     * item_id : 1001812190658887
     * qty : 237
     * remark :
     * in_wh_id : 1001512260168341
     * out_wh_id : 1001512260168339
     * apply_qty : 237
     * item_code : 41.11.011.1001
     * item_name : 一体式柔性保温管
     * item_spec : EIT-20-D25（黄色）
     */

    private int id;
    private int out_in_id;
    private long apply_line_id;
    private long item_id;
    private int qty;
    private String remark;
    private long in_wh_id;
    private long out_wh_id;
    private int apply_qty;
    private String item_code;
    private String item_name;
    private String item_spec;
    private String supplier_name;

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

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

    public long getApply_line_id() {
        return apply_line_id;
    }

    public void setApply_line_id(long apply_line_id) {
        this.apply_line_id = apply_line_id;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public int getApply_qty() {
        return apply_qty;
    }

    public void setApply_qty(int apply_qty) {
        this.apply_qty = apply_qty;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_spec() {
        return item_spec;
    }

    public void setItem_spec(String item_spec) {
        this.item_spec = item_spec;
    }
}