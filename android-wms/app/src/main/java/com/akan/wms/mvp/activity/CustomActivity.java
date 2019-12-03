package com.akan.wms.mvp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.BarBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.InforListBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.ScanPresenter;
import com.akan.wms.mvp.view.ISacnView;
import com.akan.wms.util.SpSingleInstance;
import com.king.zxing.CaptureHelper;
import com.king.zxing.OnCaptureCallback;
import com.king.zxing.ViewfinderView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 自定义扫码：当直接使用CaptureActivity
 * 自定义扫码，切记自定义扫码需在{@link Activity}或者{@link Fragment}相对应的生命周期里面调用{@link #mCaptureHelper}对应的生命周期
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class CustomActivity extends BaseFragment<ISacnView, ScanPresenter> implements ISacnView, OnCaptureCallback {
    public static final int REQUEST_CODE_SCAN = 0X01;
    public static final int REQUEST_CODE_PHOTO = 0X02;
    @BindView(R.id.surfaceView)
    SurfaceView surfaceView;
    @BindView(R.id.viewfinderView)
    ViewfinderView viewfinderView;
    @BindView(R.id.ivFlash)
    TextView ivFlash;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    TextView ivRight;

    Unbinder unbinder;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private CaptureHelper mCaptureHelper;

    private List<InforListBean> oldList;
    private List<BarBean> barList;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mCode = "";
    private String type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getActivity().getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /*    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Bundle bundle = getIntent().getExtras();
        type = bundle.getString(Constants.DETAIL_TYPE);
        switch (type) {
            case "receive":
                oldList = (List<InforListBean>) bundle.getSerializable(Constants.LIST_DATA);
                break;
        }

    }*/

    public static CustomActivity newInstance(List<InforListBean> list,String type) {
        Bundle args = new Bundle();
        CustomActivity fragment = new CustomActivity();
        fragment.type=type;
        fragment.oldList = list;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.custom_activity;
    }

    @Override
    public void initUI() {
        barList = new ArrayList<>();
        mCaptureHelper = new CaptureHelper(this, surfaceView, viewfinderView);
        mCaptureHelper.onCreate();
        mCaptureHelper.setOnCaptureCallback(this);
        mCaptureHelper.vibrate(true)
                .playBeep(true)
                .fullScreenScan(true)//全屏扫码
                .supportVerticalCode(false)//支持扫垂直条码，建议有此需求时才使用。
                .continuousScan(true)
                .autoRestartPreviewAndDecode(false);

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    /**
     * 扫码结果回调
     *
     * @param result 扫码结果
     * @return
     */
    @Override
    public boolean onResultCallback(String result) {
        mCode = result;
        queryBar(result);
        return true;
    }

    //请求服务器
    private void queryBar(String barCode) {
        map.clear();
        map.put("bar", barCode);
        getPresenter().queryBar(userBean.getStaff_token(), map);

    }

    //请求服务器返回
    @Override
    public void OnQueryBar(BarBean data) {
        if (data != null) {
            ResultProcess(data);
        } else {
            showNotDialog(mCode);
        }

    }

    //扫码结果处理
    private void ResultProcess(BarBean bean) {
        boolean isIn = false;
        for (int i = 0; i < oldList.size(); i++) {
            if (oldList.get(i).getItem_id().equals(bean.getItem_id())) {
                int qty = bean.getQty();
                int number = oldList.get(i).getScan_num();
                oldList.get(i).setScan_num(number + qty);
                bean.setOld_num(oldList.get(i).getNumber());
                isIn = true;
            }
        }
        if (isIn) {
            barList.add(bean);
            showRightDialog(bean.getItem_name());
        } else {
            showNotDialog(bean.getItem_name());
        }

    }


    //扫码成功弹框
    private void showRightDialog(String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("扫描成功");
        builder.setMessage(result + "\n已加入扫描记录");
        builder.setCancelable(false);
        builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCaptureHelper.restartPreviewAndDecode();
            }
        });
        builder.create().show();

    }


    //扫码失败弹框
    private void showNotDialog(String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("扫描失败");
        builder.setMessage(result + "\n该商品不在此单内");
        builder.setCancelable(false);
        builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCaptureHelper.restartPreviewAndDecode();
            }
        });
        builder.create().show();

    }


    @OnClick({R.id.ivLeft, R.id.ivFlash, R.id.ivRight})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivFlash:
                clickFlash(v);
                break;
            case R.id.ivRight:
                //打开扫码记录界面
                startScanResultFragment(oldList, barList,type);
                break;
        }
    }


    //开启关闭手电筒
    private void clickFlash(View v) {
        if (v.isSelected()) {
            offFlash();
            v.setSelected(false);
        } else {
            openFlash();
            v.setSelected(true);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mCaptureHelper.onResume();
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


/*    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mCaptureHelper.onTouchEvent(event);
        return super.onTouchEvent(event);
    }*/


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
    public ScanPresenter createPresenter() {
        return new ScanPresenter(getApp());
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(e.getMessage());
        builder.setCancelable(false);
        builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCaptureHelper.restartPreviewAndDecode();
            }
        });
        builder.create().show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        switch (event.getMsg()) {
            case "scan_finish":
                finish();
                break;
        }
    }


}
