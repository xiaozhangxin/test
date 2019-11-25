package com.ak.pt.mvp.fragment.statistics;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.AreaPressureBean;
import com.ak.pt.bean.BigAreaBean;
import com.ak.pt.bean.FilterBean;
import com.ak.pt.bean.FirstEventFilter;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OrderCompleteDetailFragment extends BaseFragment<IPressureView, PressurePresenter> implements IPressureView {

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
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvStateTittle)
    TextView tvStateTittle;
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.tvTimeTittle)
    TextView tvTimeTittle;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvRefuseTittle)
    TextView tvRefuseTittle;
    @BindView(R.id.tvRefuseReason)
    TextView tvRefuseReason;
    @BindView(R.id.llRefuse)
    LinearLayout llRefuse;
    @BindView(R.id.ivOne)
    ImageView ivOne;
    @BindView(R.id.rlOne)
    RelativeLayout rlOne;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvHopeTime)
    TextView tvHopeTime;
    @BindView(R.id.tvQOne)
    TextView tvQOne;
    @BindView(R.id.tvQTwo)
    TextView tvQTwo;
    @BindView(R.id.tvQOneAdd)
    TextView tvQOneAdd;
    @BindView(R.id.tvQThree)
    TextView tvQThree;
    @BindView(R.id.tvQFive)
    TextView tvQFive;
    @BindView(R.id.tvQSix)
    TextView tvQSix;
    @BindView(R.id.tvQSeven)
    TextView tvQSeven;
    @BindView(R.id.tvQEight)
    TextView tvQEight;
    @BindView(R.id.tvQNine)
    TextView tvQNine;
    @BindView(R.id.tvQTen)
    TextView tvQTen;
    @BindView(R.id.llChildOne)
    LinearLayout llChildOne;
    @BindView(R.id.ivTwo)
    ImageView ivTwo;
    @BindView(R.id.rlTwo)
    RelativeLayout rlTwo;
    @BindView(R.id.tvWOne)
    TextView tvWOne;
    @BindView(R.id.tvWTwo)
    TextView tvWTwo;
    @BindView(R.id.tvWThree)
    TextView tvWThree;
    @BindView(R.id.tvWFour)
    TextView tvWFour;
    @BindView(R.id.tvWFive)
    TextView tvWFive;
    @BindView(R.id.tvWSix)
    TextView tvWSix;
    @BindView(R.id.tvWSeven)
    TextView tvWSeven;
    @BindView(R.id.tvWEight)
    TextView tvWEight;
    @BindView(R.id.tvWNine)
    TextView tvWNine;
    @BindView(R.id.tvWTen)
    TextView tvWTen;
    @BindView(R.id.tvWEleven)
    TextView tvWEleven;
    @BindView(R.id.tvWTwelve)
    TextView tvWTwelve;
    @BindView(R.id.llChildTwo)
    LinearLayout llChildTwo;
    @BindView(R.id.ivThree)
    ImageView ivThree;
    @BindView(R.id.rlThree)
    RelativeLayout rlThree;
    @BindView(R.id.tvEOne)
    TextView tvEOne;
    @BindView(R.id.tvEOneAdd)
    TextView tvEOneAdd;
    @BindView(R.id.tvETwoAdd)
    TextView tvETwoAdd;
    @BindView(R.id.tvETwo)
    TextView tvETwo;
    @BindView(R.id.tvWaterType)
    TextView tvWaterType;
    @BindView(R.id.tvEThree)
    TextView tvEThree;
    @BindView(R.id.tvTds)
    TextView tvTds;
    @BindView(R.id.tvEFour)
    TextView tvEFour;
    @BindView(R.id.tvEFive)
    TextView tvEFive;
    @BindView(R.id.tvESix)
    TextView tvESix;
    @BindView(R.id.tvESeven)
    TextView tvESeven;
    @BindView(R.id.tvEEight)
    TextView tvEEight;
    @BindView(R.id.tvENine)
    TextView tvENine;
    @BindView(R.id.llChildThree)
    LinearLayout llChildThree;
    @BindView(R.id.ivFour)
    ImageView ivFour;
    @BindView(R.id.rlFour)
    RelativeLayout rlFour;
    @BindView(R.id.tvMOneAdd)
    TextView tvMOneAdd;
    @BindView(R.id.tvMTwoAdd)
    TextView tvMTwoAdd;
    @BindView(R.id.tvMOne)
    TextView tvMOne;
    @BindView(R.id.tvMTwo)
    TextView tvMTwo;
    @BindView(R.id.tvMThree)
    TextView tvMThree;
    @BindView(R.id.tvMFour)
    TextView tvMFour;
    @BindView(R.id.tvMFive)
    TextView tvMFive;
    @BindView(R.id.tvMSix)
    TextView tvMSix;
    @BindView(R.id.tvMSeven)
    TextView tvMSeven;
    @BindView(R.id.tvMEight)
    TextView tvMEight;
    @BindView(R.id.tvIsPressurePass)
    TextView tvIsPressurePass;
    @BindView(R.id.tvMNine)
    TextView tvMNine;
    @BindView(R.id.tvMTen)
    TextView tvMTen;
    @BindView(R.id.fiveR)
    TextView fiveR;
    @BindView(R.id.tvPhoto)
    TextView tvPhoto;
    @BindView(R.id.llChildFour)
    LinearLayout llChildFour;
    @BindView(R.id.ok)
    Button ok;

    @BindView(R.id.tvAddDepartment)
    TextView tvAddDepartment;


    private PressurePageBean beanNow;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String doc_id;
    private AppPermissionsBean permissionsBean;

    public static OrderCompleteDetailFragment newInstance(String doc_id, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        OrderCompleteDetailFragment fragment = new OrderCompleteDetailFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.doc_id = doc_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_order_manager_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("试压单详情");
        initAnimation();
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("doc_no", doc_id);
        getPresenter().queryDetail(userBean.getStaff_token(), map);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void OnQueryDetail(PressurePageBean bean) {
        if (TextUtils.isEmpty(bean.getDoc_no())) {
            final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
            builder1.setMessage(getString(R.string.deleted));
            builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    dialog.dismiss();
                }
            });
            builder1.onCreate().show();
            return;
        }
        //（order:未发出,accept:已发出,plan:已接单,success:待审核,result:拒绝,cancel:作废,audit:已审核delete:已删除）
        switch (bean.getFlow_state()) {
            case "order":
            case "success":
            case "accept":
            case "plan":
            case "cancel":
            case "delete":
            case "audit":
                tvState.setTextColor(getResources().getColor(R.color.audit_two));
                break;
            case "result":
                List<ResultListBean> resultList = bean.getResultList();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < resultList.size(); i++) {
                    stringBuilder.append(resultList.get(i).getResult_remark());
                }
                tvRefuseReason.setText(stringBuilder);
                tvState.setTextColor(getResources().getColor(R.color.img_color));
                break;
        }
        beanNow = bean;
        tvAddDepartment.setText(bean.getArea());
        tvOne.setText(bean.getAddress());
        tvNo.setText(bean.getDoc_no());
        tvName.setText(bean.getStaff_name());
        tvState.setText(bean.getFlow_state_show());
        tvTime.setText(bean.getTrn_date());
        tvHopeTime.setText(bean.getBook_time());
        tvTds.setText(bean.getTds());

        tvQOne.setText(bean.getPressure_type());
        tvQTwo.setText(bean.getHouse_area_name());
        tvQOneAdd.setText(bean.getHouse_area_no());
        tvQThree.setText(bean.getAddress_type());
        // tvQFour.setText(bean.getPressure_type());
        tvQFive.setText(bean.getScene_contact());
        tvQSix.setText(bean.getScene_contact_tel());
        tvQSeven.setText(bean.getDeclaration_type());
        tvQEight.setText(bean.getOwner_name());
        tvQNine.setText(bean.getOwner_tel());

        tvQTen.setText(bean.getIs_presence());

        tvWOne.setText(bean.getDistributor_name());
        tvWTwo.setText(bean.getDecoration_company());
        tvWThree.setText(bean.getDecoration_company_tel());
        tvWFour.setText(bean.getProject_manager());
        tvWFive.setText(bean.getProject_manager_tel());
        tvWSix.setText(bean.getProject_manager_score());
        // tvWSeven.setText(bean.getIntegral_score());
        tvWEight.setText(bean.getHydraulic_name());
        tvWNine.setText(bean.getHydraulic_tel());
        tvWTen.setText(bean.getHydraulic_score());
        //  tvWEleven.setText(bean.getHydraulic_add());
        tvWTwelve.setText(bean.getMember_card());


        tvEOne.setText(bean.getKitchen());
        tvEOneAdd.setText(bean.getToilet());
        tvETwoAdd.setText(bean.getBalcony());
        tvETwo.setText(bean.getPipe_type());//水管类型
        tvEThree.setText(bean.getServe_type());//试压类型
        tvEFour.setText(bean.getPipe_trend());
        tvEFive.setText(bean.getInstall_type());
        tvESix.setText(bean.getPipe_brand());

        tvESeven.setText(bean.getSpool_type());//线管品牌
        tvEEight.setText(bean.getDoor_packet());
        tvENine.setText(bean.getIs_care_water());

        tvMOneAdd.setText(bean.getPlumber_name());
        tvMTwoAdd.setText(bean.getPlumber_tel());
        tvMOne.setText(bean.getSpecial_event());
        tvMTwo.setText(bean.getLeakage_reason());
        tvMThree.setText(bean.getDescription());
        tvMFour.setText(bean.getPressure_time());
        tvMFive.setText(bean.getPressure_complete_time());
        tvMSix.setText(bean.getPressure_tool());
        tvMSeven.setText(bean.getPressure_pressure());
        tvMEight.setText(bean.getPressure_drop());
        tvMNine.setText(bean.getAuthentic());
        tvIsPressurePass.setText(bean.getIs_qualified());
        tvWaterType.setText(bean.getWater_type());

        tvMTen.setText(bean.getQuality_card());
        if (bean.getPhotoList().size() > 0) {
            tvPhoto.setText("已上传");
        } else {
            tvPhoto.setText("未上传");
        }

    }

    @Override
    public void OnQueryBigAreaCountPressurePage(List<BigAreaBean> data) {

    }

    @Override
    public void OnGetTestPressureList(List<PressurePageBean> data,String total) {

    }

    @Override
    public void onAuditTestPressure(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        onResume();
    }

    @Override
    public void onSendWaterMessage(String data) {

    }

    @Override
    public void onDeleteTestPressure(String data) {
        EventBus.getDefault().post(new FirstEventFilter("mpt_delete", new FilterBean("", "", "", "", "")));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onAuditAppTestPressure(String data) {

    }

    private boolean oneShow = true;
    private boolean twoShow = false;
    private boolean threeShow = false;
    private boolean fourShow = false;
    private Animation upRotate;
    private Animation upRotateOne;
    private Animation upRotateTwo;
    private Animation upRotateThree;
    private Animation downRotate;
    private Animation downRotateTwo;
    private Animation downRotateThree;
    private Animation downRotateFour;

    private void initAnimation() {
        upRotate = AnimationUtils.loadAnimation(context, R.anim.rote_anim);
        upRotate.setFillAfter(true);
        upRotateOne = AnimationUtils.loadAnimation(context, R.anim.rote_anim);
        upRotateOne.setFillAfter(true);
        upRotateTwo = AnimationUtils.loadAnimation(context, R.anim.rote_anim);
        upRotateTwo.setFillAfter(true);
        upRotateThree = AnimationUtils.loadAnimation(context, R.anim.rote_anim);
        upRotateThree.setFillAfter(true);


        downRotate = AnimationUtils.loadAnimation(context, R.anim.rote_anim_down);
        downRotate.setFillAfter(true);
        downRotateTwo = AnimationUtils.loadAnimation(context, R.anim.rote_anim_down);
        downRotateTwo.setFillAfter(true);
        downRotateThree = AnimationUtils.loadAnimation(context, R.anim.rote_anim_down);
        downRotateThree.setFillAfter(true);
        downRotateFour = AnimationUtils.loadAnimation(context, R.anim.rote_anim_down);
        downRotateFour.setFillAfter(true);
    }


    @OnClick({R.id.ivLeft, R.id.tvPhoto, R.id.rlOne, R.id.rlTwo, R.id.rlThree, R.id.rlFour,
            R.id.tvQSix, R.id.tvQNine, R.id.tvWThree, R.id.tvWFive, R.id.tvWNine, R.id.tvMTwoAdd})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvQSix://现场联系人电话
                String contactTel = beanNow.getScene_contact_tel();
                if (!TextUtils.isEmpty(contactTel)) {
                    callPhone("现场联系人" + beanNow.getScene_contact(), contactTel);
                }
                break;
            case R.id.tvQNine://业主电话
                String ownerTel = beanNow.getOwner_tel();
                if (!TextUtils.isEmpty(ownerTel)) {
                    callPhone("业主" + beanNow.getOwner_name(), ownerTel);
                }
                break;
            case R.id.tvWThree://装修公司电话
                String decorationCompanyTel = beanNow.getDecoration_company_tel();
                if (!TextUtils.isEmpty(decorationCompanyTel)) {
                    callPhone("装修公司" + beanNow.getDecoration_company(), decorationCompanyTel);
                }
                break;
            case R.id.tvWFive://项目经理电话
                String projectManagerTel = beanNow.getProject_manager_tel();
                if (!TextUtils.isEmpty(projectManagerTel)) {
                    callPhone("项目经理" + beanNow.getProject_manager(), projectManagerTel);
                }
                break;
            case R.id.tvWNine://水工电话
                String hydraulicTel = beanNow.getHydraulic_tel();
                if (!TextUtils.isEmpty(hydraulicTel)) {
                    callPhone("水工" + beanNow.getHydraulic_name(), hydraulicTel);
                }
                break;
            case R.id.tvMTwoAdd://试压工电话
                String plumberTel = beanNow.getPlumber_tel();
                if (!TextUtils.isEmpty(plumberTel)) {
                    callPhone("试压工" + beanNow.getPlumber_name(), plumberTel);
                }
                break;
            case R.id.rlOne:
                if (oneShow) {
                    llChildOne.setVisibility(View.GONE);
                    ivOne.startAnimation(downRotate);
                } else {
                    llChildOne.setVisibility(View.VISIBLE);
                    ivOne.startAnimation(upRotate);
                }
                oneShow = !oneShow;
                break;
            case R.id.rlTwo:
                if (twoShow) {
                    llChildTwo.setVisibility(View.GONE);
                    ivTwo.startAnimation(downRotateTwo);
                } else {
                    llChildTwo.setVisibility(View.VISIBLE);
                    ivTwo.startAnimation(upRotateOne);
                }
                twoShow = !twoShow;
                break;
            case R.id.rlThree:
                if (threeShow) {
                    llChildThree.setVisibility(View.GONE);
                    ivThree.startAnimation(downRotateThree);
                } else {
                    llChildThree.setVisibility(View.VISIBLE);
                    ivThree.startAnimation(upRotateTwo);
                }
                threeShow = !threeShow;
                break;
            case R.id.rlFour:
                if (fourShow) {
                    llChildFour.setVisibility(View.GONE);
                    ivFour.startAnimation(downRotateFour);
                } else {
                    llChildFour.setVisibility(View.VISIBLE);
                    ivFour.startAnimation(upRotateThree);
                }
                fourShow = !fourShow;
                break;
            case R.id.tvPhoto:
                if (beanNow.getPhotoList().size() > 0) {
                    startOrderImgTwoFragment(beanNow.getPhotoList());
                }
                break;
        }
    }

    public void callPhone(String type, final String phone) {
        final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
        builder1.setMessage("呼叫" + type + "\n" + phone + "?");
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public PressurePresenter createPresenter() {
        return new PressurePresenter(getApp());
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

    @Override
    public void OnQueryAreaCountPressurePage(List<AreaPressureBean> data) {

    }

    @Override
    public void OnQueryPressurePage(List<PressurePageBean> data) {

    }


}
