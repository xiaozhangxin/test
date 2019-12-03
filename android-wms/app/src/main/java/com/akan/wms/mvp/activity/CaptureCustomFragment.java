package com.akan.wms.mvp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.mvp.base.SimpleFragment;
import com.king.base.util.ToastUtils;
import com.king.zxing.CaptureFragment;
import com.king.zxing.CaptureHelper;
import com.king.zxing.OnCaptureCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Fragment扫码
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class CaptureCustomFragment extends SimpleFragment implements OnCaptureCallback {


    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    TextView ivRight;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragmentContent)
    FrameLayout fragmentContent;
    private List<String> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            Window window = getActivity().getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public static CaptureCustomFragment newInstance() {
        Bundle args = new Bundle();
        CaptureCustomFragment fragment = new CaptureCustomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_activity_capture;
    }


    @Override
    public void initUI() {
        list = new ArrayList<>();
        CaptureFragment captureFragment = CaptureFragment.newInstance();
        CaptureHelper mCaptureHelper = captureFragment.getCaptureHelper();
        mCaptureHelper.onCreate();
        mCaptureHelper.vibrate(true)
                .fullScreenScan(true)//全屏扫码
                .supportVerticalCode(false)//支持扫垂直条码，建议有此需求时才使用。
                .continuousScan(true);
        replaceFragment(captureFragment);


    }


    @Override
    public void initData() {

    }

    public void replaceFragment(Fragment fragment) {
        replaceFragment(R.id.fragmentContent, fragment);
    }

    public void replaceFragment(@IdRes int id, Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(id, fragment).commit();
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

    @OnClick({R.id.ivLeft, R.id.ivRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivRight:
                break;
        }
    }


    @Override
    public boolean onResultCallback(String result) {
        list.add(result);
        ToastUtils.showToast(context.getApplicationContext(), result);
        return true;
    }



}
