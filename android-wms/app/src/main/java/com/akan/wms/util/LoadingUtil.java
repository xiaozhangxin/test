package com.akan.wms.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;

public class LoadingUtil {
    public static Dialog createLoading(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_loading_view);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
        tipTextView.setText(msg);
        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);
        loadingDialog.setCancelable(true);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();

        return loadingDialog;
    }

    /**
     * 关闭dialog
     *
     * @param mDialogUtils
     */
    public static void closeDialog(Dialog mDialogUtils) {
        if (mDialogUtils != null && mDialogUtils.isShowing()) {
            mDialogUtils.dismiss();
        }
    }


}
