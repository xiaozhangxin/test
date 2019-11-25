package com.ak.pt.mvp.fragment.ble;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.FirstEvent;
import com.ak.pt.bean.PressureDropBean;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.DeviceAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.fragment.comm.ObserverManager;
import com.ak.pt.mvp.presenter.PressureDropPresenter;
import com.ak.pt.mvp.view.IPressureDropView;
import com.ak.pt.util.BeepUtils;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by admin on 2019/1/9.
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BleListFragment extends BaseFragment<IPressureDropView, PressureDropPresenter> implements IPressureDropView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;

    Unbinder unbinder;
    @BindView(R.id.list_device)
    ListView listDevice;
    @BindView(R.id.btn_scan)
    TextView btn_scan;
    private List<BluetoothDevice> list;
    private DeviceAdapter mDeviceAdapter;
    private ProgressDialog progressDialog;
    private static final int REQUEST_CODE_OPEN_GPS = 1;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 2;

    private PressurePageBean ptBean;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static BleListFragment newInstance(PressurePageBean ptBean) {
        Bundle args = new Bundle();
        BleListFragment fragment = new BleListFragment();
        fragment.ptBean = ptBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_ble_list;
    }


    @Override
    public void initUI() {
        tvTitle.setText(R.string.ble_list);
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle(getString(R.string.connecting));
        }
        mDeviceAdapter = new DeviceAdapter(context);
        mDeviceAdapter.setOnDeviceClickListener(new DeviceAdapter.OnDeviceClickListener() {
            @Override
            public void onConnect(final BleDevice bleDevice) {
                if (!BleManager.getInstance().isConnected(bleDevice)) {
                    BleManager.getInstance().cancelScan();
                    connect(bleDevice);
             /*       String name = bleDevice.getName();
                    if (!TextUtils.isEmpty(name)) {
                        String bleDeviceName = name.trim();
                        if ("保利管路安检仪".equals(bleDeviceName) || "保利试压仪".equals(bleDeviceName)) {
                            connect(bleDevice);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle(R.string.this_notpossible_ble);
                            builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    connect(bleDevice);
                                }
                            });
                            builder.show();
                        }

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle(R.string.this_notpossible_ble);
                        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                connect(bleDevice);
                            }
                        });
                        builder.show();
                    }*/

                } else {
                    showList(new String[]{"旧试压仪", "新试压仪"}, bleDevice);
               /*     String deviceName = bleDevice.getName();
                    if (!TextUtils.isEmpty(deviceName)) {
                        String name = deviceName.trim();
                        if ("保利试压仪".equals(name)) {
                            Intent intent = new Intent(getActivity(), PressureOldActivity.class);
                            intent.putExtra(Constants.KEY_DATA, bleDevice);
                            intent.putExtra(Constants.PT_BEAN, ptBean);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getActivity(), PressureeginActivity.class);
                            intent.putExtra(Constants.KEY_DATA, bleDevice);
                            intent.putExtra(Constants.PT_BEAN, ptBean);
                            intent.putExtra(Constants.PT_MODE, (Serializable) modeList);
                            startActivity(intent);
                        }
                    }
*/

                }
            }

            @Override
            public void onDisConnect(final BleDevice bleDevice) {
                if (BleManager.getInstance().isConnected(bleDevice)) {
                    BleManager.getInstance().disconnect(bleDevice);
                }
            }
     /*       @Override
            public void onDetail(BleDevice bleDevice) {
                if (BleManager.getInstance().isConnected(bleDevice)) {
                    if (BleManager.getInstance().isConnected(bleDevice)) {
                        Intent intent = new Intent(getActivity(), PressureeginActivity.class);
                        intent.putExtra(PressureeginActivity.KEY_DATA, bleDevice);
                        startActivity(intent);
                    }
                }
            }*/
        });

        listDevice.setAdapter(mDeviceAdapter);


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getPresenter().getPressureDropList(userBean.getStaff_token(), map);
    }

    private List<PressureDropBean> modeList = new ArrayList<>();

    @Override
    public void OnGetPressureDropList(List<PressureDropBean> data) {
        modeList = data;
    }


    private void checkPermissions() {
        BluetoothManager mBluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        if (mBluetoothManager != null) {
            BluetoothAdapter bluetoothAdapter = mBluetoothManager.getAdapter();
            if (!bluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(bluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
                return;
            }
        }
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
        List<String> permissionDeniedList = new ArrayList<>();
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted(permission);
            } else {
                permissionDeniedList.add(permission);
            }
        }
        if (!permissionDeniedList.isEmpty()) {
            String[] deniedPermissions = permissionDeniedList.toArray(new String[permissionDeniedList.size()]);
            ActivityCompat.requestPermissions(getActivity(), deniedPermissions, REQUEST_CODE_PERMISSION_LOCATION);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OPEN_GPS) {
            if (checkGPSIsOpen()) {
                startScan();
            }
        } else if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                ToastUtil.showToast(context.getApplicationContext(), getString(R.string.ble));
                startScan();
            } else if (resultCode == RESULT_CANCELED) {
                ToastUtil.showToast(context.getApplicationContext(), "蓝牙启动失败,请手动打开");
            }
        }
    }


    private void onPermissionGranted(String permission) {
        switch (permission) {
            case Manifest.permission.ACCESS_FINE_LOCATION:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkGPSIsOpen()) {
                    new AlertDialog.Builder(context)
                            .setTitle(R.string.notifyTitle)
                            .setMessage(R.string.gpsNotifyMsg)
                            .setNegativeButton(R.string.cancel,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    })
                            .setPositiveButton(R.string.setting,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                            startActivityForResult(intent, REQUEST_CODE_OPEN_GPS);
                                        }
                                    })

                            .setCancelable(false)
                            .show();
                } else {
                    startScan();
                }
                break;
        }
    }

    private boolean checkGPSIsOpen() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null)
            return false;
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    //连接蓝牙
    private void connect(final BleDevice bleDevice) {
        BleManager.getInstance().connect(bleDevice, new BleGattCallback() {
            @Override
            public void onStartConnect() {
                if (progressDialog != null) {
                    progressDialog.show();
                }
            }

            @Override
            public void onConnectFail(BleDevice bleDevice, BleException exception) {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
                btn_scan.setText(R.string.start_scan);
                ToastUtil.showToast(context.getApplicationContext(), getString(R.string.connect_fail));
            }

            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
                mDeviceAdapter.notifyDataSetChanged();
                BeepUtils.yilianjie(context.getApplicationContext());
                showList(new String[]{"旧试压仪", "新试压仪"}, bleDevice);
/*                String bleDeviceName = bleDevice.getName();
                if (!TextUtils.isEmpty(bleDeviceName)) {
                    String name = bleDeviceName.trim();
                    if ("保利试压仪".equals(name)) {
                        Intent intentOld = new Intent(getActivity(), PressureOldActivity.class);
                        intentOld.putExtra(Constants.KEY_DATA, bleDevice);
                        intentOld.putExtra(Constants.PT_BEAN, ptBean);
                        startActivity(intentOld);
                    } else {

                        Intent intent = new Intent(getActivity(), PressureeginActivity.class);
                        intent.putExtra(Constants.KEY_DATA, bleDevice);
                        intent.putExtra(Constants.PT_BEAN, ptBean);
                        intent.putExtra(Constants.PT_MODE, (Serializable) modeList);
                        startActivity(intent);
                    }
                }*/
            }

            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice bleDevice, BluetoothGatt gatt, int status) {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
                mDeviceAdapter.removeDevice(bleDevice);
                mDeviceAdapter.notifyDataSetChanged();
                if (alertDialogNew!=null){
                    alertDialogNew.dismiss();
                }
                if (isActiveDisConnected) {
                    //  ToastUtil.showToast(context.getApplicationContext(), getString(R.string.ble_disconnect));
                } else {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.ble_disconnect_already));
                    ObserverManager.getInstance().notifyObserver(bleDevice);

                }

            }
        });
    }


    private androidx.appcompat.app.AlertDialog alertDialogNew;

    public void showList(final String[] items, final BleDevice bleDevice) {
        androidx.appcompat.app.AlertDialog.Builder alertBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (items[i]) {
                    case "旧试压仪":
                        Intent intentOld = new Intent(getActivity(), PressureOldActivity.class);
                        intentOld.putExtra(Constants.KEY_DATA, bleDevice);
                        intentOld.putExtra(Constants.PT_BEAN, ptBean);
                        startActivity(intentOld);
                        break;
                    case "新试压仪":
                        Intent intent = new Intent(getActivity(), PressureeginActivity.class);
                        intent.putExtra(Constants.KEY_DATA, bleDevice);
                        intent.putExtra(Constants.PT_BEAN, ptBean);
                        intent.putExtra(Constants.PT_MODE, (Serializable) modeList);
                        startActivity(intent);
                        break;
                }

                alertDialogNew.dismiss();
            }


        });
        alertDialogNew = alertBuilder.create();
        alertDialogNew.show();
    }

    //开始扫描
    private void startScan() {

/*        UUID uuid = UUID.fromString(UUIDManager.SERVICE_UUID);

       UUID[] serviceUuids = new UUID[1];
       serviceUuids[0]=uuid;
        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
                .setServiceUuids(serviceUuids)    // 只扫描指定的服务的设备，可选
                .setScanTimeOut(10000)
                .build();
        BleManager.getInstance().initScanRule(scanRuleConfig);*/
        BleManager.getInstance().scan(new BleScanCallback() {
                    @Override
                    public void onScanStarted(boolean success) {
                        btn_scan.setText("停止扫描");
                        mDeviceAdapter.clearScanDevice();
                        mDeviceAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onLeScan(BleDevice bleDevice) {
                        super.onLeScan(bleDevice);
                    }

                    @Override
                    public void onScanning(BleDevice bleDevice) {
                        mDeviceAdapter.addDevice(bleDevice);
                        mDeviceAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onScanFinished(List<BleDevice> scanResultList) {
                        if (btn_scan != null) {
                            btn_scan.setText("开始扫描");
                        }
                    }
                });
    }


    @OnClick({R.id.ivLeft, R.id.btn_scan})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.btn_scan:
                String s = btn_scan.getText().toString();
                switch (s) {
                    case "开始扫描":
                        checkPermissions();
                        break;
                    case "停止扫描":
                        BleManager.getInstance().cancelScan();
                        break;
                }
                break;
        }
    }

    @Override
    public void onPause() {
        BleManager.getInstance().cancelScan();
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        switch (event.getMsg()) {
            case "close_ble_list"://数据上传关闭界面
                finish();
                break;
        }

    }

    @Override
    public PressureDropPresenter createPresenter() {
        return new PressureDropPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //断开所有设备
        BleManager.getInstance().disconnectAllDevice();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);

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

}
