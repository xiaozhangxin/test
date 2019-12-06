package com.akan.qf.mvp.fragment.bapproval;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.FinancialBean;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.CostStatisticsPresenter;
import com.akan.qf.mvp.view.home.ICostStatisticsView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.DialogLoadding;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2019/2/27.
 */

public class CostStatisticsAddFragment extends BaseFragment<ICostStatisticsView, CostStatisticsPresenter> implements ICostStatisticsView {


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
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    EditText tvFour;
    @BindView(R.id.tvFive)
    EditText tvFive;
    @BindView(R.id.tvSix)
    EditText tvSix;
    @BindView(R.id.tvSeven)
    EditText tvSeven;
    @BindView(R.id.tvNine)
    EditText tvNine;
    @BindView(R.id.tvTen)
    EditText tvTen;
    @BindView(R.id.tEleven)
    EditText tEleven;
    @BindView(R.id.tvTwelve)
    EditText tvTwelve;
    @BindView(R.id.tvThirteen)
    EditText tvThirteen;
    @BindView(R.id.tvFourteen)
    EditText tvFourteen;
    @BindView(R.id.tvFifteen)
    EditText tvFifteen;
    @BindView(R.id.tvSixteen)
    EditText tvSixteen;
    @BindView(R.id.tvSeventeen)
    EditText tvSeventeen;
    @BindView(R.id.tvEighteen)
    EditText tvEighteen;
    @BindView(R.id.tvNineteen)
    EditText tvNineteen;
    @BindView(R.id.tvTwenty)
    EditText tvTwenty;
    @BindView(R.id.tvTwentyOne)
    EditText tvTwentyOne;
    @BindView(R.id.tvTwentyTwo)
    EditText tvTwentyTwo;
    @BindView(R.id.tvTwentyThree)
    EditText tvTwentyThree;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.tvAddOne)
    EditText tvAddOne;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String type;
    private String groud_id;
    private FinancialBean data;

    private PermissionsBean permissionsBean;
    public static CostStatisticsAddFragment newInstance(FinancialBean bean, String type,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        CostStatisticsAddFragment fragment = new CostStatisticsAddFragment();
        fragment.data = bean;
        fragment.type = type;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_costtatistics_add;
    }


    @Override
    public void initUI() {
     /*   InputFilter[] filters = {new CashierInputFilter(2)};

        tvFour.setFilters(filters);
        tvFive.setFilters(filters);
        tvSix.setFilters(filters);
        tvSeven.setFilters(filters);

        tvNine.setFilters(filters);
        tvTen.setFilters(filters);
        tEleven.setFilters(filters);
        tvTwelve.setFilters(filters);
        tvThirteen.setFilters(filters);
        initListener();*/

        if ("0".equals(type)) {
            getData();
            ok.setText("提交");
            tvTitle.setText("费用统计");

        } else {
            tvTitle.setText("修改费用统计");
            ok.setText("确认修改");

            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getApply_create_time());
            tvDepartment.setText(data.getStaff_department());
            tvOne.setText(data.getApply_department());
            tvTwo.setText(data.getApply_year());
            tvThree.setText(data.getApply_month());

            tvFour.setText(data.getApply_income());
            tvFive.setText(data.getApply_others());
            tvSix.setText(data.getApply_cost());
            tvSeven.setText(data.getApply_claimant());
            tvNine.setText(data.getApply_ring());
            tvTen.setText(data.getApply_total());
            tEleven.setText(data.getApply_with());
            tvTwelve.setText(data.getApply_real());
            tvThirteen.setText(data.getApply_find());
            tvFourteen.setText(data.getApply_rate());
            tvFifteen.setText(data.getApply_market());
            tvSixteen.setText(data.getApply_person());
            tvSeventeen.setText(data.getApply_carry());
            tvEighteen.setText(data.getApply_office());
            tvNineteen.setText(data.getApply_entertain());
            tvTwenty.setText(data.getApply_advert());
            tvTwentyOne.setText(data.getApply_depreciation());
            tvTwentyTwo.setText(data.getApply_account());
            tvTwentyThree.setText(data.getApply_remark());
            tvAddOne.setText(data.getApply_pay());

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
        if ("0".equals(type)) {
            tvOne.setText(userBean.getSimple_department_name());
            groud_id = userBean.getDepartment_id();
        } else {
            tvOne.setText(data.getApply_department());
            groud_id = data.getGroup_id();
        }
    }

    private void initListener() {
        TextChange textChange = new TextChange();
        tvFour.addTextChangedListener(textChange);
        tvFive.addTextChangedListener(textChange);
        tvSix.addTextChangedListener(textChange);
        tvSeven.addTextChangedListener(textChange);
        tvNine.addTextChangedListener(textChange);
        tvTen.addTextChangedListener(textChange);
        tEleven.addTextChangedListener(textChange);
        tvTwelve.addTextChangedListener(textChange);
        tvThirteen.addTextChangedListener(textChange);


    }

    private class TextChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Double t1 = 0.0, t2 = 0.0, t3 = 0.0, t4 = 0.0, t5 = 0.0, t6 = 0.0, t7 = 0.0, t8 = 0.0, t9 = 0.0, t10 = 0.0;
            String s1 = tvFour.getText().toString();
            String s2 = tvFive.getText().toString();
            String s3 = tvSix.getText().toString();
            String s4 = tvSeven.getText().toString();
            String s6 = tvNine.getText().toString();
            String s7 = tvTen.getText().toString();
            String s8 = tEleven.getText().toString();
            String s9 = tvTwelve.getText().toString();
            String s10 = tvThirteen.getText().toString();
            if (s1.length() > 0) {
                if (s1.endsWith(".")) {
                    s1 = s1 + "0";
                }
                t1 = Double.parseDouble(s1);
            }
            if (s2.length() > 0) {
                if (s2.endsWith(".")) {
                    s3 = s1 + "0";
                }
                t2 = Double.parseDouble(s2);
            }
            if (s3.length() > 0) {
                if (s3.endsWith(".")) {
                    s3 = s1 + "0";
                }
                t3 = Double.parseDouble(s3);
            }
            if (s4.length() > 0) {
                if (s4.endsWith(".")) {
                    s4 = s1 + "0";
                }
                t4 = Double.parseDouble(s4);
            }

            if (s6.length() > 0) {
                if (s6.endsWith(".")) {
                    s6 = s1 + "0";
                }
                t6 = Double.parseDouble(s6);
            }
            if (s7.length() > 0) {
                if (s7.endsWith(".")) {
                    s7 = s1 + "0";
                }
                t7 = Double.parseDouble(s7);
            }
            if (s8.length() > 0) {
                if (s8.endsWith(".")) {
                    s8 = s1 + "0";
                }
                t8 = Double.parseDouble(s8);
            }
            if (s9.length() > 0) {
                if (s9.endsWith(".")) {
                    s9 = s1 + "0";
                }
                t9 = Double.parseDouble(s9);
            }
            if (s10.length() > 0) {
                if (s10.endsWith(".")) {
                    s10 = s1 + "0";
                }
                t10 = Double.parseDouble(s10);
            }
            double v = t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10;
            BigDecimal bg3 = new BigDecimal(v);
            double f3 = bg3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            tvFourteen.setText(f3 + "");
        }
    }

    @OnClick({R.id.tvThree, R.id.ivLeft, R.id.ok, R.id.tvTwo, R.id.tvOne})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvOne:
                StartChooseDepartmentFragment("CostSt");
                break;

            case R.id.tvTwo:
                chooseTimeYear();
                break;
            case R.id.tvThree:
                chooseTime();
                break;
            case R.id.ok:
                String mtvOne = tvOne.getText().toString().trim();
                if (TextUtils.isEmpty(mtvOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入部门");
                    return;
                }
                String mtvTwo = tvTwo.getText().toString().trim();
                if (TextUtils.isEmpty(mtvTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择年份");
                    return;
                }
                String mtvThree = tvThree.getText().toString().trim();
                if (TextUtils.isEmpty(mtvThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择月份");
                    return;
                }


                ok.setEnabled(false);
                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("apply_department", mtvOne);
                map.put("group_id", groud_id);
                map.put("apply_year", mtvTwo);
                map.put("apply_month", mtvThree);

                map.put("apply_income", tvFour.getText().toString());
                map.put("apply_others", tvFive.getText().toString());
                map.put("apply_cost", tvSix.getText().toString());
                map.put("apply_claimant", tvSeven.getText().toString());
                map.put("apply_ring", tvNine.getText().toString());
                map.put("apply_total", tvTen.getText().toString());
                map.put("apply_with", tEleven.getText().toString());
                map.put("apply_real", tvTwelve.getText().toString());
                map.put("apply_find", tvThirteen.getText().toString());
                map.put("apply_rate", tvFourteen.getText().toString());
                map.put("apply_market", tvFifteen.getText().toString());
                map.put("apply_person", tvSixteen.getText().toString());
                map.put("apply_carry", tvSeventeen.getText().toString());
                map.put("apply_office", tvEighteen.getText().toString());
                map.put("apply_entertain", tvNineteen.getText().toString());
                map.put("apply_advert", tvTwenty.getText().toString());
                map.put("apply_depreciation", tvTwentyOne.getText().toString());
                map.put("apply_account", tvTwentyTwo.getText().toString());
                map.put("apply_remark", tvTwentyThree.getText().toString());
                map.put("apply_pay", tvAddOne.getText().toString());

                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());
                map.put("operation", "0");
                if ("1".equals(type)) {
                    map.put("operation", "1");
                    map.put("apply_id", data.getApply_id());
                }
                getPresenter().insertOrUpdateStatistics(userBean.getStaff_token(), map);

                break;
        }
    }

    private DialogLoadding dialogLoadding;

    private void chooseTime() {
        TimePickerView build = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM");
                String format = formatter.format(date);
                tvThree.setText(format);
            }
        })
                .setType(new boolean[]{false, true, false, false, false, false})
                .setCancelText("取消")
                .setSubmitText("确认")
                .setTitleSize(15)
                .setSubCalSize(14)
                .setTitleText("月份")
                .setOutSideCancelable(false)
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)
                .setTitleBgColor(0xFF4DA9EB)
                .setBgColor(0xFFFFFFFF)
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .isDialog(true)
                .build();
        build.show();

    }

    private void chooseTimeYear() {
        TimePickerView build = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
                String format = formatter.format(date);
                tvTwo.setText(format);
            }
        })
                .setType(new boolean[]{true, false, false, false, false, false})
                .setCancelText("取消")
                .setSubmitText("确认")
                .setTitleSize(15)
                .setSubCalSize(14)
                .setTitleText("年份")
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
    public CostStatisticsPresenter createPresenter() {
        return new CostStatisticsPresenter(getApp());
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getMsg();
        if (msg.contains("CostSt")) {
            tvOne.setText(msg.substring(6, msg.indexOf("+")));
            groud_id = msg.substring((msg.indexOf("+") + 1), msg.length());
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
    public void OnInsertOrUpdateStatistics(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvOne.setText("");
        tvTwo.setText("");
        tvThree.setText("");
        tvFour.setText("");

        tvFive.setText("");
        tvSix.setText("");
        tvSeven.setText("");
        tvNine.setText("");
        tvTen.setText("");
        tEleven.setText("");
        tvTwelve.setText("");
        tvThirteen.setText("");
        tvFourteen.setText("");
        tvFifteen.setText("");
        tvSixteen.setText("");
        tvSeventeen.setText("");
        tvEighteen.setText("");
        tvNineteen.setText("");
        tvTwenty.setText("");
        tvTwentyOne.setText("");
        tvTwentyTwo.setText("");
        tvAddOne.setText("");
        tvTwentyThree.setText("");

        finish();
    }

    @Override
    public void OnGetFinancialStatisticsList(List<FinancialBean> data, String total) {

    }

    @Override
    public void OnGetFinancialStatistics(FinancialBean data) {

    }

    @Override
    public void OnDeleteFinancialStatistics(String data) {

    }

    private void saveData() {
        FinancialBean bean = new FinancialBean();


        bean.setApply_department(tvOne.getText().toString());
        bean.setApply_year(tvTwo.getText().toString());
        bean.setApply_month(tvThree.getText().toString());
        bean.setApply_income(tvFour.getText().toString());
        bean.setApply_others(tvFive.getText().toString());
        bean.setApply_cost(tvSix.getText().toString());
        bean.setApply_claimant(tvSeven.getText().toString());
        bean.setApply_ring(tvNine.getText().toString());
        bean.setApply_total(tvTen.getText().toString());
        bean.setApply_with(tEleven.getText().toString());
        bean.setApply_real(tvTwelve.getText().toString());
        bean.setApply_find(tvThirteen.getText().toString());
        bean.setApply_rate(tvFourteen.getText().toString());
        bean.setApply_market(tvFifteen.getText().toString());
        bean.setApply_person(tvSixteen.getText().toString());
        bean.setApply_carry(tvSeventeen.getText().toString());
        bean.setApply_office(tvEighteen.getText().toString());
        bean.setApply_entertain(tvNineteen.getText().toString());
        bean.setApply_advert(tvTwenty.getText().toString());
        bean.setApply_depreciation(tvTwentyOne.getText().toString());
        bean.setApply_account(tvTwentyTwo.getText().toString());
        bean.setApply_remark(tvTwentyThree.getText().toString());
        bean.setApply_pay(tvAddOne.getText().toString());

        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("coststatistic", s);
        editor.commit();
    }

    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("coststatistic", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);

                tvOne.setText(object.getString("apply_department"));
                tvTwo.setText(object.getString("apply_year"));
                tvThree.setText(object.getString("apply_month"));
                tvFour.setText(object.getString("apply_income"));

                tvFive.setText(object.getString("apply_others"));
                tvSix.setText(object.getString("apply_cost"));
                tvSeven.setText(object.getString("apply_claimant"));
                tvNine.setText(object.getString("apply_ring"));
                tvTen.setText(object.getString("apply_total"));
                tEleven.setText(object.getString("apply_with"));
                tvTwelve.setText(object.getString("apply_real"));
                tvThirteen.setText(object.getString("apply_find"));
                tvFourteen.setText(object.getString("apply_rate"));
                tvFifteen.setText(object.getString("apply_market"));
                tvSixteen.setText(object.getString("apply_person"));
                tvSeventeen.setText(object.getString("apply_carry"));
                tvEighteen.setText(object.getString("apply_office"));
                tvNineteen.setText(object.getString("apply_entertain"));
                tvTwenty.setText(object.getString("apply_advert"));
                tvTwentyOne.setText(object.getString("apply_depreciation"));
                tvTwentyTwo.setText(object.getString("apply_account"));
                tvTwentyThree.setText(object.getString("apply_remark"));
                tvAddOne.setText(object.getString("apply_pay"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
