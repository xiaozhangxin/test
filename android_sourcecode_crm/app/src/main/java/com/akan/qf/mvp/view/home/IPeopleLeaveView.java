package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.PeopleLeaveBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/2/21.
 */

public interface IPeopleLeaveView extends BaseView{

    void onInsertOrUpdateQuiteApply(String data);

    void onGetQuiteApplyList(List<PeopleLeaveBean> data, String total);

    void onGetQuiteApplyDaily(PeopleLeaveBean data);

    void onAuditQuiteApply(String data);

    void onDeleteQuiteApply(String data);

    void onUploadFile(String data);

    void onGetCheckPerson(List<MeParentBean> data);
}
