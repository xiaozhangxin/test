package com.akan.qf.mvp.view.channel;

import com.akan.qf.bean.NetworkBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/2/25.
 */

public interface INetworkView extends BaseView{

    void OnInsertOrUpdateDistributionApply(String data);

    void OnGetDistributionApplyList(List<NetworkBean> data, String total);

    void OnGetDistributionApply(NetworkBean data);

    void OnAuditDistributionApply(String data);

    void OnDeleteDistributionApply(String data);
}
