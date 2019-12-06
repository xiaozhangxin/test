package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.VisitorBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/16.
 */

public interface IVisitView extends BaseView{
    void OnInsertVisitorApply(String data);

    void OnGetVisitorApplyList(List<VisitorBean> data, String total);

    void OnGetVisitorApply(VisitorBean data);

    void OnAuditVisitorApply(String data);

    void OnupdateVisitorApply(String data);

    void OnDeleteVisitorApply(String data);

    void onGetCheckPerson(List<MeParentBean> data);
}
