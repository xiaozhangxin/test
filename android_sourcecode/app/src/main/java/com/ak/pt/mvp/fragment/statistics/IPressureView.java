package com.ak.pt.mvp.fragment.statistics;

import com.ak.pt.bean.AreaPressureBean;
import com.ak.pt.bean.BigAreaBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/23.
 */

public interface IPressureView extends BaseView {
    void OnQueryAreaCountPressurePage(List<AreaPressureBean> data);

    void OnQueryPressurePage(List<PressurePageBean> data);

    void OnQueryDetail(PressurePageBean data);

    void OnQueryBigAreaCountPressurePage(List<BigAreaBean> data);

    void OnGetTestPressureList(List<PressurePageBean> data,String total);

    void onAuditTestPressure(String data);

    void onSendWaterMessage(String data);

    void onDeleteTestPressure(String data);

    void onAuditAppTestPressure(String data);
}
