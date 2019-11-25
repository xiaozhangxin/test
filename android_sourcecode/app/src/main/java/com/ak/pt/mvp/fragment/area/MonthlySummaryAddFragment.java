package com.ak.pt.mvp.fragment.area;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.BookNameBean;
import com.ak.pt.bean.FirstEventBook;
import com.ak.pt.bean.FixFileBean;
import com.ak.pt.bean.MonthTotalBean;
import com.ak.pt.bean.SummaryInfoBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.area.TableListAdapter;
import com.ak.pt.mvp.adapter.water.ImageFixAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.area.MonthlySummaryPresenter;
import com.ak.pt.mvp.view.area.IMonthlySummaryView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.ak.pt.view.DialogLoading;
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
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

/**
 * Created by admin on 2019/5/27.
 */

public class MonthlySummaryAddFragment extends BaseFragment<IMonthlySummaryView, MonthlySummaryPresenter> implements IMonthlySummaryView {


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
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.recycleViewTable)
    RecyclerView recycleViewTable;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.ok)
    Button ok;

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private DialogLoading dialogLoading;
    private List<FixFileBean> list;
    private ImageFixAdapter adapter;
    private ArrayList<SummaryInfoBean> tableList;
    private TableListAdapter tableAdapter;
    private static final int IMAGE = 0X02;
    private String type;
    private MonthTotalBean data;

    private int departmentPosition = 0;
    private AppPermissionsBean permissionsBean;

    public static MonthlySummaryAddFragment newInstance(MonthTotalBean bean, String type, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        MonthlySummaryAddFragment fragment = new MonthlySummaryAddFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.data = bean;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_month_snmmary;
    }

    @Override
    public void initUI() {
        list = new ArrayList<>();
        tableList = new ArrayList<>();
        fileList = new ArrayList<>();
        if ("0".equals(type)) {
            ok.setText(R.string.commit);
            tvTitle.setText(R.string.summary_add);
            tableList.add(new SummaryInfoBean());
        } else {
            tvTitle.setText(R.string.summary_update);
            ok.setText(R.string.update_sure);

            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getCreate_time());
            tvDepartment.setText(data.getDepartment_name());
            tvOne.setText(data.getYear());
            tvTwo.setText(data.getMonth());

            if (data.getInfoList().size() > 0) {
                tableList.addAll(data.getInfoList());
            } else {
                tableList.add(new SummaryInfoBean());
            }
            list.addAll(data.getFileList());
        }


        recycleViewTable.setLayoutManager(new LinearLayoutManager(context));
        tableAdapter = new TableListAdapter(context, tableList);
        recycleViewTable.setNestedScrollingEnabled(false);
        recycleViewTable.setAdapter(tableAdapter);
        tableAdapter.setOnDeleteClick(new TableListAdapter.OnDeleteClick() {
            @Override
            public void onDeleteClick(final int position) {
                deleteTable(position);

            }
            @Override
            public void onChoosDepartment(int data) {
                departmentPosition = data;
                startDepartmentPermissionFragment(permissionsBean.getMenu_id(), "summary");
            }
        });

        list.add(new FixFileBean(""));
        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        adapter = new ImageFixAdapter(context, list);
        recycleView.setNestedScrollingEnabled(false);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapter.getAllData().size() <= 5) {
                    show_img(IMAGE);
                } else {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.max_five_img));
                }
            }
        });
        adapter.setOnDeleteClick(new ImageFixAdapter.OnDeleteClick() {
            @Override
            public void onDeleteClick(final int position) {
                final String file_id = adapter.getItem(position).getFile_id();
                if (TextUtils.isEmpty(file_id)) {
                    adapter.remove(position);
                } else {
                    onDeleteFile(file_id, position);

                }
            }


        });

    }

    //删除文件
    private void onDeleteFile(final String file_id, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.detete_file);
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("file_id", file_id);
                getPresenter().deleteMonthTotalFile(userBean.getStaff_token(), map);
                adapter.remove(position);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //删除数量表
    private void deleteTable(final int position) {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage(getString(R.string.sure_delete_month_table) + (position + 1) + "?");
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                tableAdapter.remove(position);
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

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        switch (type) {
            case "0":
                tvName.setText(userBean.getStaff_name());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                Date curDate = new Date(System.currentTimeMillis());
                String str = formatter.format(curDate);
                tvTime.setText(str);
                tvDepartment.setText(userBean.getSimple_department_name());
                break;
        }
    }


    @Override
    public void oninsertMonthTotal(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void ondeleteMonthTotal(String data) {

    }

    @Override
    public void ondeleteMonthTotalFile(String data) {

    }

    @Override
    public void onupdateMonthTotal(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onauditMonthTotal(String data) {

    }

    @Override
    public void OngetMonthTotalList(List<MonthTotalBean> data) {

    }

    @Override
    public void ongetMonthTotalDetail(MonthTotalBean data) {

    }

    private ArrayList<FixFileBean> fileList;

    @Override
    public void onUploadFiles(String[] data) {

        for (int i = 0; i < data.length; i++) {
            fileList.get(i).setFile_url(data[i]);
        }
        String toJson = new Gson().toJson(fileList);
        if ("0".equals(type)) {
            map.put("monthList", toJson);
            getPresenter().insertMonthTotal(userBean.getStaff_token(), map);
        } else {
            map.put("monthList", toJson);
            getPresenter().updateMonthTotal(userBean.getStaff_token(), map);
        }

    }


    @OnClick({R.id.ivLeft, R.id.ok, R.id.tvOne, R.id.tvTwo, R.id.tvAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvAdd:
                tableAdapter.add(new SummaryInfoBean());
                break;
            case R.id.tvOne:
                chooseTimeOne("年份");
                break;
            case R.id.tvTwo:
                chooseTimeTwo("月份");
                break;
            case R.id.ok:
                ok.setEnabled(false);
                if (dialogLoading != null) {
                    dialogLoading.showDialog();
                } else {
                    dialogLoading = new DialogLoading(context);
                    dialogLoading.showDialog();
                }
                map.clear();
                if (!TextUtils.isEmpty(tvOne.getText().toString())) {
                    map.put("year", tvOne.getText().toString());
                }
                if (!TextUtils.isEmpty(tvTwo.getText().toString())) {
                    map.put("month", tvTwo.getText().toString());
                }


                List<SummaryInfoBean> infoList = tableAdapter.getAllData();
                Gson gson = new Gson();
                String toJson = gson.toJson(infoList);
                map.put("infoBeans", toJson);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());
                fileList.clear();
                List<FixFileBean> allData = adapter.getAllData();
                switch (type) {
                    case "0"://新建
                        map.put("operation", "0");
                        for (FixFileBean s : allData) {
                            if (!TextUtils.isEmpty(s.getFile_url())) {
                                fileList.add(new FixFileBean(s.getFile_url()));
                            }
                        }
                        if (fileList.size() > 0) {
                            upImage(fileList);
                        } else {
                            getPresenter().insertMonthTotal(userBean.getStaff_token(), map);
                        }
                        break;
                    case "1"://编辑
                        map.put("operation", "1");
                        map.put("month_id", data.getMonth_id());
                        for (FixFileBean s : allData) {
                            if (TextUtils.isEmpty(s.getFile_id()) && !TextUtils.isEmpty(s.getFile_url())) {
                                fileList.add(new FixFileBean(s.getFile_url()));
                            }
                        }
                        if (fileList.size() > 0) {
                            upImage(fileList);
                        } else {
                            getPresenter().updateMonthTotal(userBean.getStaff_token(), map);
                        }
                }
                break;


        }
    }


    private void chooseTimeOne(final String type) {
        TimePickerView build = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
                String format = formatter.format(date);
                switch (type) {
                    case "年份":
                        tvOne.setText(format);
                        break;
                }
            }
        }).setType(new boolean[]{true, false, false, false, false, false})
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
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .isDialog(true)//是否显示为对话框样式
                .build();
        build.show();

    }

    private void chooseTimeTwo(final String type) {
        TimePickerView build = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM");
                String format = formatter.format(date);
                switch (type) {
                    case "月份":
                        tvTwo.setText(format);
                        break;
                }
            }
        }).setType(new boolean[]{false, true, false, false, false, false})
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
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .isDialog(true)//是否显示为对话框样式
                .build();
        build.show();

    }


    //-----------------------------------------------上传报备图片------------------------------------------------------//

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
                    //adapter.clear();
                    adapter.remove(adapter.getAllData().size() - 1);
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);

                    for (BaseMedia media : medias) {
                        if (media instanceof ImageMedia) {
                            adapter.add(new FixFileBean(((ImageMedia) media).getThumbnailPath()));

                        } else {
                            adapter.add(new FixFileBean(media.getPath()));
                        }
                    }

                    if (adapter.getAllData().size() <= 6) {
                        adapter.add(new FixFileBean(""));
                    }
                }
                break;

        }
    }


    //上传图片
    private void upImage(final List<FixFileBean> imgList) {
        final List<File> files = new ArrayList<>();
        final List<String> sList = new ArrayList<>();
        for (FixFileBean bean : imgList) {
            sList.add(bean.getFile_url());
        }
        Luban.with(getActivity())
                .load(sList)  // 传人要压缩的图片列表
                .ignoreBy(100).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(File file) {
                files.add(file);
                if (imgList.size() == files.size()) {
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("path", "/images/monthTotal");
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
    public MonthlySummaryPresenter createPresenter() {
        return new MonthlySummaryPresenter(getApp());
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventBook event) {
        switch (event.getType()) {
            case "department":
                BookNameBean bookNameBean = event.getBookNameBean();
                tableAdapter.getItem(departmentPosition).setMonth_department(bookNameBean.getName());
                tableAdapter.getItem(departmentPosition).setGroup_id(bookNameBean.getNames());
                tableAdapter.notifyItemChanged(departmentPosition);
                break;

        }

    }

}
