package com.ak.pt.bean;

/**
 * Created by admin on 2019/1/9.
 */

public  class AppVersionBean {


        /**
         * version_id : 1
         * version_name : 安卓
         * version_type : android
         * version_no : 1.0.0
         * update_content : 全新版本
         界面优化
         功能升级!
         * download_address : https://www.pgyer.com/54Be
         * server_address : http://pressure.akan.com.cn//files/20190418/15555889552921732068895.apk
         * must_update : 1
         * update_time : 2019-04-18 20:02:50
         * server_update : 0
         */

        private String version_id;
        private String version_name;
        private String version_type;
        private String version_no;
        private String update_content;
        private String download_address;
        private String server_address;
        private String must_update;
        private String update_time;
        private String server_update;

        public String getVersion_id() {
                return version_id;
        }

        public void setVersion_id(String version_id) {
                this.version_id = version_id;
        }

        public String getVersion_name() {
                return version_name;
        }

        public void setVersion_name(String version_name) {
                this.version_name = version_name;
        }

        public String getVersion_type() {
                return version_type;
        }

        public void setVersion_type(String version_type) {
                this.version_type = version_type;
        }

        public String getVersion_no() {
                return version_no;
        }

        public void setVersion_no(String version_no) {
                this.version_no = version_no;
        }

        public String getUpdate_content() {
                return update_content;
        }

        public void setUpdate_content(String update_content) {
                this.update_content = update_content;
        }

        public String getDownload_address() {
                return download_address;
        }

        public void setDownload_address(String download_address) {
                this.download_address = download_address;
        }

        public String getServer_address() {
                return server_address;
        }

        public void setServer_address(String server_address) {
                this.server_address = server_address;
        }

        public String getMust_update() {
                return must_update;
        }

        public void setMust_update(String must_update) {
                this.must_update = must_update;
        }

        public String getUpdate_time() {
                return update_time;
        }

        public void setUpdate_time(String update_time) {
                this.update_time = update_time;
        }

        public String getServer_update() {
                return server_update;
        }

        public void setServer_update(String server_update) {
                this.server_update = server_update;
        }
}
