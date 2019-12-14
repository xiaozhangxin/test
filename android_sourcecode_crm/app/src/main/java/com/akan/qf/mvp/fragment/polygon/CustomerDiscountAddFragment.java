package com.akan.qf.mvp.fragment.polygon;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.AdFileBean;
import com.akan.qf.bean.AdManagementBean;
import com.akan.qf.bean.FileListNewBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageAdapter;
import com.akan.qf.mvp.adapter.ImageFileAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.ad.ADManagementPresenter;
import com.akan.qf.mvp.view.ad.IADManagementView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.CustomDialog;
import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class CustomerDiscountAddFragment extends BaseFragment<IADManagementView, ADManagementPresenter> implements IADManagementView {


    Unbinder unbinder;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTopOne)
    TextView tvTopOne;
    @BindView(R.id.tvTopTwo)
    TextView tvTopTwo;
    @BindView(R.id.tvTopThree)
    TextView tvTopThree;
    @BindView(R.id.tvTopFour)
    TextView tvTopFour;
    @BindView(R.id.tvQOne)
    EditText tvQOne;
    @BindView(R.id.tvQTwo)
    EditText tvQTwo;
    @BindView(R.id.tvQThree)
    EditText tvQThree;
    @BindView(R.id.tvQFour)
    EditText tvQFour;
    @BindView(R.id.tvQFive)
    EditText tvQFive;
    @BindView(R.id.tvQSix)
    EditText tvQSix;
    @BindView(R.id.oneRecyclerView)
    RecyclerView oneRecyclerView;
    @BindView(R.id.tvWOne)
    EditText tvWOne;
    @BindView(R.id.tvWTwo)
    TextView tvWTwo;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.ok)
    TextView ok;

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private List<String> list;
    private ImageAdapter adapter;
    private ImageFileAdapter fileAdapter;
    private static final int IMAGE = 0X02;
    private String type;
    private AdManagementBean data;

    private PermissionsBean permissionsBean;

    public static CustomerDiscountAddFragment newInstance(AdManagementBean bean, String type, PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        CustomerDiscountAddFragment fragment = new CustomerDiscountAddFragment();
        fragment.data = bean;
        fragment.permissionsBean = permissionsBean;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_customer_disount;
    }

    @Override
    public void initUI() {
        switch (type) {
            case "0":
                getData();
                ok.setText("提交");
                tvTitle.setText("客户折扣");
                list = new ArrayList<>();
                list.add("");
                adapter = new ImageAdapter(context, list);
                recycleView.setNestedScrollingEnabled(false);
                recycleView.setLayoutManager(new GridLayoutManager(context, 3));
                recycleView.setAdapter(adapter);
                adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (adapter.getAllData().size() <= 30) {
                            show_img(IMAGE);
                        } else {
                            ToastUtil.showToast(context.getApplicationContext(), "最多30张");
                        }
                    }
                });
                adapter.setOnDeleteClick(new ImageAdapter.OnDeleteClick() {
                    @Override
                    public void onDeleteClick(int data) {
                        adapter.remove(data);
                    }
                });
                break;
            case "1":
                tvTitle.setText("修改推广广告");
                ok.setText("确认修改");

                ArrayList<FileListNewBean> fileList = new ArrayList<>();
                fileList.addAll(data.getFileList());
                fileList.add(new FileListNewBean());
                fileAdapter = new ImageFileAdapter(context, fileList, "1");
                recycleView.setNestedScrollingEnabled(false);
                recycleView.setLayoutManager(new GridLayoutManager(context, 3));
                recycleView.setAdapter(fileAdapter);
                fileAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (fileAdapter.getAllData().size() <= 30) {
                            show_img(IMAGE);
                        } else {
                            ToastUtil.showToast(context.getApplicationContext(), "最多30张");
                        }
                    }
                });

                fileAdapter.setOnDeleteClick(new ImageFileAdapter.OnDeleteClick() {
                    @Override
                    public void onDeleteClick(final int dataPotion) {
                        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                        builder.setMessage(R.string.detete_file);
                        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String file_id = fileAdapter.getItem(dataPotion).getFile_id();
                                map.clear();
                                map.put("file_id", file_id);
                                getPresenter().deleteAdvertApplyFile(userBean.getStaff_token(), map);
                                fileAdapter.remove(dataPotion);
                                dialog.dismiss();
                            }
                        });
                        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.onCreate().show();
                    }
                });

                break;
        }


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTopOne.setText(userBean.getCompany_name());
        tvTopTwo.setText(userBean.getSimple_department_name());
        tvTopThree.setText(userBean.getStaff_name());
        tvTopFour.setText(str);
    }


    @Override
    public void onUploadFiles(String[] data) {
        ArrayList<AdFileBean> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            AdFileBean fileBean = new AdFileBean();
            fileBean.setFile_url(data[i]);
            list.add(fileBean);
        }
        Gson gson = new Gson();
        String content = gson.toJson(list);
        map.put("fileBeanList", content);
        getPresenter().insertOrUpdateAdvertApply(userBean.getStaff_token(), map);
    }


    private AlertDialog alertDialog2;
    private int choose = 0;

    public void showSingleAlertDialog() {
        choose = 0;
        final String[] items = {"水工会", "小区推广", "推广会", "其他"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle("类型");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose = i;
            }
        });
        alertBuilder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // tvTwo.setText(items[choose]);
                alertDialog2.dismiss();
            }
        });

        alertBuilder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog2.dismiss();
            }
        });
        alertDialog2 = alertBuilder.create();
        alertDialog2.show();
    }

    //-----------------------------------------------上传图片------------------------------------------------------//
    protected void show_img(int requestcode) {
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
        if ("1".equals(type)) {
            BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG)
                    .withMaxCount(31 - fileAdapter.getAllData().size()).withCropOption(new BoxingCropOption(destUri))
                    .needCamera(R.drawable.ic_boxing_camera_white)
                    .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
            Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, requestcode);
        } else {
            BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG)
                    .withMaxCount(31 - adapter.getAllData().size()).withCropOption(new BoxingCropOption(destUri))
                    .needCamera(R.drawable.ic_boxing_camera_white)
                    .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
            Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, requestcode);
        }


    }

    //从相册选择图片返回
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE:
                if ("0".equals(type)) {
                    if (resultCode == RESULT_OK) {
                        adapter.remove(adapter.getAllData().size() - 1);
                        final ArrayList<BaseMedia> medias = Boxing.getResult(data);
                        for (BaseMedia media : medias) {
                            if (media instanceof ImageMedia) {
                                adapter.add(((ImageMedia) media).getThumbnailPath());

                            } else {
                                adapter.add(media.getPath());
                            }
                        }
                        if (adapter.getAllData().size() > 0) {

                        }
                        if (adapter.getAllData().size() <= 31) {
                            adapter.add("");
                        }
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    if (resultCode == RESULT_OK) {
                        fileAdapter.remove(fileAdapter.getAllData().size() - 1);
                        final ArrayList<BaseMedia> medias = Boxing.getResult(data);

                        for (BaseMedia media : medias) {
                            FileListNewBean adFileBean = new FileListNewBean();

                            if (media instanceof ImageMedia) {
                                adFileBean.setFile_url(((ImageMedia) media).getThumbnailPath());
                                adFileBean.setIs_up(true);
                                fileAdapter.add(adFileBean);

                            } else {
                                adFileBean.setFile_url(media.getPath());
                                adFileBean.setIs_up(true);
                                fileAdapter.add(adFileBean);
                            }
                        }
                        if (fileAdapter.getAllData().size() > 0) {

                        }
                        if (fileAdapter.getAllData().size() <= 31) {
                            fileAdapter.add(new FileListNewBean());
                        }
                        fileAdapter.notifyDataSetChanged();
                    }
                }
                break;
        }
    }

    //上传图片
    private void upImage(final List<String> imgList) {
        final List<File> files = new ArrayList<>();
        Luban.with(getActivity())
                .load(imgList)  // 传人要压缩的图片列表
                .ignoreBy(100).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(File file) {
                files.add(file);
                if (imgList.size() == files.size()) {
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("path", "/images/admanagement");
                    for (int i = 0; i < files.size(); i++) {
                        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), files.get(i)));
                    }
                    MultipartBody build = builder.build();
                    getPresenter().uploadFiles(userBean.getStaff_token(), build);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }).launch();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public ADManagementPresenter createPresenter() {
        return new ADManagementPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveData();
        unbinder.unbind();
    }

    private void saveData() {
        AdManagementBean bean = new AdManagementBean();

        // bean.setApply_department(tvOne.getText().toString());

        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("adPromotion", s);
        editor.commit();
    }

    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("adPromotion", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                // tvOne.setText(object.getString("apply_department"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
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
    public void onInsertOrUpdateAdvertApply(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvQOne.setText("");
        tvQTwo.setText("");
        tvQFour.setText("");
        tvQFive.setText("");
        tvQSix.setText("");
        tvWOne.setText("");
        tvWTwo.setText("");
        finish();
    }

    @Override
    public void onGetAdvertApplyList(List<AdManagementBean> data, String total) {

    }

    @Override
    public void onGetAdvertApply(AdManagementBean data) {

    }

    @Override
    public void onAuditAdvertApply(String data) {

    }

    @Override
    public void onDdeleteAdvertApply(String data) {

    }

    @Override
    public void onDeleteAdvertApplyFile(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);

    }


    @OnClick({R.id.ivLeft, R.id.tvTopTwo, R.id.tvWTwo, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvTopTwo:
                showSingleAlertDialog();
                break;
            case R.id.tvWTwo:
                break;
            case R.id.ok:
                String mtvTopTwo = tvTopTwo.getText().toString().trim();
                if (TextUtils.isEmpty(mtvTopTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择部门");
                    return;
                }
                String mtvQOne = tvQOne.getText().toString().trim();
                if (TextUtils.isEmpty(mtvQOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入客户编号");
                    return;
                }
                String mtvQTwo = tvQTwo.getText().toString().trim();
                if (TextUtils.isEmpty(mtvQTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入客户名称");
                    return;
                }
                String mtvQThree = tvQThree.getText().toString().trim();
                if (TextUtils.isEmpty(mtvQThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入联系人");
                    return;
                }
                String mtvQFour = tvQFour.getText().toString().trim();
                if (TextUtils.isEmpty(mtvQFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入电话");
                    return;
                }
                String mtvQFive = tvQFive.getText().toString().trim();
                if (TextUtils.isEmpty(mtvQFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入地址");
                    return;
                }
                String mtvQSix = tvQSix.getText().toString().trim();
                if (TextUtils.isEmpty(mtvQSix)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入业务员");
                    return;
                }
                String mtvWOne = tvWTwo.getText().toString().trim();
                if (TextUtils.isEmpty(mtvWOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择合同日期");
                    return;
                }



                ok.setEnabled(false);
                map.clear();
                map.put("staff_id", userBean.getStaff_id());

                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());

                switch (type) {
                    case "0"://新建
                        map.put("operation", "0");
                        List<String> allData1 = adapter.getAllData();
                        allData1.remove(allData1.size() - 1);
                        if (allData1.size() > 0) {
                            upImage(allData1);
                        } else {
                            getPresenter().insertOrUpdateAdvertApply(userBean.getStaff_token(), map);
                        }
                        break;
                    case "1"://编辑
                        map.put("operation", "1");
                        List<FileListNewBean> allData = fileAdapter.getAllData();
                        allData.remove(allData.size() - 1);
                        map.put("apply_id", data.getApply_id());
                        if (allData.size() > 0) {
                            ArrayList<String> newList = new ArrayList<>();
                            for (int i = 0; i < allData.size(); i++) {
                                if (!allData.get(i).getFile_url().contains("images")) {
                                    newList.add(allData.get(i).getFile_url());
                                }
                            }
                            if (newList.size() > 0) {
                                upImage(newList);
                            } else {
                                getPresenter().insertOrUpdateAdvertApply(userBean.getStaff_token(), map);
                            }
                        } else {
                            getPresenter().insertOrUpdateAdvertApply(userBean.getStaff_token(), map);
                        }
                        break;
                }
                break;
        }
    }
}


