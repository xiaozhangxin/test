package com.ak.pt.mvp.fragment;


import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.FirstEventWorker;
import com.ak.pt.bean.UserBean;
import com.ak.pt.bean.WorkerBean;
import com.ak.pt.mvp.activity.Logger;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.WebFragmentPresenter;
import com.ak.pt.mvp.view.IWebFragmentView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.king.base.util.LogUtils;
import com.king.base.util.StringUtils;
import com.king.base.util.SystemUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/21
 */

public class WebFragment extends BaseFragment<IWebFragmentView, WebFragmentPresenter> implements IWebFragmentView {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.vError)
    LinearLayout vError;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.fc)
    FrameLayout fc;
    @BindView(R.id.ivRightTwo)
    ImageView ivRightTwo;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    Unbinder unbinder;

    private String url;
    private String title;
    protected boolean isError;

    private boolean isShowError;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mCode;

    public static WebFragment newInstance(String url, String title, String mCode) {
        Bundle args = new Bundle();

        WebFragment fragment = new WebFragment();
        fragment.mCode = mCode;
        fragment.url = url;
        fragment.title = title;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_webwiew;
    }

    @Override
    public void initUI() {

        if ("真伪查询结果".equals(title)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("确定");
        }

        if (!StringUtils.isBlank(title)) {
            tvTitle.setText(title);
        }

        progressBar.setMax(100);

        isShowError = addErrorView(vError);

        WebSettings ws = webView.getSettings();
        //是否允许脚本支持
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        // 开启JavaScript支持
        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "java_obj");
        ws.setJavaScriptCanOpenWindowsAutomatically(true);

//        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);

//        webView.setHorizontalScrollBarEnabled(false);

//        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        String str = getIntent().getStringExtra(Constants.KEY_URL);
        if (!TextUtils.isEmpty(str)) {
            this.url = str;
        }


        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                updateProgressBar(newProgress);
            }

        });
        webView.setWebViewClient(getWebViewClient());

        load(webView, url);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }


    public WebViewClient getWebViewClient() {
        return new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LogUtils.d("startUrl:" + url);
                isError = false;
                if (view != null) {
                    webView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.d("url:" + url);

                return super.shouldOverrideUrlLoading(view, url);

            }


            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                updateProgressBar(100);
            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                LogUtils.w("errorCode:" + errorCode + "|failingUrl:" + failingUrl);
                showReceiveError();
            }


            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.cancel();
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // view.loadUrl("javascript:window.java_obj.showSource(document.getElementsByTagName('html')[0].innerHTML);");
                // 获取解析<span id=rtnCon" rtnCon="获取到的值">
                view.loadUrl("javascript:window.java_obj.showDescription(document.getElementById('qcontent').innerHTML);");
                super.onPageFinished(view, url);
                if (view != null) {
                    // 获取页面内容
                    hideReceiveError();
                }
            }
        };
    }

    /**
     * @param group
     * @return true表示已添加ErrorView并显示ErrorView/false表示不处理
     */
    public boolean addErrorView(ViewGroup group) {
        group.addView(LayoutInflater.from(context).inflate(R.layout.layout_error, null));
        return true;
    }

    private void showReceiveError() {
        isError = true;
        if (SystemUtils.isNetWorkActive(context)) {
            LogUtils.w("Page loading failed.");
        } else {
            LogUtils.w("Network unavailable.");
        }

        if (isShowError) {
            webView.setVisibility(View.GONE);
            vError.setVisibility(View.VISIBLE);

        }


    }

    private void hideReceiveError() {
        try {
            if (isError) {
                showReceiveError();
            } else {
                webView.setVisibility(View.VISIBLE);
                vError.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 加载url
     *
     * @param webView
     * @param url
     */
    private void load(WebView webView, String url) {
        Logger.e("url:", url);
        if (!TextUtils.isEmpty(url)) {
            webView.loadUrl(url);
        }

    }

    private boolean isGoBack() {
        return webView != null && webView.canGoBack();
    }


    private void updateProgressBar(int progress) {
        updateProgressBar(true, progress);
    }


    private void updateProgressBar(boolean isVisibility, int progress) {

        try {
            progressBar.setVisibility((isVisibility && progress < 100) ? View.VISIBLE : View.GONE);
            progressBar.setProgress(progress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                if (!TextUtils.isEmpty(result)) {
                    EventBus.getDefault().post(new FirstEventWorker("2",new WorkerBean(result,"","")));
                }
                finish();
                break;
        }
    }

    private String result = "";

    @Override
    public void onInsertAntiFake(String data) {
       // Log.e("result", data);
        result = data;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());

    }

    @Override
    public WebFragmentPresenter createPresenter() {
        return new WebFragmentPresenter(getApp());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showDescription(String str) {
            map.put("staff_id", userBean.getStaff_id());
            map.put("fake_name", userBean.getStaff_name());
            map.put("fake_department", userBean.getSimple_department_name());
            map.put("fake_no", mCode);
            map.put("is_true", str);
            getPresenter().insertAntiFake(userBean.getStaff_token(), map);

        }
    }

}
