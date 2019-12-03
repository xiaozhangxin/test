package com.akan.wms.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.akan.wms.R;

public class BottomPopWindow extends PopupWindow implements View.OnClickListener {

    private Button btOne, btTwo, btThree, btFour;
    private View mPopView, lineTwo,lineOne;
    private OnItemClickListener mListener;

    public BottomPopWindow(Context context) {
        super(context);
        init(context);
        setPopupWindow();
        btOne.setOnClickListener(this);
        btTwo.setOnClickListener(this);
        btThree.setOnClickListener(this);
        btFour.setOnClickListener(this);
    }

    public void setTwoGone() {
        btTwo.setVisibility(View.GONE);
        lineTwo.setVisibility(View.GONE);
    }
  public void setOneGone() {
        btOne.setVisibility(View.GONE);
      lineOne.setVisibility(View.GONE);
    }


    public void setOneName(String name) {
        btOne.setText(name);
    }
    public void setTwoName(String name) {
        btTwo.setText(name);
    }

    /**
     * 初始化
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mPopView = inflater.inflate(R.layout.bottom_popup_window, null);
        btOne = mPopView.findViewById(R.id.btOne);
        btTwo = mPopView.findViewById(R.id.btTwo);
        btThree = mPopView.findViewById(R.id.btThree);
        btFour = mPopView.findViewById(R.id.btFour);
        lineTwo = mPopView.findViewById(R.id.lineTwo);
        lineOne = mPopView.findViewById(R.id.lineOne);
    }

    /**
     * 设置窗口的相关属性
     */
    @SuppressLint("InlinedApi")
    private void setPopupWindow() {
        this.setContentView(mPopView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.Bottom_popwindow_anim_style);
        this.setBackgroundDrawable(new ColorDrawable(0x66000000));
        mPopView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int height = mPopView.findViewById(R.id.id_pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }


    /**
     * 定义一个接口，公布出去 在Activity中操作按钮的单击事件
     */
    public interface OnItemClickListener {
        void setOnItemClick(View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.setOnItemClick(v);
        }
    }

}
