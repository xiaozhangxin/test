package com.akan.qf.mvp.presenter.mine;

import com.akan.qf.App;
import com.akan.qf.bean.NoticeBean;
import com.akan.qf.http.HttpResult;
import com.akan.qf.mvp.base.BasePresenter;
import com.akan.qf.mvp.view.mine.INoticeView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2018/11/7.
 */

public class NoticePresenter extends BasePresenter<INoticeView>{
    public NoticePresenter(App app) {
        super(app);
    }

    public void getNoticeList(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getNoticeList(token, parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<NoticeBean>>>() {

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
                    public void onNext(HttpResult<List<NoticeBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().OnGetNoticeList(userBeanHttpResult.getData(), userBeanHttpResult.getTotal());
                            }
                        }

                    }
                });

    }
    public void getNoticeDetail(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getNoticeDetail(token, parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<NoticeBean>>() {

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
                    public void onNext(HttpResult<NoticeBean> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().OnGetNoticeDetail(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }  public void getNotReadNoticeCount(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getNotReadNoticeCount(token, parmer)
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
                                getView().OnGetNotReadNoticeCount(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }
}
