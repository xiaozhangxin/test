package com.ak.pt.mvp.fragment.ble;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.FirstEventDrop;
import com.ak.pt.bean.PressureDropBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.NumAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.PtSettingPresenter;
import com.ak.pt.mvp.view.IPtSettingView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2019/1/18.
 */

public class PtSettingFragment extends BaseFragment<IPtSettingView, PtSettingPresenter> implements IPtSettingView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    Unbinder unbinder;
    @BindView(R.id.ivRightTwo)
    ImageView ivRightTwo;
    @BindView(R.id.ptType)
    TextView ptType;
    @BindView(R.id.tvChangge)
    TextView tvChangge;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private NumAdapter numAdapter;
    private List<PressureDropBean> list;
    private int mPosition = 0;
    private String mMsg = "";
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    List<PressureDropBean> localList = new ArrayList<>();//本地参数


    public static PtSettingFragment newInstance() {
        Bundle args = new Bundle();
        PtSettingFragment fragment = new PtSettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_pt_setting;
    }

    @Override
    public void initUI() {
        SharedPreferences sp = context.getSharedPreferences("ptDropType", MODE_PRIVATE);
        String type = sp.getString("type", "");
        switch (type) {
            case "back":
                ptType.setText("当前使用后台参数");
                break;
            case "local":
                ptType.setText("当前使用本地参数");
                break;

        }
        tvTitle.setText("设置");
        list = new ArrayList<>();
        initLocalList();
        if (localList.size() > 0) {
            list.addAll(localList);
        }

        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        numAdapter = new NumAdapter(context, list, "2");
        recycleView.setAdapter(numAdapter);
        numAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<PressureDropBean> allData = numAdapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    if (position == i) {
                        allData.get(i).setCheck(true);
                    } else {
                        allData.get(i).setCheck(false);
                    }
                }
                numAdapter.notifyDataSetChanged();
                mPosition = position;
                startPTSettingChildFragment(allData.get(position));
            }
        });


    }



    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getPresenter().getPressureDropList(userBean.getStaff_token(), map);
    }

    //获取本地参数
    private void initLocalList() {
        SharedPreferences sp = context.getSharedPreferences("ptDrop", MODE_PRIVATE);
        String list = sp.getString("dropList", "");
        if (!list.equals("") && list.length() > 0) {
            localList.clear();
            Gson gson = new Gson();
            JsonArray array = new JsonParser().parse(list).getAsJsonArray();
            for (JsonElement elem : array) {
                PressureDropBean dropBean = gson.fromJson(elem, PressureDropBean.class);
                localList.add(dropBean);
            }
        }

    }

    @OnClick({R.id.ivLeft, R.id.tv_save, R.id.tvChangge})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvChangge:
                if ("当前使用后台参数".equals(ptType.getText().toString())) {

                    if (localList.size()<=0){
                        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                        builder.setMessage("请先设置本地参数");
                        builder.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.onCreate().show();
                    }else {
                        SharedPreferences sp = context.getSharedPreferences("ptDropType", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("type", "local");
                        editor.commit();
                        ptType.setText("当前使用本地参数");


                        showPop();

                    }
                } else {

                    SharedPreferences sp = context.getSharedPreferences("ptDropType", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("type", "back");
                    editor.commit();
                    ptType.setText("当前使用后台参数");
                    showPop();
                }
                break;
            case R.id.tv_save:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                final View inflate1 = LayoutInflater.from(context).inflate(R.layout.dialog_edittext_three, null);
                final EditText edit1 = (EditText) inflate1.findViewById(R.id.editText);//获得输入框对象
                builder2.setView(inflate1);
                builder2.setNegativeButton("取消", null);
                builder2.setPositiveButton("确定", null);
                final AlertDialog alertDialog1 = builder2.create();
                //dialog点击其他地方不关闭
                alertDialog1.setCancelable(false);

                alertDialog1.show();
                alertDialog1.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //如果想关闭dialog直接加上下面这句代码就行
                        //
                        String s = edit1.getText().toString();
                        if (TextUtils.isEmpty(s)) {
                            ToastUtil.showToast(context.getApplicationContext(), "请输入密码");
                            return;
                        }
                        map.clear();
                        map.put("instrument_pass", s);
                        getPresenter().verificationPass(userBean.getStaff_token(), map);

                        alertDialog1.cancel();
                    }
                });


                break;
        }
    }

    private void showPop() {
        final AlertDialog alert = new AlertDialog.Builder(context).create();
        alert.setTitle("提示");
        alert.setMessage("切换成功");
        alert.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                alert.dismiss();
            }


        }, 2000);
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
    public PtSettingPresenter createPresenter() {
        return new PtSettingPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventDrop event) {
        switch (event.getType()) {
            case "drop":
                PressureDropBean pressureDropBean = event.getPressureDropBean();
                numAdapter.getItem(mPosition).setDrop_num(pressureDropBean.getDrop_num());
                numAdapter.getItem(mPosition).setModuleList(pressureDropBean.getModuleList());
                numAdapter.notifyItemChanged(mPosition);
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

    }

    @Override
    public void OnVerificationPass(String data) {

        switch (data) {
            case "0":
                ToastUtil.showToast(context.getApplicationContext(), "密码错误!");
                break;
            case "1":
                SharedPreferences sharedPre = context.getSharedPreferences("ptDrop", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPre.edit();
                List<PressureDropBean> allData = numAdapter.getAllData();
                Gson gson = new Gson();
                String s = gson.toJson(allData);
                editor.putString("dropList", s);
                editor.commit();
                localList=allData;
                ToastUtil.showToast(context.getApplicationContext(), "本地参数设置成功");
                break;
        }


    }


    @Override
    public void OnGetPressureDropList(List<PressureDropBean> data) {
        if (localList.size() <= 0) {
            numAdapter.clear();
            numAdapter.addAll(data);
            numAdapter.notifyDataSetChanged();
        }
    }
}
