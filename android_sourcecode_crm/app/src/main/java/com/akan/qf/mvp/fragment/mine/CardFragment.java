package com.akan.qf.mvp.fragment.mine;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.SimpleFragment;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.bumptech.glide.Glide;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 2018/11/8.
 */

public class CardFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.viewBg)
    View viewBg;
    @BindView(R.id.textView21)
    TextView textView21;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.ivAvatar)
    CircleImageView ivAvatar;
    @BindView(R.id.ivCode)
    ImageView ivCode;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.line)
    View line;
    Unbinder unbinder;
    private UserBean userBean;
    private Bitmap bmap;
    Bitmap image;
    public static CardFragment newInstance() {
        Bundle args = new Bundle();
        CardFragment fragment = new CardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_card;
    }

    @Override
    public void initUI() {
        tvTitle.setText("名片");
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        tvName.setText(userBean.getStaff_name());
        Glide.with(context).load(Constants.BASE_URL + userBean.getHead_img()).error(R.drawable.error_img).into(ivAvatar);

        ivAvatar.buildDrawingCache();
        bmap = ivAvatar.getDrawingCache();
         image = CodeUtils.createImage(userBean.getStaff_name(), 400, 400, bmap);
        ivCode.setImageBitmap(image);
    }

    @OnClick({R.id.ivLeft, R.id.textView4, R.id.textView3})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break; case R.id.textView3:
               // saveBitmap(image,"我的名片");
                MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), image, "title", "description");
                ToastUtil.showToast(context.getApplicationContext(),"保存成功");
                break;
            case R.id.textView4:
                UMImage image1 = new UMImage(getActivity(), image);
                new ShareAction(getActivity())
                        .withText("扫一扫加我哦")
                        .withMedia(image1)
                        .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE )
                        .setCallback(umShareListener)
                        .open();
                break;
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtil.showToast(context.getApplicationContext(),"分享成功啦");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtil.showToast(context.getApplicationContext(),"分享失败了");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtil.showToast(context.getApplicationContext(),"分享取消了");

        }
    };


    /**
     * 保存生成的二维码图片
     */
    private Uri imageFileUri;

    private void saveBitmap(Bitmap bitmap, String bitName){
        //获取与应用相关联的路径
        String imageFilePath = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        File imageFile = new File(imageFilePath,"/" + bitName);// 通过路径创建保存文件
        imageFileUri = Uri.fromFile(imageFile);
        if (imageFile.exists()) {
            imageFile.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(imageFile);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                out.flush();
                out.close();
                ToastUtil.showToast(context.getApplicationContext(),"保存成功");
            }
        } catch (Exception e) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}