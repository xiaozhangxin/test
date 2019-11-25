package com.ak.pt.bean;

/**
 * Created by admin on 2019/3/18.
 */

public class photoBeanListBean {


    /**
     * doc_no : 所属表单编号
     * image_url : 图片地址
     * type_name : 图片类别名称
     */

    private String doc_no;
    private String image_url;
    private String type_name;

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
