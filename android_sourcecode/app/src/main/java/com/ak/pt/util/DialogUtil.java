package com.ak.pt.util;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.ak.pt.R;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DialogUtil {

    public static void showSingleChoiceDialog(final TextView textView, String title, final String[] items) {
        showSingleChoiceDialog(textView, title, items, (dialog, which) -> textView.setText(items[which]));
    }
    public static void showSingleChoiceDialog(TextView textView, String title, String[] items, DialogInterface.OnClickListener listener) {
        int choose = getIndex(items, textView.getText().toString());
        new AlertDialog.Builder(textView.getContext())
                .setTitle(title)
                .setSingleChoiceItems(items, choose, listener)
                .setNegativeButton("关闭", null)
                .show();
    }

    public static void showMultiChoiceDialog(final TextView textView, String title, String[] items) {
        showMultiChoiceDialog(textView, title, items, (items1, checkedItems) -> textView.setText(formatChoiceResult(items1, checkedItems)));
    }

    public static void showMultiChoiceDialog(TextView textView, String title, final String[] items, final OnMultiChoiceListener listener) {
        String[] source = textView.getText().toString().split(",");
        showMultiChoiceDialog(textView.getContext(), title, source, items, listener);
    }

    public static void showMultiChoiceDialog(Context context, String title, String[] source, final String[] items, final OnMultiChoiceListener listener) {
        final boolean[] checkedItems = new boolean[items.length];
        for (String item : source) {
            int index = getIndex(items, item);
            if(index >= 0) {
                checkedItems[index] = true;
            }
        }
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMultiChoiceItems(items, checkedItems, (dialog, which, isChecked) -> checkedItems[which] = isChecked)
                .setPositiveButton(context.getString(R.string.sure), (dialog, which) -> listener.onPositive(items, checkedItems))
                .show();
    }

    public static void showTimePickerDialog(TextView textView, String title){
        final SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DATE_FORMAT, Locale.CHINA);
        showTimePickerDialog(textView, title, DateUtil.DATE_FORMAT, (date, v) -> textView.setText(formatter.format(date)));
    }

    public static void showTimePickerDialog(TextView textView, String title, String dateFormat, OnTimeSelectListener listener){
        Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DATE_FORMAT, Locale.CHINA);
        try {
            calendar.setTime(formatter.parse(textView.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean[] type;
        switch (dateFormat){
            case DateUtil.TIME_FORMAT:
                type = new boolean[]{true, true, true, true, true, true};
                break;
            case DateUtil.DATE_FORMAT:
            default:
                type = new boolean[]{true, true, true, false, false, false};
                break;
        }
        new TimePickerBuilder(textView.getContext(), listener)
                .setDate(calendar)
                .setType(type)
                .setCancelText("取消")
                .setSubmitText("确认")
                .setTitleSize(15)
                .setSubCalSize(14)
                .setTitleText(title)
                .setOutSideCancelable(false)
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)
                .setTitleBgColor(0xFF4DA9EB)
                .setBgColor(0xFFFFFFFF)
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .isDialog(true)
                .build()
                .show();
    }

    public static String formatChoiceResult(CharSequence[] items, boolean[] checkedItems){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            if (checkedItems[i]) {
                if (TextUtils.isEmpty(s)) {
                    s.append(items[i]);
                } else {
                    s.append(",").append(items[i]);
                }
            }
        }
        return s.toString();
    }

    private static int getIndex(String[] items, String source) {
        for (int i = 0; i < items.length; ++i) {
            if (items[i].equals(source)) {
                return i;
            }
        }
        return -1;
    }

    public interface OnMultiChoiceListener {
        void onPositive(CharSequence[] items, boolean[] checkedItems);
    }
}
