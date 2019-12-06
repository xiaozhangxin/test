package com.akan.qf.view;

import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.akan.qf.R;


/**
 * Created by Administrator on 2016/9/16.
 */
public class ERROEDialogFragment extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true); // 外部点击取消
        View view = inflater.inflate(R.layout.er_dialog, container);
        setLayout();
        initView(view);
        return view;
    }

    private void initView(View mDetailDialog) {
        TextView tv_da = (TextView) mDetailDialog.findViewById(R.id.tv_da);
        tv_da.setText(getArguments().getString("da"));
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                dismiss();
            }

        }, 3000);
    }

    private void setLayout() {
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.dialog_exit_live_animation);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.dimAmount = 0;

        window.setAttributes(lp);
    }
}
