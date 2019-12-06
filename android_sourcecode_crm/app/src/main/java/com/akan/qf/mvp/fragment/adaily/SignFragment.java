package com.akan.qf.mvp.fragment.adaily;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.SignPresenter;
import com.akan.qf.mvp.view.ISignView;
import com.akan.qf.util.GlideRoundTransform;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.king.base.util.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;

/**
 * Created by admin on 2018/7/2.
 */

public class SignFragment extends BaseFragment<ISignView, SignPresenter> implements ISignView {


    Unbinder unbinder;
    public static final int TAKE_PHOTO = 101;
    public static final int REQUEST_CARERAM = 102;
    public static final int REQUEST_LOCATION = 103;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.ivAvatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.etContent)
    EditText etContent;
    @BindView(R.id.ivImg)
    ImageView ivImg;
    @BindView(R.id.tvSign)
    TextView tvSign;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    //获取当前位置
    private AMapLocationClient mLocationClient = null;
    private String mAddress = "";

    private Uri imageUri; //图片路径
    private File imageFile; //图片文件
    private PermissionsBean permissionsBean;

    public static SignFragment newInstance(PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        SignFragment fragment = new SignFragment();
        fragment.setArguments(args);
        fragment.permissionsBean=permissionsBean;
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_sign;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.sign);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("token_staff_id", userBean.getStaff_id());
        map.put("staff_token", userBean.getStaff_token());
        map.put("staff_id", userBean.getStaff_id());
        tvName.setText(userBean.getStaff_name());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTime.setText(str);
        Glide.with(getContext())
                .load(Constants.BASE_URL + userBean.getHead_img())
                .error(R.drawable.error_img)
                .into(ivAvatar);
        if (Build.VERSION.SDK_INT >= 23) {
            showContacts();
        } else {
            initLocation();
        }

    }


    @Override
    public void onInsertSign(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onUploadFile(String data) {
        map.put("sign_image", data);
        getPresenter().insertSign(userBean.getStaff_token(), map);
    }


    //----------------------------------定位------------------------------------------------//

    //申请定位权限
    public void showContacts() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            initLocation();
        }
    }

    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(context.getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //启动定位
        mLocationClient.startLocation();
    }

    //异步获取定位结果
    private AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    String address = amapLocation.getAddress();
                    mAddress = address;
                }
                mLocationClient.stopLocation();
            }
        }
    };
    //----------------------------------拍照------------------------------------------------//

    public void takePhoto() {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        imageFile = new File(path, System.currentTimeMillis() + "sign.jpg");
        try {
            if (imageFile.exists()) {
                imageFile.delete();
            }
            imageFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将File对象转换为Uri并启动照相程序
        //imageUri= FileProvider.getUriForFile(getActivity(), "com.ak.qf.fileProvider", imageFile);
        imageUri = Uri.fromFile(imageFile);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //照相
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //指定图片输出地址
        startActivityForResult(intent, TAKE_PHOTO); //启动照相
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_PHOTO) {
                try {
                    Glide.with(context).load(imageFile).bitmapTransform(new GlideRoundTransform(context, 16)).into(ivImg);
                } catch (Exception e) {
                    imageFile = null;
                    e.printStackTrace();
                }
            }
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
                takePhoto();
            }
        } else {
            takePhoto();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CARERAM:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    takePhoto();
                } else {
                    // Permission Denied
                    ToastUtil.showToast(context.getApplicationContext(), "请开启相机权限");
                }
                break;
            case REQUEST_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initLocation();
                } else {
                    ToastUtil.showToast(context.getApplicationContext(), "请开启定位权限");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //上传图片
    private void upImage() {
        Luban.with(getActivity())
                .load(imageFile)
                .ignoreBy(100)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                        builder.addFormDataPart("path", "/images/sign");
                        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
                        MultipartBody build = builder.build();
                        getPresenter().uploadFile(userBean.getStaff_token(), build);
                    }

                    @Override
                    public void onError(Throwable e) {
                    } //设置回调

                }).launch();    //启动压缩

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public SignPresenter createPresenter() {
        return new SignPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mLocationClient.onDestroy();
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        tvSign.setEnabled(true);
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());

    }

    @OnClick({R.id.ivLeft, R.id.tvSign, R.id.ivImg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivImg:
                isHavePermission();
                break;
            case R.id.tvSign:
                if (TextUtils.isEmpty(mAddress)) {
                    ToastUtil.showToast(context.getApplicationContext(), "获取不到当前位置,请开启位置权限");
                    showContacts();
                    return;
                }
                String s = etContent.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入签到内容");
                    return;
                }
                tvSign.setEnabled(false);
                map.put("sign_content", s);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());
                map.put("operation", "0");
                map.put("sign_address", mAddress);
                if (imageFile != null) {
                    upImage();
                } else {
                    getPresenter().insertSign(userBean.getStaff_token(), map);
                }
                break;
        }
    }

}
