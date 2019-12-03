package com.akan.wms.mvp.fragment.out;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.SaleShipBean;
import com.akan.wms.bean.SaleShipTypeBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.home.OutSaleListAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.OutSalePresenter;
import com.akan.wms.mvp.view.home.IOutSaleView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.akan.wms.view.DropDownView.DropDownMenu;
import com.akan.wms.view.DropDownView.GirdDropDownAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OutSaleListFragment extends BaseFragment<IOutSaleView, OutSalePresenter> implements IOutSaleView, View.OnClickListener {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;


    private List<SaleShipBean> list;
    private OutSaleListAdapter adapter;
    private int page = 1;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;


    private String headers[] = {"全部状态", "条件筛选"};
    private String mOne[] = {"全部", "已配货", "已出库", "已装车"};
    private List<View> popupViews = new ArrayList<>();
    private GirdDropDownAdapter oneAdapter;
    private EditText tvOne;
    private EditText tvTwo;
    private TextView tvThree;
    private TextView tvStateTime;
    private TextView tvEndTime;
    private TextView tvClear;
    private TextView tvOk;
    private int deletePosition;

    public static OutSaleListFragment newInstance() {
        Bundle args = new Bundle();
        OutSaleListFragment fragment = new OutSaleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_list_public_new;
    }

    @Override
    public void initUI() {
        tvTitle.setText("销售出库单");
        ivRight.setVisibility(View.VISIBLE);
        initMenu();
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new OutSaleListAdapter(context, list);
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                deletePosition=position;
                startOutSaleDetailFragment(adapter.getItem(position).getId() + "");
            }
        });
        adapter.setNoMore(R.layout.view_nomore);
        recyclerView.setRefreshingColorResources(R.color.colorPrimary);
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();
    }

    private void refresh() {
        map.put("org_id", userBean.getOrg_id());
        map.put("page", page + "");
        getPresenter().querySaleShipPage(userBean.getStaff_token(), map);
    }

    //初始化选择栏
    private void initMenu() {
        final ListView oneView = new ListView(context);
        oneView.setDividerHeight(0);
        oneAdapter = new GirdDropDownAdapter(context, Arrays.asList(mOne));
        oneView.setAdapter(oneAdapter);
        View twoView = LayoutInflater.from(context).inflate(R.layout.top_select_out_sale, null);
        popupViews.add(oneView);
        popupViews.add(twoView);
        oneView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                oneAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : mOne[position]);
                mDropDownMenu.closeMenu();
                switch (mOne[position]) {
                    case "全部":
                        map.put("status", "");
                        break;
                    case "已配货":
                        map.put("status", "0");
                        break;
                    case "已出库":
                        map.put("status", "1");
                        break;
                    case "已装车":
                        map.put("status", "2");
                        break;
                }
                page = 1;
                refresh();
            }
        });
        tvOne = twoView.findViewById(R.id.tvOne);
        tvTwo = twoView.findViewById(R.id.tvTwo);
        tvThree = twoView.findViewById(R.id.tvThree);

        tvStateTime = twoView.findViewById(R.id.tvStateTime);
        tvEndTime = twoView.findViewById(R.id.tvEndTime);
        tvClear = twoView.findViewById(R.id.tvClear);
        tvOk = twoView.findViewById(R.id.tvOk);
        tvThree.setOnClickListener(this);
        tvStateTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        tvClear.setOnClickListener(this);
        tvOk.setOnClickListener(this);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvStateTime:
                showStartTime();
                break;
            case R.id.tvEndTime:
                showEndTime();
                break;
            case R.id.tvThree:
                startChooseSaleTypeFragment();
                break;
            case R.id.tvClear:
                mDropDownMenu.closeMenu();
                tvOne.setText("");
                tvTwo.setText("");
                tvThree.setText("");
                tvStateTime.setText("");
                tvEndTime.setText("");
                map.put("doc_no", "");
                map.put("create_name", "");
                map.put("doc_type_name", "");
                map.put("start_time", "");
                map.put("end_time", "");
                page = 1;
                refresh();
                recyclerView.setRefreshing(true);
                break;
            case R.id.tvOk:
                mDropDownMenu.closeMenu();
                map.put("doc_no", tvOne.getText().toString());
                map.put("create_name", tvTwo.getText().toString());
                map.put("doc_type_name", tvThree.getText().toString());
                map.put("start_time", tvStateTime.getText().toString());
                map.put("end_time", tvEndTime.getText().toString());
                page = 1;
                refresh();
                recyclerView.setRefreshing(true);
                break;
        }
    }

    //选择开始时间
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

    //选择结束时间
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
            tvStateTime.setText(s);
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
    public OutSalePresenter createPresenter() {
        return new OutSalePresenter(getApp());
    }


    @OnClick({R.id.ivLeft, R.id.ivRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivRight:
                startOutSaleAddFragment();
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
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getMsg();
        switch (msg) {
            case "refresh":
                page = 1;
                refresh();
                break;
            case "delete":
                adapter.remove(deletePosition);
                break;
            case "22"://选择单据类型
                SaleShipTypeBean typeBean = event.getmSaleShipTypeBean();
                tvThree.setText(typeBean.getName());
                break;
        }

    }

    @Override
    public void onQuerySaleShipPage(List<SaleShipBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void OnQuerySaleShipByCode(SaleShipBean data) {

    }

    @Override
    public void OnAddSaleShip(String data) {

    }

    @Override
    public void OninvalidSaleShip(String data) {

    }

    @Override
    public void OnoutOfShip(String data) {

    }

    @Override
    public void OnsendSaleShip(String data) {

    }

    @Override
    public void OnDeleteSaleShip(String data) {

    }
}
