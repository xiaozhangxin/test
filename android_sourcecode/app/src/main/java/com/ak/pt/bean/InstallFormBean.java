package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/31.
 */

public class InstallFormBean implements Serializable{


    /**
     * module_name :
     * name :
     * number : 0
     * g_number : 4
     * n_number : 0
     * all_number : 4
     * percent :
     * tel :
     * department_name : 东海县李正楠
     * pressure_type :
     * balcony :
     * kitchen :
     * toilet :
     */

    private String module_name;
    private String name;
    private String number;
    private String g_number;
    private String n_number;
    private String all_number;
    private String percent;
    private String tel;
    private String department_name;
    private String pressure_type;
    private String balcony;
    private String kitchen;
    private String toilet;
    private String type;

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getG_number() {
        return g_number;
    }

    public void setG_number(String g_number) {
        this.g_number = g_number;
    }

    public String getN_number() {
        return n_number;
    }

    public void setN_number(String n_number) {
        this.n_number = n_number;
    }

    public String getAll_number() {
        return all_number;
    }

    public void setAll_number(String all_number) {
        this.all_number = all_number;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getPressure_type() {
        return pressure_type;
    }

    public void setPressure_type(String pressure_type) {
        this.pressure_type = pressure_type;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public String getToilet() {
        return toilet;
    }

    public void setToilet(String toilet) {
        this.toilet = toilet;
    }
}
