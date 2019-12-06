package com.akan.qf.mvp.fragment.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/3/18.
 */

public class PhotoListBean implements Serializable {


    /**
     * seq_no : 1
     * base_type : yhp
     * type_name : 用户门牌号
     * order_by :
     * memo :
     * photos : [{"seq_no":29,"cus_no":"","doc_no":"SYBD2019011623704","add_date":"2019-01-18 13:43:02","image_url":"/images/pressure/20190118/1547790181660641494749.jpg","type":"0","base_pic":"1","type_name":""}]
     */

    private String seq_no;
    private String base_type;
    private String type_name;
    private String order_by;
    private String memo;
    private List<PhotosBean> photos = new ArrayList<>();

    public String getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(String seq_no) {
        this.seq_no = seq_no;
    }

    public String getBase_type() {
        return base_type;
    }

    public void setBase_type(String base_type) {
        this.base_type = base_type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getOrder_by() {
        return order_by;
    }

    public void setOrder_by(String order_by) {
        this.order_by = order_by;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<PhotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosBean> photos) {
        this.photos = photos;
    }

    public static class PhotosBean  implements Serializable {
        /**
         * seq_no : 29
         * cus_no :
         * doc_no : SYBD2019011623704
         * add_date : 2019-01-18 13:43:02
         * image_url : /images/pressure/20190118/1547790181660641494749.jpg
         * type : 0
         * base_pic : 1
         * type_name :
         */

        private String seq_no;
        private String cus_no;
        private String doc_no;
        private String add_date;
        private String image_url;
        private String type;
        private String base_pic;
        private String type_name;

        public String getSeq_no() {
            return seq_no;
        }

        public void setSeq_no(String seq_no) {
            this.seq_no = seq_no;
        }

        public String getCus_no() {
            return cus_no;
        }

        public void setCus_no(String cus_no) {
            this.cus_no = cus_no;
        }

        public String getDoc_no() {
            return doc_no;
        }

        public void setDoc_no(String doc_no) {
            this.doc_no = doc_no;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBase_pic() {
            return base_pic;
        }

        public void setBase_pic(String base_pic) {
            this.base_pic = base_pic;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }
    }
}
