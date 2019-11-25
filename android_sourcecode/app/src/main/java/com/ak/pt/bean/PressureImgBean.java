package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

public class PressureImgBean implements Serializable {
    private String doc_no;
    private List<ImgTypeBean> imgTypeBeanList;

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public List<ImgTypeBean> getImgTypeBeanList() {
        return imgTypeBeanList;
    }

    public void setImgTypeBeanList(List<ImgTypeBean> imgTypeBeanList) {
        this.imgTypeBeanList = imgTypeBeanList;
    }
}
