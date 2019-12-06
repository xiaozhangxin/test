package com.akan.qf.bean;

/**
 * Created by admin on 2018/12/24.
 */

public class ClassList {


    /**
     * class_id : 2
     * class_name : PVC
     * sort : 0
     * create_time : 2018-10-30 10:55:25
     */

    private String class_id;
    private String class_name;
    private String sort;
    private String create_time;

    public ClassList(String class_id, String class_name, String sort, String create_time) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.sort = sort;
        this.create_time = create_time;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
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
}
