package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.TemporaryBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/9.
 */

public interface ITemporaryView extends BaseView{
    void OnInsertTemporarySupport(String data);

    void OnGetTemporarySupportList(List<TemporaryBean> data, String total);

    void OnGetTemporarySupport(TemporaryBean data);

    void OnAuditTemporarySupport(String data);

    void onUploadFiles(String[] data);

    void OnDeleteTemporarySupportFile(String data);

    void OnDeleteTemporarySupport(String data);

    void OnUpdateTemporarySupport(String data);

    void onGetCheckPerson(List<MeParentBean> data);
}
