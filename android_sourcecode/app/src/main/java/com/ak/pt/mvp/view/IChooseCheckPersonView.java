package com.ak.pt.mvp.view;

import com.ak.pt.bean.MeParentBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/5/29.
 */

public interface IChooseCheckPersonView extends BaseView{
    void onGetMeAndParentList(List<MeParentBean> data);

    void onGgetParentDepartmentStaffList(List<MeParentBean> data);

    void ongetLargeStaffList(List<MeParentBean> data);
}
