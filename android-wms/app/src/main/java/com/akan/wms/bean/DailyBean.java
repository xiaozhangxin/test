package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/3/20.
 */

public class DailyBean implements Serializable {


    /**
     * daily_id : 9
     * daily_no : 2018062600009
     * daily_state : 1
     * staff_id : 4
     * today_work : 肯定
     * daily_image1 : /images/others/20180705/15307627313311764725328.jpg
     * daily_image2 : /images/others/20180705/1530762731332785699770.jpg
     * daily_image3 :
     * tomorrow_work : 时空
     * audit_note :
     * create_time : 2018-07-05 11:52:11
     * is_delete : 0
     * daily_type : 0
     * audit_staff_ids : 4
     * audit_staff_names : 石磊
     * daily_state_show : 已审核
     * staff_name : 石磊
     * department_name : 爱康企业集团（上海）有限公司/北方营销中心/山东/山东民用
     * job : 文员
     * start_time :
     * end_time :
     * staff_uuid :
     * dailyImageBeans : ["/images/others/20180705/15307627313311764725328.jpg","/images/others/20180705/1530762731332785699770.jpg"]
     * dailyAuditBeans : [{"audit_id":14,"daily_id":9,"staff_id":4,"staff_name":"石磊","audit_content":"哈都不知道","create_time":"2018-07-09 17:06:19"}]
     */

    private String daily_id;
    private String daily_no;
    private String daily_state;
    private String staff_id;
    private String today_work;
    private String daily_image1;
    private String daily_image2;
    private String daily_image3;
    private String tomorrow_work;
    private String audit_note;
    private String create_time;
    private String is_delete;
    private String daily_type;
    private String audit_staff_ids;
    private String audit_staff_names;
    private String daily_state_show;
    private String staff_name;
    private String department_name;
    private String job;
    private String start_time;
    private String end_time;
    private String staff_uuid;
    private List<String> dailyImageBeans;
    private List<DailyAuditBeansBean> dailyAuditBeans;



    public String getDaily_no() {
        return daily_no;
    }

    public void setDaily_no(String daily_no) {
        this.daily_no = daily_no;
    }

    public String getDaily_state() {
        return daily_state;
    }

    public void setDaily_state(String daily_state) {
        this.daily_state = daily_state;
    }

    public String getDaily_id() {
        return daily_id;
    }

    public void setDaily_id(String daily_id) {
        this.daily_id = daily_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getToday_work() {
        return today_work;
    }

    public void setToday_work(String today_work) {
        this.today_work = today_work;
    }

    public String getDaily_image1() {
        return daily_image1;
    }

    public void setDaily_image1(String daily_image1) {
        this.daily_image1 = daily_image1;
    }

    public String getDaily_image2() {
        return daily_image2;
    }

    public void setDaily_image2(String daily_image2) {
        this.daily_image2 = daily_image2;
    }

    public String getDaily_image3() {
        return daily_image3;
    }

    public void setDaily_image3(String daily_image3) {
        this.daily_image3 = daily_image3;
    }

    public String getTomorrow_work() {
        return tomorrow_work;
    }

    public void setTomorrow_work(String tomorrow_work) {
        this.tomorrow_work = tomorrow_work;
    }

    public String getAudit_note() {
        return audit_note;
    }

    public void setAudit_note(String audit_note) {
        this.audit_note = audit_note;
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

    public String getDaily_type() {
        return daily_type;
    }

    public void setDaily_type(String daily_type) {
        this.daily_type = daily_type;
    }

    public String getAudit_staff_ids() {
        return audit_staff_ids;
    }

    public void setAudit_staff_ids(String audit_staff_ids) {
        this.audit_staff_ids = audit_staff_ids;
    }

    public String getAudit_staff_names() {
        return audit_staff_names;
    }

    public void setAudit_staff_names(String audit_staff_names) {
        this.audit_staff_names = audit_staff_names;
    }

    public String getDaily_state_show() {
        return daily_state_show;
    }

    public void setDaily_state_show(String daily_state_show) {
        this.daily_state_show = daily_state_show;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public List<String> getDailyImageBeans() {
        return dailyImageBeans;
    }

    public void setDailyImageBeans(List<String> dailyImageBeans) {
        this.dailyImageBeans = dailyImageBeans;
    }

    public List<DailyAuditBeansBean> getDailyAuditBeans() {
        return dailyAuditBeans;
    }

    public void setDailyAuditBeans(List<DailyAuditBeansBean> dailyAuditBeans) {
        this.dailyAuditBeans = dailyAuditBeans;
    }

    public static class DailyAuditBeansBean implements Serializable {
        /**
         * audit_id : 14
         * daily_id : 9
         * staff_id : 4
         * staff_name : 石磊
         * audit_content : 哈都不知道
         * create_time : 2018-07-09 17:06:19
         */

        private int audit_id;
        private int daily_id;
        private int staff_id;
        private String staff_name;
        private String audit_content;
        private String create_time;

        public int getAudit_id() {
            return audit_id;
        }

        public void setAudit_id(int audit_id) {
            this.audit_id = audit_id;
        }

        public int getDaily_id() {
            return daily_id;
        }

        public void setDaily_id(int daily_id) {
            this.daily_id = daily_id;
        }

        public int getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(int staff_id) {
            this.staff_id = staff_id;
        }

        public String getStaff_name() {
            return staff_name;
        }

        public void setStaff_name(String staff_name) {
            this.staff_name = staff_name;
        }

        public String getAudit_content() {
            return audit_content;
        }

        public void setAudit_content(String audit_content) {
            this.audit_content = audit_content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
