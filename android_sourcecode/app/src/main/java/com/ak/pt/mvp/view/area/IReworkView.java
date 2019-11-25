package com.ak.pt.mvp.view.area;

import com.ak.pt.bean.MeParentBean;
import com.ak.pt.bean.PressureBackBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/5/30.
 */

public interface IReworkView extends BaseView{
    void oninsertPressureBack(String data);

    void ondeletePressureBack(String data);

    void onupdatePressureBack(String data);

    void onauditPressureBack(String data);

    void OngetPressureBackList(List<PressureBackBean> data);

    void ongetPressureBackDetail(PressureBackBean data);

    void ongetLargeStaffList(List<MeParentBean> data);
}
