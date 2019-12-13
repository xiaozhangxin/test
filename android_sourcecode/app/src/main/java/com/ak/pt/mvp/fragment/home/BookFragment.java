package com.ak.pt.mvp.fragment.home;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.BookBean;
import com.ak.pt.bean.BookNameBean;
import com.ak.pt.bean.FirstEventBook;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.BookListAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.BookPresenter;
import com.ak.pt.mvp.view.IBookView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/1/17.
 */

public class BookFragment extends BaseFragment<IBookView, BookPresenter> implements IBookView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRightTwo)
    ImageView ivRightTwo;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private BookListAdapter adapter;
    private List<BookBean> mList;

    private int page = 1;
    private AppPermissionsBean permissionsBean;

    public static BookFragment newInstance(AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        BookFragment fragment = new BookFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_book;
    }

    @Override
    public void initUI() {
        tvTitle.setText("通讯录");
        tvRight.setVisibility(View.VISIBLE);
        mList = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new BookListAdapter(context, mList);
        recycleView.setAdapterWithProgress(adapter);

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

        adapter.setOnItemClickListener(position -> {
            final String phone = adapter.getItem(position).getPhone();
            if (!TextUtils.isEmpty(phone)) {
                showCallPhonePop(phone, position);

            }
        });

        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String toString = etSearch.getText().toString();
                if (TextUtils.isEmpty(toString)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入内容");
                } else {
                    page = 1;
                    refresh();
                }
            }
            return false;
        });

    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        switch (permissionsBean.getApp_data()) {
            case "0":
                tvRight.setVisibility(View.GONE);
                break;
            case "1":
                tvRight.setText(userBean.getSimple_department_name());
                break;
            case "2":
                tvRight.setText("选择部门");
                break;
        }
        map.put("staff_id", userBean.getStaff_id());
        map.put("is_select", "1");
        map.put("is_app", "1");
        map.put("operation", "1000");
        map.put("module_id", permissionsBean.getMenu_id());
        refresh();

    }

    private void refresh() {
        map.put("all_select", etSearch.getText().toString());
        map.put("page", String.valueOf(page));
        getPresenter().getAddressBookList(userBean.getStaff_token(), map);
    }

    @OnClick({R.id.tvRight, R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                startDepartmentPermissionFragment(permissionsBean.getMenu_id(), "book");
        }
    }


    @Override
    public void onGetAddressBookList(List<BookBean> data,String total) {
        tvTitle.setText("通讯录("+total+")");
        if (page==1){
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }



    //是否拨打电话弹框
    private void showCallPhonePop(String phone, int position) {
        String staff_name = adapter.getItem(position).getStaff_name();
        final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
        builder1.setMessage("呼叫" + phone + "(" + staff_name + ")?");
        builder1.setPositiveButton(getString(R.string.sure), (dialog, which) -> {
            sq(phone);
            dialog.dismiss();
        });
        builder1.setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss());
        builder1.onCreate().show();
    }

    private void sq(String phone) {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {
                ToastUtil.showToast(context.getApplicationContext(), getString(R.string.authorized));
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        } else {
            CallPhone(phone);
        }
    }

    private void CallPhone(String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        startActivity(intent);
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
    public BookPresenter createPresenter() {
        return new BookPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventBook event) {
        switch (event.getType()) {
            case "book":
                BookNameBean bean = event.getBookNameBean();
                String name = bean.getName();
                String names = bean.getNames();
                tvRight.setText(names);
                map.put("group_parent_uuid", name);
                page = 1;
                refresh();
                recycleView.setRefreshing(true);
                break;
        }

    }


}
