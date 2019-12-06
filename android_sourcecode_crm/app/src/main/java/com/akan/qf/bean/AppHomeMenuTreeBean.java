package com.akan.qf.bean;

import java.util.List;

/**
 * Created by admin on 2018/10/19.
 */

public class AppHomeMenuTreeBean {


    /**
     * menu_id : 1
     * menu_title : 日常工作
     * menu_group : 0
     */

    private String menu_id;
    private String menu_title;
    private List<AppHomeMenuBean> appHomeMenuBeans;

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_title() {
        return menu_title;
    }

    public void setMenu_title(String menu_title) {
        this.menu_title = menu_title;
    }

    public List<AppHomeMenuBean> getAppHomeMenuBeans() {
        return appHomeMenuBeans;
    }

    public void setAppHomeMenuBeans(List<AppHomeMenuBean> appHomeMenuBeans) {
        this.appHomeMenuBeans = appHomeMenuBeans;
    }


}
