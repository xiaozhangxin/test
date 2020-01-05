package com.akan.wms.mvp.fragment.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.akan.wms.Constants;
import com.akan.wms.R;
import com.akan.wms.bean.AppVersionBean;
import com.akan.wms.bean.BarMsgBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WarnTwoBean;
import com.akan.wms.mvp.activity.ContentActivity;
import com.akan.wms.mvp.base.BaseActivity;
import com.akan.wms.mvp.presenter.home.HomePresenter;
import com.akan.wms.mvp.view.home.IHomeView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.king.zxing.CaptureHelper;
import com.king.zxing.Intents;
import com.king.zxing.OnCaptureCallback;
import com.king.zxing.ViewfinderView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.akan.wms.Constants.HOME_SCAN;
import static com.akan.wms.Constants.HOME_SCAN_INPUT;

public class HomeScanActivity extends BaseActivity <IHomeView, HomePresenter> implements IHomeView,OnCaptureCallback {

    public static final String KEY_RESULT = Intents.Scan.RESULT;
    @BindView(R.id.surfaceView)
    SurfaceView surfaceView;
    @BindView(R.id.viewfinderView)
    ViewfinderView viewfinderView;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.llInput)
    LinearLayout llInput;
    @BindView(R.id.ivScanLight)
    ImageView ivScanLight;
    @BindView(R.id.llLight)
    LinearLayout llLight;

    private CaptureHelper mCaptureHelper;
    private boolean isOpenLight=false;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mResult="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeStatusBarTransparent(this);
        ButterKnife.bind(this);

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_home_scan;
    }

    @Override
    public void initUI() {
        mCaptureHelper = new CaptureHelper(this, surfaceView, viewfinderView);
        mCaptureHelper.setOnCaptureCallback(this);
        mCaptureHelper.onCreate();
        mCaptureHelper.setOnCaptureCallback(this);
        mCaptureHelper.vibrate(true)
                .playBeep(true)
                .fullScreenScan(true)//全屏扫码
                .supportVerticalCode(true)//支持扫垂直条码，建议有此需求时才使用。
                .continuousScan(false)
                .supportAutoZoom(true);
    }

    public static void makeStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();

    }


    /**
     * 接收扫码结果回调
     *
     * @param result 扫码结果
     * @return 返回true表示拦截，将不自动执行后续逻辑，为false表示不拦截，默认不拦截
     */
    @Override
    public boolean onResultCallback(String result) {
        mResult=result;
        map.clear();
        map.put("barCode",result);
        getPresenter().selectItemBarMsgList(userBean.getStaff_token(),map);
        return true;
    }



    @Override
    public void onSelectItemBarMsgList(BarMsgBean data) {
        Intent intent = new Intent(HomeScanActivity.this, ContentActivity.class);
        intent.putExtra(Constants.KEY_FRAGMENT, HOME_SCAN);
        intent.putExtra("bean",data);
        startActivity(intent);
    }

    @Override
    public void OnGetAppVersionDetail(AppVersionBean data) {

    }


    @OnClick({R.id.ivBack, R.id.llInput, R.id.llLight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.llInput:
                Intent intent = new Intent(HomeScanActivity.this, ContentActivity.class);
                intent.putExtra(Constants.KEY_FRAGMENT, HOME_SCAN_INPUT);
                startActivity(intent);
                break;
            case R.id.llLight:
                if (isOpenLight){
                    offFlash();
                    isOpenLight=false;
                    ivScanLight.setImageResource(R.drawable.scan_light_black);
                }
                else {
                    openFlash();
                    ivScanLight.setImageResource(R.drawable.scan_light_blue);
                    isOpenLight=true;
                }
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mCaptureHelper.onResume();
    }

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(getApp());
    }

    @Override
    public void onPause() {
        super.onPause();
        mCaptureHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCaptureHelper.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mCaptureHelper.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    /**
     * 关闭闪光灯（手电筒）
     */
    private void offFlash() {
        Camera camera = mCaptureHelper.getCameraManager().getOpenCamera().getCamera();
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(parameters);
    }

    /**
     * 开启闪光灯（手电筒）
     */
    public void openFlash() {
        Camera camera = mCaptureHelper.getCameraManager().getOpenCamera().getCamera();
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parameters);
    }

    @Override
    public void onQueryBoardWarnings(List<WarnTwoBean> data) {

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        showDialog();

    }

    //操作确认弹框
    private void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("("+mResult+")\n查询失败,是否重新扫码？");
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCaptureHelper.restartPreviewAndDecode();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create().show();

    }




}