package com.akan.qf.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/7/9.
 */

public class ReviewFragment extends DialogFragment {


    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.etContent)
    EditText etContent;
    @BindView(R.id.ok)
    Button ok;
    Unbinder unbinder;
    @BindView(R.id.close)
    ImageView close;
    private String type;

    public static ReviewFragment newInstance(String type) {
        Bundle args = new Bundle();
        ReviewFragment fragment = new ReviewFragment();
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_new, container);
        setLayout();
        unbinder = ButterKnife.bind(this, view);
        switch (type) {
            case "0":
                tvTittle.setText("审阅");
                break;
            case "1":
                tvTittle.setText("审阅回复");
                break;
        }
        return view;

    }
    private void setLayout() {
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawableResource(R.color.black_transparent);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.dimAmount = 0;
        window.setAttributes(lp);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public interface OnReviewClickListener {
        void ok(String content);
    }
    private OnReviewClickListener onReviewClickListener;

    public void setOnReviewClickListener(OnReviewClickListener onReviewClickListener) {
        this.onReviewClickListener = onReviewClickListener;
    }

    @OnClick({R.id.ok, R.id.close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ok:
                String s = etContent.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    ToastUtil.showToast(getContext().getApplicationContext(), "请输入意见");
                    return;
                }
                onReviewClickListener.ok(s);
                dismiss();
                break;
            case R.id.close:
                dismiss();
                break;
        }
    }



}
