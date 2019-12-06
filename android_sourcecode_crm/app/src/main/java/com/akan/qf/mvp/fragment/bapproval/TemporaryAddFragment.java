package com.akan.qf.mvp.fragment.bapproval;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.FileListBean;
import com.akan.qf.bean.FirstEventNew;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.TemporaryBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.TemporaryPresenter;
import com.akan.qf.mvp.view.home.ITemporaryView;
import com.akan.qf.util.CashierInputFilter;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.DialogLoadding;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bumptech.glide.Glide;
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
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/11/5.
 */

public class TemporaryAddFragment extends BaseFragment<ITemporaryView, TemporaryPresenter> implements ITemporaryView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.lineEleven)
    View lineEleven;
    @BindView(R.id.tvDepartmentTittle)
    TextView tvDepartmentTittle;
    @BindView(R.id.tvDepartment)
    EditText tvDepartment;
    @BindView(R.id.lineOne)
    View lineOne;
    @BindView(R.id.tvTimeTittle)
    TextView tvTimeTittle;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.lineThree)
    View lineThree;
    @BindView(R.id.tvContentTittle)
    TextView tvContentTittle;
    @BindView(R.id.tvContent)
    EditText tvContent;
    @BindView(R.id.lineFour)
    View lineFour;
    @BindView(R.id.tvPriceNumTittle)
    TextView tvPriceNumTittle;
    @BindView(R.id.tvPriceNum)
    EditText tvPriceNum;
    @BindView(R.id.lineSeven)
    View lineSeven;
    @BindView(R.id.tvPriceTypeTittle)
    TextView tvPriceTypeTittle;
    @BindView(R.id.tvPriceType)
    TextView tvPriceType;
    @BindView(R.id.lineTen)
    View lineTen;
    @BindView(R.id.tvBankTittle)
    TextView tvBankTittle;
    @BindView(R.id.tvBank)
    EditText tvBank;
    @BindView(R.id.lineThirteen)
    View lineThirteen;
    @BindView(R.id.tvPayeeTittle)
    TextView tvPayeeTittle;
    @BindView(R.id.tvPayee)
    EditText tvPayee;
    @BindView(R.id.lineTwo)
    View lineTwo;
    @BindView(R.id.tvRemakeTittle)
    TextView tvRemakeTittle;
    @BindView(R.id.tvRemake)
    EditText tvRemake;
    @BindView(R.id.oneLine)
    View oneLine;
    @BindView(R.id.tvImgTittle)
    TextView tvImgTittle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.twoLine)
    View twoLine;
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    @BindView(R.id.circleImageVIew)
    CircleImageView circleImageVIew;
    @BindView(R.id.ivCheckDelete)
    ImageView ivCheckDelete;
    @BindView(R.id.tvCheckPersonName)
    TextView tvCheckPersonName;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private List<String> list;
    private ImageAdapter adapter;
    private static final int IMAGE = 0X02;
    private String ask_id = "";
    private TemporaryBean data;
    private String type;
    private List<FileListBean> imgList = new ArrayList();
    private DialogLoadding dialogLoadding;

    private PermissionsBean permissionsBean;
    public static TemporaryAddFragment newInstance(TemporaryBean bean, String type,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        TemporaryAddFragment fragment = new TemporaryAddFragment();
        fragment.type = type;
        fragment.permissionsBean=permissionsBean;
        fragment.data = bean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_temporary;
    }

    @Override
    public void initUI() {
        InputFilter[] filters = {new CashierInputFilter(2)};
        tvPriceNum.setFilters(filters);
        if ("0".equals(type)) {
            getData();
            tvTitle.setText("新增借支申请");
            ok.setText("确认申请");
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
            tvTitle.setText("修改借支申请");
            ok.setText("确认修改");
            imgList.addAll(data.getFileList());
            tvDepartment.setText(data.getStaff_department());
            tvRemake.setText(data.getSupport_remark());
            tvContent.setText(data.getSupport_result());
            tvPriceNum.setText(data.getSupport_price());
            tvPriceType.setText(data.getSupport_back_time());
            tvBank.setText(data.getSupport_project());
            tvPayee.setText(data.getSupport_accept());
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
                    if (adapter.getAllData().size() <= 10) {
                        show_img(IMAGE);
                    } else {
                        ToastUtil.showToast(context.getApplicationContext(), "最多10张");
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
                                getPresenter().deleteTemporarySupportFile(userBean.getStaff_token(), map);
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
        tvDepartment.setText(userBean.getSimple_department_name());

        if ("0".equals(type)) {
            tvDepartment.setText(userBean.getSimple_department_name());
            if (!TextUtils.isEmpty(userBean.getParent_id())) {
                ask_id = userBean.getParent_id();
                Glide.with(context)
                        .load(Constants.BASE_URL + userBean.getParent_head_img())
                        .error(R.drawable.error_img)
                        .into(circleImageVIew);
                tvCheckPersonName.setText(userBean.getParent_staff_name());
            }
            map.clear();
            map.put("group_id", userBean.getDepartment_id());
            map.put("staff_id", userBean.getStaff_id());
            getPresenter().getCheckPerson(userBean.getStaff_token(), map);
        } else {
            ask_id = data.getNext_staff_id();
            Glide.with(context)
                    .load(Constants.BASE_URL + data.getNext_audit_staff_head_img())
                    .error(R.drawable.error_img)
                    .into(circleImageVIew);
            tvCheckPersonName.setText(data.getNext_audit_staff_name());
        }
    }

    @Override
    public void OnInsertTemporarySupport(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvPriceNum.setText("");
        tvPriceType.setText("");
        tvContent.setText("");
        tvBank.setText("");
        tvPayee.setText("");
        tvDepartment.setText("");
        tvRemake.setText("");
        finish();

    }

    @Override
    public void OnGetTemporarySupportList(List<TemporaryBean> data, String total) {

    }

    @Override
    public void OnGetTemporarySupport(TemporaryBean data) {

    }

    @Override
    public void OnAuditTemporarySupport(String data) {

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
        map.put("supportFileList", s);
        if ("1".equals(type)) {
            map.put("operation", "1");
            map.put("support_id", data.getSupport_id());
            getPresenter().updateTemporarySupport(userBean.getStaff_token(), map);
        } else {
            map.put("operation", "0");
            getPresenter().insertTemporarySupport(userBean.getStaff_token(), map);
        }

    }

    @Override
    public void OnDeleteTemporarySupportFile(String data) {

        ToastUtil.showToast(context.getApplicationContext(), data);
    }

    @Override
    public void OnDeleteTemporarySupport(String data) {


    }

    @Override
    public void OnUpdateTemporarySupport(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();

    }

    @Override
    public void onGetCheckPerson(List<MeParentBean> data) {
        for (int i = 0; i < data.size(); i++) {
            String module = data.get(i).getStaff_module();
            if (module.contains("TemporarySupport")) {
                String staff_give = data.get(i).getStaff_give();
                if ("1".equals(staff_give)) {
                    MeParentBean checkBean = data.get(i);
                    ask_id = checkBean.getStaff_id();
                    Glide.with(context)
                            .load(Constants.BASE_URL + checkBean.getHead_img())
                            .error(R.drawable.error_img)
                            .into(circleImageVIew);
                    tvCheckPersonName.setText(checkBean.getStaff_name());
                }

            }
        }
    }

    @OnClick({R.id.ivLeft, R.id.tvPriceType, R.id.circleImageVIew, R.id.ivCheckDelete, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvPriceType:
                showStartTime();
                break;
            case R.id.circleImageVIew:
                startChooseCheckPersonFragment("1");
                break;
            case R.id.ivCheckDelete:
                ivCheckDelete.setVisibility(View.GONE);
                tvCheckPersonName.setText("请选择");
                ask_id = "";
                Glide.with(context).load("").error(R.drawable.check_img).into(circleImageVIew);
                break;
            case R.id.ok:

                String mDepartment = tvDepartment.getText().toString().trim();
                if (TextUtils.isEmpty(mDepartment)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请填写部门");
                    return;
                }

                String mtvContent = tvContent.getText().toString().trim();
                if (TextUtils.isEmpty(mtvContent)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入暂支事由");
                    return;
                }
                String mPriceNum = tvPriceNum.getText().toString().trim();
                if (TextUtils.isEmpty(mPriceNum)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入暂支金额");
                    return;
                }
                String mPriceText = tvPriceType.getText().toString().trim();
                if (TextUtils.isEmpty(mPriceText)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择预计归还日期");
                    return;
                }
                String mPriceType = tvBank.getText().toString().trim();
                if (TextUtils.isEmpty(mPriceType)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选输入科目");
                    return;
                }
                String mPriceStyle = tvPayee.getText().toString().trim();
                if (TextUtils.isEmpty(mPriceStyle)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入受款人");
                    return;
                }
                if (TextUtils.isEmpty(ask_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择审批人");
                    return;
                }
                ok.setEnabled(false);
                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("support_price", mPriceNum);
                map.put("support_back_time", mPriceText);
                map.put("support_result", mtvContent);
                map.put("support_project", mPriceType);
                map.put("support_accept", mPriceStyle);
                map.put("support_department", mDepartment);
                map.put("support_remark", tvRemake.getText().toString());
                map.put("next_staff_id", ask_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());


                if ((adapter.getAllData().size() - imgList.size()) > 1) {
                    upImage();
                } else {
                    if ("1".equals(type)) {
                        map.put("operation", "1");
                        map.put("support_id", data.getSupport_id());
                        getPresenter().updateTemporarySupport(userBean.getStaff_token(), map);
                    } else {
                        map.put("operation", "0");
                        getPresenter().insertTemporarySupport(userBean.getStaff_token(), map);
                    }
                }

                break;
        }
    }

    private void showStartTime() {
        TimePickerView build = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String format = formatter.format(date);
                tvPriceType.setText(format);
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setTitleSize(15)//标题文字大小
                .setSubCalSize(14)
                .setTitleText("预计归还日期")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.WHITE)//标题文字颜色
                .setSubmitColor(Color.WHITE)//确定按钮文字颜色
                .setCancelColor(Color.WHITE)//取消按钮文字颜色
                .setTitleBgColor(0xFF4DA9EB)//标题背景颜色
                .setBgColor(0xFFFFFFFF)//滚轮背景颜色
                //.setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                //.setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build();
        build.show();

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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(11 - adapter.getAllData().size()).withCropOption(new BoxingCropOption(destUri)).needCamera(R.drawable.ic_boxing_camera_white)
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
                    //adapter.clear();
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
                    if (adapter.getAllData().size() <= 10) {
                        adapter.add("");
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }


    @Override
    public TemporaryPresenter createPresenter() {
        return new TemporaryPresenter(getApp());
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
        TemporaryBean bean = new TemporaryBean();
        bean.setSupport_price(tvPriceNum.getText().toString());
        bean.setSupport_back_time(tvPriceType.getText().toString());
        bean.setSupport_result(tvContent.getText().toString());
        bean.setSupport_project(tvBank.getText().toString());
        bean.setSupport_accept(tvPayee.getText().toString());
        bean.setSupport_department(tvDepartment.getText().toString());
        bean.setSupport_remark(tvRemake.getText().toString());
        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("temporary", s);
        editor.commit();
    }

    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("temporary", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                tvPriceNum.setText(object.getString("support_price"));
                tvPriceType.setText(object.getString("support_back_time"));
                tvContent.setText(object.getString("support_result"));
                tvBank.setText(object.getString("support_project"));
                tvPayee.setText(object.getString("support_accept"));
                tvDepartment.setText(object.getString("support_department"));
                tvRemake.setText(object.getString("support_remark"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventNew event) {
        switch (event.getMsg()) {
            case "1":
                MeParentBean bean = event.getmBean();
                Glide.with(context)
                        .load(Constants.BASE_URL + bean.getHead_img())
                        .error(R.drawable.error_img)
                        .into(circleImageVIew);
                tvCheckPersonName.setText(bean.getStaff_name());
                ivCheckDelete.setVisibility(View.VISIBLE);
                ask_id = bean.getStaff_id();
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


}