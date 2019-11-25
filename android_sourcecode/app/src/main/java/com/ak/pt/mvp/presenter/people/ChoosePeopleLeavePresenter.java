package com.ak.pt.mvp.presenter.people;

import com.ak.pt.App;
import com.ak.pt.bean.StaffInfoLeaveBean;
import com.ak.pt.http.HttpResult;
import com.ak.pt.mvp.base.BasePresenter;
import com.ak.pt.mvp.view.people.IChoosePeopleLeaveView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChoosePeopleLeavePresenter extends BasePresenter<IChoosePeopleLeaveView>{
    public ChoosePeopleLeavePresenter(App app) {
        super(app);
    }
    public void getStaffInfoList(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getStaffInfoList(token, parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<StaffInfoLeaveBean>>>() {

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
                    public void onNext(HttpResult<List<StaffInfoLeaveBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().getStaffInfoList(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }
}
