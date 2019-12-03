package com.akan.wms.mvp.fragment.scan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
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
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.ScanPresenter;
import com.akan.wms.mvp.view.ISacnView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.keyback.FragmentBackHandler;
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

public class ScanInBuyFragment extends BaseFragment<ISacnView, ScanPresenter> implements ISacnView, OnCaptureCallback, FragmentBackHandler {
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
    private List<ScanInfoBean> oldList;
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

    public static ScanInBuyFragment newInstance(List<ScanInfoBean> scanInfoList, String type) {
        Bundle args = new Bundle();
        ScanInBuyFragment fragment = new ScanInBuyFragment();
        fragment.oldList = scanInfoList;
        fragment.type = type;
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
        if (oldList.get(0).getBarList() != null) {
            barList.addAll(oldList.get(0).getBarList());
            ivRight.setText("记录（" + barList.size() + "）");
        }

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }


    // 扫码结果
    @Override
    public boolean onResultCallback(String result) {
        mCode = result;
        queryBar(result);
        return true;
    }

    //查询条码
    private void queryBar(String barCode) {
        map.clear();
        map.put("bar", barCode);
        getPresenter().queryBar(userBean.getStaff_token(), map);

    }

    //查询条码返回
    @Override
    public void OnQueryBar(BarBean data) {
        ResultProcess(data);
    }

    //扫码结果处理
    private void ResultProcess(BarBean bean) {
        boolean isIn = false;
        for (int i = 0; i < oldList.size(); i++) {
            if (oldList.get(i).getItem_id().equals(bean.getItem_code())) {

                int qty = bean.getQty();//条码里的数量
                ScanInfoBean scanInfoBean = oldList.get(i);
                switch (type) {
                    case "in_buy_point"://采购入库(点收)
                    case "pro_return_point"://生产退料(点收)
                    case "pro_return_in"://生产退料(入库)
                    case "pro_receive_point"://生产领料(点收)
                    case "pro_receive_out"://生产领料(出库)
                    case "out_buy_add"://采购退货(新增)
                    case "out_buy_detail"://采购退货(核定)
                    case "out_sale_add"://销售出库(新增)
                    case "in_transfer_add"://调拨入库(新增)
                    case "out_transfer_add"://调拨出库(新增)
                        int number1 = scanInfoBean.getArrive_qty();
                        if (number1 >= scanInfoBean.getSend_qty()) {
                            showMoreDialog(scanInfoBean.getItem_name());
                            return;
                        }
                        scanInfoBean.setBar_code(bean.getBar_code());
                        scanInfoBean.setArrive_qty(number1 + qty);
                        bean.setOld_num(scanInfoBean.getSend_qty());
                        isIn = true;
                        break;
                    case "in_buy_agree"://采购入库(同意入库)
                        int number2 = scanInfoBean.getIn_qty();
                        if (number2 >= scanInfoBean.getArrive_qty()) {
                            showMoreDialog(scanInfoBean.getItem_name());
                            return;
                        }

                        scanInfoBean.setBar_code(bean.getBar_code());
                        scanInfoBean.setIn_qty(number2 + qty);
                        bean.setOld_num(scanInfoBean.getArrive_qty());
                        isIn = true;
                        break;
                    case "finish_check"://成品入库(审核)
                    case "finish_add"://成品入库(添加)
                        int number3 = scanInfoBean.getArrive_qty();
                        if (number3 >= scanInfoBean.getSend_qty()) {
                            showMoreDialog(scanInfoBean.getItem_name());
                            return;
                        }
                        scanInfoBean.setBar_code(bean.getBar_code());
                        scanInfoBean.setArrive_qty(number3 + qty);
                        bean.setOld_num(scanInfoBean.getSend_qty());
                        isIn = true;
                        break;
                    case "check_add":
                    case "transfer_add":
                        int number4 = scanInfoBean.getArrive_qty();
                        scanInfoBean.setBar_code(bean.getBar_code());
                        scanInfoBean.setArrive_qty(number4 + qty);
                        isIn = true;
                        break;

                }

            }
        }
        if (isIn) {
            barList.add(bean);
            ivRight.setText("记录（" + barList.size() + "）");
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

    //扫码数量限制
    private void showMoreDialog(String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("扫码结果");
        builder.setMessage(result + "\n扫码数量大于申请数量");
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
                if (barList.size() > 0) {
                    showCloseDialog();
                } else {
                    finish();
                }
                break;
            case R.id.ivFlash:
                clickFlash(v);
                break;
            case R.id.ivRight:
                //打开扫码记录界面
                startScanInBUyResultFragment(oldList, barList, type);
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
        ivRight.setText("记录（" + barList.size() + "）");
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

    @Override
    public boolean onBackPressed() {
        if (barList.size() > 0) {
            showCloseDialog();
        } else {
            finish();
        }
        return true;
    }

    //操作确认弹框
    private void showCloseDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.point);
        builder.setMessage(R.string.scan_close_sure);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();

    }
}
