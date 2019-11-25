package com.ak.pt.mvp.view.water;

import com.ak.pt.bean.WarrantyBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/5/23.
 */

public interface IWarrantyView extends BaseView{

    void onIinsertElectronicCard(String data);

    void onDdeleteElectronicCard(String data);

    void onDdeleteElectronicImg(String data);

    void onUupdateElectronicCard(String data);

    void OnGgetElectronicCardList(List<WarrantyBean> data);

    void onGgetElectronicCardDetail(WarrantyBean data);

    void onUploadFiles(String[] data);
}
