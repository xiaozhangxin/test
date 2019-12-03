package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class InfoBeanList implements Serializable{


    /**
     * info_id :
     * inventory_id : 11
     * item_id :
     * info_num :
     * info_status :
     * codeList : [{"code_id":"","info_id":7,"bar_code":"","code":"","name":"","item_id":""}]
     * item_name : 白色PPR铝塑管S2.5
     * item_spec : Φ20×3.4(3m)
     */

    private String info_id;
    private String inventory_id;
    private String item_id;
    private String info_num;
    private String info_status;
    private String item_name;
    private String item_spec;
    private List<CodeListBean> codeList;

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public String getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(String inventory_id) {
        this.inventory_id = inventory_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getInfo_num() {
        return info_num;
    }

    public void setInfo_num(String info_num) {
        this.info_num = info_num;
    }

    public String getInfo_status() {
        return info_status;
    }

    public void setInfo_status(String info_status) {
        this.info_status = info_status;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_spec() {
        return item_spec;
    }

    public void setItem_spec(String item_spec) {
        this.item_spec = item_spec;
    }

    public List<CodeListBean> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<CodeListBean> codeList) {
        this.codeList = codeList;
    }

    public static class CodeListBean {
        /**
         * code_id :
         * info_id : 7
         * bar_code :
         * code :
         * name :
         * item_id :
         */

        private String code_id;
        private int info_id;
        private String bar_code;
        private String code;
        private String name;
        private String item_id;

        public String getCode_id() {
            return code_id;
        }

        public void setCode_id(String code_id) {
            this.code_id = code_id;
        }

        public int getInfo_id() {
            return info_id;
        }

        public void setInfo_id(int info_id) {
            this.info_id = info_id;
        }

        public String getBar_code() {
            return bar_code;
        }

        public void setBar_code(String bar_code) {
            this.bar_code = bar_code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }
    }
}
