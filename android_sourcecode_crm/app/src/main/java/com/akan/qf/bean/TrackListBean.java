package com.akan.qf.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/4/4.
 */

public class TrackListBean implements Serializable {


    /**
     * track_id : 195
     * apply_id : 45
     * track_remark : 还莫
     * track_type : JZGS
     * track_create_time : 2019-04-04 12:05:14
     */

    private String track_id;
    private String apply_id;
    private String track_remark;
    private String track_type;
    private String track_create_time;

    public String getTrack_id() {
        return track_id;
    }

    public void setTrack_id(String track_id) {
        this.track_id = track_id;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getTrack_remark() {
        return track_remark;
    }

    public void setTrack_remark(String track_remark) {
        this.track_remark = track_remark;
    }

    public String getTrack_type() {
        return track_type;
    }

    public void setTrack_type(String track_type) {
        this.track_type = track_type;
    }

    public String getTrack_create_time() {
        return track_create_time;
    }

    public void setTrack_create_time(String track_create_time) {
        this.track_create_time = track_create_time;
    }
}
