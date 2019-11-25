package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/31.
 */

public class FilterTypeBean implements Serializable{


    /**
     * product_soft : 产品系别
     * product_type : 产品类别
     * product_no : 产品编号
     * product_name : 故障详情
     */

    private String product_soft;
    private String product_type;
    private String product_no;
    private String product_name;
    private String product_id;
    private String filter_id;
    private String create_time;


    public String getProduct_soft() {
        return product_soft;
    }

    public void setProduct_soft(String product_soft) {
        this.product_soft = product_soft;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_no() {
        return product_no;
    }

    public void setProduct_no(String product_no) {
        this.product_no = product_no;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
