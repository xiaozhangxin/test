package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/11/26.
 */

public class ContributeBean implements Serializable{

    private List<StaffSignUnionBean> staffSignUnionList;

    public List<StaffSignUnionBean> getStaffSignUnionList() {
        return staffSignUnionList;
    }

    public void setStaffSignUnionList(List<StaffSignUnionBean> staffSignUnionList) {
        this.staffSignUnionList = staffSignUnionList;
    }
    /**
     * id : 8
     * staff_id : 47
     * staff_name : lv1
     * head_img : /images/others/logo.png
     * title : De
     * tag : Dede’s
     * content : Seeded
     * class_id : 2
     * class_ids : 1,2
     * read_count : 12
     * create_time : 2018-11-26 04:25:51
     * files : 
     * contributeCommentBeans : [{"comment_id":1,"contribute_id":1,"staff_id":1,"staff_name":"方林","head_img":"/images/others/logo.png","content":"这是评论","create_time":"2018-11-23 06:16:27"},{"comment_id":4,"contribute_id":7,"staff_id":47,"staff_name":"lv1","head_img":"/images/others/logo.png","content":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","create_time":"2018-11-26 03:08:25"},{"comment_id":5,"contribute_id":8,"staff_id":53,"staff_name":"二哥","head_img":"/images/member/20181123/1542953303846266492251.jpg","content":"哈哈","create_time":"2018-11-26 06:36:31"}]
     */

    private String id;
    private String staff_id;
    private String staff_name;
    private String head_img;
    private String title;
    private String tag;
    private String content;
    private String class_id;
    private String class_ids;
    private String read_count;
    private String create_time;
    private String files;
    private String state;
    private String total;

    private List<ContributeAuditBeans> contributeAuditBeans;
    private List<ContributeCommentBeansBean> contributeCommentBeans;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    public String getId() {
        return id;
    }

    public List<ContributeAuditBeans> getContributeAuditBeans() {
        return contributeAuditBeans;
    }

    public void setContributeAuditBeans(List<ContributeAuditBeans> contributeAuditBeans) {
        this.contributeAuditBeans = contributeAuditBeans;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_ids() {
        return class_ids;
    }

    public void setClass_ids(String class_ids) {
        this.class_ids = class_ids;
    }

    public String getRead_count() {
        return read_count;
    }

    public void setRead_count(String read_count) {
        this.read_count = read_count;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public List<ContributeCommentBeansBean> getContributeCommentBeans() {
        return contributeCommentBeans;
    }

    public void setContributeCommentBeans(List<ContributeCommentBeansBean> contributeCommentBeans) {
        this.contributeCommentBeans = contributeCommentBeans;
    }


}
