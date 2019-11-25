package com.ak.pt.mvp.fragment.area;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.FirstEventNew;
import com.ak.pt.bean.MeParentBean;
import com.ak.pt.bean.PressureBackBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.area.ReworkPresenter;
import com.ak.pt.mvp.view.area.IReworkView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.ak.pt.view.DialogLoading;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.uniquext.android.widget.view.CornerImageView;

/**
 * Created by admin on 2019/5/30.
 */

public class ReworkAddFragment extends BaseFragment<IReworkView, ReworkPresenter> implements IReworkView {


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
    EditText tvOne;
    @BindView(R.id.tvTwo)
    EditText tvTwo;
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
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    @BindView(R.id.circleImageVIew)
    CornerImageView circleImageVIew;
    @BindView(R.id.ivCheckDelete)
    ImageView ivCheckDelete;
    @BindView(R.id.tvCheckPersonName)
    TextView tvCheckPersonName;
    @BindView(R.id.ok)
    Button ok;


    private Map<String, String> map = new HashMap<>();
    private Map<String, String> map2 = new HashMap<>();
    private UserBean userBean;
    private DialogLoading dialogLoading;
    private String type;
    private PressureBackBean data;
    private String mNext_staff_id = "";
    private AppPermissionsBean permissionsBean;

    public static ReworkAddFragment newInstance(PressureBackBean bean, String type,AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ReworkAddFragment fragment = new ReworkAddFragment();
        fragment.permissionsBean=permissionsBean;
        fragment.data = bean;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_rework_add;
    }

    @Override
    public void initUI() {


        if ("0".equals(type)) {
            ok.setText("提交");
            tvTitle.setText("试压仪返修");

        } else {
            tvTitle.setText("修改试压仪返修");
            ok.setText("确认修改");

            tvOne.setText(data.getSend_name());
            tvTwo.setText(data.getSend_address());
            tvThree.setText(data.getSend_tel());
            tvFour.setText(data.getTool_name());
            tvFive.setText(data.getOrder_no());
            tvSix.setText(data.getShop_name());
            tvSeven.setText(data.getBack_remark());
            tvEight.setText(data.getReceipt_name());
            tvNine.setText(data.getReceipt_tel());
            tvTen.setText(data.getReceipt_address());
            tvEleven.setText(data.getRemark());
            mNext_staff_id = data.getNext_audit_id();
        }


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTime.setText(str);
        tvName.setText(userBean.getStaff_name());
        tvDepartment.setText(userBean.getSimple_department_name());
        map2.clear();
        getPresenter().getLargeStaffList(userBean.getStaff_token(), map2);
    }


    @OnClick({R.id.ivLeft, R.id.circleImageVIew, R.id.ivCheckDelete, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivCheckDelete:
                ivCheckDelete.setVisibility(View.GONE);
                tvCheckPersonName.setText("请选择");
                Glide.with(context).load(R.drawable.check_img).into(circleImageVIew);
                mNext_staff_id = "";
                break;
            case R.id.circleImageVIew:
                startChooseCheckFragment("2");
                break;
            case R.id.ok:
                if (TextUtils.isEmpty(mNext_staff_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择审批人");
                    return;
                }
                ok.setEnabled(false);
                dialogLoading = new DialogLoading(context);
                dialogLoading.showDialog();
                map.clear();
                map.put("send_name", tvOne.getText().toString());
                map.put("send_address", tvTwo.getText().toString());
                map.put("send_tel", tvThree.getText().toString());
                map.put("tool_name", tvFour.getText().toString());
                map.put("order_no", tvFive.getText().toString());
                map.put("shop_name", tvSix.getText().toString());
                map.put("back_remark", tvSeven.getText().toString());
                map.put("receipt_name", tvEight.getText().toString());
                map.put("receipt_tel", tvNine.getText().toString());
                map.put("receipt_address", tvTen.getText().toString());
                map.put("remark", tvEleven.getText().toString());
                map.put("next_audit_id", mNext_staff_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());

                switch (type) {
                    case "0"://新建
                        map.put("operation", "0");
                        getPresenter().insertPressureBack(userBean.getStaff_token(), map);
                        break;
                    case "1"://编辑
                        map.put("operation", "1");
                        map.put("back_id", data.getBack_id());
                        getPresenter().updatePressureBack(userBean.getStaff_token(), map);
                        break;
                }
                break;
        }
    }


    @Override
    public ReworkPresenter createPresenter() {
        return new ReworkPresenter(getApp());
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
    public void onEventMainThread(FirstEventNew event) {
        switch (event.getMsg()) {
            case "2":
                MeParentBean bean = event.getmBean();
                Glide.with(context)
                        .load(Constants.BASE_URL + bean.getHead_img())
                        .error(R.drawable.error_img)
                        .into(circleImageVIew);
                tvCheckPersonName.setText(bean.getStaff_name());
                ivCheckDelete.setVisibility(View.VISIBLE);
                mNext_staff_id = bean.getStaff_id();
                ivCheckDelete.setVisibility(View.VISIBLE);
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
    public void oninsertPressureBack(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void ondeletePressureBack(String data) {

    }

    @Override
    public void onupdatePressureBack(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onauditPressureBack(String data) {

    }

    @Override
    public void OngetPressureBackList(List<PressureBackBean> data) {

    }

    @Override
    public void ongetPressureBackDetail(PressureBackBean data) {

    }

    @Override
    public void ongetLargeStaffList(List<MeParentBean> data) {
        switch (type) {
            case "0":
                for (int i = 0; i < data.size(); i++) {
                    MeParentBean bean = data.get(i);
                    if ("1".equals(bean.getStaff_give())) {
                        Glide.with(context)
                                .load(Constants.BASE_URL + bean.getHead_img())
                                .error(R.drawable.error_img)
                                .into(circleImageVIew);
                        tvCheckPersonName.setText(bean.getStaff_name());
                        ivCheckDelete.setVisibility(View.VISIBLE);
                        mNext_staff_id = bean.getStaff_id();
                        ivCheckDelete.setVisibility(View.VISIBLE);
                    }

                }
                break;
            case "1":
                for (int i = 0; i < data.size(); i++) {
                    MeParentBean bean = data.get(i);
                    if (mNext_staff_id.equals(bean.getStaff_id())) {
                        Glide.with(context)
                                .load(Constants.BASE_URL + bean.getHead_img())
                                .error(R.drawable.error_img)
                                .into(circleImageVIew);
                        tvCheckPersonName.setText(bean.getStaff_name());
                        ivCheckDelete.setVisibility(View.VISIBLE);
                        mNext_staff_id = bean.getStaff_id();
                        ivCheckDelete.setVisibility(View.VISIBLE);
                    }

                }

                break;
        }

    }
}