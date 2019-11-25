package com.ak.pt.mvp.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.AreaPressureBean;
import com.ak.pt.bean.BigAreaBean;
import com.ak.pt.bean.PhotoListBean;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.mvp.fragment.statistics.ResultListBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.fragment.statistics.PressurePresenter;
import com.ak.pt.mvp.fragment.statistics.IPressureView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/11/23.
 */

public class OrderWorkerDetailFragment extends BaseFragment<IPressureView, PressurePresenter> implements IPressureView {
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
    @BindView(R.id.tvEdit)
    TextView tvEdit;
    @BindView(R.id.tvSendMsg)
    TextView tvSendMsg;
    @BindView(R.id.TvBle)
    TextView TvBle;
    @BindView(R.id.llWork)
    LinearLayout llWork;
    @BindView(R.id.tvRefuse)
    TextView tvRefuse;
    @BindView(R.id.tvAccept)
    TextView tvAccept;
    @BindView(R.id.llAccept)
    LinearLayout llAccept;
    @BindView(R.id.lineRefuse)
    View lineRefuse;
    @BindView(R.id.tvAddDepartment)
    TextView tvAddDepartment;
    @BindView(R.id.tvCardType)
    TextView tvCardType;
    @BindView(R.id.llCard)
    LinearLayout llCard;
    @BindView(R.id.tvAddTime)
    TextView tvAddTime;
    @BindView(R.id.tvOverKg)
    TextView tvOverKg;
    @BindView(R.id.tvWAddOne)
    TextView tvWAddOne;

    private PressurePageBean beanNow;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String doc_id;
    private AppPermissionsBean permissionsBean;

