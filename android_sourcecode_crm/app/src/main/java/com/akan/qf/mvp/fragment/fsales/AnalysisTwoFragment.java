package com.akan.qf.mvp.fragment.fsales;

import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akan.qf.R;
import com.akan.qf.bean.AnalysisBean;
import com.akan.qf.bean.SaleDataContrastBean;
import com.akan.qf.mvp.adapter.home.AnalysisAdapter;
import com.akan.qf.mvp.base.SimpleFragment;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/11/20.
 */

public class AnalysisTwoFragment extends SimpleFragment {

    @BindView(R.id.combinedChart)
    CombinedChart dataChart;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    Unbinder unbinder;
    private SaleDataContrastBean bean;
    private CombinedData data;
    ArrayList<AnalysisBean> list;
    AnalysisAdapter adapter;

    public static AnalysisTwoFragment newInstance(SaleDataContrastBean bean) {
        Bundle args = new Bundle();
        AnalysisTwoFragment fragment = new AnalysisTwoFragment();
        fragment.bean = bean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_analysisi_two;
    }

    @Override
    public void initUI() {

        showDataOnChart();
        Legend legend = dataChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        //第二部分
        list = new ArrayList<>();
        list.add(new AnalysisBean("月份", "销售量(元)", "完成率", "增长率"));
        DecimalFormat df = new DecimalFormat("0.0");
        String one = df.format((bean.getThis_month1() - bean.getThat_month1()) / bean.getThat_month1() * 100) + "%";
        String two = df.format((bean.getThis_month2() - bean.getThat_month2()) / bean.getThat_month2() * 100) + "%";
        String three = df.format((bean.getThis_month3() - bean.getThat_month3()) / bean.getThat_month3() * 100) + "%";
        String four = df.format((bean.getThis_month4() - bean.getThat_month4()) / bean.getThat_month4() * 100) + "%";
        String five = df.format((bean.getThis_month5() - bean.getThat_month5()) / bean.getThat_month5() * 100) + "%";
        String six = df.format((bean.getThis_month6() - bean.getThat_month6()) / bean.getThat_month6() * 100) + "%";
        String seven = df.format((bean.getThis_month7() - bean.getThat_month7()) / bean.getThat_month7() * 100) + "%";
        String eight = df.format((bean.getThis_month8() - bean.getThat_month8()) / bean.getThat_month8() * 100) + "%";
        String nine = df.format((bean.getThis_month9() - bean.getThat_month9()) / bean.getThat_month9() * 100) + "%";
        String ten = df.format((bean.getThis_month10() - bean.getThat_month10()) / bean.getThat_month10() * 100) + "%";
        String eleven = df.format((bean.getThis_month11() - bean.getThat_month11()) / bean.getThat_month11() * 100) + "%";
        String twelve = df.format((bean.getThis_month12() - bean.getThat_month12()) / bean.getThat_month12() * 100) + "%";

		if (bean.getThat_month1() == 0){
			one = "0%";
		}

		if (bean.getThat_month2() == 0){
			two = "0%";
		}

		if (bean.getThat_month3() == 0){
			three = "0%";
		}
		
		if (bean.getThat_month4() == 0){
			four = "0%";
		}
		
		if (bean.getThat_month5() == 0){
			five = "0%";
		}
		
		if (bean.getThat_month6() == 0){
			six = "0%";
		}
		
		if (bean.getThat_month7() == 0){
			seven = "0%";
		}
		
		if (bean.getThat_month8() == 0){
			eight = "0%";
		}
		
		if (bean.getThat_month9() == 0){
			nine = "0%";
		}
		
		if (bean.getThat_month10() == 0){
			ten = "0%";
		}
		
		if (bean.getThat_month11() == 0){
			eleven = "0%";
		}
		
		if (bean.getThat_month12() == 0){
			twelve = "0%";
		}


        list.add(new AnalysisBean("1", bean.getThis_month1() + "", df.format(bean.getThis_month1() / bean.getTask_month1() * 100) + "%", one));
        list.add(new AnalysisBean("2", bean.getThis_month2() + "", df.format(bean.getThis_month2() / bean.getTask_month2() * 100 )+ "%", two));
        list.add(new AnalysisBean("3", bean.getThis_month3() + "", df.format(bean.getThis_month3() / bean.getTask_month3() * 100) + "%", three));
        list.add(new AnalysisBean("4", bean.getThis_month4() + "", df.format(bean.getThis_month4() / bean.getTask_month4() * 100) + "%", four));
        list.add(new AnalysisBean("5", bean.getThis_month5() + "", df.format(bean.getThis_month5() / bean.getTask_month5() * 100) + "%", five));
        list.add(new AnalysisBean("6", bean.getThis_month6() + "", df.format(bean.getThis_month6() / bean.getTask_month6() * 100) + "%", six));
        list.add(new AnalysisBean("7", bean.getThis_month7() + "", df.format(bean.getThis_month7() / bean.getTask_month7() * 100) + "%", seven));
        list.add(new AnalysisBean("8", bean.getThis_month8() + "", df.format(bean.getThis_month8() / bean.getTask_month8() * 100) + "%", eight));
        list.add(new AnalysisBean("9", bean.getThis_month9() + "", df.format(bean.getThis_month9() / bean.getTask_month9() * 100) + "%", nine));
        list.add(new AnalysisBean("10", bean.getThis_month10() + "", df.format(bean.getThis_month10() / bean.getTask_month10() * 100) + "%", ten));
        list.add(new AnalysisBean("11", bean.getThis_month11() + "", df.format(bean.getThis_month11() / bean.getTask_month11() * 100) + "%", eleven));
        list.add(new AnalysisBean("12", bean.getThis_month12() + "", df.format(bean.getThis_month12() / bean.getTask_month12() * 100) + "%", twelve));


        recycleView.setLayoutManager(new LinearLayoutManager(context));
        recycleView.setNestedScrollingEnabled(false);
        adapter = new AnalysisAdapter(context, list);
        recycleView.setAdapter(adapter);


    }

