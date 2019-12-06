package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.ReprotedBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/19.
 */

public interface IReportView extends BaseView {
    void OnInsertProjectApply(String data);

    void OnGetProjectApplyList(List<ReprotedBean> data, String total);

    void OnGetProjectApply(ReprotedBean data);

    void OnAuditProjectApply(String data);

    void OnDeleteProjectApply(String data);

    void OnupdateProjectApply(String data);

    void onGetCheckPerson(List<MeParentBean> data);
}
