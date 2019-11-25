package com.ak.pt.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

public class IsInternet {

    /**
     * 判断网络情况
     * @param context 上下文
     * @return false 表示没有网络 true 表示有网络
     */
    public static boolean isNetworkAvalible(Context context) {
        // 获得网络状态管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 建立网络数组
            NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();

            if (net_info != null) {
                for (int i = 0; i < net_info.length; i++) {
                    // 判断获得的网络状态是否是处于连接状态
                    if (net_info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 如果没有网络，则弹出网络设置对话框
    public static void checkNetwork(final Activity activity) {
        if (!IsInternet.isNetworkAvalible(activity)) {
            final CustomDialog.Builder builder = new CustomDialog.Builder(activity);
            builder.setMessage("当前没有可以使用的网络，请设置网络！");
            builder.setPositiveButton("前往设置", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // 跳转到设置界面

                    activity.startActivityForResult(new Intent(
                                    Settings.ACTION_AIRPLANE_MODE_SETTINGS),
                            0);
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.onCreate().show();
        }
        return;
    }


}