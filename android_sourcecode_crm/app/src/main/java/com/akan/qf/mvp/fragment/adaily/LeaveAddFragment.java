package com.akan.qf.mvp.fragment.adaily;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.FileListBean;
import com.akan.qf.bean.FirstEventNew;
import com.akan.qf.bean.LeaveBean;
import com.akan.qf.bean.LeaveFileBean;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.LeavePresenter;
import com.akan.qf.mvp.view.home.ILeaveView;
import com.akan.qf.util.CashierInputFilter;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.CustomDialog;
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
 * Created by admin on 2018/7/12.
 */

public class LeaveAddFragment extends BaseFragment<ILeaveView, LeavePresenter> implements ILeaveView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.lineOne)
    View lineOne;
    @BindView(R.id.tvTypeTittle)
    TextView tvTypeTittle;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.lineTwo)
    View lineTwo;
    @BindView(R.id.tvStartTimeTittle)
    TextView tvStartTimeTittle;
    @BindView(R.id.tvStartTime)
    TextView tvStartTime;
    @BindView(R.id.lineThree)
    View lineThree;
    @BindView(R.id.tvEndTimeTittle)
    TextView tvEndTimeTittle;
    @BindView(R.id.tvEndTime)
    TextView tvEndTime;
    @BindView(R.id.lineFour)
    View lineFour;
    @BindView(R.id.tvAllTimeTittle)
    TextView tvAllTimeTittle;
    @BindView(R.id.tvAllTime)
    EditText tvAllTime;
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

    @BindView(R.id.lineSeven)
    View lineSeven;
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    @BindView(R.id.circleImageVIew)
    CircleImageView circleImageVIew;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.tvCheckPersonName)
    TextView tvCheckPersonName;
    @BindView(R.id.parentview)
    LinearLayout parentview;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.ivCheckDelete)
    ImageView ivCheckDelete;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private List<String> list;

    private ImageAdapter adapter;
    private static final int IMAGE = 0X02;
    private String next_id = "";
    private long sartTime = 0;
    private long endTime = 0;
    private LeaveBean mdata;
    private String type;
    private List<FileListBean> imgList = new ArrayList();
    private PermissionsBean permissionsBean;
    public static LeaveAddFragment newInstance(LeaveBean bean, String type,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        LeaveAddFragment fragment = new LeaveAddFragment();
        fragment.type = type;
        fragment.mdata = bean;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_leave;
    }

    @Override
    public void initUI() {
        InputFilter[] filters = {new CashierInputFilter(1)};
        tvAllTime.setFilters(filters);

        //0新增 1编辑
        switch (type) {
            case "0":
                tvTitle.setText(R.string.leave_request);
                ok.setText(R.string.confirmation_application);
                getData();
                list = new ArrayList<>();
                list.add("");
                recycleView.setLayoutManager(new GridLayoutManager(context, 3));
                recycleView.setNestedScrollingEnabled(false);
                adapter = new ImageAdapter(context, list);
                recycleView.setAdapter(adapter);
                adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (adapter.getAllData().size() <= 10) {
                            show_img(IMAGE);
                        } else {
                            ToastUtil.showToast(context.getApplicationContext(), getString(R.string.max_ten_img));
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
                tvTitle.setText(R.string.modify_leave_request);
                ok.setText(R.string.confirm_the_changes);
                tvType.setText(mdata.getAsk_type());
                tvStartTime.setText(mdata.getStart_time());
                tvEndTime.setText(mdata.getEnd_time());
                tvAllTime.setText(mdata.getAll_time());
                etReason.setText(mdata.getAsk_why());
                list = new ArrayList<>();
                List<FileListBean> fileList = mdata.getAskLeaveFileBeans();
                imgList.addAll(fileList);
                for (int i = 0; i < fileList.size(); i++) {
                    list.add(fileList.get(i).getFile_path());
                }

                list.add("");
                recycleView.setLayoutManager(new GridLayoutManager(context, 3));
                recycleView.setNestedScrollingEnabled(false);
                adapter = new ImageAdapter(context, list);
                recycleView.setAdapter(adapter);
                adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (adapter.getAllData().size() <= 10) {
                            show_img(IMAGE);
                        } else {
                            ToastUtil.showToast(context.getApplicationContext(), getString(R.string.max_ten_img));
                        }
                    }
                });
                adapter.setOnDeleteClick(new ImageAdapter.OnDeleteClick() {
                    @Override
                    public void onDeleteClick(final int dataPotion) {
                        if (adapter.getItem(dataPotion).contains("storage/")) {
                            adapter.remove(dataPotion);
                        } else {
                            final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                            builder.setMessage(R.string.detete_file);
                            builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    map.clear();
                                    map.put("file_id", imgList.get(dataPotion).getFile_id() + "");
                                    getPresenter().deleteAskLeaveFile(userBean.getStaff_token(), map);
                                    adapter.remove(dataPotion);
                                    imgList.remove(dataPotion);
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
                    }
                });
                break;
        }
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        switch (type) {
            case "0"://新增
                if (!TextUtils.isEmpty(userBean.getParent_id())) {
                    next_id = userBean.getParent_id();
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
                break;
            case "1"://编辑
                next_id = mdata.getNext_audit_staff_id();
                Glide.with(context)
                        .load(Constants.BASE_URL + mdata.getNext_audit_staff_head_img())
                        .error(R.drawable.error_img)
                        .into(circleImageVIew);
                tvCheckPersonName.setText(mdata.getNext_audit_staff_name());
                break;
        }


    }




    @Override
    public void onGetLeaveDetail(LeaveBean data) {

    }

    @Override
    public void auditAskLeave(String data) {

    }

    @Override
    public void onGetAskLeaveList(List<LeaveBean> data, String total) {

    }

    @Override
    public void onDeleteAskLeaveFile(String data) {

    }

    @Override
    public void onDeleteAskLeave(String data) {

    }


    @Override
    public void onUploadFiles(String[] data) {
        ArrayList<LeaveFileBean> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            LeaveFileBean leaveFileBean = new LeaveFileBean();
            leaveFileBean.setFile_path(data[i]);
            leaveFileBean.setFile_size(fileSize.get(i));
            leaveFileBean.setUp_name(userBean.getStaff_name());
            list.add(leaveFileBean);
        }
        Gson gson = new Gson();
        String content = gson.toJson(list);
        map.put("files", content);
        getPresenter().insertAskLeave(userBean.getStaff_token(), map);
    }


    @Override
    public void onInsertAskLeave(String data) {
        if (dialogLoadding != null) {
            dialogLoadding.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvType.setText("");
        tvStartTime.setText("");
        tvEndTime.setText("");
        tvAllTime.setText("");
        etReason.setText("");
        finish();
    }


    @Override
    public void onGetCheckPerson(List<MeParentBean> data) {
        for (int i = 0; i < data.size(); i++) {
            String module = data.get(i).getStaff_module();
            if (module.contains("AskLeave")) {
                String staff_give = data.get(i).getStaff_give();
                if ("1".equals(staff_give)) {
                    MeParentBean checkBean = data.get(i);
                    next_id = checkBean.getStaff_id();
                    Glide.with(context)
                            .load(Constants.BASE_URL + checkBean.getHead_img())
                            .error(R.drawable.error_img)
                            .into(circleImageVIew);
                    tvCheckPersonName.setText(checkBean.getStaff_name());
                }

            }
        }

    }


    @OnClick({R.id.ivLeft, R.id.tvType, R.id.tvStartTime, R.id.tvEndTime, R.id.ok, R.id.circleImageVIew, R.id.ivCheckDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvType:
                showSingleAlertDialog();
                break;
            case R.id.circleImageVIew:
                startChooseCheckPersonFragment("1");
                break;
            case R.id.ivCheckDelete:
                ivCheckDelete.setVisibility(View.GONE);
                tvCheckPersonName.setText("请选择");
                Glide.with(context).load(Constants.BASE_URL)
                        .error(R.drawable.check_img)
                        .into(circleImageVIew);
                next_id = "";
                break;
            case R.id.tvStartTime:
                showStartTime();
                break;
            case R.id.tvEndTime:
                showEndTime();
                break;
            case R.id.ok:
                String mtype = tvType.getText().toString().trim();
                if (TextUtils.isEmpty(mtype)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择请假类型");
                    return;
                }
                String startTime = tvStartTime.getText().toString().trim();
                if (TextUtils.isEmpty(startTime)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择开始时间");
                    return;
                }
                String endTime = tvEndTime.getText().toString().trim();
                if (TextUtils.isEmpty(endTime)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择结束时间");
                    return;
                }
                String allTime = tvAllTime.getText().toString().trim();
                if (TextUtils.isEmpty(allTime)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入时长");
                    return;
                }
                String reason = etReason.getText().toString().trim();
                if (TextUtils.isEmpty(reason)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请填写请假原因");
                    return;
                }
                if (TextUtils.isEmpty(next_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择审批人");
                    return;
                }
                ok.setEnabled(false);
                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("ask_type", mtype);
                map.put("ask_why", reason);
                map.put("start_time", startTime);
                map.put("end_time", endTime);
                map.put("next_audit_staff_id", next_id);
                map.put("all_time", allTime);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());
                map.put("operation", "0");
                if ("1".equals(type)) {
                    map.put("ask_id", mdata.getAsk_id());
                }
                if ((adapter.getAllData().size() - imgList.size()) > 1) {
                    upImage();
                } else {
                    getPresenter().insertAskLeave(userBean.getStaff_token(), map);
                }
                break;
        }
    }

    private DialogLoadding dialogLoadding;
    private AlertDialog alertDialog2;
    private int choose = 0;
    public void showSingleAlertDialog() {
        choose = 0;
        final String[] items = {"事假", "病假", "婚假", "产假", "其他"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle("选择请假类型");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose = i;
            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvType.setText(items[choose]);
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

    private void showStartTime() {
        TimePickerView build = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                date.setSeconds(0);
                sartTime = date.getTime();
                if (endTime > 0) {
                    if (sartTime >= endTime) {
                        ToastUtil.showToast(context.getApplicationContext(), "结束时间要大于开始时间");
                        tvStartTime.setText("");
                        return;
                    }
                }
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String format = formatter.format(date);
                tvStartTime.setText(format);
            }
        })
                .setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setCancelText("取消")
                .setSubmitText("确认")
                .setTitleSize(15)
                .setSubCalSize(14)
                .setTitleText("开始时间")
                .setOutSideCancelable(false)
                .isCyclic(false)//是否循环滚动
                .setTitleColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)
                .setTitleBgColor(0xFF4DA9EB)
                .setBgColor(0xFFFFFFFF)
                //.setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .isDialog(true)
                .build();
        build.show();

    }

    private void showEndTime() {
        TimePickerView build = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                date.setSeconds(0);
                endTime = date.getTime();
                if (sartTime > 0) {
                    if (endTime <= sartTime) {
                        ToastUtil.showToast(context.getApplicationContext(), "结束时间要大于开始时间");
                        tvEndTime.setText("");
                        return;
                    }
                }
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String format = formatter.format(date);
                tvEndTime.setText(format);
            }
        })
                .setType(new boolean[]{true, true, true, true, true, false})
                .setCancelText("取消")
                .setSubmitText("确认")
                .setTitleSize(15)
                .setSubCalSize(14)
                .setTitleText("结束时间")
                .setOutSideCancelable(false)
                .isCyclic(false)
                .setTitleColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)
                .setTitleBgColor(0xFF4DA9EB)
                .setBgColor(0xFFFFFFFF)
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .isDialog(true)
                .build();
        build.show();
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
                    adapter.remove(adapter.getAllData().size() - 1);
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);
                    for (BaseMedia media : medias) {
                        if (media instanceof ImageMedia) {
                            adapter.add(((ImageMedia) media).getThumbnailPath());
                        } else {
                            adapter.add(media.getPath());
                        }
                    }
                    if (adapter.getAllData().size() <= 10) {
                        adapter.add("");
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private List<String> fileSize = new ArrayList<>();
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
                    builder.addFormDataPart("path", "/images/leave");
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
    public LeavePresenter createPresenter() {
        return new LeavePresenter(getApp());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }


    private void saveData() {
        LeaveBean bean = new LeaveBean();
        bean.setAsk_type(tvType.getText().toString());
        bean.setStart_time(tvStartTime.getText().toString());
        bean.setEnd_time(tvEndTime.getText().toString());
        bean.setAll_time(tvAllTime.getText().toString());
        bean.setAsk_why(etReason.getText().toString());
        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("leave", s);
        editor.commit();
    }

    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("leave", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                tvType.setText(object.getString("ask_type"));
                tvStartTime.setText(object.getString("start_time"));
                tvEndTime.setText(object.getString("end_time"));
                tvAllTime.setText(object.getString("all_time"));
                etReason.setText(object.getString("ask_why"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveData();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
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
                next_id = bean.getStaff_id();
                break;
        }

    }

}
