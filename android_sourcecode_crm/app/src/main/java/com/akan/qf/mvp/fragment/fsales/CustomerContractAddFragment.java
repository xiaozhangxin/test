package com.akan.qf.mvp.fragment.fsales;

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
import com.akan.qf.bean.ContractApplyBean;
import com.akan.qf.bean.FileNewBean;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.CustomerContractPresenter;
import com.akan.qf.mvp.view.home.ICustomerContractView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
 * Created by admin on 2018/11/27.
 */

public class CustomerContractAddFragment extends BaseFragment<ICustomerContractView, CustomerContractPresenter> implements ICustomerContractView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvOne)
    EditText tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    EditText tvThree;
    @BindView(R.id.tvFour)
    EditText tvFour;
    @BindView(R.id.tvFive)
    EditText tvFive;
    @BindView(R.id.tvSix)
    EditText tvSix;
    @BindView(R.id.tvSeven)
    EditText tvSeven;
    @BindView(R.id.tvEight)
    EditText tvEight;
    @BindView(R.id.tvNine)
    EditText tvNine;
    @BindView(R.id.tvTen)
    EditText tvTen;
    @BindView(R.id.tvEleven)
    EditText tvEleven;
    @BindView(R.id.tvRemark)
    EditText tvRemark;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.tvImgTittle)
    TextView tvImgTittle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String type;
    private String groud_id = "";
    private ContractApplyBean data;
    private List<String> list;
    private ImageAdapter adapter;
    private static final int IMAGE = 0X02;
    private List<FileNewBean> imgList = new ArrayList();
    private PermissionsBean permissionsBean;
    public static CustomerContractAddFragment newInstance(ContractApplyBean bean, String type,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        CustomerContractAddFragment fragment = new CustomerContractAddFragment();
        fragment.data = bean;
        fragment.type = type;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_contract_add;
    }

    @Override
    public void initUI() {

        if ("0".equals(type)) {
            getData();
            ok.setText("提交");
            tvTitle.setText("新增客户合同");
            list = new ArrayList<>();
            list.add("");
            adapter = new ImageAdapter(context, list);
            recycleView.setNestedScrollingEnabled(false);
            recycleView.setLayoutManager(new GridLayoutManager(context, 3));
            recycleView.setAdapter(adapter);
            adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (adapter.getAllData().size() <= 10) {
                        show_img(IMAGE);
                    } else {
                        ToastUtil.showToast(context.getApplicationContext(), "最多10张");
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
            tvTitle.setText("修改客户合同");
            ok.setText("确认修改");
            tvRemark.setText(data.getApply_remark());

            tvOne.setText(data.getApply_address());
            tvTwo.setText(data.getApply_department());
            tvThree.setText(data.getApply_cost());
            tvFour.setText(data.getCustomer_no());
            tvFive.setText(data.getApply_name());
            tvSix.setText(data.getApply_tel());
            tvSeven.setText(data.getApply_sale());
            tvEight.setText(data.getApply_info());
            tvNine.setText(data.getDeadline_time());
            tvTen.setText(data.getApply_fare());
            tvEleven.setText(data.getApply_rebate());
            tvRemark.setText(data.getApply_remark());

            imgList.addAll(data.getFileList());
            list = new ArrayList<>();
            List<FileNewBean> fileList = data.getFileList();
            for (int i = 0; i < fileList.size(); i++) {
                list.add(fileList.get(i).getFile_url());

            }
            list.add("");
            adapter = new ImageAdapter(context, list);
            recycleView.setNestedScrollingEnabled(false);
            recycleView.setLayoutManager(new GridLayoutManager(context, 3));
            recycleView.setAdapter(adapter);
            adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (adapter.getAllData().size() <= 10) {
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
                                map.put("file_ids", imgList.get(position).getFile_id() + "");
                                getPresenter().deleteContractFileApply(userBean.getStaff_token(), map);
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
        tvName.setText(userBean.getStaff_name());
        tvTwo.setText(userBean.getSimple_department_name());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTime.setText(str);
        if ("0".equals(type)) {
            tvTwo.setText(userBean.getSimple_department_name());
            groud_id = userBean.getDepartment_id();
        } else {
            tvTwo.setText(data.getApply_department());
            groud_id = data.getGroup_id();
        }
    }

    @OnClick({R.id.ivLeft, R.id.ok, R.id.tvTwo})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvTwo:
                StartChooseDepartmentFragment("CostSt");
                break;

            case R.id.ok:
                String mtvOne = tvOne.getText().toString().trim();
                if (TextUtils.isEmpty(mtvOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入区域负责人");
                    return;
                }
                String mtvTwo = tvTwo.getText().toString().trim();
                if (TextUtils.isEmpty(mtvTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择所属部门");
                    return;
                }
                String mtvThree = tvThree.getText().toString().trim();
                if (TextUtils.isEmpty(mtvThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入客户编号");
                    return;
                }
                String mtvFour = tvFour.getText().toString().trim();
                if (TextUtils.isEmpty(mtvFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入客户抬头");
                    return;
                }
                String mtvFive = tvFive.getText().toString().trim();
                if (TextUtils.isEmpty(mtvFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入联系人");
                    return;
                }

                String mtvSix = tvSix.getText().toString().trim();
                if (TextUtils.isEmpty(mtvSix)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入联系电话");
                    return;
                }
                String mtvSeven = tvSeven.getText().toString().trim();
                if (TextUtils.isEmpty(mtvSeven)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入年任务量");
                    return;
                }
                String mtvEight = tvEight.getText().toString().trim();
/*                if (TextUtils.isEmpty(mtvEight)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入价格体系");
                    return;
                }*/
                String mtvNine = tvNine.getText().toString().trim();
                if (TextUtils.isEmpty(mtvNine)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入经营产品");
                    return;
                }
                String mtvTen = tvTen.getText().toString().trim();
                if (TextUtils.isEmpty(mtvTen)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入运费政策");
                    return;
                }
                String mtvEleven = tvEleven.getText().toString().trim();
                if (TextUtils.isEmpty(mtvEleven)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入返点政策");
                    return;
                }
                ok.setEnabled(false);
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("apply_address", mtvOne);
                map.put("apply_department", mtvTwo);
                map.put("group_id", groud_id);
                map.put("apply_cost", mtvThree);
                map.put("customer_no", mtvFour);
                map.put("apply_name", mtvFive);
                map.put("apply_tel", mtvSix);
                map.put("apply_sale", mtvSeven);
                map.put("apply_info", mtvEight);
                map.put("deadline_time", mtvNine);
                map.put("apply_fare", mtvTen);
                map.put("apply_rebate", mtvEleven);
                map.put("apply_remark", tvRemark.getText().toString());
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
                        getPresenter().updateContractApply(userBean.getStaff_token(), map);
                    } else {
                        getPresenter().insertContractApply(userBean.getStaff_token(), map);
                    }
                }
                break;
        }
    }

    //-----------------------------------------------上传图片------------------------------------------------------//
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
                    builder.addFormDataPart("path", "/images/apply");
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
                    if (adapter.getAllData().size() > 0) {

                    }
                    if (adapter.getAllData().size() <= 6) {
                        adapter.add("");
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
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
    public CustomerContractPresenter createPresenter() {
        return new CustomerContractPresenter(getApp());
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
        saveData();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    private void saveData() {
        ContractApplyBean bean = new ContractApplyBean();


        bean.setApply_address(tvOne.getText().toString());
        bean.setApply_department(tvTwo.getText().toString());
        bean.setGroup_id(groud_id);
        bean.setApply_cost(tvThree.getText().toString());
        bean.setCustomer_no(tvFour.getText().toString());
        bean.setApply_name(tvFive.getText().toString());
        bean.setApply_tel(tvSix.getText().toString());
        bean.setApply_sale(tvSeven.getText().toString());
        bean.setApply_info(tvEight.getText().toString());
        bean.setDeadline_time(tvNine.getText().toString());
        bean.setApply_fare(tvTen.getText().toString());
        bean.setApply_rebate(tvEleven.getText().toString());
        bean.setApply_remark(tvRemark.getText().toString());
        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("customer", s);
        editor.commit();
    }

    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("customer", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                tvOne.setText(object.getString("apply_address"));
                tvTwo.setText(object.getString("apply_department"));
                groud_id = object.getString("group_id");
                tvThree.setText(object.getString("apply_cost"));
                tvFour.setText(object.getString("customer_no"));
                tvFive.setText(object.getString("apply_name"));
                tvSix.setText(object.getString("apply_tel"));
                tvSeven.setText(object.getString("apply_sale"));
                tvEight.setText(object.getString("apply_info"));
                tvNine.setText(object.getString("deadline_time"));
                tvTen.setText(object.getString("apply_fare"));
                tvEleven.setText(object.getString("apply_rebate"));
                tvRemark.setText(object.getString("apply_remark"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getMsg();
        if (msg.contains("CostSt")) {
            tvTwo.setText(msg.substring(6, msg.indexOf("+")));
            groud_id = msg.substring((msg.indexOf("+") + 1), msg.length());
        }

    }

    @Override
    public void OnInsertContractApply(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvOne.setText("");
        tvTwo.setText("");
        groud_id = "";
        tvThree.setText("");
        tvFour.setText("");
        tvFive.setText("");
        tvSix.setText("");
        tvSeven.setText("");
        tvEight.setText("");
        tvNine.setText("");
        tvTen.setText("");
        tvEleven.setText("");
        tvRemark.setText("");
        finish();
    }

    @Override
    public void OnGetContractApplyList(List<ContractApplyBean> data, String total) {

    }

    @Override
    public void OndeleteContractApply(String data) {

    }

    @Override
    public void OnupdateContractApply(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void getContractApply(ContractApplyBean data) {

    }

    @Override
    public void OnDeleteContractFileApply(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
    }

    @Override
    public void onUploadFiles(String[] mdata) {
        ArrayList<FileNewBean> list = new ArrayList<>();
        for (int i = 0; i < mdata.length; i++) {
            FileNewBean bean = new FileNewBean();
            bean.setFile_url(mdata[i]);
            bean.setFile_remark("大小:"+fileSize.get(i));
            list.add(bean);
        }
        Gson gson = new Gson();
        String s = gson.toJson(list);
        map.put("applyFileList", s);
        if ("1".equals(type)) {
            map.put("apply_id", data.getApply_id());
            getPresenter().updateContractApply(userBean.getStaff_token(), map);
        } else {
            getPresenter().insertContractApply(userBean.getStaff_token(), map);
        }
    }
}