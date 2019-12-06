package com.akan.qf.mvp.view;

import com.akan.qf.bean.staffGroupBeans;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

public interface IChoosePermissionView extends BaseView{
    void onGetStaffGroupByDepartment(List<staffGroupBeans> data);
}
