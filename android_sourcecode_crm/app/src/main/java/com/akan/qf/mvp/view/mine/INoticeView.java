package com.akan.qf.mvp.view.mine;

import com.akan.qf.bean.NoticeBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/7.
 */

public interface INoticeView extends BaseView{
    void OnGetNoticeList(List<NoticeBean> data, String total);

    void OnGetNoticeDetail(NoticeBean data);

    void OnGetNotReadNoticeCount(String data);
}
