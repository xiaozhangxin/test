package com.akan.qf.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2018/9/26.
 */

public class CashierInputFilter implements InputFilter {
    Pattern mPattern;
    private static final int MAX_VALUE = 999999999;
    private static int POINTER_LENGTH = 2;
    private static final String POINTER = ".";
    private static final String ZERO = "0";

    public CashierInputFilter(int pointer) {
        POINTER_LENGTH=pointer;
        mPattern = Pattern.compile("([0-9]|\\.)*");
    }


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String sourceText = source.toString();
        String destText = dest.toString();
        if (TextUtils.isEmpty(sourceText)) {
            return "";
        }
        Matcher matcher = mPattern.matcher(source);
        if (destText.contains(POINTER)) {
            if (!matcher.matches()) {
                return "";
            } else {
                if (POINTER.equals(source.toString())) {
                    return "";
                }
            }
            int index = destText.indexOf(POINTER);
            int length = dend - index;
            if (length > POINTER_LENGTH) {
                return dest.subSequence(dstart, dend);
            }
        } else {
            /**
         * 没有输入小数点的情况下，只能输入小数点和数字
         * 1. 首位不能输入小数点
             * * 2. 如果首位输入0，则接下来只能输入小数点了  */
            if (!matcher.matches()) {
                return "";
            } else {
                if ((POINTER.equals(source.toString())) && TextUtils.isEmpty(destText)) {
                    return "";
                } else if (!POINTER.equals(source.toString()) && ZERO.equals(destText)) {
                    return "";
                }
            }
        }
        double sumText = Double.parseDouble(destText + sourceText);
        if (sumText > MAX_VALUE) {
            return dest.subSequence(dstart, dend);
        }
        return dest.subSequence(dstart, dend) + sourceText;
    }
}