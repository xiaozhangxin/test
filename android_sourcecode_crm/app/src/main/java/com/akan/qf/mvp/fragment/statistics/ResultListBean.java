package com.akan.qf.mvp.fragment.statistics;

import java.io.Serializable;

/**
 * Created by admin on 2019/3/29.
 */

 public  class ResultListBean implements Serializable {

    /**
     * result_id : 55
     * doc_no : SYBD2019032826118
     * result_remark : 大嫂
     * result_type : result
     * result_create_time : 2019-03-28 18:22:33
     * result_type_show : 拒绝原因
     */

    private String result_id;
    private String doc_no;
    private String result_remark;
    private String result_type;
    private String result_create_time;
    private String result_type_show;

    public String getResult_id() {
        return result_id;
    }

    public void setResult_id(String result_id) {
        this.result_id = result_id;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getResult_remark() {
        return result_remark;
    }

    public void setResult_remark(String result_remark) {
        this.result_remark = result_remark;
    }

    public String getResult_type() {
        return result_type;
    }

    public void setResult_type(String result_type) {
        this.result_type = result_type;
    }

    public String getResult_create_time() {
        return result_create_time;
    }

    public void setResult_create_time(String result_create_time) {
        this.result_create_time = result_create_time;
    }

    public String getResult_type_show() {
        return result_type_show;
    }

    public void setResult_type_show(String result_type_show) {
        this.result_type_show = result_type_show;
    }
}
