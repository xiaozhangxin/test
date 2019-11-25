package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/27.
 */

public class AreaStudyBean implements Serializable{


    /**
     * study_id : 2
     * study_no : QYPX2019052789332
     * staff_id : 113
     * staff_name : 二哥
     * department_name : 南方营销中心
     * group_no : 001001
     * address : 上海市静安区好几家
     * content : 潘总
     * remark : 投了
     * study_state : wait_audit
     * is_delete : 0
     * study_time : 2019-05-27
     * create_time : 2019-05-27 16:33:17
     * update_time : 2019-05-27 16:33:17
     * fileList : [{"file_id":2,"study_id":2,"file_name":"","file_url":"/images/area/20190527/15589459969651443153278.jpg","create_time":"2019-05-27 16:33:17"},{"file_id":3,"study_id":2,"file_name":"","file_url":"/images/area/20190527/15589459969662073297356.jpg","create_time":"2019-05-27 16:33:17"},{"file_id":4,"study_id":2,"file_name":"","file_url":"/images/area/20190527/1558945996967601976406.jpg","create_time":"2019-05-27 16:33:17"}]
     * recordList : []
     * job_name_list : []
     * study_state_show : 待审阅
     * job_name : 外部水工
     * start_time : 
     * end_time : 
     * staff_uuid : 
     * group_parent_uuid : 
     */

    private String study_id;
    private String study_no;
    private String staff_id;
    private String staff_name;
    private String department_name;
    private String group_no;
    private String address;
    private String content;
    private String remark;
    private String study_state;
    private String is_delete;
    private String study_time;
    private String create_time;
    private String update_time;
    private String study_state_show;
    private String job_name;
    private String start_time;
    private String end_time;
    private String staff_uuid;
    private String group_parent_uuid;
    private List<FixFileBean> fileList;
    private List<RecordBean> recordList;
    private List<?> job_name_list;

    public String getStudy_id() {
        return study_id;
    }

    public void setStudy_id(String study_id) {
        this.study_id = study_id;
    }

    public String getStudy_no() {
        return study_no;
    }

    public void setStudy_no(String study_no) {
        this.study_no = study_no;
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

    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStudy_state() {
        return study_state;
    }

    public void setStudy_state(String study_state) {
        this.study_state = study_state;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getStudy_time() {
        return study_time;
    }

    public void setStudy_time(String study_time) {
        this.study_time = study_time;
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

    public String getStudy_state_show() {
        return study_state_show;
    }

    public void setStudy_state_show(String study_state_show) {
        this.study_state_show = study_state_show;
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

    public List<FixFileBean> getFileList() {
        return fileList;
    }

    public void setFileList(List<FixFileBean> fileList) {
        this.fileList = fileList;
    }

    public List<RecordBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordBean> recordList) {
        this.recordList = recordList;
    }

    public List<?> getJob_name_list() {
        return job_name_list;
    }

    public void setJob_name_list(List<?> job_name_list) {
        this.job_name_list = job_name_list;
    }

    public static class FileListBean {
        /**
         * file_id : 2
         * study_id : 2
         * file_name : 
         * file_url : /images/area/20190527/15589459969651443153278.jpg
         * create_time : 2019-05-27 16:33:17
         */

        private String file_id;
        private String study_id;
        private String file_name;
        private String file_url;
        private String create_time;

        public String getFile_id() {
            return file_id;
        }

        public void setFile_id(String file_id) {
            this.file_id = file_id;
        }

        public String getStudy_id() {
            return study_id;
        }

        public void setStudy_id(String study_id) {
            this.study_id = study_id;
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getFile_url() {
            return file_url;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
