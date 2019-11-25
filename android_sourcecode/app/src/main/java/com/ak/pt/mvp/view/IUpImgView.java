package com.ak.pt.mvp.view;

import com.ak.pt.mvp.base.BaseView;

/**
 * Created by admin on 2019/3/18.
 */

public interface IUpImgView extends BaseView{
    void onInsertPiptbFixPhoto(String data);

    void onUploadFiles(String[] data);
}
