package com.akan.qf.bean;

import java.io.Serializable;

/**
 * Created by admin on 2018/11/28.
 */

public class NoticeFileBean implements Serializable{

    /**
     * file_id : 2
     * notice_id : 2
     * file_path : /images/others/logo.png
     * file_name : 文件old
     * file_note : 这是公告的第一个文件
     * create_time : 2018-10-23 15:26:20
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
