package com.akan.wms.mvp.presenter.part;

import com.akan.wms.App;
import com.akan.wms.bean.DailyBean;
import com.akan.wms.bean.NoticeBean;
import com.akan.wms.http.HttpResult;
import com.akan.wms.mvp.base.BasePresenter;
import com.akan.wms.mvp.view.part.IPartView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PartPresenter extends BasePresenter<IPartView>{

    public PartPresenter(App app) {
        super(app);
    }

}
