package com.ak.pt.mvp.fragment.area;

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
import com.ak.pt.bean.FirstEventNew;
import com.ak.pt.bean.MeParentBean;
import com.ak.pt.bean.PeopleAddBean;
import com.ak.pt.bean.PeopleBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.area.PeopleAddAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.area.PeoplePresenter;
import com.ak.pt.mvp.view.area.IPeopleView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.ak.pt.view.DialogLoading;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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


/**
 * Created by admin on 2019/5/29.
 */

public class PeopleAddFragment extends BaseFragment<IPeopleView, PeoplePresenter> implements IPeopleView {


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
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvOne)
    EditText tvOne;
    @BindView(R.id.tvArea)
    EditText tvArea;
    @BindView(R.id.tvRegion)
    EditText tvRegion;
    @BindView(R.id.tvTwo)
    EditText tvTwo;
    @BindView(R.id.tvThree)
    EditText tvThree;
    @BindView(R.id.tvFour)
    EditText tvFour;
    @BindView(R.id.tvFive)
    EditText tvFive;
    @BindView(R.id.tvSix)
    EditText tvSix;
    @BindView(R.id.tvSeven)
    EditText tvSeven;
    @BindView(R.id.tvEight)
    EditText tvEight;
    @BindView(R.id.tvNine)
    EditText tvNine;
    @BindView(R.id.tvTen)
    EditText tvTen;
    @BindView(R.id.recycleViewOne)
    RecyclerView recycleViewOne;
    @BindView(R.id.recycleViewTwo)
    RecyclerView recycleViewTwo;
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
    private Map<String, String> map2 = new HashMap<>();
    private UserBean userBean;
    private DialogLoading dialogLoading;
    private String type;
    private PeopleBean data;
    private String mNext_staff_id = "";
    private List<PeopleAddBean> list;
    private PeopleAddAdapter adapterOne;
    private List<PeopleAddBean> listTwo;
    private PeopleAddAdapter adapterTwo;
    private AppPermissionsBean permissionsBean;

    public static PeopleAddFragment newInstance(PeopleBean bean, String type, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        PeopleAddFragment fragment = new PeopleAddFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.data = bean;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_people_add;
    }

    @Override
    public void initUI() {

        list = new ArrayList<>();
        listTwo = new ArrayList<>();

        if ("0".equals(type)) {
            ok.setText("提交");
            tvTitle.setText("端口添加");
            list.add(new PeopleAddBean("pressure"));
            listTwo.add(new PeopleAddBean("clerk"));

        } else {
            tvTitle.setText("修改端口添加");
            ok.setText("确认修改");
            mNext_staff_id = data.getNext_audit_id();
            tvOne.setText(data.getAdd_city());
            tvArea.setText(data.getBig_area());
            tvRegion.setText(data.getArea());
            tvTwo.setText(data.getAdd_shop());
            tvThree.setText(data.getShop_name());
            tvFour.setText(data.getShop_tel());
            tvFive.setText(data.getServe_name());
            tvSix.setText(data.getServe_tel());
            tvSeven.setText(data.getPerson_num());
            tvEight.setText(data.getPressure_num());
            tvNine.setText(data.getPhoto_tool());
            tvTen.setText(data.getVehicle());
            List<PeopleAddBean> infoList = data.getInfoList();
            if (infoList.size() > 0) {
                list.addAll(infoList);
            } else {
                list.add(new PeopleAddBean("pressure"));
            }
            List<PeopleAddBean> infoListtwo = data.getInfoClerkList();
            if (infoListtwo.size() > 0) {
                listTwo.addAll(infoListtwo);
            } else {
                listTwo.add(new PeopleAddBean("clerk"));
            }


        }

        adapterOne = new PeopleAddAdapter(context, list, "1");
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
                adapterOne.add(new PeopleAddBean("pressure"));
            }
        });


        adapterTwo = new PeopleAddAdapter(context, listTwo, "2");
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
                adapterTwo.add(new PeopleAddBean("clerk"));
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
        tvDepartment.setText(userBean.getSimple_department_name());

        map2.clear();
        map2.put("group_id", userBean.getDepartment_id());
        map2.put("staff_id", userBean.getStaff_id());
        getPresenter().getParentDepartmentStaffList(userBean.getStaff_token(), map2);
    }


    @OnClick({R.id.ivLeft, R.id.circleImageVIew, R.id.ivCheckDelete, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
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
                String mTvArea = tvArea.getText().toString();
                if (TextUtils.isEmpty(mTvArea)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入所属大区");
                    return;
                }
                String mTvRegion = tvRegion.getText().toString();
                if (TextUtils.isEmpty(mTvRegion)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入所属区域");
                    return;
                }
                String mTvFour = tvTwo.getText().toString();
                if (TextUtils.isEmpty(mTvFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入经销商名称");
                    return;
                }
                String mtvThree = tvThree.getText().toString();
                if (TextUtils.isEmpty(mtvThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入经销商姓名");
                    return;
                }
                String mtvFour = tvFour.getText().toString();
                if (TextUtils.isEmpty(mtvFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入经销商电话");
                    return;
                }


                ok.setEnabled(false);
                dialogLoading = new DialogLoading(context);
                dialogLoading.showDialog();
                map.clear();
                map.put("add_city", tvOne.getText().toString());
                map.put("big_area", mTvArea);
                map.put("area", mTvRegion);
                map.put("add_shop", mTvFour);
                map.put("shop_name", mtvThree);
                map.put("shop_tel", mtvFour);
                map.put("serve_name", tvFive.getText().toString());
                map.put("serve_tel", tvSix.getText().toString());
                map.put("person_num", tvSeven.getText().toString());
                map.put("pressure_num", tvEight.getText().toString());
                map.put("photo_tool", tvNine.getText().toString());
                map.put("vehicle", tvTen.getText().toString());
                map.put("next_audit_id", mNext_staff_id);

                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());

                List<PeopleAddBean> allData = adapterTwo.getAllData();
                List<PeopleAddBean> allData1 = adapterOne.getAllData();
                ArrayList<PeopleAddBean> list = new ArrayList<>();
                list.addAll(allData);
                list.addAll(allData1);
                if (list.size() > 0) {
                    String json = new Gson().toJson(list);
                    map.put("staffList", json);
                }
                switch (type) {
                    case "0"://新建
                        map.put("operation", "0");
                        getPresenter().insertStaffAdd(userBean.getStaff_token(), map);
                        break;
                    case "1"://编辑
                        map.put("operation", "1");
                        map.put("add_id", data.getAdd_id());
                        getPresenter().updateStaffAdd(userBean.getStaff_token(), map);
                        break;
                }
                break;
        }
    }


    @Override
    public PeoplePresenter createPresenter() {
        return new PeoplePresenter(getApp());
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
    public void onEventMainThread(FirstEventNew event) {
        switch (event.getMsg()) {
            case "1":
                MeParentBean bean = event.getmBean();
                Glide.with(context)
                        .load(Constants.BASE_URL + bean.getHead_img())
                        .error(R.drawable.error_img)
//                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(circleImageVIew);
                tvCheckPersonName.setText(bean.getStaff_name());
                ivCheckDelete.setVisibility(View.VISIBLE);
                mNext_staff_id = bean.getStaff_id();

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
        ok.setEnabled(true);
    }


    @Override
    public void oninsertStaffAdd(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void ondeleteStaffAdd(String data) {

    }

    @Override
    public void onupdateStaffAdd(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onauditStaffAdd(String data) {

    }

    @Override
    public void OngetStaffAddList(List<PeopleBean> data) {

    }

    @Override
    public void ongetStaffAddDetail(PeopleBean data) {

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