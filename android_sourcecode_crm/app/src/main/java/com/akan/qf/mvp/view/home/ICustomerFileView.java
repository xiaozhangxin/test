package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.ArchivesApplyBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/27.
 */

public interface ICustomerFileView extends BaseView
{
    void OnInsertArchivesApply(String data);

    void OnGetArchivesApplyList(List<ArchivesApplyBean> data);

    void OngetArchivesApply(ArchivesApplyBean data);

    void OndeleteArchivesApply(String data);

    void OnupdateArchivesApply(String data);

    void onUploadFiles(String[] data);
}
