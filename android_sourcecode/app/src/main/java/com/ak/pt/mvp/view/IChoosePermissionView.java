package com.ak.pt.mvp.view;

import com.ak.pt.bean.staffGroupBeans;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

public interface IChoosePermissionView extends BaseView{
    void onGetStaffGroupByDepartment(List<staffGroupBeans> data);
}
