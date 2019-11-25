package com.ak.pt.mvp.presenter;

import com.ak.pt.App;
import com.ak.pt.http.HttpResult;
import com.ak.pt.mvp.base.BasePresenter;
import com.ak.pt.mvp.view.IWebFragmentView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2019/3/26.
 */

public class WebFragmentPresenter extends BasePresenter<IWebFragmentView>{
    public WebFragmentPresenter(App app) {
        super(app);
    }

    public void insertAntiFake(String token,Map<String, String> map) {
        getAppComponent().getAPIService()
                .insertAntiFake(token,map)
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
                                getView().onInsertAntiFake(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }
}
