package com.akan.qf.mvp.fragment.bapproval;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.CkeckBean;
import com.akan.qf.bean.FirstEventNew;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.bean.VisitorBean;
import com.akan.qf.mvp.adapter.message.OneAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.VisitPresenter;
import com.akan.qf.mvp.view.home.IVisitView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.DialogLoadding;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

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
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/11/15.
 */

public class VisitAddFragment extends BaseFragment<IVisitView, VisitPresenter> implements IVisitView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvDepartment)
    EditText tvDepartment;
    @BindView(R.id.tvOne)
    EditText tvOne;
    @BindView(R.id.tvTwo)
    EditText tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    EditText tvFour;
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.tvSix)
    EditText tvSix;
    @BindView(R.id.oneRecycleView)
    RecyclerView oneRecycleView;
    @BindView(R.id.tvSeven)
    EditText tvSeven;
    @BindView(R.id.tvEight)
    TextView tvEight;
    @BindView(R.id.twoRecycleView)
    RecyclerView twoRecycleView;
    @BindView(R.id.threeRecycleView)
    RecyclerView threeRecycleView;
    @BindView(R.id.tvNine)
    EditText tvNine;
    @BindView(R.id.fourRecycleView)
    RecyclerView fourRecycleView;
    @BindView(R.id.fiveRecycleView)
    RecyclerView fiveRecycleView;
    @BindView(R.id.tvTen)
    EditText tvTen;
    @BindView(R.id.tvEleven)
    TextView tvEleven;
    @BindView(R.id.tvTwelve)
    EditText tvTwelve;
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    @BindView(R.id.circleImageVIew)
    CircleImageView circleImageVIew;
    @BindView(R.id.ivCheckDelete)
    ImageView ivCheckDelete;
    @BindView(R.id.tvCheckPersonName)
    TextView tvCheckPersonName;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;
    private OneAdapter OneAdapter;
    private List<CkeckBean> oneList;
    private OneAdapter twoAdapter;
    private List<CkeckBean> twoList;
    private OneAdapter threeAdapter;
    private List<CkeckBean> threeList;
    private OneAdapter fourAdapter;
    private List<CkeckBean> fourList;
    private OneAdapter fiveAdapter;
    private List<CkeckBean> fiveList;

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String ask_id = "";
    private String type;
    private VisitorBean data;
    private DialogLoadding dialogLoadding;
    private PermissionsBean permissionsBean;

    public static VisitAddFragment newInstance(VisitorBean bean, String type, PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        VisitAddFragment fragment = new VisitAddFragment();
        fragment.data = bean;
        fragment.type = type;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_visit;
    }

    @Override
    public void initUI() {


        if ("0".equals(type)) {
            getData();
            ok.setText("提交");
            tvTitle.setText("新增客户来访接待申请");
            oneList = new ArrayList<>();
            oneList.add(new CkeckBean("一楼展厅", false));
            oneList.add(new CkeckBean("生产车间", false));
            oneList.add(new CkeckBean("实验室", false));
            oneList.add(new CkeckBean("仓库", false));
            oneRecycleView.setNestedScrollingEnabled(false);
            oneRecycleView.setLayoutManager(new LinearLayoutManager(context));
            OneAdapter = new OneAdapter(context, oneList, true);
            oneRecycleView.setAdapter(OneAdapter);

            twoList = new ArrayList<>();
            twoList.add(new CkeckBean("6楼会议室一（小）", false));
            twoList.add(new CkeckBean("6楼会议室二（小）", false));
            twoList.add(new CkeckBean("6楼会议室三（小）", false));
            twoList.add(new CkeckBean("6楼圆桌会议室", false));
            twoList.add(new CkeckBean("实验楼会议室", false));
            twoList.add(new CkeckBean("6楼大会议室", false));
            twoRecycleView.setNestedScrollingEnabled(false);
            twoRecycleView.setLayoutManager(new LinearLayoutManager(context));
            twoAdapter = new OneAdapter(context, twoList, true);
            twoRecycleView.setAdapter(twoAdapter);

            threeList = new ArrayList<>();
            threeList.add(new CkeckBean("笔记本", false));
            threeList.add(new CkeckBean("投影仪", false));
            threeList.add(new CkeckBean("话筒", false));
            threeList.add(new CkeckBean("白板、白板笔、白报纸", false));
            threeList.add(new CkeckBean("茶水", false));
            threeList.add(new CkeckBean("矿泉水", false));
            threeList.add(new CkeckBean("水果", false));
            threeList.add(new CkeckBean("茶歇", false));
            threeList.add(new CkeckBean("宣传册", false));
            threeRecycleView.setNestedScrollingEnabled(false);
            threeRecycleView.setLayoutManager(new LinearLayoutManager(context));
            threeAdapter = new OneAdapter(context, threeList, true);
            threeRecycleView.setAdapter(threeAdapter);

            fourList = new ArrayList<>();
            fourList.add(new CkeckBean("公司工作餐", false));
            fourList.add(new CkeckBean("外部安排就餐", false));
            fourList.add(new CkeckBean("自行安排", false));
            fourRecycleView.setNestedScrollingEnabled(false);
            fourRecycleView.setLayoutManager(new LinearLayoutManager(context));
            fourAdapter = new OneAdapter(context, fourList, false);
            fourRecycleView.setAdapter(fourAdapter);
            fourAdapter.setOnDeleteClick(new OneAdapter.OnDeleteClick() {
                @Override
                public void onDeleteClick(int position, boolean isCheck) {
                    List<CkeckBean> allData = fourAdapter.getAllData();
                    for (int i = 0; i < allData.size(); i++) {
                        fourAdapter.getItem(i).setCkeck(false);
                    }
                    fourAdapter.getItem(position).setCkeck(!isCheck);
                    fourAdapter.notifyDataSetChanged();
                }
            });

            fiveList = new ArrayList<>();
            fiveList.add(new CkeckBean("康桥诺富特酒店600元含早", false));
            fiveList.add(new CkeckBean("坦直维纳斯酒店250元含早", false));
            fiveList.add(new CkeckBean("坦直如家酒店150元含早", false));
            fiveList.add(new CkeckBean("坦直城市便捷酒店120元含早", false));
            fiveList.add(new CkeckBean("坦直馨豪宾馆100元不含早", false));
            fiveList.add(new CkeckBean("公司不安排住宿", false));
            fiveRecycleView.setNestedScrollingEnabled(false);
            fiveRecycleView.setLayoutManager(new LinearLayoutManager(context));
            fiveAdapter = new OneAdapter(context, fiveList, false);
            fiveRecycleView.setAdapter(fiveAdapter);
            fiveAdapter.setOnDeleteClick(new OneAdapter.OnDeleteClick() {
                @Override
                public void onDeleteClick(int position, boolean isCheck) {
                    List<CkeckBean> allData = fiveAdapter.getAllData();
                    for (int i = 0; i < allData.size(); i++) {
                        fiveAdapter.getItem(i).setCkeck(false);
                    }
                    fiveAdapter.getItem(position).setCkeck(!isCheck);
                    fiveAdapter.notifyDataSetChanged();
                }
            });

        } else {
            tvTitle.setText("修改客户来访接待申请");
            ok.setText("确认修改");
            tvOne.setText(data.getApply_name());
            tvTwo.setText(data.getApply_number());
            tvThree.setText(data.getVisit_time());
            tvFour.setText(data.getApply_result());
            tvFive.setText(data.getApply_led());
            tvSix.setText(data.getApply_text());
            tvSeven.setText(data.getApply_need());
            tvEight.setText(data.getApply_car());
            tvNine.setText(data.getApply_other());
            tvTen.setText(data.getApply_sign());
            tvEleven.setText(data.getApply_time());
            tvTwelve.setText(data.getApply_remark());


            oneList = new ArrayList<>();
            String area = data.getApply_area();
            if (area.contains("一楼展厅")) {
                oneList.add(new CkeckBean("一楼展厅", true));
            } else {
                oneList.add(new CkeckBean("一楼展厅", false));
            }
            if (area.contains("生产车间")) {
                oneList.add(new CkeckBean("生产车间", true));
            } else {
                oneList.add(new CkeckBean("生产车间", false));
            }
            if (area.contains("实验室")) {
                oneList.add(new CkeckBean("实验室", true));
            } else {
                oneList.add(new CkeckBean("实验室", false));
            }
            if (area.contains("仓库")) {
                oneList.add(new CkeckBean("仓库", true));
            } else {
                oneList.add(new CkeckBean("仓库", false));
            }


            oneRecycleView.setNestedScrollingEnabled(false);
            oneRecycleView.setLayoutManager(new LinearLayoutManager(context));
            OneAdapter = new OneAdapter(context, oneList, true);
            oneRecycleView.setAdapter(OneAdapter);

            twoList = new ArrayList<>();
            String home = data.getApply_home();
            if (home.contains("6楼会议室一（小）")) {
                twoList.add(new CkeckBean("6楼会议室一（小）", true));
            } else {
                twoList.add(new CkeckBean("6楼会议室一（小）", false));
            }
            if (home.contains("6楼会议室二（小）")) {
                twoList.add(new CkeckBean("6楼会议室二（小）", true));
            } else {
                twoList.add(new CkeckBean("6楼会议室二（小）", false));
            }
            if (home.contains("6楼会议室三（小）")) {
                twoList.add(new CkeckBean("6楼会议室三（小）", true));
            } else {
                twoList.add(new CkeckBean("6楼会议室三（小）", false));
            }
            if (home.contains("6楼圆桌会议室")) {
                twoList.add(new CkeckBean("6楼圆桌会议室", true));
            } else {
                twoList.add(new CkeckBean("6楼圆桌会议室", false));
            }
            if (home.contains("实验楼会议室")) {
                twoList.add(new CkeckBean("实验楼会议室", true));
            } else {
                twoList.add(new CkeckBean("实验楼会议室", false));
            }
            if (home.contains("6楼大会议室")) {
                twoList.add(new CkeckBean("6楼大会议室", true));
            } else {
                twoList.add(new CkeckBean("6楼大会议室", false));
            }
            twoRecycleView.setNestedScrollingEnabled(false);
            twoRecycleView.setLayoutManager(new LinearLayoutManager(context));
            twoAdapter = new OneAdapter(context, twoList, true);
            twoRecycleView.setAdapter(twoAdapter);

            threeList = new ArrayList<>();
            String goods = data.getApply_goods();
            if (goods.contains("笔记本")) {
                threeList.add(new CkeckBean("笔记本", true));
            } else {
                threeList.add(new CkeckBean("笔记本", false));
            }
            if (goods.contains("投影仪")) {
                threeList.add(new CkeckBean("投影仪", true));
            } else {
                threeList.add(new CkeckBean("投影仪", false));
            }
            if (goods.contains("话筒")) {
                threeList.add(new CkeckBean("话筒", true));
            } else {
                threeList.add(new CkeckBean("话筒", false));
            }
            if (goods.contains("白板、白板笔、白报纸")) {
                threeList.add(new CkeckBean("白板、白板笔、白报纸", true));
            } else {
                threeList.add(new CkeckBean("白板、白板笔、白报纸", false));
            }
            if (goods.contains("茶水")) {
                threeList.add(new CkeckBean("茶水", true));
            } else {
                threeList.add(new CkeckBean("茶水", false));
            }
            if (goods.contains("矿泉水")) {
                threeList.add(new CkeckBean("矿泉水", true));
            } else {
                threeList.add(new CkeckBean("矿泉水", false));
            }
            if (goods.contains("水果")) {
                threeList.add(new CkeckBean("水果", true));
            } else {
                threeList.add(new CkeckBean("水果", false));
            }
            if (goods.contains("茶歇")) {
                threeList.add(new CkeckBean("茶歇", true));
            } else {
                threeList.add(new CkeckBean("茶歇", false));
            }
            if (goods.contains("宣传册")) {
                threeList.add(new CkeckBean("宣传册", true));
            } else {
                threeList.add(new CkeckBean("宣传册", false));
            }
            threeRecycleView.setNestedScrollingEnabled(false);
            threeRecycleView.setLayoutManager(new LinearLayoutManager(context));
            threeAdapter = new OneAdapter(context, threeList, true);
            threeRecycleView.setAdapter(threeAdapter);

            fourList = new ArrayList<>();
            String food = data.getApply_food();
            if (food.contains("公司工作餐")) {
                fourList.add(new CkeckBean("公司工作餐", true));
            } else {
                fourList.add(new CkeckBean("公司工作餐", false));
            }
            if (food.contains("外部安排就餐")) {
                fourList.add(new CkeckBean("外部安排就餐", true));
            } else {
                fourList.add(new CkeckBean("外部安排就餐", false));
            }
            if (food.contains("自行安排")) {
                fourList.add(new CkeckBean("自行安排", true));
            } else {
                fourList.add(new CkeckBean("自行安排", false));
            }

            fourRecycleView.setNestedScrollingEnabled(false);
            fourRecycleView.setLayoutManager(new LinearLayoutManager(context));
            fourAdapter = new OneAdapter(context, fourList, false);
            fourRecycleView.setAdapter(fourAdapter);
            fourAdapter.setOnDeleteClick(new OneAdapter.OnDeleteClick() {
                @Override
                public void onDeleteClick(int position, boolean isCheck) {
                    List<CkeckBean> allData = fourAdapter.getAllData();
                    for (int i = 0; i < allData.size(); i++) {
                        fourAdapter.getItem(i).setCkeck(false);
                    }
                    fourAdapter.getItem(position).setCkeck(!isCheck);
                    fourAdapter.notifyDataSetChanged();
                }
            });

            fiveList = new ArrayList<>();
            String room = data.getApply_room();
            if (room.contains("康桥诺富特酒店600元含早")) {
                fiveList.add(new CkeckBean("康桥诺富特酒店600元含早", true));
            } else {
                fiveList.add(new CkeckBean("康桥诺富特酒店600元含早", false));
            }
            if (room.contains("坦直维纳斯酒店250元含早")) {
                fiveList.add(new CkeckBean("坦直维纳斯酒店250元含早", true));
            } else {
                fiveList.add(new CkeckBean("坦直维纳斯酒店250元含早", false));
            }
            if (room.contains("坦直如家酒店150元含早")) {
                fiveList.add(new CkeckBean("坦直如家酒店150元含早", true));
            } else {
                fiveList.add(new CkeckBean("坦直如家酒店150元含早", false));
            }
            if (room.contains("坦直城市便捷酒店120元含早")) {
                fiveList.add(new CkeckBean("坦直城市便捷酒店120元含早", true));
            } else {
                fiveList.add(new CkeckBean("坦直城市便捷酒店120元含早", false));
            }
            if (room.contains("坦直馨豪宾馆100元不含早")) {
                fiveList.add(new CkeckBean("坦直馨豪宾馆100元不含早", true));
            } else {
                fiveList.add(new CkeckBean("坦直馨豪宾馆100元不含早", false));
            }
            if (room.contains("公司不安排住宿")) {
                fiveList.add(new CkeckBean("公司不安排住宿", true));
            } else {
                fiveList.add(new CkeckBean("公司不安排住宿", false));
            }
            fiveRecycleView.setNestedScrollingEnabled(false);
            fiveRecycleView.setLayoutManager(new LinearLayoutManager(context));
            fiveAdapter = new OneAdapter(context, fiveList, false);
            fiveRecycleView.setAdapter(fiveAdapter);
            fiveAdapter.setOnDeleteClick(new OneAdapter.OnDeleteClick() {
                @Override
                public void onDeleteClick(int position, boolean isCheck) {
                    List<CkeckBean> allData = fiveAdapter.getAllData();
                    for (int i = 0; i < allData.size(); i++) {
                        fiveAdapter.getItem(i).setCkeck(false);
                    }
                    fiveAdapter.getItem(position).setCkeck(!isCheck);
                    fiveAdapter.notifyDataSetChanged();
                }
            });
        }

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        tvName.setText(userBean.getStaff_name());
        tvDepartment.setText(userBean.getSimple_department_name());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTime.setText(str);
        tvEleven.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));

        if ("0".equals(type)) {
            tvDepartment.setText(userBean.getSimple_department_name());
            if (!TextUtils.isEmpty(userBean.getParent_id())) {
                ask_id = userBean.getParent_id();
                Glide.with(context)
                        .load(Constants.BASE_URL + userBean.getParent_head_img())
                        .error(R.drawable.error_img)
                        .into(circleImageVIew);
                tvCheckPersonName.setText(userBean.getParent_staff_name());
            }
            map.clear();
            map.put("group_id", userBean.getDepartment_id());
            map.put("staff_id", userBean.getStaff_id());
            getPresenter().getCheckPerson(userBean.getStaff_token(), map);
        } else {
            ask_id = data.getNext_staff_id();
            Glide.with(context)
                    .load(Constants.BASE_URL + data.getNext_audit_staff_head_img())
                    .error(R.drawable.error_img)
                    .into(circleImageVIew);
            tvCheckPersonName.setText(data.getNext_audit_staff_name());
        }
    }

    @Override
    public void OnInsertVisitorApply(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvDepartment.setText("");
        tvOne.setText("");
        tvTwo.setText("");
        tvThree.setText("");
        tvFour.setText("");
        tvFive.setText("");
        tvSix.setText("");
        tvSeven.setText("");
        tvEight.setText("");
        tvNine.setText("");
        tvTen.setText("");
        tvEleven.setText("");
        tvTwelve.setText("");
        finish();
    }

    @OnClick({R.id.ivLeft, R.id.tvThree, R.id.tvTime, R.id.tvEleven, R.id.tvEight, R.id.tvFive,
            R.id.ok, R.id.circleImageVIew, R.id.ivCheckDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvThree:
                chooseTime("来访时间");
                break;
            case R.id.tvEleven:
                chooseTime("日期");
                break;
            case R.id.tvFive:
                showSingleAlertDialog("LED显示屏", new String[]{"使用", "不使用"});
                break;
            case R.id.tvEight:
                showSingleAlertDialog("车辆使用", new String[]{"是", "否"});
                break;
            case R.id.circleImageVIew:
                startChooseCheckPersonFragment("1");
                break;
            case R.id.ivCheckDelete:
                ivCheckDelete.setVisibility(View.GONE);
                tvCheckPersonName.setText("请选择");
                ask_id = "";
                Glide.with(context).load("").error(R.drawable.check_img).into(circleImageVIew);
                break;
            case R.id.ok:
                String mtvDepartment = tvDepartment.getText().toString().trim();
                if (TextUtils.isEmpty(mtvDepartment)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入部门");
                    return;
                }
                String mtvOne = tvOne.getText().toString().trim();
                if (TextUtils.isEmpty(mtvOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入客户名称");
                    return;
                }
                String mtvTwo = tvTwo.getText().toString().trim();
                if (TextUtils.isEmpty(mtvTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入来访人数");
                    return;
                }
                String mtvThree = tvThree.getText().toString().trim();
                if (TextUtils.isEmpty(mtvThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择来访时间");
                    return;
                }
                String mtvFour = tvFour.getText().toString().trim();
                if (TextUtils.isEmpty(mtvFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入来访事由");
                    return;
                }
                if (TextUtils.isEmpty(ask_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择审批人");
                    return;
                }
                ok.setEnabled(false);
                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("apply_department", mtvDepartment);
                map.put("apply_name", mtvOne);
                map.put("apply_number", mtvTwo);
                map.put("visit_time", mtvThree);
                map.put("apply_result", mtvFour);

                map.put("apply_led", tvFive.getText().toString());
                map.put("apply_text", tvSix.getText().toString());
                String oneCheck = "";
                for (CkeckBean one : OneAdapter.getAllData()) {
                    if (one.isCkeck()) {
                        oneCheck = oneCheck + one.getName() + ",";
                    }
                }
                map.put("apply_area", oneCheck);
                map.put("apply_need", tvSeven.getText().toString());


                map.put("apply_car", tvEight.getText().toString()
                );
                map.put("apply_text", tvSix.getText().toString());
                String twoCheck = "";
                for (CkeckBean two : twoAdapter.getAllData()) {
                    if (two.isCkeck()) {
                        twoCheck = twoCheck + two.getName() + ",";
                    }
                }
                map.put("apply_home", twoCheck);
                String threeCheck = "";
                for (CkeckBean one : threeAdapter.getAllData()) {
                    if (one.isCkeck()) {
                        threeCheck = threeCheck + one.getName() + ",";
                    }
                }
                map.put("apply_goods", threeCheck);
                map.put("apply_other", tvNine.getText().toString());
                for (CkeckBean one : fourAdapter.getAllData()) {
                    if (one.isCkeck()) {
                        map.put("apply_food", one.getName());
                    }
                }
                for (CkeckBean one : fiveAdapter.getAllData()) {
                    if (one.isCkeck()) {
                        map.put("apply_room", one.getName());
                    }
                }
                map.put("apply_sign", tvTen.getText().toString());
                map.put("apply_remark", tvTwelve.getText().toString());
                map.put("apply_time", tvEleven.getText().toString());
                map.put("next_staff_id", ask_id);//负责人评定
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());

                if ("1".equals(type)) {
                    map.put("apply_id", data.getApply_id());
                    map.put("operation", "1");
                    getPresenter().updateVisitorApply(userBean.getStaff_token(), map);
                } else {
                    map.put("operation", "0");
                    getPresenter().insertVisitorApply(userBean.getStaff_token(), map);
                }

                break;
        }
    }

    private AlertDialog alertDialog2;
    private int choose = 0;

    public void showSingleAlertDialog(final String tittle, final String[] items) {
        choose = 0;
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle(tittle);
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose = i;
            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (tittle) {
                    case "LED显示屏":
                        tvFive.setText(items[choose]);
                        break;
                    case "车辆使用":
                        tvEight.setText(items[choose]);
                        break;
                }

                alertDialog2.dismiss();
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog2.dismiss();
            }
        });
        alertDialog2 = alertBuilder.create();
        alertDialog2.show();
    }

    private void chooseTime(final String type) {

        TimePickerView build = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String format = formatter.format(date);
                switch (type) {
                    case "来访时间":
                        tvThree.setText(format);
                        break;
                    case "日期":
                        tvEleven.setText(format);
                        break;
                }

            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setCancelText("取消")
                .setSubmitText("确认")
                .setTitleSize(15)
                .setSubCalSize(14)
                .setTitleText(type)
                .setOutSideCancelable(false)
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)
                .setTitleBgColor(0xFF4DA9EB)
                .setBgColor(0xFFFFFFFF)
                //.setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                //.setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .isDialog(true)//是否显示为对话框样式
                .build();
        build.show();

    }


    @Override
    public VisitPresenter createPresenter() {
        return new VisitPresenter(getApp());
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
        saveData();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    private void saveData() {
        VisitorBean bean = new VisitorBean();
        bean.setApply_department(tvDepartment.getText().toString());
        bean.setApply_name(tvOne.getText().toString());
        bean.setApply_number(tvTwo.getText().toString());
        bean.setVisit_time(tvThree.getText().toString());
        bean.setApply_result(tvFour.getText().toString());
        bean.setApply_led(tvFive.getText().toString());
        bean.setApply_text(tvSix.getText().toString());
        String oneCheck = "";
        for (CkeckBean one : OneAdapter.getAllData()) {
            if (one.isCkeck()) {
                oneCheck = oneCheck + one.getName() + ",";
            }
        }
        bean.setApply_area(oneCheck);


        bean.setApply_need(tvSeven.getText().toString());
        bean.setApply_car(tvEight.getText().toString());

        String twoCheck = "";
        for (CkeckBean two : twoAdapter.getAllData()) {
            if (two.isCkeck()) {
                twoCheck = twoCheck + two.getName() + ",";
            }
        }
        bean.setApply_home(twoCheck);


        String threeCheck = "";
        for (CkeckBean one : threeAdapter.getAllData()) {
            if (one.isCkeck()) {
                threeCheck = threeCheck + one.getName() + ",";
            }
        }
        bean.setApply_goods(threeCheck);
        bean.setApply_other(tvNine.getText().toString());
        for (CkeckBean one : fourAdapter.getAllData()) {
            if (one.isCkeck()) {
                bean.setApply_food(one.getName());
            }
        }
        for (CkeckBean one : fiveAdapter.getAllData()) {
            if (one.isCkeck()) {
                bean.setApply_room(one.getName());
            }
        }
        bean.setApply_sign(tvTen.getText().toString());
        bean.setApply_time(tvEleven.getText().toString());
        bean.setApply_remark(tvTwelve.getText().toString());
        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("visit", s);
        editor.commit();
    }

    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("visit", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                tvDepartment.setText(object.getString("apply_department"));
                tvOne.setText(object.getString("apply_name"));
                tvTwo.setText(object.getString("apply_number"));
                tvThree.setText(object.getString("visit_time"));
                tvFour.setText(object.getString("apply_result"));
                tvFive.setText(object.getString("apply_led"));
                tvSix.setText(object.getString("apply_text"));
                tvSeven.setText(object.getString("apply_need"));
                tvEight.setText(object.getString("apply_car"));
                tvNine.setText(object.getString("apply_other"));
                tvTen.setText(object.getString("apply_sign"));
                tvEleven.setText(object.getString("apply_time"));
                tvTwelve.setText(object.getString("apply_remark"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventNew event) {
        switch (event.getMsg()) {
            case "1":
                MeParentBean bean = event.getmBean();
                Glide.with(context)
                        .load(Constants.BASE_URL + bean.getHead_img())
                        .error(R.drawable.error_img)
                        .into(circleImageVIew);
                tvCheckPersonName.setText(bean.getStaff_name());
                ivCheckDelete.setVisibility(View.VISIBLE);
                ask_id = bean.getStaff_id();
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
    public void OnGetVisitorApplyList(List<VisitorBean> data, String total) {

    }

    @Override
    public void OnGetVisitorApply(VisitorBean data) {

    }

    @Override
    public void OnAuditVisitorApply(String data) {

    }

    @Override
    public void OnupdateVisitorApply(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void OnDeleteVisitorApply(String data) {

    }

    @Override
    public void onGetCheckPerson(List<MeParentBean> data) {
        for (int i = 0; i < data.size(); i++) {
            String module = data.get(i).getStaff_module();
            if (module.contains("VisitorApply")) {
                String staff_give = data.get(i).getStaff_give();
                if ("1".equals(staff_give)) {
                    MeParentBean checkBean = data.get(i);
                    ask_id = checkBean.getStaff_id();
                    Glide.with(context)
                            .load(Constants.BASE_URL + checkBean.getHead_img())
                            .error(R.drawable.error_img)
                            .into(circleImageVIew);
                    tvCheckPersonName.setText(checkBean.getStaff_name());
                }

            }
        }
    }
}
