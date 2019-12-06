package com.akan.qf.mvp.fragment.statistics;


import com.akan.qf.mvp.base.BaseView;
import com.akan.qf.mvp.fragment.qifei.BigAreaBean;

import java.util.List;

/**
 * Created by admin on 2018/11/23.
 */

public interface IPressureView extends BaseView {
    void OnQueryAreaCountPressurePage(List<BigAreaBean> data);

    void OnQueryPressurePage(List<PressurePageBean> data);

    void OnQueryDetail(PressurePageBean data);

    void OnQueryBigAreaCountPressurePage(List<BigAreaBean> data);

    void OnGetTestPressureList(List<PressurePageBean> data, String total);

    void onAuditTestPressure(String data);

    void onSendWaterMessage(String data);

    void onDeleteTestPressure(String data);

    void onAuditAppTestPressure(String data);
}
