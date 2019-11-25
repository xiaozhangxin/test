package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/1/9.
 */

public class AreaPressureBean  implements Serializable {


    /**
     * start_time :
     * end_time :
     * area :
     * group_name : 浙江大区
     * group_no :
     * count_start :
     * count_end :
     * big_area :
     * if_stop :
     * type :
     * fullArea :
     * count : 0
     */

    private String start_time;
    private String end_time;
    private String area;
    private String group_name;
    private String group_no;
    private String count_start;
    private String count_end;
    private String big_area;
    private String if_stop;
    private String type;
    private String fullArea;
    private String count;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public String getCount_start() {
        return count_start;
    }

    public void setCount_start(String count_start) {
        this.count_start = count_start;
    }

    public String getCount_end() {
        return count_end;
    }

    public void setCount_end(String count_end) {
        this.count_end = count_end;
    }

    public String getBig_area() {
        return big_area;
    }

    public void setBig_area(String big_area) {
        this.big_area = big_area;
    }

    public String getIf_stop() {
        return if_stop;
    }

    public void setIf_stop(String if_stop) {
        this.if_stop = if_stop;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullArea() {
        return fullArea;
    }

    public void setFullArea(String fullArea) {
        this.fullArea = fullArea;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
