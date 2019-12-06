package com.akan.qf.mvp.adapter.mine;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.ContributeBean;
import com.akan.qf.bean.ContributeCommentBeansBean;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 2018/11/26.
 */

public class CommentListAdapter extends RecyclerArrayAdapter<ContributeCommentBeansBean> {


    public CommentListAdapter(Context context, List<ContributeCommentBeansBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_comment);
    }

    public class MyImageHolder extends BaseViewHolder<ContributeCommentBeansBean> {

        private TextView tvName, tvNum, tvTime, tvContent;
        private CircleImageView ivAvatar;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tvName = $(R.id.tvName);
            tvNum = $(R.id.tvNum);
            tvTime = $(R.id.tvTime);
            tvContent = $(R.id.tvContent);
            ivAvatar = $(R.id.ivAvatar);

        }

        @Override
        public void setData(final ContributeCommentBeansBean data) {
            Glide.with(getContext())
                    .load(Constants.BASE_URL + data.getHead_img())
                    .error(R.drawable.error_img)
                    .into(ivAvatar);
            tvName.setText(data.getStaff_name());
            tvNum.setText((getDataPosition() + 1) + "楼");
            tvTime.setText(data.getCreate_time());
            tvContent.setText(data.getContent());

        }
    }


}
