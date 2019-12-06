package com.akan.qf.mvp.fragment.fsales;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.SaleDataContrastBean;
import com.akan.qf.mvp.base.SimpleFragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/11/20.
 */

public class AnalysisOneFragment extends SimpleFragment {
    Unbinder unbinder;
    @BindView(R.id.tvTopOne)
    TextView tvTopOne;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTopTwo)
    TextView tvTopTwo;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvTopThree)
    TextView tvTopThree;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvTopFour)
    TextView tvTopFour;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.chart1)
    BarChart chart;
    @BindView(R.id.tvThreeTittle)
    TextView tvThreeTittle;
    @BindView(R.id.tvNowYear)
    TextView tvNowYear;
    @BindView(R.id.progressOne)
    View progressOne;
    @BindView(R.id.imgOne)
    ImageView imgOne;
    @BindView(R.id.tvNowSum)
    TextView tvNowSum;
    @BindView(R.id.tvOldYear)
    TextView tvOldYear;
    @BindView(R.id.progressTwo)
    View progressTwo;
    @BindView(R.id.imgTwo)
    ImageView imgTwo;
    @BindView(R.id.tvOldSum)
    TextView tvOldSum;
    @BindView(R.id.tvTittleTwo)
    TextView tvTittleTwo;
    @BindView(R.id.tvBottomOne)
    TextView tvBottomOne;
    @BindView(R.id.tvBottomTwo)
    TextView tvBottomTwo;
    @BindView(R.id.tvBottomThree)
    TextView tvBottomThree;
    @BindView(R.id.tvBottomFour)
    TextView tvBottomFour;
    @BindView(R.id.topTime)
    TextView topTime;
    @BindView(R.id.tvPercentage)
    TextView tvPercentage;
    private SaleDataContrastBean bean;

    public static AnalysisOneFragment newInstance(SaleDataContrastBean bean) {
        Bundle args = new Bundle();
        AnalysisOneFragment fragment = new AnalysisOneFragment();
        fragment.bean = bean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_analysisi_one;
    }

    @Override
    public void initUI() {

        //第一部分
        chart.getDescription().setEnabled(false);
        chart.setPinchZoom(true);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);
        final ArrayList<String> listtittle = new ArrayList<>();
        listtittle.add("月均");
        listtittle.add("目标");
        listtittle.add("实际");
        listtittle.add("差值");

        //x坐标轴设置
        XAxis xAxis = chart.getXAxis();
        xAxis.setLabelCount(listtittle.size());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(getResources().getColor(R.color.white));//x轴的颜色
        // xAxis.setDrawLabels(false);
        xAxis.setGranularity(1f);
        //xAxis.setCenterAxisLabels(true);//设置标签居中
        String[] str = new String[listtittle.size()];
        for (int j = 0; j < listtittle.size(); j++) {
            str[j] = listtittle.get(j);
        }
        LabelFormatter labelFormatter = new LabelFormatter(str);
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
        chart.getAxisLeft().setEnabled(false);
        //chart.getAxisLeft().setTextColor(R.color.colorTextG3);
        //chart.getAxisLeft().setLabelCount(5,true);//强制设置y轴的显示数量
        // xAxis.setXOffset(0);//设置坐标在原点
        chart.getAxisLeft().setAxisMinValue(0f);//设置坐标最小值
        ArrayList<BarEntry> values = new ArrayList<>();
        float all_task = bean.getAll_task();
        if (0 == all_task) {
            values.add(new BarEntry(1, 0));
            values.add(new BarEntry(2, 100));
            values.add(new BarEntry(3, 0));
        } else if (bean.getThis_year_sum() > all_task) {
            values.add(new BarEntry(1, 100));
            values.add(new BarEntry(2, (bean.getThis_year_sum() / all_task) * 100));
            values.add(new BarEntry(3, 0));
        } else {
            values.add(new BarEntry(1, 100));
            values.add(new BarEntry(2, (bean.getThis_year_sum() / all_task) * 100));
            values.add(new BarEntry(3, ((all_task - bean.getThis_year_sum()) / all_task) * 100));
        }


        // values.add(new BarEntry(4, bean.getThis_year_sum() / 12));
        BarDataSet set1 = new BarDataSet(values, "季度目标量");
        set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        //set1.setColors(new int[]{Color.rgb(65, 117, 255)});
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


        //第一部分
        DecimalFormat df = new DecimalFormat("#0");
        if (0 == all_task | bean.getThis_year_sum() > all_task) {
            tvOne.setText(addComma(all_task + "") + "元");
            tvTwo.setText(addComma(bean.getThis_year_sum() + "") + "元");
            tvThree.setText("0元");
            tvFour.setText("0元");
        } else {
            tvOne.setText(addComma(bean.getAll_task() + "") + "元");
            tvTwo.setText(addComma(bean.getThis_year_sum() + "") + "元");
            tvThree.setText(addComma((bean.getAll_task() - bean.getThis_year_sum()) + "") + "元");
            tvFour.setText((df.format((bean.getAll_task() - bean.getThis_year_sum()) / 12)) + "元");
        }
        String year = bean.getYear();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Calendar c = Calendar.getInstance();
        Date y = c.getTime();
        String thisYear = formatter.format(y);
        if (year.equals(thisYear + "")) {
            topTime.setText(bean.getYear() + "年01月-" + bean.getYear() + "年" + getLastMonth() + "月");
            tvNowYear.setText(bean.getYear() + "年01月-" + getLastMonth() + "月");
            tvOldYear.setText((Integer.parseInt(bean.getYear()) - 1) + "年01月" + getLastMonth() + "月");
        } else {
            topTime.setText(bean.getYear() + "年01月-" + bean.getYear() + "年12月");
            tvNowYear.setText(bean.getYear() + "年01月-12月");
            tvOldYear.setText((Integer.parseInt(bean.getYear()) - 1) + "年01月-12月");
        }
        //第三部分
      //  tvNowYear.setText(bean.getYear() + "年" + getLastMonth() + "01月");
        //tvOldYear.setText((Integer.parseInt(bean.getYear()) - 1) + "-" + getLastMonth() + "-01");
        float this_year_sum = bean.getThis_year_sum();
        float that_year_sum = bean.getThat_year_sum();
        tvNowSum.setText(addComma(this_year_sum + "") + "");
        tvOldSum.setText(addComma(that_year_sum + "") + "");
		if (that_year_sum !=0){
			tvPercentage.setText("增长率" + df.format((this_year_sum - that_year_sum) / that_year_sum * 100) + "%");
		} else {
			tvPercentage.setText("增长率" + df.format(0) + "%");
		}


        if (this_year_sum > that_year_sum) {
            ViewGroup.LayoutParams layoutParams1 = progressOne.getLayoutParams();
            layoutParams1.width = 500;
            progressOne.setLayoutParams(layoutParams1);
            ViewGroup.LayoutParams layoutParams2 = progressTwo.getLayoutParams();
            int i = (int) (500 * (that_year_sum / this_year_sum));
            if (i < 50) {
                layoutParams2.width = 50;
            } else {
                layoutParams2.width = i;
            }
            progressTwo.setLayoutParams(layoutParams2);
        } else if (this_year_sum < that_year_sum) {
            ViewGroup.LayoutParams layoutParams1 = progressOne.getLayoutParams();
            int j = (int) (500 * (this_year_sum / that_year_sum));
            if (j < 50) {
                layoutParams1.width = 50;
            } else {
                layoutParams1.width = j;
            }
            progressOne.setLayoutParams(layoutParams1);
            ViewGroup.LayoutParams layoutParams2 = progressTwo.getLayoutParams();
            layoutParams2.width = 500;
            progressTwo.setLayoutParams(layoutParams2);


        }

        //第四部分
        if (TextUtils.isEmpty(bean.getClass_name())) {
            tvBottomTwo.setText("合计");
        } else {
            tvBottomTwo.setText(bean.getClass_name());
        }
        tvBottomOne.setText(bean.getRegion());

        tvBottomThree.setText(addComma(bean.getAll_task() + "") + "元");
        tvBottomFour.setText(addComma(bean.getThis_year_sum() + "") + "元");

    }

    public static String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(Double.parseDouble(str));
    }

    /**
     * 获取上一个月
     *
     * @return
     */
    public String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("MM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
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

}
