package com.akan.qf.mvp.view.treeview;

import com.akan.qf.bean.ContributeClassBean;
import com.akan.qf.bean.staffGroupBeans;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/7/5.
 */

public interface IChooseDepartmentView  extends BaseView{
    void onGetStaffGroupTreeByStaff(List<staffGroupBeans> data);

    void OnGetContributeClassTree(List<ContributeClassBean> data);
}
