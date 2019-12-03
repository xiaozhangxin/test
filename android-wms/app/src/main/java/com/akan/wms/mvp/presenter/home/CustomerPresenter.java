package com.akan.wms.mvp.presenter.home;

import com.akan.wms.App;
import com.akan.wms.bean.CustomBean;
import com.akan.wms.http.HttpResult;
import com.akan.wms.mvp.base.BasePresenter;
import com.akan.wms.mvp.view.home.ICustomerView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CustomerPresenter extends BasePresenter<ICustomerView>{
    public CustomerPresenter(App app) {
        super(app);
    }

    public void queryCustomLists(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .queryCustomLists(token, parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<CustomBean>>>() {

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
                    public void onNext(HttpResult<List<CustomBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().OnQueryCustomLists(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }
}
