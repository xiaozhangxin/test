package com.akan.qf.mvp.fragment.home;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.mvp.fragment.qifei.StaffBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.PersonInfoPresenter;
import com.akan.qf.mvp.view.home.IPersonInfoView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.CustomDialog;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/11/29.
 */

public class PersonInfoDetailFragment extends BaseFragment<IPersonInfoView, PersonInfoPresenter> implements IPersonInfoView {
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
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.tvSex)
    TextView tvSex;
    @BindView(R.id.tvBirthday)
    TextView tvBirthday;
    @BindView(R.id.tvaddress)
    EditText tvaddress;
    @BindView(R.id.tvCountry)
    EditText tvCountry;
    @BindView(R.id.tvEducation)
    TextView tvEducation;
    @BindView(R.id.tvProfession)
    EditText tvProfession;
    @BindView(R.id.tvNowAddress)
    EditText tvNowAddress;
    @BindView(R.id.tvPhone)
    EditText tvPhone;
    @BindView(R.id.tvTel)
    EditText tvTel;
    @BindView(R.id.tvWeChat)
    EditText tvWeChat;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.textView22)
    TextView textView22;
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.tvSix)
    TextView tvSix;
    @BindView(R.id.tvSeven)
    TextView tvSeven;
    @BindView(R.id.tvEight)
    TextView tvEight;
    @BindView(R.id.tvNine)
    TextView tvNine;
    @BindView(R.id.tvTen)
    TextView tvTen;
    @BindView(R.id.tvEleven)
    TextView tvEleven;
    @BindView(R.id.tvTwelve)
    TextView tvTwelve;
    @BindView(R.id.tvThriteen)
    TextView tvThriteen;
    @BindView(R.id.tvForTeen)
    EditText tvForTeen;
    Unbinder unbinder;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String detail_id;
    private String staff_id;
    private boolean isEnAble=false;

    private PermissionsBean permissionsBean;
    public static PersonInfoDetailFragment newInstance(String detail_id,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        PersonInfoDetailFragment fragment = new PersonInfoDetailFragment();
        fragment.detail_id = detail_id;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_info_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("人员信息详情");
        setEditFalse();
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("staff_id", detail_id);
        getPresenter().getStaffDetail(userBean.getStaff_token(), map);
        //appOperation 0新增 1编辑 2删除 3审核
        String appOperation = permissionsBean.getApp_operation();
        String[] strings = appOperation.split(",");
        if (isHave("1", strings)){
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("编辑");
        }else {
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

    @Override
    public void OnGetStaffDetail(StaffBean data) {



        staff_id=data.getStaff_id();
        tvName.setText(data.getStaff_name());
        tvNo.setText(data.getStaff_account());

        tvSex.setText(data.getSex());
        tvBirthday.setText(data.getBirthday());
        tvaddress.setText(data.getHome_address());
        tvCountry.setText(data.getNative_place());
        tvEducation.setText(data.getSchool_level());
        tvProfession.setText(data.getProfessional());
        tvNowAddress.setText(data.getContact_address());
        tvPhone.setText(data.getPhone());
        tvTel.setText(data.getTel());
        tvWeChat.setText(data.getWe_chat());

        tvOne.setText(data.getCompany_name());
        tvTwo.setText(data.getDepartment_name());
        tvThree.setText(data.getJob_name());
        tvFour.setText(data.getParent_staff_name());
        tvFive.setText(data.getHire_date());
        tvSix.setText(data.getObtainment_date());
        if ("0".equals(data.getIs_departure())) {
            tvSeven.setText("在职");
        } else {
            tvSeven.setText("离职");
        }
        tvEight.setText(data.getDeparture_date());

        tvNine.setText(data.getSocial_security());
        if ("0".equals(data.getIs_all_data())) {
            tvTen.setText("不具有");
        } else {
            tvTen.setText("具有");
        }
        if ("0".equals(data.getIs_disable())) {
            tvTwelve.setText("启用");
        } else {
            tvTwelve.setText("禁用");
        }
        //tvEleven.setText(data.getDepartment_name());
        tvForTeen.setText(data.getNote());
        tvThriteen.setText(data.getModule_role_names());


    }

    @Override
    public void OnUpdateStaff(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);

        setEditFalse();
        map.clear();
        map.put("staff_id", detail_id);
        getPresenter().getStaffDetail(userBean.getStaff_token(), map);
    }

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tvSex, R.id.tvBirthday, R.id.tvEducation, R.id.tvSix, R.id.tvSeven,
            R.id.tvEight, R.id.tvNine, R.id.tvTen, R.id.tvEleven, R.id.tvTwelve, R.id.tvFive, R.id.tvThriteen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                if ("编辑".equals(tvRight.getText().toString())) {
                    tvRight.setText("保存");
                    setEditTrue();
                } else {
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage("是否确认保存?");
                    builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            map.clear();
                            map.put("staff_id", staff_id);
                            map.put("sex", tvSex.getText().toString());
                            map.put("birthday", tvBirthday.getText().toString());
                            map.put("home_address", tvaddress.getText().toString());
                            map.put("native_place", tvCountry.getText().toString());
                            map.put("school_level", tvEducation.getText().toString());
                            map.put("professional", tvProfession.getText().toString());
                            map.put("contact_address", tvNowAddress.getText().toString());
                            map.put("phone", tvPhone.getText().toString());
                            map.put("tel", tvTel.getText().toString());
                            map.put("we_chat", tvWeChat.getText().toString());
                            map.put("note", tvForTeen.getText().toString());

                            map.put("hire_date", tvFive.getText().toString());
                            map.put("obtainment_date", tvSix.getText().toString());
                            String seven = tvSeven.getText().toString();
                            if (!TextUtils.isEmpty(seven)) {
                                if ("离职".equals(seven)) {
                                    map.put("is_departure", "1");
                                } else {
                                    map.put("is_departure", "0");
                                }
                            }
                            map.put("departure_date", tvEight.getText().toString());
                            map.put("social_security", tvNine.getText().toString());
                            String ten = tvTen.getText().toString();
                            if (!TextUtils.isEmpty(ten)) {
                                if ("具有".equals(ten)) {
                                    map.put("is_all_data", "1");
                                } else {
                                    map.put("is_all_data", "0");
                                }
                            }
                            String twelve = tvTwelve.getText().toString();
                            if (!TextUtils.isEmpty(twelve)) {
                                if ("禁用".equals(twelve)) {
                                    map.put("is_disable", "1");
                                } else {
                                    map.put("is_disable", "0");
                                }
                            }
                            map.put("is_select", "0");
                            map.put("is_app", "1");
                            map.put("module_id", permissionsBean.getMenu_id());
                            map.put("operation", "1");
                            map.put("module_role_names", tvThriteen.getText().toString());
                            getPresenter().updateStaff(userBean.getStaff_token(), map);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            tvRight.setText("编辑");
                            setEditFalse();
                            map.clear();
                            map.put("staff_id", detail_id);

                            getPresenter().getStaffDetail(userBean.getStaff_token(), map);
                            dialog.dismiss();
                        }
                    });
                    builder.onCreate().show();
                }
                break;
            case R.id.tvSex:
                if (!isEnAble){
                    return;
                }
                showSingleAlertDialog("性别", new String[]{"男", "女"});
                break;
            case R.id.tvBirthday:
                if (!isEnAble){
                    return;
                }
                chooseTime("出生年月");
                break;
            case R.id.tvEducation:
                if (!isEnAble){
                    return;
                }
                showSingleAlertDialog("最高学历", new String[]{"高中及以下", "大专", "本科", "硕士", "博士"});
                break;
            case R.id.tvFive:
                if (!isEnAble){
                    return;
                }
                chooseTime("入职日期");
                break;
            case R.id.tvSix:
                if (!isEnAble){
                    return;
                }
                chooseTime("转正日期");
                break;
            case R.id.tvSeven:
                if (!isEnAble){
                    return;
                }
                showSingleAlertDialog("是否离职", new String[]{"未离职", "离职"});
                break;
            case R.id.tvEight:
                if (!isEnAble){
                    return;
                }
                chooseTime("离职日期");
                break;
            case R.id.tvNine:
                if (!isEnAble){
                    return;
                }
                showSingleAlertDialog("社保情况", new String[]{"属地化", "自购", "上海", " 无"});
                break;
            case R.id.tvTen:
               /* if (!isEnAble){
                    return;
                }
                showSingleAlertDialog("部门数据权限", new String[]{"不具有", "具有"});*/
                break;
            case R.id.tvTwelve:
                if (!isEnAble){
                    return;
                }
                showSingleAlertDialog("是否禁用", new String[]{"不禁用", "禁用"});
                break;
            case R.id.tvThriteen:
        /*        if (!isEnAble){
                    return;
                }
                showSingleAlertDialog("系统身份", new String[]{"开发人员", "业务员", "管理员", " 客服", "仓管", "超级管理员"});*/
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
                    case "性别":
                        tvSex.setText(items[choose]);
                        break;
                    case "最高学历":
                        tvEducation.setText(items[choose]);
                        break;
                    case "是否离职":
                        tvSeven.setText(items[choose]);
                        break;
                    case "社保情况":
                        tvNine.setText(items[choose]);
                        break;
                    case "部门数据权限":
                        tvTen.setText(items[choose]);
                        break;
                    case "是否禁用":
                        tvTwelve.setText(items[choose]);
                        break;
                    case "系统身份":
                        tvThriteen.setText(items[choose]);
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

    private void chooseTime(final String type) {

        TimePickerView build = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String format = formatter.format(date);
                switch (type) {
                    case "出生年月":
                        tvBirthday.setText(format);
                        break;
                    case "入职日期":
                        tvFive.setText(format);
                        break;
                    case "转正日期":
                        tvSix.setText(format);
                        break;
                    case "离职日期":
                        tvEight.setText(format);
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
    public void OnGetStaffList(List<StaffBean> data, String total) {

    }


    @Override
    public PersonInfoPresenter createPresenter() {
        return new PersonInfoPresenter(getApp());
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

    public void setEditTrue() {
        isEnAble=true;
        tvSex.setBackgroundResource(R.drawable.setbar_mm);
        tvSex.setClickable(true);
        tvBirthday.setBackgroundResource(R.drawable.setbar_mm);
        tvBirthday.setClickable(true);
        tvaddress.setBackgroundResource(R.drawable.setbar_mm);
        tvaddress.setEnabled(true);
        tvCountry.setBackgroundResource(R.drawable.setbar_mm);
        tvCountry.setEnabled(true);
        tvEducation.setBackgroundResource(R.drawable.setbar_mm);
        tvEducation.setClickable(true);

        tvProfession.setBackgroundResource(R.drawable.setbar_mm);
        tvProfession.setEnabled(true);
        tvNowAddress.setBackgroundResource(R.drawable.setbar_mm);
        tvNowAddress.setEnabled(true);
        tvPhone.setBackgroundResource(R.drawable.setbar_mm);
        tvPhone.setEnabled(true);
        tvTel.setBackgroundResource(R.drawable.setbar_mm);
        tvTel.setEnabled(true);
        tvWeChat.setBackgroundResource(R.drawable.setbar_mm);
        tvWeChat.setEnabled(true);


        tvFive.setBackgroundResource(R.drawable.setbar_mm);
        tvFive.setClickable(true);
        tvSix.setBackgroundResource(R.drawable.setbar_mm);
        tvSix.setClickable(true);
        tvSeven.setBackgroundResource(R.drawable.setbar_mm);
        tvSeven.setClickable(true);
        tvEight.setBackgroundResource(R.drawable.setbar_mm);
        tvEight.setClickable(true);
        tvNine.setBackgroundResource(R.drawable.setbar_mm);
        tvNine.setClickable(true);
        //tvTen.setBackgroundResource(R.drawable.setbar_mm);
       // tvTen.setClickable(true);
        // tvEleven.setBackgroundResource(R.drawable.setbar_mm);
        //tvEleven.setClickable(true);
        tvTwelve.setBackgroundResource(R.drawable.setbar_mm);
        tvTwelve.setClickable(true);
       // tvThriteen.setBackgroundResource(R.drawable.setbar_mm);
       // tvThriteen.setClickable(true);
        tvForTeen.setBackgroundResource(R.drawable.setbar_mm);
        tvForTeen.setEnabled(true);

    }

    public void setEditFalse() {
        isEnAble=false;
        tvSex.setBackground(null);
        tvSex.setClickable(false);
        tvBirthday.setBackground(null);
        tvBirthday.setClickable(false);
        tvaddress.setBackground(null);
        tvaddress.setEnabled(false);
        tvCountry.setBackground(null);
        tvCountry.setEnabled(false);
        tvEducation.setBackground(null);
        tvEducation.setClickable(false);

        tvProfession.setBackground(null);
        tvProfession.setEnabled(false);
        tvNowAddress.setBackground(null);
        tvNowAddress.setEnabled(false);
        tvPhone.setBackground(null);
        tvPhone.setEnabled(false);
        tvTel.setBackground(null);
        tvTel.setEnabled(false);
        tvWeChat.setBackground(null);
        tvWeChat.setEnabled(false);

        tvFive.setBackground(null);
        tvFive.setClickable(false);
        tvSix.setBackground(null);
        tvSix.setClickable(false);
        tvSeven.setBackground(null);
        tvSeven.setClickable(false);
        tvEight.setBackground(null);
        tvEight.setClickable(false);
        tvNine.setBackground(null);
        tvNine.setClickable(false);
      //  tvTen.setBackground(null);
       // tvTen.setClickable(false);
        //tvEleven.setBackground(null);
        tvTwelve.setBackground(null);
        tvTwelve.setClickable(false);
       // tvThriteen.setBackground(null);
       // tvThriteen.setClickable(false);
        tvForTeen.setBackground(null);
        tvForTeen.setEnabled(false);

    }

}