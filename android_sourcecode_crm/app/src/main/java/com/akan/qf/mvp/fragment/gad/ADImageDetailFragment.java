package com.akan.qf.mvp.fragment.gad;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.AdManagementBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.FileListNewBean;
import com.akan.qf.bean.FirstEventFilter;
import com.akan.qf.bean.RecordListBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageShowAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.fragment.ReviewFragment;
import com.akan.qf.mvp.presenter.ad.ADManagementPresenter;
import com.akan.qf.mvp.view.ad.IADManagementView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.CustomDialog;
import com.akan.qf.view.img.ShowPictureActivity;
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
 * Created by admin on 2019/2/22.
 */

public class ADImageDetailFragment extends BaseFragment<IADManagementView, ADManagementPresenter> implements IADManagementView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvTop)
    TextView tvTop;
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvState)
    TextView tvState;
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
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.tvSix)
    TextView tvSix;
    @BindView(R.id.tvSeven)
    TextView tvSeven;
    @BindView(R.id.tvEight)
    TextView tvEight;
    @BindView(R.id.tvImgTittle)
    TextView tvImgTittle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.tvAssessTittle)
    TextView tvAssessTittle;
    @BindView(R.id.tvAssess)
    TextView tvAssess;
    @BindView(R.id.tvJobName)
    TextView tvJobName;
    @BindView(R.id.tvPee)
    TextView tvPee;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String detail_id;
    private List<String> imgList;
    private ImageShowAdapter imgAdapter;
    private AdManagementBean bean;

    private PermissionsBean permissionsBean;

    public static ADImageDetailFragment newInstance(String detail_id, PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ADImageDetailFragment fragment = new ADImageDetailFragment();
        fragment.detail_id = detail_id;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_ad_shop_detail;
    }

    @Override
    public void initUI() {
        tvImgTittle.setText("形象店（展墙）广告上传");
        tvTitle.setText("形象广告详情");

        imgList = new ArrayList<>();
        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        recycleView.setNestedScrollingEnabled(false);
        imgAdapter = new ImageShowAdapter(context, imgList);
        recycleView.setAdapter(imgAdapter);
        imgAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ShowPictureActivity.class);
                intent.putExtra("imagelist", (Serializable) imgAdapter.getAllData());
                intent.putExtra("position", position);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();

    }

    private void refresh() {
        map.clear();
        map.put("apply_id", detail_id);
        getPresenter().getAdvertApply(userBean.getStaff_token(), map);
    }

    private String appOperation;
    private String orderStaffId;//制单人id

    @Override
    public void onGetAdvertApply(AdManagementBean data) {
        if (TextUtils.isEmpty(data.getApply_id())) {
            showEmptyDialog();
            return;
        }

        orderStaffId=data.getStaff_id();
        //appOperation 0新增 1编辑 2删除 3审核
        appOperation = permissionsBean.getApp_operation();
        String[] strings = appOperation.split(",");
        switch (data.getApply_state()) {
            case "wait_audit":
                if (data.getStaff_id().equals(userBean.getStaff_id())) {
                    if (isHave("2", strings)) {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText(R.string.more);
                    } else {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText(R.string.edit);
                    }
                } else {
                    showRight(strings);

                }
                tvState.setTextColor(getResources().getColor(R.color.audit_one));
                break;
            case "accept":
                if (data.getStaff_id().equals(userBean.getStaff_id())) {
                    tvRight.setVisibility(View.VISIBLE);
                    tvRight.setText(R.string.review_replay);
                } else {
                    if (isHave("3", strings)) {
                        tvRight.setVisibility(View.VISIBLE);
                        tvRight.setText(R.string.review);
                    } else {
                        tvRight.setVisibility(View.GONE);
                    }
                }
                tvState.setTextColor(getResources().getColor(R.color.audit_two));
                break;
        }

        bean = data;
        tvJobName.setText(data.getJob_name());
        tvName.setText(data.getStaff_name());
        tvNo.setText(data.getApply_no());
        tvState.setText(data.getApply_state_show());
        tvTime.setText(data.getApply_create_time());
        tvDepartment.setText(data.getStaff_department());

        tvOne.setText(data.getApply_department());
        tvTwo.setText(data.getApply_category());
        // tvThree.setText(data.getApply_name());
        tvFour.setText(data.getApply_tel());
        tvFive.setText(data.getApply_input());
        tvSix.setText(data.getApply_address());
        tvSeven.setText(data.getApply_fee());
        tvEight.setText(data.getApply_remark());

        List<FileListNewBean> fileList = data.getFileList();
        if (fileList.size() > 0) {
            ArrayList<String> imgList = new ArrayList<>();
            for (int i = 0; i < fileList.size(); i++) {
                imgList.add(fileList.get(i).getFile_url());
            }
            tvImgTittle.setVisibility(View.VISIBLE);
            recycleView.setVisibility(View.VISIBLE);
            imgAdapter.clear();
            imgAdapter.addAll(imgList);
            imgAdapter.notifyDataSetChanged();
        } else {
            tvImgTittle.setVisibility(View.GONE);
            recycleView.setVisibility(View.GONE);
        }


        List<RecordListBean> dailyAuditBeans = data.getRecordList();
        StringBuilder audit = new StringBuilder();
        if (dailyAuditBeans.size() > 0) {
            tvAssess.setVisibility(View.VISIBLE);
            tvAssessTittle.setVisibility(View.VISIBLE);
            for (int i = 0; i < dailyAuditBeans.size(); i++) {
                audit.append(dailyAuditBeans.get(i).getRecord_name() + "："
                        + dailyAuditBeans.get(i).getRecord_remark() + "  "
                        + dailyAuditBeans.get(i).getRecord_create_time() + "\n\n");
            }
            tvAssess.setText(audit.toString());
        } else {
            tvAssess.setVisibility(View.GONE);
            tvAssessTittle.setVisibility(View.GONE);
        }

    }
    //右上角显示
    private void showRight(String[] strings) {
        if (isHave("1", strings) && isHave("2", strings) && isHave("3", strings)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.more);
        } else if (isHave("1", strings) && isHave("2", strings) && !isHave("3", strings)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.more);
        } else if (isHave("1", strings) && isHave("3", strings) && !isHave("2", strings)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.more);
        } else if (isHave("2", strings) && isHave("3", strings) && !isHave("1", strings)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.more);
        } else if (isHave("1", strings) && !isHave("2", strings) && !isHave("3", strings)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.edit);
        } else if (isHave("2", strings) && !isHave("1", strings) && !isHave("3", strings)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.delete);
        } else if (isHave("3", strings) && !isHave("1", strings) && !isHave("2", strings)) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(R.string.review);
        } else if (!isHave("1", strings) && !isHave("2", strings) && !isHave("3", strings)) {
            tvRight.setVisibility(View.GONE);
        }

    }

    //数组钟是否包含某元素
    public boolean isHave(String index, String[] split) {
        for (int i = 0; i < split.length; i++) {
            if (index.equals(split[i])) {
                return true;
            }
        }
        return false;
    }

    //显示空单据弹框
    private void showEmptyDialog() {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage(getString(R.string.deleted));
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
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
                    case "编辑":
                        startADImageAddFragment(bean, "1", permissionsBean);
                        break;
                    case "删除":
                        toDelete();
                        break;
                    case "审阅":
                        startDayReviewFragment("0");
                        break;
                    case "审阅回复":
                        startDayReviewFragment("1");
                        break;
                    case "更多":
                        if (TextUtils.isEmpty(orderStaffId) || TextUtils.isEmpty(appOperation)) {
                            return;
                        }
                        moreOperation(orderStaffId, appOperation);
                        break;
                }
                break;
        }
    }

    //更多显示的操作  //appOperation  1编辑 2删除 3审核
    private void moreOperation(String orderStaffId, String appOperation) {
        if (orderStaffId.equals(userBean.getStaff_id())) {//自己的单据
            showList(new String[]{"编辑", "删除"});
        } else {
            String[] strings = appOperation.split(",");
            if (isHave("1",strings) && isHave("2", strings) && isHave("3", strings)) {
                showList(new String[]{"编辑", "删除", "审阅"});
            } else if (isHave("1",strings) && isHave("2", strings) && !isHave("3", strings)) {
                showList(new String[]{"编辑", "删除"});
            } else if (isHave("1",strings) && isHave("3", strings) && !isHave("2", strings)) {
                showList(new String[]{"编辑", "审阅"});
            } else if (isHave("2", strings) && isHave("3", strings) && !isHave("1",strings)) {
                showList(new String[]{"删除", "审阅"});
            }
        }
    }


    private AlertDialog alertDialog1;

    public void showList(final String[] items) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (items[i]) {
                    case "编辑":
                        startADImageAddFragment(bean, "1", permissionsBean);
                        break;
                    case "删除":
                        toDelete();
                        break;
                    case "审阅":
                        startDayReviewFragment("0");
                        break;
                }

                alertDialog1.dismiss();
            }
        });
        alertDialog1 = alertBuilder.create();
        alertDialog1.show();
    }

    //单据删除
    private void toDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.delete_order);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("apply_id", detail_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().deleteAdvertApply(userBean.getStaff_token(), map);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void startDayReviewFragment(String type) {
        ReviewFragment fragment = ReviewFragment.newInstance(type);
        fragment.setOnReviewClickListener(new ReviewFragment.OnReviewClickListener() {
            @Override
            public void ok(String content) {
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("apply_id", detail_id);
                map.put("apply_remark", content);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "3");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().auditAdvertApply(userBean.getStaff_token(), map);
            }
        });
        fragment.show(getFragmentManager(), ADImageDetailFragment.class.getSimpleName());
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
    public ADManagementPresenter createPresenter() {
        return new ADManagementPresenter(getApp());
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


    @Override
    public void onInsertOrUpdateAdvertApply(String data) {

    }

    @Override
    public void onGetAdvertApplyList(List<AdManagementBean> data, String total) {

    }


    @Override
    public void onAuditAdvertApply(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        refresh();
    }

    @Override
    public void onDdeleteAdvertApply(String data) {
        EventBus.getDefault().post(new FirstEventFilter("adImage_delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onDeleteAdvertApplyFile(String data) {

    }

    @Override
    public void onUploadFiles(String[] data) {

    }
}
