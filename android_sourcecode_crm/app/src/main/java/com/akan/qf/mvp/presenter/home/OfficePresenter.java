package com.akan.qf.mvp.presenter.home;

import com.akan.qf.App;
import com.akan.qf.bean.DocumentBean;
import com.akan.qf.bean.WaterBean;
import com.akan.qf.http.HttpResult;
import com.akan.qf.mvp.base.BasePresenter;
import com.akan.qf.mvp.view.home.IOfficeView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2019/2/27.
 */

public class OfficePresenter extends BasePresenter<IOfficeView>{
    public OfficePresenter(App app) {
        super(app);
    }

    public void getDocumentList(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getDocumentList(token, parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<DocumentBean>>>() {

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<List<DocumentBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().OnGetDocumentList(userBeanHttpResult.getData(),userBeanHttpResult.getTotal());
                            }
                        }

                    }
                });

    }
}
