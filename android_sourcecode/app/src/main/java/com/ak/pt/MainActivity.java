package com.ak.pt;import android.Manifest;import android.content.Intent;import android.content.IntentFilter;import android.content.pm.PackageManager;import android.os.Build;import android.os.Bundle;import androidx.fragment.app.Fragment;import androidx.fragment.app.FragmentTransaction;import androidx.core.content.ContextCompat;import com.ak.pt.bean.FirstEvent;import com.ak.pt.mvp.activity.ContentActivity;import com.ak.pt.mvp.base.PureActivity;import com.ak.pt.mvp.fragment.LoginActivity;import com.ak.pt.mvp.fragment.home.HomeFragment;import com.ak.pt.util.ToastUtil;import org.greenrobot.eventbus.EventBus;import org.greenrobot.eventbus.Subscribe;import org.greenrobot.eventbus.ThreadMode;import butterknife.ButterKnife;import static com.ak.pt.mvp.fragment.adaily.SignFragment.REQUEST_CARERAM;public class MainActivity extends PureActivity {    HomeFragment homeFragment;    private boolean isExit;    public static boolean isForeground = false;    //for receive customer msg from jpush server    public static final String MESSAGE_RECEIVED_ACTION = "com.ak.pt.MESSAGE_RECEIVED_ACTION";    public static final String KEY_TITLE = "title";    public static final String KEY_MESSAGE = "message";    public static final String KEY_EXTRAS = "extras";    private IntentFilter intentFilter;   // private MyInternetResiver internetResiver;    public MainActivity() {    }    @Override    public int getRootViewId() {        return R.layout.activity_main;    }    @Override    public void initUI() {        showHomeFragment();        isHavePermission();    }    //请求相机 定位 文件读写权限    public void isHavePermission() {        if (Build.VERSION.SDK_INT >= 23) {            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||                    ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||                    ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||                    ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED||                    ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {                requestPermissions(new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE,                        android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,                        android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CARERAM);                return;            }        }    }    //首页    public void showHomeFragment() {        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();        if (homeFragment == null) {            homeFragment = HomeFragment.newInstance();            fragmentTransaction.add(R.id.fragmentContent, homeFragment);        }        commitShowFragment(fragmentTransaction, homeFragment);    }    public void commitShowFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {        fragmentTransaction.show(fragment);        fragmentTransaction.commit();    }    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        // TODO: add setContentView(...) invocation        EventBus.getDefault().register(this);        ButterKnife.bind(this);        //intentFilter = new IntentFilter();        //intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");        //internetResiver = new MyInternetResiver();       // registerReceiver(internetResiver, intentFilter);    }    @Override    protected void onDestroy() {        super.onDestroy();        //unregisterReceiver(internetResiver);        EventBus.getDefault().unregister(this);    }    private int time = 0;    @Subscribe(threadMode = ThreadMode.MAIN)    public void onEventMainThread(FirstEvent event) {        switch (event.getMsg()) {            case "exit":                finish();                break;            case "expired":                if (time <= 0) {                    time++;                    EventBus.getDefault().post(new FirstEvent("expired_two"));                    Intent intent = new Intent(this, LoginActivity.class);//                    intent.putExtra(Constants.KEY_FRAGMENT, LOGIN_FRAGMENT);                    startActivity(intent);                    finish();                }                break;        }    }    @Override    public void onBackPressed() {        //由于推送服务会造成toast关闭不掉，所以去掉再按一次退出        if (!isExit) {            ToastUtil.showToast(context.getApplicationContext(), R.string.press_again_to_exit);            isExit = true;        } else {            super.onBackPressed();        }    }/*    class MyInternetResiver extends BroadcastReceiver {        @Override        public void onReceive(Context context, Intent intent) {            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();            if (networkInfo != null && networkInfo.isAvailable()) {                EventBus.getDefault().post(new FirstEvent("isNetWork"));            } else {                ToastUtil.showToast(getApplicationContext(), "当前无网络连接");            }        }    }*/}