package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.ReimbursementInfoBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/10/29.
 */

public interface IReimburseView extends BaseView{
    void onGetExpenseReimbursement(ReimbursementInfoBean data);

    void onAuditExpenseReimbursementt(String data);

    void onGetExpenseReimbursementInfoList(List<ReimbursementInfoBean> data, String total);

    void onDeleteExpenseReimbursement(String data);

    void onGetCheckPerson(List<MeParentBean> data);
}
