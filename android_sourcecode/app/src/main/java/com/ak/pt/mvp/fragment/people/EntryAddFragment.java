package com.ak.pt.mvp.fragment.people;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.ClerkInfoListBean;
import com.ak.pt.bean.DepartmentBean;
import com.ak.pt.bean.FirstEventNew;
import com.ak.pt.bean.MeParentBean;
import com.ak.pt.bean.StaffApplyBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.area.PeopleAddAdapter;
import com.ak.pt.mvp.adapter.people.EntryPeopleAddAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.people.EntryPresenter;
import com.ak.pt.mvp.view.people.IEntryView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.ak.pt.view.DialogLoading;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.uniquext.android.widget.view.CornerImageView;

public class EntryAddFragment extends BaseFragment<IEntryView, EntryPresenter> implements IEntryView {

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
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvOne)
    TextView tvOne;
//    @BindView(R.id.tvTwo)
//    EditText tvTwo;
//    @BindView(R.id.tvThree)
//    EditText tvThree;
//    @BindView(R.id.tvFour)
//    EditText tvFour;
//    @BindView(R.id.tvFive)
//    EditText tvFive;
//    @BindView(R.id.tvSix)
//    EditText tvSix;
//    @BindView(R.id.tvSeven)
//    EditText tvSeven;
    @BindView(R.id.oneRecycleView)
    RecyclerView recycleViewOne;
    @BindView(R.id.twoRecycleView)
    RecyclerView recycleViewTwo;
    @BindView(R.id.lineCheck)
    View lineCheck;
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    @BindView(R.id.circleImageVIew)
    CornerImageView circleImageVIew;
    @BindView(R.id.ivCheckDelete)
    ImageView ivCheckDelete;
    @BindView(R.id.tvCheckPersonName)
    TextView tvCheckPersonName;
    @BindView(R.id.ok)
    Button ok;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private List<ClerkInfoListBean> list;
    private EntryPeopleAddAdapter adapterOne;
    private List<ClerkInfoListBean> listTwo;
    private EntryPeopleAddAdapter adapterTwo;


    private String type;
    private StaffApplyBean data;
    private AppPermissionsBean permissionsBean;
    private String mNext_staff_id = "";//审核人id

    public static EntryAddFragment newInstance(StaffApplyBean bean, String type, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        EntryAddFragment fragment = new EntryAddFragment();
        fragment.data = bean;
        fragment.type = type;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_entry;
    }

    @Override
    public void initUI() {
        list = new ArrayList<>();
        listTwo = new ArrayList<>();
        if ("0".equals(type)) {
            ok.setText("提交");
            tvTitle.setText("入职申请");
            list.add(new ClerkInfoListBean("pressure"));
            listTwo.add(new ClerkInfoListBean("clerk"));

        } else {
            tvTitle.setText("修改入职申请");
            ok.setText("确认修改");

            group_id = data.getShop_id();
            tvOne.setText(data.getShop_name());
//            tvTwo.setText(data.getContact_name());
//            tvThree.setText(data.getContact_tel());
//            tvFour.setText(data.getPerson_num());
//            tvFive.setText(data.getPressure_num());
//            tvSix.setText(data.getPhoto_tool());
//            tvSeven.setText(data.getVehicle());

            mNext_staff_id = data.getNext_audit_id();

            List<ClerkInfoListBean> infoList = data.getPressureInfoList();
            if (infoList.size() > 0) {
                list.addAll(infoList);
            } else {
                list.add(new ClerkInfoListBean("pressure"));
            }
            List<ClerkInfoListBean> infoListtwo = data.getClerkInfoList();
            if (infoListtwo.size() > 0) {
                listTwo.addAll(infoListtwo);
            } else {
                listTwo.add(new ClerkInfoListBean("clerk"));
            }
        }

        adapterOne = new EntryPeopleAddAdapter(context, list, "pressure");
        recycleViewOne.setNestedScrollingEnabled(false);
        recycleViewOne.setLayoutManager(new LinearLayoutManager(context));
        recycleViewOne.setAdapter(adapterOne);
        adapterOne.setOnDeleteClick(new PeopleAddAdapter.OnDeleteClick() {
            @Override
            public void onDeleteClick(int data) {
                adapterOne.remove(data);
            }

            @Override
            public void onAddClick() {
                adapterOne.add(new ClerkInfoListBean("pressure"));
            }
        });


        adapterTwo = new EntryPeopleAddAdapter(context, listTwo, "clerk");
        recycleViewTwo.setNestedScrollingEnabled(false);
        recycleViewTwo.setLayoutManager(new LinearLayoutManager(context));
        recycleViewTwo.setAdapter(adapterTwo);
        adapterTwo.setOnDeleteClick(new PeopleAddAdapter.OnDeleteClick() {
            @Override
            public void onDeleteClick(int data) {
                adapterTwo.remove(data);
            }

            @Override
            public void onAddClick() {
                adapterTwo.add(new ClerkInfoListBean("clerk"));
            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTime.setText(str);
        tvName.setText(userBean.getStaff_name());
        if ("0".equals(type)) {
            group_id = userBean.getDepartment_id();
            tvOne.setText(userBean.getDepartment_name());
        }
        //获取审核人列表
        map.clear();
        map.put("group_id", userBean.getDepartment_id());
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getParentDepartmentStaffList(userBean.getStaff_token(), map);
    }


    @OnClick({R.id.ivLeft, R.id.circleImageVIew, R.id.ivCheckDelete, R.id.ok, R.id.tvOne})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvOne:
                startDepartmentPermissionFragment(permissionsBean.getMenu_id(), "entry");
                break;
            case R.id.ivCheckDelete:
                ivCheckDelete.setVisibility(View.GONE);
                tvCheckPersonName.setText("请选择");
                Glide.with(context).load(R.drawable.check_img).into(circleImageVIew);
                mNext_staff_id = "";
                break;
            case R.id.circleImageVIew:
                startChooseCheckFragment("1");
                break;
            case R.id.ok:
                if (TextUtils.isEmpty(mNext_staff_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择审批人");
                    return;
                }

                String mtvOne = tvOne.getText().toString();
                if (TextUtils.isEmpty(mtvOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择经销商");
                    return;
                }
                ok.setEnabled(false);
                dialogLoading = new DialogLoading(context);
                dialogLoading.showDialog();
                map.clear();
                map.put("shop_id", group_id);
//                map.put("contact_name", tvTwo.getText().toString());
//                map.put("contact_tel", tvThree.getText().toString());
//                map.put("person_num", tvFour.getText().toString());
//                map.put("pressure_num", tvFive.getText().toString());
//                map.put("photo_tool", tvSix.getText().toString());
//                map.put("vehicle", tvSeven.getText().toString());
                map.put("next_audit_id", mNext_staff_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());

                List<ClerkInfoListBean> allData = adapterTwo.getAllData();
                List<ClerkInfoListBean> allData1 = adapterOne.getAllData();
                ArrayList<ClerkInfoListBean> list = new ArrayList<>();

                list.addAll(allData);
                list.addAll(allData1);
                if (list.size() > 0) {
                    String json = new Gson().toJson(list);
                    map.put("staffList", json);
                }
                switch (type) {
                    case "0"://新建
                        map.put("operation", "0");
                        getPresenter().insertStaffApply(userBean.getStaff_token(), map);
                        break;
                    case "1"://编辑
                        map.put("operation", "1");
                        map.put("apply_id", data.getApply_id());
                        getPresenter().updateStaffApply(userBean.getStaff_token(), map);
                        break;
                }
                break;
        }
    }


    private DialogLoading dialogLoading;


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());
        ok.setEnabled(true);
    }

    @Override
    public EntryPresenter createPresenter() {
        return new EntryPresenter(getApp());
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

    private String group_id = "";//经销商id

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventNew event) {
        switch (event.getMsg()) {
            case "entry":
                DepartmentBean bean = event.getmDepartBean();
                tvOne.setText(bean.getName());
                group_id = bean.getUuid();
                break;
            case "1":
                MeParentBean mbean = event.getmBean();
                Glide.with(context)
                        .load(Constants.BASE_URL + mbean.getHead_img())
                        .error(R.drawable.error_img)
                        .into(circleImageVIew);
                tvCheckPersonName.setText(mbean.getStaff_name());
                ivCheckDelete.setVisibility(View.VISIBLE);
                mNext_staff_id = mbean.getStaff_id();

                break;
        }

    }


    @Override
    public void insertStaffApply(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();

    }

    @Override
    public void deleteStaffApply(String data) {

    }

    @Override
    public void updateStaffApply(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void auditStaffApply(String data) {

    }

    @Override
    public void getStaffApplyList(List<StaffApplyBean> data) {

    }

    @Override
    public void getStaffApplyDetail(StaffApplyBean data) {

    }



    @Override
    public void onGgetParentDepartmentStaffList(List<MeParentBean> data) {
        switch (type) {
            case "0":
                for (int i = 0; i < data.size(); i++) {
                    MeParentBean bean = data.get(i);
                    if ("1".equals(bean.getStaff_give())) {
                        Glide.with(context)
                                .load(Constants.BASE_URL + bean.getHead_img())
                                .error(R.drawable.error_img)
                                .into(circleImageVIew);
                        tvCheckPersonName.setText(bean.getStaff_name());
                        ivCheckDelete.setVisibility(View.VISIBLE);
                        mNext_staff_id = bean.getStaff_id();
                    }

                }
                break;
            case "1":
                for (int i = 0; i < data.size(); i++) {
                    MeParentBean bean = data.get(i);
                    if (mNext_staff_id.equals(bean.getStaff_id())) {
                        Glide.with(context)
                                .load(Constants.BASE_URL + bean.getHead_img())
                                .error(R.drawable.error_img)
                                .into(circleImageVIew);
                        tvCheckPersonName.setText(bean.getStaff_name());
                        ivCheckDelete.setVisibility(View.VISIBLE);
                        mNext_staff_id = bean.getStaff_id();
                    }

                }

                break;
        }

    }
}
