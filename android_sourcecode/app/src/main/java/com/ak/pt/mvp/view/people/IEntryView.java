package com.ak.pt.mvp.view.people;

import com.ak.pt.bean.MeParentBean;
import com.ak.pt.bean.StaffApplyBean;
import com.ak.pt.bean.staffGroupBeans;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

public interface IEntryView extends BaseView{
    void insertStaffApply(String data);

    void deleteStaffApply(String data);

    void updateStaffApply(String data);

    void auditStaffApply(String data);

    void getStaffApplyList(List<StaffApplyBean> data);

    void getStaffApplyDetail(StaffApplyBean data);



    void onGgetParentDepartmentStaffList(List<MeParentBean> data);
}
