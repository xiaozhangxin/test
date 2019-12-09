package com.ak.pt.mvp.fragment.ble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.FirstEvent;
import com.ak.pt.bean.ModuleListBean;
import com.ak.pt.bean.PressureDropBean;
import com.ak.pt.mvp.fragment.comm.Observer;
import com.ak.pt.mvp.fragment.comm.ObserverManager;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.mvp.activity.ContentActivity;
import com.ak.pt.mvp.adapter.NumAdapter;
import com.ak.pt.util.BeepUtils;
import com.ak.pt.util.ToastUtil;
import com.bumptech.glide.Glide;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

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
import static com.ak.pt.Constants.PT_SETTING;

/**
 * Created by admin on 2019/1/14.
 * 新试压仪
 */
public class PressureNewActivity extends AppCompatActivity implements Observer {
    @BindView(R.id.fashinon)
    ImageView fashinon;
    @BindView(R.id.tvPV)
    TextView tvPV;
    @BindView(R.id.tvPVTime)
    TextView tvPVTime;
    @BindView(R.id.tvNowTime)
    TextView tvNowTime;
    @BindView(R.id.tvStartPt)
    TextView tvStartPt;
    @BindView(R.id.tvNowPt)
    TextView tvNowPt;
    @BindView(R.id.tvEndPt)
    TextView tvEndPt;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.tvStartOrEnd)
    ImageView tvStartOrEnd;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llTop)
    LinearLayout llTop;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;

    private BleDevice bleDevice;
    private BluetoothGattCharacteristic characteristic;
    private NumAdapter numAdapter;
    private PressurePageBean ptBean;
    private List<PressureDropBean> modeList;
    private boolean ptState = false;//是否正在进行试压
    private String ptValue = "";//试压仪返回的参数
    private String times = "0";//当前试压的阶段
    boolean isFirst = false;//是否是第一次进入试压


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure_begin_new);
        ButterKnife.bind(this);
        initView();
        initBle();
        ObserverManager.getInstance().addObserver(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.with(getApplicationContext()).pauseRequests();
        BleManager.getInstance().clearCharacterCallback(bleDevice);
        ObserverManager.getInstance().deleteObserver(this);
    }

    @Override
    public void disConnected(BleDevice device) {
        if (device != null && bleDevice != null && device.getKey().equals(bleDevice.getKey())) {
            finish();
        }
    }

    //初始化蓝牙
    private void initBle() {
        bleDevice = getIntent().getParcelableExtra(Constants.KEY_DATA);
        ptBean = (PressurePageBean) getIntent().getSerializableExtra(Constants.PT_BEAN);
        //服务器配置的试压参数
        modeList = (List<PressureDropBean>) getIntent().getSerializableExtra(Constants.PT_MODE);
        numAdapter.addAll(modeList);
        numAdapter.notifyDataSetChanged();
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

    //随时同步参数
    private void openNotify() {
        BleManager.getInstance().notify(bleDevice, UUIDManager.SERVICE_UUID, UUIDManager.NOTIFY_UUID, new BleNotifyCallback() {
            @Override
            public void onNotifySuccess() {
            }

            @Override
            public void onNotifyFailure(final BleException exception) {
            }

            @Override
            public void onCharacteristicChanged(byte[] data) {
                runOnUiThread(() -> notifyText());
            }
        });
    }

    //特征值改变
    private void notifyText() {
        if (characteristic == null) {
            return;
        }
        String hexString = HexUtil.formatHexString(characteristic.getValue());
//        data.setText(hexString);
        ptValue = hexString;
        if ("575047040101".equals(hexString)) {
            bleWrite("4154600101");
        }

        if (hexString.length() >= 38) {
            Log.e("#### hexString", hexString);
            String ptTime = Integer.valueOf(hexString.substring(17, 18), 16).toString();//试压阶段
            tvPVTime.setText("第" + ptTime + "次");
            Log.e("#### 次数", ptTime);
            Integer nowP = Integer.valueOf(hexString.substring(18, 22), 16);//实时压力值
            Integer startP = Integer.valueOf(hexString.substring(22, 26), 16);//每阶段开始压力值
            Integer endP = Integer.valueOf(hexString.substring(26, 30), 16);//每阶段结束压力值
            float nowS = (float) nowP / 100;
            float startS = (float) startP / 100;
            float endS = (float) endP / 100;
            DecimalFormat df = new DecimalFormat("00.00");//格式化小数，不足的补0
            String nowSize = df.format(nowS);
            String startSize = df.format(startS);
            String endSize = df.format(endS);
            tvPV.setText(nowSize);//当前压力值
            tvNowPt.setText(nowSize);//当前压力值
            tvStartPt.setText(startSize);//开始压力值
            tvEndPt.setText(endSize);//结束压力值
            Log.e("#### 当前压力值", nowSize);
            Log.e("#### 开始压力值", startSize);
            Log.e("#### 结束压力值", endSize);
            Log.e("#### 压降", (endS - startS) + "");
            //tvStartTime.setText(s.substring(30, 32) + ":" + s.substring(32, 34));//开始时间
            //实时时间
            String ss = Integer.valueOf(hexString.substring(36, 38), 16).toString();
            String mm = Integer.valueOf(hexString.substring(34, 36), 16).toString();
            if (ss.length() < 2) {
                ss = "0" + ss;
            }
            if (mm.length() < 2) {
                mm = "0" + mm;
            }
            tvNowTime.setText(mm + ":" + ss);

            //试压状态 0 没有试压 1试压中 2试压结束不合格 3试压结束合格
            String state = hexString.substring(15, 16);
            Log.e("#### 试压状态", state);
            switch (state) {
                case "0":
                    ptState = false;
                    if (!isFirst) {
                        tvStartOrEnd.setImageResource(R.drawable.pt_start);
                    }
                    break;
                case "1":
                    ptState = true;
                    String mTimes = hexString.substring(17, 18);//试压阶段
                    showTimesVoice(mTimes);
                    if (!isFirst) {
                        tvStartOrEnd.setImageResource(R.drawable.pt_end);
                        startAnim(hexString.substring(13, 14));
                    }
                    break;
                case "2":
                    BeepUtils.fail(PressureNewActivity.this);
                    toSavePressureData(startSize, nowSize);
                    ptBean.setPressure_result("不合格");
                    startPressureResult(hexString);
                    endPressure();
                    finish();
                    break;
                case "3"://试压合格
                    BeepUtils.success(PressureNewActivity.this);
                    toSavePressureData(startSize, nowSize);
                    ptBean.setPressure_result("合格");
                    startPressureResult(hexString);
                    endPressure();
                    finish();
                    break;
            }
            isFirst = false;
        }
        switch (hexString) {
            case "575047020100"://收到此指令可以进行试压参数同步
                bleWrite(getDropData());
                break;
        }

    }

    //试压结束 保存试压信息
    private void toSavePressureData(String startSize, String nowSize) {
        //开始压力值
        if (TextUtils.isEmpty(ptBean.getPressure_start_value())) {
            ptBean.setPressure_start_value(startSize);
        }
        //试压开始时间
        if (TextUtils.isEmpty(ptBean.getPressure_time())) {
            SharedPreferences ptDtaa = getSharedPreferences("ptData", MODE_PRIVATE);
            ptBean.setPressure_time(ptDtaa.getString("startTime", ""));
        }
        //试压选择的公斤数
        if (TextUtils.isEmpty(ptBean.getPressure_pressure())) {
            SharedPreferences ptDtaa = getSharedPreferences("ptData", MODE_PRIVATE);
            ptBean.setPressure_pressure(ptDtaa.getString("ptKg", ""));
        }

        //第二阶段试压时间
        if (TextUtils.isEmpty(ptBean.getPress_time())){
            SharedPreferences ptDtaa = getSharedPreferences("ptData", MODE_PRIVATE);
            ptBean.setPress_time(ptDtaa.getString("ptTime", ""));
        }
        ptBean.setPressure_complete_time(getSystemTime());//试压完成时间
        ptBean.setPressure_end_value(nowSize);//结束压力值
    }

   private String moduleTime ="";

    //初始化界面
    private void initView() {
        modeList = new ArrayList<>();
        recycleView.setLayoutManager(new GridLayoutManager(this, 3));
        numAdapter = new NumAdapter(this, modeList, "1");
        recycleView.setAdapter(numAdapter);
        numAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String kgAll = numAdapter.getItem(position).getDrop_num();
                chooseModeVoice(Integer.valueOf(kgAll));
                List<PressureDropBean> allData = numAdapter.getAllData();
                //保存第二阶段试压时间
                List<ModuleListBean> moduleList = numAdapter.getItem(position).getModuleList();
                if (moduleList.size() > 1) {
                     moduleTime = numAdapter.getItem(position).getModuleList().get(1).getModule_time();//第二阶段试压时间
                    ptBean.setPress_time(moduleTime);
                }
                for (int i = 0; i < allData.size(); i++) {
                    if (position == i) {
                        allData.get(i).setCheck(true);
                    } else {
                        allData.get(i).setCheck(false);
                    }
                }
                numAdapter.notifyDataSetChanged();
            }
        });
    }


    //每次设置完参数进行同步
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("ptDropType", MODE_PRIVATE);
        String type = sp.getString("type", "");
        //type back使用后台配置的参数 local使用本地配置的参数
        switch (type) {
            case "back":
                if (numAdapter != null) {
                    numAdapter.clear();
                    numAdapter.addAll(modeList);
                    numAdapter.notifyDataSetChanged();
                }
                break;
            case "local":
                initLocalList();
                break;

        }
        bleWrite(getDropData());


    }

    //获取本地保存的试压模式参数
    private void initLocalList() {
        SharedPreferences sp = getSharedPreferences("ptDrop", MODE_PRIVATE);
        String list = sp.getString("dropList", "");
        if (!list.equals("") && list.length() > 0) {
            numAdapter.clear();
            Gson gson = new Gson();
            JsonArray array = new JsonParser().parse(list).getAsJsonArray();
            for (JsonElement elem : array) {
                PressureDropBean dropBean = gson.fromJson(elem, PressureDropBean.class);
                numAdapter.add(dropBean);
            }
            numAdapter.notifyDataSetChanged();
        }

    }


    @OnClick({R.id.tvRight, R.id.tvStartOrEnd, R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                //closePtView();
                break;
            case R.id.tvRight:
                if (ptState) {
                    ToastUtil.showToast(getApplicationContext(), "测试进行中");
                } else {
                    startPTSetting();
                }
                break;
            case R.id.tvStartOrEnd:
                if (ptState) {//试压是否开始
                    showEndPressureDialog();
                } else {
                    List<PressureDropBean> allData = numAdapter.getAllData();
                    boolean isKgSelect = false;
                    int kgMode = 0;
                    int kgInt = 0;
                    for (int i = 0; i < allData.size(); i++) {
                        boolean check = allData.get(i).isCheck();
                        if (check) {
                            isKgSelect = true;
                            kgMode = i;
                            kgInt = Integer.parseInt(allData.get(i).getDrop_num());
                        }
                    }
                    if (isKgSelect) {
                        switch (kgMode) {
                            case 0:
                                bleWrite("415430020201");
                                startPressure(kgInt);
                                break;
                            case 1:
                                bleWrite("415430020202");
                                startPressure(kgInt);
                                break;
                            case 2:
                                bleWrite("415430020203");
                                startPressure(kgInt);

                                break;
                        }
                        ptState = true;
                    } else {
                        BeepUtils.selectKg(PressureNewActivity.this);
                    }

                }

                break;
        }

    }

    //显示是否关闭试压弹窗
    private void showEndPressureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.sure_stop_pressure);
        builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> {
        });
        builder.setPositiveButton(getString(R.string.sure), (dialog, which) -> {
            endPressure();
            ptState = false;
        });
        builder.show();
    }


    //获取同步的参数
    private String getDropData() {
        StringBuilder builder = new StringBuilder();
        builder.append("41545036");
        StringBuilder builderTwo = new StringBuilder();
        List<PressureDropBean> allData = numAdapter.getAllData();
        if (allData.size() > 2) {
            for (int i = 0; i < 3; i++) {
                PressureDropBean pressureDropBean = allData.get(i);
                List<ModuleListBean> list = pressureDropBean.getModuleList();
                ModuleListBean moduleListBean = list.get(0);
                String kg = Integer.toHexString(Integer.parseInt(moduleListBean.getModule_num()));
                String time = Integer.toHexString(Integer.parseInt(moduleListBean.getModule_time()));
                if (kg.length() <= 1) {
                    kg = "0" + kg;
                }
                if (time.length() <= 1) {
                    time = "0" + time;
                }
                builder.append(kg + time);
                String hexString = Integer.toHexString(Integer.parseInt(pressureDropBean.getDrop_num()));
                if (hexString.length() <= 1) {
                    hexString = "0" + hexString;
                }
                builderTwo.append(hexString);
                for (int j = 0; j < list.size(); j++) {
                    ModuleListBean moduleListBeanChild = list.get(j);
                    String timeChild = Integer.toHexString(Integer.parseInt(moduleListBeanChild.getModule_time()));
                    String module_pressure = moduleListBeanChild.getModule_pressure();
                    int intValue = ((Number) (Float.parseFloat(module_pressure) * 10)).intValue();
                    String downChild = Integer.toHexString(intValue);
                    if (timeChild.length() <= 1) {
                        timeChild = "0" + timeChild;
                    }
                    if (downChild.length() <= 1) {
                        downChild = "0" + downChild;
                    }
                    if (0 == j) {//第一阶段不自动判断
                        builderTwo.append(timeChild + downChild + "00");
                    } else {//后面阶段自动判断
                        builderTwo.append(timeChild + downChild + "01");
                    }

                }
                builderTwo.append("000000000000000000");
            }
        }
        builder.append(builderTwo);
        String data = builder.toString();
        return data;
    }


    //试压中退出再次进入界面恢复已选择的模式
    private boolean isAnim = false;

    private void startAnim(String checKg) {
        if (!isAnim) {
            fashinon.setImageResource(R.drawable.water_animlist);
            AnimationDrawable animationDrawable1 = (AnimationDrawable) fashinon.getDrawable();
            animationDrawable1.start();
            switch (checKg) {
                case "1":
                    numAdapter.getAllData().get(0).setCheck(true);
                    numAdapter.notifyDataSetChanged();
                    break;
                case "2":
                    numAdapter.getAllData().get(1).setCheck(true);
                    numAdapter.notifyDataSetChanged();
                    break;
                case "3":
                    numAdapter.getAllData().get(2).setCheck(true);
                    numAdapter.notifyDataSetChanged();
                    break;
            }
            isAnim = true;
        }
    }


    //开始试压
    private void startPressure(int kgInt) {
        BeepUtils.startPt(PressureNewActivity.this);
        tvStartOrEnd.setImageResource(R.drawable.pt_end);
        ptBean.setPressure_time(getSystemTime());//试压开始时间
        ptBean.setPressure_pressure(kgInt + "");//试压压力值
        savePtData(kgInt + "");
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
        bleWrite("41544000");
        ToastUtil.showToast(getApplicationContext(), "结束测试");
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

    //保存试压开始时间和试压公斤数
    private void savePtData(String kgInt) {
        SharedPreferences ptData = getSharedPreferences("ptData", MODE_PRIVATE);
        SharedPreferences.Editor edit = ptData.edit();
        edit.putString("startTime", getSystemTime());
        edit.putString("ptKg", kgInt);
        edit.putString("ptTime", moduleTime);
        edit.commit();
    }


    //打开参数设置界面
    protected void startPTSetting() {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra(Constants.KEY_FRAGMENT, PT_SETTING);
        startActivity(intent);
    }


    //试压完成打开参数上传界面
    protected void startPressureResult(String s) {
        EventBus.getDefault().post(new FirstEvent("close_ble_list"));
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra(Constants.KEY_FRAGMENT, PT_RESULT);
        intent.putExtra(Constants.DETAIL_BEAN, ptBean);
        intent.putExtra(Constants.DETAIL_ID, s);
        startActivity(intent);
    }


    //获取系统当前时间
    private String getSystemTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }


    //写入数据
    private void bleWrite(String hex) {
        BleManager.getInstance().write(
                bleDevice,
                UUIDManager.SERVICE_UUID,
                UUIDManager.WRITE_UUID,
                HexUtil.hexStringToBytes(hex), new BleWriteCallback() {
                    @Override
                    public void onWriteSuccess(int current, int total, byte[] justWrite) {

                    }

                    @Override
                    public void onWriteFailure(BleException exception) {

                    }
                });
    }

    //试压进行阶段提示音
    private void showTimesVoice(String mTimes) {
        switch (mTimes) {
            case "1":
                if (!times.equals(mTimes)) {
                    BeepUtils.oneBegin(PressureNewActivity.this);
                    ptBean.setPressure_start_value(tvStartPt.getText().toString());
                }
                times = mTimes;
                break;
            case "2":
                ptBean.setPressure_start_value(tvStartPt.getText().toString());
                if (!times.equals(mTimes)) {
                    BeepUtils.oneOver(PressureNewActivity.this);
                }
                times = mTimes;
                break;
            case "3":
                if (!times.equals(mTimes)) {
                    BeepUtils.twoOver(PressureNewActivity.this);
                }
                times = mTimes;
                break;
            case "4":
                if (!times.equals(mTimes)) {
                    BeepUtils.threeOver(PressureNewActivity.this);
                }
                times = mTimes;
                break;

            case "5":
                if (!times.equals(mTimes)) {
                    BeepUtils.fourOver(PressureNewActivity.this);
                }
                times = mTimes;
                break;
        }
    }


    //选择多少公斤模式提示音
    private void chooseModeVoice(int kgInt) {
        switch (kgInt) {
            case 1:
                BeepUtils.chooseOne(PressureNewActivity.this);
                break;
            case 2:
                BeepUtils.chooseTwo(PressureNewActivity.this);
                break;
            case 3:
                BeepUtils.chooseThree(PressureNewActivity.this);
                break;
            case 4:
                BeepUtils.chooseFour(PressureNewActivity.this);
                break;
            case 5:
                BeepUtils.chooseFive(PressureNewActivity.this);
                break;
            case 6:
                BeepUtils.chooseSix(PressureNewActivity.this);
                break;
            case 7:
                BeepUtils.chooseSeven(PressureNewActivity.this);
                break;
            case 8:
                BeepUtils.chooseEight(PressureNewActivity.this);
                break;
            case 9:
                BeepUtils.chooseNine(PressureNewActivity.this);
                break;
            case 10:
                BeepUtils.chooseTen(PressureNewActivity.this);
                break;
            case 11:
                BeepUtils.chooseEleven(PressureNewActivity.this);
                break;
            case 12:
                BeepUtils.chooseTwelve(PressureNewActivity.this);
                break;
            case 13:
                BeepUtils.chooseThirteen(PressureNewActivity.this);
                break;
            case 14:
                BeepUtils.chooseFourteen(PressureNewActivity.this);
                break;
            case 15:
                BeepUtils.chooseFifteen(PressureNewActivity.this);
                break;
            case 16:
                BeepUtils.chooseSixteen(PressureNewActivity.this);
                break;
        }
    }


    /*
    @Override
    public void onBackPressed() {
        closePtView();
    }
*/


    //屏蔽返回键处理
    private void closePtView() {
        if (ptState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("测试进行中，是否确认关闭此界面?");
            builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> {
            });
            builder.setPositiveButton(getString(R.string.sure), (dialog, which) -> finish());
            builder.show();
        } else {
            finish();
        }
    }


}
