package com.ak.pt.mvp.presenter;


import com.ak.pt.App;
import com.ak.pt.mvp.base.BasePresenter;
import com.ak.pt.mvp.view.IWebView;

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
