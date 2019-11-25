package com.ak.pt.bean;

import java.io.Serializable;

public class LeavePerpleBean implements Serializable{
    public LeavePerpleBean(String type) {
        this.info_sign = type;
    }


    /**
     * info_id : 87
     * quit_id : 26
     * staff_id : 295
     * info_sign : 1
     * staff_name : 周经销
     * tel : 18438552938
     * create_time : 2019-10-18 17:29:53
     */

    private String info_id;
    private String quit_id;
    private String staff_id;
    private String info_sign;
    private String staff_name;
    private String tel;
    private String create_time;

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public String getQuit_id() {
        return quit_id;
    }

    public void setQuit_id(String quit_id) {
        this.quit_id = quit_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getInfo_sign() {
        return info_sign;
    }

    public void setInfo_sign(String info_sign) {
        this.info_sign = info_sign;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}