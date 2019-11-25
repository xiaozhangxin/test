package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/22.
 */

public class DownFileBean implements Serializable{

    public DownFileBean(String file_url, String file_type) {
        this.file_url = file_url;
        this.file_type = file_type;
    }

    /**
     * file_id : 11
     * filter_id : 6
     * file_name : 
     * file_url : /images/waterFilter/20190522/1558518075896106434696.jpeg
     * file_type : down
     * create_time : 2019-05-22 17:41:16
     */


    private String file_id;
    private String filter_id;
    private String file_name;
    private String file_url;
    private String file_type;
    private String create_time;

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getFilter_id() {
        return filter_id;
    }

    public void setFilter_id(String filter_id) {
        this.filter_id = filter_id;
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

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
