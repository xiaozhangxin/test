package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.PayApplyBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/5.
 */

public interface IPayRequestView extends BaseView{
    void OnInsertPayApply(String data);

    void OnGetPayApplyList(List<PayApplyBean> data, String total);

    void OnGetPayApply(PayApplyBean data);

    void onUploadFiles(String[] data);

    void OnAuditPayApply(String data);

    void OnDeletePayApply(String data);

    void OnDeletePayApplyFile(String data);

    void OnUpdatePayApply(String data);

    void onGetCheckPerson(List<MeParentBean> data);
}
