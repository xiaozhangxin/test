package com.akan.qf.mvp.view.channel;

import com.akan.qf.bean.WaterBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/2/25.
 */

public interface IWaterView extends BaseView{


    void OnInsertOrUpdateForemanApply(String data);

    void OnGetForemanApplyList(List<WaterBean> data, String total);

    void OnGetForemanApply(WaterBean data);

    void OnAuditForemanApply(String data);

    void OnDeleteForemanApply(String data);
}
