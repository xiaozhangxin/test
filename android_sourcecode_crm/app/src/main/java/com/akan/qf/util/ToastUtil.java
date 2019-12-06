package com.akan.qf.util;

import android.content.Context;
import android.widget.Toast;

import com.akan.qf.bean.DepartmentEvent;
import com.akan.qf.bean.FirstEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by admin on 2018/10/19.
 */

public class ToastUtil {

    private static Toast toast;

    private ToastUtil() {
        throw new AssertionError();
    }

    public static void showToast(Context context, int resId) {
        showToast(context, context.getResources().getString(resId));
    }

    public static void showToast(Context context, int resId, int duration) {
        showToast(context, context.getResources().getString(resId), duration);
    }

    public static void showToast(Context context, CharSequence text) {
        if (text == null) {
            return;
        }
        if (text.toString().contains("Unable to resolve host") || text.toString().contains("Failed to connect to")) {
            showToast(context, "网络连接异常,请检查网络", Toast.LENGTH_SHORT);
        } else if (text.toString().contains("用户登陆时效已过期")) {
            EventBus.getDefault().post(new FirstEvent("expired"));
            EventBus.getDefault().post(new DepartmentEvent("expired"));
            showToast(context, text, Toast.LENGTH_SHORT);
        } else {
            showToast(context, text, Toast.LENGTH_SHORT);
        }
    }

    public static void showToast(Context context, String text, int duration, Object... args) {
        showToast(context, String.format(text, args), duration);
    }

    public static void showToast(Context context, CharSequence text, int duration) {

        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
        } else {
            toast.setText(text);
            toast.setDuration(duration);
        }
        toast.show();
    }
}
