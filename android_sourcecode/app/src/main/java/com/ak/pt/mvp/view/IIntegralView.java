package com.ak.pt.mvp.view;

import com.ak.pt.bean.FakeBean;
import com.ak.pt.bean.IntegralBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/3/26.
 */

public interface IIntegralView extends BaseView{
    void OnGetIntegralDailyList(List<IntegralBean> data);

    void OnGetAntiFakeList(List<FakeBean> data,String total);
}
