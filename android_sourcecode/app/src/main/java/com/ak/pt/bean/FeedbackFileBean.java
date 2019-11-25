package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/24.
 */

public class FeedbackFileBean implements Serializable{


    /**
     * file_name : 附件名
     * file_url : 附件地址
     */

    private String file_name;
    private String file_url;
    /**
     * file_id : 2
     * quality_id : 6
     * create_time :
     */

    private String file_id;
    private String quality_id;
    private String create_time;

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

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getQuality_id() {
        return quality_id;
    }

    public void setQuality_id(String quality_id) {
        this.quality_id = quality_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
