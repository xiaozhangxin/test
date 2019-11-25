package com.ak.pt.mvp.view.area;

import com.ak.pt.bean.AreaStudyBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/5/27.
 */

public interface IRegionView extends BaseView{
    void onIinsertAreaStudy(String data);

    void onDdeleteAreaStudy(String data);

    void onDdeleteAreaStudyFile(String data);

    void onUupdateAreaStudy(String data);

    void OnGgetAreaStudyList(List<AreaStudyBean> data);

    void onGgetAreaStudyDetail(AreaStudyBean data);

    void onUploadFiles(String[] data);

    void onUauditAreaStudy(String data);
}
