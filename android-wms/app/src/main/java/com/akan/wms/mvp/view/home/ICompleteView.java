package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.CompleteDecBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface ICompleteView extends BaseView{
    void onQueryCompleteDecList(List<CompleteDecBean> data);

    void onQueryCompleteDec(CompleteDecBean data);

    void onAddCompleteDec(String data);

    void onPastCompleteDec(String data);

    void onInvalidCompleteDec(String data);

    void onDelCompleteDec(String data);
}
