package com.akan.wms.mvp.fragment.base;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.bean.WarnBean;
import com.akan.wms.bean.WarnTypeBean;
import com.akan.wms.mvp.adapter.home.FlowListAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.FlowPresenter;
import com.akan.wms.mvp.view.home.IFlowView;
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

public class FlowListFragment extends BaseFragment<IFlowView, FlowPresenter> implements IFlowView, View.OnClickListener {

    Unbinder unbinder;


    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;

    private List<WarnBean> list;
    private FlowListAdapter adapter;
    private int page = 1;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private String headers[] = {"全部状态", "条件筛选"};
    private String mOne[] = {"全部", "出库", "入库"};
    private List<View> popupViews = new ArrayList<>();
    private GirdDropDownAdapter oneAdapter;
    private EditText tvOne;
    private EditText tvTwo;
    private TextView tvThree;
    private TextView tvFour;
    private TextView tvStateTime;
    private TextView tvEndTime;
    private TextView tvClear;
    private TextView tvOk;
    private LinearLayout llOne;
    private LinearLayout llTwo;
    private View lineOne;
    private View lineTwo;
    private List<String> mTypeList = new ArrayList<>();

    private String itemId;

    public static FlowListFragment newInstance(String id) {
        Bundle args = new Bundle();
        FlowListFragment fragment = new FlowListFragment();
        fragment.itemId = id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_list_public_new;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.Flo_into_and_out);
        list = new ArrayList<>();
        initMenu();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new FlowListAdapter(context, list);
        recyclerView.setAdapterWithProgress(adapter);
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

        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
        if (!TextUtils.isEmpty(itemId)) {
            llOne.setVisibility(View.GONE);
            llTwo.setVisibility(View.GONE);
            lineOne.setVisibility(View.GONE);
            lineTwo.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getPresenter().queryInventoryStatementTypeList(userBean.getStaff_token(), map);
        if (!TextUtils.isEmpty(itemId)) {
            map.put("item_id", itemId);
        }
        refresh();
    }

    private void refresh() {
        map.put("org_id", userBean.getOrg_id());
        map.put("page", page + "");
        getPresenter().queryWarningList(userBean.getStaff_token(), map);

    }


    //初始化选择栏
    private void initMenu() {
        final ListView oneView = new ListView(context);
        oneView.setDividerHeight(0);
        oneAdapter = new GirdDropDownAdapter(context, Arrays.asList(mOne));
        oneView.setAdapter(oneAdapter);
        View twoView = LayoutInflater.from(context).inflate(R.layout.top_select_flow, null);
        popupViews.add(oneView);
        popupViews.add(twoView);
        oneView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                oneAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[0] : mOne[position]);
                dropDownMenu.closeMenu();
                switch (mOne[position]) {
                    case "全部":
                        map.put("in_out_type", "");
                        break;
                    case "出库":
                        map.put("in_out_type", "1");
                        break;
                    case "入库":
                        map.put("in_out_type", "0");
                        break;

                }
                page = 1;
                refresh();
                recyclerView.setRefreshing(true);
            }
        });

        llOne = twoView.findViewById(R.id.llOne);
        llTwo = twoView.findViewById(R.id.llTwo);
        lineOne = twoView.findViewById(R.id.lineOne);
        lineTwo = twoView.findViewById(R.id.lineTwo);
        tvOne = twoView.findViewById(R.id.tvOne);
        tvTwo = twoView.findViewById(R.id.tvTwo);
        tvThree = twoView.findViewById(R.id.tvThree);
        tvFour = twoView.findViewById(R.id.tvFour);
        tvStateTime = twoView.findViewById(R.id.tvStateTime);
        tvEndTime = twoView.findViewById(R.id.tvEndTime);

        tvClear = twoView.findViewById(R.id.tvClear);
        tvOk = twoView.findViewById(R.id.tvOk);
        tvStateTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        tvClear.setOnClickListener(this);
        tvOk.setOnClickListener(this);
        tvThree.setOnClickListener(this);
        tvFour.setOnClickListener(this);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tvThree:
                startChooseDeportFragment();
                break;
            case R.id.tvFour:
                if (mTypeList.size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.no_type));
                    return;
                }
                showSingleDialog();
                break;

            case R.id.tvStateTime:
                showStartTime();
                break;
            case R.id.tvEndTime:
                showEndTime();
                break;
            case R.id.tvClear:
                tvOne.setText("");
                tvTwo.setText("");
                tvThree.setText("");
                tvFour.setText("");
                tvStateTime.setText("");
                tvEndTime.setText("");
                dropDownMenu.closeMenu();
                mTypeId="";
                map.put("wh_name", "");
                map.put("start_time", "");
                map.put("end_time", "");
                map.put("item_id", "");
                map.put("item_name", "");
                map.put("inventory_type", "");
                page = 1;
                refresh();
                recyclerView.setRefreshing(true);
                break;
            case R.id.tvOk:
                dropDownMenu.closeMenu();
                map.put("item_id", tvOne.getText().toString());
                map.put("item_name", tvTwo.getText().toString());
                map.put("wh_name", tvThree.getText().toString());
                map.put("start_time", tvStateTime.getText().toString());
                map.put("end_time", tvEndTime.getText().toString());
                map.put("inventory_type", mTypeId);

                page = 1;
                refresh();
                recyclerView.setRefreshing(true);
                break;
        }

    }


    @OnClick({R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }

    private AlertDialog alertDialog;
    private int choosePosition = 0;
    private String mTypeId="";

    public void showSingleDialog() {
        String[] strings = new String[mTypeList.size()];
        final String[] items = mTypeList.toArray(strings);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle(R.string.select_type);
        alertBuilder.setSingleChoiceItems(items, choosePosition, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choosePosition = i;
            }
        });
        alertBuilder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvFour.setText(items[choosePosition]);
                mTypeId=  mTypeBeanList.get(choosePosition).getInventory_type();
                tvFour.setText(items[choosePosition]);
            }
        });

        alertDialog = alertBuilder.create();
        alertDialog.show();

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
    public void onQueryWarningList(List<WarnBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    private  List<WarnTypeBean> mTypeBeanList=new ArrayList<>();
    @Override
    public void onQueryInventoryStatementTypeList(List<WarnTypeBean> data) {
        mTypeBeanList.clear();
        mTypeBeanList.addAll(data);
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                mTypeList.add(data.get(i).getInventory_type_name());
            }
        }
    }


    @Override
    public FlowPresenter createPresenter() {
        return new FlowPresenter(getApp());
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
            case "6"://选择仓库
                WareHouseBean houseBean = event.getmWareHouseBean();
                tvThree.setText(houseBean.getWarehouse_name());
                break;

        }

    }


}

