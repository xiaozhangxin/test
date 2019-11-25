package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/7.
 */

public class AddressBean implements Serializable {


    /**
     * address_id : 86
     * staff_id : 113
     * address_flag : 
     * address_mobile : 856 695 5699
     * address_name : 兰陵王
     * address_province : 上海市
     * address_city : 上海市
     * address_district : 杨浦区
     * address_detail : 江杨北路216874
     * create_time : 
     * update_time : 
     * is_default : 1
     * is_delete : 0
     */

    private String address_id;
    private String staff_id;
    private String address_flag;
    private String address_mobile;
    private String address_name;
    private String address_province;
    private String address_city;
    private String address_district;
    private String address_detail;
    private String create_time;
    private String update_time;
    private String is_default;
    private String is_delete;

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getAddress_flag() {
        return address_flag;
    }

    public void setAddress_flag(String address_flag) {
        this.address_flag = address_flag;
    }

    public String getAddress_mobile() {
        return address_mobile;
    }

    public void setAddress_mobile(String address_mobile) {
        this.address_mobile = address_mobile;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public String getAddress_province() {
        return address_province;
    }

    public void setAddress_province(String address_province) {
        this.address_province = address_province;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddress_district() {
        return address_district;
    }

    public void setAddress_district(String address_district) {
        this.address_district = address_district;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }
}
