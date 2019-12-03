package com.akan.wms.mvp.fragment.manager;

import android.app.AlertDialog;
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
import com.akan.wms.bean.OutTypeLBean;
import com.akan.wms.bean.StaffOrgsBean;
import com.akan.wms.bean.TransferOutBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.home.OutTransferListAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.OutTransferPresenter;
import com.akan.wms.mvp.view.home.IOutTransferView;
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

public class OutTransferListFragment extends BaseFragment<IOutTransferView, OutTransferPresenter> implements IOutTransferView, View.OnClickListener {

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


    private List<TransferOutBean> list;
    private OutTransferListAdapter adapter;
    private int page = 1;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> map1 = new HashMap<>();
    private UserBean userBean;

    private String headers[] = {"全部状态", "条件筛选"};
    private String mOne[] = {"全部", "配货", "出库"};
    private List<View> popupViews = new ArrayList<>();
    private GirdDropDownAdapter oneAdapter;
    private EditText tvOne;
    private EditText tvTwo;
    private TextView tvThree;
    private TextView tvFour;
    private TextView tvFive;
    private TextView tvStateTime;
    private TextView tvEndTime;
    private TextView tvClear;
    private TextView tvOk;

    public static OutTransferListFragment newInstance() {
        Bundle args = new Bundle();
        OutTransferListFragment fragment = new OutTransferListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_list_public_new;
    }

    @Override
    public void initUI() {
        tvTitle.setText("调出单列表");
        ivRight.setVisibility(View.VISIBLE);
        list = new ArrayList<>();
        initMenu();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new OutTransferListAdapter(context, list);
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startOutTransferFragment(adapter.getItem(position).getId() + "");
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
                getPresenter().sync(userBean.getStaff_token(),map1);
            }
        });
        SonnyJackDragView build = new SonnyJackDragView.Builder()
                .setActivity(getActivity())
                .setDefaultLeft(100)
                .setDefaultTop(100)
                .setNeedNearEdge(false)
                .setSize(160)
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
        getPresenter().queryTransferOutPageTwo(userBean.getStaff_token(), map);
    }


    //初始化选择栏
    private void initMenu() {
        final ListView oneView = new ListView(context);
        oneView.setDividerHeight(0);
        oneAdapter = new GirdDropDownAdapter(context, Arrays.asList(mOne));
        oneView.setAdapter(oneAdapter);
        View twoView = LayoutInflater.from(context).inflate(R.layout.top_select_transfer_out, null);
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
                    case "配货":
                        map.put("status", "0");
                        break;
                    case "出库":
                        map.put("status", "1");
                        break;

                }
                page = 1;
                refresh();
                recyclerView.setRefreshing(true);

            }
        });

        tvOne = twoView.findViewById(R.id.tvOne);
        tvTwo = twoView.findViewById(R.id.tvTwo);
        tvThree = twoView.findViewById(R.id.tvThree);
        tvFour = twoView.findViewById(R.id.tvFour);
        tvFive = twoView.findViewById(R.id.tvFive);
        tvStateTime = twoView.findViewById(R.id.tvStateTime);
        tvEndTime = twoView.findViewById(R.id.tvEndTime);
        tvClear = twoView.findViewById(R.id.tvClear);
        tvOk = twoView.findViewById(R.id.tvOk);
        tvThree.setOnClickListener(this);
        tvFour.setOnClickListener(this);
        tvFive.setOnClickListener(this);
        tvStateTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        tvClear.setOnClickListener(this);
        tvOk.setOnClickListener(this);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews);
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
                startOutApplyTypeFragment();
                break;
            case R.id.tvFour:
                startProduceChooseFragment("11");
                break;
            case R.id.tvFive:
                showSingleDialog();
                break;
            case R.id.tvClear:
                dropDownMenu.closeMenu();
                tvOne.setText("");
                tvTwo.setText("");
                tvThree.setText("");
                tvFour.setText("");
                tvFive.setText("");
                tvStateTime.setText("");
                tvEndTime.setText("");
                map.put("doc_no", "");
                map.put("create_name", "");
                map.put("doc_type_name", "");
                map.put("in_org_name", "");
                map.put("transfer_direction", "");
                map.put("start_time", "");
                map.put("end_time", "");
                page = 1;
                refresh();
                recyclerView.setRefreshing(true);
                break;
            case R.id.tvOk:
                dropDownMenu.closeMenu();
                map.put("doc_no", tvOne.getText().toString());
                map.put("create_name", tvTwo.getText().toString());
                map.put("doc_type_name", tvThree.getText().toString());
                map.put("in_org_name", tvFour.getText().toString());
                map.put("start_time", tvStateTime.getText().toString());
                map.put("end_time", tvEndTime.getText().toString());
                page = 1;
                refresh();
                recyclerView.setRefreshing(true);
                break;
        }

    }


    @OnClick({R.id.ivLeft, R.id.ivRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivRight:
                startOutTransferAddFragment();
                break;

        }
    }

    private AlertDialog alertDialog;
    private int choosePosition = 0;

    public void showSingleDialog() {
        final String[] items = {"普通", "退货"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle("选择调拨方向");
        alertBuilder.setSingleChoiceItems(items, choosePosition, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choosePosition = i;
            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvFive.setText(items[choosePosition]);
                if ("普通".equals(items[choosePosition])) {
                    map.put("transfer_direction", "0");
                } else {
                    map.put("transfer_direction", "1");
                }
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public OutTransferPresenter createPresenter() {
        return new OutTransferPresenter(getApp());
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
            case "16"://选择单据类型
                OutTypeLBean typeBean = event.getmOutTypeLBean();
                tvThree.setText(typeBean.getName());
                break;
            case "11"://选择组织
                StaffOrgsBean staffOrgsBean = event.getmBean();
                tvFour.setText(staffOrgsBean.getName());
                break;

        }

    }


    @Override
    public void onQueryTransferOutPage(List<TransferOutBean> data) {

    }

    @Override
    public void onQueryTransferById(TransferOutBean data) {

    }

    @Override
    public void onAddTransferOut(String data) {

    }

    @Override
    public void onTransferOutWh(String data) {

    }

    @Override
    public void onQueryTransferOutPageTwo(List<TransferOutBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSync(String data) {
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

