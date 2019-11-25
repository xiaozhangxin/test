package com.ak.pt.mvp.view.water;

import com.ak.pt.bean.FeedBackBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/5/23.
 */

public interface IFeedbackView extends BaseView{
    void onInsertQualityFeedback(String data);

    void onDeleteQualityFeedback(String data);

    void onDeleteQualityFile(String data);

    void onUpdateQualityFeedback(String data);

    void OnGetQualityFeedbackList(List<FeedBackBean> data);

    void onGetQualityFeedbackDetail(FeedBackBean data);

    void onUploadFiles(String[] data);
}
