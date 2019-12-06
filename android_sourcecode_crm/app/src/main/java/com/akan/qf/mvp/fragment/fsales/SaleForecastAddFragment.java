package com.akan.qf.mvp.fragment.fsales;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import com.akan.qf.R;
import com.akan.qf.bean.ClassList;
import com.akan.qf.bean.DepartmentBean;
import com.akan.qf.bean.DepartmentEvent;
import com.akan.qf.bean.FinancialBean;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.SaleForecastBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.sale.SaleForecastPresenter;
import com.akan.qf.mvp.view.sale.ISaleForecastView;
import com.akan.qf.util.CashierInputFilter;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

public class SaleForecastAddFragment extends BaseFragment<ISaleForecastView, SaleForecastPresenter> implements ISaleForecastView {


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
    @BindView(R.id.tvWOne)
    TextView tvWOne;
    @BindView(R.id.tvWTwo)
    TextView tvWTwo;
    @BindView(R.id.tvWThree)
    TextView tvWThree;
    @BindView(R.id.tvWFour)
    TextView tvWFour;
    @BindView(R.id.tvOne)
    EditText tvOne;
    @BindView(R.id.tvTwo)
    EditText tvTwo;
    @BindView(R.id.tvThree)
    EditText tvThree;
    @BindView(R.id.tvOneTotal)
    TextView tvOneTotal;
    @BindView(R.id.tvFour)
    EditText tvFour;
    @BindView(R.id.tvFive)
    EditText tvFive;
    @BindView(R.id.tvSix)
    EditText tvSix;
    @BindView(R.id.tvTwoTotal)
    TextView tvTwoTotal;
    @BindView(R.id.tvSeven)
    EditText tvSeven;
    @BindView(R.id.tvEight)
    EditText tvEight;
    @BindView(R.id.tvNine)
    EditText tvNine;
    @BindView(R.id.tvThreeTotal)
    TextView tvThreeTotal;
    @BindView(R.id.tvTen)
    EditText tvTen;
    @BindView(R.id.tvEleven)
    EditText tvEleven;
    @BindView(R.id.tvTwelve)
    EditText tvTwelve;
    @BindView(R.id.tvFourTotal)
    TextView tvFourTotal;
    @BindView(R.id.etReason)
    EditText etReason;
    @BindView(R.id.ok)
    TextView ok;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String type;
    private String groud_id;
    private String class_id = "1";
    private FinancialBean data;
    private List<ClassList> classList;

    private PermissionsBean permissionsBean;
    public static SaleForecastAddFragment newInstance(FinancialBean bean, String type,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        SaleForecastAddFragment fragment = new SaleForecastAddFragment();
        fragment.data = bean;
        fragment.type = type;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_saleforecast_add;
    }


    @Override
    public void initUI() {
        classList = new ArrayList<>();
        InputFilter[] filters = {new CashierInputFilter(0)};
        tvOne.setFilters(filters);
        tvWTwo.setFilters(filters);
        tvThree.setFilters(filters);
        tvFour.setFilters(filters);
        tvFive.setFilters(filters);
        tvSix.setFilters(filters);
        tvSeven.setFilters(filters);
        tvEight.setFilters(filters);
        tvNine.setFilters(filters);
        tvTen.setFilters(filters);
        tvEleven.setFilters(filters);
        tvTwelve.setFilters(filters);
        initListener();
        if ("0".equals(type)) {
            getData();
            ok.setText("提交");
            tvTitle.setText("销售预测");

        } else {
            tvTitle.setText("修改销售预测");
            ok.setText("确认修改");
            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getApply_create_time());


        }
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getPresenter().getSaleTaskClassList(userBean.getStaff_token(), map);

