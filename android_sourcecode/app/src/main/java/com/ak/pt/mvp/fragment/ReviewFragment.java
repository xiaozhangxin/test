package com.ak.pt.mvp.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/3/20.
 */

public class ReviewFragment extends BottomSheetDialogFragment {


    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.etContent)
    EditText etContent;
    @BindView(R.id.ok)
    Button ok;
    Unbinder unbinder;
    @BindView(R.id.line)
    TextView line;
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
        View view = inflater.inflate(R.layout.fragment_review, container);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private OnReviewClickListener onReviewClickListener;

    public void setOnReviewClickListener(OnReviewClickListener onReviewClickListener) {
        this.onReviewClickListener = onReviewClickListener;
    } public interface OnReviewClickListener {
        void ok(String content);
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
