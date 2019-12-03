package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.StoragingProBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IFinishView extends BaseView{
    void onQueryStoragingProList(List<StoragingProBean> data);

    void onQueryStoragingPro(StoragingProBean data);

    void onAddStoragingPro(String data);

    void onPastStoragingPro(String data);

    void onInvalidStoragingPro(String data);

    void onDelStoragingPro(String data);
}
