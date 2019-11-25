package com.ak.pt.bean;

/**
 * Created by admin on 2019/5/22.
 */

public class WaterFileBean {


    /**
     * file_name : 文件名
     * file_url : 文件地址
     * file_type : up换上down换下
     */

    private String file_name;
    private String file_url;
    private String file_type;

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

    public WaterFileBean(String file_name, String file_url, String file_type) {
        this.file_name = file_name;
        this.file_url = file_url;
        this.file_type = file_type;
    }
}
