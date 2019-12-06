package com.akan.qf.mvp.view.mine;

import com.akan.qf.bean.ContributeBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/22.
 */

public interface IArticleView extends BaseView {
    void OnGetAreaContributeList(List<ContributeBean> data,String total);

    void onUploadFiles(String[] data);

    void OnInsertOrUpdateAreaContribute(String data);

    void OnGetAreaContributeDetail(ContributeBean data);

    void OnInsertContributeComment(String data);

    void OnDeleteAreaContribute(String data);

    void OnAauditAreaContribute(String data);
}
