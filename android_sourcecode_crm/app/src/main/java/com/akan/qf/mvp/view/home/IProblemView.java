package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.NewApplyBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/13.
 */

public interface IProblemView extends BaseView{
    void onUploadFiles(String[] data);

    void OnInsertNewApplyy(String data);

    void OnGetNewApplyList(List<NewApplyBean> data, String total);

    void OnGetNewApply(NewApplyBean data);

    void OnAuditNewApply(String data);

    void OndeleteNewApplyFile(String data);

    void OndeleteNewApply(String data);

    void OnupdateNewApply(String data);
}
