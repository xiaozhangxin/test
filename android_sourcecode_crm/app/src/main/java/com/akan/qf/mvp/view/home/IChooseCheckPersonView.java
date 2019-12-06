package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.MeParentBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/10/23.
 */

public interface IChooseCheckPersonView extends BaseView{
    void onGetMyAndParentDepartmentStaffList(List<MeParentBean> data);
}
