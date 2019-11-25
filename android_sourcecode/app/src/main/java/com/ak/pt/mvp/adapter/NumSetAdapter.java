package com.ak.pt.mvp.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.ModuleListBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by admin on 2019/1/18.
 */

public class NumSetAdapter extends RecyclerArrayAdapter<ModuleListBean> {
    public NumSetAdapter(Context context, List<ModuleListBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<ModuleListBean> {
        private TextView tvOne, tvTwo, tvThree, tv_left, tv_right, tvTimes;
        SeekBar seekBar, seekBar1, seekBar2;
        private ImageView imgOne, imgTwo, imgThree;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_num_set);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvThree = $(R.id.tvThree);
            seekBar = $(R.id.seekBar);
            seekBar1 = $(R.id.seekBar1);
            seekBar2 = $(R.id.seekBar2);
            imgOne = $(R.id.imgOne);
            imgTwo = $(R.id.imgTwo);
            imgThree = $(R.id.imgThree);
            tv_left = $(R.id.tv_left);
            tv_right = $(R.id.tv_right);
            tvTimes = $(R.id.tvTimes);

        }

        @Override
        public void setData(final ModuleListBean data) {
            super.setData(data);
            tvTimes.setText((getDataPosition() + 1) + "次模式设置");
            switch (data.getmState()) {
                case "1":
                    imgOne.setImageResource(R.drawable.pt_kg_h);
                    imgTwo.setImageResource(R.drawable.pt_time);
                    imgThree.setImageResource(R.drawable.pt_down);
                    tv_left.setText("0kg");
                    tv_right.setText("16kg");
                    seekBar.setVisibility(View.VISIBLE);
                    seekBar1.setVisibility(View.GONE);
                    seekBar2.setVisibility(View.GONE);
                    seekBar.setMax(16);
                    seekBar.setProgress(Integer.parseInt(data.getModule_num()));
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            String s = String.valueOf(progress);
                            tvOne.setText("模式" + s + "kg");
                            mOnSetClickListener.changeKg(s);
                            data.setModule_num(s);
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });

                    break;
                case "2":
                    imgOne.setImageResource(R.drawable.pt_kg);
                    imgTwo.setImageResource(R.drawable.pt_time_h);
                    imgThree.setImageResource(R.drawable.pt_down);
                    tv_left.setText("0min");
                    tv_right.setText("30min");
                    seekBar1.setVisibility(View.VISIBLE);
                    seekBar.setVisibility(View.GONE);
                    seekBar2.setVisibility(View.GONE);
                    seekBar1.setMax(30);
                    seekBar1.setProgress(Integer.parseInt(data.getModule_time()));
                    seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            String s1 = String.valueOf(progress);
                            if (s1.startsWith("0") && s1.length() > 1) {
                                String substring = s1.substring(0, s1.length());
                                tvTwo.setText("时间" + substring + "min");
                            } else {
                                tvTwo.setText("时间" + s1 + "min");
                            }

                            String s = String.valueOf(progress);
                            if (s.length() <= 1) {
                                data.setModule_time("0" + s);
                            } else {
                                data.setModule_time(s);
                            }

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });
                    break;
                case "3":
                    imgOne.setImageResource(R.drawable.pt_kg);
                    imgTwo.setImageResource(R.drawable.pt_time);
                    imgThree.setImageResource(R.drawable.pt_down_h);
                    tv_left.setText("0kg");
                    tv_right.setText("2kg");
                    seekBar2.setVisibility(View.VISIBLE);
                    seekBar1.setVisibility(View.GONE);
                    seekBar.setVisibility(View.GONE);
                    seekBar2.setMax(20);
                    String module_pressure = data.getModule_pressure();
                    int i = (int) (Float.parseFloat(module_pressure) * 10);
                    seekBar2.setProgress(i);
                    seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            BigDecimal num1 = new BigDecimal(String.valueOf(progress));
                            BigDecimal num2 = new BigDecimal("10");
                            BigDecimal divide = num1.divide(num2);
                            tvThree.setText("压降" + divide + "kg");
                            data.setModule_pressure(divide.toString());

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });
                    break;

            }
            tvOne.setText("模式" + data.getModule_num() + "kg");
            tvTwo.setText("时间" + data.getModule_time() + "min");
            tvThree.setText("压降" + data.getModule_pressure() + "kg");
            imgOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnSetClickListener.oneClick(getDataPosition());

                }
            });
            imgTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnSetClickListener.twoClick(getDataPosition());
                }
            });
            imgThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnSetClickListener.threeClick(getDataPosition());

                }
            });


        }
    }

    private OnSetClickListener mOnSetClickListener;

    public interface OnSetClickListener {
        void oneClick(int position);

        void twoClick(int position);

        void threeClick(int position);

        void changeKg(String mkg);
    }

    ;

    public void setmOnSetClickListener(OnSetClickListener mOnSetClickListener) {
        this.mOnSetClickListener = mOnSetClickListener;
    }
}