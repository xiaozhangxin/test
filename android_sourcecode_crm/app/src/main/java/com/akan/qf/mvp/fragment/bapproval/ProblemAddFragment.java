package com.akan.qf.mvp.fragment.bapproval;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.FileListBean;
import com.akan.qf.bean.NewApplyBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.ProblemPresenter;
import com.akan.qf.mvp.view.home.IProblemView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.DialogLoadding;
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

/**
 * Created by admin on 2018/11/12.
 */

public class ProblemAddFragment extends BaseFragment<IProblemView, ProblemPresenter> implements IProblemView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvTypeTittle)
    TextView tvTypeTittle;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.lineTwo)
    View lineTwo;
    @BindView(R.id.tvTimeTittle)
    TextView tvTimeTittle;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.lineThree)
    View lineThree;
    @BindView(R.id.tvAddressTittle)
    TextView tvAddressTittle;
    @BindView(R.id.tvAddress)
    EditText tvAddress;
    @BindView(R.id.lineFour)
    View lineFour;
    @BindView(R.id.tvTyTittle)
    TextView tvTyTittle;
    @BindView(R.id.tvTy)
    TextView tvTy;
    @BindView(R.id.lineFive)
    View lineFive;
    @BindView(R.id.etReason)
    EditText etReason;
    @BindView(R.id.tvReasonTittle)
    TextView tvReasonTittle;
    @BindView(R.id.lineSix)
    View lineSix;
    @BindView(R.id.tvImgTittle)
    TextView tvImgTittle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private List<String> list;
    private ImageAdapter adapter;
    private static final int IMAGE = 0X02;
    private String type;
    private NewApplyBean data;
    private List<FileListBean> imgList = new ArrayList();
    private DialogLoadding dialogLoadding;
    private PermissionsBean permissionsBean;
    public static ProblemAddFragment newInstance(NewApplyBean bean, String type,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ProblemAddFragment fragment = new ProblemAddFragment();
        fragment.data = bean;
        fragment.type = type;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_problem;
    }

    @Override
    public void initUI() {

        if ("0".equals(type)) {
            getData();
            ok.setText("提交");
            tvTitle.setText("新增问题建议/新品申请");
            list = new ArrayList<>();
            list.add("");
            adapter = new ImageAdapter(context, list);
            recycleView.setNestedScrollingEnabled(false);
            recycleView.setLayoutManager(new GridLayoutManager(context, 3));
            recycleView.setAdapter(adapter);
            adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (adapter.getAllData().size() <= 5) {
                        show_img(IMAGE);
                    } else {
                        ToastUtil.showToast(context.getApplicationContext(), "最多5张");
                    }
                }
            });
            adapter.setOnDeleteClick(new ImageAdapter.OnDeleteClick() {
                @Override
                public void onDeleteClick(int data) {
                    adapter.remove(data);
                }
            });
        } else {
            tvTitle.setText("修改问题建议/新品申请");
            ok.setText("确认修改");
            imgList.addAll(data.getFileList());
            tvTy.setText(data.getApply_category_show());
            etReason.setText(data.getApply_remark());
            list = new ArrayList<>();
            List<FileListBean> fileList = data.getFileList();
            for (int i = 0; i < fileList.size(); i++) {
                list.add(fileList.get(i).getUp_url());
            }
            list.add("");
            adapter = new ImageAdapter(context, list);
            recycleView.setNestedScrollingEnabled(false);
            recycleView.setLayoutManager(new GridLayoutManager(context, 3));
            recycleView.setAdapter(adapter);
            adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (adapter.getAllData().size() <= 5) {
                        show_img(IMAGE);
                    } else {
                        ToastUtil.showToast(context.getApplicationContext(), "最多5张");
                    }
                }
            });
            adapter.setOnDeleteClick(new ImageAdapter.OnDeleteClick() {
                @Override
                public void onDeleteClick(final int position) {
                    if (adapter.getItem(position).contains("storage/")) {
                        adapter.remove(position);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage(R.string.detete_file);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                map.clear();
                                map.put("file_id", imgList.get(position).getFile_id() + "");
                                getPresenter().deleteNewApplyFile(userBean.getStaff_token(), map);
                                imgList.remove(position);
                                adapter.remove(position);
                            }
                        });
                        builder.setNegativeButton("取消", null);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
            });
        }
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        tvType.setText(userBean.getStaff_name());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTime.setText(str);
        tvAddress.setText(userBean.getSimple_department_name());
    }

    @Override
    public void onUploadFiles(String[] mdata) {
        ArrayList<FileListBean> list = new ArrayList<>();
        for (int i = 0; i < mdata.length; i++) {
            FileListBean bean = new FileListBean();
            bean.setUp_url(mdata[i]);
            bean.setFile_size(fileSize.get(i));

            list.add(bean);
        }
        Gson gson = new Gson();
        String s = gson.toJson(list);
        map.put("applyFileList", s);
        if ("1".equals(type)) {
            map.put("operation", "1");
            map.put("apply_id", data.getApply_id());
            getPresenter().updateNewApply(userBean.getStaff_token(), map);
        } else {
            getPresenter().insertNewApply(userBean.getStaff_token(), map);
        }
    }

    @Override
    public void OnInsertNewApplyy(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        etReason.setText("");
        finish();

    }

    @OnClick({R.id.tvTy, R.id.ivLeft, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvTy:
                showSingleAlertDialog("选择类型", new String[]{"问题反馈", "新品申请", "建议"});
                break;
            case R.id.ok:
                String mDepartment = tvTy.getText().toString().trim();
                if (TextUtils.isEmpty(mDepartment)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择类型");
                    return;
                }
                String metReason = etReason.getText().toString().trim();
                if (TextUtils.isEmpty(metReason)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入详情描述");
                    return;
                }
                ok.setEnabled(false);
                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                switch (mDepartment) {
                    case "问题反馈":
                        map.put("apply_category", "problem");
                        break;
                    case "新品申请":
                        map.put("apply_category", "new");
                        break;
                    case "建议":
                        map.put("apply_category", "suggest");
                        break;
                }
                map.put("apply_department", userBean.getDepartment_name());
                map.put("apply_remark", metReason);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "0");
                map.put("module_id", permissionsBean.getMenu_id());
                if ((adapter.getAllData().size() - imgList.size()) > 1) {
                    upImage();
                } else {
                    if ("1".equals(type)) {
                        map.put("operation", "1");
                        map.put("apply_id", data.getApply_id());
                        getPresenter().updateNewApply(userBean.getStaff_token(), map);
                    } else {
                        getPresenter().insertNewApply(userBean.getStaff_token(), map);
                    }
                }

                break;
        }
    }

    private AlertDialog alertDialog2;
    private int choose = 0;

    public void showSingleAlertDialog(final String tittle, final String[] items) {
        choose = 0;
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle(tittle);
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose = i;
            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvTy.setText(items[choose]);
                alertDialog2.dismiss();
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(6 - adapter.getAllData().size()).withCropOption(new BoxingCropOption(destUri)).needCamera(R.drawable.ic_boxing_camera_white)
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);

        Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, requestcode);
    }

    //从相册选择图片返回
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE:
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
                    if (adapter.getAllData().size() <= 5) {
                        adapter.add("");
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
    List<String> fileSize = new ArrayList<>();

    //上传图片
    private void upImage() {
        final List<File> files = new ArrayList<>();
        adapter.remove(adapter.getAllData().size() - 1);
        final List<String> allData = adapter.getAllData();
        final ArrayList<String> inList = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            if (allData.get(i).contains("storage/")) {
                inList.add(allData.get(i));
            }
        }
        Luban.with(getActivity())
                .load(inList)  // 传人要压缩的图片列表
                .ignoreBy(100).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(File file) {
                files.add(file);
                fileSize.add(file.length() / 1000 + "k");
                if (inList.size() == files.size()) {
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("path", "/images/problem");
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
    public ProblemPresenter createPresenter() {
        return new ProblemPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveData();
        unbinder.unbind();
    }
    private void saveData() {
        NewApplyBean bean = new NewApplyBean();
        bean.setApply_remark(etReason.getText().toString());
        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("problem", s);
        editor.commit();
    }
    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("problem", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                etReason.setText(object.getString("apply_remark"));
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
        ok.setEnabled(true);
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());
    }


    @Override
    public void OnGetNewApplyList(List<NewApplyBean> data, String total) {

    }

    @Override
    public void OnGetNewApply(NewApplyBean data) {

    }

    @Override
    public void OnAuditNewApply(String data) {

    }

    @Override
    public void OndeleteNewApplyFile(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
    }

    @Override
    public void OndeleteNewApply(String data) {

    }

    @Override
    public void OnupdateNewApply(String data) {
        if (dialogLoadding!=null){
            dialogLoadding.closeDialog();
        }

        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();

    }
}