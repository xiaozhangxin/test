package com.akan.qf.bean;

/**
 * Created by admin on 2018/11/5.
 */

public class BannerBean {


    /**
     * banner_id : 3
     * banner_title : 这是轮播图
     * banner_desc : 详情
     * banner_img : /images/others/test.png
     * banner_url : www.baidu.com
     * banner_url_content :
     * banner_type : common
     * sort : 54
     * is_delete : 0
     * create_time : 2018-11-01 14:01:14
     */

    private String banner_id;
    private String banner_title;
    private String banner_desc;
    private String banner_img;
    private String banner_url;
    private String banner_url_content;
    private String banner_type;
    private String sort;
    private String is_delete;
    private String create_time;



    public String getBanner_title() {
        return banner_title;
    }

    public void setBanner_title(String banner_title) {
        this.banner_title = banner_title;
    }

    public String getBanner_desc() {
        return banner_desc;
    }

    public void setBanner_desc(String banner_desc) {
        this.banner_desc = banner_desc;
    }

    public String getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(String banner_img) {
        this.banner_img = banner_img;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    public String getBanner_url_content() {
        return banner_url_content;
    }

    public void setBanner_url_content(String banner_url_content) {
        this.banner_url_content = banner_url_content;
    }

    public String getBanner_type() {
        return banner_type;
    }

    public void setBanner_type(String banner_type) {
        this.banner_type = banner_type;
    }

    public String getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
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
}
