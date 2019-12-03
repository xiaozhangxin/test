package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class TransferBean implements Serializable{


    /**
     * id : 9
     * apply_id :
     * apply_no :
     * org_id : 1001512200010027
     * out_wh_id : 1001512260168339
     * in_wh_id : 1001512260168341
     * doc_no : 2307130163870041097
     * u9_id :
     * u9_code :
     * status : 0
     * out_wh_name : 内销成品仓-PPR管材
     * in_wh_name : PE管件仓库
     * create_id : 6001
     * create_time : 2019-07-23 15:19:45
     * remark :
     * ap_id :
     * ap_name :
     * create_name : 樊彩红
     * status_show : 未处理
     */

    private int id;
    private String apply_id;
    private String apply_no;
    private long org_id;
    private long out_wh_id;
    private long in_wh_id;
    private String doc_no;
    private String u9_id;
    private String u9_code;
    private int status;
    private String out_wh_name;
    private String in_wh_name;
    private int create_id;
    private String create_time;
    private String remark;
    private String ap_id;
    private String ap_name;
    private String create_name;
    private String status_show;
    private List<LineBeanListBean> lineBeanList;
    private List<BarBeanListBean> barBeanList;
    private List<RecordsBean> recordsBeans;

    public List<RecordsBean> getRecordsBeans() {
        return recordsBeans;
    }

    public void setRecordsBeans(List<RecordsBean> recordsBeans) {
        this.recordsBeans = recordsBeans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getApply_no() {
        return apply_no;
    }

    public void setApply_no(String apply_no) {
        this.apply_no = apply_no;
    }

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public long getOut_wh_id() {
        return out_wh_id;
    }

    public void setOut_wh_id(long out_wh_id) {
        this.out_wh_id = out_wh_id;
    }

    public long getIn_wh_id() {
        return in_wh_id;
    }

    public void setIn_wh_id(long in_wh_id) {
        this.in_wh_id = in_wh_id;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getU9_id() {
        return u9_id;
    }

    public void setU9_id(String u9_id) {
        this.u9_id = u9_id;
    }

    public String getU9_code() {
        return u9_code;
    }

    public void setU9_code(String u9_code) {
        this.u9_code = u9_code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOut_wh_name() {
        return out_wh_name;
    }

    public void setOut_wh_name(String out_wh_name) {
        this.out_wh_name = out_wh_name;
    }

    public String getIn_wh_name() {
        return in_wh_name;
    }

    public void setIn_wh_name(String in_wh_name) {
        this.in_wh_name = in_wh_name;
    }

    public int getCreate_id() {
        return create_id;
    }

    public void setCreate_id(int create_id) {
        this.create_id = create_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAp_id() {
        return ap_id;
    }

    public void setAp_id(String ap_id) {
        this.ap_id = ap_id;
    }

    public String getAp_name() {
        return ap_name;
    }

    public void setAp_name(String ap_name) {
        this.ap_name = ap_name;
    }

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }

    public String getStatus_show() {
        return status_show;
    }

    public void setStatus_show(String status_show) {
        this.status_show = status_show;
    }

    public List<LineBeanListBean> getLineBeanList() {
        return lineBeanList;
    }

    public void setLineBeanList(List<LineBeanListBean> lineBeanList) {
        this.lineBeanList = lineBeanList;
    }

    public List<BarBeanListBean> getBarBeanList() {
        return barBeanList;
    }

    public void setBarBeanList(List<BarBeanListBean> barBeanList) {
        this.barBeanList = barBeanList;
    }



}
