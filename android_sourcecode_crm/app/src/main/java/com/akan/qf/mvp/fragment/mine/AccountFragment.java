package com.akan.qf.mvp.fragment.mine;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.DepartmentBean;
import com.akan.qf.bean.DepartmentEvent;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.SimpleFragment;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.slide.InventoryAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2019/4/3.
 */

public class AccountFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;

    @BindView(R.id.tvAdd)
    TextView tvAdd;
    Unbinder unbinder;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private List<UserBean> list;
    private InventoryAdapter adapter;
    UserBean userBean;
    public static AccountFragment newInstance() {
        Bundle args = new Bundle();
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_account;
    }

    @Override
    public void initUI() {
        tvTitle.setText("账号管理");


        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        recycleView.setNestedScrollingEnabled(false);
        adapter = new InventoryAdapter(context, list);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                List<UserBean> allData = adapter.getAllData();
                for (int i=0;i<allData.size();i++){
                    allData.get(i).setNow(false);
                }
                adapter.getItem(position).setNow(true);
                saveUser(adapter.getItem(position));
                adapter.notifyDataSetChanged();
                EventBus.getDefault().post(new DepartmentEvent("changeAccount", new DepartmentBean("","")));
                EventBus.getDefault().post(new FirstEvent("changeAccount"));
                saveLoginInfo(context,adapter.getItem(position).getStaff_account(),adapter.getItem(position).getStaff_password());
                ToastUtil.showToast(context.getApplicationContext(),"账号切换成功");
            }
        });
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final int position) {
                if (userBean.getStaff_id().equals(adapter.getItem(position).getStaff_id())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("此账号为当前登陆账号,是否确认删除?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.remove(position);
                            saveList();
                            exitLogin();
                            EventBus.getDefault().post(new FirstEvent("exit"));
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("你确定要删除此账号吗?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            adapter.remove(position);
                            saveList();
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

                return false;
            }
        });

    }

    /**
     * 使用SharedPreferences保存用户登录信息
     *
     * @param context
     * @param username
     */
    public static void saveLoginInfo(Context context, String username, String pwd) {
        SharedPreferences sharedPre = context.getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("username", username);
        editor.putString("pwd", pwd);
        editor.commit();
    }

    private void saveList(){
        SharedPreferences sp = context.getSharedPreferences("userList", MODE_PRIVATE);
        String toJson = new Gson().toJson(adapter.getAllData());
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("list", toJson);
        editor.commit();
    }
    private void getList() {
        SharedPreferences sp = context.getSharedPreferences("userList", MODE_PRIVATE);
        String list = sp.getString("list", "");
        if (!list.equals("") && list.length() > 0) {
            Gson gson = new Gson();
            JsonArray array = new JsonParser().parse(list).getAsJsonArray();
            for (JsonElement elem : array) {
                UserBean usern = gson.fromJson(elem, UserBean.class);
                if (userBean.getStaff_id().equals(usern.getStaff_id())){
                    usern.setNow(true);
                }else {
                    usern.setNow(false);
                }
                adapter.add(usern);
            }

            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getList();
    }

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tvAdd})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvAdd:
                startLogin();
            case R.id.tvRight:
                break;
        }
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
}
