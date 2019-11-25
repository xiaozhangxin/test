package com.ak.pt.mvp.presenter;

import com.ak.pt.App;
import com.ak.pt.bean.PressureDropBean;
import com.ak.pt.http.HttpResult;
import com.ak.pt.mvp.base.BasePresenter;
import com.ak.pt.mvp.view.IPressureDropView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2019/4/29.
 */

public class PressureDropPresenter extends BasePresenter<IPressureDropView>{

    public PressureDropPresenter(App app) {
        super(app);
    }

    public void getPressureDropList(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getPressureDropList(token, parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<PressureDropBean>>>() {

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
                    public void onNext(HttpResult<List<PressureDropBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().OnGetPressureDropList(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }
}
