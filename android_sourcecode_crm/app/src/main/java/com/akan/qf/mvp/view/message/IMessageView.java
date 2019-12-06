package com.akan.qf.mvp.view.message;

import com.akan.qf.bean.AppHomeMenuTreeBean;
import com.akan.qf.bean.AppVersionBean;
import com.akan.qf.bean.BookBean;
import com.akan.qf.bean.StaffMessageBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/10/23.
 */

public interface IMessageView extends BaseView{
    void onGetStaffMessageList(List<StaffMessageBean> data);

    void onGetAddressBookList(List<BookBean> data, String total);

    void onGetNotReadMessageCount(String data);

    void onDeleteStaffMessages(String data);

    void OnGetAppVersionDetail(AppVersionBean data);

    void onGetAppHomeMenuTreeByStaff(List<AppHomeMenuTreeBean> data);
}
