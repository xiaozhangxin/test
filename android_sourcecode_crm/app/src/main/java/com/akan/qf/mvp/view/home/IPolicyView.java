package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.PolicyBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/9.
 */

public interface IPolicyView extends BaseView{
    void OnInsertPolicyApply(String data);

    void OnGetPolicyApplyList(List<PolicyBean> data, String total);

    void OnGetPolicyApply(PolicyBean data);

    void OnAuditPolicyApply(String data);

    void onUploadFiles(String[] data);

    void OnDeletePolicyApply(String data);

    void OnDeletePolicyApplyFile(String data);

    void OnUpdatePolicyApply(String data);

    void onGetCheckPerson(List<MeParentBean> data);
}
