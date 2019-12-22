package com.akan.wms.mvp.fragment.mix;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import com.akan.wms.bean.RcvBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.mix.ReceiveMixListAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.mix.ReceiveMixPresenter;
import com.akan.wms.mvp.view.mix.IReceiveMixView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.akan.wms.view.CustomDialog;
import com.akan.wms.view.DropDownView.DropDownMenu;
import com.akan.wms.view.DropDownView.GirdDropDownAdapter;
import com.akan.wms.view.SonnyJackDragView;
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

public class ReceiveMixListFragment extends BaseFragment<IReceiveMixView, ReceiveMixPresenter> implements IReceiveMixView, View.OnClickListener {

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


    private List<RcvBean> list;
    private ReceiveMixListAdapter adapter;
    private int page = 1;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> map1 = new HashMap<>();
    private UserBean userBean;

    private String headers[] = {"审核中", "条件筛选"};
    private String mOne[] = {"全部", "审核中", "已审核"};
    private List<View> popupViews = new ArrayList<>();
    private GirdDropDownAdapter oneAdapter;
    private EditText tvOne;
    private TextView tvStartTime;
    private TextView tvEndTime;
    private TextView tvClear;
    private TextView tvOk;


    public static ReceiveMixListFragment newInstance() {
        Bundle args = new Bundle();
        ReceiveMixListFragment fragment = new ReceiveMixListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_list_public_new;
    }

    @Override
    public void initUI() {
        tvTitle.setText("杂收单列表");
        list = new ArrayList<>();
        initMenu();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ReceiveMixListAdapter(context, list);
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startReceiveMixDetailFragment(adapter.getItem(position).getId() + "");
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

        //悬浮可拖动按钮
        ImageView imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.sync);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().syncMix(userBean.getStaff_token(),map1);
            }
        });
        SonnyJackDragView build = new SonnyJackDragView.Builder()
                .setActivity(getActivity())
                .setNeedNearEdge(false)
                .setView(imageView)
                .build();
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();
    }

    private void refresh() {
        map.put("org_id", userBean.getOrg_id());
        map.put("ship_org", userBean.getOrg_id());
        map.put("page", page + "");
        getPresenter().queryRcvPage(userBean.getStaff_token(), map);
    }


    //初始化选择栏
    private void initMenu() {
        final ListView oneView = new ListView(context);
        oneView.setDividerHeight(0);
        oneAdapter = new GirdDropDownAdapter(context, Arrays.asList(mOne));
        oneView.setAdapter(oneAdapter);
        View twoView = LayoutInflater.from(context).inflate(R.layout.top_select_rec_mix, null);
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
                        map.put("status", "");
                        break;
                    case "审核中":
                        map.put("status", "1");
                        break;
                    case "已审核":
                        map.put("status", "2");
                        break;

                }
                recyclerView.setRefreshing(true);
                page = 1;
                refresh();

            }
        });


        tvOne = twoView.findViewById(R.id.tvOne);
        tvStartTime = twoView.findViewById(R.id.tvStartTime);
        tvEndTime = twoView.findViewById(R.id.tvEndTime);
        tvClear = twoView.findViewById(R.id.tvClear);
        tvOk = twoView.findViewById(R.id.tvOk);
        tvStartTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        tvClear.setOnClickListener(this);
        tvOk.setOnClickListener(this);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews);

        oneAdapter.setCheckItem(1);
        dropDownMenu.setTabText("审核中");
        map.put("status", "1");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvStartTime:
                showStartTime();
                break;
            case R.id.tvEndTime:
                showEndTime();
                break;
            case R.id.tvClear:
                dropDownMenu.closeMenu();
                tvOne.setText("");
                tvStartTime.setText("");
                tvEndTime.setText("");
                map.put("doc_no", "");
                map.put("start_time", "");
                map.put("end_time", "");
                page = 1;
                recyclerView.setRefreshing(true);
                refresh();
                break;
            case R.id.tvOk:
                dropDownMenu.closeMenu();
                map.put("doc_no", tvOne.getText().toString());
                map.put("start_time", tvStartTime.getText().toString());
                map.put("end_time", tvEndTime.getText().toString());
                page = 1;
                recyclerView.setRefreshing(true);
                refresh();
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
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public ReceiveMixPresenter createPresenter() {
        return new ReceiveMixPresenter(getApp());
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
                recyclerView.setRefreshing(true);
                refresh();
                break;

        }

    }


    @Override
    public void onQueryRcvPage(List<RcvBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onQueryRcvById(RcvBean data) {

    }

    @Override
    public void onAuditRcv(String data) {

    }

    @Override
    public void onSyncMix(String data) {
        syncSuccess();
    }

    private void syncSuccess() {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(getString(R.string.point));
        builder.setMessage(getString(R.string.sync_success));
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.onCreate().show();
    }
}

