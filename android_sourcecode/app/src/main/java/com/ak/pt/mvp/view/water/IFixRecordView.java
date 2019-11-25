package com.ak.pt.mvp.view.water;

import com.ak.pt.bean.FixRecordBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/5/22.
 */

public interface IFixRecordView extends BaseView{
    void onInsertRepairRecord(String data);

    void onDeleteRepairRecord(String data);

    void onDeleteRepairFile(String data);

    void onUpdateRepairRecord(String data);

    void OnGetRepairRecordList(List<FixRecordBean> data);

    void onUploadFiles(String[] data);

    void onGetRepairRecordDetail(FixRecordBean data);
}
