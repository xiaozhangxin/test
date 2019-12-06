package com.akan.qf.mvp.fragment.cpeople;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.FirstEventNew;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.PeopleJionBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.PeopleJoinPresenter;
import com.akan.qf.mvp.view.home.IPeopleJoinView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.DialogLoadding;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2019/2/18.
 */

public class PeopleJoinAddFragment extends BaseFragment<IPeopleJoinView, PeopleJoinPresenter> implements IPeopleJoinView {


    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.textView26)
    TextView textView26;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvOne)
    EditText tvOne;
    @BindView(R.id.tvTwo)
    EditText tvTwo;
    @BindView(R.id.tvThree)
    EditText tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.tvFive)
    EditText tvFive;
    @BindView(R.id.tvSix)
    EditText tvSix;
    @BindView(R.id.tvRemark)
    EditText tvRemark;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    @BindView(R.id.circleImageVIew)
    CircleImageView circleImageVIew;
    @BindView(R.id.ivCheckDelete)
    ImageView ivCheckDelete;
    @BindView(R.id.tvCheckPersonName)
    TextView tvCheckPersonName;

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String type;
    private PeopleJionBean data;
    private String mNext_staff_id = "";
    private DialogLoadding dialogLoadding;
    private PermissionsBean permissionsBean;
    public static PeopleJoinAddFragment newInstance(PeopleJionBean bean, String type,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        PeopleJoinAddFragment fragment = new PeopleJoinAddFragment();
        fragment.data = bean;
        fragment.permissionsBean=permissionsBean;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_peoplejion_add;
    }

    @Override
    public void initUI() {
        if ("0".equals(type)) {
            getData();
            ok.setText("提交");
            tvTitle.setText("招人申请");
        } else {
            tvTitle.setText("修改招人申请");
            ok.setText("确认修改");
            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getApply_create_time());
            tvOne.setText(data.getApply_area());
            tvTwo.setText(data.getApply_job());
            tvThree.setText(data.getApply_number());
            tvFour.setText(data.getApply_time());
            tvFive.setText(data.getApply_wages());
            tvSix.setText(data.getApply_way());
            tvRemark.setText(data.getApply_remark());
        }
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        tvName.setText(userBean.getStaff_name());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTime.setText(str);
        tvDepartment.setText(userBean.getSimple_department_name());
        if ("0".equals(type)) {
            if (!TextUtils.isEmpty(userBean.getParent_id())) {
                tvOne.setText(userBean.getSimple_department_name());
                mNext_staff_id = userBean.getParent_id();
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
            tvOne.setText(userBean.getSimple_department_name());
            mNext_staff_id = data.getNext_staff_id();
            Glide.with(context)
                    .load(Constants.BASE_URL + data.getNext_audit_staff_head_img())
                    .error(R.drawable.error_img)
                    .into(circleImageVIew);
            tvCheckPersonName.setText(data.getNext_audit_staff_name());
        }

    }

    @OnClick({R.id.ivLeft, R.id.ok, R.id.tvFour,R.id.circleImageVIew, R.id.ivCheckDelete})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivCheckDelete:
                ivCheckDelete.setVisibility(View.GONE);
                tvCheckPersonName.setText("请选择");
                Glide.with(context).load(Constants.BASE_URL)
                        .error(R.drawable.check_img)
                        .into(circleImageVIew);
                mNext_staff_id = "";
                break;
            case R.id.circleImageVIew:
                startChooseCheckPersonFragment("1");
                break;
            case R.id.tvFour:
                chooseTime("计划到岗时间");
                break;
            case R.id.ok:
                String mtvOne = tvOne.getText().toString().trim();
                if (TextUtils.isEmpty(mtvOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入需求部门");
                    return;
                }
                String mtvTwo = tvTwo.getText().toString().trim();
                if (TextUtils.isEmpty(mtvTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入需求岗位");
                    return;
                }
                String mtvThree = tvThree.getText().toString().trim();
                if (TextUtils.isEmpty(mtvThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入需求人数");
                    return;
                }
                String mtvFour = tvFour.getText().toString().trim();
                if (TextUtils.isEmpty(mtvFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择计划到岗时间");
                    return;
                }

                String mtvFive = tvFive.getText().toString().trim();
                if (TextUtils.isEmpty(mtvFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入预计薪资");
                    return;
                }
                String mtvSix = tvSix.getText().toString().trim();
                if (TextUtils.isEmpty(mtvSix)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入招聘途径");
                    return;
                }
                if (TextUtils.isEmpty(mNext_staff_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择审批人");
                    return;
                }
                ok.setEnabled(false);
                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("apply_area", mtvOne);
                map.put("apply_job", mtvTwo);
                map.put("apply_number", mtvThree);
                map.put("apply_time", mtvFour);
                map.put("apply_wages", mtvFive);
                map.put("apply_way", mtvSix);
                map.put("apply_remark", tvRemark.getText().toString());
                map.put("next_staff_id", mNext_staff_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());
                map.put("operation", "0");
                if ("1".equals(type)) {
                    map.put("operation", "1");
                    map.put("apply_id", data.getApply_id());
                }
                getPresenter().insertOrUpdateRecruitApply(userBean.getStaff_token(), map);

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
                    case "计划到岗时间":
                        tvFour.setText(format);
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
                .isDialog(true)
                .build();
        build.show();

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
    public PeopleJoinPresenter createPresenter() {
        return new PeopleJoinPresenter(getApp());
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
        PeopleJionBean bean = new PeopleJionBean();
        bean.setApply_area(tvOne.getText().toString());
        bean.setApply_job(tvTwo.getText().toString());
        bean.setApply_number(tvThree.getText().toString());
        bean.setApply_time(tvFour.getText().toString());
        bean.setApply_wages(tvFive.getText().toString());
        bean.setApply_way(tvSix.getText().toString());
        bean.setApply_remark(tvRemark.getText().toString());
        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("peopleJoin", s);
        editor.commit();
    }
    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("peopleJoin", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                tvOne.setText(object.getString("apply_area"));
                tvTwo.setText(object.getString("apply_job"));
                tvThree.setText(object.getString("apply_number"));
                tvFour.setText(object.getString("apply_time"));
                tvFive.setText(object.getString("apply_wages"));
                tvSix.setText(object.getString("apply_way"));
                tvRemark.setText(object.getString("apply_remark"));

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
                mNext_staff_id = bean.getStaff_id();
                break;
        }

    }
    @Override
    public void onInsertOrUpdateRecruitApply(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(),data);
        tvOne.setText("");
        tvTwo.setText("");
        tvThree.setText("");
        tvFour.setText("");
        tvFive.setText("");
        tvSix.setText("");
        tvRemark.setText("");
        finish();

    }

    @Override
    public void onGetRecruitApplyList(List<PeopleJionBean> data, String total) {

    }

    @Override
    public void onGetRecruitApply(PeopleJionBean data) {

    }

    @Override
    public void onAuditRecruitApply(String data) {

    }

    @Override
    public void onDeleteRecruitApply(String data) {

    }

    @Override
    public void onGetCheckPerson(List<MeParentBean> data) {
        for (int i = 0; i < data.size(); i++) {
            String module = data.get(i).getStaff_module();
            if (module.contains("Recruitment")) {
                String staff_give = data.get(i).getStaff_give();
                if ("1".equals(staff_give)) {
                    MeParentBean checkBean = data.get(i);
                    mNext_staff_id = checkBean.getStaff_id();
                    Glide.with(context)
                            .load(Constants.BASE_URL + checkBean.getHead_img())
                            .error(R.drawable.error_img)
                            .into(circleImageVIew);
                    tvCheckPersonName.setText(checkBean.getStaff_name());
                }

            }
        }
    }
}

