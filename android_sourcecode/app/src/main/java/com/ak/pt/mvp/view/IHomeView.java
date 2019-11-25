package com.ak.pt.mvp.view;

import com.ak.pt.bean.AppHomeMenuTreeBean;
import com.ak.pt.bean.AppVersionBean;
import com.ak.pt.bean.BannerBean;
import com.ak.pt.bean.NoticeBean;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/1/15.
 */

public interface IHomeView extends BaseView{
    void onGetNotReadMessageCount(String data);

    void OnGetNoticeList(List<NoticeBean> data);

    void OnGetBannerList(List<BannerBean> data);



    void OnGetNotReadNoticeCount(String data);


    void OnGetAppVersionDetail(AppVersionBean data);

    void onGetAppSystemModuleTree(List<AppHomeMenuTreeBean> data);

    void OnGetAppTestPressureList(List<PressurePageBean> data);

    void OnGetTestPressureListTwo(List<PressurePageBean> data);
}
