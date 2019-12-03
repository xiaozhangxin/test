package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class ShipCodeListBean implements Serializable{


    /**
     * info_id : 对应料品id
     * num : 数量
     * codeList : [{"info_id":"对应料品id","bar_code":"二维码","code":"料品号","name":"料品名"}]
     */

    private String info_id;
    private int num;
    private List<CodeListBean> codeList;

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<CodeListBean> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<CodeListBean> codeList) {
        this.codeList = codeList;
    }


}
