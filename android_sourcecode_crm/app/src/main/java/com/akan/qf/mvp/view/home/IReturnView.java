package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.RetnrnBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/14.
 */

public interface IReturnView extends BaseView{
    void OnInsertGoodsApply(String data);

    void OnGetGoodsApplyList(List<RetnrnBean> data, String total);

    void OnGetGoodsApply(RetnrnBean data);

    void OnAuditGoodsApply(String data);

    void onUploadFiles(String[] data);

    void OndeleteGoodsApply(String data);

    void OnupdateGoodsApplyList(String data);

    void OndeleteGoodsApplyFile(String data);

    void onGetCheckPerson(List<MeParentBean> data);
}