    /**
     * 展示数据
     */
    private void showDataOnChart() {
        //绘制图表数据
        data = new CombinedData();
        //设置折线图数据
        data.setData(getLineData());
        //设置柱状图数据
        data.setData(getBarData());
        dataChart.setData(data);
        //设置横坐标数据
        //  setAxisXBottom();
        //设置右侧纵坐标数据
        setAxisYRight();
        //设置左侧纵坐标数据
        setAxisYLeft();
        dataChart.setTouchEnabled(true);
        dataChart.getDescription().setEnabled(false);
        dataChart.setDrawGridBackground(false);
        dataChart.setDrawBarShadow(false);
        dataChart.setHighlightFullBarEnabled(false);
        dataChart.animateY(2000);

        // 设置是否绘制边框
        dataChart.setDrawBorders(false);
        // 设置是否可以缩放图表
        dataChart.setScaleEnabled(true);
        // 设置是否可以用手指移动图表
        dataChart.setDragEnabled(true);

        Matrix matrix = new Matrix();
        // x轴放大4倍，y不变
        matrix.postScale(2.0f, 1.0f);
        // 设置缩放
        dataChart.getViewPortHandler().refresh(matrix, dataChart, false);

        XAxis xAxis = dataChart.getXAxis();

        // 是否显示x轴线
        xAxis.setDrawAxisLine(true);
        // 设置x轴线的颜色
        xAxis.setAxisLineColor(Color.parseColor("#70C0FF"));
        // 是否绘制x方向网格线
        xAxis.setDrawGridLines(false);
        //x方向网格线的颜色
        xAxis.setGridColor(Color.parseColor("#30FFFFFF"));

        // 设置x轴数据的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 设置x轴文字的大小
        xAxis.setTextSize(9);
        xAxis.setXOffset(0);


        // 设置x轴数据偏移量
        xAxis.setYOffset(9f);
        final List<String> labels = new ArrayList<>();
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");
        labels.add("5");
        labels.add("6");
        labels.add("7");
        labels.add("8");
        labels.add("9");
        labels.add("10");
        labels.add("11");
        labels.add("12");
        // 显示x轴标签
        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                if (index < 0 || index >= labels.size()) {
                    return "";
                }
                return labels.get(index);
                // return labels.get(Math.min(Math.max((int) value, 0), labels.size() - 1));
            }

        };
        // 引用标签
        xAxis.setValueFormatter(formatter);
        // 设置x轴文字颜色
        xAxis.setTextColor(dataChart.getResources().getColor(R.color.colorTextG3));
        // 设置x轴每最小刻度 interval
        xAxis.setGranularity(0f);


    }

    /**
     * 设置横坐标数据
     */
    private void setAxisXBottom() {
        List<String> valuesX = new ArrayList<>();
        String date[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        for (int i = 0; i < date.length; i++) {
            valuesX.add(date[i]);
        }
        XAxis bottomAxis = dataChart.getXAxis();
        bottomAxis.setDrawGridLines(false);//网格线
        bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //bottomAxis.setCenterAxisLabels(true);
        bottomAxis.setValueFormatter(new IndexAxisValueFormatter(valuesX));
        bottomAxis.setAxisMinimum(data.getXMin());
        bottomAxis.setAxisMaximum(data.getXMax());
        bottomAxis.setAxisLineColor(getResources().getColor(R.color.week_bar_xaxis));//x轴的颜色
        bottomAxis.setLabelCount(date.length);
        bottomAxis.setAvoidFirstLastClipping(false);
        bottomAxis.setXOffset(0);//设置坐标在原点
    }

    /**
     * 设置右侧纵坐标数据
     */
    private void setAxisYRight() {
        YAxis right = dataChart.getAxisRight();
        right.setDrawAxisLine(false);//右边y轴
        right.setDrawGridLines(false);
        right.setEnabled(false);
        right.setAxisMinimum(-20f);//为坐标轴设置最小值
    }

    /**
     * 设置左侧纵坐标数据
     */
    private void setAxisYLeft() {
        YAxis yAxis = dataChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setAxisMinValue(0f);
        yAxis.setAxisLineColor(getResources().getColor(R.color.week_bar_xaxis));//x轴的颜色
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value + "";
            }
        });


        //设置x轴的最大值
        //yAxis.setAxisMaximum(1);
        // 设置y轴的最大值
        //yAxis.setAxisMinimum(1);
        // 不显示y轴
        //yAxis.setDrawAxisLine(false);
        // 设置y轴数据的位置
        // yAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        // 不从y轴发出横向直线
        // yAxis.setDrawGridLines(false);
        // 是否显示y轴坐标线
        //yAxis.setDrawZeroLine(true);
        // 设置y轴的文字颜色
        // 设置y轴文字的大小
        // yAxis.setTextSize(12);
        // 设置y轴数据偏移量
        yAxis.setXOffset(-30);
        yAxis.setYOffset(-30);
        yAxis.setXOffset(0);
        // 设置y轴label 数量
        // yAxis.setLabelCount(12, false);
        // 设置y轴的最小刻度
        //yAxis.setGranularity(1);//interval


    }

    /**
     * 设置折线图绘制数据
     *
     * @return
     */
    public LineData getLineData() {
        LineData lineData = new LineData();
        List<Entry> customCounts = new ArrayList<>();
        float[] data = {bean.getTask_month1(), bean.getTask_month2(), bean.getTask_month3(),
                bean.getTask_month4(), bean.getTask_month5(), bean.getTask_month6(),
                bean.getTask_month7(), bean.getTask_month8(), bean.getTask_month9(),
                bean.getTask_month10(), bean.getTask_month11(), bean.getTask_month12()};
        //人数
        for (int i = 0; i < data.length; i++) {
            customCounts.add(new Entry(i + 0.5f, data[i]));
        }
        LineDataSet lineDataSet = new LineDataSet(customCounts, "分派目标量");
        lineDataSet.setDrawValues(false);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setColor(Color.parseColor("#4175FF"));
        lineDataSet.setCircleColor(Color.parseColor("#4175FF"));
        lineDataSet.setCircleRadius(3);
        lineDataSet.setLineWidth(1);
        lineDataSet.setValueTextSize(9);
        lineDataSet.setValueTextColor(Color.parseColor("#4175FF"));
        lineData.addDataSet(lineDataSet);
        return lineData;
    }

    /**
     * 设置柱状图绘制数据
     *
     * @return
     */
    public BarData getBarData() {
        BarData barData = new BarData();
        //今年完成量
        List<BarEntry> amounts = new ArrayList<>();
        float z[] = {

                bean.getThis_month1(), bean.getThis_month2(), bean.getThis_month3(),
                bean.getThis_month4(), bean.getThis_month5(), bean.getThis_month6(),
                bean.getThis_month7(), bean.getThis_month8(), bean.getThis_month9(),
                bean.getThis_month10(), bean.getThis_month11(), bean.getThis_month12()};
        //去年完成量
        List<BarEntry> averages = new ArrayList<>();
        float j[] = {bean.getThat_month1(), bean.getThat_month2(), bean.getThat_month3(),
                bean.getThat_month4(), bean.getThat_month5(), bean.getThat_month6(),
                bean.getThat_month7(), bean.getThat_month8(), bean.getThat_month9(),
                bean.getThat_month10(), bean.getThat_month11(), bean.getThat_month12()};
        //添加数据
        for (int i = 0; i < z.length; i++) {

            averages.add(new BarEntry(i, j[i]));
            amounts.add(new BarEntry(i, z[i]));
        }
        //设置总数的柱状图
        BarDataSet amountBar = new BarDataSet(amounts, "今年实际完成量");
        amountBar.setDrawValues(false);
        amountBar.setAxisDependency(YAxis.AxisDependency.LEFT);
        amountBar.setColor(Color.parseColor("#74C0FF"));
        //设置平均的柱状图
        BarDataSet averageBar = new BarDataSet(averages, "去年实际完成量");
        averageBar.setDrawValues(false);
        averageBar.setAxisDependency(YAxis.AxisDependency.LEFT);
        averageBar.setColor(Color.parseColor("#FF9494"));
        amountBar.setValueTextSize(9);
        averageBar.setValueTextSize(9);
        barData.addDataSet(amountBar);
        barData.addDataSet(averageBar);
        //设置柱状图显示的大小
        float groupSpace = 0.16f;
        float barSpace = 0.02f;
        float barWidth = 0.45f;
        barData.setBarWidth(barWidth);
        barData.groupBars(0, groupSpace, barSpace);
        return barData;
    }

    @Override
    public void initData() {

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
