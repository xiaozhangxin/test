package com.ak.pt.mvp.view;


import com.ak.pt.bean.AppHomeMenuTreeBean;
import com.ak.pt.bean.StaffMessageBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/10/23.
 */

public interface IMessageView extends BaseView {
    void onGetStaffMessageList(List<StaffMessageBean> data);

    void onGetNotReadMessageCount(String data);

    void onDeleteStaffMessages(String data);

    void onGetStaffMessageDetail(StaffMessageBean data);

    void onGetAppSystemModuleTree(List<AppHomeMenuTreeBean> data);
}
