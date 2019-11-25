package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/3/26.
 */

public class DocumentBean  implements Serializable {


    public String getDocument_count() {
        return document_count;
    }

    public void setDocument_count(String document_count) {
        this.document_count = document_count;
    }

    /**
     * document_id : 1
     * staff_id : 47
     * staff_name : lv1
     * document_name : 图片
     * document_url : /images/others/20181225/1545730359546890391555.jpg
     * document_is_delete : 0
     * document_create_time : 2019-02-22 15:51:21
     * start_time :
     * end_time :

     */

    private String document_id;
    private String staff_id;
    private String staff_name;
    private String document_name;
    private String document_url;
    private String document_is_delete;
    private String document_create_time;
    private String start_time;
    private String end_time;
    private String document_count;

    public String getDocument_id() {
        return document_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
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

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }

    public String getDocument_url() {
        return document_url;
    }

    public void setDocument_url(String document_url) {
        this.document_url = document_url;
    }

    public String getDocument_is_delete() {
        return document_is_delete;
    }

    public void setDocument_is_delete(String document_is_delete) {
        this.document_is_delete = document_is_delete;
    }

    public String getDocument_create_time() {
        return document_create_time;
    }

    public void setDocument_create_time(String document_create_time) {
        this.document_create_time = document_create_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
