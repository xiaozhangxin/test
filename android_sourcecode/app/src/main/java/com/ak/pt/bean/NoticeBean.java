package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/1/17.
 */

public class NoticeBean implements Serializable {


    /**
     * notice_id : 35
     * create_id : 58
     * create_name : 校长十四
     * notice_no : GG201812191550
     * notice_title : 测试公告2
     * notice_content : 大家好，12.31公司举办年会，地点在xxx,10点开始，望大家踊跃参加
     * create_time : 2018-12-19 10:05:30
     * group_ids : 3,4,175,190,193,5,198,204,6,199,7,200,201,8,245,9,10,11,12,13,14,185,192,197,15,16,195,196,17,18,19,20,21,194
     * staff_ids :
     * read_ids : ,42,52,53,
     * is_read :
     * noticeFileBeans : [{"file_id":26,"notice_id":35,"file_path":"/file/expenseAccount/20181219/15451851300351645284896.jpg","file_name":"ddd.jpg","file_note":"","create_time":"2018-12-19 10:05:30"}]
     * staff_id :
     */

    private String notice_id;
    private String create_id;
    private String create_name;
    private String notice_no;
    private String notice_title;
    private String notice_content;
    private String create_time;
    private String group_ids;
    private String staff_ids;
    private String read_ids;
    private String is_read;
    private String staff_id;
    private List<NoticeFileBeans> noticeFileBeans;

    public String getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(String notice_id) {
        this.notice_id = notice_id;
    }

    public String getCreate_id() {
        return create_id;
    }

    public void setCreate_id(String create_id) {
        this.create_id = create_id;
    }

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }

    public String getNotice_no() {
        return notice_no;
    }

    public void setNotice_no(String notice_no) {
        this.notice_no = notice_no;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getGroup_ids() {
        return group_ids;
    }

    public void setGroup_ids(String group_ids) {
        this.group_ids = group_ids;
    }

    public String getStaff_ids() {
        return staff_ids;
    }

    public void setStaff_ids(String staff_ids) {
        this.staff_ids = staff_ids;
    }

    public String getRead_ids() {
        return read_ids;
    }

    public void setRead_ids(String read_ids) {
        this.read_ids = read_ids;
    }

    public String getIs_read() {
        return is_read;
    }

    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public List<NoticeFileBeans> getNoticeFileBeans() {
        return noticeFileBeans;
    }

    public void setNoticeFileBeans(List<NoticeFileBeans> noticeFileBeans) {
        this.noticeFileBeans = noticeFileBeans;
    }

    public static class NoticeFileBeans implements Serializable{
        /**
         * file_id : 26
         * notice_id : 35
         * file_path : /file/expenseAccount/20181219/15451851300351645284896.jpg
         * file_name : ddd.jpg
         * file_note :
         * create_time : 2018-12-19 10:05:30
         */

        private int file_id;
        private int notice_id;
        private String file_path;
        private String file_name;
        private String file_note;
        private String create_time;

        public int getFile_id() {
            return file_id;
        }

        public void setFile_id(int file_id) {
            this.file_id = file_id;
        }

        public int getNotice_id() {
            return notice_id;
        }

        public void setNotice_id(int notice_id) {
            this.notice_id = notice_id;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getFile_note() {
            return file_note;
        }

        public void setFile_note(String file_note) {
            this.file_note = file_note;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
