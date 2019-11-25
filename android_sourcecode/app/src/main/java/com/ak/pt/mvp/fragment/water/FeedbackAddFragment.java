package com.ak.pt.mvp.fragment.water;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.BookNameBean;
import com.ak.pt.bean.FeedBackBean;
import com.ak.pt.bean.FeedbackFileBean;
import com.ak.pt.bean.FirstEventBook;
import com.ak.pt.bean.TwoChooseBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.water.FeedbackPresenter;
import com.ak.pt.mvp.view.water.IFeedbackView;
import com.ak.pt.util.DialogUtil;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.ak.pt.view.DialogLoading;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.uniquext.videopicker.VideoBean;
import com.uniquext.videopicker.VideoHelper;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.ak.pt.mvp.fragment.adaily.SignFragment.REQUEST_CARERAM;

/**
 * Created by admin on 2019/5/23.
 */

public class FeedbackAddFragment extends BaseFragment<IFeedbackView, FeedbackPresenter> implements IFeedbackView {


    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRightTwo)
    ImageView ivRightTwo;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.et_one)
    EditText etOne;
    @BindView(R.id.tv_one)
    EditText tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    EditText tvThree;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.tv_five)
    EditText tvFive;
    @BindView(R.id.tv_six)
    EditText tvSix;
    @BindView(R.id.tvQOne)
    EditText tvQOne;
    @BindView(R.id.tvQTwo)
    EditText tvQTwo;
    @BindView(R.id.tvWOne)
    TextView tvWOne;

    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.wTittle)
    TextView wTittle;
    @BindView(R.id.tvWTwo)
    EditText tvWTwo;
    @BindView(R.id.tvWTwoImg)
    ImageView tvWTwoImg;
    @BindView(R.id.tvWThree)
    EditText tvWThree;
    @BindView(R.id.takeVideo)
    ImageView takeVideo;
    @BindView(R.id.delete)
    ImageView delete;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;

    private DialogLoading dialogLoading;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;


    private static final int CODE_ONE = 0X01;
    private static final int VIDEO_WITH_CAMERA = 0x02;
    private static final int VIDEO_LIBRARY = 0x03;
    private String type;
    private FeedBackBean data;
    private ArrayList<FeedbackFileBean> fileList = new ArrayList<>();


    private AppPermissionsBean permissionsBean;

    public static FeedbackAddFragment newInstance(FeedBackBean bean, String type, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        FeedbackAddFragment fragment = new FeedbackAddFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.data = bean;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_feed_back;
    }

    @Override
    public void initUI() {
        if ("0".equals(type)) {
            ok.setText("提交");
            tvTitle.setText("产品质量反馈表");
        } else {
            tvTitle.setText("修改产品质量反馈表");
            ok.setText("确认修改");

            tvDepartment.setText(data.getDepartment_name());
            group_id = data.getGroup_id();
            tvOne.setText(data.getCustomer_name());
            tvTwo.setText(data.getCustomer_sex());
            tvThree.setText(data.getCustomer_tel());
            tvFour.setText(data.getCustomer_address());
            tvFive.setText(data.getAddress_info());
            //  tvSix.setText(data.getAddress_code());

            tvQOne.setText(data.getQuality_name());
            tvQTwo.setText(data.getQuality_tel());

            tvWOne.setText(data.getFault_model() + data.getFault_no());
            fault_model = data.getFault_model();
            fault_no = data.getFault_no();
            tvWTwo.setText(data.getProduct_no());
            tvWThree.setText(data.getQuality_info());
            if (data.getFileList().size() > 0) {
                delete.setVisibility(View.VISIBLE);
                // Bitmap videoThumbnail = getNetVideoBitmap(Constants.BASE_URL + data.getFileList().get(0).getFile_url());
                // takeVideo.setImageBitmap(videoThumbnail);
                Glide.with(context).load(R.drawable.video_play).into(takeVideo);
            }
        }


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if ("0".equals(type)) {
            tvDepartment.setText(userBean.getDepartment_name());
            group_id = userBean.getDepartment_id();
        }
    }


    @OnClick({R.id.ivLeft, R.id.tv_two, R.id.tv_four, R.id.tvWOne, R.id.ok, R.id.tvWTwoImg, R.id.takeVideo, R.id.delete, R.id.tvDepartment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.delete:
                if ("0".equals(type)) {
                    delete.setVisibility(View.GONE);
                    Glide.with(context).load(R.drawable.video).into(takeVideo);
                    fileUri = null;
                } else {
                    if (data.getFileList().size() > 0) {
                        toDeleteFile();

                    } else {
                        delete.setVisibility(View.GONE);
                        Glide.with(context).load(R.drawable.video).into(takeVideo);
                        fileUri = null;
                    }
                }
                break;
            case R.id.takeVideo:
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CARERAM);
                        return;
                    }
                }
                if ("0".equals(type)) {
                    if (fileUri != null) {
                        showVideo(fileUri);
                    } else {
                        showDialog();
                    }
                } else {
                    if (data.getFileList().size() > 0) {
                        showVideo(Uri.parse(Constants.BASE_URL + data.getFileList().get(0).getFile_url()));
                    } else {
                        showDialog();
                    }

                }
                break;
            case R.id.tvWTwoImg:
                isHavePermission();
                break;
            case R.id.tvDepartment:
                startDepartmentPermissionFragment(permissionsBean.getMenu_id(), "water");
                break;
            case R.id.tv_two:
                showSingleAlertDialog(tvTwo, "选择性别", new String[]{"先生", "女士"});
                break;
            case R.id.tvWOne:
                startTwoChooseFragment();
                break;
            case R.id.tv_four:
                hideInputMethod(tvOne);
                cityPicker("上海市", "上海市", "浦东新区");
                break;

            case R.id.ok:
                String mtvOne = tvOne.getText().toString().trim();
                if (TextUtils.isEmpty(mtvOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入用户姓名");
                    return;
                }
                String mtvTwo = tvTwo.getText().toString();
                if (TextUtils.isEmpty(mtvTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择用户性别");
                    return;
                }
                String mtvThree = tvThree.getText().toString();
                if (TextUtils.isEmpty(mtvThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入用户电话");
                    return;
                }
                String mtvFour = tvFour.getText().toString();
                if (TextUtils.isEmpty(mtvFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择地区");
                    return;
                }
                String mtvFive = tvFive.getText().toString();
                if (TextUtils.isEmpty(mtvFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入详细地址");
                    return;
                }
/*                String mtvSix = tvSix.getText().toString();
                if (TextUtils.isEmpty(mtvSix)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入邮编");
                    return;
                }*/
                String mtvQOne = tvQOne.getText().toString();
                if (TextUtils.isEmpty(mtvQOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入反馈人姓名");
                    return;
                }
                String mtvQTwo = tvQTwo.getText().toString();
                if (TextUtils.isEmpty(mtvQTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入反馈人电话");
                    return;
                }
                String mtvWOne = tvWOne.getText().toString();
                if (TextUtils.isEmpty(mtvWOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择故障产品型号");
                    return;
                }
                String mtvWTwo = tvWTwo.getText().toString();
                if (TextUtils.isEmpty(mtvWTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入产品编号");
                    return;
                }
                String mtvWThree = tvWThree.getText().toString();
                if (TextUtils.isEmpty(mtvWThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入问题描述");
                    return;
                }
                ok.setEnabled(false);
                dialogLoading = new DialogLoading(context);
                dialogLoading.showDialog();
                map.put("group_id", group_id);
                map.put("customer_name", mtvOne);
                map.put("customer_sex", mtvTwo);
                map.put("customer_tel", mtvThree);
                map.put("customer_address", mtvFour);
                map.put("address_info", mtvFive);
                //   map.put("address_code", mtvSix);

                map.put("quality_name", mtvQOne);
                map.put("quality_tel", mtvQTwo);
                map.put("fault_model", fault_model);
                map.put("fault_no", fault_no);
                map.put("product_no", mtvWTwo);
                map.put("quality_info", mtvWThree);

                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());

                switch (type) {
                    case "0"://新建
                        map.put("operation", "0");
                        if (mediaFile == null) {
                            getPresenter().insertQualityFeedback(userBean.getStaff_token(), map);
                        } else {

                            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                            builder.addFormDataPart("path", "/video/feedback");
                            builder.addFormDataPart("file", mediaFile.getName(), RequestBody.create(MediaType.parse("video/mp4"), mediaFile));
                            MultipartBody build = builder.build();
                            getPresenter().uploadFiles(userBean.getStaff_token(), build);
                        }
                        break;
                    case "1"://编辑
                        map.put("operation", "1");
                        map.put("quality_id", data.getQuality_id());
                        if (mediaFile == null) {
                            getPresenter().updateQualityFeedback(userBean.getStaff_token(), map);
                        } else {
                            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                            builder.addFormDataPart("path", "/video/feedback");
                            builder.addFormDataPart("file", mediaFile.getName(), RequestBody.create(MediaType.parse("video/mp4"), mediaFile));
                            MultipartBody build = builder.build();
                            getPresenter().uploadFiles(userBean.getStaff_token(), build);
                        }


                }
                break;


        }
    }

    //删除视频文件
    private void toDeleteFile() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.detete_file);
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("file_id", data.getFileList().get(0).getFile_id());
                getPresenter().deleteQualityFile(userBean.getStaff_token(), map);
                data.getFileList().remove(0);
                fileUri = null;
                delete.setVisibility(View.GONE);
                Glide.with(context).load(R.drawable.video).into(takeVideo);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //-------------------------------------申请相机权限-------------------------------------------//

    public void isHavePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CARERAM);
                return;
            } else {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, CODE_ONE);
            }
        } else {
            Intent intent = new Intent(getActivity(), CaptureActivity.class);
            startActivityForResult(intent, CODE_ONE);
        }
    }


    public Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    Uri fileUri = null;
    File mediaFile;

    private void takeSmallVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        //设置视频录制的最长时间
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 30);

        try {
            mediaFile = createMediaFile();
            fileUri = Uri.fromFile(mediaFile); // create a file to save the video_v
        } catch (IOException e) {
            e.printStackTrace();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // set the image file name
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video_v image quality to high
        startActivityForResult(intent, VIDEO_WITH_CAMERA);
    }

    public void showDialog() {
        final String[] content = new String[]{"拍摄视频", "选择视频"};
        new AlertDialog.Builder(context)
                .setItems(content, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (content[i]) {
                            case "拍摄视频":
                                takeSmallVideo();
                                break;
                            case "选择视频":
                                openVideoLibrary();
                                break;
                        }
                    }
                })
                .show();
    }
    private void openVideoLibrary() {
        Intent intent = new Intent(VideoHelper.ACTION_VIDEO_LIBRARY);
        intent.putExtra(VideoHelper.PICK_TOTAL, 1);
        intent.putExtra(VideoHelper.MAX_VIDEO_SIZE, 500 * 1024 * 1024);
        startActivityForResult(intent, VIDEO_LIBRARY);
    }

    // 播放视频
    private void showVideo(Uri fileUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(fileUri, "video/mp4");
        startActivity(intent);
    }


    private File createMediaFile() throws IOException {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "CameraDemo");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "VID_" + timeStamp;
        String suffix = ".mp4";
        File mediaFile = new File(mediaStorageDir + File.separator + imageFileName + suffix);
        return mediaFile;
    }

    private void startTwoChooseFragment() {
        ArrayList<TwoChooseBean> list = new ArrayList<>();
        String json = "[{\"childList\":[{\"isCheck\":false,\"name\":\"CR50-D1\"},{\"isCheck\":false,\"name\":\"CR75-F1\"},{\"isCheck\":false,\"name\":\"CR400-K1\"},{\"isCheck\":false,\"name\":\"CR75-F2\"},{\"isCheck\":false,\"name\":\"CWRF-H11\"},{\"isCheck\":false,\"name\":\"CR-600V\"},{\"isCheck\":false,\"name\":\"CR50-D1\"}],\"isCheck\":true,\"name\":\"反渗透净水机系列\"},\n" +
                "{\"childList\":[{\"isCheck\":false,\"name\":\"CWUF-3100\"}],\"isCheck\":false,\"name\":\"超滤净水机\"},\n" +
                "{\"childList\":[{\"isCheck\":false,\"name\":\"CW-A1\"},{\"isCheck\":false,\"name\":\"CW-B1\"},{\"isCheck\":false,\"name\":\"CW-A3\"},{\"isCheck\":false,\"name\":\"CW-A8\"}],\"isCheck\":false,\"name\":\"前置过滤器系列\"},\n" +
                "{\"childList\":[{\"isCheck\":false,\"name\":\"CWAF-C10\"},{\"isCheck\":false,\"name\":\"CWAF-C05\"},{\"isCheck\":false,\"name\":\"CWAF-C06\"},{\"isCheck\":false,\"name\":\"CWAF-C15\"},{\"isCheck\":false,\"name\":\"CWAF-C20\"},{\"isCheck\":false,\"name\":\"CWAF-C25\"}],\"isCheck\":false,\"name\":\"中央净水机系列\"},\n" +
                "{\"childList\":[{\"isCheck\":false,\"name\":\"CWIE-R10\"},{\"isCheck\":false,\"name\":\"CWIE-R05\"},{\"isCheck\":false,\"name\":\"CWIE-R15\"},{\"isCheck\":false,\"name\":\"CWIE-R20\"},{\"isCheck\":false,\"name\":\"CWIE-R25\"}],\"isCheck\":false,\"name\":\"中央软水机系列\"},\n" +
                "{\"childList\":[{\"isCheck\":false,\"name\":\"CWHF-J11金色\"},{\"isCheck\":false,\"name\":\"CWHF-J11黑色\"},{\"isCheck\":false,\"name\":\"CWHF-J21\"}],\"isCheck\":false,\"name\":\"管线饮水机系列\"}]";

        if (!json.equals("") && json.length() > 0) {
            Gson gson = new Gson();
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement elem : array) {
                TwoChooseBean usern = gson.fromJson(elem, TwoChooseBean.class);
                list.add(usern);
            }
        }
        TwoChooseFragment fragment = TwoChooseFragment.newInstance(list);
        fragment.setOnOkClickListener(new TwoChooseFragment.OnOkClickListener() {
            @Override
            public void ok(String s1, String s2) {
                tvWOne.setText(s1 + s2);
                fault_model = s1;
                fault_no = s2;
            }
        });
        fragment.show(getFragmentManager(), FeedbackAddFragment.class.getSimpleName());
    }

    private String fault_model = "";
    private String fault_no = "";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_ONE) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    tvWTwo.setText(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtil.showToast(context.getApplicationContext(), "解析二维码失败");
                }
            }
        } else if (requestCode == VIDEO_WITH_CAMERA) {
            if (resultCode == Activity.RESULT_OK && data.getData() != null){
                delete.setVisibility(View.VISIBLE);
                Glide.with(context).load(fileUri).into(takeVideo);
            } else {
                fileUri = null;
            }
        } else if (requestCode == VIDEO_LIBRARY) {
            if (resultCode == Activity.RESULT_OK  &&  data != null) {
                List<VideoBean> videoBeanList = data.getParcelableArrayListExtra(VideoHelper.PICK_VIDEO);
                if (videoBeanList != null && !videoBeanList.isEmpty()) {
                    delete.setVisibility(View.VISIBLE);
                    mediaFile = new File(videoBeanList.get(0).getFilePath());
                    fileUri = Uri.fromFile(mediaFile);
                    Glide.with(context).load(videoBeanList.get(0).getThumbPath()).into(takeVideo);
                }
            } else {
                fileUri = null;
            }
        }
    }


    @Override
    public void onInsertQualityFeedback(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onDeleteQualityFeedback(String data) {

    }

    @Override
    public void onDeleteQualityFile(String data) {

    }

    @Override
    public void onUpdateQualityFeedback(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void OnGetQualityFeedbackList(List<FeedBackBean> data) {

    }

    @Override
    public void onGetQualityFeedbackDetail(FeedBackBean data) {

    }

    @Override
    public void onUploadFiles(String[] data) {

        FeedbackFileBean feedBackBean = new FeedbackFileBean();
        for (int i = 0; i < data.length; i++) {

            feedBackBean.setFile_url(data[i]);
        }
        fileList.add(feedBackBean);
        String toJson = new Gson().toJson(fileList);
        if ("0".equals(type)) {
            map.put("qualityList", toJson);
            getPresenter().insertQualityFeedback(userBean.getStaff_token(), map);
        } else {
            map.put("qualityList", toJson);
            getPresenter().updateQualityFeedback(userBean.getStaff_token(), map);
        }

    }


    //单选
    private void showSingleAlertDialog(TextView textView, String title, String[] items) {
        DialogUtil.showSingleChoiceDialog(textView, title, items);
    }


    /**
     * 显示城市列表
     */
    private void cityPicker(String province, String city, String district) {
        CityPicker cityPicker = new CityPicker.Builder(getActivity())
                .textSize(16)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#fafafa")
                .titleTextColor("#666666")
                .backgroundPop(0xa0000000)
                .confirTextColor("#33aaff")
                .cancelTextColor("#999999")
                .province(province)
                .city(city)
                .district(district)
                .textColor(Color.parseColor("#666666"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(8)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();

        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                tvFour.setText(province + "-" + city + "-" + district);
                // tvSix.setText(code);
            }

            @Override
            public void onCancel() {
            }

        });
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
        ok.setEnabled(true);
    }

    @Override
    public FeedbackPresenter createPresenter() {
        return new FeedbackPresenter(getApp());
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

    private String group_id = "";

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventBook event) {
        switch (event.getType()) {
            case "water":
                BookNameBean bean = event.getBookNameBean();
                tvDepartment.setText(bean.getName());
                group_id = bean.getNames();
                break;
        }

    }


}
