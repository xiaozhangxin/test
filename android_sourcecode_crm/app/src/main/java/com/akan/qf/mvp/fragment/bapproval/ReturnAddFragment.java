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
import com.akan.qf.bean.RetnrnBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.ReturnPresenter;
import com.akan.qf.mvp.view.home.IReturnView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.util.VerificationUtil;
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
import com.king.base.util.ToastUtils;

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
 * Created by admin on 2018/11/13.
 */

public class ReturnAddFragment extends BaseFragment<IReturnView, ReturnPresenter> implements IReturnView {
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
    @BindView(R.id.tvTimeTittle)
    TextView tvTimeTittle;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvTittleOne)
    TextView tvTittleOne;
    @BindView(R.id.tvBuyTimeTittle)
    TextView tvBuyTimeTittle;
    @BindView(R.id.tvBuyTime)
    TextView tvBuyTime;
    @BindView(R.id.lineThree)
    View lineThree;
    @BindView(R.id.tvUnitTittle)
    TextView tvUnitTittle;
    @BindView(R.id.tvUnit)
    EditText tvUnit;
    @BindView(R.id.lineFour)
    View lineFour;
    @BindView(R.id.tvPhoneTittle)
    TextView tvPhoneTittle;
    @BindView(R.id.tvPhone)
    EditText tvPhone;
    @BindView(R.id.lineFive)
    View lineFive;
    @BindView(R.id.tvDepartmentTittle)
    TextView tvDepartmentTittle;
    @BindView(R.id.tvDepartment)
    EditText tvDepartment;
    @BindView(R.id.lineSix)
    View lineSix;
    @BindView(R.id.tvAllnumTittle)
    TextView tvAllnumTittle;
    @BindView(R.id.tvAllnum)
    EditText tvAllnum;
    @BindView(R.id.lineSeven)
    View lineSeven;
    @BindView(R.id.tvReturnTypeTittle)
    TextView tvReturnTypeTittle;
    @BindView(R.id.tvReturnType)
    TextView tvReturnType;
    @BindView(R.id.lineEight)
    View lineEight;
    @BindView(R.id.tvCurrentTittle)
    TextView tvCurrentTittle;
    @BindView(R.id.tvCurrent)
    TextView tvCurrent;
    @BindView(R.id.lineNine)
    View lineNine;
    @BindView(R.id.tvDescripeTittle)
    TextView tvDescripeTittle;
    @BindView(R.id.tvDescripe)
    EditText tvDescripe;
    @BindView(R.id.lineTen)
    View lineTen;
    @BindView(R.id.tvTotalTittle)
    TextView tvTotalTittle;
    @BindView(R.id.tvTotal)
    EditText tvTotal;
    @BindView(R.id.lineEleven)
    View lineEleven;
    @BindView(R.id.tvPackageNumTittle)
    TextView tvPackageNumTittle;
    @BindView(R.id.tvPackageNum)
    EditText tvPackageNum;
    @BindView(R.id.lineTwelve)
    View lineTwelve;
    @BindView(R.id.tvBoxNumTittle)
    TextView tvBoxNumTittle;
    @BindView(R.id.tvBoxNum)
    EditText tvBoxNum;
    @BindView(R.id.tvTittleTwo)
    TextView tvTittleTwo;
    @BindView(R.id.tvContentTittle)
    TextView tvContentTittle;
    @BindView(R.id.tvContent)
    EditText tvContent;
    @BindView(R.id.oneLine)
    View oneLine;
    @BindView(R.id.tvManagerTittle)
    TextView tvManagerTittle;
    @BindView(R.id.tvManager)
    EditText tvManager;
    @BindView(R.id.twoLine)
    View twoLine;
    @BindView(R.id.tvManagerTimeTittle)
    TextView tvManagerTimeTittle;
    @BindView(R.id.tvManagerTime)
    TextView tvManagerTime;
    @BindView(R.id.tvTittleThree)
    TextView tvTittleThree;
    @BindView(R.id.tvMartAuditTittle)
    TextView tvMartAuditTittle;
    @BindView(R.id.tvMartAudit)
    TextView tvMartAudit;
    @BindView(R.id.threeLine)
    View threeLine;
    @BindView(R.id.tvMartNameTittle)
    TextView tvMartNameTittle;
    @BindView(R.id.tvMartName)
    EditText tvMartName;
    @BindView(R.id.fourLline)
    View fourLline;
    @BindView(R.id.tvMartTimeTittle)
    TextView tvMartTimeTittle;
    @BindView(R.id.tvMartTime)
    TextView tvMartTime;
    @BindView(R.id.fiveLine)
    View fiveLine;
    @BindView(R.id.tvImgTittle)
    TextView tvImgTittle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.Line)
    View Line;
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
    Unbinder unbinder;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private List<String> list;
    private ImageAdapter adapter;
    private static final int IMAGE = 0X02;
    private String ask_id = "";
    private RetnrnBean data;
    private String type;
    private List<FileListBean> imgList = new ArrayList();
    private DialogLoadding dialogLoadding;
    private PermissionsBean permissionsBean;
    public static ReturnAddFragment newInstance(RetnrnBean bean, String type,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ReturnAddFragment fragment = new ReturnAddFragment();
        fragment.type = type;
        fragment.data = bean;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_return;
    }

    @Override
    public void initUI() {
        if ("0".equals(type)) {
            tvTitle.setText("新增退货申请");
            ok.setText("确认申请");
            getData();
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
            tvTitle.setText("修改退货申请");
            ok.setText("确认修改");
            imgList.addAll(data.getFileList());
            tvBuyTime.setText(data.getApply_time());
            tvUnit.setText(data.getBuy_department());
            tvPhone.setText(data.getApply_tel());
            tvDepartment.setText(data.getApply_department());
            tvAllnum.setText(data.getApply_number());
            tvReturnType.setText(data.getApply_category_show());
            tvCurrent.setText(data.getApply_status_show());
            tvDescripe.setText(data.getApply_remark());
            tvTotal.setText(data.getApply_total());
            tvPackageNum.setText(data.getBag_number());
            tvBoxNum.setText(data.getBox_number());
            tvContent.setText(data.getArea_remark());
            tvManager.setText(data.getArea_name());
            tvManagerTime.setText(data.getArea_time());
            tvMartAudit.setText(data.getMarket_status());
            tvMartName.setText(data.getMarket_name());
            tvMartTime.setText(data.getMarket_time());

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
                                getPresenter().deleteGoodsApplyFile(userBean.getStaff_token(), map);
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
        if ("0".equals(type) ){
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
        }else {
            ask_id = data.getNext_staff_id();
            Glide.with(context)
                    .load(Constants.BASE_URL + data.getNext_audit_staff_head_img())
                    .error(R.drawable.error_img)
                    .into(circleImageVIew);
            tvCheckPersonName.setText(data.getNext_audit_staff_name());
        }


    }

    @Override
    public void onUploadFiles(String[] mdata) {
        ArrayList<FileListBean> list = new ArrayList<>();
        for (int i = 0; i < mdata.length; i++) {
            FileListBean bean = new FileListBean();
            bean.setUp_url(mdata[i]);

            list.add(bean);
        }
        Gson gson = new Gson();
        String s = gson.toJson(list);
        map.put("applyFileList", s);
        if ("1".equals(type)) {
            map.put("operation", "1");
            map.put("apply_id", data.getApply_id());
            getPresenter().updateGoodsApplyList(userBean.getStaff_token(), map);
        } else {
            map.put("operation", "0");
            getPresenter().insertGoodsApply(userBean.getStaff_token(), map);
        }

    }

    @Override
    public void OndeleteGoodsApply(String data) {

    }

    @Override
    public void OnupdateGoodsApplyList(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();

    }

    @Override
    public void OndeleteGoodsApplyFile(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
    }

    @Override
    public void onGetCheckPerson(List<MeParentBean> data) {
        for (int i = 0; i < data.size(); i++) {
            String module = data.get(i).getStaff_module();
            if (module.contains("GoodsApply")) {
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

    @Override
    public void OnInsertGoodsApply(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvBuyTime.setText("");
        tvUnit.setText("");
        tvPhone.setText("");
        tvDepartment.setText("");
        tvAllnum.setText("");
        tvDescripe.setText("");
        tvTotal.setText("");
        tvPackageNum.setText("");
        tvBoxNum.setText("");
        tvManager.setText("");
        tvContent.setText("");
        tvMartName.setText("");
        finish();
    }

    @OnClick({R.id.ivLeft, R.id.tvTime, R.id.tvReturnType, R.id.tvBuyTime, R.id.tvCurrent,
            R.id.tvManagerTime, R.id.tvMartAudit, R.id.tvMartTime, R.id.circleImageVIew,
            R.id.ivCheckDelete, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvTime:
                break;
            case R.id.tvReturnType:

                showSingleAlertDialog("申请类型", new String[]{"退货", "换货",});
                break;
            case R.id.tvCurrent:

                showSingleAlertDialog("产品当前状况", new String[]{"产品全新未拆包装", "产品已使用或包装袋已拆开", "产品全新但在运输时损坏", "产品已使用，无保装", "其他"});
                break;
            case R.id.tvMartAudit:

                showSingleAlertDialog("市场部门（评定退货审批）", new String[]{"同意", "不同意",});
                break;
            case R.id.tvManagerTime:
                chooseTime("日期(区域经理)");
                break;
            case R.id.tvMartTime:
                chooseTime("日期(市场部门)");
                break;
            case R.id.tvBuyTime:
                chooseTime("订购日期");
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
                String mBuyTime = tvBuyTime.getText().toString().trim();
                if (TextUtils.isEmpty(mBuyTime)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择购买时间");
                    return;
                }

                String mtvUnit = tvUnit.getText().toString().trim();
                if (TextUtils.isEmpty(mtvUnit)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入购买单位");
                    return;
                }
                String mtvPhone = tvPhone.getText().toString().trim();
                if (TextUtils.isEmpty(mtvPhone)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请联系电话");
                    return;
                }
                if (!VerificationUtil.isPhone(mtvPhone)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入正确的手机号");
                    return;
                }
                String mtvDepartment = tvDepartment.getText().toString().trim();
                if (TextUtils.isEmpty(mtvDepartment)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入所在区域");
                    return;
                }
                String mtvAllnum = tvAllnum.getText().toString().trim();
                if (TextUtils.isEmpty(mtvAllnum)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入总件数");
                    return;
                }
                String mtvReturnType = tvReturnType.getText().toString().trim();
                if (TextUtils.isEmpty(mtvReturnType)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择类型");
                    return;
                }
                String mtvCurrent = tvCurrent.getText().toString().trim();
                if (TextUtils.isEmpty(mtvCurrent)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入状况");
                    return;
                }
                String mtvDescripe = tvDescripe.getText().toString().trim();
                if (TextUtils.isEmpty(mtvDescripe)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入详细描述");
                    return;
                }


                String mtvTotal = tvTotal.getText().toString().trim();
                if (TextUtils.isEmpty(mtvTotal)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请合计数量");
                    return;
                }
                String mtvPackageNum = tvPackageNum.getText().toString().trim();
                if (TextUtils.isEmpty(mtvPackageNum)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入管件包数");
                    return;
                }
                String mtvBoxNum = tvBoxNum.getText().toString().trim();
                if (TextUtils.isEmpty(mtvBoxNum)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入管件箱数");
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
                map.put("apply_time", mBuyTime);
                map.put("buy_department", mtvUnit);
                map.put("apply_tel", mtvPhone);
                map.put("apply_department", mtvDepartment);
                map.put("apply_number", mtvAllnum);
                switch (mtvReturnType) {
                    case "退货":
                        map.put("apply_category", "retreat");
                        break;
                    case "换货":
                        map.put("apply_category", "change");
                        break;
                }
                switch (mtvCurrent) {
                    case "其它":
                        map.put("apply_status", "other");
                        break;
                    case "产品已使用或包装袋已拆开":
                        map.put("apply_status", "split");
                        break;
                    case "产品全新但在运输时损坏":
                        map.put("apply_status", "damage");
                        break;
                    case "产品全新未拆包装":
                        map.put("apply_status", "new");
                        break;
                    case "产品已使用，无保装":
                        map.put("apply_status", "use");
                        break;
                }
                map.put("apply_remark", mtvDescripe);
                map.put("apply_total", mtvTotal);//合计
                map.put("bag_number", mtvPackageNum);//包数
                map.put("box_number", mtvBoxNum);//箱数
                map.put("area_name", tvManager.getText().toString());//区域经理
                map.put("area_remark", tvContent.getText().toString());//评定原因
                map.put("market_name", tvMartName.getText().toString());//市场负责人
                String string = tvMartAudit.getText().toString();
                if (!TextUtils.isEmpty(string)) {
                    switch (string) {
                        case "同意":
                            map.put("market_status", "agree");
                            break;
                        case "不同意":
                            map.put("market_status", "disagree");
                            break;
                    }
                }
                String areaTIme = tvManagerTime.getText().toString();
                if (!TextUtils.isEmpty(areaTIme)) {
                    map.put("area_time", areaTIme);//区域审批时间
                }
                String matkTIme = tvMartTime.getText().toString();
                if (!TextUtils.isEmpty(matkTIme)) {
                    map.put("market_time", matkTIme);//市场审批时间
                }
                map.put("next_staff_id", ask_id);//负责人评定
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());


                if ((adapter.getAllData().size() - imgList.size()) > 1) {
                    upImage();
                } else {
                    if ("1".equals(type)) {
                        map.put("operation", "1");
                        map.put("apply_id", data.getApply_id());
                        getPresenter().updateGoodsApplyList(userBean.getStaff_token(), map);
                    } else {
                        map.put("operation", "0");
                        getPresenter().insertGoodsApply(userBean.getStaff_token(), map);
                    }
                }

                break;
        }
    }

    private void chooseTime(final String type) {

        TimePickerView build = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String format = formatter.format(date);
                switch (type) {
                    case "日期(区域经理)":
                        tvManagerTime.setText(format);
                        break;
                    case "日期(市场部门)":
                        tvMartTime.setText(format);
                        break;
                    case "订购日期":
                        tvBuyTime.setText(format);
                        break;
                }

            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setCancelText("取消")
                .setSubmitText("确认")
                .setTitleSize(15)
                .setSubCalSize(14)
                .setTitleText(type)
                .setOutSideCancelable(false)
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)
                .setTitleBgColor(0xFF4DA9EB)
                .setBgColor(0xFFFFFFFF)
                //.setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                //.setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .isDialog(true)//是否显示为对话框样式
                .build();
        build.show();

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
                switch (tittle) {

                    case "申请类型":
                        tvReturnType.setText(items[choose]);
                        break;
                    case "产品当前状况":
                        tvCurrent.setText(items[choose]);
                        break;
                    case "市场部门（评定退货审批）":
                        tvMartAudit.setText(items[choose]);
                        break;

                }

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

    List<String> fileSize = new ArrayList<>();
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
                    builder.addFormDataPart("path", "/images/return");
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
    public ReturnPresenter createPresenter() {
        return new ReturnPresenter(getApp());
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
        RetnrnBean bean = new RetnrnBean();

        bean.setApply_time(tvBuyTime.getText().toString());
        bean.setBuy_department(tvUnit.getText().toString());
        bean.setApply_tel(tvPhone.getText().toString());
        bean.setApply_department(tvDepartment.getText().toString());
        bean.setApply_number(tvAllnum.getText().toString());
        bean.setApply_remark(tvDescripe.getText().toString());
        bean.setApply_total(tvTotal.getText().toString());
        bean.setBag_number(tvPackageNum.getText().toString());
        bean.setBox_number(tvBoxNum.getText().toString());
        bean.setArea_name(tvManager.getText().toString());
        bean.setApply_remark(tvContent.getText().toString());
        bean.setArea_name(tvMartName.getText().toString());

        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("return", s);
        editor.commit();
    }
    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("return", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);

                tvBuyTime.setText(object.getString("apply_time"));
                tvUnit.setText(object.getString("buy_department"));
                tvPhone.setText(object.getString("apply_tel"));
                tvDepartment.setText(object.getString("apply_department"));
                tvAllnum.setText(object.getString("apply_number"));
                tvDescripe.setText(object.getString("apply_remark"));
                tvTotal.setText(object.getString("apply_total"));
                tvPackageNum.setText(object.getString("bag_number"));
                tvBoxNum.setText(object.getString("box_number"));
                tvManager.setText(object.getString("area_name"));
                tvContent.setText(object.getString("area_remark"));
                tvMartName.setText(object.getString("market_name"));
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


    @Override
    public void OnGetGoodsApplyList(List<RetnrnBean> data, String total) {

    }

    @Override
    public void OnGetGoodsApply(RetnrnBean data) {

    }

    @Override
    public void OnAuditGoodsApply(String data) {

    }

}