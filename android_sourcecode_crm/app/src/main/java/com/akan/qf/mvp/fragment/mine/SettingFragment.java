package com.akan.qf.mvp.fragment.mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.mvp.base.SimpleFragment;
import com.akan.qf.util.DataCleanManager;
import com.akan.qf.view.CustomDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public class SettingFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvPersonInfo)
    TextView tvPersonInfo;
    @BindView(R.id.tvAboutUs)
    TextView tvAboutUs;
    @BindView(R.id.tvClear)
    TextView tvClear;
    @BindView(R.id.tvSaftware)
    TextView tvSaftware;
    @BindView(R.id.tvSuggest)
    TextView tvSuggest;
    Unbinder unbinder;
    @BindView(R.id.cacheSize)
    TextView cacheSize;
    @BindView(R.id.exit)
    Button exit;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.setting);
        try {
            cacheSize.setText(DataCleanManager.getTotalCacheSize(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.tvPersonInfo, R.id.tvAccount, R.id.tvAboutUs, R.id.tvClear, R.id.tvPwd,
            R.id.tvSaftware, R.id.tvSuggest, R.id.ivLeft, R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvPersonInfo://个人信息
                startPersonalnfoFragment();
                break;
            case R.id.tvAccount://账户管理
                startAccountFragment();
                break;
            case R.id.tvPwd://修改密码
                startChangePwdFragment();
                break;//关于我们
            case R.id.tvAboutUs:
                break;
            case R.id.tvClear://清楚缓存
                DataCleanManager.clearAllCache(context);
                try {
                    cacheSize.setText(DataCleanManager.getTotalCacheSize(getContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tvSaftware://软件信息
                startSaftwareFragment();
                break;
            case R.id.tvSuggest://问题建议
                startSuggestFragment();
                break;
            case R.id.exit:
                exit();
                break;
        }
    }

    private void exit() {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage(getString(R.string.quit));
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                exitLogin();
                EventBus.getDefault().post(new FirstEvent("exit"));
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
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
