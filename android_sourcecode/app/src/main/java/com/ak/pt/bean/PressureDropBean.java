package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/4/29.
 */

public class PressureDropBean implements Serializable {


    /**
     * drop_id : 5
     * drop_num : 8
     * moduleList : [{"module_id":3,"drop_id":5,"module_num":"8","module_time":"15","module_pressure":"1","module_create_time":"2019-04-29 17:06:12"},{"module_id":4,"drop_id":5,"module_num":"8","module_time":"15","module_pressure":"1","module_create_time":"2019-04-29 17:06:12"}]
     */
    private boolean isCheck;
    private String drop_id;
    private String drop_num;
    private List<ModuleListBean> moduleList;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getDrop_id() {
        return drop_id;
    }

    public void setDrop_id(String drop_id) {
        this.drop_id = drop_id;
    }

    public String getDrop_num() {
        return drop_num;
    }

    public void setDrop_num(String drop_num) {
        this.drop_num = drop_num;
    }

    public List<ModuleListBean> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<ModuleListBean> moduleList) {
        this.moduleList = moduleList;
    }


}
