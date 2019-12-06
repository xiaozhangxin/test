package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.AppHomeMenuTreeBean;
import com.akan.qf.bean.BannerBean;
import com.akan.qf.bean.LableBean;
import com.akan.qf.bean.NoticeBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/10/19.
 */

public interface IHomeView extends BaseView{
    void onGetAppHomeMenuTreeByStaff(List<AppHomeMenuTreeBean> data);

    void onGetBannerListt(List<BannerBean> data);

    void OnGetNoticeList(List<NoticeBean> data);

    void OnGetNotReadNoticeCount(String data);

    void OnGetStaffSignList(List<LableBean> data);
}
