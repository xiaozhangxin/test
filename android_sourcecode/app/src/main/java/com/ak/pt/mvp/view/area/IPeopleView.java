package com.ak.pt.mvp.view.area;

import com.ak.pt.bean.MeParentBean;
import com.ak.pt.bean.PeopleBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/5/29.
 */

public interface IPeopleView extends BaseView{
    void oninsertStaffAdd(String data);

    void ondeleteStaffAdd(String data);

    void onupdateStaffAdd(String data);

    void onauditStaffAdd(String data);

    void OngetStaffAddList(List<PeopleBean> data);

    void ongetStaffAddDetail(PeopleBean data);


    void onGgetParentDepartmentStaffList(List<MeParentBean> data);
}
