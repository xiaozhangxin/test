package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/23.
 */

public class FixFileBean implements Serializable{

    /**
     * file_id : 13
     * repair_id : 19
     * file_name : 
     * file_url : /storage/emulated/0/DCIM/1558060765654sign.jpg
     * create_time : 2019-05-23 14:32:09
     */

    private String file_id;
    private String repair_id;
    private String file_name;
    private String file_url;
    private String create_time;
    private String study_id;
    private String interview_id;

    public String getInterview_id() {
        return interview_id;
    }

    public void setInterview_id(String interview_id) {
        this.interview_id = interview_id;
    }

    public String getStudy_id() {
        return study_id;
    }

    public void setStudy_id(String study_id) {
        this.study_id = study_id;
    }

    public FixFileBean(String file_url) {
        this.file_url = file_url;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(String repair_id) {
        this.repair_id = repair_id;
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

    /**
     * file_name : 名称
     * file_url :
     */

   
}
