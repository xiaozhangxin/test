package com.akan.qf.mvp.view.ad;

import com.akan.qf.bean.AdManagementBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/2/21.
 */

public interface IADManagementView extends BaseView{
    void onInsertOrUpdateAdvertApply(String data);

    void onGetAdvertApplyList(List<AdManagementBean> data, String total);

    void onGetAdvertApply(AdManagementBean data);

    void onAuditAdvertApply(String data);

    void onDdeleteAdvertApply(String data);

    void onDeleteAdvertApplyFile(String data);

    void onUploadFiles(String[] data);
}
