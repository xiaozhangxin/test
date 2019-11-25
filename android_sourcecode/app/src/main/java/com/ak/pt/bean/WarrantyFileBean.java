package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/24.
 */

public class WarrantyFileBean implements Serializable {

    public WarrantyFileBean(String img_url, String img_type) {
        this.img_url = img_url;
        this.img_type = img_type;
    }

    /**
     * img_url : 图片地址
     * img_type : 产品安装完成(success),保修单图片(bill)其他图片(other)
     */

    private String img_url;
    private String img_type;
    private String file_id;
    private String file_url;
    private String img_id;
    private String card_id;
    private String create_time;


    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }


    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getImg_type() {
        return img_type;
    }

    public void setImg_type(String img_type) {
        this.img_type = img_type;
    }


}
