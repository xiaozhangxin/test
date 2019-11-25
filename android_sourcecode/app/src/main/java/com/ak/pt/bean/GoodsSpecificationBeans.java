package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/10.
 */

public class GoodsSpecificationBeans implements Serializable{


    /**
     * specification_id : 353
     * goods_id : 113
     * specification_state : 1
     * specification_sku : FE18ZT6DDA
     * specification_names : 无分类
     * specification_sales : 0
     * specification_stock : 100
     * specification_img : 
     * specification_price : 200
     * create_time : 2018-10-19 17:24:02
     * update_time : 2018-10-19 17:24:02
     * is_delete : 0
     */

    private String specification_id;
    private String goods_id;
    private String specification_state;
    private String specification_sku;
    private String specification_names;
    private String specification_sales;
    private int specification_stock;
    private String specification_img;
    private String specification_price;
    private String create_time;
    private String update_time;
    private String is_delete;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getSpecification_id() {
        return specification_id;
    }

    public void setSpecification_id(String specification_id) {
        this.specification_id = specification_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getSpecification_state() {
        return specification_state;
    }

    public void setSpecification_state(String specification_state) {
        this.specification_state = specification_state;
    }

    public String getSpecification_sku() {
        return specification_sku;
    }

    public void setSpecification_sku(String specification_sku) {
        this.specification_sku = specification_sku;
    }

    public String getSpecification_names() {
        return specification_names;
    }

    public void setSpecification_names(String specification_names) {
        this.specification_names = specification_names;
    }

    public String getSpecification_sales() {
        return specification_sales;
    }

    public void setSpecification_sales(String specification_sales) {
        this.specification_sales = specification_sales;
    }

    public int getSpecification_stock() {
        return specification_stock;
    }

    public void setSpecification_stock(int specification_stock) {
        this.specification_stock = specification_stock;
    }

    public String getSpecification_img() {
        return specification_img;
    }

    public void setSpecification_img(String specification_img) {
        this.specification_img = specification_img;
    }

    public String getSpecification_price() {
        return specification_price;
    }

    public void setSpecification_price(String specification_price) {
        this.specification_price = specification_price;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }
}
