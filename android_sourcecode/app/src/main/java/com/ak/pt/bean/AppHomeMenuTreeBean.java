package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/3/18.
 */

public class AppHomeMenuTreeBean implements Serializable{

    /**
     * menu_id : 1
     * menu_title : 日常工作
     * menu_group : 0
     * menu_type :
     * menu_key :
     * menu_url :
     * parent_id :
     * menu_icon :
     * sort :
     * create_time :
     * is_disable :
     * appHomeMenuBeans : [{"menu_id":28,"menu_title":"签到","menu_group":"0","menu_type":"1","menu_key":"Sign","menu_url":"","parent_id":"","menu_icon":"","sort":"","create_time":"","is_disable":"1","appHomeMenuBeans":[]},{"menu_id":29,"menu_title":"请假申请","menu_group":"0","menu_type":"1","menu_key":"AskLeave","menu_url":"","parent_id":"","menu_icon":"","sort":"","create_time":"","is_disable":"1","appHomeMenuBeans":[]},{"menu_id":30,"menu_title":"日报","menu_group":"0","menu_type":"1","menu_key":"Daily","menu_url":"","parent_id":"","menu_icon":"","sort":"","create_time":"","is_disable":"1","appHomeMenuBeans":[]},{"menu_id":32,"menu_title":"周报","menu_group":"0","menu_type":"1","menu_key":"Week","menu_url":"","parent_id":"","menu_icon":"","sort":"","create_time":"","is_disable":"1","appHomeMenuBeans":[]},{"menu_id":31,"menu_title":"月报","menu_group":"0","menu_type":"1","menu_key":"Month","menu_url":"","parent_id":"","menu_icon":"","sort":"","create_time":"","is_disable":"1","appHomeMenuBeans":[]}]
     */

    private String menu_id;
    private String menu_title;
    private String menu_group;
    private String menu_type;
    private String menu_key;
    private String menu_url;
    private String parent_id;
    private String menu_icon;
    private String sort;
    private String create_time;
    private String is_disable;
    private List<AppHomeMenuBeansBean> appHomeMenuBeans;

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

    public String getMenu_group() {
        return menu_group;
    }

    public void setMenu_group(String menu_group) {
        this.menu_group = menu_group;
    }

    public String getMenu_type() {
        return menu_type;
    }

    public void setMenu_type(String menu_type) {
        this.menu_type = menu_type;
    }

    public String getMenu_key() {
        return menu_key;
    }

    public void setMenu_key(String menu_key) {
        this.menu_key = menu_key;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getMenu_icon() {
        return menu_icon;
    }

    public void setMenu_icon(String menu_icon) {
        this.menu_icon = menu_icon;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIs_disable() {
        return is_disable;
    }

    public void setIs_disable(String is_disable) {
        this.is_disable = is_disable;
    }

    public List<AppHomeMenuBeansBean> getAppHomeMenuBeans() {
        return appHomeMenuBeans;
    }

    public void setAppHomeMenuBeans(List<AppHomeMenuBeansBean> appHomeMenuBeans) {
        this.appHomeMenuBeans = appHomeMenuBeans;
    }


}
