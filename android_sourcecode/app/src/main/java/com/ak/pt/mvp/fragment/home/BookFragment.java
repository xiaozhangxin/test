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
    private List<BookBean> mList = new ArrayList<>();//当前账号下的所有数据
    private BookNameBean bean;//选择的部门信息
    private List<BookBean> allList = new ArrayList<>();//选择部门后的所有数据
    private List<BookBean> timeList = new ArrayList<>();//临时的数据

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
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new BookListAdapter(context, mList);
        recycleView.setAdapterWithProgress(adapter);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                final String phone = adapter.getItem(position).getPhone();
                if (!TextUtils.isEmpty(phone)) {
                    String staff_name = adapter.getItem(position).getStaff_name();
                    final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
                    builder1.setMessage("呼叫" + phone + "(" + staff_name + ")?");
                    builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            sq(phone);
                            dialog.dismiss();
                        }
                    });
                    builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder1.onCreate().show();
                }
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                timeList.clear();
                if (TextUtils.isEmpty(s.toString())) {
                    if (bean == null) {
                        timeList.addAll(mList);

                    } else {
                        timeList.addAll(allList);
                    }

                } else {
                    if (bean == null) {
                        for (int i = 0; i < mList.size(); i++) {
                            String s1 = mList.get(i).getStaff_name() + mList.get(i).getJob_name() + mList.get(i).getPhone();
                            if (s1.contains(s.toString())) {
                                timeList.add(mList.get(i));
                            }
                        }
                    } else {
                        for (int i = 0; i < allList.size(); i++) {
                            String s1 = allList.get(i).getStaff_name() + allList.get(i).getJob_name() + allList.get(i).getPhone();
                            if (s1.contains(s.toString())) {
                                timeList.add(allList.get(i));
                            }
                        }
                    }


                }


                adapter.clear();
                adapter.addAll(timeList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
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
    public void onGetAddressBookList(List<BookBean> data) {
        mList.clear();
        adapter.clear();
        if (data.size() > 0) {
            mList.addAll(data);
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
        }
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
                bean = event.getBookNameBean();
                String name = bean.getName();
                String names = bean.getNames();
                tvRight.setText(names);
                allList.clear();
                for (int i = 0; i < mList.size(); i++) {
                    String s1 = mList.get(i).getDepartment_id();
                    if (name.contains("," + s1 + ",")) {
                        allList.add(mList.get(i));
                    }
                }
                if (allList.size() > 0) {
                    adapter.clear();
                    adapter.addAll(allList);
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.clear();
                }
                break;
        }

    }


}
