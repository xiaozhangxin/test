package com.ak.pt.mvp.fragment.ble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.FirstEvent;
import com.ak.pt.bean.NumOldBean;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.mvp.activity.ContentActivity;
import com.ak.pt.mvp.adapter.NumOldAdapter;
import com.ak.pt.util.BeepUtils;
import com.ak.pt.util.ToastUtil;
import com.bumptech.glide.Glide;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ak.pt.Constants.PT_RESULT;

/**
 * Created by admin on 2019/6/11.
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class PressureOldActivity extends AppCompatActivity {

    public static final String KEY_DATA = "key_data";
    public static final String PT_BEAN = "ptBean";
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
//    @BindView(R.id.data)
//    TextView data;
    @BindView(R.id.fashinon)
    ImageView fashinon;
    @BindView(R.id.tvPV)
    TextView tvPV;
    @BindView(R.id.tvNowTime)
    TextView tvNowTime;
    @BindView(R.id.bt_bg)
    ImageView btBg;
    @BindView(R.id.tvStartPt)
    TextView tvStartPt;
    @BindView(R.id.tvNowPt)
    TextView tvNowPt;
    @BindView(R.id.tvEndPt)
    TextView tvEndPt;
    @BindView(R.id.llData)
    LinearLayout llData;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tvStartOrEnd)
    ImageView tvStartOrEnd;
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.tvPower)
    TextView tvPower;
    @BindView(R.id.tvKeepTime)
    TextView tvKeepTime;
    @BindView(R.id.tvMode)
    TextView tvMode;
    private NumOldAdapter numAdapter;
    private List<NumOldBean> modeList;
    private boolean ptState = false;

    private BleDevice bleDevice;
    private BluetoothGattCharacteristic characteristic;
    private PressurePageBean ptBean;
    String ptValue = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure_begin_old);

        ButterKnife.bind(this);
        initView();
        initData();
    }


    //初始化蓝牙
    private void initData() {
        bleDevice = getIntent().getParcelableExtra(KEY_DATA);
        ptBean = (PressurePageBean) getIntent().getSerializableExtra(PT_BEAN);
        if (bleDevice == null)
            finish();
        List<BluetoothGattService> servers = BleManager.getInstance().getBluetoothGattServices(bleDevice);
        for (int i = 0; i < servers.size(); i++) {
            if (UUIDManager.SERVICE_UUID.equals(servers.get(i).getUuid().toString())) {
                List<BluetoothGattCharacteristic> characteristics = servers.get(i).getCharacteristics();
                for (int j = 0; j < characteristics.size(); j++) {
                    if (UUIDManager.NOTIFY_UUID.equals(characteristics.get(j).getUuid().toString())) {
                        characteristic = characteristics.get(j);
                        openNotify();
                    }
                }
            }

        }
    }

    //初始化界面
    private void initView() {
        Glide.with(this).load(R.drawable.old_bg).asGif().into(btBg);
        modeList = new ArrayList<>();
        modeList.add(new NumOldBean("0.6"));
        modeList.add(new NumOldBean("0.8"));
        modeList.add(new NumOldBean("1.0"));
        modeList.add(new NumOldBean("1.2"));

        recycleView.setLayoutManager(new GridLayoutManager(this, 4));
        numAdapter = new NumOldAdapter(this, modeList);
        recycleView.setAdapter(numAdapter);
        numAdapter.setOnItemClickListener(position -> {

            if (ptState) {
                ToastUtil.showToast(getApplicationContext(), "正在测试中");
                return;
            }
            switch (position) {
                case 0:
                    BeepUtils.chooseSixOld(PressureOldActivity.this);
                    break;
                case 1:
                    BeepUtils.chooseEightOld(PressureOldActivity.this);
                    break;
                case 2:
                    BeepUtils.chooseTenOld(PressureOldActivity.this);
                    break;
                case 3:
                    BeepUtils.chooseTwelveOld(PressureOldActivity.this);
                    break;
            }
            chooseMode(position);
        });


    }


    //选择压力值模式
    private void chooseMode(int position) {
        List<NumOldBean> allData = numAdapter.getAllData();
        for (int i = 0; i < allData.size(); i++) {
            if (position == i) {
                allData.get(i).setCheck(true);
            } else {
                allData.get(i).setCheck(false);
            }
        }
        numAdapter.notifyDataSetChanged();
    }


    //实时获取试压仪反馈的数据
    private void notifyText() {
        if (characteristic != null) {
            String s = HexUtil.formatHexString(characteristic.getValue());
//            data.setText(s);
            ptValue = s;
            if ("575047030100".equals(s)) {
                bleWrite("4154400100");
            }
            if (s.contains("5750470201")) {
                switch (s.substring(10, 12)) {
                    case "01"://工况准备就绪，开始测试
                        BeepUtils.startPt(PressureOldActivity.this);
                        ptBean.setPressure_time(getSystemTime());//试压开始时间
                        break;
                    case "02"://请加压
                        BeepUtils.addpt(PressureOldActivity.this);
                        break;
                    case "03"://已超压
                        BeepUtils.ptOver(PressureOldActivity.this);
                        break;
                    case "04"://低电提示音
                        BeepUtils.moPower(PressureOldActivity.this);
                        break;
                }
            }
            if (s.length() >= 36) {
                String power = s.substring(13, 14);//电池电量
                int i = Integer.parseInt(power) / 4 * 100;
                //tvPower.setText("电池电量:" + i + "%");

                String startPt = pressureChange(s.substring(14, 18));//初始压力
                tvStartPt.setText(startPt);
                String endPt = pressureChange(s.substring(18, 22));//结束压力
                tvEndPt.setText(endPt);
                String nowPt = pressureChange(s.substring(22, 26));//当前MPa值
                tvNowPt.setText(nowPt);
                tvPV.setText(nowPt);

                //保压时间
                String bM = timeChange(s.substring(26, 28));//分钟
                String bS = timeChange(s.substring(28, 30));//秒
                //tvKeepTime.setText("保压时间:" + bM + ":" + bS);

                //倒计时时间
                String mm = timeChange(s.substring(30, 32));//分钟
                String ss = timeChange(s.substring(32, 34));//秒
                tvNowTime.setText("倒计时 " + mm + ":" + ss);

                //多少公斤模式
                String mode = s.substring(34, 36);
                String mMode = Integer.valueOf(mode, 16).toString();
                //tvMode.setText("选择模式:" + mMode);

                String state = s.substring(10, 12);//测试状态
                switch (state) {
                    case "00"://没有进行测试
                        ptState = false;
                        // tvState.setText("试压仪状态:没有测试");
                        break;
                    case "01"://测试进行中
                        ptState = true;
                        switch (Integer.parseInt(mMode)) {
                            case 1:
                                chooseMode(1);
                                break;
                            case 2:
                                chooseMode(2);
                                break;
                            case 3:
                                chooseMode(3);
                                break;
                            case 4:
                                chooseMode(0);
                                break;
                        }

                        //  tvState.setText("试压仪状态:测试进行中");
                        startPressure();
                        break;
                    case "02"://不合格
                        BeepUtils.fail(PressureOldActivity.this);
                        ptBean.setPressure_complete_time(getSystemTime());
                        ptBean.setPressure_start_value(startPt);
                        ptBean.setPress_time("30");//旧试压仪默认30分钟
                        ptBean.setPressure_end_value(endPt);
                        ptBean.setPressure_result("否");
                        switch (mMode) {
                            case "1":
                                ptBean.setPressure_pressure("8");
                                break;
                            case "2":
                                ptBean.setPressure_pressure("10");
                                break;
                            case "3":
                                ptBean.setPressure_pressure("12");
                                break;
                            case "4":
                                ptBean.setPressure_pressure("6");
                                break;
                        }
                        startPressureResult(s);
                        endPressure();
                        finish();
                        break;
                    case "03"://合格
                        BeepUtils.success(PressureOldActivity.this);
                        ptBean.setPressure_complete_time(getSystemTime());
                        ptBean.setPress_time("30");//旧试压仪默认30分钟
                        ptBean.setPressure_start_value(startPt);
                        ptBean.setPressure_end_value(endPt);
                        ptBean.setPressure_result("是");
                        switch (mMode) {
                            case "1":
                                ptBean.setPressure_pressure("8");
                                break;
                            case "2":
                                ptBean.setPressure_pressure("10");
                                break;
                            case "3":
                                ptBean.setPressure_pressure("12");
                                break;
                            case "4":
                                ptBean.setPressure_pressure("6");
                                break;
                        }
                        startPressureResult(s);
                        endPressure();
                        finish();
                        break;
                }


            }
        }
    }

    //格式化时间
    private String timeChange(String time) {
        String mTime = Integer.valueOf(time, 16).toString();
        if (mTime.length() < 2) {
            mTime = "0" + mTime;
        }
        return mTime;
    }

    //格式化压力值
    private String pressureChange(String mPt) {
        Integer PtInt = Integer.valueOf(mPt, 16);
        float ptFloat = (float) PtInt / 100;
        DecimalFormat df = new DecimalFormat("00.00");
        return df.format(ptFloat);
    }

    //打开参数上传
    protected void startPressureResult(String s) {
        EventBus.getDefault().post(new FirstEvent("close_ble_list"));
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra(Constants.KEY_FRAGMENT, PT_RESULT);
        intent.putExtra(Constants.DETAIL_BEAN, ptBean);
        intent.putExtra(Constants.DETAIL_ID, s);
        startActivity(intent);
    }


    @OnClick({R.id.tvStartOrEnd, R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
               // closePtView();
                finish();
                break;
            case R.id.tvStartOrEnd:
                if (ptState) {//试压是否开始
                    isEndPting();
                } else {
                    List<NumOldBean> allData = numAdapter.getAllData();
                    boolean mCheck = false;//是否选择了压力值模式
                    int mKg = 0;//当前选择第几组
                    for (int i = 0; i < allData.size(); i++) {
                        if (allData.get(i).isCheck()) {
                            mCheck = true;
                            mKg=i;
                        }
                    }

                    if (mCheck) {
                        ptState = true;
                        switch (mKg) {
                            case 0:
                                bleWrite("4154040101");
                                startPressure();
                                break;
                            case 1:
                                bleWrite("4154010101");
                                startPressure();
                                break;
                            case 2:
                                bleWrite("4154020101");
                                startPressure();
                                break;
                            case 3:
                                bleWrite("4154030101");
                                startPressure();
                                break;

                        }

                    } else {
                        BeepUtils.selectKg(PressureOldActivity.this);
                    }

                }
                break;
        }
    }

    //开始试压
    private void startPressure() {
        Glide.with(this).load(R.drawable.pt_end).into(tvStartOrEnd);
        try {
            fashinon.setImageResource(R.drawable.water_animlist);
            AnimationDrawable animationDrawable = (AnimationDrawable) fashinon.getDrawable();
            animationDrawable.start();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    //结束试压
    private void endPressure() {
        bleWrite("4154050101");
        ToastUtil.showToast(getApplicationContext(), "结束测试!");
        Glide.with(this).load(R.drawable.pt_start).into(tvStartOrEnd);
        try {
            fashinon.setImageResource(R.drawable.water_animlist);
            AnimationDrawable animationDrawable = (AnimationDrawable) fashinon.getDrawable();
            animationDrawable.stop();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }

    }


    //是否结束试压
    private void isEndPting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.is_end_pting);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                endPressure();
                ptState = false;

            }
        });
        builder.show();
    }


/*    //返回键拦截
    @Override
    public void onBackPressed() {
        closePtView();
    }*/

    //判断是否正在试压
    private void closePtView() {
        if (ptState) {
            isClosePressureingActivity();
        } else {
            finish();
        }
    }

    //试压中是否关闭界面
    private void isClosePressureingActivity() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.is_close_pting_view);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }

    //发送数据
    private void bleWrite(String hex) {
        BleManager.getInstance().write(
                bleDevice,
                UUIDManager.SERVICE_UUID,
                UUIDManager.WRITE_UUID,
                HexUtil.hexStringToBytes(hex),
                new BleWriteCallback() {
                    @Override
                    public void onWriteSuccess(final int current, final int total, final byte[] justWrite) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                    }

                    @Override
                    public void onWriteFailure(final BleException exception) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
                    }
                });
    }

    //随时同步参数
    private void openNotify() {
        BleManager.getInstance().notify(bleDevice, UUIDManager.SERVICE_UUID, UUIDManager.NOTIFY_UUID, new BleNotifyCallback() {
            @Override
            public void onNotifySuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //addText(dataText, "notify success");
                    }
                });
            }

            @Override
            public void onNotifyFailure(final BleException exception) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(exception.toString());
                    }
                });
            }

            @Override
            public void onCharacteristicChanged(byte[] data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notifyText();
                    }

                });
            }
        });
    }


    //获取当前时间
    private String getSystemTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    //显示toast
    private void showToast(String content) {
        ToastUtil.showToast(this.getApplicationContext(), content);
    }

    // 重写activity的onDestroy（）方法，停止该页面的glide的加载请求


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.with(getApplicationContext()).pauseRequests();
        BleManager.getInstance().disconnectAllDevice();
    }
}
