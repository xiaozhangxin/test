package com.ak.pt.mvp.view;


import com.ak.pt.bean.SignBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/7/2.
 */

public interface ISignView extends BaseView {


    void onUploadFiles(String[] data);

    void onGetSignList(List<SignBean> data);

    void onGetDaySignList(List<SignBean> data);

    void onUploadFile(String data);

    void onInsertSign(String data);
}
