package com.akan.qf.mvp.fragment.bapproval;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.akan.qf.bean.AddReburseBean;
import com.akan.qf.bean.FileListBean;
import com.akan.qf.bean.FirstEventNew;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.ReimbursementInfoBean;
import com.akan.qf.bean.TextListBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageAdapter;
import com.akan.qf.mvp.adapter.home.AddReimburseAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.AddReimbursePresenter;
import com.akan.qf.mvp.view.home.IAddReimburseView;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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

public class ReimburseAddFragment extends BaseFragment<IAddReimburseView, AddReimbursePresenter> implements IAddReimburseView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.topRecycleView)
    RecyclerView topRecycleView;
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
    @BindView(R.id.ivCheckDelete)
    ImageView ivCheckDelete;
    @BindView(R.id.tvCheckPersonName)
    TextView tvCheckPersonName;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;

    @BindView(R.id.tvTotalMoenyTittle)
    TextView tvTotalMoenyTittle;
    @BindView(R.id.tvTotalMoeny)
    TextView tvTotalMoeny;
    @BindView(R.id.lineTwo)
    View lineTwo;
    @BindView(R.id.tvTimeTittle)
    TextView tvTimeTittle;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.lineThree)
    View lineThree;
    @BindView(R.id.tvRemakeTittle)
    TextView tvRemakeTittle;
    @BindView(R.id.tvRemake)
    EditText tvRemake;
    @BindView(R.id.lineFour)
    View lineFour;
    @BindView(R.id.tvAdd)
    TextView tvAdd;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private DialogLoadding dialogLoadding;
    private List<String> list;
    private List<TextListBean> typeList;
    private ImageAdapter adapter;
    private AddReimburseAdapter addAdapter;
    private static final int IMAGE = 0X02;
    private String ask_id = "";
    private ReimbursementInfoBean data;
    private String type;
    private List<FileListBean> imgList = new ArrayList();

    private PermissionsBean permissionsBean;
    public static ReimburseAddFragment newInstance(ReimbursementInfoBean bean, String type,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ReimburseAddFragment fragment = new ReimburseAddFragment();
        fragment.type = type;
        fragment.data = bean;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_reimburse;
    }

    @Override
    public void initUI() {
        DecimalFormat df = new DecimalFormat("#0");
        typeList = new ArrayList<>();
        if ("0".equals(type)) {
            getData();
            tvTitle.setText("新增报销申请");
            ok.setText("确认申请");
            list = new ArrayList<>();
            list.add("");
            adapter = new ImageAdapter(context, list);
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
            tvTitle.setText("修改报销申请");
            ok.setText("确认修改");
            imgList.addAll(data.getFileList());
            tvTime.setText(data.getInfo_create_time());
            tvRemake.setText(data.getInfo_remarks());

            list = new ArrayList<>();
            List<FileListBean> fileList = data.getFileList();
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
                                getPresenter().deleteExpenseReimbursementFile(userBean.getStaff_token(), map);
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


        if ("1".equals(type)) {
            tvTime.setText(data.getInfo_reimbursement_time());
            tvTotalMoeny.setText(data.getInfo_price());
            typeList.addAll(data.getTextList());
        } else {
            if (typeList.size()<=0){
                typeList.add(new TextListBean("","","1",""));
            }

        }


        addAdapter = new AddReimburseAdapter(context, typeList);
        topRecycleView.setNestedScrollingEnabled(false);
        topRecycleView.setLayoutManager(new LinearLayoutManager(context));
        topRecycleView.setAdapter(addAdapter);
        addAdapter.setHideDelete(false);
        topRecycleView.setItemAnimator(new DefaultItemAnimator());
        addAdapter.setOnDeleteClick(new AddReimburseAdapter.OnAddorReduceClick() {
            @Override
            public void onDeleteClick(final int data) {

                final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                builder.setMessage("你确定要删除报销单明细(" + (data + 1) + ")?");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        addAdapter.remove(data);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.onCreate().show();

            }

            @Override
            public void onSubPriceClick() {
                List<TextListBean> allData = addAdapter.getAllData();
                double totalPrice = 0;
                for (int i = 0; i < allData.size(); i++) {
                    if (!TextUtils.isEmpty(allData.get(i).getText_price())) {
                        totalPrice = totalPrice + Double.parseDouble(allData.get(i).getText_price());
                    }
                }
                BigDecimal bg3 = new BigDecimal(totalPrice);
                double f3 = bg3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                tvTotalMoeny.setText(f3 + "");
            }

        });


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if ("0".equals(type)) {
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

            ask_id = data.getInfo_appoint_audit();
            Glide.with(context)
                    .load(Constants.BASE_URL + data.getNext_audit_staff_head_img())
                    .error(R.drawable.error_img)
                    .into(circleImageVIew);
            tvCheckPersonName.setText(data.getNext_audit_staff_name());
        }
    }

    @Override
    public void onInsertExpenseReimbursement(String data) {
        tvTime.setText("");
        tvRemake.setText("");
        addAdapter.clear();
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onUploadFiles(String[] mdata) {
        String mTvTime = tvTime.getText().toString();
        String mTvTotalMoney = tvTotalMoeny.getText().toString();
        String mTvRemake = tvRemake.getText().toString();
        if ("0".equals(type)) {
            AddReburseBean addReburseBean = new AddReburseBean();
            addReburseBean.setInfo_appoint_audit(ask_id);
            addReburseBean.setInfo_name(userBean.getStaff_name());
            addReburseBean.setInfo_remarks(mTvRemake);
            addReburseBean.setInfo_price(mTvTotalMoney);
            addReburseBean.setInfo_department(userBean.getDepartment_name());
            addReburseBean.setInfo_reimbursement_time(mTvTime);
            ArrayList<FileListBean> list = new ArrayList<>();
            for (int i = 0; i < mdata.length; i++) {
                FileListBean fileBean = new FileListBean();
                fileBean.setFile_url(mdata[i]);
                fileBean.setFile_size(fileSize.get(i));
                list.add(fileBean);
            }
            addReburseBean.setFileList(list);
            List<TextListBean> allData = addAdapter.getAllData();
            ArrayList<TextListBean> listText = new ArrayList<>();
            for (int j = 0; j < allData.size(); j++) {
                TextListBean textListBean = new TextListBean("","","1","");
                textListBean.setText_info(allData.get(j).getText_info());
                textListBean.setText_number(allData.get(j).getText_number());
                textListBean.setText_price(allData.get(j).getText_price());
                textListBean.setText_subject(allData.get(j).getText_subject());
                listText.add(textListBean);
            }
            addReburseBean.setTextList(listText);
            Gson gson = new Gson();
            String s = gson.toJson(addReburseBean);
            map.clear();
            map.put("staff_id", userBean.getStaff_id());
            map.put("expenseReimbursementInfoBean", s);
            map.put("is_select", "0");
            map.put("is_app", "1");
            map.put("module_id", permissionsBean.getMenu_id());
            map.put("operation", "0");
            getPresenter().insertExpenseReimbursement(userBean.getStaff_token(), map);
        } else {

            AddReburseBean addReburseBean = new AddReburseBean();
            addReburseBean.setInfo_appoint_audit(ask_id);
            addReburseBean.setInfo_id(data.getInfo_id());
            addReburseBean.setInfo_remarks(mTvRemake);
            addReburseBean.setInfo_price(mTvTotalMoney);
            addReburseBean.setInfo_reimbursement_time(mTvTime);
            addReburseBean.setInfo_name(userBean.getStaff_name());
            addReburseBean.setInfo_department(userBean.getDepartment_name());
            ArrayList<FileListBean> list = new ArrayList<>();
            for (int i = 0; i < mdata.length; i++) {
                FileListBean fileBean = new FileListBean();
                fileBean.setFile_url(mdata[i]);
                fileBean.setFile_size(fileSize.get(i));
                list.add(fileBean);
            }
            addReburseBean.setFileList(list);
            List<TextListBean> allData = addAdapter.getAllData();
            ArrayList<TextListBean> listText = new ArrayList<>();
            for (int j = 0; j < allData.size(); j++) {
                TextListBean textListBean = new TextListBean("","","1","");
                textListBean.setText_info(allData.get(j).getText_info());
                textListBean.setText_number(allData.get(j).getText_number());
                textListBean.setText_price(allData.get(j).getText_price());
                textListBean.setText_subject(allData.get(j).getText_subject());
                listText.add(textListBean);
            }
            addReburseBean.setTextList(listText);
            Gson gson = new Gson();
            String s = gson.toJson(addReburseBean);
            map.clear();
            map.put("staff_id", userBean.getStaff_id());
            map.put("expenseReimbursementInfoBean", s);
            map.put("is_select", "0");
            map.put("is_app", "1");
            map.put("module_id", permissionsBean.getMenu_id());
            map.put("operation", "1");
            getPresenter().updateExpenseReimbursement(userBean.getStaff_token(), map);
        }
    }

    @Override
    public void onDeleteExpenseReimbursementFile(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);

    }

    @Override
    public void onUpdateExpenseReimbursement(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();

    }

    @Override
    public void onGetCheckPerson(List<MeParentBean> data) {
        for (int i = 0; i < data.size(); i++) {
            String module = data.get(i).getStaff_module();
            if (module.contains("ExpenseReimbursement")) {
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


    @OnClick({R.id.ivLeft, R.id.ok, R.id.circleImageVIew, R.id.ivCheckDelete, R.id.tvAdd, R.id.tvTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvTime:
                showStartTime();
                break;
            case R.id.tvAdd:
                addAdapter.add(new TextListBean("","","1",""));
              //  addAdapter.notify();
//                if (addAdapter.getAllData().size() <= 1) {
//                    addAdapter.setHideDelete(true);
//                } else {
//                    addAdapter.setHideDelete(false);
//                }
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
                ask_id = "";
                break;
            case R.id.ok:
                String mTvTime = tvTime.getText().toString();
                if (TextUtils.isEmpty(mTvTime)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择报销日期");
                    return;
                }
               // addAdapter.notifyDataSetChanged();
                List<TextListBean> allData = addAdapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {

                    if (TextUtils.isEmpty(allData.get(i).getText_subject())) {
                        ToastUtil.showToast(context.getApplicationContext(), "科目不能为空");
                        return;
                    }
                    if (TextUtils.isEmpty(allData.get(i).getText_price())) {
                        ToastUtil.showToast(context.getApplicationContext(), "报销金额不能为空");
                        return;
                    }
                    if (TextUtils.isEmpty(allData.get(i).getText_info())) {
                        ToastUtil.showToast(context.getApplicationContext(), "摘要不能为空");
                        return;
                    }
                }

                if (TextUtils.isEmpty(ask_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择审批人");
                    return;
                }
                ok.setEnabled(false);

                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                String mTvTotalMoney = tvTotalMoeny.getText().toString();
                String mTvRemake = tvRemake.getText().toString();
                if ((adapter.getAllData().size() - imgList.size()) > 1) {
                    upImage();
                } else {
                    if ("1".equals(type)) {
                        AddReburseBean addReburseBean = new AddReburseBean();
                        addReburseBean.setInfo_appoint_audit(ask_id);
                        addReburseBean.setInfo_id(data.getInfo_id());
                        addReburseBean.setInfo_remarks(mTvRemake);
                        addReburseBean.setInfo_reimbursement_time(mTvTime);
                        addReburseBean.setInfo_name(userBean.getStaff_name());
                        addReburseBean.setInfo_price(mTvTotalMoney);
                        addReburseBean.setInfo_department(userBean.getDepartment_name());
                        ArrayList<TextListBean> list = new ArrayList<>();
                        for (int j = 0; j < allData.size(); j++) {
                            TextListBean textListBean = new TextListBean("","","1","");
                            textListBean.setText_info(allData.get(j).getText_info());
                            textListBean.setText_number(allData.get(j).getText_number());
                            textListBean.setText_price(allData.get(j).getText_price());
                            textListBean.setText_subject(allData.get(j).getText_subject());
                            list.add(textListBean);
                        }
                        addReburseBean.setTextList(list);
                        Gson gson = new Gson();
                        String s = gson.toJson(addReburseBean);
                        map.clear();
                        map.put("staff_id", userBean.getStaff_id());
                        map.put("expenseReimbursementInfoBean", s);
                        map.put("is_select", "0");
                        map.put("is_app", "1");
                        map.put("module_id", permissionsBean.getMenu_id());
                        map.put("operation", "1");
                        getPresenter().updateExpenseReimbursement(userBean.getStaff_token(), map);
                    } else {
                        AddReburseBean addReburseBean = new AddReburseBean();
                        addReburseBean.setInfo_appoint_audit(ask_id);
                        addReburseBean.setInfo_name(userBean.getStaff_name());
                        addReburseBean.setInfo_remarks(mTvRemake);
                        addReburseBean.setInfo_price(mTvTotalMoney);
                        addReburseBean.setInfo_department(userBean.getDepartment_name());
                        addReburseBean.setInfo_reimbursement_time(mTvTime);
                        ArrayList<TextListBean> list = new ArrayList<>();
                        for (int j = 0; j < allData.size(); j++) {
                            TextListBean textListBean = new TextListBean("","","1","");
                            textListBean.setText_info(allData.get(j).getText_info());
                            textListBean.setText_number(allData.get(j).getText_number());
                            textListBean.setText_price(allData.get(j).getText_price());
                            textListBean.setText_subject(allData.get(j).getText_subject());
                            list.add(textListBean);
                        }
                        addReburseBean.setTextList(list);
                        Gson gson = new Gson();
                        String s = gson.toJson(addReburseBean);
                        map.clear();
                        map.put("staff_id", userBean.getStaff_id());
                        map.put("expenseReimbursementInfoBean", s);
                        map.put("is_select", "0");
                        map.put("is_app", "1");
                        map.put("module_id", permissionsBean.getMenu_id());
                        map.put("operation", "0");
                        getPresenter().insertExpenseReimbursement(userBean.getStaff_token(), map);
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
                tvTime.setText(format);
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setTitleSize(15)//标题文字大小
                .setSubCalSize(14)
                .setTitleText("选择时间")//标题文字
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
                    builder.addFormDataPart("path", "/images/reimburse");
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
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());
        ok.setEnabled(true);
    }

    @Override
    public AddReimbursePresenter createPresenter() {
        return new AddReimbursePresenter(getApp());
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
        ReimbursementInfoBean bean = new ReimbursementInfoBean();

        bean.setInfo_reimbursement_time(tvTime.getText().toString());
        bean.setInfo_remarks(tvRemake.getText().toString());
       List<TextListBean> allData = addAdapter.getAllData();
        ArrayList<TextListBean> list = new ArrayList<>();
        for (int j = 0; j < allData.size(); j++) {
            TextListBean textListBean = new TextListBean("","","1","");
            textListBean.setText_info(allData.get(j).getText_info());
            textListBean.setText_number(allData.get(j).getText_number());
            textListBean.setText_price(allData.get(j).getText_price());
            textListBean.setText_subject(allData.get(j).getText_subject());
            list.add(textListBean);
        }
        bean.setTextList(list);
        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("reimburse", s);
        editor.commit();
    }
    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("reimburse", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                tvTime.setText(object.getString("info_reimbursement_time"));
                tvRemake.setText(object.getString("info_remarks"));
                String textList = object.getString("textList");
                Gson gson = new Gson();
                JsonArray array = new JsonParser().parse(textList).getAsJsonArray();
                for (JsonElement elem : array) {
                    TextListBean usern = gson.fromJson(elem, TextListBean.class);
                    typeList.add(usern);
                }
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


}
