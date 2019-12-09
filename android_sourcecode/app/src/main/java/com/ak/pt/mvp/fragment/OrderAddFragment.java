package com.ak.pt.mvp.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.core.content.ContextCompat;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.DepartmentBean;
import com.ak.pt.bean.FirstEvent;
import com.ak.pt.bean.FirstEventWorker;
import com.ak.pt.mvp.fragment.statistics.MemoryBean;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.bean.WorkerBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.OrderPresenter;
import com.ak.pt.mvp.view.IOrderView;
import com.ak.pt.util.DateUtil;
import com.ak.pt.util.DialogUtil;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.ak.pt.mvp.fragment.adaily.SignFragment.REQUEST_CARERAM;
import static com.king.base.BaseInterface.REQUEST_CODE;

/**
 * Created by admin on 2019/1/16.
 */

public class OrderAddFragment extends BaseFragment<IOrderView, OrderPresenter> implements IOrderView {

    Unbinder unbinder;

    @BindView(R.id.tvAddTime)
    EditText tvAddTime;//第二阶段试压时间
    @BindView(R.id.tvOverKg)
    EditText tvOverKg;//最终压力值
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
    @BindView(R.id.tvAddDepartment)
    TextView tvAddDepartment;
    @BindView(R.id.ivOne)
    ImageView ivOne;
    @BindView(R.id.rlOne)
    RelativeLayout rlOne;
    @BindView(R.id.tvQOne)
    EditText tvQOne;
    @BindView(R.id.tvHopeTIme)
    TextView tvHopeTIme;
    @BindView(R.id.tvQTwo)
    EditText tvQTwo;
    @BindView(R.id.tvQOneAdd)
    EditText tvQOneAdd;
    @BindView(R.id.tvQThree)
    TextView tvQThree;
    @BindView(R.id.tvQFour)
    TextView tvQFour;
    @BindView(R.id.tvQFive)
    EditText tvQFive;
    @BindView(R.id.tvQSix)
    EditText tvQSix;
    @BindView(R.id.tvQSeven)
    TextView tvQSeven;
    @BindView(R.id.tvQEight)
    EditText tvQEight;
    @BindView(R.id.tvQNine)
    EditText tvQNine;
    @BindView(R.id.tvQTen)
    TextView tvQTen;
    @BindView(R.id.labQTen)
    TextView labQTen;
    @BindView(R.id.llChildOne)
    LinearLayout llChildOne;
    @BindView(R.id.ivTwo)
    ImageView ivTwo;
    @BindView(R.id.rlTwo)
    RelativeLayout rlTwo;
    @BindView(R.id.tvWSix)
    EditText tvWSix;
    @BindView(R.id.tvWSeven)
    EditText tvWSeven;
    @BindView(R.id.tvWTen)
    EditText tvWTen;
    @BindView(R.id.tvWEleven)
    EditText tvWEleven;
    @BindView(R.id.tvWTwelve)
    EditText tvWTwelve;
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
    EditText tvWaterType;
    @BindView(R.id.tvEThree)
    TextView tvEThree;
    @BindView(R.id.tvTDS)
    EditText tvTDS;
    @BindView(R.id.tvEFour)
    TextView tvEFour;
    @BindView(R.id.tvEFive)
    TextView tvEFive;
    @BindView(R.id.tvESix)
    EditText tvESix;
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
    EditText tvMTwoAdd;
    @BindView(R.id.tvMOne)
    TextView tvMOne;
    @BindView(R.id.tvMTwo)
    EditText tvMTwo;
    @BindView(R.id.tvMThree)
    EditText tvMThree;
    @BindView(R.id.tvMFour)
    TextView tvMFour;
    @BindView(R.id.tvMFive)
    TextView tvMFive;
    @BindView(R.id.tvMSix)
    TextView tvMSix;
    @BindView(R.id.tvMSeven)
    EditText tvMSeven;
    @BindView(R.id.tvMEight)
    EditText tvMEight;
    @BindView(R.id.tvIsPressurePass)
    TextView tvIsPressurePass;
    @BindView(R.id.oneTittle)
    TextView oneTittle;
    @BindView(R.id.tvMNine)
    TextView tvMNine;
    @BindView(R.id.ivSCAN)
    ImageView ivSCAN;
    @BindView(R.id.tvCardType)
    TextView tvCardType;
    @BindView(R.id.tvMTen)
    EditText tvMTen;
    @BindView(R.id.llCard)
    LinearLayout llCard;
    @BindView(R.id.llChildFour)
    LinearLayout llChildFour;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.lineDepartment)
    View lineDepartment;
    @BindView(R.id.llDepartment)
    LinearLayout llDepartment;

    @BindView(R.id.tvWOne)
    AppCompatAutoCompleteTextView tvWOne;
    @BindView(R.id.tvWAddOne)
    AppCompatAutoCompleteTextView tvWAddOne;
    @BindView(R.id.tvWTwo)
    AppCompatAutoCompleteTextView tvWTwo;
    @BindView(R.id.tvWThree)
    AppCompatAutoCompleteTextView tvWThree;
    @BindView(R.id.tvWEight)
    AppCompatAutoCompleteTextView tvWEight;
    @BindView(R.id.tvWNine)
    AppCompatAutoCompleteTextView tvWNine;
    @BindView(R.id.tvWFour)
    AppCompatAutoCompleteTextView tvWFour;
    @BindView(R.id.tvWFive)
    AppCompatAutoCompleteTextView tvWFive;

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private PressurePageBean bean;
    private String addType;
    private AppPermissionsBean permissionsBean;

    private String worker_id = "";//试压工id
    private String group_id = "";//所属区域id

    private AppCompatAutoCompleteTextView[] memoryAutoCompleteText = new AppCompatAutoCompleteTextView[8];

    public static OrderAddFragment newInstance(PressurePageBean bean, String id, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        OrderAddFragment fragment = new OrderAddFragment();
        fragment.bean = bean;
        fragment.permissionsBean = permissionsBean;
        fragment.addType = id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_order_add;
    }

    private void initMemoryAutoComplete() {
        memoryAutoCompleteText[0] = tvWOne;     //  经销商名称
        memoryAutoCompleteText[1] = tvWAddOne;  //  经销商电话
        memoryAutoCompleteText[2] = tvWTwo;     //  装修公司
        memoryAutoCompleteText[3] = tvWThree;   //  装修公司电话
        memoryAutoCompleteText[4] = tvWEight;   //  水工姓名
        memoryAutoCompleteText[5] = tvWNine;    //  水工电话
        memoryAutoCompleteText[6] = tvWFour;    //  项目经理姓名
        memoryAutoCompleteText[7] = tvWFive;    //  项目经理电话
    }

    private void initMemoryData() {
        for (int i = 0; i < 8; ++ i){
            getMemory(String.valueOf(i), "");
        }
    }

    @Override
    public void initUI() {
        llDepartment.setVisibility(View.GONE);
        lineDepartment.setVisibility(View.GONE);
        initMemoryAutoComplete();
        if ("0".equals(addType)) {
            tvCardType.setTag("1");
            tvTitle.setText("新增试压报单");
        } else {
            tvTitle.setText("编辑试压报单");
            worker_id = bean.getPlumber_id();

            labQTen.setCompoundDrawablesWithIntrinsicBounds(R.drawable.must_img,0,0,0);

            tvAddDepartment.setText(bean.getArea());//所属区域
            group_id = bean.getArea_id();

            tvQOne.setText(bean.getAddress());
            tvQTwo.setText(bean.getHouse_area_name());
            tvQOneAdd.setText(bean.getHouse_area_no());
            tvQThree.setText(bean.getAddress_type());
            tvQFour.setText(bean.getPressure_type());
            tvQFive.setText(bean.getScene_contact());
            tvQSix.setText(bean.getScene_contact_tel());
            tvQSeven.setText(bean.getDeclaration_type());
            tvQEight.setText(bean.getOwner_name());
            tvQNine.setText(bean.getOwner_tel());
            tvQTen.setText(bean.getIs_presence());
            tvHopeTIme.setText(bean.getBook_time());


            tvWAddOne.setText(bean.getDistributor_tel());

            tvWOne.setText(bean.getDistributor_name());
            tvWTwo.setText(bean.getDecoration_company());
            tvWThree.setText(bean.getDecoration_company_tel());
            tvWFour.setText(bean.getProject_manager());
            tvWFive.setText(bean.getProject_manager_tel());
            tvWSix.setText(bean.getProject_manager_score());
            // tvWSeven.setText(bean.getIntegral_score());项目经理累计积分
            tvWEight.setText(bean.getHydraulic_name());
            tvWNine.setText(bean.getHydraulic_tel());
            tvWTen.setText(bean.getHydraulic_score());
            // tvWEleven.setText(bean.getHydraulic_add());水工累计积分
            tvWTwelve.setText(bean.getMember_card());

            tvTDS.setText(bean.getTds());
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

            worker_id = bean.getPlumber_id();
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

            tvOverKg.setText(bean.getLast_pressure());//结束压力值
            tvAddTime.setText(bean.getPress_time());//试压时间（分钟）

            tvIsPressurePass.setText(bean.getIs_qualified());

            tvCardType.setTag(bean.getAssurance_card());
            switch (bean.getAssurance_card()) {
                case "0":
                    tvCardType.setText("电子质保卡");
                    llCard.setVisibility(View.VISIBLE);
                    tvMTen.setEnabled(false);
                    tvMTen.setText(bean.getQuality_card());
                    break;
                case "1":
                    tvCardType.setText("纸质质保卡");
                    llCard.setVisibility(View.VISIBLE);
                    tvMTen.setEnabled(true);
                    tvMTen.setText(bean.getQuality_card());
                    break;
                case "2":
                    tvCardType.setText("无质保卡");
                    llCard.setVisibility(View.GONE);
                    break;
            }

            tvWaterType.setText(bean.getWater_type());
        }
        initPressureTool(tvMSix.getText());
        initAnimation();
    }

    private void initPressureTool(CharSequence tool){
        if (TextUtils.isEmpty(tool)){
            tvMSix.setText("电子试压仪");
        }
        setPressureInfoEnable("手动试压泵".equals(tool));
    }

    private void setPressureInfoEnable(boolean enable){
        tvMFour.setEnabled(enable);
        tvMFour.setHint(enable ? "请输入试压开始时间" : "试压完成自动上传");
        tvMFive.setEnabled(enable);
        tvMFive.setHint(enable ? "请输入试压完成时间" : "试压完成自动上传");
        tvAddTime.setEnabled(enable);
        tvAddTime.setHint(enable ? "请输入试压时间" : "试压完成自动上传");
        tvMSeven.setEnabled(enable);
        tvMSeven.setHint(enable ? "请输入试压压力值" : "试压完成自动上传");
        tvOverKg.setEnabled(enable);
        tvOverKg.setHint(enable ? "请输入试压结束压力值" : "试压完成自动上传");
        tvMEight.setEnabled(enable);
        tvMEight.setHint(enable ? "请输入试压压降值" : "试压完成自动上传");
        tvIsPressurePass.setEnabled(enable);
        tvIsPressurePass.setHint(enable ? "请输入试压是否合格" : "试压完成自动上传");
    }

    private void clearPressureInfoText(){
        tvMFour.setText("");
        tvMFive.setText("");
        tvAddTime.setText("");
        tvMSeven.setText("");
        tvOverKg.setText("");
        tvMEight.setText("");

        tvIsPressurePass.setText("");
    }

    public static String transformAssuranceCard(String type) {
        switch (type) {
            case "0":
                return "电子质保卡";
            case "1":
                return "纸质质保卡";
            case "2":
                return "无质保卡";
        }
        return "";
    }

    //获取记忆词汇
    @Override
    public void onGetMemoryList(String type, List<MemoryBean> data) {
        if (data.size() > 0) {
            String[] res = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                res[i] = data.get(i).getName();
            }
            memoryAutoCompleteText[Integer.valueOf(type)].setAdapter(new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, res));
        }
    }


    //请求记忆词汇
    //type  0:经销商名1:经销商电话2:装饰公司名3:装饰公司电话4:水工名5:水工电话
    private void getMemory(String type, String content) {
        Map<String, String> temp = new HashMap<>();
        temp.put("name", content);
        temp.put("type", type);
        if (userBean==null){
            return;
        }
        getPresenter().getMemoryList(userBean.getStaff_token(), temp);

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        initMemoryData();
        switch (addType) {
            case "0":
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                Date curDate = new Date(System.currentTimeMillis());
                String str = formatter.format(curDate);
                tvTime.setText(str);
                tvName.setText(userBean.getStaff_name());
                group_id = userBean.getDepartment_id();
                tvAddDepartment.setText(userBean.getSimple_department_name());
                break;
            case "1":
                tvTime.setText(bean.getTrn_date());
                tvName.setText(bean.getStaff_name());
                break;
        }

    }


    @OnClick({R.id.ivLeft, R.id.tvAddDepartment, R.id.tvQThree, R.id.tvMOneAdd, R.id.tvQFour, R.id.tvQSeven, R.id.tvQTen, R.id.tvWEight, R.id.tvENine, R.id.tvHopeTIme,
            R.id.tvEOne, R.id.tvEOneAdd, R.id.tvETwoAdd, R.id.tvETwo, R.id.tvEThree, R.id.tvEFour, R.id.tvEFive, R.id.tvESix, R.id.tvESeven, R.id.tvEEight,
            R.id.ok, R.id.tvMOne, R.id.tvMFour, R.id.tvMFive, R.id.tvMSix, R.id.tvMNine, R.id.ivSCAN, R.id.tvIsPressurePass, R.id.rlOne, R.id.rlTwo, R.id.rlThree,
            R.id.rlFour, R.id.tvCardType})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvAddDepartment://选择所属区域
                startDepartmentPermissionFragment(permissionsBean.getMenu_id(), "order_add");
                break;
            case R.id.tvCardType://质保卡类型
                showSingleAlertDialog(tvCardType, "质保卡类型", new String[]{ "电子质保卡", "纸质质保卡", "无质保卡"});
                break;
            case R.id.rlOne://单据展开收缩
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

            case R.id.ivSCAN:
                isHavePermission();
                break;
            case R.id.tvMFour:
                chooseTime(tvMFour, "试压开始时间");
                break;
            case R.id.tvHopeTIme:
                chooseDate(tvHopeTIme, "预约时间");
                break;
            case R.id.tvMFive:
                chooseTime(tvMFive, "试压完成时间");
                break;
            case R.id.tvQThree:
                showSingleAlertDialog(tvQThree, "地址分类", new String[]{"市区", "乡镇", "偏远乡镇"});
                break;
            case R.id.tvQFour:
                //chooseMore("服务类型", new String[]{"管路试压", "地暖验收"});
                showSingleAlertDialog(tvQFour, "服务类型", new String[]{"管路试压", "地暖验收"});
                break;
            case R.id.tvMSix:
                showSingleAlertDialog(tvMSix, "试压工具", new String[]{"手动试压泵", "电子试压仪", "智能试压泵"});
                break;

            case R.id.tvQSeven:
                showSingleAlertDialog(tvQSeven, "现场联系人身份", new String[]{"水工", "项目经理", "经销商", "业主"});
                break;

            case R.id.tvETwo:
                showSingleAlertDialog(tvETwo, "水管类型", new String[]{"白色热水管", "绿色热水管", "黄色热水管", "灰色普通管", "白色冷热水管", "灰色冷热水管", "白色冷水管", "绿色铜管",
                        "橘黄色", "柠檬黄PERT", "白色铝塑管", "HI-PPR复合管", "HI-PPR绿色热水管", "工程管", "绿色冷水管", "紫色PERT地暖管", "龙发定制管", "HI-PPR灰黄复合管",
                        "HI-PPR绿色复合管", "空调专用管", "欧标墨绿色管", "满堂红pe-rt", "Pe-rt二型阻氧", "Pe-rt精品黄", "柠檬黄地热管", "pe-rt阻氧一型地暖管",
                        "白色Pe-rt一型地暖管", "PPR白色复合管", "橙黄色PPR双色管（内白）", "HI-PPR墨绿色复合管", "PB采暖管", "外白内绿复合管", "灰黄色PPR给水管AG系列",
                        "白色PPR给水管AG系列", "绿色PPR给水管AG系列", "精品黄PPR给水管AG系列", "HI-PPR给水管AG系列", "灰黄色HI-PPR给水管AG系列", "绿色HI-PPR给水管AG系列",
                        "橙黄色PPR给水管AG系列", "橙黄色PPR给水管AG系列（宁波专供）", "紫色HI-PPR给水管AG系列", "墨绿色PPR给水管AG系列", "墨绿色HI-PPR给水管AG系列", "天蓝色PPR给水管AG系列", "其他"});
                break;
            case R.id.tvQTen:
                showSingleAlertDialog(tvQTen, "业主是否在场", new String[]{"是", "否"});
                break;

            case R.id.tvEOne:
                showSingleAlertDialog(tvEOne, "厨", new String[]{"无", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五"});
                break;
            case R.id.tvEOneAdd:
                showSingleAlertDialog(tvEOneAdd, "卫", new String[]{"无", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五"});
                break;
            case R.id.tvETwoAdd:
                showSingleAlertDialog(tvETwoAdd, "阳台", new String[]{"无", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五"});
                break;
            case R.id.tvEThree:
                showSingleAlertDialog(tvEThree, "试压类型", new String[]{"初次试压", "二次试压", "三次试压", "事故试压", "整改试压"});
                break;
            case R.id.tvEFour:
                showSingleAlertDialog(tvEFour, "管道走向", new String[]{"走顶", "走地", "走墙"});
                break;
            case R.id.tvEFive:
                showSingleAlertDialog(tvEFive, "安装方式", new String[]{"装饰公司", "水电工全包", "水电半包", "自装", "项目经理私单", "其他"});
                break;
            case R.id.tvEEight:
                showSingleAlertDialog(tvEEight, "门套安装情况", new String[]{"通用门套", "家装+保利门套", "装饰公司门套", "无门套"});
                break;
            case R.id.tvENine:
                showSingleAlertDialog(tvENine, "朴勒防水", new String[]{"是", "否"});
                break;
            case R.id.tvMOne:
                chooseMore(tvMOne, "异常情况", new String[]{"空跑", "现场暗敷回填", "混装", "现场检查漏水", "老阀回流", "焊接不合格"});
                break;
            case R.id.tvMNine:
                showSingleAlertDialog(tvMNine, "产品真伪", new String[]{"真", "假"});
                break;
            case R.id.tvIsPressurePass:
                 showSingleAlertDialog(tvIsPressurePass, "测试是否合格", new String[]{"是", "否"});
                break;
            case R.id.tvESeven:
                showSingleAlertDialog(tvESeven, "使用线管品牌", new String[]{"保利", "日丰", "联塑", "伟星", "金牛", "其他"});
                break;
            case R.id.tvMOneAdd:
                if ("2".equals(addType)) {
                    return;
                }
                startChooseWorkerFragment(permissionsBean);
                break;

            case R.id.ok:
                String mtvQOne = tvQOne.getText().toString();
                if (TextUtils.isEmpty(mtvQOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入试压地址");
                    return;
                }
                String mtvHopeTIme = tvHopeTIme.getText().toString();
                if (TextUtils.isEmpty(mtvHopeTIme)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择预约时间");
                    return;
                }
                String mtvQTwo = tvQTwo.getText().toString();
/*                if (TextUtils.isEmpty(mtvQTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "输入小区名称");
                    return;
                }*/
                String mtvQThree = tvQThree.getText().toString();
/*                if (TextUtils.isEmpty(mtvQThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择地址分类");
                    return;
                }*/
                String mtvQFour = tvQFour.getText().toString();
                if (TextUtils.isEmpty(mtvQFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择服务类型");
                    return;
                }
                String mtvQTen = tvQTen.getText().toString();
                if (!"0".equals(addType)) {//试压工编辑单据必须选择业主是否在场
                    if (TextUtils.isEmpty(mtvQTen)) {
                        ToastUtil.showToast(context.getApplicationContext(), "请选择业主是否在场");
                        return;
                    }
                }

                String mtvQFive = tvQFive.getText().toString();
/*                if (TextUtils.isEmpty(mtvQFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入现场联系人");
                    return;
                }*/
                String mtvQSix = tvQSix.getText().toString();
                if (!TextUtils.isEmpty(mtvQSix)) {
                    if (!isMobileNumber(mtvQSix)) {
                        ToastUtil.showToast(context.getApplicationContext(), "请输入正确的现场联系人电话");
                        return;
                    }
                }
                String mtvQNine = tvQNine.getText().toString();
                if (!TextUtils.isEmpty(mtvQNine)) {
                    if (!isMobileNumber(mtvQNine)) {
                        ToastUtil.showToast(context.getApplicationContext(), "请输入正确的业主电话");
                        return;
                    }
                }
                String mtvWThree = tvWThree.getText().toString();
                if (!TextUtils.isEmpty(mtvWThree)) {
                    if (!isMobileNumber(mtvWThree)) {
                        ToastUtil.showToast(context.getApplicationContext(), "请输入正确的装修公司电话");
                        return;
                    }
                }
                String mtvWFive = tvWFive.getText().toString();
                if (!TextUtils.isEmpty(mtvWFive)) {
                    if (!isMobileNumber(mtvWFive)) {
                        ToastUtil.showToast(context.getApplicationContext(), "请输入正确的项目经理电话");
                        return;
                    }
                }
                String mtvWNine = tvWNine.getText().toString();
                if (!TextUtils.isEmpty(mtvWNine)) {
                    if (!isMobileNumber(mtvWNine)) {
                        ToastUtil.showToast(context.getApplicationContext(), "请输入正确的水工电话");
                        return;
                    }
                }
                String mtvMTwoAdd = tvMTwoAdd.getText().toString();
                if (!TextUtils.isEmpty(mtvMTwoAdd)) {
                    if (!isMobileNumber(mtvMTwoAdd)) {
                        ToastUtil.showToast(context.getApplicationContext(), "请输入正确的试压工电话");
                        return;
                    }
                }
/*                if (TextUtils.isEmpty(mtvQSix)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入现场联系人电话");
                    return;
                }*/
                String mtvWTwo = tvWTwo.getText().toString();
/*                if (TextUtils.isEmpty(mtvWTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入装修公司名称");
                    return;
                }*/
                String mtvEThree = tvEThree.getText().toString();
                if (TextUtils.isEmpty(mtvEThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择试压类型");
                    return;
                }
                String mtvEFive = tvEFive.getText().toString();
                if (TextUtils.isEmpty(mtvEFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择安装方式");
                    return;
                }
                String mtvMOneAdd = tvMOneAdd.getText().toString();
                if (TextUtils.isEmpty(mtvMOneAdd)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择试压水工");
                    return;
                }


                if (TextUtils.isEmpty(worker_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择试压水工");
                    return;
                }


                String mtvMTen = tvMTen.getText().toString();//质保卡卡号

                ok.setEnabled(false);
                map.clear();
                map.put("staff_id", userBean.getStaff_id());

                map.put("is_weixin", "0");
                map.put("area_id", group_id);
                map.put("book_time", mtvHopeTIme);
                map.put("address", mtvQOne);
                map.put("house_area_name", mtvQTwo);
                map.put("address_type", mtvQThree);
                map.put("scene_contact", mtvQFive);
                map.put("scene_contact_tel", mtvQSix);
                map.put("install_type", mtvEFive);
                map.put("decoration_company", mtvWTwo);

                map.put("distributor_tel", tvWAddOne.getText().toString());//经销商电话

                map.put("house_area_no", tvQOneAdd.getText().toString().trim());
                map.put("declaration_type", tvQSeven.getText().toString().trim());
                map.put("owner_name", tvQEight.getText().toString().trim());
                map.put("owner_tel", mtvQNine);
                map.put("is_presence", tvQTen.getText().toString().trim());
                map.put("distributor_name", tvWOne.getText().toString().trim());
                map.put("decoration_company_tel", mtvWThree);
                map.put("project_manager", tvWFour.getText().toString().trim());
                map.put("project_manager_tel", mtvWFive);
                map.put("project_manager_score", tvWSix.getText().toString().trim());
                map.put("hydraulic_name", tvWEight.getText().toString().trim());
                map.put("hydraulic_tel", mtvWNine);
                map.put("hydraulic_score", tvWTen.getText().toString().trim());
                map.put("member_card", tvWTwelve.getText().toString().trim());
                map.put("kitchen", tvEOne.getText().toString().trim());
                map.put("toilet", tvEOneAdd.getText().toString().trim());
                map.put("balcony", tvETwoAdd.getText().toString().trim());
                map.put("pipe_type", tvETwo.getText().toString().trim());
                map.put("serve_type", mtvEThree);
                map.put("pipe_trend", tvEFour.getText().toString().trim());
                map.put("pipe_brand", tvESix.getText().toString().trim());
                map.put("spool_type", tvESeven.getText().toString().trim());
                map.put("door_packet", tvEEight.getText().toString().trim());
                map.put("plumber_name", tvMOneAdd.getText().toString().trim());
                map.put("plumber_tel", mtvMTwoAdd);
                map.put("special_event", tvMOne.getText().toString().trim());
                map.put("leakage_reason", tvMTwo.getText().toString().trim());
                map.put("description", tvMThree.getText().toString().trim());
                map.put("pressure_time", tvMFour.getText().toString().trim());
                map.put("pressure_complete_time", tvMFive.getText().toString().trim());
                map.put("pressure_tool", tvMSix.getText().toString().trim());
                map.put("pressure_pressure", tvMSeven.getText().toString().trim());
                map.put("pressure_drop", tvMEight.getText().toString().trim());
                map.put("authentic", tvMNine.getText().toString().trim());
                map.put("is_care_water", tvENine.getText().toString().trim());
                map.put("is_qualified", tvIsPressurePass.getText().toString().trim());
                map.put("tds", tvTDS.getText().toString().trim());
                map.put("water_type", tvWaterType.getText().toString().trim());

                map.put("last_pressure", tvOverKg.getText().toString().trim());
                map.put("press_time", tvAddTime.getText().toString().trim());

                map.put("pressure_type", mtvQFour);
                map.put("plumber_id", worker_id);

                map.put("assurance_card", tvCardType.getTag().toString());//质保卡类型
                map.put("quality_card", mtvMTen);//质保卡卡号

                switch (addType) {
                    case "0"://非试压工新增单据
                        map.put("is_select", "0");
                        map.put("is_app", "1");
                        map.put("module_id", permissionsBean.getMenu_id());
                        map.put("operation", "0");
                        getPresenter().insertOrUpdateTestPressure(userBean.getStaff_token(), map);
                        break;
                    case "1"://非试压工编辑单据
                        map.put("is_select", "0");
                        map.put("is_app", "1");
                        map.put("module_id", permissionsBean.getMenu_id());
                        map.put("operation", "1");
                        map.put("doc_no", bean.getDoc_no());
                        getPresenter().insertOrUpdateTestPressure(userBean.getStaff_token(), map);
                        break;
                    case "2"://试压工编辑单据
                        map.put("is_app", "1");
                        map.put("doc_no", bean.getDoc_no());
                        getPresenter().insertOrUpdateAppTestPressure(userBean.getStaff_token(), map);
                        break;
                }


                break;
        }
    }

    //正则限制手机号
    public static boolean isMobileNumber(String mobiles) {
        String telRegex = "\\d{7,8}||\\d{11}";
        return !TextUtils.isEmpty(mobiles) && mobiles.matches(telRegex);
    }


    //获取拍照读写权限
    public void isHavePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CARERAM);
                return;
            } else {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        } else {
            Intent intent = new Intent(getActivity(), CaptureActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    startWeb("真伪查询结果", result, "http://120.79.29.57/AkanWeb/QueryEnter.aspx?code=" + result + "&dis=&cap=");
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtil.showToast(context.getApplicationContext(), "解析二维码失败");
                }
            }
        }
    }

    private void showSingleAlertDialog(final TextView textView, String tittle, final String[] items) {
        DialogUtil.showSingleChoiceDialog(textView, tittle, items, (dialog, which) -> {
            String item = items[which];
            switch (item) {
                case "纸质质保卡":
                    tvCardType.setTag("1");
                    llCard.setVisibility(View.VISIBLE);
                    tvMTen.setEnabled(true);
                    break;
                case "电子质保卡":
                    tvCardType.setTag("0");
                    llCard.setVisibility(View.GONE);
                    tvMTen.setText("");
                    break;
                case "无质保卡":
                    tvCardType.setTag("2");
                    llCard.setVisibility(View.GONE);
                    tvMTen.setText("");
                    break;

                case "手动试压泵":
                    if (!"手动试压泵".equals(tvMSix.getText())){
                        clearPressureInfoText();
                        setPressureInfoEnable(true);
                    }
                    break;
                case "电子试压仪":
                case "智能试压泵":
                    if ("手动试压泵".equals(tvMSix.getText())){
                        clearPressureInfoText();
                        setPressureInfoEnable(false);
                    }
                    break;
            }
            textView.setText(item);
        });
    }

    //多选
    private void chooseMore(TextView textView, String tittle, String[] items) {
        DialogUtil.showMultiChoiceDialog(textView, tittle, items);
    }

    private void chooseDate(final TextView textView, String title) {
        DialogUtil.showTimePickerDialog(textView, title);
    }

    private void chooseTime(final TextView textView, String title) {
        DialogUtil.showTimePickerDialog(textView, title, DateUtil.TIME_FORMAT,
                (date, v) -> textView.setText(new SimpleDateFormat(DateUtil.TIME_FORMAT, Locale.CHINA).format(date))
        );
    }

    @Override
    public OrderPresenter createPresenter() {
        return new OrderPresenter(getApp());
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ok.setEnabled(true);
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());

    }

    @Override
    public void onInsertOrUpdateTestPressure(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void OnGetAppTestPressureList(List<PressurePageBean> data,String total) {

    }

    @Override
    public void onInsertOrUpdateAppTestPressure(String data) {
        EventBus.getDefault().post(new FirstEvent("refresh"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }


    @Override
    public void OnGetTestPressureList(List<PressurePageBean> data,String total) {

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
    public void onEventMainThread(FirstEventWorker bean) {
        String msg = bean.getMsg();
        switch (msg) {
            case "1":
                WorkerBean worbean = bean.getFilterBean();
                tvMOneAdd.setText(worbean.getStaff_name());
                tvMTwoAdd.setText(worbean.getPhone());
                worker_id = worbean.getStaff_id();
                break;
            case "2":
                WorkerBean workerbean = bean.getFilterBean();
                switch (workerbean.getStaff_id()) {
                    case "正确":
                        tvMNine.setText("真");
                        break;
                    case "错误":
                        tvMNine.setText("假");
                        break;
                }
                break;
            case "orderAdd":
                DepartmentBean departmentBean = bean.getmDepartmentBean();
                tvAddDepartment.setText(departmentBean.getName());
                group_id = departmentBean.getUuid();

                break;
        }

    }

    //初始化动画
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

    private class OnMemoryTextWatch implements TextWatcher{

        private int type;

        OnMemoryTextWatch(int type){
            this.type = type;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            getMemory(String.valueOf(type), s.toString());
        }
    }

}
