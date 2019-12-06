package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.PeopleJionBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/2/18.
 */

public interface IPeopleJoinView extends BaseView{
    void onInsertOrUpdateRecruitApply(String data);

    void onGetRecruitApplyList(List<PeopleJionBean> data, String total);

    void onGetRecruitApply(PeopleJionBean data);

    void onAuditRecruitApply(String data);

    void onDeleteRecruitApply(String data);

    void onGetCheckPerson(List<MeParentBean> data);
}
