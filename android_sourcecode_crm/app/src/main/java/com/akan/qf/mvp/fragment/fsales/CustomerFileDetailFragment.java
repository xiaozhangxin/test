package com.akan.qf.mvp.fragment.fsales;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.ArchivesApplyBean;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.CustomerFilePresenter;
import com.akan.qf.mvp.view.home.ICustomerFileView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.img.ShowPictureActivity;
import com.bumptech.glide.Glide;

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
 * Created by admin on 2018/11/28.
 */

public class CustomerFileDetailFragment extends BaseFragment<ICustomerFileView, CustomerFilePresenter> implements ICustomerFileView {


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
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvBuyTime)
    TextView tvBuyTime;
    @BindView(R.id.tvUnit)
    TextView tvUnit;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvDepartmentCustome)
    TextView tvDepartmentCustome;
    @BindView(R.id.tvReturnNum)
    TextView tvReturnNum;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.ivImg)
    ImageView ivImg;
    @BindView(R.id.tvDescribe)
    TextView tvDescribe;
    private ArchivesApplyBean bean;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static CustomerFileDetailFragment newInstance(ArchivesApplyBean bean) {
        Bundle args = new Bundle();
        CustomerFileDetailFragment fragment = new CustomerFileDetailFragment();
        fragment.bean = bean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_file_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("客户档案详情单");


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("apply_id", bean.getApply_id());
        getPresenter().getArchivesApply(userBean.getStaff_token(), map);
    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("apply_id", bean.getApply_id());
        getPresenter().getArchivesApply(userBean.getStaff_token(), map);
    }

    @Override
    public void OngetArchivesApply(final ArchivesApplyBean data) {
        bean = data;
        if (userBean.getStaff_id().equals(data.getStaff_id())) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("更多");
        } else {
            tvRight.setVisibility(View.GONE);
        }
        tvNo.setText(data.getApply_no());
        tvState.setText(data.getStaff_name());
        tvTime.setText(data.getApply_create_time());
        tvDepartment.setText(data.getApply_remark());
        tvBuyTime.setText(data.getApply_name());
        tvUnit.setText(data.getCustomer_no());
        tvPhone.setText(data.getApply_department());
        tvDepartmentCustome.setText(data.getApply_number());
        tvReturnNum.setText(data.getApply_tel());
        tvType.setText(data.getApply_address());
        Glide.with(context)
                .load(Constants.BASE_URL + data.getApply_license())
                .error(R.drawable.error_img)
                .into(ivImg);
        ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List mImgList = new ArrayList();
                mImgList.clear();
                mImgList.add(data.getApply_license());
                Intent intent = new Intent(getContext(), ShowPictureActivity.class);
                intent.putExtra("imagelist", (Serializable) mImgList);
                intent.putExtra("position", 0);
                getContext().startActivity(intent);
            }
        });
        tvDescribe.setText(data.getApply_time());
    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                showList(new String[]{"编辑", "删除"});
                break;
        }
    }

    private AlertDialog alertDialog1;

    public void showList(final String[] items) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        startFileAddFragment(bean, "1");
                        break;
                    case 1:
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("你确定要删除吗?");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                map.clear();
                                map.put("apply_id", bean.getApply_id());
                                map.put("staff_id", userBean.getStaff_id());
                                getPresenter().deleteArchivesApply(userBean.getStaff_token(), map);
                            }
                        });
                        builder.setNegativeButton("取消", null);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();


                        break;
                }

                alertDialog1.dismiss();
            }
        });
        alertDialog1 = alertBuilder.create();
        alertDialog1.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public CustomerFilePresenter createPresenter() {
        return new CustomerFilePresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void OnInsertArchivesApply(String data) {

    }

    @Override
    public void OnGetArchivesApplyList(List<ArchivesApplyBean> data) {

    }


    @Override
    public void OndeleteArchivesApply(String data) {
        EventBus.getDefault().post(new FirstEvent("leave_arrears"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();

    }

    @Override
    public void OnupdateArchivesApply(String data) {

    }

    @Override
    public void onUploadFiles(String[] data) {

    }
}
