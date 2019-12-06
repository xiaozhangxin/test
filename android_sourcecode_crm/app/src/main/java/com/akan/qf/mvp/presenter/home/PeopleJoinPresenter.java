package com.akan.qf.mvp.presenter.home;

import com.akan.qf.App;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.PeopleJionBean;
import com.akan.qf.http.HttpResult;
import com.akan.qf.mvp.base.BasePresenter;
import com.akan.qf.mvp.view.home.IPeopleJoinView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2019/2/18.
 */

public class PeopleJoinPresenter extends BasePresenter<IPeopleJoinView>{
    public PeopleJoinPresenter(App app) {
        super(app);
    }
    public void getCheckPerson(String token,Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getMyAndParentDepartmentStaffList(token,parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<MeParentBean>>>() {

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
                    public void onNext(HttpResult<List<MeParentBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onGetCheckPerson(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }
    public void insertOrUpdateRecruitApply(String token,Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .insertOrUpdateRecruitApply(token,parmer)
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
                                getView().onInsertOrUpdateRecruitApply(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }
    public void getRecruitApplyList(String token,Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getRecruitApplyList(token,parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<PeopleJionBean>>>() {

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
                    public void onNext(HttpResult<List<PeopleJionBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onGetRecruitApplyList(userBeanHttpResult.getData(), userBeanHttpResult.getTotal());
                            }
                        }

                    }
                });

    }
    public void getRecruitApply(String token,Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getRecruitApply(token,parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<PeopleJionBean>>() {

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
                    public void onNext(HttpResult<PeopleJionBean> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onGetRecruitApply(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }
    public void auditRecruitApply(String token,Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .auditRecruitApply(token,parmer)
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
                                getView().onAuditRecruitApply(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }
    public void deleteRecruitApply(String token,Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .deleteRecruitApply(token,parmer)
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
                                getView().onDeleteRecruitApply(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }

}
