package com.akan.qf.mvp.fragment.statistics;


import com.akan.qf.App;
import com.akan.qf.http.HttpResult;
import com.akan.qf.mvp.base.BasePresenter;
import com.akan.qf.mvp.fragment.qifei.AreaPressureBean;
import com.akan.qf.mvp.fragment.qifei.BigAreaBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2019/3/20.
 */

public class PtRankPresenter extends BasePresenter<IPtRankView> {
    public PtRankPresenter(App app) {
        super(app);
    }

    public void getAreaTestPressure(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getAreaTestPressure(token, parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<BigAreaBean>>>() {

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
                    public void onNext(HttpResult<List<BigAreaBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().OnGetAreaTestPressure(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }
}
