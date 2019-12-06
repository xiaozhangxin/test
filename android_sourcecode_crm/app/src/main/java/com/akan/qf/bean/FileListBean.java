package com.akan.qf.bean;

import java.io.Serializable;

/**
 * Created by admin on 2018/11/6.
 */

public class FileListBean implements Serializable{


    /**
     * file_id : 6
     * apply_id : 7
     * file_name : 吕臣豪2
     * file_size : 58 KB
     * file_remark :
     * up_url : /images/newapply/20181113/15421071986041520737712.jpg
     * file_create_time : 2018-11-13 19:06:38
     */

    private String file_id;
    private String apply_id;
    private String file_name;
    private String file_size;
    private String file_remark;
    private String up_url;
    private String file_url;
    private String file_create_time;

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    private String file_path;

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    public String getFile_remark() {
        return file_remark;
    }

    public void setFile_remark(String file_remark) {
        this.file_remark = file_remark;
    }

    public String getUp_url() {
        return up_url;
    }

    public void setUp_url(String up_url) {
        this.up_url = up_url;
    }

    public String getFile_create_time() {
        return file_create_time;
    }

    public void setFile_create_time(String file_create_time) {
        this.file_create_time = file_create_time;
    }
}
