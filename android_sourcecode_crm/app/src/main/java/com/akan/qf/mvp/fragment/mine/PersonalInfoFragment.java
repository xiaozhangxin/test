package com.akan.qf.mvp.fragment.mine;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.mine.PersonalInfoPersenter;
import com.akan.qf.mvp.view.mine.IPersonalInfoView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.CustomDialog;
import com.akan.qf.view.DialogLoadding;
import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bumptech.glide.Glide;
import com.king.base.util.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
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
import static com.king.base.BaseInterface.REQUEST_CODE;

/**
 * Created by admin on 2018/11/1.
 */

public class PersonalInfoFragment extends BaseFragment<IPersonalInfoView, PersonalInfoPersenter> implements IPersonalInfoView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvPersonInfo)
    TextView tvPersonInfo;
    @BindView(R.id.ivAvatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tvNameTittle)
    TextView tvNameTittle;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvSexTittle)
    TextView tvSexTittle;
    @BindView(R.id.tvSex)
    TextView tvSex;
    @BindView(R.id.tvBornTittle)
    TextView tvBornTittle;
    @BindView(R.id.tvBorn)
    TextView tvBorn;
    @BindView(R.id.tvAddressTittle)
    TextView tvAddressTittle;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvPhoneTittle)
    TextView tvPhoneTittle;
    @BindView(R.id.tvSuperior)
    TextView tvSuperior;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvRoleName)
    TextView tvRoleName;
    Unbinder unbinder;
    private String path = "";
    private String newPath = "";
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private DialogLoadding dialogLoadding;

    public static PersonalInfoFragment newInstance() {
        Bundle args = new Bundle();
        PersonalInfoFragment fragment = new PersonalInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_personal_info;
    }

    @Override
    public void initUI() {
        tvTitle.setText("个人资料");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("保存");
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        Glide.with(context).load(Constants.BASE_URL + userBean.getHead_img()).error(R.drawable.error_img).into(ivAvatar);
        tvName.setText(userBean.getStaff_name());
        tvPhone.setText(userBean.getPhone());
        tvAddress.setText(userBean.getContact_address());
        tvSex.setText(userBean.getSex());
        tvBorn.setText(userBean.getBirthday());
        tvSuperior.setText(userBean.getParent_staff_name());
        tvRoleName.setText(userBean.getModule_role_names());
    }

    @Override
    public void onUploadFile(String data) {
        newPath = data;
        map.put("staff_id", userBean.getStaff_id());
        map.put("head_img", data);
        getPresenter().updateStaff(userBean.getStaff_token(), map);

    }

    @Override
    public void OnUpdateStaff(String data) {
        ToastUtil.showToast(context.getApplicationContext(), "上传成功");
        if (dialogLoadding != null) {
            dialogLoadding.closeDialog();
        }

        if (!TextUtils.isEmpty(newPath)) {
            userBean.setHead_img(newPath);
            saveUser(userBean);
            path = "";
        }
        if (dialogLoadding != null) {
            dialogLoadding.closeDialog();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public PersonalInfoPersenter createPresenter() {
        return new PersonalInfoPersenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvPersonInfo, R.id.ivAvatar, R.id.ivLeft, R.id.tvPhoneCall, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                if (TextUtils.isEmpty(path)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请先更换头像");
                    return;
                }
                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                upImg();
                break;
            case R.id.tvPersonInfo:
            case R.id.ivAvatar:
                show_img();
                break;
            case R.id.tvPhoneCall:
                final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
                builder1.setMessage("是否确认拨打客服电话?");
                builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sq("4008208717");
                        dialog.dismiss();
                    }
                });
                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder1.onCreate().show();
                break;
        }
    }

    private void sq(String phone) {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {
                ToastUtil.showToast(context.getApplicationContext(), getString(R.string.authorized));
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        } else {
            CallPhone(phone);
        }
    }

    private void CallPhone(String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL); // 设置动作
        Uri data = Uri.parse("tel:" + phone); // 设置数据
        intent.setData(data);
        startActivity(intent); // 激活Activity组件
    }


    //-----------------------------------------------上传图片------------------------------------------------------//

    protected void show_img() {
        String cachePath = BoxingFileHelper.getCacheDir(context);
        if (TextUtils.isEmpty(cachePath)) {
            ToastUtil.showToast(context.getApplicationContext(), R.string.boxing_storage_deny);
            return;
        }
        Uri destUri = new Uri.Builder()
                .scheme("file")
                .appendPath(cachePath)
                .appendPath(String.format(Locale.US, "%s.jpg", System.currentTimeMillis()))
                .build();
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG).withCropOption(new BoxingCropOption(destUri)).needCamera(R.drawable.ic_boxing_camera_white)
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
        Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);
                    if (medias.get(0) instanceof ImageMedia) {
                        path = ((ImageMedia) medias.get(0)).getThumbnailPath();
                    } else {
                        path = medias.get(0).getPath();
                    }
                    BoxingMediaLoader.getInstance().displayThumbnail(ivAvatar, path, 480, 480);
                    Glide.with(context).load(path).into(ivAvatar);

                }
                break;
        }
    }

    //上传图片
    private void upImg() {
        Luban.with(getActivity())
                .load(path)  // 传人要压缩的图片列表
                .ignoreBy(100).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(File file) {
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                builder.addFormDataPart("path", "/images/member");
                builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
                MultipartBody build = builder.build();
                getPresenter().uploadFile(userBean.getStaff_token(), build);
            }

            @Override
            public void onError(Throwable e) {

            }
        }).launch();
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
