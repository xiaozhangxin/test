package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/7/13.
 */

public class LeaveBean implements Serializable{


    /**
     * staff_name : 石磊
     * department_name : 爱康企业集团（上海）有限公司/南方营销中心
     * job : 文员
     * staff_uuid :
     * group_parent_uuid :
     * start_time : 2018-10-24 15:46
     * end_time : 2018-10-25 15:46
     * ask_id : 65
     * ask_state : wait_audit
     * staff_id : 4
     * ask_no : 2018071300064
     * ask_why : 哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈
     * ask_type : 事假
     * create_time : 2018-10-24 15:47:50
     * is_delete : 0
     * audit_staff_ids :
     * audit_staff_names :
     * refuse_note :
     * all_time : 24
     * next_audit_staff_id : 1
     * ask_state_show : 未审核
     * askLeaveAuditBeans : [{"audit_id":"","ask_id":"","staff_id":"","staff_name":"石磊","audit_state":"","audit_content":"发出申请","create_time":"2018-10-24 15:47:50"}]
     * askLeaveFileBeans : [{"file_id":41,"ask_id":65,"file_name":"","file_path":"/images/leave/20181024/1540367270722544768252.jpg","file_size":"","file_note":"","up_name":"","create_time":"2018-10-24 15:47:51"},{"file_id":42,"ask_id":65,"file_name":"","file_path":"/images/leave/20181024/1540367270732848060256.jpg","file_size":"","file_note":"","up_name":"","create_time":"2018-10-24 15:47:51"},{"file_id":43,"ask_id":65,"file_name":"","file_path":"/images/leave/20181024/15403672707331424488978.jpg","file_size":"","file_note":"","up_name":"","create_time":"2018-10-24 15:47:51"}]
     */

    public String getNext_audit_staff_name() {
        return next_audit_staff_name;
    }

    public void setNext_audit_staff_name(String next_audit_staff_name) {
        this.next_audit_staff_name = next_audit_staff_name;
    }

    public String getNext_audit_staff_head_img() {
        return next_audit_staff_head_img;
    }

    public void setNext_audit_staff_head_img(String next_audit_staff_head_img) {
        this.next_audit_staff_head_img = next_audit_staff_head_img;
    }
    private String staff_sign_name;
    private String next_audit_staff_name;
    private String next_audit_staff_head_img;
    private String staff_name;

    public String getStaff_sign_name() {
        return staff_sign_name;
    }

    public void setStaff_sign_name(String staff_sign_name) {
        this.staff_sign_name = staff_sign_name;
    }
    private List<StaffSignUnionBean> staffSignUnionList;

    public List<StaffSignUnionBean> getStaffSignUnionList() {
        return staffSignUnionList;
    }

    public void setStaffSignUnionList(List<StaffSignUnionBean> staffSignUnionList) {
        this.staffSignUnionList = staffSignUnionList;
    }

    private String department_name;
    private String job;
    private String staff_uuid;
    private String group_parent_uuid;
    private String start_time;
    private String end_time;
    private String ask_id;
    private String ask_state;
    private String staff_id;
    private String ask_no;
    private String ask_why;
    private String ask_type;
    private String create_time;
    private String is_delete;
    private String audit_staff_ids;
    private String audit_staff_names;
    private String refuse_note;
    private String all_time;
    private String next_audit_staff_id;
    private String ask_state_show;
    private List<AskLeaveAuditBeansBean> askLeaveAuditBeans;
    private List<FileListBean> askLeaveFileBeans;

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


    public String getAsk_state() {
        return ask_state;
    }

    public void setAsk_state(String ask_state) {
        this.ask_state = ask_state;
    }



    public String getAsk_no() {
        return ask_no;
    }

    public void setAsk_no(String ask_no) {
        this.ask_no = ask_no;
    }

    public String getAsk_why() {
        return ask_why;
    }

    public void setAsk_why(String ask_why) {
        this.ask_why = ask_why;
    }

