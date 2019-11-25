package com.ak.pt.mvp.fragment.area;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.AreaStudyBean;
import com.ak.pt.bean.FirstEventFilter;
import com.ak.pt.bean.FixFileBean;
import com.ak.pt.bean.RecordBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.activity.TbsFileActivity;
import com.ak.pt.mvp.adapter.ImageAllTypeShowAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.fragment.ReviewFragment;
import com.ak.pt.mvp.presenter.area.RegionPresenter;
import com.ak.pt.mvp.view.area.IRegionView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.ak.pt.util.img.ShowPictureActivity;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;


import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/27.
 */

public class RegionDetailFragmrnt extends BaseFragment<IRegionView, RegionPresenter> implements IRegionView {

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
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.imgTittleDown)
    TextView imgTittleDown;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.tvAssess)
    TextView tvAssess;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String detail_id;
    private List<FixFileBean> imgList;
    private ImageAllTypeShowAdapter imgAdapter;
    private AreaStudyBean bean;
    private AppPermissionsBean permissionsBean;

    public static RegionDetailFragmrnt newInstance(String detail_id, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        RegionDetailFragmrnt fragment = new RegionDetailFragmrnt();
        fragment.detail_id = detail_id;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_region_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("区域培训详情");
        tvRight.setVisibility(View.VISIBLE);


        imgList = new ArrayList<>();
        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        recycleView.setNestedScrollingEnabled(false);
        imgAdapter = new ImageAllTypeShowAdapter(context, imgList);
        recycleView.setAdapter(imgAdapter);
        imgAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String upUrl = imgAdapter.getItem(position).getFile_url();
                if (upUrl.endsWith(".jpg") | upUrl.endsWith(".png") | upUrl.endsWith(".jpeg") | upUrl.endsWith(".JPEG") | upUrl.endsWith(".PNG") | upUrl.endsWith(".JPG")) {
                    ArrayList<String> list = new ArrayList<>();
                    int mPosition = 0;
                    for (int i = 0; i < imgAdapter.getAllData().size(); i++) {
                        String file_path = imgAdapter.getItem(i).getFile_url();
                        if (file_path.equals(upUrl)) {
                            mPosition = list.size();
                        }
                        if (upUrl.endsWith(".jpg") | upUrl.endsWith(".png") | upUrl.endsWith(".jpeg") | upUrl.endsWith(".JPEG") | upUrl.endsWith(".PNG") | upUrl.endsWith(".JPG")) {
                            list.add(file_path);
                        }
                    }

                    Intent intent = new Intent(getActivity(), ShowPictureActivity.class);
                    intent.putExtra("imagelist", (Serializable) list);
                    intent.putExtra("position", mPosition);
                    getActivity().startActivity(intent);
                } else {
                    Intent intentVisit = new Intent(getActivity(), TbsFileActivity.class);
                    intentVisit.putExtra("file_url", upUrl);
                    intentVisit.putExtra("file_name", imgAdapter.getItem(position).getFile_name());
                    startActivity(intentVisit);

                }


            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("study_id", detail_id);
        getPresenter().getAreaStudyDetail(userBean.getStaff_token(), map);
    }

    private int moreType = 1;   //  0:123   1:12    2:13    3:23
    @Override
    public void onGgetAreaStudyDetail(AreaStudyBean data) {
        if (TextUtils.isEmpty(data.getStudy_id())) {
            final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
            builder1.setMessage(getString(R.string.deleted));
            builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    dialog.dismiss();
                }
            });
            builder1.onCreate().show();
            return;
        }
        //appOperation 0新增 1编辑 2删除 3审核
        String appOperation = permissionsBean.getApp_operation();
        switch (data.getStudy_state()) {
            case "wait_audit":
                if (userBean.getStaff_id().equals(data.getStaff_id())) {
                    if (appOperation.contains("2")) {
                        tvRight.setVisibility(View.VISIBLE);
                        moreType = 1;
                        tvRight.setText("更多");
                    } else {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText("编辑");
                    }
                } else {
                    tvRight.setVisibility(View.VISIBLE);
                    if (appOperation.contains("1") && appOperation.contains("2") && appOperation.contains("3")){
                        moreType = 0;
                        tvRight.setText("更多");
                    } else if (appOperation.contains("1")&&appOperation.contains("2")) {
                        moreType = 1;
                        tvRight.setText("更多");
                    } else if (appOperation.contains("1")&&appOperation.contains("3")) {
                        moreType = 2;
                        tvRight.setText("更多");
                    } else if (appOperation.contains("2")&&appOperation.contains("3")) {
                        moreType = 3;
                        tvRight.setText("更多");
                    } else if (appOperation.contains("1")) {
                        tvRight.setText("编辑");
                    } else if (appOperation.contains("2")) {
                        tvRight.setText("删除");
                    } else if (appOperation.contains("3")) {
                        tvRight.setText("审阅");
                    } else {
                        tvRight.setVisibility(View.GONE);
                    }
                }
                tvState.setTextColor(getResources().getColor(R.color.audit_one));
                break;
            case "accept":
                if (data.getStaff_id().equals(userBean.getStaff_id())) {
                    tvRight.setText(R.string.review_replay);
                } else {
                    if (appOperation.contains("3")) {
                        tvRight.setText(R.string.review);
                    } else {
                        tvRight.setVisibility(View.GONE);
                    }
                }

                tvState.setTextColor(getResources().getColor(R.color.audit_two));
                break;
        }
        bean = data;
        tvName.setText(data.getStaff_name());
        tvTime.setText(data.getCreate_time());
        tvDepartment.setText(data.getDepartment_name());
        tvState.setText(data.getStudy_state_show());
        tvOne.setText(data.getStudy_time());
        tvTwo.setText(data.getAddress());
        tvThree.setText(data.getContent());
        tvFour.setText(data.getRemark());


        List<FixFileBean> upFileList = data.getFileList();
        if (upFileList.size() > 0) {
            imgTittleDown.setVisibility(View.VISIBLE);
            recycleView.setVisibility(View.VISIBLE);
            imgAdapter.clear();
            imgAdapter.addAll(upFileList);
            imgAdapter.notifyDataSetChanged();
        } else {
            imgTittleDown.setVisibility(View.GONE);
            recycleView.setVisibility(View.GONE);
        }

        List<RecordBean> recordList = data.getRecordList();
        if (recordList.size() > 0) {

            StringBuilder audit = new StringBuilder();
            for (int i = 0; i < recordList.size(); i++) {
                RecordBean bean = recordList.get(i);
                audit.append(bean.getRecord_name() + "：<font color=\"#333333\">"
                        + bean.getRecord_remark() + "</font>&nbsp&nbsp&nbsp<font color=\"#999999\"><small>"
                        + bean.getRecord_create_time().substring(0,16) + "</small></font><br><br>");
            }
            tvAssess.setText(Html.fromHtml(audit.toString()));

        } else {
            tvAssess.setText(R.string.no_comment);
        }

    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                String s = tvRight.getText().toString();
                switch (s) {
                    case "删除":
                        onDelete();
                        break;
                    case "编辑":
                        startRegionAddFragment(bean, "1", permissionsBean);
                        break;
                    case "审阅":
                        startDayReviewFragment("0");
                        break;
                    case "审阅回复":
                        startDayReviewFragment("1");
                        break;
                    case "更多":
                        switch (moreType){
                            case 0:
                                showList(new String[]{"编辑", "删除", "审阅"});
                                break;
                            case 1:
                                showList(new String[]{"编辑", "删除"});
                                break;
                            case 2:
                                showList(new String[]{"编辑", "审阅"});
                                break;
                            case 3:
                                showList(new String[]{"删除", "审阅"});
                                break;
                        }
                        break;
                }
                break;
        }

    }

    //单据删除
    private void onDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(getString(R.string.sure_delete_order));
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("study_id", detail_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().deleteAreaStudy(userBean.getStaff_token(), map);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //单据审阅
    private void startDayReviewFragment(String type) {
        ReviewFragment fragment = ReviewFragment.newInstance(type);
        fragment.setOnReviewClickListener(new ReviewFragment.OnReviewClickListener() {
            @Override
            public void ok(String content) {
                map.clear();
                map.put("study_id", bean.getStudy_id());
                map.put("remark", content);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "3");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().auditAreaStudy(userBean.getStaff_token(), map);
            }
        });

        fragment.show(getFragmentManager(), RegionDetailFragmrnt.class.getSimpleName());
    }


    @Override
    public void onIinsertAreaStudy(String data) {

    }

    @Override
    public void onDdeleteAreaStudy(String data) {
        EventBus.getDefault().post(new FirstEventFilter("filter_delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onDdeleteAreaStudyFile(String data) {

    }

    @Override
    public void onUupdateAreaStudy(String data) {

    }

    @Override
    public void OnGgetAreaStudyList(List<AreaStudyBean> data) {

    }


    @Override
    public void onUploadFiles(String[] data) {

    }

    @Override
    public void onUauditAreaStudy(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        map.put("study_id", detail_id);
        getPresenter().getAreaStudyDetail(userBean.getStaff_token(), map);
    }


    private void showList(final String[] items) {
        new AlertDialog.Builder(context)
                .setItems(items, (dialogInterface, i) -> {
                    switch (items[i]) {
                        case "编辑":
                            startRegionAddFragment(bean, "1", permissionsBean);
                            break;
                        case "删除":
                            onDelete();
                            break;
                        case "审阅":
                            startDayReviewFragment("0");
                            break;
                    }
                })
                .show();
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
    }

    @Override
    public RegionPresenter createPresenter() {
        return new RegionPresenter(getApp());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

