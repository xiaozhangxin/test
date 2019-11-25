package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/23.
 */

public class WarrantyBean implements Serializable {


    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    /**
     * card_id : 12
     * staff_id : 47
     * staff_name : 试压工lv1
     * department_name : 南方营销中心
     * customer_name : 用户姓名
     * customer_sex : 先生
     * customer_tel : 17621399953
     * customer_address : 上海市-上海市-静安区
     * address_info : 详细地址
     * address_code : 邮编
     * product_series1 : 中央净水机系列-CWAF-C05
     * product_category1 : 128395586665
     * product_no1 :
     * product_series2 :
     * product_category2 :
     * product_no2 :
     * product_series3 :
     * product_category3 :
     * product_no3 :
     * product_series4 :
     * product_category4 :
     * product_no4 :
     * product_series5 :
     * product_category5 :
     * product_no5 :
     * water_pressure : 原水水压
     * service_provider : 服务商信息
     * install_name : 安装技师
     * install_tel : 安装电话
     * install_no :
     * install_time : 2019-05-20 00:00:00
     * edit_time : 2019-05-20 12:25:09
     * create_time : 2019-05-20 12:25:09
     * is_delete : 0
     * water_quality : 原水水质
     * card_no :
     * group_no :
     * job_name_list : []
     * imgSuccessList : []
     * imgBillList : []
     * imgOtherList : []
     * job_name : 试压工
     * start_time :
     * end_time :
     * staff_uuid :
     * group_parent_uuid :
     */


    private String card_id;
    private String group_id;
    private String staff_id;
    private String staff_name;
    private String department_name;
    private String customer_name;
    private String customer_sex;
    private String customer_tel;
    private String customer_address;
    private String address_info;
    private String address_code;
    private String product_series1;
    private String product_category1;
    private String product_no1;
    private String product_series2;
    private String product_category2;
    private String product_no2;
    private String product_series3;
    private String product_category3;
    private String product_no3;
    private String product_series4;
    private String product_category4;
    private String product_no4;
    private String product_series5;
    private String product_category5;
    private String product_no5;
    private String service_tel;

    public String getService_tel() {
        return service_tel;
    }

    public void setService_tel(String service_tel) {
        this.service_tel = service_tel;
    }

    private String water_pressure;
    private String service_provider;
    private String install_name;
    private String install_tel;
    private String install_no;
    private String install_time;
    private String edit_time;
    private String create_time;
    private String is_delete;
    private String water_quality;

    public List<FilterTypeBean> getProductList() {
        return productList;
    }

    public void setProductList(List<FilterTypeBean> productList) {
        this.productList = productList;
    }

    private String card_no;
    private String group_no;
    private String job_name;
    private String start_time;
    private String end_time;
    private String staff_uuid;
    private String group_parent_uuid;
    private List<?> job_name_list;
    private List<FilterTypeBean> productList;
    private List<WarrantyFileBean> imgSuccessList;
    private List<WarrantyFileBean> imgBillList;
    private List<WarrantyFileBean> imgOtherList;

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_sex() {
        return customer_sex;
    }

    public void setCustomer_sex(String customer_sex) {
        this.customer_sex = customer_sex;
    }

    public String getCustomer_tel() {
        return customer_tel;
    }

