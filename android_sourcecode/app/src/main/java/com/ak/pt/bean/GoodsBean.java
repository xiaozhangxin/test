package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/10.
 */

public class GoodsBean implements Serializable{


    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    /**
     * goods_id : 104
     * group_id : 1
     * group_no : 001001019
     * class_id : 92
     * class_name : 
     * goods_no : 
     * goods_name : 康夫吹风机家用理发店电吹风机专业2300W大功率风筒冷热风不伤发
     * goods_img : /images/goods/20181019/15399255329711915092727.png
     * goods_min_price : 300
     * goods_max_price : 300
     * goods_now_price : 300.0
     * goods_desc : 2300w大功率 速干秀发，发廊通用
     强劲风力出风口 可拆卸双层进风口，一键自然风 六档调节 沙龙级造型
     * goods_url : /html/goods/201810191311531814592143.html
     * goods_url_content : 
     * total_sales : 0
     * goods_stock : 300
     * goods_state : 1
     * goods_state_show : 
     * is_delete : 0
     * create_time : 2018-10-19 13:06:13
     * update_time : 2019-05-10 10:30:56
     * staff_id : 
     * show_price : 300
     * order : 
     * goodsImgBeans : []
     * goodsSpecificationBeans : []
     * goodsClassBean : {}
     */

    private boolean isFirst;
    private String goods_id;
    private String group_id;
    private String group_no;
    private String class_id;
    private String class_name;
    private String goods_no;
    private String goods_name;
    private String goods_img;
    private String goods_min_price;
    private String goods_max_price;
    private String goods_now_price;
    private String goods_desc;
    private String goods_url;
    private String goods_url_content;
    private String total_sales;
    private int goods_stock;
    private String goods_state;
    private String goods_state_show;
    private String is_delete;
    private String create_time;
    private String update_time;
    private String staff_id;
    private String show_price;
    private String order;
    private GoodsClassBeanBean goodsClassBean;
    private List<GoodsImgBeans> goodsImgBeans;
    private List<GoodsSpecificationBeans> goodsSpecificationBeans;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getGoods_no() {
        return goods_no;
    }

    public void setGoods_no(String goods_no) {
        this.goods_no = goods_no;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getGoods_min_price() {
        return goods_min_price;
    }

    public void setGoods_min_price(String goods_min_price) {
        this.goods_min_price = goods_min_price;
    }

    public String getGoods_max_price() {
        return goods_max_price;
    }

    public void setGoods_max_price(String goods_max_price) {
        this.goods_max_price = goods_max_price;
    }

    public String getGoods_now_price() {
        return goods_now_price;
    }

    public void setGoods_now_price(String goods_now_price) {
        this.goods_now_price = goods_now_price;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public String getGoods_url() {
        return goods_url;
    }

    public void setGoods_url(String goods_url) {
        this.goods_url = goods_url;
    }

    public String getGoods_url_content() {
        return goods_url_content;
    }

    public void setGoods_url_content(String goods_url_content) {
        this.goods_url_content = goods_url_content;
    }

    public String getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(String total_sales) {
        this.total_sales = total_sales;
    }

    public int getGoods_stock() {
        return goods_stock;
    }

    public void setGoods_stock(int goods_stock) {
        this.goods_stock = goods_stock;
    }

    public String getGoods_state() {
        return goods_state;
    }

    public void setGoods_state(String goods_state) {
        this.goods_state = goods_state;
    }

    public String getGoods_state_show() {
        return goods_state_show;
    }

    public void setGoods_state_show(String goods_state_show) {
        this.goods_state_show = goods_state_show;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
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

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getShow_price() {
        return show_price;
    }

    public void setShow_price(String show_price) {
        this.show_price = show_price;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public GoodsClassBeanBean getGoodsClassBean() {
        return goodsClassBean;
    }

    public void setGoodsClassBean(GoodsClassBeanBean goodsClassBean) {
        this.goodsClassBean = goodsClassBean;
    }

    public List<GoodsImgBeans> getGoodsImgBeans() {
        return goodsImgBeans;
    }

    public void setGoodsImgBeans(List<GoodsImgBeans> goodsImgBeans) {
        this.goodsImgBeans = goodsImgBeans;
    }

    public List<GoodsSpecificationBeans> getGoodsSpecificationBeans() {
        return goodsSpecificationBeans;
    }

    public void setGoodsSpecificationBeans(List<GoodsSpecificationBeans> goodsSpecificationBeans) {
        this.goodsSpecificationBeans = goodsSpecificationBeans;
    }

    public static class GoodsClassBeanBean {
    }
}
