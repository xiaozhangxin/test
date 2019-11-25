package com.ak.pt.mvp.view.mall;

import com.ak.pt.bean.AddressBean;
import com.ak.pt.mvp.base.BaseView;

/**
 * Created by admin on 2019/5/10.
 */

public interface IMallOrderView extends BaseView{
    void onGetDefaultAddress(AddressBean data);

    void onInsertOrder(String data);
}
