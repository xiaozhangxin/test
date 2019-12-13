package com.ak.pt.mvp.fragment.qifei;

import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/23.
 */

public interface IPressureView extends BaseView {
    void OnQueryAreaCountPressurePage(List<AreaPressureBean> data);

    void OnQueryPressurePage(List<PressurePageBean> data,String total);

    void OnQueryDetail(PressurePageBean data);

    void OnQueryBigAreaCountPressurePage(List<BigAreaBean> data);
}
