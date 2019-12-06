package com.akan.qf.mvp.fragment.fsales;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.MonthBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.SaleForecastBean;
import com.akan.qf.mvp.adapter.home.DispatchAdapter;
import com.akan.qf.mvp.base.SimpleFragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SaleForecastDetailFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.chart1)
    BarChart chart;
    Unbinder unbinder;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.textView20)
    TextView textView20;
    @BindView(R.id.tvPriceUnit)
    TextView tvPriceUnit;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.tvTittleTwo)
    TextView tvTittleTwo;
    @BindView(R.id.tvTopOne)
    TextView tvTopOne;
    @BindView(R.id.tvTopTwo)
    TextView tvTopTwo;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvTopThree)
    TextView tvTopThree;
    @BindView(R.id.tvTopFour)
    TextView tvTopFour;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    private SaleForecastBean mBean;
    ArrayList<MonthBean> list;
    DispatchAdapter adapter;

    private PermissionsBean permissionsBean;
    public static SaleForecastDetailFragment newInstance(SaleForecastBean mBean,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        SaleForecastDetailFragment fragment = new SaleForecastDetailFragment();
        fragment.mBean = mBean;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_dispatch_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("销售预测详情");
        textView20.setText("季度预测量");
        tvTopFour.setText("总计");
        //第一部分
        chart.getDescription().setEnabled(false);
        chart.setPinchZoom(true);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);
        final ArrayList<String> listtittle = new ArrayList<>();
        listtittle.add("第四季度");
        listtittle.add("第一季度");
        listtittle.add("第二季度");
        listtittle.add("第三季度");
        //x坐标轴设置
        XAxis xAxis = chart.getXAxis();
        xAxis.setLabelCount(listtittle.size());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(getResources().getColor(R.color.week_bar_xaxis));//x轴的颜色
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        //xAxis.setCenterAxisLabels(true);//设置标签居中
        String[] str = new String[listtittle.size()];
        for (int j = 0; j < listtittle.size(); j++) {
            str[j] = listtittle.get(j);
        }
        SaleForecastDetailFragment.LabelFormatter labelFormatter = new SaleForecastDetailFragment.LabelFormatter(str);
        xAxis.setValueFormatter(labelFormatter);


        //chart.getAxisLeft().setAxisMinValue(0f);
        chart.getAxisLeft().setDrawGridLines(false);
        // add a nice and smooth animation
        chart.animateY(1500);
        chart.getLegend().setEnabled(false);//左下角标题
        chart.getXAxis().setDrawGridLines(false);//网格线
        chart.getAxisRight().setDrawAxisLine(false);//右边y轴
        chart.getAxisLeft().setAxisLineColor(getResources().getColor(R.color.week_bar_xaxis));//y轴颜色
        chart.getAxisRight().setEnabled(false);
        //chart.getAxisLeft().setTextColor(R.color.colorTextG3);
        //chart.getAxisLeft().setLabelCount(5,true);//强制设置y轴的显示数量
        // xAxis.setXOffset(0);//设置坐标在原点
        chart.getAxisLeft().setAxisMinValue(0f);//设置坐标最小值
        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(1, (mBean.getMonth1() + mBean.getMonth2() + mBean.getMonth3())));
        values.add(new BarEntry(2, (mBean.getMonth4() + mBean.getMonth5() + mBean.getMonth6())));
        values.add(new BarEntry(3, (mBean.getMonth7() + mBean.getMonth8() + mBean.getMonth9())));
        values.add(new BarEntry(4, (mBean.getMonth10() + mBean.getMonth11() + mBean.getMonth12())));
        BarDataSet set1 = new BarDataSet(values, "季度目标量");
        // set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set1.setColors(new int[]{Color.rgb(65, 117, 255)});
        set1.setHighLightAlpha(37);
        set1.setDrawValues(true);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);


        BarData data = new BarData(dataSets);
        data.setValueTextSize(11);
        data.setBarWidth(0.3f);

        chart.setData(data);
        chart.setFitBars(true);
        chart.invalidate();

        //第二部分
        list = new ArrayList<>();
        list.add(new MonthBean("月份", "销售预测(元)"));
        list.add(new MonthBean("月份", "销售预测(元)"));
        list.add(new MonthBean("1", mBean.getMonth1() + ""));
        list.add(new MonthBean("7", mBean.getMonth7() + ""));
        list.add(new MonthBean("2", mBean.getMonth2() + ""));
        list.add(new MonthBean("8", mBean.getMonth8() + ""));
        list.add(new MonthBean("3", mBean.getMonth3() + ""));
        list.add(new MonthBean("9", mBean.getMonth9() + ""));
        list.add(new MonthBean("4", mBean.getMonth4() + ""));
        list.add(new MonthBean("10", mBean.getMonth10() + ""));
        list.add(new MonthBean("5", mBean.getMonth5() + ""));
        list.add(new MonthBean("11", mBean.getMonth11() + ""));
        list.add(new MonthBean("6", mBean.getMonth6() + ""));
        list.add(new MonthBean("12", mBean.getMonth12() + ""));
        recycleView.setLayoutManager(new GridLayoutManager(context, 2));
        recycleView.setNestedScrollingEnabled(false);
        adapter = new DispatchAdapter(context, list);
        recycleView.setAdapter(adapter);


        //第三部分
        switch (mBean.getArea_type()) {
            case "department":
            case "":
                tvTopOne.setText("部门");
                tvOne.setText(mBean.getGroup_name());
                break;
            case "staff":
                tvTopOne.setText("个人");
                tvOne.setText(mBean.getStaff_name());
                break;

        }


        if (TextUtils.isEmpty(mBean.getClass_name())) {
            tvTwo.setText("合计");
        } else {
            tvTwo.setText(mBean.getClass_name());
        }
        tvThree.setText(mBean.getYear() + "年");
        DecimalFormat df = new DecimalFormat("#0");
        tvFour.setText(addComma(mBean.getAll_sale() + "") + "元");
    }

    public static String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat(",###");
        return decimalFormat.format(Double.parseDouble(str));
    }

    class LabelFormatter implements IAxisValueFormatter {
        private final String[] mLabels;

        public LabelFormatter(String[] labels) {
            mLabels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            try {
                return mLabels[(int) value];
            } catch (Exception e) {
                e.printStackTrace();
                return mLabels[0];
            }
        }
    }


    @Override
    public void initData() {

    }

    @OnClick(R.id.ivLeft)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
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