    public static OrderWorkerDetailFragment newInstance(String doc_id, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        OrderWorkerDetailFragment fragment = new OrderWorkerDetailFragment();
        fragment.doc_id = doc_id;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_pressure_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.pressure_detail);
        initAnimation();
    }

    @Override
    public void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("doc_no", doc_id);
        getPresenter().queryDetail(userBean.getStaff_token(), map);
    }

    @Override
    public void OnQueryDetail(PressurePageBean bean) {
        if (TextUtils.isEmpty(bean.getDoc_no())) {
            final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
            builder1.setMessage(getString(R.string.deleted));
            builder1.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
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
            case "accept":
                llAccept.setVisibility(View.VISIBLE);
                ivRight.setVisibility(View.GONE);
                ivRightTwo.setVisibility(View.GONE);
                tvState.setTextColor(getResources().getColor(R.color.audit_one));
                break;
            case "plan":
                llWork.setVisibility(View.VISIBLE);
                llAccept.setVisibility(View.GONE);
                ivRight.setVisibility(View.VISIBLE);
                ivRightTwo.setVisibility(View.VISIBLE);
                tvState.setTextColor(getResources().getColor(R.color.audit_two));
                break;
            case "success":
                tvState.setTextColor(getResources().getColor(R.color.audit_two));
                llWork.setVisibility(View.GONE);
                llAccept.setVisibility(View.GONE);
                ivRight.setVisibility(View.VISIBLE);
                ivRightTwo.setVisibility(View.GONE);
                break;
            case "result":
                tvState.setTextColor(getResources().getColor(R.color.audit_three));
                ivRight.setVisibility(View.GONE);
                ivRightTwo.setVisibility(View.GONE);
                llAccept.setVisibility(View.GONE);
                llWork.setVisibility(View.GONE);
                llRefuse.setVisibility(View.VISIBLE);
                lineRefuse.setVisibility(View.VISIBLE);
                List<ResultListBean> resultList = bean.getResultList();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < resultList.size(); i++) {
                    stringBuilder.append(resultList.get(i).getResult_remark());
                }
                tvRefuseReason.setText(stringBuilder);
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
        tvWAddOne.setText(bean.getDistributor_tel());

        tvQOne.setText(bean.getPressure_type());
        tvQTwo.setText(bean.getHouse_area_name());
        tvQOneAdd.setText(bean.getHouse_area_no());
        tvQThree.setText(bean.getAddress_type());
        //tvQFour.setText(bean.getServe_type());
        tvQFive.setText(bean.getScene_contact());
        tvQSix.setText(bean.getScene_contact_tel());
        tvQSeven.setText(bean.getDeclaration_type());
        tvQEight.setText(bean.getOwner_name());
        tvQNine.setText(bean.getOwner_tel());
        tvQTen.setText(bean.getIs_presence());

        tvTds.setText(bean.getTds());
        tvWOne.setText(bean.getDistributor_name());
        tvWTwo.setText(bean.getDecoration_company());
        tvWThree.setText(bean.getDecoration_company_tel());
        tvWFour.setText(bean.getProject_manager());
        tvWFive.setText(bean.getProject_manager_tel());
        tvWSix.setText(bean.getProject_manager_score());


        tvWEight.setText(bean.getHydraulic_name());
        tvWNine.setText(bean.getHydraulic_tel());
        tvWTen.setText(bean.getHydraulic_score());
        // tvWEleven.setText(bean.getHydraulic_add());
        tvWTwelve.setText(bean.getMember_card());

        tvEOne.setText(bean.getKitchen());
        tvEOneAdd.setText(bean.getToilet());
        tvETwoAdd.setText(bean.getBalcony());
        tvETwo.setText(bean.getPipe_type());
        tvEThree.setText(bean.getServe_type());
        tvEFour.setText(bean.getPipe_trend());
        tvEFive.setText(bean.getInstall_type());
        tvESix.setText(bean.getPipe_brand());
        tvESeven.setText(bean.getSpool_type());
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
        tvCardType.setText(OrderAddFragment.transformAssuranceCard(bean.getAssurance_card()));//质保卡类型

        tvOverKg.setText(bean.getLast_pressure());//结束压力值
        tvAddTime.setText(bean.getPress_time());//试压时间（分钟）
        if ("2".equals(bean.getAssurance_card())) {
            llCard.setVisibility(View.GONE);
        } else {
            llCard.setVisibility(View.VISIBLE);
            tvMTen.setText(bean.getQuality_card());//质保卡卡号
        }
        if (bean.getPhotoList().size() > 0) {
            tvPhoto.setText(R.string.already_upload);
        } else {
            tvPhoto.setText(R.string.not_upload);
        }
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


    @OnClick({R.id.ivLeft, R.id.tvPhoto, R.id.TvBle, R.id.ivRight, R.id.ivRightTwo,
            R.id.tvRefuse, R.id.tvAccept, R.id.tvEdit, R.id.tvSendMsg, R.id.rlOne,
            R.id.rlTwo, R.id.rlThree,
            R.id.rlFour, R.id.tvQSix, R.id.tvQNine, R.id.tvWThree, R.id.tvWFive, R.id.tvWNine, R.id.tvMTwoAdd})
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
            case R.id.ivRight://上传图片
                if (beanNow != null) {
                    startUpImgFragment(beanNow.getDoc_no(), beanNow.getAddress());
                }
                break;
            case R.id.ivRightTwo://确认完成(未上传图片无法确认完成)
                boolean isPhoto = false;
                List<PhotoListBean> photoList = beanNow.getPhotoList();
                for (int i = 0; i < photoList.size(); i++) {
                    if ("安装回执单".equals(photoList.get(i).getType_name())) {
                        isPhoto = true;
                    }
                }
                //判断是否上传回执单
                if (isPhoto) {
                    onComplete();
                } else {
                    onPoint();
                }
                break;
            case R.id.tvEdit:
                startOrderAddFragment(beanNow, "2", permissionsBean);
                break;
            case R.id.TvBle:
                startBleListFragment(beanNow);
                break;
            case R.id.tvSendMsg:
                sendMessage();
                break;
            case R.id.tvAccept:
                onAccept();
                break;
            case R.id.tvRefuse:
                onRefuse();

                break;
            case R.id.tvPhoto:
                if (beanNow.getPhotoList().size() > 0) {
                    startOrderImgTwoFragment(beanNow.getPhotoList());
                } else {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.hava_no_img));
                }
                break;
        }
    }


    //打电话
    public void callPhone(String type, final String phone) {
        final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
        builder1.setMessage("呼叫" + type + "\n" + phone + "?");
        builder1.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                sq(phone);
                dialog.dismiss();
            }
        });
        builder1.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
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


    //提示上传回执单照片弹框
    private void onPoint() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.please_up_img);
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    //是否拒绝派单
    private void onRefuse() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_edittext_two, null);
        final EditText edit = (EditText) inflate.findViewById(R.id.editText);//获得输入框对象
        builder.setView(inflate);
        builder.setNegativeButton(getString(R.string.cancel), null);
        builder.setPositiveButton(getString(R.string.sure), null);
        final AlertDialog alertDialog1 = builder.create();
        alertDialog1.setCancelable(false);
        alertDialog1.show();
        alertDialog1.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果想关闭dialog直接加上下面这句代码就行
                String s = edit.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_input_refuse_reason));
                    return;
                }
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("doc_no", beanNow.getDoc_no());
                map.put("result_remark", edit.getText().toString());
                map.put("flow_state", "result");
                getPresenter().auditAppTestPressure(userBean.getStaff_token(), map);
                alertDialog1.cancel();
            }
        });
    }

    //接受派单弹窗
    private void onAccept() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle(R.string.sure_accept);
        builder1.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder1.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("doc_no", beanNow.getDoc_no());
                map.put("flow_state", "plan");
                getPresenter().auditAppTestPressure(userBean.getStaff_token(), map);
            }
        });
        builder1.show();
    }

    //给水工发消息
    private void sendMessage() {
        AlertDialog.Builder builderTwo = new AlertDialog.Builder(context);
        builderTwo.setTitle(R.string.send_message_to_water);
        builderTwo.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builderTwo.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("doc_no", beanNow.getDoc_no());
                getPresenter().sendWaterMessage(userBean.getStaff_token(), map);
            }
        });
        builderTwo.show();
    }

    //是否确认完工
    private void onComplete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.finish_sure);
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("doc_no", beanNow.getDoc_no());
                map.put("flow_state", "success");
                getPresenter().auditAppTestPressure(userBean.getStaff_token(), map);
            }
        });
        builder.show();
    }

    @Override
    public void OnQueryBigAreaCountPressurePage(List<BigAreaBean> data) {
    }

    @Override
    public void OnGetTestPressureList(List<PressurePageBean> data, String total) {
    }

    @Override
    public void onAuditTestPressure(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        onResume();
    }

    @Override
    public void onSendWaterMessage(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
    }

    @Override
    public void onDeleteTestPressure(String data) {

    }

    @Override
    public void onAuditAppTestPressure(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        onResume();
    }

    @Override
    public PressurePresenter createPresenter() {
        return new PressurePresenter(getApp());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
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
