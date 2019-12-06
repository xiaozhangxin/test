package com.akan.qf.mvp.presenter;

import com.akan.qf.App;
import com.akan.qf.bean.NullBean;
import com.akan.qf.http.HttpResult;
import com.akan.qf.mvp.base.BasePresenter;
import com.akan.qf.mvp.view.IWebLoginView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2018/12/4.
 */

public class WebLoginPresenter extends BasePresenter<IWebLoginView>{
    public WebLoginPresenter(App app) {
        super(app);
    }
    public void scanQrLoginCode(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .scanQrLoginCode(token, parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String>>() {

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
                    public void onNext(HttpResult<String> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().OnScanQrLoginCode(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }
}
