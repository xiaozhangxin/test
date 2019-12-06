package com.akan.qf.mvp.presenter;

import com.akan.qf.App;
import com.akan.qf.mvp.base.BasePresenter;
import com.akan.qf.mvp.view.IWebView;

import javax.inject.Inject;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/21
 */

public class WebPresenter extends BasePresenter<IWebView> {

    @Inject
    public WebPresenter(App app) {
        super(app);
    }




}
