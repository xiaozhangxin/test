package com.akan.qf.mvp.fragment.statistics;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.fragment.qifei.AreaPressureBean;
import com.akan.qf.mvp.fragment.qifei.BigAreaBean;
import com.akan.qf.mvp.fragment.qifei.bigAreaAdapter;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.TopMiddlePopup;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/12/3.
 */

public class BigTestPressureFragment extends BaseFragment<IPtRankView, PtRankPresenter> implements IPtRankView {


    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.bgTime)
    TextView bgTime;
    @BindView(R.id.tvStartTime)
    TextView tvStartTime;
    @BindView(R.id.tvEndTime)
    TextView tvEndTime;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;
    @BindView(R.id.tvDepartment)
    EditText tvDepartment;

    private List<BigAreaBean> list;
    private bigAreaAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;

    public static BigTestPressureFragment newInstance() {
        Bundle args = new Bundle();
        BigTestPressureFragment fragment = new BigTestPressureFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_pressuer_big_list;
    }

    @Override
    public void initUI() {
        tvTitle.setText("大区试压量排行");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("下属大区");
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new bigAreaAdapter(context, list);
        recycleView.setAdapterWithProgress(adapter);
        adapter.setNoMore(R.layout.view_nomore);
        //下拉刷新
        recycleView.setRefreshingColorResources(R.color.colorPrimaryNew);
        recycleView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                refresh();
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                refresh();
            }
        });
    }

    private void refresh() {
        String start = tvStartTime.getText().toString();
        if (TextUtils.isEmpty(start)) {
            map.put("start_time", "");
        } else {
            map.put("start_time", start);
        }
        String end = tvEndTime.getText().toString();
        if (TextUtils.isEmpty(end)) {
            map.put("end_time", "");
        } else {
            map.put("end_time", end);
        }

        map.put("page", page + "");
        map.put("group_name", tvDepartment.getText().toString());
        getPresenter().getAreaTestPressure(userBean.getStaff_token(), map);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        Date starDate = calendar.getTime();
        String str = formatter.format(starDate);
        tvStartTime.setText(str);
        map.put("type", "1");
        refresh();

    }

    @Override
    public void OnGetAreaTestPressure(List<BigAreaBean> data) {

        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }


    @OnClick({R.id.ivLeft, R.id.tvStartTime, R.id.tvEndTime, R.id.ok, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                getScreenPixels();
                setPopup(2);
                middlePopup.show(tvRight);
                break;
            case R.id.tvStartTime:
                showStartTime();
                break;
            case R.id.tvEndTime:
                showEndTime();
                break;
            case R.id.ok:
                page = 1;
                recycleView.setRefreshing(true);
                refresh();
                break;
        }
    }

    private TopMiddlePopup middlePopup;
    public static int screenW, screenH;

    /**
     * 获取屏幕的宽和高
     */
    public void getScreenPixels() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenW = metrics.widthPixels;
        screenH = metrics.heightPixels;
    }

    private ArrayList<String> getItemsName() {
        ArrayList<String> items = new ArrayList<String>();
        items.add("下属大区");
        items.add("南北大区");
        return items;
    }

    private void setPopup(int type) {
        middlePopup = new TopMiddlePopup(getActivity(), screenW, screenH,
                onItemClickListener, getItemsName(), type);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String s = getItemsName().get(position);
            tvRight.setText(s);
            if ("下属大区".equals(s)) {
                map.put("type", "1");
            } else {
                map.put("type", "0");
            }
            middlePopup.dismiss();
            page=1;
            recycleView.setRefreshing(true);
            refresh();
        }
    };


    private void showStartTime() {
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);//当前年
        int month = calendar.get(Calendar.MONTH);//当前月
        int day = calendar.get(Calendar.DAY_OF_MONTH);//当前日
        //new一个日期选择对话框的对象,并设置默认显示时间为当前的年月日时间.
        DatePickerDialog dialog = new DatePickerDialog(getContext(), mdateListener, year, month, day);
        dialog.show();
    }

    private void showEndTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), mdateListenerTwo, year, month, day);
        dialog.show();
    }

    /**
     * 日期选择的回调监听
     */
    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int years, int monthOfYear, int dayOfMonth) {
            String time = years + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            String s = formatDate(time);
            tvStartTime.setText(s);
        }


    };
    private DatePickerDialog.OnDateSetListener mdateListenerTwo = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int years, int monthOfYear, int dayOfMonth) {
            String time = years + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            String s = formatDate(time);
            tvEndTime.setText(s);
        }
    };

    //将日历选择的日期日起进行格式转换
    private String formatDate(String date) {
        Date parse = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            parse = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return simpleDateFormat.format(parse);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public PtRankPresenter createPresenter() {
        return new PtRankPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}