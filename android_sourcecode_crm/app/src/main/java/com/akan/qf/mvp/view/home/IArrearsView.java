package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.DebtApplyBean;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/30.
 */

public interface IArrearsView extends BaseView{
    void OnAuditDebtApply(String data);

    void OnGetDebtApply(DebtApplyBean data);

    void OnGetDebtApplyList(List<DebtApplyBean> data, String total);

    void OnInsertOrUpdateDebtApply(String data);

    void OnDeleteDebtApply(String data);

    void onGetCheckPerson(List<MeParentBean> data);
}
