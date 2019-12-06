package com.akan.qf.bean;

import java.util.List;

/**
 * Created by admin on 2018/11/26.
 */

public class ContributeClassBeansBean {
    /**
     * class_id : 2
     * class_name : 目录1-1
     * parent_id : 1
     * parent_ids : 1
     * create_time : 2018-11-23 04:20:56
     * contributeClassBeans : []
     */

    private String class_id;
    private String class_name;
    private String parent_id;
    private String parent_ids;
    private String create_time;
    private  boolean isShow;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    private List<ContributeClassBeansBean> contributeClassBeans;

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

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_ids() {
        return parent_ids;
    }

    public void setParent_ids(String parent_ids) {
        this.parent_ids = parent_ids;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public List<ContributeClassBeansBean> getContributeClassBeans() {
        return contributeClassBeans;
    }

    public void setContributeClassBeans(List<ContributeClassBeansBean> contributeClassBeans) {
        this.contributeClassBeans = contributeClassBeans;
    }
}
