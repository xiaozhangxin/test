package com.akan.qf.mvp.presenter;

import com.akan.qf.App;
import com.akan.qf.bean.SignBean;
import com.akan.qf.http.HttpResult;
import com.akan.qf.mvp.base.BasePresenter;
import com.akan.qf.mvp.view.ISignRecordView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2018/7/2.
 */

public class SignRecordPresenter extends BasePresenter<ISignRecordView> {
    public SignRecordPresenter(App app) {
        super(app);
    }

    public void getSignList(String token,Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getSignList(token,parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<SignBean>>>() {

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
                    public void onNext(HttpResult<List<SignBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onGetSignList(userBeanHttpResult.getData(), userBeanHttpResult.getTotal());
                            }
                        }

                    }
                });
    }  public void getDaySignList(String token,Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getDaySignList(token,parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<SignBean>>>() {

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
                    public void onNext(HttpResult<List<SignBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onGetDaySignList(userBeanHttpResult.getData());
                            }
                        }

                    }
                });
    }
}