    public String getAsk_type() {
        return ask_type;
    }

    public void setAsk_type(String ask_type) {
        this.ask_type = ask_type;
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

    public String getRefuse_note() {
        return refuse_note;
    }

    public void setRefuse_note(String refuse_note) {
        this.refuse_note = refuse_note;
    }

    public String getAsk_id() {
        return ask_id;
    }

    public void setAsk_id(String ask_id) {
        this.ask_id = ask_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getAll_time() {
        return all_time;
    }

    public void setAll_time(String all_time) {
        this.all_time = all_time;
    }

    public String getNext_audit_staff_id() {
        return next_audit_staff_id;
    }

    public void setNext_audit_staff_id(String next_audit_staff_id) {
        this.next_audit_staff_id = next_audit_staff_id;
    }

    public String getAsk_state_show() {
        return ask_state_show;
    }

    public void setAsk_state_show(String ask_state_show) {
        this.ask_state_show = ask_state_show;
    }

    public List<AskLeaveAuditBeansBean> getAskLeaveAuditBeans() {
        return askLeaveAuditBeans;
    }

    public void setAskLeaveAuditBeans(List<AskLeaveAuditBeansBean> askLeaveAuditBeans) {
        this.askLeaveAuditBeans = askLeaveAuditBeans;
    }

    public List<FileListBean> getAskLeaveFileBeans() {
        return askLeaveFileBeans;
    }

    public void setAskLeaveFileBeans(List<FileListBean> askLeaveFileBeans) {
        this.askLeaveFileBeans = askLeaveFileBeans;
    }

    public static class AskLeaveAuditBeansBean implements Serializable {
        /**
         * audit_id :
         * ask_id :
         * staff_id :
         * staff_name : 石磊
         * audit_state :
         * audit_content : 发出申请
         * create_time : 2018-10-24 15:47:50
         */
        private String cc_person_name;
        private String audit_id;
        private String ask_id;
        private String staff_id;
        private String staff_name;
        private String audit_state;
        private String audit_content;
        private String create_time;
        private String audit_state_show;

        public String getCc_person_name() {
            return cc_person_name;
        }

        public void setCc_person_name(String cc_person_name) {
            this.cc_person_name = cc_person_name;
        }

        public String getAudit_state_show() {
            return audit_state_show;
        }

        public void setAudit_state_show(String audit_state_show) {
            this.audit_state_show = audit_state_show;
        }

        public String getAudit_id() {
            return audit_id;
        }

        public void setAudit_id(String audit_id) {
            this.audit_id = audit_id;
        }

        public String getAsk_id() {
            return ask_id;
        }

        public void setAsk_id(String ask_id) {
            this.ask_id = ask_id;
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

        public String getAudit_state() {
            return audit_state;
        }

        public void setAudit_state(String audit_state) {
            this.audit_state = audit_state;
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

    public static class AskLeaveFileBeansBean implements Serializable{
        /**
         * file_id : 41
         * ask_id : 65
         * file_name :
         * file_path : /images/leave/20181024/1540367270722544768252.jpg
         * file_size :
         * file_note :
         * up_name :
         * create_time : 2018-10-24 15:47:51
         */

        private int file_id;
        private int ask_id;
        private String file_name;
        private String file_path;
        private String file_size;
        private String file_note;
        private String up_name;
        private String create_time;

        public int getFile_id() {
            return file_id;
        }

        public void setFile_id(int file_id) {
            this.file_id = file_id;
        }

        public int getAsk_id() {
            return ask_id;
        }

        public void setAsk_id(int ask_id) {
            this.ask_id = ask_id;
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public String getFile_size() {
            return file_size;
        }

        public void setFile_size(String file_size) {
            this.file_size = file_size;
        }

        public String getFile_note() {
            return file_note;
        }

        public void setFile_note(String file_note) {
            this.file_note = file_note;
        }

        public String getUp_name() {
            return up_name;
        }

        public void setUp_name(String up_name) {
            this.up_name = up_name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
