package com.akan.qf.bean;

import java.util.List;

/**
 * Created by admin on 2018/10/29.
 */

public class AddReburseBean {


    /**
     * info_appoint_audit : 指定审批人ID
     * info_reimbursement_time : 报销日期
     * info_name : 申请人
     * info_department : 申请者部门
     * info_remarks : 报销单备注
     * textList : [{"text_price":"报销项价格","text_subject":"报销科目","text_number":"科目数量","text_info":"科目简介"}]
     * fileList : [{"file_name":"文件名","file_info":"文件简介","file_size":"文件大小","file_url":"文件路径","up_name":"上传人"}]
     */

    private String info_id;
    private String info_appoint_audit;
    private String info_reimbursement_time;
    private String info_name;
    private String info_department;
    private String info_price;

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    private String info_remarks;

    public String getInfo_price() {
        return info_price;
    }

    public void setInfo_price(String info_price) {
        this.info_price = info_price;
    }

    private List<TextListBean> textList;
    private List<FileListBean> fileList;

    public String getInfo_appoint_audit() {
        return info_appoint_audit;
    }

    public void setInfo_appoint_audit(String info_appoint_audit) {
        this.info_appoint_audit = info_appoint_audit;
    }

    public String getInfo_reimbursement_time() {
        return info_reimbursement_time;
    }

    public void setInfo_reimbursement_time(String info_reimbursement_time) {
        this.info_reimbursement_time = info_reimbursement_time;
    }

    public String getInfo_name() {
        return info_name;
    }

    public void setInfo_name(String info_name) {
        this.info_name = info_name;
    }

    public String getInfo_department() {
        return info_department;
    }

    public void setInfo_department(String info_department) {
        this.info_department = info_department;
    }

    public String getInfo_remarks() {
        return info_remarks;
    }

    public void setInfo_remarks(String info_remarks) {
        this.info_remarks = info_remarks;
    }

    public List<TextListBean> getTextList() {
        return textList;
    }

    public void setTextList(List<TextListBean> textList) {
        this.textList = textList;
    }

    public List<FileListBean> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileListBean> fileList) {
        this.fileList = fileList;
    }




}
