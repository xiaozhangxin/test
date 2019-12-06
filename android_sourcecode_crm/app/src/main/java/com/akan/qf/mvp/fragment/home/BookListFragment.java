package com.akan.qf.mvp.fragment.home;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.AppHomeMenuTreeBean;
import com.akan.qf.bean.AppVersionBean;
import com.akan.qf.bean.BookBean;
import com.akan.qf.bean.DepartmentBean;
import com.akan.qf.bean.DepartmentEvent;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.StaffMessageBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.message.BookListAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.message.MessagePresenter;
import com.akan.qf.mvp.view.message.IMessageView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.CustomDialog;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BookListFragment extends BaseFragment<IMessageView, MessagePresenter> implements IMessageView {


    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
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
    private DepartmentBean bean;//选择的部门信息
    private List<BookBean> allList = new ArrayList<>();//选择部门后的所有数据
    private List<BookBean> timeList = new ArrayList<>();//临时的数据
    private PermissionsBean permissionsBean;

    public static BookListFragment newInstance(PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        BookListFragment fragment = new BookListFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_book_list;
    }

    @Override
    public void initUI() {
        tvTitle.setText("通讯录");
        tvRight.setVisibility(View.VISIBLE);
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new BookListAdapter(context, mList);
        recycleView.setAdapterWithProgress(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                final String phone = adapter.getItem(position).getPhone();
                String staff_name = adapter.getItem(position).getStaff_name();
                if (TextUtils.isEmpty(phone)) {
                    return;
                }
                sendCall(phone, staff_name);
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


    private void sendCall(final String phone, String staff_name) {
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


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        tvRight.setText(userBean.getSimple_department_name());
        refresh();

    }



    @OnClick({R.id.tvRight,R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                switch (permissionsBean.getApp_data()) {
                    case "0":
                        ToastUtil.showToast(context.getApplicationContext(), "暂无权限");
                        break;
                    case "1":
                        StartChooseDepartmentFragment("book");
                        break;
                    case "2":
                        startDepartmentPermissionFragment(permissionsBean.getMenu_id(), "book");
                        break;
                }
                break;

        }
    }

    @Override
    public void onGetStaffMessageList(List<StaffMessageBean> data) {

    }

    @Override
    public void onGetAddressBookList(List<BookBean> data, String total) {
        tvTitle.setText(String.format(Locale.CHINA, "通讯录(%s)", data.size()));
        adapter.clear();
        mList.clear();
        if (data.size() > 0) {
            mList.addAll(data);
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onGetNotReadMessageCount(String data) {

    }

    @Override
    public void onDeleteStaffMessages(String data) {

    }

    @Override
    public void OnGetAppVersionDetail(AppVersionBean data) {

    }


    @Override
    public void onGetAppHomeMenuTreeByStaff(List<AppHomeMenuTreeBean> data) {

    }

    private void refresh() {
        map.put("staff_id", userBean.getStaff_id());
        map.put("is_select", "1");
        map.put("is_app", "1");
        map.put("operation", "1000");
        map.put("module_id", permissionsBean.getMenu_id());
        getPresenter().getAddressBookList(userBean.getStaff_token(), map);
    }


    private void sq(String phone) {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 没有获得授权，申请授权
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
    public MessagePresenter createPresenter() {
        return new MessagePresenter(getApp());
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
    public void onEventMainThread(DepartmentEvent event) {
        switch (event.getmMsg()) {
            case "1":
                bean = event.getmBean();
                String name = bean.getName();
                String uuid = bean.getUuid();
                tvRight.setText(name);
                allList.clear();
                for (int i = 0; i < mList.size(); i++) {
                    String s1 = mList.get(i).getDepartment_id();
                    if (uuid.contains("," + s1 + ",")) {
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
            case "changeAccount":
                userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
                tvRight.setText(userBean.getSimple_department_name());
                if (permissionsBean == null) {
                    return;
                }
                map.put("staff_id", userBean.getStaff_id());
                map.put("is_select", "1");
                map.put("is_app", "1");
                map.put("operation", "1000");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().getAddressBookList(userBean.getStaff_token(), map);
                break;

        }
    }


}
