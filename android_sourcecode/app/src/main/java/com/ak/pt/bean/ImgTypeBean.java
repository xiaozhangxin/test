package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/3/18.
 */

public class ImgTypeBean implements Serializable{

    public ImgTypeBean(String tittle, List<String> urlList) {
        this.tittle = tittle;
        this.urlList = urlList;
    }

    private String tittle;
    private List<String> urlList;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }
}
