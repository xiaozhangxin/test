package com.ak.pt.mvp.view;

import com.ak.pt.bean.FileBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/3/27.
 */

public interface IDocumentNewView extends BaseView{
    void OnGetFilePaperList(List<FileBean> data,String total);
}
