package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/28.
 */

public class SummaryInfoBean implements Serializable{


    /**
     * month_department : 部门
     * month_name : 负责人
     * month_test : 3
     * month_finish : 1
     * finish_rate : 完成率
     * remark : 备注
     */

    private String month_department;
    private String month_name;
    private int month_test;
    private int month_finish;
    private String finish_rate;
    private String remark;
    private String info_id;
    private String month_id;
    private String create_time;
    private String group_id;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public String getMonth_id() {
        return month_id;
    }

    public void setMonth_id(String month_id) {
        this.month_id = month_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getMonth_department() {
        return month_department;
    }

    public void setMonth_department(String month_department) {
        this.month_department = month_department;
    }

    public String getMonth_name() {
        return month_name;
    }

    public void setMonth_name(String month_name) {
        this.month_name = month_name;
    }

    public int getMonth_test() {
        return month_test;
    }

    public void setMonth_test(int month_test) {
        this.month_test = month_test;
    }

    public int getMonth_finish() {
        return month_finish;
    }

    public void setMonth_finish(int month_finish) {
        this.month_finish = month_finish;
    }

    public String getFinish_rate() {
        return finish_rate;
    }

    public void setFinish_rate(String finish_rate) {
        this.finish_rate = finish_rate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
