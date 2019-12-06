package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.PeopleNewBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/2/20.
 */

public interface IPeopleNewView extends BaseView{
    void onInsertOrUpdateEntryApply(String data);

    void onGetEntryApplyList(List<PeopleNewBean> data, String total);

    void onGetEntryApplyDaily(PeopleNewBean data);

    void onAuditEntryApply(String data);

    void onDeleteEntryApply(String data);

    void onUploadFiles(String[] data);

    void onGetCheckPerson(List<MeParentBean> data);
}
