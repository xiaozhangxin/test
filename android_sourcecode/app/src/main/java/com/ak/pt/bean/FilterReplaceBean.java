package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/22.
 */

public class FilterReplaceBean implements Serializable {


    /**
     * filter_id : 24
     * staff_id : 113
     * staff_name : 二哥
     * department_name : 南方营销中心
     * customer_name : 呵呵
     * customer_sex : 先生
     * customer_tel : 431959894949
     * customer_address : 上海市 上海市 浦东新区
     * address_info : 闺蜜您
     * address_code : 200000
     * serve_shop : 咯哦咯公公
     * serve_name : 8英亩
     * serve_tel : 87984646
     * is_delete : 0
     * create_time : 2019-05-31 16:45:44
     * update_time : 2019-05-31 16:45:44
     * filter_no : LXTH2019053148178
     * group_no : 001001
     * job_name_list : []
     * upFileList : []
     * downFileList : []
     * productList : [{"product_id":27,"filter_id":24,"product_soft":"反渗透净水机","product_type":"CR400-K1","product_no":"6924010000321","product_name":"PP1,RO膜","create_time":"2019-05-31 16:45:44"},{"product_id":28,"filter_id":24,"product_soft":"超滤净水机","product_type":"CWUF-3100","product_no":"突突突6924010000321","product_name":"超滤膜,后置活性炭","create_time":"2019-05-31 16:45:44"}]
     * job_name : 外部水工
     * start_time : 
     * end_time : 
     * staff_uuid : 
     * group_parent_uuid : 
     * all_name : 
     */

    private String filter_id;
    private String staff_id;
    private String staff_name;
    private String department_name;
    private String customer_name;
    private String customer_sex;
    private String customer_tel;
    private String customer_address;
    private String address_info;
    private String address_code;
    private String serve_shop;
    private String serve_name;
    private String serve_tel;
    private String is_delete;
    private String create_time;
    private String update_time;
    private String filter_no;
    private String group_no;
    private String job_name;
    private String start_time;
    private String end_time;
    private String staff_uuid;
    private String group_parent_uuid;
    private String all_name;
    private String group_id;
    private List<DownFileBean> upFileList;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    private List<DownFileBean> downFileList;
    private List<FilterTypeBean> productList;

    public List<FilterTypeBean> getProductList() {
        return productList;
    }

    public void setProductList(List<FilterTypeBean> productList) {
        this.productList = productList;
    }

    public String getFilter_id() {

        return filter_id;
    }

    public void setFilter_id(String filter_id) {
        this.filter_id = filter_id;
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

    public String getServe_shop() {
        return serve_shop;
    }

    public void setServe_shop(String serve_shop) {
        this.serve_shop = serve_shop;
    }

    public String getServe_name() {
        return serve_name;
    }

    public void setServe_name(String serve_name) {
        this.serve_name = serve_name;
    }

    public String getServe_tel() {
        return serve_tel;
    }

    public void setServe_tel(String serve_tel) {
        this.serve_tel = serve_tel;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
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

    public String getFilter_no() {
        return filter_no;
    }

    public void setFilter_no(String filter_no) {
        this.filter_no = filter_no;
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

    public String getAll_name() {
        return all_name;
    }

    public void setAll_name(String all_name) {
        this.all_name = all_name;
    }


    public List<DownFileBean> getUpFileList() {
        return upFileList;
    }

    public void setUpFileList(List<DownFileBean> upFileList) {
        this.upFileList = upFileList;
    }

    public List<DownFileBean> getDownFileList() {
        return downFileList;
    }

    public void setDownFileList(List<DownFileBean> downFileList) {
        this.downFileList = downFileList;
    }




}
