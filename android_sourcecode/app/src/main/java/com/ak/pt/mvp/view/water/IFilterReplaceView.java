package com.ak.pt.mvp.view.water;

import com.ak.pt.bean.FilterReplaceBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/5/22.
 */

public interface IFilterReplaceView extends BaseView{
    void onInsertFilterElement(String data);

    void onDeleteFilterElement(String data);

    void onDeleteFilterFile(String data);

    void onUpdateFilterElement(String data);

    void onGetFilterElementDetail(FilterReplaceBean data);

    void OnGetFilterElementList(List<FilterReplaceBean> data);

    void onUploadFiles(String[] data);
}
