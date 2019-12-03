package com.akan.wms.mvp.view;

import com.akan.wms.bean.MessageBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IMessageView extends BaseView{
    void onGetStaffMessageList(List<MessageBean> data);
}
