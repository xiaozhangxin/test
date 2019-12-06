package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.StoreApplyBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/30.
 */

public interface IProjectView extends BaseView{
    void OnInsertOrUpdateStoreApply(String data);

    void OnGetStoreApplyList(List<StoreApplyBean> data, String total);

    void OnGetStoreApply(StoreApplyBean data);

    void OnAuditStoreApply(String data);

    void OndeleteStoreApply(String data);
}
