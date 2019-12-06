package com.akan.qf.mvp.fragment.bapproval;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.akan.qf.bean.PayApplyBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.PayRequestPresenter;
import com.akan.qf.mvp.view.home.IPayRequestView;
import com.akan.qf.util.CashierInputFilter;
import com.akan.qf.util.MoneyUtil;
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
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
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

public class PayRequestAddFragment extends BaseFragment<IPayRequestView, PayRequestPresenter> implements IPayRequestView {

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
    @BindView(R.id.lineTwo)
    View lineTwo;
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
    @BindView(R.id.lineFive)
    View lineFive;
    @BindView(R.id.tvPriceTextTittle)
    TextView tvPriceTextTittle;
    @BindView(R.id.tvPriceText)
    EditText tvPriceText;
    @BindView(R.id.lineSix)
    View lineSix;
    @BindView(R.id.tvPriceTypeTittle)
    TextView tvPriceTypeTittle;
    @BindView(R.id.tvPriceType)
    TextView tvPriceType;
    @BindView(R.id.lineEight)
    View lineEight;
    @BindView(R.id.tvPriceStyleTittle)
    TextView tvPriceStyleTittle;
    @BindView(R.id.tvPriceStyle)
    TextView tvPriceStyle;
    @BindView(R.id.lineNine)
    View lineNine;
    @BindView(R.id.tvBankNumTittle)
    TextView tvBankNumTittle;
    @BindView(R.id.tvBankNum)
    EditText tvBankNum;
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
    @BindView(R.id.oneLineAdd)
    View oneLineAdd;
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
    private String ask_id = "";
    private List<String> list;
    private ImageAdapter adapter;
    private static final int IMAGE = 0X02;
    private PayApplyBean data;
    private String type;
    private List<FileListBean> imgList = new ArrayList();
    private DialogLoadding dialogLoadding;
    private PermissionsBean permissionsBean;

    public static PayRequestAddFragment newInstance(PayApplyBean bean, String type, PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        PayRequestAddFragment fragment = new PayRequestAddFragment();
        fragment.type = type;
        fragment.data = bean;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_request_add;
    }

    @Override
    public void initUI() {
        InputFilter[] filters = {new CashierInputFilter(2)};
        tvPriceNum.setFilters(filters);
        tvPriceNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (".".equals(s.toString()) | TextUtils.isEmpty(s.toString())) {
                    tvPriceText.setText("");
                } else {
                    BigDecimal numberOfMoney = new BigDecimal(s.toString());
                    String s1 = MoneyUtil.number2CNMontrayUnit(numberOfMoney);
                    tvPriceText.setText(s1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if ("0".equals(type)) {
            getData();
            tvTitle.setText("新增付款申请");
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
            tvTitle.setText("修改付款申请");
            ok.setText("确认修改");
            imgList.addAll(data.getFileList());
            tvDepartment.setText(data.getApply_department());
            tvRemake.setText(data.getApply_remark());
            tvContent.setText(data.getApply_use());
            tvPriceNum.setText(data.getApply_coin());
            tvPriceText.setText(data.getApply_up());
            tvPriceType.setText(data.getApply_category_show());
            tvPriceStyle.setText(data.getApply_way());
            tvBankNum.setText(data.getApply_bank_no());
            tvBank.setText(data.getApply_bank());
            tvPayee.setText(data.getApply_accept());


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
                                getPresenter().deletePayApplyFile(userBean.getStaff_token(), map);
                                imgList.remove(position);
                                adapter.remove(position);

                            }
                        });
                        builder.setNegativeButton("取消", null);
                        AlertDialog alertDialog = builder.create();

                        alertDialog.show();
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        }

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        tvDepartment.setText(userBean.getSimple_department_name());
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