        if ("0".equals(type)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
            Date curDate = new Date(System.currentTimeMillis());
            String str = formatter.format(curDate);
            tvTime.setText(str);
            tvName.setText(userBean.getStaff_name());
            tvOne.setText(userBean.getSimple_department_name());
            groud_id = userBean.getDepartment_id();
        } else {
            tvOne.setText(data.getApply_department());
            groud_id = data.getGroup_id();
        }
    }


    @Override
    public void OnGetSaleTaskClassList(List<ClassList> data) {
        classList.addAll(data);
    }

    @Override
    public void OnAddOrUpdateSaleForecast(String data) {
        if (dialogLoadding != null) {
            dialogLoadding.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(),data);
        tvOne.setText("");
        tvTwo.setText("");
        tvThree.setText("");
        tvFour.setText("");
        tvFive.setText("");
        tvSix.setText("");
        tvSeven.setText("");
        tvEight.setText("");
        tvNine.setText("");
        tvTen.setText("");
        tvEleven.setText("");
        tvTwelve.setText("");
        finish();

    }

    @Override
    public void OnDeleteSaleForecast(String data) {

    }

    @Override
    public void OnQuerySaleForecastPage(List<SaleForecastBean> data) {

    }

    private double doubleParseChange(String s) {
        if (s.length() > 0) {
            if (s.endsWith(".")) {
                s = s + "0";
            }
            return Double.parseDouble(s);
        }
        return 0.0;
    }



    @OnClick({R.id.ivLeft, R.id.tvWOne, R.id.tvWTwo, R.id.tvWThree, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvWOne:
                StartChooseDepartmentFragment("saleForcast");
                break;

            case R.id.tvWTwo:
                chooseTimeYear();
                break;
            case R.id.tvWThree:
                showSingleAlertDialog();
                break;
            case R.id.ok:
                String mtvOne = tvWOne.getText().toString().trim();
                if (TextUtils.isEmpty(mtvOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入部门");
                    return;
                }
                String mtvTwo = tvWTwo.getText().toString().trim();
                if (TextUtils.isEmpty(mtvTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择年份");
                    return;
                }
                String mtvThree = tvWThree.getText().toString().trim();
                if (TextUtils.isEmpty(mtvThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择产品");
                    return;
                }


                ok.setEnabled(false);
                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("group_id", groud_id);
                map.put("year", mtvTwo);
                map.put("class_id", class_id);

                map.put("all_sale", tvWFour.getText().toString());
                map.put("quarter1", tvOneTotal.getText().toString());
                map.put("quarter2", tvTwoTotal.getText().toString());
                map.put("quarter3", tvThreeTotal.getText().toString());
                map.put("quarter4", tvFourTotal.getText().toString());
                map.put("month1", tvOne.getText().toString());
                map.put("month2", tvTwo.getText().toString());
                map.put("month3", tvThree.getText().toString());
                map.put("month4", tvFour.getText().toString());
                map.put("month5", tvFive.getText().toString());
                map.put("month6", tvSix.getText().toString());
                map.put("month7", tvSeven.getText().toString());
                map.put("month8", tvEight.getText().toString());
                map.put("month9", tvNine.getText().toString());
                map.put("month10", tvTen.getText().toString());
                map.put("month11", tvEleven.getText().toString());
                map.put("month12", tvTwelve.getText().toString());

                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());
                map.put("operation", "0");
                if ("1".equals(type)) {
                    map.put("operation", "1");
                    map.put("forecast_id", data.getApply_id());
                }
                getPresenter().addOrUpdateSaleForecast(userBean.getStaff_token(), map);

                break;
        }
    }

    private DialogLoadding dialogLoadding;


    private void chooseTimeYear() {
        TimePickerView build = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
                String format = formatter.format(date);
                tvWTwo.setText(format);
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
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .isDialog(true)
                .build();
        build.show();

    }

    private AlertDialog alertDialog2;
    private int choose = 0;
    public void showSingleAlertDialog() {
        List<String> sigList = new ArrayList<>();
        for (ClassList mm : classList) {

            sigList.add(mm.getClass_name());
        }
        final String[] items = new String[classList.size()];
        sigList.toArray(items);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle("选择产品");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose=i;
            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvWThree.setText(items[choose]);
                class_id=classList.get(choose).getClass_id();
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

    @Override
    public SaleForecastPresenter createPresenter() {
        return new SaleForecastPresenter(getApp());
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
    public void onEventMainThread(DepartmentEvent event) {
        DepartmentBean bean = event.getmBean();
            tvWOne.setText(bean.getName());
            groud_id = bean.getUuid();

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


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private void initListener() {
        TextChange textChange = new TextChange();
        tvOne.addTextChangedListener(textChange);
        tvTwo.addTextChangedListener(textChange);
        tvThree.addTextChangedListener(textChange);
        tvFour.addTextChangedListener(textChange);
        tvFive.addTextChangedListener(textChange);
        tvSix.addTextChangedListener(textChange);
        tvSeven.addTextChangedListener(textChange);
        tvEight.addTextChangedListener(textChange);
        tvNine.addTextChangedListener(textChange);
        tvTen.addTextChangedListener(textChange);
        tvEleven.addTextChangedListener(textChange);
        tvTwelve.addTextChangedListener(textChange);

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
            String s1 = tvOne.getText().toString();
            String s2 = tvTwo.getText().toString();
            String s3 = tvThree.getText().toString();
            String s4 = tvFour.getText().toString();
            String s5 = tvFive.getText().toString();
            String s6 = tvSix.getText().toString();
            String s7 = tvSeven.getText().toString();
            String s8 = tvEight.getText().toString();
            String s9 = tvNine.getText().toString();
            String s10 = tvTen.getText().toString();
            String s11 = tvEleven.getText().toString();
            String s12 = tvTwelve.getText().toString();
            double f1 = new BigDecimal(doubleParseChange(s1) + doubleParseChange(s2) + doubleParseChange(s3)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            double f2 = new BigDecimal(doubleParseChange(s4) + doubleParseChange(s5) + doubleParseChange(s6)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            double f3 = new BigDecimal(doubleParseChange(s7) + doubleParseChange(s8) + doubleParseChange(s9)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            double f4 = new BigDecimal(doubleParseChange(s10) + doubleParseChange(s11) + doubleParseChange(s12)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            tvOneTotal.setText(f1 + "");
            tvTwoTotal.setText(f2 + "");
            tvThreeTotal.setText(f3 + "");
            tvFourTotal.setText(f4 + "");
            tvWFour.setText(""+(f1+f2+f3+f4));

        }
    }
}
