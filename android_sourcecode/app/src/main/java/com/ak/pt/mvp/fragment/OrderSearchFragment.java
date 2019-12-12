package com.ak.pt.mvp.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.OrderAcceptAdapter;
import com.ak.pt.mvp.adapter.SearchHistoryAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.mvp.presenter.OrderSearchPresenter;
import com.ak.pt.mvp.view.IOrderSearchView;
import com.ak.pt.util.MyFlexboxLayoutManager;
import com.ak.pt.util.RecordSQLiteOpenHelper;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderSearchFragment extends BaseFragment<IOrderSearchView, OrderSearchPresenter> implements IOrderSearchView {


    Unbinder unbinder;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.Flexbox)
    RecyclerView Flexbox;


    private List<PressurePageBean> list;
    private List<String> fleList;
    private OrderAcceptAdapter adapter;
    private SearchHistoryAdapter flexAdapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private String type;//0试压报单 1未完成的试压单
    private AppPermissionsBean permissionsBean;
    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public static OrderSearchFragment newInstance(String type, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        OrderSearchFragment fragment = new OrderSearchFragment();
        fragment.type = type;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_order_search;
    }

    @Override
    public void initUI() {

        fleList = new ArrayList<>();
        FlexboxLayoutManager flexboxLayoutManager = new MyFlexboxLayoutManager(context);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);
        Flexbox.setLayoutManager(flexboxLayoutManager);
        flexAdapter = new SearchHistoryAdapter(context, fleList);
        Flexbox.setAdapter(flexAdapter);

        flexAdapter.setOnItemClickListener(position -> {
            etName.setText(flexAdapter.getItem(position));
            page = 1;
            refresh();
        });

        flexAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.item_history_top, null);
                inflate.findViewById(R.id.tvClear).setOnClickListener(v -> deleteData());
                return inflate;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });

        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new OrderAcceptAdapter(context, list);
        recycleView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(position -> {
                    switch (type) {
                        case "1":
                            startTestPressureDetailFragment(adapter.getItem(position).getDoc_no(), permissionsBean);
                            break;
                        case "0":
                            PressurePageBean item = adapter.getItem(position);
                            //待接单已接单并且指派给自己
                            if (("accept".equals(item.getFlow_state()) || "plan".equals(item.getFlow_state())) && userBean.getStaff_id().equals(item.getPlumber_id())) {
                                startTestPressureDetailFragment(adapter.getItem(position).getDoc_no(), permissionsBean);
                            } else {
                                startOrderManagerDetailFragment(adapter.getItem(position).getDoc_no(), permissionsBean);
                            }
                            break;
                    }
                }
        );


        adapter.setNoMore(R.layout.view_nomore);
        //下拉刷新
        recycleView.setRefreshingColorResources(R.color.colorPrimaryNew);
        recycleView.setRefreshListener(() -> {
            page = 1;
            refresh();
        });
        //上拉加载
        adapter.setMore(R.layout.view_more, () -> {
            page++;
            refresh();
        });

        helper = new RecordSQLiteOpenHelper(getContext());
        etName.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String toString = etName.getText().toString();
                if (TextUtils.isEmpty(toString)) {
                    ToastUtils.showToast(getActivity().getApplicationContext(), "请输入内容");
                } else {
                    if (!hasData(toString)) {
                        insertData(toString);
                    }
                    page = 1;
                    refresh();
                }
            }
            return false;
        });

        cancel.setOnClickListener(v -> {
            finish();
        });
        queryData();
    }

    @Override
    public void initData() {

    }


    //插入数据
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    //检查数据库中是否已经有该条记录
    private boolean hasData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    //清空数据
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
        flexAdapter.clear();
        flexAdapter.notifyDataSetChanged();
    }

    //查询数据
    private void queryData() {
        Cursor cursor = helper.getReadableDatabase().rawQuery("select * from records order by id asc ", null);
        flexAdapter.clear();
        while (cursor.moveToNext()) {
            final String name = cursor.getString(cursor.getColumnIndex("name"));
            flexAdapter.add(name);
        }
        flexAdapter.notifyDataSetChanged();
        cursor.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        //page = 1;
        //refresh();
    }

    private void refresh() {
        map.put("doc_no", etName.getText().toString());
        map.put("page", page + "");
        map.put("limit", "20");
        map.put("staff_id", userBean.getStaff_id());
        map.put("job_name", userBean.getJob_name());
        switch (type) {
            case "0":
                map.put("is_select", "1");
                map.put("is_app", "1");
                map.put("operation", "1000");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().getTestPressureList(userBean.getStaff_token(), map);
                break;
            case "1":
                getPresenter().getAppTestPressureList(userBean.getStaff_token(), map);
                break;
        }

    }


    @Override
    public void OnGetAppTestPressureList(List<PressurePageBean> data, String total) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
        Flexbox.setVisibility(View.GONE);
    }

    @Override
    public void OnGetTestPressureList(List<PressurePageBean> data, String total) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
        Flexbox.setVisibility(View.GONE);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public OrderSearchPresenter createPresenter() {
        return new OrderSearchPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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


}
