package com.akan.qf.mvp.fragment.home;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.AppHomeMenuTreeBean;
import com.akan.qf.bean.AppVersionBean;
import com.akan.qf.bean.BookBean;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.FirstEventNew;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.StaffMessageBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.activity.ScanActivity;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.message.MessagePresenter;
import com.akan.qf.mvp.view.message.IMessageView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;
import static com.akan.qf.R.id.circleImageVIew;
import static com.akan.qf.R.id.ivCheckDelete;
import static com.akan.qf.R.id.tvCheckPersonName;
import static com.akan.qf.mvp.activity.ScanActivity.REQUEST_CAMERA_PERM;
import static com.akan.qf.mvp.fragment.adaily.SignFragment.REQUEST_CARERAM;

/**
 * Created by admin on 2018/6/28.
 */

public class HomeFragment extends BaseFragment<IMessageView, MessagePresenter> implements IMessageView {
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;
    @BindView(R.id.rbDJ)
    RadioButton rbDJ;
    @BindView(R.id.rbYT)
    RadioButton rbYT;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    private Map<String, String> versionMap = new HashMap<>();
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI() {
/*        SharedPreferences sp = context.getSharedPreferences("login", MODE_PRIVATE);
        String password = sp.getString("pwd", "");
        if ("a123456".equals(password) | "123456".equals(password)) {
            final CustomDialog.Builder builder = new CustomDialog.Builder(context);
            builder.setMessage(getString(R.string.please_change_pwd));
            builder.setPositiveButton("马上修改", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startChangePwdFragment();
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.onCreate().show();
        }*/
        ArrayList<BaseFragment> list = new ArrayList<>();
        list.add(new SalesmanFragment().newInstance());
        list.add(new CustomerFragment().newInstance());
        ViewAdapter viewAdapter = new ViewAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(viewAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (0 == position) {
                    rbDJ.setChecked(true);
                } else {
                    rbYT.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //版本更新
    private String versionName;
    private String server_address;
    private String isServer;
    private String webAddress;

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getNotReadMessageCount(userBean.getStaff_token(), map);
    }

    @Override
    public void onResume() {
        super.onResume();
        versionName = getVersionName();
        versionMap.put("version_type", "android");
        getPresenter().getAppVersionDetail(userBean.getStaff_token(), versionMap);
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
            case "refreshNews":
                userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
                map.put("staff_id", userBean.getStaff_id());
                getPresenter().getNotReadMessageCount(userBean.getStaff_token(), map);
                break;
        }


    }

    //----------------------------------------版本更新----------------------------------------------//
    /*
 * 获取当前程序的版本名
 */
    private String getVersionName() {
        //获取packagemanager的实例
        PackageManager packageManager = getActivity().getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }

    private final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 102;//内存权限

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate(String vesion, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("发现新版本V" + vesion + "!").
                setIcon(R.mipmap.ic_launcher).
                setMessage(content).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if ("0".equals(isServer)) {
                            if (!TextUtils.isEmpty(webAddress)) {
                                Uri uri = Uri.parse(webAddress);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        } else {
                            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    != PackageManager.PERMISSION_GRANTED) {
                                //申请WRITE_EXTERNAL_STORAGE权限
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                            } else {
                                loadNewVersionProgress();//下载最新的版本程序
                            }
                        }
                    }
                }).setNegativeButton("取消", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }


    /**
     * 下载新版本程序，需要子线程
     */
    private void loadNewVersionProgress() {
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(getActivity());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCanceledOnTouchOutside(false);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(server_address, pd);
                    sleep(2000);
                    installApk(file);
                    pd.dismiss();
                } catch (Exception e) {
                    //下载apk失败
                    ToastUtil.showToast(context.getApplicationContext(), "下载新版本失败");
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    public static File getFileFromServer(String uri, ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory(), time + "updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {

            return null;
        }
    }


    /**
     * 安装apk
     */
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    public void onGetNotReadMessageCount(String data) {
        EventBus.getDefault().post(new FirstEvent("message" + data));
    }

    @Override
    public void onDeleteStaffMessages(String data) {

    }

    @Override
    public void OnGetAppVersionDetail(AppVersionBean data) {
        server_address = data.getServer_address();
        isServer = data.getServer_update();
        webAddress = data.getDownload_address();
        if (!TextUtils.isEmpty(versionName)) {
            if (!versionName.equals(data.getVersion_no())) {
                if ("0".equals(data.getMust_update())) {
                } else {
                    showDialogUpdate(data.getVersion_no(), data.getUpdate_content());
                }
            } else {
            }
        }
    }

    @Override
    public void onGetAppHomeMenuTreeByStaff(List<AppHomeMenuTreeBean> data) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onGetStaffMessageList(List<StaffMessageBean> data) {

    }

    @Override
    public void onGetAddressBookList(List<BookBean> data, String total) {

    }


    class ViewAdapter extends FragmentPagerAdapter {
        private List<BaseFragment> datas;

        public ViewAdapter(FragmentManager fm, ArrayList<BaseFragment> list) {
            super(fm);
            datas = list;
        }


        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }


    }


    @Override
    public MessagePresenter createPresenter() {
        return new MessagePresenter(getApp());
    }


    @OnClick({R.id.rbDJ, R.id.rbYT, R.id.ivScan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbDJ:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rbYT:
                viewPager.setCurrentItem(1);
                break;
            case R.id.ivScan:
                isHavePermission();
                break;
        }
    }


    //-------------------------------------申请相机权限-------------------------------------------//

    public void isHavePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CARERAM);
                return;
            } else {
                Intent intent = new Intent(getActivity(), ScanActivity.class);
                startActivityForResult(intent, 111);
            }
        } else {
            Intent intent = new Intent(getActivity(), ScanActivity.class);
            startActivityForResult(intent, 111);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    if (result.startsWith("http")) {
                        Uri uri = Uri.parse(result);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    } else {
                        JSONObject a = null;
                        try {
                            a = new JSONObject(result);

                            switch (a.get("type").toString()) {
                                case "qrCodeLogin":
                                    startWebLoginFragment(a.get("value").toString());
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    // Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        } else if (requestCode == REQUEST_CAMERA_PERM) {
            Toast.makeText(getActivity(), "从设置页面返回...", Toast.LENGTH_SHORT)
                    .show();
        }
    }





}
