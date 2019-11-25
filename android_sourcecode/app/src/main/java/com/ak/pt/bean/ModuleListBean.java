package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/4/29.
 */

public class ModuleListBean implements Serializable {


    /**
     * module_id : 3
     * drop_id : 5
     * module_num : 8
     * module_time : 15
     * module_pressure : 1
     * module_create_time : 2019-04-29 17:06:12
     */

    private String module_id;
    private String drop_id;
    private String module_num;
    private String module_time;
    private String module_pressure;
    private String module_create_time;
    private String mState;

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getDrop_id() {
        return drop_id;
    }

    public void setDrop_id(String drop_id) {
        this.drop_id = drop_id;
    }

    public String getModule_num() {
        return module_num;
    }

    public void setModule_num(String module_num) {
        this.module_num = module_num;
    }

    public String getModule_time() {
        return module_time;
    }

    public void setModule_time(String module_time) {
        this.module_time = module_time;
    }

    public String getModule_pressure() {
        return module_pressure;
    }

    public void setModule_pressure(String module_pressure) {
        this.module_pressure = module_pressure;
    }

    public String getModule_create_time() {
        return module_create_time;
    }

    public void setModule_create_time(String module_create_time) {
        this.module_create_time = module_create_time;
    }
}
