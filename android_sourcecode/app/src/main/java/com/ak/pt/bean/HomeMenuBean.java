package com.ak.pt.bean;

import java.util.List;

/**
 * Created by admin on 2019/3/15.
 */

public class HomeMenuBean {

    private String menu_title;
    private List<HomeMenuChildBean> homeMenuChildBean;

    public String getMenu_title() {
        return menu_title;
    }

    public void setMenu_title(String menu_title) {
        this.menu_title = menu_title;
    }


    public List<HomeMenuChildBean> getHomeMenuChildBean() {
        return homeMenuChildBean;
    }

    public void setHomeMenuChildBean(List<HomeMenuChildBean> homeMenuChildBean) {
        this.homeMenuChildBean = homeMenuChildBean;
    }


    public static class HomeMenuChildBean {
        private String menu_title;
        private String menu_type;
        private int menu_url;

        public String getMenu_title() {
            return menu_title;
        }

        public void setMenu_title(String menu_title) {
            this.menu_title = menu_title;
        }


        public String getMenu_type() {
            return menu_type;
        }

        public void setMenu_type(String menu_type) {
            this.menu_type = menu_type;
        }


        public int getMenu_url() {
            return menu_url;
        }

        public void setMenu_url(int menu_url) {
            this.menu_url = menu_url;
        }

    }
}
