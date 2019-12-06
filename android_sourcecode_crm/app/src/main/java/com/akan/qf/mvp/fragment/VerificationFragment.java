package com.akan.qf.mvp.fragment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.MainActivity;
import com.akan.qf.R;
import com.akan.qf.bean.AppVersionBean;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.LoginPresenter;
import com.akan.qf.mvp.view.ILoginView;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.CustomDialog;
import com.akan.qf.view.DialogLoadding;
import com.akan.qf.view.SecurityCodeView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2019/3/11.
 */

public class VerificationFragment extends BaseFragment<ILoginView, LoginPresenter> implements ILoginView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvSecurityView)
    SecurityCodeView tvSecurityView;
    @BindView(R.id.tvTime)
    TextView tvTime;
    Unbinder unbinder;
    @BindView(R.id.tvSend)
    TextView tvSend;
    private Map<String, String> map = new HashMap<>();
    private UserBean mUserBean;
    private String uuid;
    private DialogLoadding dialogLoadding;

    public static VerificationFragment newInstance(UserBean mUserBean, String uuid) {
        Bundle args = new Bundle();
        VerificationFragment fragment = new VerificationFragment();
        fragment.mUserBean = mUserBean;
        fragment.uuid = uuid;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_verification;
    }

    @Override
    public void initUI() {
        tvTitle.setVisibility(View.GONE);
        String phone = "系统监测到您的设备更换，已向" + mUserBean.getPhone() + "发送验证码短信，请查看短信并输入验证码";
        Spannable sp = new SpannableString(phone);
        sp.setSpan(new AbsoluteSizeSpan(16, true), 0, 14, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 0, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new AbsoluteSizeSpan(22, true), 14, 25, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 14, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new AbsoluteSizeSpan(16, true), 25, phone.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 25, phone.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvPhone.setText(sp);
        tvSecurityView.setInputCompleteListener(new SecurityCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                if (tvSecurityView.getTextContent().length() >= 4) {
                    map.clear();
                    map.put("terminal_type", "app");
                    map.put("code", tvSecurityView.getTextContent());
                    map.put("staff_account", mUserBean.getStaff_account());
                    map.put("staff_password", mUserBean.getStaff_password());
                    map.put("app_sign", uuid);
                    getPresenter().getLogin("", map);
                    dialogLoadding = new DialogLoadding(context);
                    dialogLoadding.showDialog();
                }
            }

            @Override
            public void deleteContent() {

            }
        });

    }

    @Override
    public void initData() {
        getCode();
    }

    //获取验证码
    private void getCode() {
        map.clear();
        map.put("mobile", mUserBean.getPhone());
        map.put("staff_account", mUserBean.getStaff_account());
        getPresenter().insertCode(map);
    }

    @OnClick({R.id.ivLeft, R.id.tvSend})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvSend:
                final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                builder.setMessage("重新获取短信验证码?");
                builder.setPositiveButton("立即获取", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        getCode();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("再等等", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.onCreate().show();


                break;
        }
    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvTime.setText("接收短信大概需要" + millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            tvTime.setVisibility(View.GONE);
            tvSend.setVisibility(View.VISIBLE);

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
        unbinder.unbind();
    }

    @Override
    public void onGetlogin(UserBean data) {
        dialogLoadding.closeDialog();
        String[] split = data.getGroup_parent_uuid().split("#");
        data.setGroup_parent_uuid_new(split[split.length - 2]);
        saveUser(data);
        getList(data);
        JPushInterface.setAlias(context.getApplicationContext(), data.getStaff_id(), new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
            }
        });
        startActivity(MainActivity.class);
        finish();
        EventBus.getDefault().post(new FirstEvent("finishLogin"));
    }

    @Override
    public void OnTnsertCode(String data) {
        ToastUtil.showToast(context.getApplicationContext(), "验证码已发送");
        tvTime.setVisibility(View.VISIBLE);
        tvSend.setVisibility(View.GONE);
        timer.start();
    }

    @Override
    public void OnGetAppVersionDetail(AppVersionBean data) {

    }

    private void getList(UserBean bean) {
        SharedPreferences sp = context.getSharedPreferences("userList", MODE_PRIVATE);
        String list = sp.getString("list", "");
        if (TextUtils.isEmpty(list) | "[]".equals(list)) {
            ArrayList<UserBean> newList = new ArrayList();
            newList.add(bean);
            String toJson = new Gson().toJson(newList);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("list", toJson);
            editor.commit();
        } else {
            boolean ishave = false;
            List<UserBean> oldList = new ArrayList();
            Gson gson = new Gson();
            JsonArray array = new JsonParser().parse(list).getAsJsonArray();
            for (JsonElement elem : array) {
                UserBean usern = gson.fromJson(elem, UserBean.class);
                if (usern.getStaff_id().equals(bean.getStaff_id())) {
                    ishave = true;
                    oldList.add(bean);
                } else {
                    oldList.add(usern);
                }
            }
            if (!ishave) {
                oldList.add(bean);
            }
            String toJson = new Gson().toJson(oldList);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("list", toJson);
            editor.commit();

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


}
