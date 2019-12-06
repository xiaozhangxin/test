package com.akan.qf.bean;

import java.io.Serializable;

public class ContributeCommentBeansBean implements Serializable {
    /**
     * comment_id : 1
     * contribute_id : 1
     * staff_id : 1
     * staff_name : 方林
     * head_img : /images/others/logo.png
     * content : 这是评论
     * create_time : 2018-11-23 06:16:27
     */

    private String comment_id;
    private String contribute_id;
    private String staff_id;
    private String staff_name;
    private String head_img;
    private String content;
    private String create_time;

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getContribute_id() {
        return contribute_id;
    }

    public void setContribute_id(String contribute_id) {
        this.contribute_id = contribute_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}