    public void setCustomer_tel(String customer_tel) {
        this.customer_tel = customer_tel;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getAddress_info() {
        return address_info;
    }

    public void setAddress_info(String address_info) {
        this.address_info = address_info;
    }

    public String getAddress_code() {
        return address_code;
    }

    public void setAddress_code(String address_code) {
        this.address_code = address_code;
    }

    public String getProduct_series1() {
        return product_series1;
    }

    public void setProduct_series1(String product_series1) {
        this.product_series1 = product_series1;
    }

    public String getProduct_category1() {
        return product_category1;
    }

    public void setProduct_category1(String product_category1) {
        this.product_category1 = product_category1;
    }

    public String getProduct_no1() {
        return product_no1;
    }

    public void setProduct_no1(String product_no1) {
        this.product_no1 = product_no1;
    }

    public String getProduct_series2() {
        return product_series2;
    }

    public void setProduct_series2(String product_series2) {
        this.product_series2 = product_series2;
    }

    public String getProduct_category2() {
        return product_category2;
    }

    public void setProduct_category2(String product_category2) {
        this.product_category2 = product_category2;
    }

    public String getProduct_no2() {
        return product_no2;
    }

    public void setProduct_no2(String product_no2) {
        this.product_no2 = product_no2;
    }

    public String getProduct_series3() {
        return product_series3;
    }

    public void setProduct_series3(String product_series3) {
        this.product_series3 = product_series3;
    }

    public String getProduct_category3() {
        return product_category3;
    }

    public void setProduct_category3(String product_category3) {
        this.product_category3 = product_category3;
    }

    public String getProduct_no3() {
        return product_no3;
    }

    public void setProduct_no3(String product_no3) {
        this.product_no3 = product_no3;
    }

    public String getProduct_series4() {
        return product_series4;
    }

    public void setProduct_series4(String product_series4) {
        this.product_series4 = product_series4;
    }

    public String getProduct_category4() {
        return product_category4;
    }

    public void setProduct_category4(String product_category4) {
        this.product_category4 = product_category4;
    }

    public String getProduct_no4() {
        return product_no4;
    }

    public void setProduct_no4(String product_no4) {
        this.product_no4 = product_no4;
    }

    public String getProduct_series5() {
        return product_series5;
    }

    public void setProduct_series5(String product_series5) {
        this.product_series5 = product_series5;
    }

    public String getProduct_category5() {
        return product_category5;
    }

    public void setProduct_category5(String product_category5) {
        this.product_category5 = product_category5;
    }

    public String getProduct_no5() {
        return product_no5;
    }

    public void setProduct_no5(String product_no5) {
        this.product_no5 = product_no5;
    }

    public String getWater_pressure() {
        return water_pressure;
    }

    public void setWater_pressure(String water_pressure) {
        this.water_pressure = water_pressure;
    }

    public String getService_provider() {
        return service_provider;
    }

    public void setService_provider(String service_provider) {
        this.service_provider = service_provider;
    }

    public String getInstall_name() {
        return install_name;
    }

    public void setInstall_name(String install_name) {
        this.install_name = install_name;
    }

    public String getInstall_tel() {
        return install_tel;
    }

    public void setInstall_tel(String install_tel) {
        this.install_tel = install_tel;
    }

    public String getInstall_no() {
        return install_no;
    }

    public void setInstall_no(String install_no) {
        this.install_no = install_no;
    }

    public String getInstall_time() {
        return install_time;
    }

    public void setInstall_time(String install_time) {
        this.install_time = install_time;
    }

    public String getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(String edit_time) {
        this.edit_time = edit_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getWater_quality() {
        return water_quality;
    }

    public void setWater_quality(String water_quality) {
        this.water_quality = water_quality;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

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

    public String getStaff_uuid() {
        return staff_uuid;
    }

    public void setStaff_uuid(String staff_uuid) {
        this.staff_uuid = staff_uuid;
    }

    public String getGroup_parent_uuid() {
        return group_parent_uuid;
    }

    public void setGroup_parent_uuid(String group_parent_uuid) {
        this.group_parent_uuid = group_parent_uuid;
    }

    public List<?> getJob_name_list() {
        return job_name_list;
    }

    public void setJob_name_list(List<?> job_name_list) {
        this.job_name_list = job_name_list;
    }

    public List<WarrantyFileBean> getImgSuccessList() {
        return imgSuccessList;
    }

    public void setImgSuccessList(List<WarrantyFileBean> imgSuccessList) {
        this.imgSuccessList = imgSuccessList;
    }

    public List<WarrantyFileBean> getImgBillList() {
        return imgBillList;
    }

    public void setImgBillList(List<WarrantyFileBean> imgBillList) {
        this.imgBillList = imgBillList;
    }

    public List<WarrantyFileBean> getImgOtherList() {
        return imgOtherList;
    }

    public void setImgOtherList(List<WarrantyFileBean> imgOtherList) {
        this.imgOtherList = imgOtherList;
    }
}
