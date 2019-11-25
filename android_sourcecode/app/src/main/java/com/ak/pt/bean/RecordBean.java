package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/27.
 */

public class RecordBean implements Serializable {

    /**
     * record_id : 3
     * study_id : 2
     * staff_id : 4
     * record_name : 石磊
     * record_remark : 太可怜了
     * record_create_time : 2019-05-27 17:43:26
     */

    private String record_id;
    private String study_id;
    private String staff_id;
    private String record_name;
    private String record_remark;
    private String record_create_time;

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public String getStudy_id() {
        return study_id;
    }

    public void setStudy_id(String study_id) {
        this.study_id = study_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getRecord_name() {
        return record_name;
    }

    public void setRecord_name(String record_name) {
        this.record_name = record_name;
    }

    public String getRecord_remark() {
        return record_remark;
    }

    public void setRecord_remark(String record_remark) {
        this.record_remark = record_remark;
    }

    public String getRecord_create_time() {
        return record_create_time;
    }

    public void setRecord_create_time(String record_create_time) {
        this.record_create_time = record_create_time;
    }
}
