package com.akan.qf.mvp.presenter;

import com.akan.qf.App;
import com.akan.qf.http.HttpResult;
import com.akan.qf.mvp.base.BasePresenter;
import com.akan.qf.mvp.view.ISignView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * Created by admin on 2018/7/2.
 */

public class SignPresenter extends BasePresenter<ISignView>{
    public SignPresenter(App app) {
        super(app);
    }

    public void insertSign(String token,Map<String, String> map) {
        getAppComponent().getAPIService()
                .insertSign(token,map)
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
                                getView().onInsertSign(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }


    public void uploadFile(String token,RequestBody map) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .uploadFile(token,map)
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
                                getView().onUploadFile(userBeanHttpResult.getData());

                            }
                        }
                    }
                });
    }

}
