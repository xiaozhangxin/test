package com.akan.qf.bean;

/**
 * Created by admin on 2018/10/23.
 */

public class LeaveFileBean {


    /**
     * file_name : 文件名
     * file_path : 文件路径
     * file_size : 42K
     * file_note : 备注
     * up_name : 是的
     */

    private String file_name;
    private String file_path;
    private String file_size;
    private String file_note;
    private String up_name;

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
}
