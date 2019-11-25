package com.ak.pt.mvp.view;

import com.ak.pt.bean.NoticeBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/1/17.
 */

public interface INoticeView extends BaseView{
    void OnGetNoticeDetail(NoticeBean data);

    void OnGetNoticeList(List<NoticeBean> data, String total);

    void OnGetNotReadNoticeCount(String data);
}
