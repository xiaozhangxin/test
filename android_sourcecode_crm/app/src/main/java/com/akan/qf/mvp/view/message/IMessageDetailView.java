package com.akan.qf.mvp.view.message;

import com.akan.qf.bean.AppHomeMenuTreeBean;
import com.akan.qf.bean.StaffMessageBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/10/26.
 */

public interface IMessageDetailView extends BaseView{
    void onGetStaffMessageDetail(StaffMessageBean data);

    void onGetAppHomeMenuTreeByStaff(List<AppHomeMenuTreeBean> data);
}
