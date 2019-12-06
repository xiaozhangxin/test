package com.akan.qf.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/4/23.
 */

public class FileNewBean implements Serializable{


    /**
     * file_id : 20
     * apply_id : 56
     * file_name : 
     * file_url : /images/apply/20190423/15560115855701243098998.png
     * file_remark : 大小:51k
     * apply_create_time : 2019-04-23 17:26:25
     */

    private String file_id;
    private String apply_id;
    private String file_name;
    private String file_url;
    private String file_remark;
    private String apply_create_time;

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

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getFile_remark() {
        return file_remark;
    }

    public void setFile_remark(String file_remark) {
        this.file_remark = file_remark;
    }

    public String getApply_create_time() {
        return apply_create_time;
    }

    public void setApply_create_time(String apply_create_time) {
        this.apply_create_time = apply_create_time;
    }
}
