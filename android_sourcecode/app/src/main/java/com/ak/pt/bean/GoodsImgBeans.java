package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/10.
 */

public class GoodsImgBeans implements Serializable{


    /**
     * img_id : 2
     * goods_id : 113
     * img_url : 
     * sort : 1
     * create_time : 2019-05-10 12:19:12
     * update_time : 
     * is_delete : 0
     */

    private String img_id;
    private String goods_id;
    private String img_url;
    private String sort;
    private String create_time;
    private String update_time;
    private String is_delete;

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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
