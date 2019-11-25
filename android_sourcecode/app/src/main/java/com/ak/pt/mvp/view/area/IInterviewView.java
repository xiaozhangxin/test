package com.ak.pt.mvp.view.area;

import com.ak.pt.bean.InterviewBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/5/28.
 */

public interface IInterviewView extends BaseView{
    void oninsertAreaInterview(String data);

    void ondeleteAreaInterview(String data);

    void ondeleteAreaInterviewFile(String data);

    void onupdateAreaInterview(String data);

    void onauditAreaInterview(String data);

    void OngetAreaInterviewList(List<InterviewBean> data);

    void ongetAreaInterviewDetail(InterviewBean data);

    void onUploadFiles(String[] data);
}