            ask_id = data.getNext_audit_staff_id();
            Glide.with(context)
                    .load(Constants.BASE_URL + data.getNext_audit_staff_head_img())
                    .error(R.drawable.error_img)
                    .into(circleImageVIew);
            tvCheckPersonName.setText(data.getNext_audit_staff_name());
        }

    }

    @Override
    public void OnInsertPayApply(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvContent.setText("");
        tvPriceNum.setText("");
        tvPriceText.setText("");
        tvBankNum.setText("");
        tvBank.setText("");
        tvPayee.setText("");
        tvRemake.setText("");
        finish();

    }

    @Override
    public void OnGetPayApplyList(List<PayApplyBean> data, String total) {

    }

    @Override
    public void OnGetPayApply(PayApplyBean data) {

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
        map.put("payFileList", s);
        if ("1".equals(type)) {
            map.put("operation", "1");
            map.put("apply_id", data.getApply_id());
            getPresenter().updatePayApply(userBean.getStaff_token(), map);
        } else {
            map.put("operation", "0");
            getPresenter().insertPayApply(userBean.getStaff_token(), map);
        }
    }

    @Override
    public void OnAuditPayApply(String data) {

    }

    @Override
    public void OnDeletePayApply(String data) {

    }

    @Override
    public void OnDeletePayApplyFile(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
    }

    @Override
    public void OnUpdatePayApply(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();

    }

    @Override
    public void onGetCheckPerson(List<MeParentBean> data) {
        for (int i = 0; i < data.size(); i++) {
            String module = data.get(i).getStaff_module();
            if (module.contains("PayApply")) {
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


    @OnClick({R.id.ivLeft, R.id.tvTime, R.id.tvPriceType, R.id.tvPriceStyle, R.id.circleImageVIew, R.id.ivCheckDelete, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvTime:
                break;

            case R.id.tvPriceType:
                showSingleAlertDialog("选择费用类型", new String[]{"办公费用", "广告费用"});

                break;
            case R.id.tvPriceStyle:
                showSingleAlertDialog("选择付款方式", new String[]{"现金", "电汇", "支票", "其他"});
                break;
            case R.id.circleImageVIew:
                startChooseCheckPersonFragment("1");
                break;
            case R.id.ivCheckDelete:
                ivCheckDelete.setVisibility(View.GONE);
                tvCheckPersonName.setText("请选择");
                ask_id = "";
                Glide.with(context).load("")
                        .error(R.drawable.check_img)
                        .into(circleImageVIew);
                break;
            case R.id.ok:
                String mDepartment = tvDepartment.getText().toString().trim();
                if (TextUtils.isEmpty(mDepartment)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请填写部门");
                    return;
                }
                String mPriceNum = tvPriceNum.getText().toString().trim();
                if (TextUtils.isEmpty(mPriceNum)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入金额");
                    return;
                }
                String mPriceText = tvPriceText.getText().toString().trim();
                if (TextUtils.isEmpty(mPriceText)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入金额大写");
                    return;
                }
                String mPriceType = tvPriceType.getText().toString().trim();
                if (TextUtils.isEmpty(mPriceType)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择费用类型");
                    return;
                }
                String mPriceStyle = tvPriceStyle.getText().toString().trim();
                if (TextUtils.isEmpty(mPriceStyle)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择费用方式");
                    return;
                }
                String mBankNum = tvBankNum.getText().toString().trim();
                if (TextUtils.isEmpty(mBankNum)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入银行账号");
                    return;
                }
                String mBank = tvBank.getText().toString().trim();
                if (TextUtils.isEmpty(mBank)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入开户行");
                    return;
                }
                String mPayee = tvPayee.getText().toString().trim();
                if (TextUtils.isEmpty(mPayee)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入收款人");
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
                map.put("next_audit_staff_id", ask_id);
                map.put("apply_coin", mPriceNum);
                map.put("apply_use", tvContent.getText().toString());
                map.put("apply_up", mPriceText);
                map.put("apply_remark", tvRemake.getText().toString());
                switch (mPriceType) {
                    case "办公费用":
                        map.put("apply_category", "offic");
                        break;
                    case "广告费用":
                        map.put("apply_category", "advert");
                        break;
                }
                switch (mPriceStyle) {
                    case "现金":
                        map.put("apply_way", "cash");
                        break;
                    case "电汇":
                        map.put("apply_way", "telegraph");
                        break;
                    case "支票":
                        map.put("apply_way", "cheque");
                        break;
                    case "其他":
                        map.put("apply_way", "other");
                        break;
                }

                map.put("apply_bank_no", mBankNum);
                map.put("apply_bank", mBank);
                map.put("apply_accept", mPayee);
                map.put("apply_accept_department", mDepartment);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());

                if ((adapter.getAllData().size() - imgList.size()) > 1) {
                    upImage();
                } else {
                    if ("1".equals(type)) {
                        map.put("operation", "1");
                        map.put("apply_id", data.getApply_id());
                        getPresenter().updatePayApply(userBean.getStaff_token(), map);
                    } else {
                        map.put("operation", "0");
                        getPresenter().insertPayApply(userBean.getStaff_token(), map);
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
                switch (tittle) {
                    case "选择费用类型":
                        tvPriceType.setText(items[choose]);
                        break;
                    case "选择付款方式":
                        tvPriceStyle.setText(items[choose]);
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
                    if (adapter.getAllData().size() <= 10) {
                        adapter.add("");
                    }
                    adapter.notifyDataSetChanged();

                }
                break;
        }
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
        PayApplyBean bean = new PayApplyBean();
        bean.setApply_use(tvContent.getText().toString());
        bean.setApply_coin(tvPriceNum.getText().toString());
        bean.setApply_up(tvPriceText.getText().toString());
        bean.setApply_bank_no(tvBankNum.getText().toString());
        bean.setApply_bank(tvBank.getText().toString());
        bean.setApply_accept(tvPayee.getText().toString());
        bean.setApply_remark(tvRemake.getText().toString());
        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("payRequest", s);
        editor.commit();
    }

    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("payRequest", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                tvContent.setText(object.getString("apply_use"));
                tvPriceNum.setText(object.getString("apply_coin"));
                tvPriceText.setText(object.getString("apply_up"));
                tvBankNum.setText(object.getString("apply_bank_no"));
                tvBank.setText(object.getString("apply_bank"));
                tvPayee.setText(object.getString("apply_accept"));
                tvRemake.setText(object.getString("apply_remark"));

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
    public PayRequestPresenter createPresenter() {
        return new PayRequestPresenter(getApp());
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


}
